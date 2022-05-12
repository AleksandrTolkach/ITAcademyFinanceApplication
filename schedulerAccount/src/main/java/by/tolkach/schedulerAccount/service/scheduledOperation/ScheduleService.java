package by.tolkach.schedulerAccount.service.scheduledOperation;

import by.tolkach.schedulerAccount.dao.api.IScheduleStorage;
import by.tolkach.schedulerAccount.dao.api.entity.ScheduleEntity;
import by.tolkach.schedulerAccount.dao.api.entity.converter.IEntityConverter;
import by.tolkach.schedulerAccount.dto.scheduledOperation.Schedule;
import by.tolkach.schedulerAccount.dto.exception.NotFoundError;
import by.tolkach.schedulerAccount.service.scheduledOperation.api.IScheduleService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ScheduleService implements IScheduleService {

    private final IEntityConverter<Schedule, ScheduleEntity> scheduleEntityConverter;
    private final IScheduleStorage scheduleStorage;

    public ScheduleService(IEntityConverter<Schedule, ScheduleEntity> scheduleEntityConverter, IScheduleStorage scheduleStorage) {
        this.scheduleEntityConverter = scheduleEntityConverter;
        this.scheduleStorage = scheduleStorage;
    }

    @Override
    public Schedule create(Schedule schedule) {
        schedule.setUuid(UUID.randomUUID());
        ScheduleEntity savedScheduleEntity = this.scheduleStorage.save(this.scheduleEntityConverter.toEntity(schedule));
        return this.scheduleEntityConverter.toDto(savedScheduleEntity);
    }

    @Override
    public Schedule read(UUID scheduleId) {
        ScheduleEntity scheduleEntity = this.scheduleStorage.findById(scheduleId).orElse(null);
        if (scheduleEntity == null) {
            throw new NotFoundError("Расписание отсутствует в базе");
        }
        return this.scheduleEntityConverter.toDto(scheduleEntity);
    }

    @Override
    public Schedule update(UUID scheduleId, Schedule schedule) {
        ScheduleEntity scheduleEntity = this.scheduleStorage.findById(scheduleId).orElse(null);
        if (scheduleEntity == null) {
            throw new NotFoundError("Расписание отсутствует в базе");
        }
        scheduleEntity = this.updateScheduleProperties(scheduleEntity, schedule);
        return this.scheduleEntityConverter.toDto(this.scheduleStorage.save(scheduleEntity));
    }

    public ScheduleEntity updateScheduleProperties(ScheduleEntity scheduleEntity, Schedule schedule) {
        scheduleEntity.setStartTime(schedule.getStartTime());
        scheduleEntity.setStopTime(schedule.getStopTime());
        scheduleEntity.setInterval(schedule.getInterval());
        scheduleEntity.setTimeUnit(schedule.getTimeUnit());
        return scheduleEntity;
    }
}
