package by.tolkach.bot.dto;

import java.util.UUID;

public class Chat {

    private UUID uuid;
    private Long chatId;
    private ChatState state;
    private UUID operation;

    public Chat(UUID uuid, Long chatId, ChatState state, UUID operation) {
        this.uuid = uuid;
        this.chatId = chatId;
        this.state = state;
        this.operation = operation;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public ChatState getState() {
        return state;
    }

    public void setState(ChatState state) {
        this.state = state;
    }

    public UUID getOperation() {
        return operation;
    }

    public void setOperation(UUID operation) {
        this.operation = operation;
    }

    public static class Builder {

        private UUID uuid;
        private Long chatId;
        private ChatState state;
        private UUID operation;

        private Builder() {
        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public Builder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setChatId(Long chatId) {
            this.chatId = chatId;
            return this;
        }

        public Builder setState(ChatState state) {
            this.state = state;
            return this;
        }

        public Builder setOperation(UUID operation) {
            this.operation = operation;
            return this;
        }

        public Chat build() {
            return new Chat(uuid, chatId, state, operation);
        }
    }
}
