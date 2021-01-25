package com.practice.pokemonddd.pokemon.infrastructure;

import com.practice.pokemonddd.pokemon.domain.Pokemon;
import com.practice.pokemonddd.pokemon.domain.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class H2PokemonRepository implements PokemonRepository {

    @Autowired
    private JpaPokemonRepository jpaRepository;

    @Override
    public Pokemon create(Pokemon pokemon) {
        return jpaRepository.save(pokemon);
    }

    @Override
    public Pokemon update(Pokemon pokemon) {
        return jpaRepository.save(pokemon);
    }

}
