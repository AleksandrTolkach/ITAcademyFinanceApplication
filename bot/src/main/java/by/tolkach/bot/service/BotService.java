package by.tolkach.bot.service;

import by.tolkach.bot.dto.Chat;
import by.tolkach.bot.service.api.IChatService;
import by.tolkach.bot.service.handler.api.BotCommandHandlerFactory;
import by.tolkach.bot.service.handler.api.HandlerFactory;
import by.tolkach.bot.service.handler.api.IHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class BotService extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

    private final IChatService chatService;
    private final HandlerFactory handlerFactory;
    private final BotCommandHandlerFactory botCommandHandlerFactory;

    public BotService(IChatService chatService,
                      HandlerFactory handlerFactory,
                      BotCommandHandlerFactory botCommandHandlerFactory) {
        this.chatService = chatService;
        this.handlerFactory = handlerFactory;
        this.botCommandHandlerFactory = botCommandHandlerFactory;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage().isCommand()) {
            IHandler botCommandHandler = this.botCommandHandlerFactory.getBotCommandHandler(update);
            SendMessage sendMessage = botCommandHandler.handle(update);
            try {
                execute(sendMessage);
                return;
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        if (update.getMessage().hasText()) {
            Chat chat = null;
            try {
                chat = this.chatService.readById(update.getMessage().getChatId());
            } catch (NullPointerException e) {
                try {
                    execute(SendMessage.builder()
                            .chatId(update.getMessage().getChatId().toString())
                            .text("Вы ввели неверное значение")
                            .build());
                    return;
                } catch (TelegramApiException ex) {
                    ex.printStackTrace();
                }
            }
            IHandler handler = this.handlerFactory.getHandler(chat);
            SendMessage sendMessage = handler.handle(update);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
