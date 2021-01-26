package com.oriako.javaspringddd.pokemon.application.create;

import com.oriako.javaspringddd.pokemon.domain.Pokemon;
import com.oriako.javaspringddd.pokemon.domain.PokemonName;
import com.oriako.javaspringddd.pokemon.domain.PokemonRepository;
import org.springframework.stereotype.Service;

@Service
public class PokemonCreateService {

    private PokemonRepository pokemonRepository;

    public PokemonCreateService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public Pokemon create(PokemonName pokemonId, Integer weight, Integer height, Integer baseExp) {
        Pokemon pokemon = Pokemon.create(pokemonId, weight, height, baseExp);
        return pokemonRepository.create(pokemon);
    }

}
