package by.tolkach.schedulerAccount.service.scheduledOperation;

import by.tolkach.schedulerAccount.dto.exception.MultipleErrorsException;
import by.tolkach.schedulerAccount.dto.exception.SingleError;
import by.tolkach.schedulerAccount.dto.scheduledOperation.Operation;
import by.tolkach.schedulerAccount.service.api.IValidationService;
import by.tolkach.schedulerAccount.service.rest.api.IAccountRestClientService;
import by.tolkach.schedulerAccount.service.rest.api.IClassifierRestClientService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class OperationValidationService implements IValidationService<Operation> {

    private final IClassifierRestClientService classifierRestClientService;
    private final IAccountRestClientService accountRestClientService;

    public OperationValidationService(IClassifierRestClientService classifierRestClientService,
                                      IAccountRestClientService accountRestClientService) {
        this.classifierRestClientService = classifierRestClientService;
        this.accountRestClientService = accountRestClientService;
    }

    @Override
    public Operation validate(Operation operation) {
        MultipleErrorsException validationException = new MultipleErrorsException();
        this.accountRestClientService.readAccount(operation.getAccount());
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
