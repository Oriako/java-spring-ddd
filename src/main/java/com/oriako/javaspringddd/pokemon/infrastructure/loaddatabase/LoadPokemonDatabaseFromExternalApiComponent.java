package com.oriako.javaspringddd.pokemon.infrastructure.loaddatabase;

import com.oriako.javaspringddd.pokemon.domain.create.PokemonCreateCommand;
import com.oriako.javaspringddd.pokemon.domain.loaddatabase.ExternalPokemon;
import com.oriako.javaspringddd.pokemon.domain.loaddatabase.ExternalPokemonGameIndex;
import com.oriako.javaspringddd.pokemon.domain.loaddatabase.ExternalPokemonInfo;
import com.oriako.javaspringddd.pokemon.domain.loaddatabase.ExternalPokemonList;
import com.oriako.javaspringddd.shared.application.NullAccepterObjectMapper;
import com.oriako.javaspringddd.shared.infrastructure.eventbus.AsyncSpringCommandBus;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.UUID;

@Lazy
@Component
public class LoadPokemonDatabaseFromExternalApiComponent implements ApplicationListener<ApplicationReadyEvent>  {

    private static final Logger LOG = LoggerFactory.getLogger(LoadPokemonDatabaseFromExternalApiComponent.class);
    private static final String POKEAPI_URL_BASE = "https://pokeapi.co/api/v2/pokemon/";
    private static final String REQUIRED_VERSION = "red";

    @Autowired
    private AsyncSpringCommandBus commandBus;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            String nextPage = POKEAPI_URL_BASE;

            do {
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(nextPage)).build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                ExternalPokemonList pokemonListResponse = NullAccepterObjectMapper.getInstance().parseStringToObject(response.body(), ExternalPokemonList.class);
                nextPage = pokemonListResponse.getNext();
                List<ExternalPokemonInfo> listOfPokemonUrl = pokemonListResponse.getResults();
                if (CollectionUtils.isEmpty(listOfPokemonUrl)) {
                    continue;
                }

                for (ExternalPokemonInfo pokemonInfo : listOfPokemonUrl) {

                    if (StringUtils.isBlank(pokemonInfo.getUrl())) {
                        continue;
                    }

                    HttpRequest detailRequest = HttpRequest.newBuilder().uri(URI.create(pokemonInfo.getUrl())).build();
                    HttpResponse<String> detailResponse = client.send(detailRequest, HttpResponse.BodyHandlers.ofString());
                    ExternalPokemon externalPokemon = NullAccepterObjectMapper.getInstance().parseStringToObject(detailResponse.body(), ExternalPokemon.class);

                    if (!isVersionMatchingRequiredVersion(REQUIRED_VERSION, externalPokemon)) {
                        continue;
                    }

                    PokemonCreateCommand createCommand = new PokemonCreateCommand(UUID.randomUUID(), externalPokemon.getName(), externalPokemon.getWeight(), externalPokemon.getHeight(), externalPokemon.getBaseExperience());
                    commandBus.dispatch(createCommand);
                }

            } while (StringUtils.isNotBlank(nextPage));

            LOG.info("ALL REQUESTED CREATED");
        } catch (Throwable e) {
            LOG.error("ERROR LOADING DATA");
        }
    }

    private boolean isVersionMatchingRequiredVersion(String requiredVersion, ExternalPokemon externalPokemon) {

        if (StringUtils.isBlank(requiredVersion)) {
            return true;
        }

        List<ExternalPokemonGameIndex> versionList = externalPokemon.getGameIndexList();
        if (CollectionUtils.isEmpty(versionList)) {
            return false;
        }

        return versionList.stream().anyMatch(p -> StringUtils.equals(p.getVersion().getName(), requiredVersion));
    }

}
