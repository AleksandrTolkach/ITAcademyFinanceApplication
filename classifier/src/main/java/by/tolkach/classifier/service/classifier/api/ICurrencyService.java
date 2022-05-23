package by.tolkach.classifier.service.classifier.api;

import by.tolkach.classifier.dto.Currency;
import by.tolkach.classifier.dto.Page;
import by.tolkach.classifier.dto.SimplePageable;

import java.util.UUID;

public interface ICurrencyService {
    Currency create(Currency currency);
    Page<Currency> read(SimplePageable pageable);
    Currency read(UUID currencyId);
}
