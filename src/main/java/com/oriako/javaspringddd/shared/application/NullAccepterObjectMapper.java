package com.oriako.javaspringddd.shared.application;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.oriako.javaspringddd.shared.domain.IObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class NullAccepterObjectMapper extends ObjectMapper implements IObjectMapper {

    private static volatile NullAccepterObjectMapper instance;

    public NullAccepterObjectMapper() {
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));
        instance = this;
    }

    public static NullAccepterObjectMapper getInstance() {
        return instance;
    }

    public <T> T parseStringToObject(String content, Class<T> valueType) {
        T result = null;

        try {
            result = super.readValue(content, valueType);
        } catch (Throwable e) {

        }

        return result;
    }

}

