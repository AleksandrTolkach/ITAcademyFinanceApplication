package by.tolkach.account.service;

import by.tolkach.account.dto.Operation;
import by.tolkach.account.service.api.IValidationService;
import by.tolkach.account.service.api.exception.ValidationError;
import by.tolkach.account.service.api.exception.ValidationException;
import org.springframework.stereotype.Service;

@Service
public class OperationValidationService implements IValidationService<Operation> {

    @Override
    public Operation validate(Operation operation) {
        ValidationException validationException = new ValidationException();
        if (nullOrEmpty(operation.getDescription())) {
            validationException.add(new ValidationError("description", "Необходимо указать описание"));
        }
        if (nullOrEmpty(operation.getCurrency())) {
            validationException.add(new ValidationError("currency", "Неверная валюта"));
        }

        if (validationException.getErrorCount() > 0) {
            throw validationException;
        }
        return operation;
    }

    private boolean nullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    private boolean nullOrEmpty(Enum<?> value) {
        return value.name().equals("UNKNOWN");
    }
}
