package by.tolkach.schedulerAccount.service.api;

import by.tolkach.schedulerAccount.dto.Operation;

import java.util.UUID;

public interface IOperationService {
    Operation create(Operation operation);
    Operation read(UUID operationId);
    Operation update(UUID operationId, Operation operation);
}
