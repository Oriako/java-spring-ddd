package com.oriako.javaspringddd.pokemon.domain.read;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.oriako.javaspringddd.pokemon.domain.PokemonVO;
import lombok.Data;

import java.util.Collection;

@Data
public class PokemonReadResponse {

    @JsonProperty("pokemonList")
    private Collection<PokemonVO> pokemonList;

}
