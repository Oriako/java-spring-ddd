package com.oriako.javaspringddd.shared.infrastructure.eventbus;

import com.oriako.javaspringddd.queueentity.application.QueueEntityService;
import com.oriako.javaspringddd.queueentity.infrastructure.H2QueueEntityRepository;
import com.oriako.javaspringddd.shared.domain.commandbus.Command;
import com.oriako.javaspringddd.shared.domain.commandbus.ICommandBus;
import com.oriako.javaspringddd.shared.domain.commandbus.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AsyncSpringCommandBus implements ICommandBus {

    @Autowired
    private H2QueueEntityRepository queueEntityRepository;
    private QueueEntityService queueEntityService;

    public AsyncSpringCommandBus() {

    }

    @PostConstruct
    public void init() {
        queueEntityService = new QueueEntityService(queueEntityRepository);
    }

    @Override
    public void register(Class<? extends Command> commandClass, ICommandHandler commandHandler) {

    }

    @Override
    public void dispatch(Command command) throws Exception {
        queueEntityService.addToQueue(command.getMessageId(), 0l, command);
    }

}
