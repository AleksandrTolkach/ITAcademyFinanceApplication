package by.tolkach.bot.service.handler.api;

import by.tolkach.bot.dto.Chat;
import by.tolkach.bot.dto.ChatState;
import by.tolkach.bot.service.handler.userText.*;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class HandlerFactory {

    private final ApplicationContext applicationContext;

    public HandlerFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public IHandler getHandler(Chat chat) {
        ChatState state = chat.getState();
        switch (state) {
            case CREAT_OPERATION:
                return applicationContext.getBean(CreateHandler.class);
            case SET_CATEGORY:
                return applicationContext.getBean(CategoryHandler.class);
            case SET_CURRENCY:
                return applicationContext.getBean(CurrencyHandler.class);
            case SET_DATE:
                return applicationContext.getBean(DateHandler.class);
            case SET_VALUE:
                return applicationContext.getBean(ValueHandler.class);
            case SET_DESCRIPTION:
                return applicationContext.getBean(DescriptionHandler.class);
            default: throw new RuntimeException();
        }
    }
}
