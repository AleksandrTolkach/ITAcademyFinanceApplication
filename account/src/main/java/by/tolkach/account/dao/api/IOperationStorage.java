package by.tolkach.account.dao.api;

import by.tolkach.account.dao.api.entity.OperationEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IOperationStorage extends PagingAndSortingRepository<OperationEntity, UUID> {
    List<OperationEntity> findAllByAccount_Uuid(UUID id, Pageable pageable);
    OperationEntity findByUuidAndAccount_Uuid(UUID operationId, UUID accountId);
    OperationEntity findByAccount_UuidAndUuid(UUID accountId, UUID operationId);
}
