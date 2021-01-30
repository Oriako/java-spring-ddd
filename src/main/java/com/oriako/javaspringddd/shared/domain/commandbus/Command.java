package com.oriako.javaspringddd.shared.domain.commandbus;

import java.util.UUID;

public abstract class Command {

    private UUID messageId;

    public Command(UUID messageId) {
        this.messageId = messageId;
    }

    public UUID getMessageId() {
        return messageId;
    }
    
}
