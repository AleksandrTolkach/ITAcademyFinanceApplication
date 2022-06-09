package by.tolkach.schedulerAccount.dto.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"stackTrace", "suppressed", "cause", "localizedMessage"})
public abstract class EssenceException extends IllegalArgumentException {

    private final static String LOG_REF = "error";

    protected EssenceException(String message) {
        super(message, null);
    }

    public String getLogRef() {
        return LOG_REF;
    }
}
