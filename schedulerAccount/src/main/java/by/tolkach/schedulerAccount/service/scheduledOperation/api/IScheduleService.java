package by.tolkach.schedulerAccount.service.scheduledOperation.api;

import by.tolkach.schedulerAccount.dto.Schedule;

import java.util.UUID;

public interface IScheduleService {
    Schedule create(Schedule schedule);
    Schedule read(UUID scheduleId);
    Schedule update(UUID scheduleId, Schedule schedule);
}
