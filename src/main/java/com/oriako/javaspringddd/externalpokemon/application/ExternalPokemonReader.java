package com.practice.pokemonddd.externalpokemon.application;

import com.practice.pokemonddd.externalpokemon.domain.ExternalPokemon;
import com.practice.pokemonddd.externalpokemon.domain.ExternalPokemonGameIndex;
import com.practice.pokemonddd.externalpokemon.domain.ExternalPokemonInfo;
import com.practice.pokemonddd.externalpokemon.domain.ExternalPokemonList;
import com.practice.pokemonddd.shared.application.OriakoObjectMapper;
import com.practice.pokemonddd.shared.infrastructure.LocalHostURI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ExternalPokemonReader {

    private static final Logger LOG = LoggerFactory.getLogger(ExternalPokemonReader.class);

    public int loadPokemonData(String versionName) throws Throwable {
        int count = 0;

        HttpClient client = HttpClient.newHttpClient();

        String nextPage = "https://pokeapi.co/api/v2/pokemon/";
        do {
            int countDiff = 0;

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(nextPage)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            try {
                ExternalPokemonList pokemonListResponse = OriakoObjectMapper.getInstance().readValue(response.body(), ExternalPokemonList.class);
                nextPage = pokemonListResponse.getNext();

                List<ExternalPokemonInfo> resultList = pokemonListResponse.getResults();

                if (resultList != null) {

                    for (ExternalPokemonInfo result : resultList) {
                        HttpRequest detailRequest = HttpRequest.newBuilder().uri(URI.create(result.getUrl())).build();
                        HttpResponse<String> detailResponse = client.send(detailRequest, HttpResponse.BodyHandlers.ofString());
                        ExternalPokemon pokemonDetailResponse = OriakoObjectMapper.getInstance().readValue(detailResponse.body(), ExternalPokemon.class);
                        List<ExternalPokemonGameIndex> versionList = pokemonDetailResponse.getGameIndexList();
                        if (versionList != null) {
                            if (versionList.stream().filter(p -> p.getVersion().getName().equals("red")).count() > 0) {
                                // Creo que lo ideal sería lanzar un evento de pokemon leído para que lo capturase nuestro dominio de pokemon local y lo guardase, por ahora, hacemos una conexión entre servicios
                                String createResponse = new RestTemplate().getForObject(new LocalHostURI("pokemon/create?pokemonName=" + pokemonDetailResponse.getName()).getURI(), String.class);
                                if (createResponse.equals("OK")) {
                                    countDiff++;
                                }
                            }
                        }
                    }
                }

                if (count > 0 && countDiff == 0) {
                    break;
                }
                count += countDiff;

                LOG.info("Added " + count + " Pokemon to DB");
            } catch (Throwable e) {
                LOG.error(e.getMessage());
                nextPage = null;
            }

        } while (nextPage != null && !nextPage.isBlank());

        return count;
    }

}
