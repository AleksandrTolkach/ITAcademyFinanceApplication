package by.tolkach.mailScheduler.service.scheduledMail;

import by.tolkach.mailScheduler.dto.Schedule;
import by.tolkach.mailScheduler.dto.SimplePageable;
import by.tolkach.mailScheduler.dto.scheduledMail.Mail;
import by.tolkach.mailScheduler.dto.scheduledMail.Param;
import by.tolkach.mailScheduler.dto.scheduledMail.ReportType;
import by.tolkach.mailScheduler.service.scheduledMail.api.IScheduledMailService;
import org.springframework.stereotype.Service;

@Service
public class ScheduledMailService implements IScheduledMailService {
    @Override
    public Mail create(Mail mail, Param param, ReportType reportType, Schedule schedule) {

        return null;
    }

    @Override
    public Mail read(SimplePageable simplePageable) {
        return null;
    }

}
