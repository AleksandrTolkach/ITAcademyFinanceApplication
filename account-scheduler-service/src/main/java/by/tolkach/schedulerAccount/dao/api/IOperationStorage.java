package by.tolkach.schedulerAccount.dao.api;

import by.tolkach.schedulerAccount.dao.api.entity.OperationEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IOperationStorage extends PagingAndSortingRepository<OperationEntity, UUID> {
}
