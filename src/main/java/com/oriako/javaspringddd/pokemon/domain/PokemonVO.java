package com.oriako.javaspringddd.pokemon.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PokemonVO {

    @JsonProperty("name")
    private String pokemonName;
    @JsonProperty("weight")
    private Integer weight;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("baseExp")
    private Integer baseExp;

    public static PokemonVO empty() {
        return new PokemonVO();
    }

}
