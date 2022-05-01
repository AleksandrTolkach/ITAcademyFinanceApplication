package by.tolkach.account.service.api.exception;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({"stackTrace", "suppressed", "cause", "message", "localizedMessage"})
public class ValidationException extends IllegalArgumentException {

    private String logRef = "structured_error";
    private List<ValidationError> errors = new ArrayList<>();
    private int errorsCount = 0;

    public ValidationException() {
    }

    public ValidationException(String logRef, List<ValidationError> error) {
        this.logRef = logRef;
        this.errors = error;
    }

    public String getLogRef() {
        return logRef;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    public void add(ValidationError error) {
        this.errors.add(error);
        this.increaseErrorsCount();

    }

    private void increaseErrorsCount() {
        this.errorsCount++;
    }

    public int getErrorCount() {
        return this.errorsCount;
    }
}
