package by.tolkach.mailScheduler.service.scheduledMail;

import by.tolkach.mailScheduler.dao.api.IMailStorage;
import by.tolkach.mailScheduler.dao.api.entity.MailEntity;
import by.tolkach.mailScheduler.dao.api.entity.converter.IEntityConverter;
import by.tolkach.mailScheduler.dto.scheduledMail.Mail;
import by.tolkach.mailScheduler.service.scheduledMail.api.IMailService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MailService implements IMailService {

    private final IMailStorage mailStorage;
    private final IEntityConverter<Mail, MailEntity> mailEntityConverter;

    public MailService(IMailStorage mailStorage, IEntityConverter<Mail, MailEntity> mailEntityConverter) {
        this.mailStorage = mailStorage;
        this.mailEntityConverter = mailEntityConverter;
    }

    @Override
    public Mail create(Mail mail) {
        MailEntity mailEntity = this.mailEntityConverter.toEntity(mail);
        mailEntity.setUuid(UUID.randomUUID());
        mailEntity = this.mailStorage.save(mailEntity);
        return this.mailEntityConverter.toDto(mailEntity);
    }

    @Override
    public Mail read(UUID mailId) {
        MailEntity mailEntity = this.mailStorage.findById(mailId).orElse(null);
        return this.mailEntityConverter.toDto(mailEntity);
    }

    @Override
    public Mail update(UUID mailId, Mail mail) {
        MailEntity mailEntity = this.mailStorage.findById(mailId).orElse(null);
        updateMailEntityParameters(mail, mailEntity);
        mailEntity = this.mailStorage.save(mailEntity);
        return this.mailEntityConverter.toDto(mailEntity);
    }

    private MailEntity updateMailEntityParameters(Mail mail, MailEntity mailEntity) {
        mailEntity.setAddress(mail.getAddress());
        mailEntity.setSubject(mail.getSubject());
        mailEntity.setText(mail.getText());
        mailEntity.setDate(mail.getDate());
        return mailEntity;
    }
}
