package by.tolkach.bot.service;

import by.tolkach.bot.dao.IChatStorage;
import by.tolkach.bot.dao.entity.ChatEntity;
import by.tolkach.bot.dao.entity.converter.IEntityConverter;
import by.tolkach.bot.dto.Chat;
import by.tolkach.bot.service.api.IChatService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChatService implements IChatService {

    private final IEntityConverter<Chat, ChatEntity> chatEntityConverter;
    private final IChatStorage chatStorage;

    public ChatService(IEntityConverter<Chat, ChatEntity> chatEntityConverter, IChatStorage chatStorage) {
        this.chatEntityConverter = chatEntityConverter;
        this.chatStorage = chatStorage;
    }

    @Override
    public Chat save(Chat chat) {
        ChatEntity chatEntity = this.chatEntityConverter.toEntity(chat);
        chatEntity = this.chatStorage.save(chatEntity);
        return this.chatEntityConverter.toDto(chatEntity);
    }

    @Override
    public Chat read(UUID chat) {
        ChatEntity chatEntity = this.chatStorage.findById(chat).orElse(null);
        return this.chatEntityConverter.toDto(chatEntity);
    }

    @Override
    public Chat readById(Long chatId) {
        ChatEntity chatEntity = this.chatStorage.findByChatId(chatId);
        return this.chatEntityConverter.toDto(chatEntity);
    }

    @Override
    public void delete(UUID chatId) {
        this.chatStorage.deleteById(chatId);
    }
}
