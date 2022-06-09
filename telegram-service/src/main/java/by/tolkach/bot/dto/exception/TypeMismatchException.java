package by.tolkach.bot.dto.exception;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class TypeMismatchException extends EssenceException {

    private SendMessage sendMessage;

    public TypeMismatchException(String message) {
        super(message);
    }

    public TypeMismatchException(String message, SendMessage sendMessage) {
        super(message);
        this.sendMessage = sendMessage;
    }

    public SendMessage getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(SendMessage sendMessage) {
        this.sendMessage = sendMessage;
    }
}
