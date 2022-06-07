package by.tolkach.mailScheduler.service.rest.api;

import by.tolkach.mailScheduler.dto.scheduledMail.Mail;
import by.tolkach.mailScheduler.dto.scheduledMail.Param;
import by.tolkach.mailScheduler.dto.scheduledMail.ReportType;

public interface IMailRestClientService {
    String create(Mail mail, Param param, ReportType reportType);
}
