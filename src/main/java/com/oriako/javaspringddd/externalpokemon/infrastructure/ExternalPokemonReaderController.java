package com.oriako.javaspringddd.externalpokemon.infrastructure;

import com.oriako.javaspringddd.externalpokemon.application.ExternalPokemonReader;
import com.oriako.javaspringddd.shared.infrastructure.NullAccepterObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ExternalPokemonReaderController {

    @RequestMapping("/externalPokemon/loadData")
    public String createAll(@RequestParam Optional<String> versionName) {
        String result = "OK";
        try {
            String versionNameValue = versionName.orElse("red");
            ExternalPokemonReader reader = new ExternalPokemonReader(NullAccepterObjectMapper.getInstance());
            reader.loadPokemonData(versionNameValue);
        } catch (Throwable e) {
            result = "KO";
        }
        return result;
    }

}
