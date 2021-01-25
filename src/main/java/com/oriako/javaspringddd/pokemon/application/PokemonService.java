package com.practice.pokemonddd.pokemon.application;

import com.practice.pokemonddd.pokemon.domain.Pokemon;
import com.practice.pokemonddd.pokemon.domain.PokemonName;
import com.practice.pokemonddd.pokemon.domain.PokemonRepository;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {

    private PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public Pokemon create(PokemonName pokemonId) {
        Pokemon pokemon = Pokemon.create(pokemonId);
        return pokemonRepository.create(pokemon);
    }

}
