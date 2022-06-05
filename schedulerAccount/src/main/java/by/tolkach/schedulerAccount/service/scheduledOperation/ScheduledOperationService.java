package by.tolkach.schedulerAccount.service.scheduledOperation;

import by.tolkach.schedulerAccount.dao.api.IScheduledOperationStorage;
import by.tolkach.schedulerAccount.dao.api.entity.ScheduledOperationEntity;
import by.tolkach.schedulerAccount.dao.api.entity.converter.IEntityConverter;
import by.tolkach.schedulerAccount.dto.*;
import by.tolkach.schedulerAccount.dto.scheduledOperation.Operation;
import by.tolkach.schedulerAccount.dto.scheduledOperation.Schedule;
import by.tolkach.schedulerAccount.dto.scheduledOperation.ScheduledOperation;
import by.tolkach.schedulerAccount.service.api.IValidationService;
import by.tolkach.schedulerAccount.dto.exception.NotFoundException;
import by.tolkach.schedulerAccount.service.scheduledOperation.api.IOperationService;
import by.tolkach.schedulerAccount.service.scheduledOperation.api.IScheduleService;
import by.tolkach.schedulerAccount.service.scheduledOperation.api.IScheduledOperationService;
import by.tolkach.schedulerAccount.service.api.Pagination;
import by.tolkach.schedulerAccount.service.scheduledOperation.api.ScheduledOperations;
import by.tolkach.schedulerAccount.service.scheduler.api.ISchedulerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
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
        operation = this.operationService.create(operation);
        schedule = this.scheduleService.create(schedule);
        ScheduledOperation scheduledOperation = ScheduledOperations.createParameters(schedule, operation);
        ScheduledOperationEntity createdScheduledOperationEntity =
                this.scheduledOperationStorage.save(this.scheduledOperationEntityConverter.toEntity(scheduledOperation));
        this.schedulerService.create(operation.getUuid(), schedule);
        return this.scheduledOperationEntityConverter.toDto(createdScheduledOperationEntity);
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
            throw new NotFoundException("Указан неверный id операции.");
        }
        if (!scheduledOperationEntity.getDtUpdate().equals(dtUpdate)) {
            throw new NotFoundException("Запись устарела. Пожалуйста обновите запрос.");
        }
        this.scheduleService.update(scheduledOperationEntity.getSchedule().getUuid(), schedule);
        this.operationService.update(scheduledOperationEntity.getOperation().getUuid(), operation);
        scheduledOperationEntity.setDtUpdate(LocalDateTime.now());
        this.schedulerService.update(scheduledOperationEntity.getOperation().getUuid(), schedule);
        return this.scheduledOperationEntityConverter
                .toDto(this.scheduledOperationStorage.save(scheduledOperationEntity));
    }
}
