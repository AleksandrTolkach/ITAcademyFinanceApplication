package by.tolkach.bot.util;

import by.tolkach.bot.dto.Chat;
import by.tolkach.bot.dto.ChatState;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.UUID;

public class TelegramUtil {
    public static Chat start(Update update) {
        return Chat.Builder.createBuilder()
                .setUuid(UUID.randomUUID())
                .setChatId(update.getMessage().getChatId())
                .setState(ChatState.CREAT_OPERATION)
                .build();
    }
}
