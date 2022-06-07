package by.tolkach.report.service.helper.api;

import by.tolkach.report.dto.operation.Currency;

import java.util.UUID;

public interface ICurrencyService {
    Currency save(Currency currency);
    Currency read(UUID currencyId);
    Currency read(String title);
    Currency update(Currency currency);
}
