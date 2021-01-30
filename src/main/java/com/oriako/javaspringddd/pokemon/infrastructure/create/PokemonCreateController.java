package com.oriako.javaspringddd.pokemon.infrastructure.create;

import com.oriako.javaspringddd.pokemon.domain.PokemonVO;
import com.oriako.javaspringddd.pokemon.domain.create.PokemonCreateCommand;
import com.oriako.javaspringddd.pokemon.domain.read.ReadPokemonQuery;
import com.oriako.javaspringddd.shared.infrastructure.eventbus.SpringQueryBus;
import com.oriako.javaspringddd.shared.infrastructure.eventbus.SyncSpringCommandBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
public class PokemonCreateController {

    private static final Logger LOG = LoggerFactory.getLogger(PokemonCreateController.class);

    @Autowired
    private SyncSpringCommandBus commandBus;

    @Autowired
    private SpringQueryBus queryBus;

    @RequestMapping("/pokemon/create")
    public String create(@RequestParam Optional<String> pokemonName, @RequestParam Optional<Integer> weight, @RequestParam Optional<Integer> height, @RequestParam Optional<Integer> baseExp) {
        String result = "KO";
        try {
            PokemonCreateCommand createCommand = new PokemonCreateCommand(UUID.randomUUID(), pokemonName.orElseThrow(), weight.orElseThrow(), height.orElseThrow(), baseExp.orElseThrow());
            commandBus.dispatch(createCommand);

            PokemonVO resultingPokemon = queryBus.ask(new ReadPokemonQuery(pokemonName.orElseThrow()));
            if (resultingPokemon != null) {
                result = "OK";
            }
        } catch (Throwable e) {
            LOG.info(e.getMessage());
        }
        return result;
    }

}
