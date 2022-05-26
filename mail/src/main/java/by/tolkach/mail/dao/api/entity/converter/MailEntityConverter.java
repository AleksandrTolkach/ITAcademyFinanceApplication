package by.tolkach.mail.dao.api.entity.converter;

import by.tolkach.mail.dao.api.entity.MailEntity;
import by.tolkach.mail.dto.Mail;
import org.springframework.stereotype.Component;

@Component
public class MailEntityConverter implements IEntityConverter<Mail, MailEntity> {
    @Override
    public MailEntity toEntity(Mail mail) {
        return MailEntity.Builder.createBuilder()
                .setUuid(mail.getUuid())
                .setDtCreate(mail.getDtCreate())
                .setDtUpdate(mail.getDtUpdate())
                .setAddress(mail.getAddress())
                .setSubject(mail.getSubject())
                .setText(mail.getText())
                .build();
    }

    @Override
    public Mail toDto(MailEntity entity) {
        return Mail.Builder.createBuilder()
                .setUuid(entity.getUuid())
                .setDtCreate(entity.getDtCreate())
                .setDtUpdate(entity.getDtUpdate())
                .setAddress(entity.getAddress())
                .setSubject(entity.getSubject())
                .setText(entity.getText())
                .build();
    }
}
