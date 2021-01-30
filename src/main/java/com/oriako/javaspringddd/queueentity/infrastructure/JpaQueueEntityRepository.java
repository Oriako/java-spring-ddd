package com.oriako.javaspringddd.queueentity.infrastructure;

import com.oriako.javaspringddd.queueentity.domain.QueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaQueueEntityRepository extends JpaRepository<QueueEntity, UUID> {

}
