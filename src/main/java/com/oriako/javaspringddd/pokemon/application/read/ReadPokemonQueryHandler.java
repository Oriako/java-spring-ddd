package com.oriako.javaspringddd.pokemon.application.read;

import com.oriako.javaspringddd.pokemon.application.create.PokemonCreateService;
import com.oriako.javaspringddd.pokemon.domain.Pokemon;
import com.oriako.javaspringddd.pokemon.domain.PokemonName;
import com.oriako.javaspringddd.pokemon.domain.PokemonVO;
import com.oriako.javaspringddd.pokemon.domain.create.PokemonCreateCommand;
import com.oriako.javaspringddd.pokemon.domain.read.ReadPokemonQuery;
import com.oriako.javaspringddd.pokemon.infrastructure.repository.H2PokemonRepository;
import com.oriako.javaspringddd.shared.domain.querybus.IQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public final class ReadPokemonQueryHandler implements IQueryHandler<PokemonVO, ReadPokemonQuery> {

    @Autowired
    private H2PokemonRepository pokemonRepository;
    private PokemonReadService pokemonService;

    public ReadPokemonQueryHandler() {

    }

    @PostConstruct
    public void init() {
        pokemonService = new PokemonReadService(pokemonRepository);
    }

    @Override
    public PokemonVO handle(ReadPokemonQuery query) throws Exception {

        PokemonName pokemonName = new PokemonName(query.getPokemonName());

        Pokemon pokemon = pokemonService.read(pokemonName);
        if (pokemon == null) {
            return null;
        }
        return pokemon.getValueObject();
    }

}
