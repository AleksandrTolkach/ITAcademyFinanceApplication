package by.tolkach.mailScheduler.service.scheduler.api;

import by.tolkach.mailScheduler.dto.Schedule;
import by.tolkach.mailScheduler.dto.scheduledMail.ReportType;

import java.util.UUID;

public interface ISchedulerService {
    void create(UUID mailId, UUID param, ReportType reportType, Schedule schedule);
    void update(UUID mailId, Schedule schedule);
}
