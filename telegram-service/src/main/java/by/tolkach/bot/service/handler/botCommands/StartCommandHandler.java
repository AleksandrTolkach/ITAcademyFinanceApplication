package by.tolkach.bot.service.handler.botCommands;

import by.tolkach.bot.service.handler.api.IHandler;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StartCommandHandler implements IHandler {
    @Override
    public SendMessage handle(Update update) {
        return SendMessage.builder()
                .chatId(update.getMessage().getChatId().toString())
                .text("Приветствую!\nТы можешь воспользоваться командой /create_operation для создания операции")
                .build();
    }
}
