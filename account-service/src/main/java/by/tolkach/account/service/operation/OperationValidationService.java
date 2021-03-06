package by.tolkach.account.service.operation;

import by.tolkach.account.dto.exception.MultipleErrorsException;
import by.tolkach.account.dto.exception.NotFoundException;
import by.tolkach.account.dto.exception.SingleError;
import by.tolkach.account.dto.operation.Operation;
import by.tolkach.account.service.api.IValidationService;
import by.tolkach.account.service.rest.api.IClassifierRestClientService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class OperationValidationService implements IValidationService<Operation> {

    private final IClassifierRestClientService classifierRestClientService;

    public OperationValidationService(IClassifierRestClientService classifierRestClientService) {
        this.classifierRestClientService = classifierRestClientService;
    }

    @Override
    public Operation validate(Operation operation) {
        MultipleErrorsException validationException = new MultipleErrorsException();
        if (operation == null) {
            throw new NotFoundException("Необходимо передать объект операции");
        }

        if (nullOrEmpty(operation.getDescription())) {
            validationException.add(new SingleError("description", "Необходимо указать описание"));
        }

        try {
            this.classifierRestClientService.readOperationCategory(operation.getCategory());
        } catch (HttpClientErrorException e) {
            validationException.add(new SingleError("category", "Указанной категории нет в базе"));
        }

        try {
            this.classifierRestClientService.readCurrency(operation.getCurrency());
        } catch (HttpClientErrorException e) {
            validationException.add(new SingleError("currency", "Указанной валюты нет в базе"));
        }

        if (validationException.getErrorCount() > 0) {
            throw validationException;
        }
        return operation;
    }

    private boolean nullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
