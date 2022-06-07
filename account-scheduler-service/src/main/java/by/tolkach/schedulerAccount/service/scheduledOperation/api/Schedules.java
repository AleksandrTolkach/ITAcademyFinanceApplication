package by.tolkach.schedulerAccount.service.scheduledOperation.api;

import by.tolkach.schedulerAccount.dao.api.entity.ScheduleEntity;
import by.tolkach.schedulerAccount.dto.scheduledOperation.Schedule;

public class Schedules {
    public static ScheduleEntity updateParameters(Schedule schedule, ScheduleEntity scheduleEntity) {
        scheduleEntity.setStartTime(schedule.getStartTime());
        scheduleEntity.setStopTime(schedule.getStopTime());
        scheduleEntity.setInterval(schedule.getInterval());
        scheduleEntity.setTimeUnit(schedule.getTimeUnit());
        return scheduleEntity;
    }
}
