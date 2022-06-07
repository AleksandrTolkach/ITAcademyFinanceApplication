package by.tolkach.report.service.helper.api;

import by.tolkach.report.dto.operation.Operation;
import by.tolkach.report.dto.report.Param;

import java.util.List;
import java.util.UUID;

public interface IOperationService {
    List<Operation> readByBalance(Param param);
    List<Operation> readByDate(Param param);
    List<Operation> readByCategory(Param param);
    void save(Operation operation);
    void update(Operation operation);
    void delete(UUID operationId, UUID accountId);
}
