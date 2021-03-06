package com.oriako.javaspringddd.pokemon.domain.loaddatabase;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExternalPokemonGameIndex {

    @JsonProperty("game_index")
    private Integer gameIndex;
    @JsonProperty("version")
    private ExternalPokemonVersion version;

}
