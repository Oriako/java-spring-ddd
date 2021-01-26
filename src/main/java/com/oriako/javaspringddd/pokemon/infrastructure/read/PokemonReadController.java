package com.oriako.javaspringddd.pokemon.infrastructure.read;

import com.oriako.javaspringddd.pokemon.application.read.PokemonReadService;
import com.oriako.javaspringddd.pokemon.domain.Pokemon;
import com.oriako.javaspringddd.pokemon.domain.PokemonName;
import com.oriako.javaspringddd.pokemon.domain.PokemonVO;
import com.oriako.javaspringddd.pokemon.domain.read.PokemonReadResponse;
import com.oriako.javaspringddd.pokemon.infrastructure.repository.H2PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class PokemonReadController {

    @Autowired
    private H2PokemonRepository pokemonRepository;

    @RequestMapping("/pokemon/read")
    public PokemonVO read(@RequestParam Optional<String> pokemonName) {
        PokemonVO result = null;
        try {
            PokemonName voPokemonId = new PokemonName(pokemonName.orElseThrow());
            PokemonReadService service = new PokemonReadService(pokemonRepository);
            result = service.read(voPokemonId).getValueObject();
        } catch (Throwable e) {
            result = PokemonVO.empty();
        }
        return result;
    }

    @RequestMapping("/pokemon/readAllOrdered")
    public PokemonReadResponse read(@RequestParam Optional<String> orderAttr, @RequestParam Optional<Integer> size, @RequestParam Optional<Integer> page, @RequestParam Optional<Boolean> ascending) {
        PokemonReadResponse pokemonReadResponse = new PokemonReadResponse();
        try {
            PokemonReadService service = new PokemonReadService(pokemonRepository);
            Collection<Pokemon> entityList = service.readAllOrderedBy(orderAttr, ascending, size, page);
            Collection<PokemonVO> pokemonList = entityList.stream().map(Pokemon::getValueObject).collect(Collectors.toList());
            pokemonReadResponse.setPokemonList(pokemonList);
        } catch (Throwable e) {

        }
        return pokemonReadResponse;
    }

}
