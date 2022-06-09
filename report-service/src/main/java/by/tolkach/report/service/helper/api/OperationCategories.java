package by.tolkach.report.service.helper.api;

import by.tolkach.report.dao.api.helper.entity.OperationCategoryEntity;
import by.tolkach.report.dto.exception.NotFoundException;
import by.tolkach.report.dto.operation.OperationCategory;

public class OperationCategories {

    private OperationCategories() {
    }

    public static OperationCategoryEntity updateOperationCategoryParameters(OperationCategory operationCategory,
                                                                      OperationCategoryEntity operationCategoryEntity) {
        operationCategoryEntity.setDtCreate(operationCategory.getDtCreate());
        operationCategoryEntity.setDtUpdate(operationCategory.getDtUpdate());
        operationCategoryEntity.setTitle(operationCategory.getTitle());
        return operationCategoryEntity;
    }

    public static boolean isExistingCategory(OperationCategoryEntity operationCategoryEntity) {
        if (operationCategoryEntity == null) {
            throw new NotFoundException("Указанной категории не существует");
        }
        return true;
    }
}
