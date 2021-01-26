package com.oriako.javaspringddd.externalpokemon.application;

import com.oriako.javaspringddd.externalpokemon.domain.ExternalPokemon;
import com.oriako.javaspringddd.externalpokemon.domain.ExternalPokemonGameIndex;
import com.oriako.javaspringddd.externalpokemon.domain.ExternalPokemonInfo;
import com.oriako.javaspringddd.externalpokemon.domain.ExternalPokemonList;
import com.oriako.javaspringddd.shared.domain.IObjectMapper;
import com.oriako.javaspringddd.shared.infrastructure.LocalHostURI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExternalPokemonReader {

    private static final Logger LOG = LoggerFactory.getLogger(ExternalPokemonReader.class);
    private IObjectMapper objectMapper;

    public ExternalPokemonReader(IObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public int loadPokemonData(String versionName) throws Throwable {
        int count = 0;

        final RestTemplate restTemplate = new RestTemplate();
        HttpClient client = HttpClient.newHttpClient();

        String nextPage = "https://pokeapi.co/api/v2/pokemon/";
        do {
            int countDiff = 0;

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(nextPage)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            try {
                ExternalPokemonList pokemonListResponse = objectMapper.parseStringToObject(response.body(), ExternalPokemonList.class);
                nextPage = pokemonListResponse.getNext();

                List<ExternalPokemonInfo> resultList = pokemonListResponse.getResults();

                if (resultList != null) {

                    for (ExternalPokemonInfo result : resultList) {
                        HttpRequest detailRequest = HttpRequest.newBuilder().uri(URI.create(result.getUrl())).build();
                        HttpResponse<String> detailResponse = client.send(detailRequest, HttpResponse.BodyHandlers.ofString());
                        ExternalPokemon pokemonDetailResponse = objectMapper.parseStringToObject(detailResponse.body(), ExternalPokemon.class);
                        List<ExternalPokemonGameIndex> versionList = pokemonDetailResponse.getGameIndexList();
                        if (versionList != null) {
                            if (versionList.stream().filter(p -> p.getVersion().getName().equals(versionName)).count() > 0) {
                                // Creo que lo ideal sería lanzar un evento de pokemon leído para que lo capturase nuestro dominio de pokemon local y lo guardase, por ahora, hacemos una conexión entre servicios
                                Map<String,Object> requestParameters = new HashMap<>();
                                requestParameters.put("pokemonName", pokemonDetailResponse.getName());
                                requestParameters.put("height", pokemonDetailResponse.getHeight());
                                requestParameters.put("weight", pokemonDetailResponse.getWeight());
                                requestParameters.put("baseExp", pokemonDetailResponse.getBaseExperience());
                                String createResponse = restTemplate.getForObject(LocalHostURI.createURI("pokemon/create", requestParameters), String.class);
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
