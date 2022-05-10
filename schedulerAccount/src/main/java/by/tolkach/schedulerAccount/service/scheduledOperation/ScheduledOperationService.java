package by.tolkach.schedulerAccount.service.scheduledOperation;

import by.tolkach.schedulerAccount.dao.api.IScheduledOperationStorage;
import by.tolkach.schedulerAccount.dao.api.entity.ScheduledOperationEntity;
import by.tolkach.schedulerAccount.dao.api.entity.converter.IEntityConverter;
import by.tolkach.schedulerAccount.dto.*;
import by.tolkach.schedulerAccount.service.rest.api.IClassifierRestClientService;
import by.tolkach.schedulerAccount.service.scheduledOperation.api.IOperationService;
import by.tolkach.schedulerAccount.service.scheduledOperation.api.IScheduleService;
import by.tolkach.schedulerAccount.service.scheduledOperation.api.IScheduledOperationService;
import by.tolkach.schedulerAccount.service.scheduledOperation.api.Pagination;
import by.tolkach.schedulerAccount.service.scheduler.SchedulerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ScheduledOperationService implements IScheduledOperationService {

    private final IOperationService operationService;
    private final IScheduleService scheduleService;
    private final SchedulerService schedulerService;
    private final IScheduledOperationStorage scheduledOperationStorage;
    private final IClassifierRestClientService classifierRestClientService;
    private final IEntityConverter<ScheduledOperation, ScheduledOperationEntity> scheduledOperationEntityConverter;

    public ScheduledOperationService(IOperationService operationService,
                                     IScheduleService scheduleService,
                                     SchedulerService schedulerService,
                                     IScheduledOperationStorage scheduledOperationStorage,
                                     IEntityConverter<ScheduledOperation, ScheduledOperationEntity> scheduledOperationEntityConverter,
                                     IClassifierRestClientService currencyRestClientService) {
        this.operationService = operationService;
        this.scheduleService = scheduleService;
        this.schedulerService = schedulerService;
        this.scheduledOperationStorage = scheduledOperationStorage;
        this.scheduledOperationEntityConverter = scheduledOperationEntityConverter;
        this.classifierRestClientService = currencyRestClientService;
    }

    @Override
    public ScheduledOperation create(Schedule schedule, Operation operation) {
        this.classifierRestClientService.readCurrency(operation.getCurrency());
        this.classifierRestClientService.readOperationCategory(operation.getCategory());
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
        this.schedulerService.create(createdOperation.getUuid(), createdSchedule);
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
        this.classifierRestClientService.readCurrency(operation.getCurrency());
        ScheduledOperationEntity scheduledOperationEntity =
                this.scheduledOperationStorage.findByUuidAndDtUpdate(scheduledOperationId, dtUpdate);
        this.scheduleService.update(scheduledOperationEntity.getSchedule().getUuid(), schedule);
        this.operationService.update(scheduledOperationEntity.getOperation().getUuid(), operation);
        scheduledOperationEntity.setDtUpdate(LocalDateTime.now().withNano(0));
        this.schedulerService.update(scheduledOperationEntity.getOperation().getUuid(), schedule);
        return this.scheduledOperationEntityConverter
                .toDto(this.scheduledOperationStorage.save(scheduledOperationEntity));
    }
}
