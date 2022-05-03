package by.tolkach.account.dao.api;

import by.tolkach.account.dao.api.entity.BalanceEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface IBalanceStorage extends PagingAndSortingRepository<BalanceEntity, UUID> {
    BalanceEntity findByAccount_Uuid(UUID accountId);
    BalanceEntity findByAccount_UuidAndDtUpdate(UUID accountId, LocalDateTime dtUpdate);
}
