package com.oriako.javaspringddd.pokemon.domain.read;

import com.oriako.javaspringddd.pokemon.domain.PokemonVO;
import com.oriako.javaspringddd.shared.domain.querybus.Query;

public final class ReadPokemonQuery extends Query<PokemonVO> {

    private String pokemonName;

    public ReadPokemonQuery(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public String getPokemonName() {
        return pokemonName;
    }

}
