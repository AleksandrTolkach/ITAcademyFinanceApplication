package by.tolkach.bot.dao.entity;

import by.tolkach.bot.dao.entity.converter.IEntityConverter;
import by.tolkach.bot.dto.Chat;
import org.springframework.stereotype.Component;

@Component
public class ChatEntityConverter implements IEntityConverter<Chat, ChatEntity> {

    @Override
    public ChatEntity toEntity(Chat chat) {
        return ChatEntity.Builder.createBuilder()
                .setUuid(chat.getUuid())
                .setChatId(chat.getChatId())
                .setState(chat.getState())
                .setOperation(chat.getOperation())
                .build();
    }

    @Override
    public Chat toDto(ChatEntity entity) {
        return Chat.Builder.createBuilder()
                .setUuid(entity.getUuid())
                .setChatId(entity.getChatId())
                .setState(entity.getState())
                .setOperation(entity.getOperation())
                .build();
    }
}
