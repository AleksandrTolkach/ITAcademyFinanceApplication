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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DateHandler implements IHandler {

    private final IChatService chatService;
    private final IOperationService operationService;
    private final IClassifierRestClientService classifierRestClientService;

    public DateHandler(IChatService chatService,
                       IOperationService operationService,
                       IClassifierRestClientService classifierRestClientService) {
        this.chatService = chatService;
        this.operationService = operationService;
        this.classifierRestClientService = classifierRestClientService;
    }

    @Override
    public SendMessage handle(Update update) {
        List<OperationCategory> operationCategories = this.classifierRestClientService.readCategory();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        for (OperationCategory category: operationCategories) {
            buttons.add(Arrays.asList(InlineKeyboardButton.builder()
                    .text(category.getTitle()).callbackData("category:" + category.getTitle())
                    .build()));
        }
        long chatId = update.getMessage().getChatId();
        Chat chat = this.chatService.readById(chatId);
        Operation operation = this.operationService.read(chat.getOperation());
        operation.setDate(LocalDateTime.parse(update.getMessage().getText()));
        this.operationService.save(operation);
        chat.setState(ChatState.SET_CATEGORY);
        this.chatService.save(chat);
        return SendMessage.builder().text("Введите категорию")
                .chatId(Long.toString(chatId))
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
                .build();
    }
}
