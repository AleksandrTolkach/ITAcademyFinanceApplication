package by.tolkach.bot.service.rest.api;

import by.tolkach.bot.dto.Currency;
import by.tolkach.bot.dto.OperationCategory;

import java.util.List;

public interface IClassifierRestClientService {
    List<OperationCategory> readCategory();
    OperationCategory readCategory(String title);
    List<Currency> readCurrency();
    Currency readCurrency(String title);
}
