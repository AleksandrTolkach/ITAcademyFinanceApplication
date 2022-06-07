package by.tolkach.mailScheduler.service.scheduledMail;

import by.tolkach.mailScheduler.dao.api.IMailStorage;
import by.tolkach.mailScheduler.dao.api.entity.MailEntity;
import by.tolkach.mailScheduler.dao.api.entity.converter.IEntityConverter;
import by.tolkach.mailScheduler.dto.exception.NotFoundException;
import by.tolkach.mailScheduler.dto.scheduledMail.Mail;
import by.tolkach.mailScheduler.service.scheduledMail.api.IMailService;
import by.tolkach.mailScheduler.service.scheduledMail.api.Mails;
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
        if (mailEntity == null) {
            throw new NotFoundException("Запланированной рассылки с таким ID не сущестует.");
        }
        return this.mailEntityConverter.toDto(mailEntity);
    }

    @Override
    public Mail update(UUID mailId, Mail mail) {
        MailEntity mailEntity = this.mailStorage.findById(mailId).orElse(null);
        if (mailEntity == null) {
            throw new NotFoundException("Запланированной рассылки с таким ID не сущестует.");
        }
        Mails.updateParameters(mail, mailEntity);
        mailEntity = this.mailStorage.save(mailEntity);
        return this.mailEntityConverter.toDto(mailEntity);
    }
}
