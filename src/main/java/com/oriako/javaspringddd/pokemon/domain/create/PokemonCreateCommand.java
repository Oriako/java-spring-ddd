package com.oriako.javaspringddd.pokemon.domain.create;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.oriako.javaspringddd.shared.domain.commandbus.Command;
import lombok.Data;

import java.util.UUID;

public final class PokemonCreateCommand extends Command {

    private String pokemonName;
    private Integer height;
    private Integer weight;
    private Integer baseExp;

    @JsonCreator
    public PokemonCreateCommand(UUID messageId, String pokemonName, Integer height, Integer weight, Integer baseExp) {
        super(messageId);
        this.pokemonName = pokemonName;
        this.height = height;
        this.weight = weight;
        this.baseExp = baseExp;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getBaseExp() {
        return baseExp;
    }

}
