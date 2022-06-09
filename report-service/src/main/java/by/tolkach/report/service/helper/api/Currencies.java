package by.tolkach.report.service.helper.api;

import by.tolkach.report.dao.api.helper.entity.CurrencyEntity;
import by.tolkach.report.dto.operation.Currency;
import by.tolkach.report.dto.exception.NotFoundException;

public class Currencies {

    private Currencies() {
    }

    public static CurrencyEntity updateCurrencyParameters(Currency currency, CurrencyEntity currencyEntity) {
        currencyEntity.setDtCreate(currency.getDtCreate());
        currencyEntity.setDtUpdate(currency.getDtUpdate());
        currencyEntity.setTitle(currency.getTitle());
        currencyEntity.setDescription(currency.getDescription());
        return currencyEntity;
    }

    public static boolean isExistingCurrency(CurrencyEntity currencyEntity) {
        if (currencyEntity == null) {
            throw new NotFoundException("Валюты с таким ID не существует.");
        }
        return true;
    }
}
