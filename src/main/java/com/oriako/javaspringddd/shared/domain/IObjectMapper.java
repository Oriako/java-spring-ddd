package com.oriako.javaspringddd.shared.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface IObjectMapper {

    public <T> T parseStringToObject(String content, Class<T> valueType);

}
