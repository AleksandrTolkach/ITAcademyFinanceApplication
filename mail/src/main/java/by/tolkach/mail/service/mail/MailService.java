package by.tolkach.mail.service.mail;

import by.tolkach.mail.dao.api.IMailStorage;
import by.tolkach.mail.dao.api.entity.MailEntity;
import by.tolkach.mail.dao.api.entity.converter.IEntityConverter;
import by.tolkach.mail.dto.*;
import by.tolkach.mail.service.api.Pagination;
import by.tolkach.mail.service.mail.api.IMailService;
import by.tolkach.mail.service.mail.api.IPostmanService;
import by.tolkach.mail.service.rest.api.IReportRestClientService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
        mailEntity.setDate(LocalDateTime.now());
        this.mailStorage.save(mailEntity);
        this.postmanService.send(mail, bytes);
        return this.mailEntityConverter.toDto(mailEntity);
    }

    @Override
    public Page<Mail> read(SimplePageable simplePageable) {
        List<MailEntity> mailEntities = this.mailStorage.findAllBy(PageRequest
                .of(simplePageable.getPage(), simplePageable.getSize()));
        List<Mail> mails = new ArrayList<>();
        for (MailEntity mailEntity: mailEntities) {
            mails.add(this.mailEntityConverter.toDto(mailEntity));
        }
        return Pagination.pageOf(Mail.class, MailEntity.class).properties(mailEntities, simplePageable,
                this.mailStorage.count(), this.mailEntityConverter);
    }
}
