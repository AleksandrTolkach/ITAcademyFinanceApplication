package by.tolkach.bot.service.handler.userText;

import by.tolkach.bot.dto.Chat;
import by.tolkach.bot.dto.ChatState;
import by.tolkach.bot.dto.Operation;
import by.tolkach.bot.dto.OperationCategory;
import by.tolkach.bot.service.api.IChatService;
import by.tolkach.bot.service.api.IOperationService;
import by.tolkach.bot.service.handler.api.IHandler;
import by.tolkach.bot.service.rest.api.IClassifierRestClientService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CategoryHandler implements IHandler {

    private final IChatService chatService;
    private final IOperationService operationService;
    private final IClassifierRestClientService classifierRestClientService;

    public CategoryHandler(IChatService chatService,
                           IOperationService operationService,
                           IClassifierRestClientService classifierRestClientService) {
        this.chatService = chatService;
        this.operationService = operationService;
        this.classifierRestClientService = classifierRestClientService;
    }

    @Override
    public SendMessage handle(Update update) {
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        Chat chat = this.chatService.readById(chatId);
        Operation operation = this.operationService.read(chat.getOperation());
        String[] param = update.getCallbackQuery().getData().split(":");
        OperationCategory operationCategory = this.classifierRestClientService.readCategory(param[1]);
        operation.setCategory(operationCategory.getUuid());
        this.operationService.save(operation);
        chat.setState(ChatState.SET_VALUE);
        this.chatService.save(chat);
        return SendMessage.builder()
                .text("Введите значение")
                .chatId(Long.toString(chatId))
                .build();
    }
}
