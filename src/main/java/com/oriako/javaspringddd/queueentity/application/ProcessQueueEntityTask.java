package com.oriako.javaspringddd.queueentity.application;

import com.oriako.javaspringddd.queueentity.domain.QueueEntity;
import com.oriako.javaspringddd.shared.application.NullAccepterObjectMapper;
import com.oriako.javaspringddd.shared.domain.commandbus.Command;
import com.oriako.javaspringddd.shared.domain.commandbus.ICommandBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ProcessQueueEntityTask implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(ProcessQueueEntityTask.class);

    private final QueueEntityService queueEntityService;
    private final QueueEntity queueEntity;
    private final  ICommandBus commandBus;

    public ProcessQueueEntityTask(QueueEntityService queueEntityService, QueueEntity queueEntity, ICommandBus commandBus) {
        this.queueEntityService = queueEntityService;
        this.queueEntity = queueEntity;
        this.commandBus = commandBus;
    }

    @Override
    public void run() {

        try {
            Class<Command> commandClass = (Class<Command>) Class.forName(queueEntity.getCommandName());
            Command commandToExecute = NullAccepterObjectMapper.getInstance().parseStringToObject(queueEntity.getCommandSerialized(), commandClass);
            if (commandToExecute != null) {
                commandBus.dispatch(commandToExecute);
                queueEntityService.updateStatus(queueEntity.getCommandId(), 2);
                LOG.info("FINISHED PROCESSING COMMAND: " + commandToExecute.getMessageId());
            }
        } catch (Throwable e) {
            queueEntityService.updateStatus(queueEntity.getCommandId(), 0);
            LOG.info("ERROR PROCESSING COMMAND: " + queueEntity.getCommandId() + " || " + queueEntity.getCommandName() + " || " + queueEntity.getCommandSerialized());
        }
    }

}
