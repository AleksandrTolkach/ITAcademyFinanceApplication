package by.tolkach.report.dao.api.helper;

import by.tolkach.report.dao.api.helper.entity.OperationEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface IOperationStorage extends PagingAndSortingRepository<OperationEntity, UUID> {
    OperationEntity findByUuidAndAccount(UUID operationId, UUID accountId);
    List<OperationEntity> findAllByAccountIn(List<UUID> accountId);
    List<OperationEntity> findAllByAccountInAndDateGreaterThanAndDateLessThanAndCategoryInOrderByDate(
            List<UUID> accountsId, LocalDateTime greaterDate, LocalDateTime lessDate, List<String> category);
    List<OperationEntity> findAllByAccountInAndDateGreaterThanAndDateLessThanAndCategoryInOrderByCategory(
            List<UUID> accountId, LocalDateTime greaterDate, LocalDateTime lessDate, List<String> category);
}
