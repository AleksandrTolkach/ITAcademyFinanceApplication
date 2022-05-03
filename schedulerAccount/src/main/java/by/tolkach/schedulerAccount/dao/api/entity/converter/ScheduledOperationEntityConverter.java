package by.tolkach.schedulerAccount.dao.api.entity.converter;

import by.tolkach.schedulerAccount.dao.api.entity.OperationEntity;
import by.tolkach.schedulerAccount.dao.api.entity.ScheduleEntity;
import by.tolkach.schedulerAccount.dao.api.entity.ScheduledOperationEntity;
import by.tolkach.schedulerAccount.dto.Operation;
import by.tolkach.schedulerAccount.dto.Schedule;
import by.tolkach.schedulerAccount.dto.ScheduledOperation;
import org.springframework.stereotype.Component;

@Component
public class ScheduledOperationEntityConverter implements IEntityConverter<ScheduledOperation, ScheduledOperationEntity> {

    private final IEntityConverter<Schedule, ScheduleEntity> scheduleEntityConverter;
    private final IEntityConverter<Operation, OperationEntity> operationEntityConverter;

    public ScheduledOperationEntityConverter(IEntityConverter<Schedule, ScheduleEntity> scheduleEntityConverter,
                                             IEntityConverter<Operation, OperationEntity> operationEntityConverter) {
        this.scheduleEntityConverter = scheduleEntityConverter;
        this.operationEntityConverter = operationEntityConverter;
    }

    @Override
    public ScheduledOperationEntity toEntity(ScheduledOperation scheduledOperation) {
        return ScheduledOperationEntity.Builder.createBuilder()
                .setUuid(scheduledOperation.getUuid())
                .setDtCreate(scheduledOperation.getDtCreate())
                .setDtUpdate(scheduledOperation.getDtUpdate())
                .setSchedule(this.scheduleEntityConverter.toEntity(scheduledOperation.getSchedule()))
                .setOperation(this.operationEntityConverter.toEntity(scheduledOperation.getOperation()))
                .build();
    }

    @Override
    public ScheduledOperation toDto(ScheduledOperationEntity entity) {
        return ScheduledOperation.Builder.createBuilder()
                .setUuid(entity.getUuid())
                .setDtUpdate(entity.getDtUpdate())
                .setDtCreate(entity.getDtCreate())
                .setSchedule(this.scheduleEntityConverter.toDto(entity.getSchedule()))
                .setOperation(this.operationEntityConverter.toDto(entity.getOperation()))
                .build();
    }
}
