package by.tolkach.report.service.api;

import by.tolkach.report.dto.Operation;
import by.tolkach.report.dto.reportParam.ExtendedParam;

import java.util.List;
import java.util.UUID;

public interface IOperationService {
    List<Operation> readByBalance(ExtendedParam extendedParam);
    void save(Operation operation);
    void update(Operation operation);
    void delete(UUID operationId, UUID accountId);
}
