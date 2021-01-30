package com.oriako.javaspringddd.queueentity.domain;

import com.oriako.javaspringddd.shared.domain.AggregateRoot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "queue_entity")
public class QueueEntity extends AggregateRoot {

    @Id
    @Column(name = "command_id")
    private String commandId;
    @Column (name = "execution_time_utc")
    private Long executionTime;
    @Column (name = "command_name")
    private String commandName;
    @Column (name = "command_serialized")
    private String commandSerialized;
    @Column (name = "status")
    private Integer status; //Cutre pero 0 pending, 1 in_progress y 2 completed

    public QueueEntity() {

    }

    public QueueEntity(UUID commandId, Long executionTime, String commandName, String commandSerialized, Integer status) {
        super();
        this.commandId = commandId.toString();
        this.executionTime = executionTime;
        this.commandName = commandName;
        this.commandSerialized = commandSerialized;
        this.status = status;
    }

    public void setStatus(Integer newStatus) {
        this.status = newStatus;
    }

    public String getCommandId() {
        return commandId;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getCommandSerialized() {
        return commandSerialized;
    }

    public Integer getStatus() {
        return status;
    }

}
