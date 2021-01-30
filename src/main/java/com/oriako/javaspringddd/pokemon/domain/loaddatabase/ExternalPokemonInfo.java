package com.oriako.javaspringddd.pokemon.domain.loaddatabase;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExternalPokemonInfo {

    @JsonProperty("name")
    private String name;
    @JsonProperty("url")
    private String url;

}
