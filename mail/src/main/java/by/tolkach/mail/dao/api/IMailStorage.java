package by.tolkach.mail.dao.api;

import by.tolkach.mail.dao.api.entity.MailEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface IMailStorage extends PagingAndSortingRepository<MailEntity, UUID> {
}
