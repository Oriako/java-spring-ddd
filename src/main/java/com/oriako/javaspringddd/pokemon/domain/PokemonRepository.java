package com.practice.pokemonddd.pokemon.domain;

public interface PokemonRepository {

    public Pokemon create(Pokemon pokemonId);
    public Pokemon update(Pokemon pokemon);

}
