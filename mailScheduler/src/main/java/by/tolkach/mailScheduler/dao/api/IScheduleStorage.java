package by.tolkach.mailScheduler.dao.api;

import by.tolkach.mailScheduler.dao.api.entity.ScheduleEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IScheduleStorage extends PagingAndSortingRepository<ScheduleEntity, UUID> {
}
