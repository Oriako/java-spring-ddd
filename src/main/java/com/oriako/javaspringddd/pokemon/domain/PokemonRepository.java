package com.oriako.javaspringddd.pokemon.domain;

import java.util.Collection;
import java.util.Optional;

public interface PokemonRepository {

    public Pokemon create(Pokemon pokemon);
    public Pokemon update(Pokemon pokemon);
    public Pokemon read(PokemonName pokemonName);
    public Collection<Pokemon> readAllOrderedBy(Optional<String> orderAttribute, Optional<Boolean> ascending, Optional<Integer> size, Optional<Integer> page);

}
