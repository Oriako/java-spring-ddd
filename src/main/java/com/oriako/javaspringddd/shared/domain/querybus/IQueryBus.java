package com.oriako.javaspringddd.shared.domain.querybus;

public interface IQueryBus {

    <T> T ask(Query<T> query) throws Exception;

}
