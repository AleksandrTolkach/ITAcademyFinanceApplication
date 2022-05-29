package by.tolkach.mailScheduler.dao.api.entity.converter;

import by.tolkach.mailScheduler.dao.api.entity.ScheduleEntity;
import by.tolkach.mailScheduler.dto.Schedule;
import org.springframework.stereotype.Component;

@Component
public class ScheduleEntityConverter implements IEntityConverter<Schedule, ScheduleEntity> {
    @Override
    public ScheduleEntity toEntity(Schedule schedule) {
        return ScheduleEntity.Builder.createBuilder()
                .setUuid(schedule.getUuid())
                .setStartTime(schedule.getStartTime())
                .setStopTime(schedule.getStopTime())
                .setInterval(schedule.getInterval())
                .setTimeUnit(schedule.getTimeUnit())
                .build();
    }

    @Override
    public Schedule toDto(ScheduleEntity entity) {
        return Schedule.Builder.createBuilder()
                .setUuid(entity.getUuid())
                .setStartTime(entity.getStartTime())
                .setStopTime(entity.getStopTime())
                .setInterval(entity.getInterval())
                .setTimeUnit(entity.getTimeUnit())
                .build();
    }
}
