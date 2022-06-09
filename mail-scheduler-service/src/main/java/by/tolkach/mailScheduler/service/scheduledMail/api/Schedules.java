package by.tolkach.mailScheduler.service.scheduledMail.api;

import by.tolkach.mailScheduler.dao.api.entity.ScheduleEntity;
import by.tolkach.mailScheduler.dto.Schedule;

public class Schedules {

    private Schedules() {
    }

    public static ScheduleEntity updateParameters(Schedule schedule, ScheduleEntity scheduleEntity) {
        scheduleEntity.setStartTime(schedule.getStartTime());
        scheduleEntity.setStopTime(schedule.getStopTime());
        scheduleEntity.setInterval(schedule.getInterval());
        scheduleEntity.setTimeUnit(schedule.getTimeUnit());
        return scheduleEntity;
    }
}
