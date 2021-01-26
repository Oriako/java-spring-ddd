package com.oriako.javaspringddd.pokemon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pokemon")
public class Pokemon {

    @Id
    @Column (name = "name")
    private String pokemonName;

    public Pokemon() {
        super();
    }

    public Pokemon(PokemonName pokemonName) {
        this.pokemonName = pokemonName.getName();
    }

    public static Pokemon create(PokemonName pokemonId) {
        Pokemon pokemon = new Pokemon(pokemonId);

        // Events

        return pokemon;
    }

}
