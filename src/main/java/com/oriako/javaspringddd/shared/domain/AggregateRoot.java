package com.oriako.javaspringddd.shared.domain;

import com.oriako.javaspringddd.shared.domain.domaineventbus.DomainEvent;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AggregateRoot {

    private Collection<DomainEvent> domainEventCollection;

    public void record(DomainEvent domainEvent) {
        if (domainEventCollection == null) {
            domainEventCollection = new ArrayList<>();
        }
        domainEventCollection.add(domainEvent);
    }

    public Collection<DomainEvent> getDomainEventCollection() {
        if (domainEventCollection == null) {
            domainEventCollection = new ArrayList<>();
        }
        return domainEventCollection;
    }

}
