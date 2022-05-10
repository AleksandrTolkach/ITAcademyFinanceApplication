package by.tolkach.schedulerAccount.service.rest.api;

import by.tolkach.schedulerAccount.dto.Currency;

import java.util.UUID;

public interface ICurrencyRestClientService {
    Currency read(UUID currencyId);
}
