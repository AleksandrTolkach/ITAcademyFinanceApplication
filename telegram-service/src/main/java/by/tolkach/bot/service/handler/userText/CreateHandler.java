package by.tolkach.bot.service.handler.userText;

import by.tolkach.bot.dto.Chat;
import by.tolkach.bot.dto.ChatState;
import by.tolkach.bot.dto.Operation;
import by.tolkach.bot.dto.exception.TypeMismatchException;
import by.tolkach.bot.service.api.IChatService;
import by.tolkach.bot.service.api.IOperationService;
import by.tolkach.bot.service.handler.api.IHandler;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.UUID;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CreateHandler implements IHandler {

    private final IChatService chatService;
    private final IOperationService operationService;

    public CreateHandler(IChatService chatService, IOperationService operationService) {
        this.chatService = chatService;
        this.operationService = operationService;
    }

    @Override
    public SendMessage handle(Update update) throws TypeMismatchException {
        long chatId = update.getMessage().getChatId();
        Operation operation = new Operation();
        Chat chat = this.chatService.readById(chatId);
        operation.setUuid(UUID.randomUUID());
        UUID accountId = null;
        try {
            accountId = UUID.fromString(update.getMessage().getText());
        } catch (IllegalArgumentException e) {
            SendMessage sendMessage = SendMessage.builder()
                    .text("Неверный тип UUID.")
                    .chatId(Long.toString(chatId))
                    .build();
            throw new TypeMismatchException("Неверный тип UUID.", sendMessage);
        }
        operation.setAccount(accountId);
        this.operationService.save(operation);
        chat.setState(ChatState.SET_DATE);
        chat.setOperation(operation.getUuid());
        this.chatService.save(chat);
        return SendMessage.builder().text("Введите дату выполнения операции").chatId(Long.toString(chatId)).build();
    }
}
