package by.tolkach.classifier.service.api;

import by.tolkach.classifier.dto.OperationCategory;
import by.tolkach.classifier.dto.Page;
import by.tolkach.classifier.dto.SimplePageable;

public interface IOperationCategoryService {
    OperationCategory create(OperationCategory operationCategory);
    Page<OperationCategory> read(SimplePageable pageable);
}
