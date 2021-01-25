package com.oriako.javaspringddd.pokemon.infrastructure;

import com.oriako.javaspringddd.pokemon.application.PokemonService;
import com.oriako.javaspringddd.pokemon.domain.PokemonName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PokemonCreateAllController {

    @Autowired
    private H2PokemonRepository pokemonRepository;

    @RequestMapping("/pokemon/create")
    public String createAll(@RequestParam Optional<String> pokemonName) {
        String result = "OK";
        try {
            PokemonName voPokemonId = new PokemonName(pokemonName.orElseThrow());
            PokemonService service = new PokemonService(pokemonRepository);
            service.create(voPokemonId);
        } catch (Throwable e) {
            result = "KO";
        }
        return result;
    }

}
