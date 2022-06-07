package by.tolkach.mailScheduler.dao;

import by.tolkach.mailScheduler.dao.api.entity.ScheduledMailEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IScheduledMailStorage extends PagingAndSortingRepository<ScheduledMailEntity, UUID> {
    List<ScheduledMailEntity> findAllBy(Pageable pageable);
}
