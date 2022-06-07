package by.tolkach.mailScheduler.dao.api.entity.converter;

import by.tolkach.mailScheduler.dao.api.entity.MailEntity;
import by.tolkach.mailScheduler.dto.scheduledMail.Mail;
import org.springframework.stereotype.Component;

@Component
public class MailEntityConverter implements IEntityConverter<Mail, MailEntity> {
    @Override
    public MailEntity toEntity(Mail mail) {
        return MailEntity.Builder.createBuilder()
                .setUuid(mail.getUuid())
                .setAddress(mail.getAddress())
                .setSubject(mail.getSubject())
                .setText(mail.getText())
                .setDate(mail.getDate())
                .build();
    }

    @Override
    public Mail toDto(MailEntity entity) {
        return Mail.Builder.createBuilder()
                .setUuid(entity.getUuid())
                .setAddress(entity.getAddress())
                .setSubject(entity.getSubject())
                .setText(entity.getText())
                .setDate(entity.getDate())
                .build();
    }
}
