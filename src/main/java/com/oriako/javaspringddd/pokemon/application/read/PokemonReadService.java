package com.oriako.javaspringddd.pokemon.application.read;

import com.oriako.javaspringddd.pokemon.domain.Pokemon;
import com.oriako.javaspringddd.pokemon.domain.PokemonName;
import com.oriako.javaspringddd.pokemon.domain.PokemonRepository;
import org.springframework.stereotype.Service;

@Service
public class PokemonReadService {

    private PokemonRepository pokemonRepository;

    public PokemonReadService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public Pokemon read (PokemonName pokemonName) {
        return pokemonRepository.read(pokemonName);
    }

}
