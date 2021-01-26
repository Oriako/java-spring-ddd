package com.oriako.javaspringddd.pokemon.infrastructure.repository;

import com.oriako.javaspringddd.pokemon.domain.Pokemon;
import com.oriako.javaspringddd.pokemon.domain.PokemonName;
import com.oriako.javaspringddd.pokemon.domain.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public final class H2PokemonRepository implements PokemonRepository {

    @Autowired
    private JpaPokemonRepository jpaRepository;

    @Override
    public Pokemon create(Pokemon pokemon) {
        return jpaRepository.save(pokemon);
    }

    @Override
    public Pokemon update(Pokemon pokemon) {
        return jpaRepository.save(pokemon);
    }

    @Override
    public Pokemon read(PokemonName pokemonName) {
        return jpaRepository.findById(pokemonName.getName()).orElse(null);
    }

    public Collection<Pokemon> readAllOrderedBy(Optional<String> orderAttribute, Optional<Boolean> ascending, Optional<Integer> size, Optional<Integer> page) {

        Collection<Pokemon> result = null;

        Sort sort = null;
        if (orderAttribute.isPresent() && ascending.isPresent()) {
            sort = ascending.get() ? Sort.by(orderAttribute.get()).ascending() : Sort.by(orderAttribute.get()).descending();
        }
        Pageable pageable = null;
        if (size.isPresent() && page.isPresent()) {
            pageable = sort != null ? PageRequest.of(page.get(), size.get(), sort) : PageRequest.of(page.get(), size.get());
        }

        if (pageable != null) {
            Page<Pokemon> resultingPage = jpaRepository.findAll(pageable);
            result = resultingPage.getContent();
        } else if (sort != null) {
            result = jpaRepository.findAll(sort);
        } else {
            result = jpaRepository.findAll();
        }

        return result;
    }

}
