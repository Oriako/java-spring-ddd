package com.oriako.javaspringddd.shared.domain.querybus;

public interface IQueryBus {

    public void register(Class<? extends Query> queryClass, IQueryHandler queryHandler);
    public <T> T ask(Query<T> query) throws Exception;

}
