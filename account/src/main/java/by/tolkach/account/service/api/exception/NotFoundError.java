package by.tolkach.account.service.api.exception;

public class NotFoundError extends IllegalArgumentException {

    private String logRef = "error";
    private String message;

    public NotFoundError() {
    }

    public NotFoundError(String logRef, String message) {
        this.logRef = logRef;
        this.message = message;
    }

    public String getLogRef() {
        return logRef;
    }

    public String getMessage() {
        return message;
    }
}
