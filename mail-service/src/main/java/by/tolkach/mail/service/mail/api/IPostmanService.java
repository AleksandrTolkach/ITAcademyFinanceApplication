package by.tolkach.mail.service.mail.api;

import by.tolkach.mail.dto.*;

import java.util.UUID;

public interface IPostmanService {
    Mail send(Mail mail, Param param, ReportType reportType);
    Mail resend(UUID mailId, UUID reportId);
    Page<Mail> read(SimplePageable simplePageable);
}
