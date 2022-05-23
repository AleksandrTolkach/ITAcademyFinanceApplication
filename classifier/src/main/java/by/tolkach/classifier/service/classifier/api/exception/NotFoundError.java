package by.tolkach.classifier.service.classifier.api.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"stackTrace", "suppressed", "cause", "localizedMessage"})
public class NotFoundError extends IllegalArgumentException {

    private String logRef = "error";

    public NotFoundError(String message) {
        super(message, null);
    }

    public String getLogRef() {
        return logRef;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
