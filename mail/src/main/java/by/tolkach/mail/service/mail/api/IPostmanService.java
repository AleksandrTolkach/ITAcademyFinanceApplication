package by.tolkach.mail.service.mail.api;

import by.tolkach.mail.dto.Mail;

public interface IPostmanService {
    void send(Mail mail, byte[] attachment);
}
