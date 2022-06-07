package by.tolkach.schedulerAccount.service.scheduledOperation.api;

import by.tolkach.schedulerAccount.dto.Page;
import by.tolkach.schedulerAccount.dto.SimplePageable;
import by.tolkach.schedulerAccount.dto.scheduledOperation.Operation;
import by.tolkach.schedulerAccount.dto.scheduledOperation.Schedule;
import by.tolkach.schedulerAccount.dto.scheduledOperation.ScheduledOperation;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IScheduledOperationService {
    ScheduledOperation create(Schedule schedule, Operation operation);
    Page<ScheduledOperation> read(SimplePageable pageable);
    ScheduledOperation update(UUID operationId, LocalDateTime dtUpdate, Schedule schedule,  Operation operation);
}
