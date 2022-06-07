package by.tolkach.bot.service.handler.api;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface IHandler {
    SendMessage handle(Update update);
}
