package by.tolkach.mailScheduler.service.scheduledMail.api;

import by.tolkach.mailScheduler.dto.scheduledMail.Mail;

import java.util.UUID;

public interface IMailService {
    Mail create(Mail mail);
    Mail read(UUID mailId);
}
