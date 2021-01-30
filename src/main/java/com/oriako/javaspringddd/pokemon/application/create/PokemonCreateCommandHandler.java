package com.oriako.javaspringddd.pokemon.application.create;

import com.oriako.javaspringddd.pokemon.domain.PokemonName;
import com.oriako.javaspringddd.pokemon.domain.create.PokemonCreateCommand;
import com.oriako.javaspringddd.pokemon.infrastructure.repository.H2PokemonRepository;
import com.oriako.javaspringddd.shared.domain.commandbus.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public final class PokemonCreateCommandHandler implements ICommandHandler<PokemonCreateCommand> {

    @Autowired
    private H2PokemonRepository pokemonRepository;
    private PokemonCreateService pokemonService;

    public PokemonCreateCommandHandler() {

    }

    @PostConstruct
    public void init() {
        pokemonService = new PokemonCreateService(pokemonRepository);
    }

    @Override
    public void handle(PokemonCreateCommand command) throws Exception {

        PokemonName pokemonName = new PokemonName(command.getPokemonName());

        pokemonService.create(pokemonName, command.getWeight(), command.getHeight(), command.getBaseExp());
    }
}
