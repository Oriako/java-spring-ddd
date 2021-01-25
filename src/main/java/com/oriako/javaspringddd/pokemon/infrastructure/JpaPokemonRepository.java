package com.practice.pokemonddd.pokemon.infrastructure;

import com.practice.pokemonddd.pokemon.domain.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPokemonRepository extends JpaRepository<Pokemon, Integer> {

}
