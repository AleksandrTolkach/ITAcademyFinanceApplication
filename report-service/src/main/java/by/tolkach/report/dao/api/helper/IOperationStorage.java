package by.tolkach.report.dao.api.helper;

import by.tolkach.report.dao.api.entity.operation.OperationEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IOperationStorage extends PagingAndSortingRepository<OperationEntity, UUID> {
    OperationEntity findByUuidAndAccount(UUID operationId, UUID accountId);
    List<OperationEntity> findAllByAccount(UUID accountId);
}