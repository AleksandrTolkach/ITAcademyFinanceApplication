package by.tolkach.report.service.helper.api;

import by.tolkach.report.dto.operation.OperationCategory;

import java.util.UUID;

public interface IOperationCategoryService {
    OperationCategory save(OperationCategory operationCategory);
    OperationCategory read(UUID operationCategoryId);
    OperationCategory read(String title);
    OperationCategory update(OperationCategory operationCategory);
}
