package by.tolkach.schedulerAccount.service.scheduledOperation;

import by.tolkach.schedulerAccount.dao.api.IScheduledOperationStorage;
import by.tolkach.schedulerAccount.dao.api.entity.ScheduledOperationEntity;
import by.tolkach.schedulerAccount.dao.api.entity.converter.IEntityConverter;
import by.tolkach.schedulerAccount.dto.*;
import by.tolkach.schedulerAccount.dto.scheduledOperation.Operation;
import by.tolkach.schedulerAccount.dto.scheduledOperation.Schedule;
import by.tolkach.schedulerAccount.dto.scheduledOperation.ScheduledOperation;
import by.tolkach.schedulerAccount.service.api.IValidationService;
import by.tolkach.schedulerAccount.dto.exception.NotFoundError;
import by.tolkach.schedulerAccount.service.scheduledOperation.api.IOperationService;
import by.tolkach.schedulerAccount.service.scheduledOperation.api.IScheduleService;
import by.tolkach.schedulerAccount.service.scheduledOperation.api.IScheduledOperationService;
import by.tolkach.schedulerAccount.service.api.Pagination;
import by.tolkach.schedulerAccount.service.scheduler.api.ISchedulerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ScheduledOperationService implements IScheduledOperationService {

    private final IOperationService operationService;
    private final IScheduleService scheduleService;
    private final ISchedulerService schedulerService;
    private final IScheduledOperationStorage scheduledOperationStorage;
    private final IEntityConverter<ScheduledOperation, ScheduledOperationEntity> scheduledOperationEntityConverter;
    private final IValidationService<Schedule> scheduleValidationService;
    private final IValidationService<Operation> operationValidationService;

    public ScheduledOperationService(IOperationService operationService,
                                     IScheduleService scheduleService,
                                     ISchedulerService schedulerService,
                                     IScheduledOperationStorage scheduledOperationStorage,
                                     IEntityConverter<ScheduledOperation, ScheduledOperationEntity> scheduledOperationEntityConverter,
                                     IValidationService<Schedule> scheduleValidationService,
                                     IValidationService<Operation> operationValidationService) {
        this.operationService = operationService;
        this.scheduleService = scheduleService;
        this.schedulerService = schedulerService;
        this.scheduledOperationStorage = scheduledOperationStorage;
        this.scheduledOperationEntityConverter = scheduledOperationEntityConverter;
        this.scheduleValidationService = scheduleValidationService;
        this.operationValidationService = operationValidationService;
    }

    @Override
    public ScheduledOperation create(Schedule schedule, Operation operation) {
        this.scheduleValidationService.validate(schedule);
        this.operationValidationService.validate(operation);
        Operation createdOperation = this.operationService.create(operation);
        Schedule createdSchedule = this.scheduleService.create(schedule);
        ScheduledOperation scheduledOperation = this.createScheduledOperationProperties(schedule, operation);
        ScheduledOperationEntity createdScheduledOperation =
                this.scheduledOperationStorage.save(this.scheduledOperationEntityConverter.toEntity(scheduledOperation));
        this.schedulerService.create(createdOperation.getUuid(), createdSchedule);
        return this.scheduledOperationEntityConverter.toDto(createdScheduledOperation);
    }

    @Override
    public Page<ScheduledOperation> read(SimplePageable pageable) {
        List<ScheduledOperationEntity> scheduledOperationEntities =
                this.scheduledOperationStorage.findAllBy(PageRequest.of(pageable.getPage(), pageable.getSize()));
        return Pagination.pageOf(ScheduledOperation.class, ScheduledOperationEntity.class)
                .properties(scheduledOperationEntities, pageable, this.scheduledOperationStorage.count(),
                        this.scheduledOperationEntityConverter);
    }

    @Override
    public ScheduledOperation update(UUID scheduledOperationId, LocalDateTime dtUpdate, Schedule schedule,
                                     Operation operation) {
        this.scheduleValidationService.validate(schedule);
        this.operationValidationService.validate(operation);
        ScheduledOperationEntity scheduledOperationEntity =
                this.scheduledOperationStorage.findById(scheduledOperationId).orElse(null);
        if (scheduledOperationEntity == null) {
            throw new NotFoundError("Указан неверный id операции.");
        }
        if (!scheduledOperationEntity.getDtUpdate().equals(dtUpdate)) {
            throw new NotFoundError("Запись устарела. Пожалуйста обновите запрос.");
        }
        this.scheduleService.update(scheduledOperationEntity.getSchedule().getUuid(), schedule);
        this.operationService.update(scheduledOperationEntity.getOperation().getUuid(), operation);
        scheduledOperationEntity.setDtUpdate(LocalDateTime.now().withNano(0));
        this.schedulerService.update(scheduledOperationEntity.getOperation().getUuid(), schedule);
        return this.scheduledOperationEntityConverter
                .toDto(this.scheduledOperationStorage.save(scheduledOperationEntity));
    }

    public ScheduledOperation createScheduledOperationProperties(Schedule schedule, Operation operation) {
        LocalDateTime dtCreate = LocalDateTime.now().withNano(0);
        return ScheduledOperation.Builder.createBuilder()
                .setDtCreate(dtCreate)
                .setDtUpdate(dtCreate)
                .setSchedule(schedule)
                .setOperation(operation)
                .build();
    }
}
