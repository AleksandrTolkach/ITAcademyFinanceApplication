package by.tolkach.bot.service.handler.userText;

import by.tolkach.bot.dto.Chat;
import by.tolkach.bot.dto.ChatState;
import by.tolkach.bot.dto.Operation;
import by.tolkach.bot.service.api.IChatService;
import by.tolkach.bot.service.api.IOperationService;
import by.tolkach.bot.service.handler.api.IHandler;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.math.BigDecimal;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ValueHandler implements IHandler {

    private final IChatService chatService;
    private final IOperationService operationService;

    public ValueHandler(IChatService chatService, IOperationService operationService) {
        this.chatService = chatService;
        this.operationService = operationService;
    }

    @Override
    public SendMessage handle(Update update) {
        long chatId = update.getMessage().getChatId();
        Chat chat = this.chatService.readById(chatId);
        Operation operation = this.operationService.read(chat.getOperation());
        operation.setValue(BigDecimal.valueOf(Long.parseLong(update.getMessage().getText())));
        this.operationService.save(operation);
        chat.setState(ChatState.SET_CURRENCY);
        this.chatService.save(chat);
        return SendMessage.builder().text("Введите валюту").chatId(Long.toString(chatId)).build();
    }
}
