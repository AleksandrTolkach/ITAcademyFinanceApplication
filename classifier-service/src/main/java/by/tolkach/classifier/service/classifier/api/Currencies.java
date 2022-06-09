package by.tolkach.classifier.service.classifier.api;

import by.tolkach.classifier.dao.api.entity.CurrencyEntity;
import by.tolkach.classifier.dto.Currency;
import by.tolkach.classifier.dto.exception.NotFoundException;

import java.time.LocalDateTime;

public class Currencies {

    private Currencies() {
    }

    public static Currency createParameters(Currency currency) {
        LocalDateTime createdTime = LocalDateTime.now();
        currency.setDtCreate(createdTime);
        currency.setDtUpdate(createdTime);
        return currency;
    }

    public static boolean isExistingCurrency(CurrencyEntity currencyEntity) {
        if (currencyEntity == null) {
            throw new NotFoundException("Валюты с таким ID не существует.");
        }
        return true;
    }
}
