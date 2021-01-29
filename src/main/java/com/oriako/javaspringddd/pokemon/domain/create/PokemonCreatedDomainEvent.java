package com.oriako.javaspringddd.pokemon.domain.create;

import com.oriako.javaspringddd.shared.domain.domaineventbus.DomainEvent;

public class PokemonCreatedDomainEvent extends DomainEvent {

    private String pokmeonName;

    public PokemonCreatedDomainEvent(String pokmeonName) {
        this.pokmeonName = pokmeonName;
    }

}
