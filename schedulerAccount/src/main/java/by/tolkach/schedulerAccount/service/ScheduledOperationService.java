package by.tolkach.schedulerAccount.service;

import by.tolkach.schedulerAccount.dao.api.IScheduledOperationStorage;
import by.tolkach.schedulerAccount.dao.api.entity.OperationEntity;
import by.tolkach.schedulerAccount.dao.api.entity.ScheduleEntity;
import by.tolkach.schedulerAccount.dao.api.entity.ScheduledOperationEntity;
import by.tolkach.schedulerAccount.dao.api.entity.converter.IEntityConverter;
import by.tolkach.schedulerAccount.dto.*;
import by.tolkach.schedulerAccount.service.api.IOperationService;
import by.tolkach.schedulerAccount.service.api.IScheduleService;
import by.tolkach.schedulerAccount.service.api.IScheduledOperationService;
import by.tolkach.schedulerAccount.service.api.Pagination;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ScheduledOperationService implements IScheduledOperationService {

    private final IOperationService operationService;
    private final IScheduleService scheduleService;
    private final IScheduledOperationStorage scheduledOperationStorage;
    private final IEntityConverter<ScheduledOperation, ScheduledOperationEntity> scheduledOperationEntityConverter;
    private final IEntityConverter<Schedule, ScheduleEntity> scheduleEntityConverter;
    private final IEntityConverter<Operation, OperationEntity> operationEntityConverter;

    public ScheduledOperationService(IOperationService operationService,
                                     IScheduleService scheduleService,
                                     IScheduledOperationStorage scheduledOperationStorage,
                                     IEntityConverter<ScheduledOperation, ScheduledOperationEntity> scheduledOperationEntityConverter,
                                     IEntityConverter<Schedule, ScheduleEntity> scheduleEntityConverter,
                                     IEntityConverter<Operation, OperationEntity> operationEntityConverter) {
        this.operationService = operationService;
        this.scheduleService = scheduleService;
        this.scheduledOperationStorage = scheduledOperationStorage;
        this.scheduledOperationEntityConverter = scheduledOperationEntityConverter;
        this.scheduleEntityConverter = scheduleEntityConverter;
        this.operationEntityConverter = operationEntityConverter;
    }

    @Override
    public ScheduledOperation create(Schedule schedule, Operation operation) {
        Operation createdOperation = this.operationService.create(operation);
        Schedule createdSchedule = this.scheduleService.create(schedule);
        LocalDateTime dtCreate = LocalDateTime.now().withNano(0);
        ScheduledOperation scheduledOperation = ScheduledOperation.Builder.createBuilder()
                .setUuid(UUID.randomUUID())
                .setDtCreate(dtCreate)
                .setDtUpdate(dtCreate)
                .setSchedule(createdSchedule)
                .setOperation(createdOperation)
                .build();
        ScheduledOperationEntity createdScheduledOperation =
                this.scheduledOperationStorage.save(this.scheduledOperationEntityConverter.toEntity(scheduledOperation));
        return this.scheduledOperationEntityConverter.toDto(createdScheduledOperation);
    }

    @Override
    public Page<ScheduledOperation> read(SimplePageable pageable) {
        List<ScheduledOperationEntity> scheduledOperationEntities =
                this.scheduledOperationStorage.findAllBy(PageRequest.of(pageable.getPage(), pageable.getSize()));
        return Pagination.pageOf(ScheduledOperation.class, ScheduledOperationEntity.class)
                .properties(scheduledOperationEntities, pageable, (int) this.scheduledOperationStorage.count(),
                        this.scheduledOperationEntityConverter);
    }

    @Override
    public ScheduledOperation update(UUID scheduledOperationId, LocalDateTime dtUpdate, Schedule schedule,
                                     Operation operation) {
        ScheduledOperationEntity scheduledOperationEntity =
                this.scheduledOperationStorage.findByUuidAndDtUpdate(scheduledOperationId, dtUpdate);
        this.scheduleService.update(scheduledOperationEntity.getSchedule().getUuid(), schedule);
        this.operationService.update(scheduledOperationEntity.getOperation().getUuid(), operation);
        scheduledOperationEntity.setDtUpdate(LocalDateTime.now().withNano(0));
        return this.scheduledOperationEntityConverter
                .toDto(this.scheduledOperationStorage.save(scheduledOperationEntity));
    }
}
