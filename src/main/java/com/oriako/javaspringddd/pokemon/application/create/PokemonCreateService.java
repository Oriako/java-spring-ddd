package com.oriako.javaspringddd.pokemon.application.create;

import com.oriako.javaspringddd.pokemon.domain.Pokemon;
import com.oriako.javaspringddd.pokemon.domain.PokemonName;
import com.oriako.javaspringddd.pokemon.domain.PokemonRepository;
import com.oriako.javaspringddd.pokemon.domain.create.PokemonCreatedDomainEvent;
import org.springframework.stereotype.Service;

@Service
public class PokemonCreateService {

    private PokemonRepository pokemonRepository;

    public PokemonCreateService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public void create(PokemonName pokemonName, Integer weight, Integer height, Integer baseExp) {
        Pokemon pokemon = Pokemon.create(pokemonName, weight, height, baseExp);
        pokemonRepository.create(pokemon);

        // Publisher
        pokemon.getDomainEventCollection();
    }

}
