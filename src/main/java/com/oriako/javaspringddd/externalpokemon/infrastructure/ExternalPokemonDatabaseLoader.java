package com.oriako.javaspringddd.externalpokemon.infrastructure;

import com.oriako.javaspringddd.externalpokemon.application.ExternalPokemonReader;
import com.oriako.javaspringddd.shared.application.NullAccepterObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class ExternalPokemonDatabaseLoader implements ApplicationListener<ApplicationReadyEvent>  {

    private static final Logger LOG = LoggerFactory.getLogger(ExternalPokemonDatabaseLoader.class);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            String versionNameValue = "red";
            ExternalPokemonReader reader = new ExternalPokemonReader(NullAccepterObjectMapper.getInstance());
            reader.loadPokemonData(versionNameValue);
            LOG.info("ALL POKEMON CREATED");
        } catch (Throwable e) {
            LOG.error("ERROR LOADING DATA");
        }
    }

}
