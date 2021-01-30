package com.oriako.javaspringddd.shared.infrastructure.eventbus;

import com.oriako.javaspringddd.shared.domain.querybus.IQueryBus;
import com.oriako.javaspringddd.shared.domain.querybus.IQueryHandler;
import com.oriako.javaspringddd.shared.domain.querybus.Query;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Primary
public class SpringQueryBus implements IQueryBus {

    private Map<Class<? extends Query>, IQueryHandler> handlers;

    public SpringQueryBus(List<IQueryHandler> queryHandlerImplementations) {
        this.handlers = new HashMap<>();
        queryHandlerImplementations.forEach(queryHandler -> {
            Class queryClass = getQueryClass(queryHandler);
            register(queryClass, queryHandler);
        });
    }

    @Override
    public void register(Class<? extends Query> queryClass, IQueryHandler queryHandler) {
        handlers.put(queryClass, queryHandler);
    }

    @Override
    public <T> T ask(Query<T> query) throws Exception {
        if (!handlers.containsKey(query.getClass())) {
            throw new Exception(String.format("No handler for %s", query.getClass().getName()));
        }
        return (T) handlers.get(query.getClass()).handle(query);
    }

    private Class<? extends Query> getQueryClass(IQueryHandler handler) {
        Type commandInterface = ((ParameterizedType) handler.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[1];
        return getClass(commandInterface.getTypeName());
    }

    private Class<? extends Query> getClass(String name) {
        try {
            return (Class<? extends Query>) Class.forName(name);
        } catch (Exception e) {
            return null;
        }
    }

}
