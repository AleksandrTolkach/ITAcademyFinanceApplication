package by.tolkach.schedulerAccount.service.rest.api;

import by.tolkach.schedulerAccount.dto.Operation;

import java.util.UUID;

public interface IOperationRestClientService {
    String create(Operation operation);
    Operation read(UUID operationId, UUID accountId);
    String update(Operation operation);
}
