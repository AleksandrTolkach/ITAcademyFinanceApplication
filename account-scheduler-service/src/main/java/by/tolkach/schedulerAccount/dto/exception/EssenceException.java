package by.tolkach.schedulerAccount.dto.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"stackTrace", "suppressed", "cause", "localizedMessage"})
public abstract class EssenceException extends IllegalArgumentException {

    private String logRef = "error";

    public EssenceException(String message) {
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
