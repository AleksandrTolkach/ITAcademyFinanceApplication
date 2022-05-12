package by.tolkach.classifier.service;

import by.tolkach.classifier.dto.OperationCategory;
import by.tolkach.classifier.service.api.IValidationService;
import by.tolkach.classifier.service.api.exception.MultipleErrorsException;
import by.tolkach.classifier.service.api.exception.NotFoundError;
import by.tolkach.classifier.service.api.exception.SingleError;
import org.springframework.stereotype.Service;

@Service
public class OperationCategoryValidationService implements IValidationService<OperationCategory> {
    @Override
    public OperationCategory validate(OperationCategory operationCategory) {
        MultipleErrorsException validationException = new MultipleErrorsException();
        if (operationCategory == null) {
            throw new NotFoundError("Необходимо передать объект категории.");
        }
        if (this.nullOrEmpty(operationCategory.getTitle())) {
            validationException.add(new SingleError("title", "Необходимо указать название валюты."));
        }

        if (validationException.getErrorCount() > 0) {
            throw validationException;
        }
        return operationCategory;
    }

    private boolean nullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
