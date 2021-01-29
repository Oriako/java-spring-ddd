package com.oriako.javaspringddd.shared.domain.querybus;

public interface IQueryBus {

    <T> T dispatch(Query<T> query) throws Exception;

}
