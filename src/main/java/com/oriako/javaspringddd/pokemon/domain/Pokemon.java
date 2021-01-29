package com.oriako.javaspringddd.pokemon.domain;

import com.oriako.javaspringddd.pokemon.domain.create.PokemonCreatedDomainEvent;
import com.oriako.javaspringddd.shared.domain.AggregateRoot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pokemon")
public class Pokemon extends AggregateRoot {

    @Id
    @Column (name = "name")
    private String pokemonName;
    @Column (name = "weight")
    private Integer weight;
    @Column (name = "height")
    private Integer height;
    @Column (name = "base_exp")
    private Integer baseExp;

    public Pokemon() {
        super();
    }

    public Pokemon(PokemonName pokemonName, Integer weight, Integer height, Integer baseExp) {
        this.pokemonName = pokemonName.getName();
        this.weight = weight;
        this.height = height;
        this.baseExp = baseExp;
    }

    public static Pokemon create(PokemonName pokemonName, Integer weight, Integer height, Integer baseExp) {
        Pokemon pokemon = new Pokemon(pokemonName, weight, height, baseExp);
        pokemon.record(new PokemonCreatedDomainEvent(pokemonName.getName()));

        return pokemon;
    }

    public PokemonVO getValueObject() {
        PokemonVO result = new PokemonVO();
        result.setPokemonName(pokemonName);
        result.setHeight(height);
        result.setWeight(weight);
        result.setBaseExp(baseExp);

        return result;
    }

}
