package by.tolkach.account.service.rest.api;

import by.tolkach.account.dto.operation.Operation;

import java.util.UUID;

public interface IReportRestClientService {
    void sendOperation(Operation operation, UUID accountId);
    void updateOperation(Operation operation, UUID accountId);
    void deleteOperation(UUID operationId, UUID accountId);
}
