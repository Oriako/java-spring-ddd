package com.oriako.javaspringddd.pokemon.application.read;

import com.oriako.javaspringddd.pokemon.domain.Pokemon;
import com.oriako.javaspringddd.pokemon.domain.PokemonName;
import com.oriako.javaspringddd.pokemon.domain.PokemonRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PokemonReadService {

    private PokemonRepository pokemonRepository;

    public PokemonReadService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public Pokemon read(PokemonName pokemonName) {
        return pokemonRepository.read(pokemonName);
    }

    public Collection<Pokemon> readAllOrderedBy(Optional<String> orderAttribute, Optional<Boolean> ascending, Optional<Integer> size, Optional<Integer> page) {
        return pokemonRepository.readAllOrderedBy(orderAttribute, ascending, size, page);
    }

}
