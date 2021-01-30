package com.oriako.javaspringddd.queueentity.domain;

import java.util.UUID;

public interface QueueEntityRepository {

    public void addToQueue(QueueEntity queueEntity);
    public void updateStatus(UUID id, Integer newStatus);
    public QueueEntity getNextFromQueue();

}
