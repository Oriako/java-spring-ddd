package com.oriako.javaspringddd.pokemon.infrastructure.read;

import com.oriako.javaspringddd.pokemon.application.create.PokemonCreateService;
import com.oriako.javaspringddd.pokemon.application.read.PokemonReadService;
import com.oriako.javaspringddd.pokemon.domain.Pokemon;
import com.oriako.javaspringddd.pokemon.domain.PokemonName;
import com.oriako.javaspringddd.pokemon.infrastructure.repository.H2PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PokemonReadController {

    @Autowired
    private H2PokemonRepository pokemonRepository;

    @RequestMapping("/pokemon/read")
    public Pokemon createAll(@RequestParam Optional<String> pokemonName) {
        Pokemon result = null;
        try {
            PokemonName voPokemonId = new PokemonName(pokemonName.orElseThrow());
            PokemonReadService service = new PokemonReadService(pokemonRepository);
            result = service.read(voPokemonId);
        } catch (Throwable e) {

        }
        return result;
    }

}
