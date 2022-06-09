package by.tolkach.schedulerAccount.service.rest.api;

import by.tolkach.schedulerAccount.dto.scheduledOperation.Operation;

import java.util.UUID;

public interface IAccountRestClientService {
    void readAccount(UUID accountId);
    String createOperation(Operation operation);
    Operation readOperation(UUID operationId, UUID accountId);
    String updateOperation(Operation operation);
}
