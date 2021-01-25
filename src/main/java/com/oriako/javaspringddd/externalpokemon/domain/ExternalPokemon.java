package com.oriako.javaspringddd.externalpokemon.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExternalPokemon {

    @JsonProperty("base_experience")
    private Integer baseExperience;
    @JsonProperty("game_indices")
    private List<ExternalPokemonGameIndex> gameIndexList;
    @JsonProperty("name")
    private String name;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("weight")
    private Integer weight;

}
