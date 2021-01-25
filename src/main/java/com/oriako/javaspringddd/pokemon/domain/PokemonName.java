package com.oriako.javaspringddd.pokemon.domain;

import lombok.Data;

@Data
public class PokemonName {

    private String name;

    public PokemonName(String name) {
        this.name = name;
    }
}
