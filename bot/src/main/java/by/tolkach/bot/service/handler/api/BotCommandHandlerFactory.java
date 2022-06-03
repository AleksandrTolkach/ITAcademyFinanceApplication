package by.tolkach.bot.service.handler.api;

import by.tolkach.bot.service.handler.CreateCommandHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Component
public class BotCommandHandlerFactory {

    private final ApplicationContext applicationContext;

    public BotCommandHandlerFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public IHandler getBotCommandHandler(Update update) {
        String substring = this.cutCommand(update.getMessage());
        switch (substring) {
            case "/create_operation":
                return applicationContext.getBean(CreateCommandHandler.class);
            default: throw new RuntimeException();
        }
    }

    private String cutCommand(Message message) {
        Optional<MessageEntity> commandEntity =
                message.getEntities().stream().filter(e -> e.getType().equals("bot_command")).findFirst();
        String substring = "";
        if (commandEntity.isPresent()) {
            substring = message.getText()
                    .substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
        }
        return substring;
    }
}
