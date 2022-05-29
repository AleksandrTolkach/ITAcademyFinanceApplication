package by.tolkach.mailScheduler.service.rest.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MailParamWrapperRestObject {

    @JsonProperty(value = "mail")
    private MailRestObject mailRestObject;
    @JsonProperty(value = "param")
    private ParamRestObject paramRestObject;

    public MailParamWrapperRestObject() {
    }

    public MailParamWrapperRestObject(MailRestObject mailRestObject, ParamRestObject paramRestObject) {
        this.mailRestObject = mailRestObject;
        this.paramRestObject = paramRestObject;
    }

    public MailRestObject getMailRestObject() {
        return mailRestObject;
    }

    public void setMailRestObject(MailRestObject mailRestObject) {
        this.mailRestObject = mailRestObject;
    }

    public ParamRestObject getParamRestObject() {
        return paramRestObject;
    }

    public void setParamRestObject(ParamRestObject paramRestObject) {
        this.paramRestObject = paramRestObject;
    }

    public static MailParamWrapperRestObject wrap(MailRestObject mailRestObject, ParamRestObject paramRestObject) {
        return new MailParamWrapperRestObject(mailRestObject, paramRestObject);
    }
}
