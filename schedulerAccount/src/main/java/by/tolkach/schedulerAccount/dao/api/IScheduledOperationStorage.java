package by.tolkach.schedulerAccount.dao.api;

import by.tolkach.schedulerAccount.dao.api.entity.ScheduledOperationEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface IScheduledOperationStorage extends PagingAndSortingRepository<ScheduledOperationEntity, UUID> {
    List<ScheduledOperationEntity> findAllBy(Pageable pageable);
    ScheduledOperationEntity findByUuidAndDtUpdate(UUID scheduledOperationId, LocalDateTime dtUpdate);

}
