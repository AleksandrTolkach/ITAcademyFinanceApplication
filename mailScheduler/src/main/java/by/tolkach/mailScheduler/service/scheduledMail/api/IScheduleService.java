package by.tolkach.mailScheduler.service.scheduledMail.api;

import by.tolkach.mailScheduler.dto.Schedule;

import java.util.UUID;

public interface IScheduleService {
    Schedule create(Schedule schedule);
    Schedule read(UUID scheduleId);
    Schedule update(UUID scheduleId, Schedule schedule);
}
