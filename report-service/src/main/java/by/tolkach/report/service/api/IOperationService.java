package by.tolkach.report.service.api;

import by.tolkach.report.dto.Operation;

import java.util.UUID;

public interface IOperationService {
    void save(Operation operation);
    void update(Operation operation);
    void delete(UUID operationId, UUID accountId);
}
