package by.tolkach.bot.service.api;

import by.tolkach.bot.dto.Chat;

import java.util.UUID;

public interface IChatService {
    Chat save(Chat chat);
    Chat read(UUID chat);
    Chat readById(Long chatId);
    void delete(UUID chatId);
}
