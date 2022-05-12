package by.tolkach.schedulerAccount.service.scheduledOperation.api;

import by.tolkach.schedulerAccount.dto.scheduledOperation.Operation;

import java.util.UUID;

public interface IOperationService {
    Operation create(Operation operation);
    Operation read(UUID operationId);
    Operation update(UUID operationId, Operation operation);
}
