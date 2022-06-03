package by.tolkach.bot.dao.entity;

import by.tolkach.bot.dto.ChatState;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(schema = "application", name = "chats")
public class ChatEntity {

    @Id
    private UUID uuid;
    @Column(unique = true)
    private Long chatId;
    @Enumerated(value = EnumType.STRING)
    private ChatState state;
    @Column(name = "operation_uuid")
    private UUID operation;

    public ChatEntity() {
    }

    public ChatEntity(UUID uuid, Long chatId, ChatState state, UUID operation) {
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

        public ChatEntity build() {
            return new ChatEntity(uuid, chatId, state, operation);
        }
    }
}
