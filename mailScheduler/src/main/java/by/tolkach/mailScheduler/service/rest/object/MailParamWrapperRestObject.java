package by.tolkach.mailScheduler.service.rest.object;

public class MailParamWrapperRestObject {

    private MailRestObject mailRestObject;
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
