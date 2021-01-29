package com.oriako.javaspringddd.shared.domain.commandbus;

public interface ICommandHandler<T extends Command> {

    public void handle(T command) throws Exception;

}
