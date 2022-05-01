package by.tolkach.account.service.api;

import by.tolkach.account.dto.Operation;
import by.tolkach.account.dto.Page;
import by.tolkach.account.dto.SimplePageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IOperationService {
    Operation create(Operation operation, UUID accountId);
    Page<Operation> read(UUID accountId, SimplePageable pageable);
    Operation read(UUID accountId);
    Operation update(UUID accountId, UUID operationId, LocalDateTime dtUpdate, Operation operation);
}
