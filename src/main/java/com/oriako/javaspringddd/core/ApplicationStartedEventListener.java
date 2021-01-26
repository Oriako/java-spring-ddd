package com.oriako.javaspringddd.core;

import com.oriako.javaspringddd.externalpokemon.application.ExternalPokemonReader;
import com.oriako.javaspringddd.shared.infrastructure.LocalHostURI;
import com.oriako.javaspringddd.shared.infrastructure.NullAccepterObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.client.RestTemplate;

public class ApplicationStartedEventListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationStartedEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        final RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(LocalHostURI.createURI("externalPokemon/loadData?versionName=red"), String.class);
        LOG.info("LOAD DATA: " + result);
    }

}
