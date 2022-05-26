package by.tolkach.mail.service.mail.api;

import by.tolkach.mail.dto.*;

public interface IMailService {
    Mail create(Mail mail, Param param, ReportType reportType);
    Page<Mail> read(SimplePageable simplePageable);
}
