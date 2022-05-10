package by.tolkach.account.service.rest.api;

import by.tolkach.account.dto.Currency;
import by.tolkach.account.dto.operation.OperationCategory;

import java.util.UUID;

public interface IClassifierRestClientService {
    Currency readCurrency(UUID currencyId);
    OperationCategory readOperationCategory(UUID operationCategoryId);

}
