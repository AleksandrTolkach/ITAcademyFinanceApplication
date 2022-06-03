package by.tolkach.bot.service.handler.api;

import by.tolkach.bot.service.handler.botCommands.CreateCommandHandler;
import by.tolkach.bot.service.handler.botCommands.NotFoundCommandHandler;
import by.tolkach.bot.service.handler.botCommands.StartCommandHandler;
import by.tolkach.bot.util.BotCommand;
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
            case BotCommand.CREATE_OPERATION:
                return applicationContext.getBean(CreateCommandHandler.class);
            case BotCommand.START:
                return applicationContext.getBean(StartCommandHandler.class);
            default:
                return applicationContext.getBean(NotFoundCommandHandler.class);
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
