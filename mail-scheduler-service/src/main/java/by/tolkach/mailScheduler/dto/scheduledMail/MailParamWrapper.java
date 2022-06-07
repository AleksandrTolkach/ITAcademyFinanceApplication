package by.tolkach.mailScheduler.dto.scheduledMail;

import by.tolkach.mailScheduler.dto.Schedule;

public class MailParamWrapper {

    private Mail mail;
    private Param param;
    private Schedule schedule;

    public MailParamWrapper() {
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    public Param getParam() {
        return param;
    }

    public void setParam(Param param) {
        this.param = param;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
