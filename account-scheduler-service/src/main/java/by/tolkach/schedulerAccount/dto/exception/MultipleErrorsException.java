package by.tolkach.schedulerAccount.dto.exception;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({"stackTrace", "suppressed", "cause", "message", "localizedMessage"})
public class MultipleErrorsException extends IllegalArgumentException {

    private String logRef = "structured_error";
    private List<SingleError> errors = new ArrayList<>();
    private int errorsCount = 0;

    public MultipleErrorsException() {
    }

    public MultipleErrorsException(String logRef, List<SingleError> error) {
        this.logRef = logRef;
        this.errors = error;
    }

    public String getLogRef() {
        return logRef;
    }

    public List<SingleError> getErrors() {
        return errors;
    }

    public void add(SingleError error) {
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
