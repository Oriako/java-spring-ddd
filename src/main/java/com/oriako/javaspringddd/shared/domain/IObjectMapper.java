package com.oriako.javaspringddd.shared.domain;

public interface IObjectMapper {

    public <T> T parseStringToObject(String content, Class<T> valueType);

}
