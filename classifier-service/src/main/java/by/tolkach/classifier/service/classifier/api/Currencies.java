package by.tolkach.classifier.service.classifier.api;

import by.tolkach.classifier.dto.Currency;

import java.time.LocalDateTime;

public class Currencies {
    public static Currency createParameters(Currency currency) {
        LocalDateTime createdTime = LocalDateTime.now();
        currency.setDtCreate(createdTime);
        currency.setDtUpdate(createdTime);
        return currency;
    }
}
