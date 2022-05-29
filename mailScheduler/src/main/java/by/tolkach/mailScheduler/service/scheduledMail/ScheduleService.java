package by.tolkach.mailScheduler.service.scheduledMail;

import by.tolkach.mailScheduler.dao.api.IScheduleStorage;
import by.tolkach.mailScheduler.dao.api.entity.ScheduleEntity;
import by.tolkach.mailScheduler.dao.api.entity.converter.IEntityConverter;
import by.tolkach.mailScheduler.dto.Schedule;
import by.tolkach.mailScheduler.service.scheduledMail.api.IScheduleService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ScheduleService implements IScheduleService {

    private final IScheduleStorage scheduleStorage;
    private final IEntityConverter<Schedule, ScheduleEntity> scheduleEntityConverter;

    public ScheduleService(IScheduleStorage scheduleStorage, IEntityConverter<Schedule, ScheduleEntity> scheduleEntityConverter) {
        this.scheduleStorage = scheduleStorage;
        this.scheduleEntityConverter = scheduleEntityConverter;
    }

    @Override
    public Schedule create(Schedule schedule) {
        ScheduleEntity scheduleEntity = this.scheduleEntityConverter.toEntity(schedule);
        scheduleEntity.setUuid(UUID.randomUUID());
        scheduleEntity = this.scheduleStorage.save(scheduleEntity);
        return this.scheduleEntityConverter.toDto(scheduleEntity);
    }

    @Override
    public Schedule read(UUID scheduleId) {
        ScheduleEntity scheduleEntity = this.scheduleStorage.findById(scheduleId).orElse(null);
        return this.scheduleEntityConverter.toDto(scheduleEntity);
    }

    @Override
    public Schedule update(UUID scheduleId, Schedule schedule) {
        ScheduleEntity scheduleEntity = this.scheduleStorage.findById(scheduleId).orElse(null);
        this.updateScheduleParameters(schedule, scheduleEntity);
        scheduleEntity = this.scheduleStorage.save(scheduleEntity);
        return this.scheduleEntityConverter.toDto(scheduleEntity);
    }

    private ScheduleEntity updateScheduleParameters(Schedule schedule, ScheduleEntity scheduleEntity) {
        scheduleEntity.setStartTime(schedule.getStartTime());
        scheduleEntity.setStopTime(schedule.getStopTime());
        scheduleEntity.setInterval(schedule.getInterval());
        scheduleEntity.setTimeUnit(schedule.getTimeUnit());
        return scheduleEntity;
    }
}
