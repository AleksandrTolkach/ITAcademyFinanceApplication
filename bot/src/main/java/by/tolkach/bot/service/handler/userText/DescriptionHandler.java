package by.tolkach.bot.service.handler.userText;

import by.tolkach.bot.dto.Chat;
import by.tolkach.bot.dto.ChatState;
import by.tolkach.bot.dto.Operation;
import by.tolkach.bot.service.api.IChatService;
import by.tolkach.bot.service.api.IOperationService;
import by.tolkach.bot.service.handler.api.IHandler;
import by.tolkach.bot.service.rest.api.IOperationRestClientService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DescriptionHandler implements IHandler {

    private final IChatService chatService;
    private final IOperationService operationService;
    private final IOperationRestClientService operationRestClientService;

    public DescriptionHandler(IChatService chatService, IOperationService operationService, IOperationRestClientService operationRestClientService) {
        this.chatService = chatService;
        this.operationService = operationService;
        this.operationRestClientService = operationRestClientService;
    }

    @Override
    public SendMessage handle(Update update) {
        long chatId = update.getMessage().getChatId();
        Chat chat = this.chatService.readById(chatId);
        Operation operation = this.operationService.read(chat.getOperation());
        operation.setDescription(update.getMessage().getText());
        this.operationService.save(operation);
        chat.setState(ChatState.NONE);
        this.chatService.delete(chat.getUuid());
        operation = this.operationService.read(chat.getOperation());
        this.operationRestClientService.create(operation);
        return SendMessage.builder().text("Операция добавлена").chatId(Long.toString(chatId)).build();
    }
}
