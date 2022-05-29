package by.tolkach.mail.service.mail;

import by.tolkach.mail.dao.api.IMailStorage;
import by.tolkach.mail.dao.api.entity.MailEntity;
import by.tolkach.mail.dao.api.entity.converter.IEntityConverter;
import by.tolkach.mail.dto.*;
import by.tolkach.mail.service.api.Pagination;
import by.tolkach.mail.service.mail.api.IMailService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
        mailEntity.setDate(LocalDateTime.now());
        this.mailStorage.save(mailEntity);
        return this.mailEntityConverter.toDto(mailEntity);
    }

    @Override
    public Page<Mail> read(SimplePageable simplePageable) {
        List<MailEntity> mailEntities = this.mailStorage.findAllBy(PageRequest
                .of(simplePageable.getPage(), simplePageable.getSize()));
        return Pagination.pageOf(Mail.class, MailEntity.class).properties(mailEntities, simplePageable,
                this.mailStorage.count(), this.mailEntityConverter);
    }

    @Override
    public Mail read(UUID mailId) {
        MailEntity mailEntity = this.mailStorage.findById(mailId).orElse(null);
        return this.mailEntityConverter.toDto(mailEntity);
    }
}
