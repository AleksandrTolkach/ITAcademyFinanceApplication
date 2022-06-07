package by.tolkach.mail.service.mail.api;

import by.tolkach.mail.dto.Mail;
import by.tolkach.mail.dto.Page;
import by.tolkach.mail.dto.SimplePageable;

import java.util.UUID;

public interface IMailService {
    Mail create(Mail mail);
    Page<Mail> read(SimplePageable simplePageable);
    Mail read(UUID mailId);
}
