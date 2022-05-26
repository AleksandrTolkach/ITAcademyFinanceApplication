package by.tolkach.mailScheduler.service.scheduler.api;

import by.tolkach.mailScheduler.dto.Schedule;

import java.util.UUID;

public interface ISchedulerService {
    void create(UUID mailId, Schedule schedule);
    void update(UUID mailId, Schedule schedule);
}
