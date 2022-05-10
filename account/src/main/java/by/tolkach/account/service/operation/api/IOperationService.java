package by.tolkach.account.service.operation.api;

import by.tolkach.account.dto.operation.Operation;
import by.tolkach.account.dto.Page;
import by.tolkach.account.dto.SimplePageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IOperationService {
    Operation create(Operation operation, UUID accountId);
    Page<Operation> read(UUID accountId, SimplePageable pageable);
    Operation read(UUID operationId, UUID accountId);
    Operation update(UUID accountId, UUID operationId, LocalDateTime dtUpdate, Operation operation);
    void delete(UUID accountId, UUID operationId, LocalDateTime dtUpdate);
}
