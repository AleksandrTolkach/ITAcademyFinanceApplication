package by.tolkach.bot.service.handler;

import by.tolkach.bot.dto.Chat;
import by.tolkach.bot.service.api.IChatService;
import by.tolkach.bot.service.handler.api.IHandler;
import by.tolkach.bot.util.TelegramUtil;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CreateCommandHandler implements IHandler {

    private final IChatService chatService;

    public CreateCommandHandler(IChatService chatService) {
        this.chatService = chatService;
    }

    @Override
    public SendMessage handle(Update update) {
        Chat chat = TelegramUtil.start(update);
        this.chatService.save(chat);
        return SendMessage.builder()
                .text("Введите номер счета")
                .chatId(update.getMessage()
                .getChatId()
                .toString())
                .build();
    }
}
