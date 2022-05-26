package by.tolkach.mail.service.mail;

import by.tolkach.mail.dao.api.IMailStorage;
import by.tolkach.mail.dao.api.entity.MailEntity;
import by.tolkach.mail.dao.api.entity.converter.IEntityConverter;
import by.tolkach.mail.dto.*;
import by.tolkach.mail.service.mail.api.IMailService;
import by.tolkach.mail.service.mail.api.IPostmanService;
import by.tolkach.mail.service.rest.api.IReportRestClientService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MailService implements IMailService {

    private final IReportRestClientService reportRestClientService;
    private final IMailStorage mailStorage;
    private final IEntityConverter<Mail, MailEntity> mailEntityConverter;
    private final IPostmanService postmanService;

    public MailService(IReportRestClientService reportRestClientService,
                       IMailStorage mailStorage,
                       IEntityConverter<Mail, MailEntity> mailEntityConverter,
                       IPostmanService postmanService) {
        this.reportRestClientService = reportRestClientService;
        this.mailStorage = mailStorage;
        this.mailEntityConverter = mailEntityConverter;
        this.postmanService = postmanService;
    }

    @Override
    public Mail create(Mail mail, Param param, ReportType reportType) {
        byte[] bytes = this.reportRestClientService.create(param, reportType);
        MailEntity mailEntity = this.mailEntityConverter.toEntity(mail);
        mailEntity.setUuid(UUID.randomUUID());
        this.mailStorage.save(mailEntity);
        this.postmanService.send(mail, bytes);
        return this.mailEntityConverter.toDto(mailEntity);
    }

    @Override
    public Page<Mail> read(SimplePageable simplePageable) {
        return null;
    }
}
