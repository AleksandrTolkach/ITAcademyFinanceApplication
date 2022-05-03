package by.tolkach.schedulerAccount.service.api;

import by.tolkach.schedulerAccount.dto.*;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IScheduledOperationService {
    ScheduledOperation create(Schedule schedule, Operation operation);
    Page<ScheduledOperation> read(SimplePageable pageable);
    ScheduledOperation update(UUID operationId, LocalDateTime dtUpdate, Schedule schedule,  Operation operation);
}
