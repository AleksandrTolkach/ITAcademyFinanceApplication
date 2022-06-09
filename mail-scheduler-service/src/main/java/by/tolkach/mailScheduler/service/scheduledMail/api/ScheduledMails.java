package by.tolkach.mailScheduler.service.scheduledMail.api;

import by.tolkach.mailScheduler.dto.Schedule;
import by.tolkach.mailScheduler.dto.scheduledMail.Mail;
import by.tolkach.mailScheduler.dto.scheduledMail.Param;
import by.tolkach.mailScheduler.dto.scheduledMail.ScheduledMail;

import java.time.LocalDateTime;
import java.util.UUID;

public class ScheduledMails {

    private ScheduledMails() {
    }

    public static ScheduledMail createParameters(Mail mail, Param param, Schedule schedule) {
        LocalDateTime dtCreate = LocalDateTime.now();
        return ScheduledMail.Builder.createBuilder()
                .setUuid(UUID.randomUUID())
                .setDtCreate(dtCreate)
                .setDtUpdate(dtCreate)
                .setMail(mail)
                .setParam(param)
                .setSchedule(schedule)
                .build();
    }

    public static ScheduledMail updateParameters(ScheduledMail scheduledMail, Mail mail, Param param,
                                                 Schedule schedule) {
        scheduledMail.setDtUpdate(LocalDateTime.now());
        scheduledMail.setMail(mail);
        scheduledMail.setParam(param);
        scheduledMail.setSchedule(schedule);
        return scheduledMail;
    }
}
