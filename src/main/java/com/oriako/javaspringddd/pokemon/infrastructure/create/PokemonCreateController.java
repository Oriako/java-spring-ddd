package com.oriako.javaspringddd.pokemon.infrastructure.create;

import com.oriako.javaspringddd.pokemon.application.create.PokemonCreateService;
import com.oriako.javaspringddd.pokemon.domain.PokemonName;
import com.oriako.javaspringddd.pokemon.infrastructure.repository.H2PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PokemonCreateController {

    @Autowired
    private H2PokemonRepository pokemonRepository;

    @RequestMapping("/pokemon/create")
    public String createAll(@RequestParam Optional<String> pokemonName, @RequestParam Optional<Integer> weight, @RequestParam Optional<Integer> height, @RequestParam Optional<Integer> baseExp) {
        String result = "OK";
        try {
            PokemonName voPokemonId = new PokemonName(pokemonName.orElseThrow());
            PokemonCreateService service = new PokemonCreateService(pokemonRepository);
            service.create(voPokemonId, weight.orElseThrow(), height.orElseThrow(), baseExp.orElseThrow());
        } catch (Throwable e) {
            result = "KO";
        }
        return result;
    }

}
