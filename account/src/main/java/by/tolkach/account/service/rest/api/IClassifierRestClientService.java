package by.tolkach.account.service.rest.api;

import by.tolkach.account.dto.Currency;
import by.tolkach.account.dto.OperationCategory;

import java.util.UUID;

public interface IClassifierRestClientService {
    Currency readCurrency(UUID currencyId);
    OperationCategory readOperationCategory(UUID operationCategoryId);

}
