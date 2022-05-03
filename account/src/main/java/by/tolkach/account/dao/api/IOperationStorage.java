package by.tolkach.account.dao.api;

import by.tolkach.account.dao.api.entity.OperationEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface IOperationStorage extends PagingAndSortingRepository<OperationEntity, UUID> {
    List<OperationEntity> findAllByAccount_Uuid(UUID id, Pageable pageable);
    OperationEntity findByAccount_UuidAndUuidAndDtUpdate(UUID accountId, UUID operationId, LocalDateTime dtUpdate);
}
