package com.oriako.javaspringddd.shared.infrastructure;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class OriakoObjectMapper extends ObjectMapper {

    private static volatile OriakoObjectMapper instance;

    public OriakoObjectMapper() {
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        instance = this;
    }

    public static OriakoObjectMapper getInstance() {
        return instance;
    }

}

