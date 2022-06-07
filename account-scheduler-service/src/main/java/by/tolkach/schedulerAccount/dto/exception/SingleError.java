package by.tolkach.schedulerAccount.dto.exception;

public class SingleError {

    private String field;
    private String message;

    public SingleError() {
    }

    public SingleError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
