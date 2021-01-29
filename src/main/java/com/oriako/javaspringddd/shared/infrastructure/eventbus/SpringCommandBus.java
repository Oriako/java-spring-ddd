package com.oriako.javaspringddd.shared.infrastructure.eventbus;

import com.oriako.javaspringddd.shared.domain.commandbus.Command;
import com.oriako.javaspringddd.shared.domain.commandbus.ICommandBus;
import com.oriako.javaspringddd.shared.domain.commandbus.ICommandHandler;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Primary
public class SpringCommandBus implements ICommandBus {

    private Map<Class, ICommandHandler> handlers;

    public SpringCommandBus(List<ICommandHandler> commandHandlerImplementations) {
        this.handlers = new HashMap<>();
        commandHandlerImplementations.forEach(commandHandler -> {
            Class<?> commandClass = getCommandClass(commandHandler);
            handlers.put(commandClass, commandHandler);
        });
    }

    @Override
    public void dispatch(Command command) throws Exception {
        if (!handlers.containsKey(command.getClass())) {
            throw new Exception(String.format("No handler for %s", command.getClass().getName()));
        }
        handlers.get(command.getClass()).handle(command);
    }

    private Class<?> getCommandClass(ICommandHandler handler) {
        Type commandInterface = ((ParameterizedType) handler.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
        return getClass(commandInterface.getTypeName());
    }

    private Class<?> getClass(String name) {
        try {
            return Class.forName(name);
        } catch (Exception e) {
            return null;
        }
    }

}