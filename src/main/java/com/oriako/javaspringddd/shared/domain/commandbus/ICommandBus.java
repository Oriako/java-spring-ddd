package com.oriako.javaspringddd.shared.domain.commandbus;

public interface ICommandBus {

    void dispatch(Command command) throws Exception;

}
