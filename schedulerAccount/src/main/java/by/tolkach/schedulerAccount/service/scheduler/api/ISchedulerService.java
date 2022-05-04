package by.tolkach.schedulerAccount.service.scheduler.api;

import by.tolkach.schedulerAccount.dto.Schedule;

import java.util.UUID;

public interface ISchedulerService {
    void create(UUID operationId, Schedule schedule);
    void update(UUID operationId, Schedule schedule);
}
