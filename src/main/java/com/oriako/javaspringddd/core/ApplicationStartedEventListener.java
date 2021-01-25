package com.oriako.javaspringddd.core;

import com.oriako.javaspringddd.externalpokemon.application.ExternalPokemonReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationStartedEventListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationStartedEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        ExternalPokemonReader reader = new ExternalPokemonReader();
        try {
            int count = reader.loadPokemonData("red");
            LOG.info("OK");
        } catch (Throwable e) {
            LOG.info("KO");
        }

    }

}
