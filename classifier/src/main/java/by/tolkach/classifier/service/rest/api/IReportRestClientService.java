package by.tolkach.classifier.service.rest.api;

import by.tolkach.classifier.dto.Currency;
import by.tolkach.classifier.dto.OperationCategory;

public interface IReportRestClientService {
    void sendCurrency(Currency currency);
    void updateCurrency(Currency currency);
    void sendOperationCategory(OperationCategory operationCategory);
    void updateOperationCategory(OperationCategory operationCategory);

}
