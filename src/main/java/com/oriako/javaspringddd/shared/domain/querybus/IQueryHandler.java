package com.oriako.javaspringddd.shared.domain.querybus;

public interface IQueryHandler<T, U extends Query<T>> {

    public T handle(U query) throws Exception;

}
