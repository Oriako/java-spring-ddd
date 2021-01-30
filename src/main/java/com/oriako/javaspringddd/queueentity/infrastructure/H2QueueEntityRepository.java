package com.oriako.javaspringddd.queueentity.infrastructure;

import com.oriako.javaspringddd.queueentity.domain.QueueEntity;
import com.oriako.javaspringddd.queueentity.domain.QueueEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public final class H2QueueEntityRepository implements QueueEntityRepository {

    @Autowired
    private JpaQueueEntityRepository jpaRepository;

    @Override
    public void addToQueue(QueueEntity queueEntity) {
        jpaRepository.save(queueEntity);
    }

    @Override
    public void updateStatus(UUID id, Integer newStatus) {
        Optional<QueueEntity> queueEntityOptional = jpaRepository.findById(id.toString());
        if (queueEntityOptional.isEmpty()) {
            return;
        }
        QueueEntity queueEntity = queueEntityOptional.get();
        queueEntity.setStatus(newStatus);
        jpaRepository.save(queueEntity);
    }

    @Override
    public QueueEntity getNextFromQueue() {

        Pageable pageable = PageRequest.of(0, 1, Sort.Direction.ASC, "status", "executionTime");
        Page<QueueEntity> resultingPage = jpaRepository.findAll(pageable);
        Optional<QueueEntity> firstToExec = resultingPage.stream().findFirst();
        if (firstToExec.isPresent()) {
            return firstToExec.get();
        }

        return null;
    }
}
