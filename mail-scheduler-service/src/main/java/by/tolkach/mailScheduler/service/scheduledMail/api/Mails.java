package by.tolkach.mailScheduler.service.scheduledMail.api;

import by.tolkach.mailScheduler.dao.api.entity.MailEntity;
import by.tolkach.mailScheduler.dto.scheduledMail.Mail;

public class Mails {

    private Mails() {
    }

    public static MailEntity updateParameters(Mail mail, MailEntity mailEntity) {
        mailEntity.setAddress(mail.getAddress());
        mailEntity.setSubject(mail.getSubject());
        mailEntity.setText(mail.getText());
        mailEntity.setDate(mail.getDate());
        return mailEntity;
    }
}
