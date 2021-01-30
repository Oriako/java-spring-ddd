package com.oriako.javaspringddd.queueentity.infrastructure;

import com.oriako.javaspringddd.queueentity.application.ProcessQueueEntityTask;
import com.oriako.javaspringddd.queueentity.application.QueueEntityService;
import com.oriako.javaspringddd.queueentity.domain.QueueEntity;
import com.oriako.javaspringddd.shared.infrastructure.eventbus.SyncSpringCommandBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class AsyncQueueEntityDaemonThread extends Thread implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(AsyncQueueEntityDaemonThread.class);

    @Autowired
    private QueueEntityService queueEntityService;

    @Autowired
    private SyncSpringCommandBus commandBus;

    private final ThreadPoolExecutor threadPoolExecutor;

    public AsyncQueueEntityDaemonThread() {
        super();
        setDaemon(true);

        threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (threadPoolExecutor.getActiveCount() >= 3) {
                    TimeUnit.MILLISECONDS.sleep(500);
                    continue;
                }
                final QueueEntity queueEntity = queueEntityService.getNextFromQueue();
                if (queueEntity == null) {
                    TimeUnit.MILLISECONDS.sleep(1000);
                    continue;
                }

                LOG.info("STARTED PROCESSING COMMAND: " + queueEntity.getCommandId());
                queueEntityService.updateStatus(queueEntity.getCommandId(), 1);

                threadPoolExecutor.execute(new ProcessQueueEntityTask(queueEntityService, queueEntity, commandBus));
            } catch (Throwable e) {
                LOG.error(this.getClass().getName() + ": " + e.getMessage());
            }
        }
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        start();
    }
}
