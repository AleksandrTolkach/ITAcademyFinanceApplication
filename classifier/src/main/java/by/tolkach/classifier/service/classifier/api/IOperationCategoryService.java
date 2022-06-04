package by.tolkach.classifier.service.classifier.api;

import by.tolkach.classifier.dto.OperationCategory;
import by.tolkach.classifier.dto.Page;
import by.tolkach.classifier.dto.SimplePageable;

import java.util.List;
import java.util.UUID;

public interface IOperationCategoryService {
    OperationCategory create(OperationCategory operationCategory);
    OperationCategory read(UUID operationCategoryId);
    Page<OperationCategory> read(SimplePageable pageable);
    List<OperationCategory> read();
    OperationCategory read(String title);
}
