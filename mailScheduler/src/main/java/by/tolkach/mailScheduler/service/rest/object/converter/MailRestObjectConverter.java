package by.tolkach.mailScheduler.service.rest.object.converter;

import by.tolkach.mailScheduler.dto.scheduledMail.Mail;
import by.tolkach.mailScheduler.service.rest.object.MailRestObject;
import org.springframework.stereotype.Component;

@Component
public class MailRestObjectConverter implements IRestObjectConverter<Mail, MailRestObject> {
    @Override
    public MailRestObject toRestObject(Mail mail) {
        return MailRestObject.Builder.createBuilder()
                .setUuid(mail.getUuid())
                .setAddress(mail.getAddress())
                .setSubject(mail.getSubject())
                .setText(mail.getText())
                .setDate(mail.getDate())
                .build();
    }

    @Override
    public Mail toDto(MailRestObject restObject) {
        return Mail.Builder.createBuilder()
                .setUuid(restObject.getUuid())
                .setAddress(restObject.getAddress())
                .setSubject(restObject.getSubject())
                .setText(restObject.getText())
                .setDate(restObject.getDate())
                .build();
    }
}
