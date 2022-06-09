package by.tolkach.classifier.service.classifier.api;

import by.tolkach.classifier.dto.OperationCategory;

import java.time.LocalDateTime;

public class OperationCategories {

    private OperationCategories() {
    }

    public static OperationCategory createParameters(OperationCategory operationCategory) {
        LocalDateTime createdTime = LocalDateTime.now();
        operationCategory.setDtCreate(createdTime);
        operationCategory.setDtUpdate(createdTime);
        return operationCategory;
    }
}
