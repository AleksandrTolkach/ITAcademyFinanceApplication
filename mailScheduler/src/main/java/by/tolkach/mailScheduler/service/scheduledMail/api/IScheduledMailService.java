package by.tolkach.mailScheduler.service.scheduledMail.api;

import by.tolkach.mailScheduler.dto.Page;
import by.tolkach.mailScheduler.dto.Schedule;
import by.tolkach.mailScheduler.dto.SimplePageable;
import by.tolkach.mailScheduler.dto.scheduledMail.Mail;
import by.tolkach.mailScheduler.dto.scheduledMail.Param;
import by.tolkach.mailScheduler.dto.scheduledMail.ReportType;
import by.tolkach.mailScheduler.dto.scheduledMail.ScheduledMail;

public interface IScheduledMailService {
    ScheduledMail create(Mail mail, Param param, ReportType reportType, Schedule schedule);
    Page<ScheduledMail> read(SimplePageable simplePageable);
}
