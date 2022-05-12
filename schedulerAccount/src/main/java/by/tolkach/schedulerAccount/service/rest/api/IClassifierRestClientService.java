package by.tolkach.schedulerAccount.service.rest.api;

import by.tolkach.schedulerAccount.dto.Currency;
import by.tolkach.schedulerAccount.dto.scheduledOperation.OperationCategory;

import java.util.UUID;

public interface IClassifierRestClientService {
    Currency readCurrency(UUID currencyId);
    OperationCategory readOperationCategory(UUID operationCategoryId);

}
