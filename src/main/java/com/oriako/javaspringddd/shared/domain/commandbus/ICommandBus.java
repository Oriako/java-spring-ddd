package com.oriako.javaspringddd.shared.domain.commandbus;

public interface ICommandBus {

    public void register(Class<? extends Command> commandClass, ICommandHandler commandHandler);
    public void dispatch(Command command) throws Exception;

}
