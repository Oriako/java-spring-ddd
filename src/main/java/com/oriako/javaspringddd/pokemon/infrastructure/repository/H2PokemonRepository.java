package com.oriako.javaspringddd.pokemon.infrastructure.repository;

import com.oriako.javaspringddd.pokemon.domain.Pokemon;
import com.oriako.javaspringddd.pokemon.domain.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public final class H2PokemonRepository implements PokemonRepository {

    @Autowired
    private JpaRepository<Pokemon, Integer> jpaRepository;

    @Override
    public Pokemon create(Pokemon pokemon) {
        return jpaRepository.save(pokemon);
    }

    @Override
    public Pokemon update(Pokemon pokemon) {
        return jpaRepository.save(pokemon);
    }

}
