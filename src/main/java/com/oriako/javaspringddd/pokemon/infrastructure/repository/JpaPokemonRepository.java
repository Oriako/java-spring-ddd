package com.oriako.javaspringddd.pokemon.infrastructure.repository;

import com.oriako.javaspringddd.pokemon.domain.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPokemonRepository extends JpaRepository<Pokemon, String> {

}
