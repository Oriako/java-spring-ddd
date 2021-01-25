package com.practice.pokemonddd.externalpokemon.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExternalPokemonVersion {

    @JsonProperty("name")
    private String name;
    @JsonProperty("url")
    private String url;

}
