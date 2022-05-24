package by.tolkach.report.service.helper.api;

import by.tolkach.report.dto.operation.Operation;
import by.tolkach.report.dto.report.Param;

import java.util.List;
import java.util.UUID;

public interface IOperationService {
    List<Operation> read(Param extendedParam);
    void save(Operation operation);
    void update(Operation operation);
    void delete(UUID operationId, UUID accountId);
}
