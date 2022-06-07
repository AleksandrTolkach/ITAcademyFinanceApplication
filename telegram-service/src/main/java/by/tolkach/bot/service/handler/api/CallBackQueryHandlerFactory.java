package by.tolkach.bot.service.handler.api;

import by.tolkach.bot.service.handler.userText.CategoryHandler;
import by.tolkach.bot.service.handler.userText.CurrencyHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class CallBackQueryHandlerFactory {

    private final ApplicationContext applicationContext;

    public CallBackQueryHandlerFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public IHandler getCallBackHandler(Update update) {
        String[] param = update.getCallbackQuery().getData().split(":");
        switch (param[0]) {
            case "category":
                return applicationContext.getBean(CategoryHandler.class);
            case "currency":
                return applicationContext.getBean(CurrencyHandler.class);
            default: throw new RuntimeException();
        }
    }
}
