package com.oriako.javaspringddd.queueentity.infrastructure;

import com.oriako.javaspringddd.queueentity.application.QueueEntityService;
import com.oriako.javaspringddd.queueentity.domain.QueueEntity;
import com.oriako.javaspringddd.shared.domain.commandbus.Command;
import com.oriako.javaspringddd.shared.infrastructure.NullAccepterObjectMapper;
import com.oriako.javaspringddd.shared.infrastructure.eventbus.SyncSpringCommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExecuteNextQueryEventController {

    @Autowired
    private QueueEntityService queueEntityService;

    @Autowired
    private SyncSpringCommandBus commandBus;

    @RequestMapping("/queryEvent/executeNext")
    public String executeNext() {
        QueueEntity queueEntity = queueEntityService.getNextFromQueue();
        Command commandToExecute = NullAccepterObjectMapper.getInstance().parseStringToObject(queueEntity.getCommandSerialized(), getClass(queueEntity.getCommandName()));
        if (commandToExecute != null) {
            try {
                queueEntityService.updateStatus(commandToExecute.getMessageId(), queueEntity.getStatus() + 1);
                commandBus.dispatch(commandToExecute);
                queueEntityService.updateStatus(commandToExecute.getMessageId(), queueEntity.getStatus() + 1);
            } catch (Throwable e) {

            }

            return commandToExecute.getMessageId().toString();
        }

        return "KO";
    }

    private <T extends Command> Class<T> getClass(String name) {
        try {
            return (Class<T>) Class.forName(name);
        } catch (Exception e) {
            return null;
        }
    }

}
