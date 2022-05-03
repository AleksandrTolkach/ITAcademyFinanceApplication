package by.tolkach.account.service;

import by.tolkach.account.dto.Operation;
import by.tolkach.account.service.api.IValidationService;
import by.tolkach.account.service.api.exception.SingleError;
import by.tolkach.account.service.api.exception.MultipleErrorsException;
import org.springframework.stereotype.Service;

@Service
public class OperationValidationService implements IValidationService<Operation> {

    @Override
    public Operation validate(Operation operation) {
        MultipleErrorsException validationException = new MultipleErrorsException();
        if (nullOrEmpty(operation.getDescription())) {
            validationException.add(new SingleError("description", "Необходимо указать описание"));
        }
        if (nullOrEmpty(operation.getCurrency().toString())) {
            validationException.add(new SingleError("currency", "Неверная валюта"));
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
