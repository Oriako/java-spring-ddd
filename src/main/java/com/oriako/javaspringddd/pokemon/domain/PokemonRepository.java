package com.oriako.javaspringddd.pokemon.domain;

public interface PokemonRepository {

    public Pokemon create(Pokemon pokemon);
    public Pokemon update(Pokemon pokemon);
    public Pokemon read(PokemonName pokemonName);

}
