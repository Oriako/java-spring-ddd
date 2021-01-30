package com.oriako.javaspringddd.queueentity.infrastructure;

import com.oriako.javaspringddd.queueentity.domain.QueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaQueueEntityRepository extends JpaRepository<QueueEntity, String> {

}
