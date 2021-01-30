package com.oriako.javaspringddd.queueentity.application;

import com.oriako.javaspringddd.queueentity.domain.QueueEntity;
import com.oriako.javaspringddd.queueentity.domain.QueueEntityRepository;
import com.oriako.javaspringddd.shared.application.NullAccepterObjectMapper;
import com.oriako.javaspringddd.shared.domain.commandbus.Command;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class QueueEntityService {

    private QueueEntityRepository queueEntityRepository;

    public QueueEntityService(QueueEntityRepository queueEntityRepository) {
        this.queueEntityRepository = queueEntityRepository;
    }

    public void addToQueue(UUID id, Long offsetInMillis, Command command) {
        String commandName = command.getClass().getName();
        try {
            String commandSerialized = NullAccepterObjectMapper.getInstance().writeValueAsString(command);
            QueueEntity queueEntity = new QueueEntity(id, Instant.now().toEpochMilli() + offsetInMillis, commandName, commandSerialized, 0);
            queueEntityRepository.addToQueue(queueEntity);
        } catch (Throwable e) {

        }
    }

    public void updateStatus(UUID messageId, Integer newStatus) {
        queueEntityRepository.updateStatus(messageId, newStatus);
    }

    public QueueEntity getNextFromQueue() {
        return queueEntityRepository.getNextFromQueue();
    }

}
