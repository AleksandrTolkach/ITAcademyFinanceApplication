package by.tolkach.classifier.service.api;

import by.tolkach.classifier.dto.Currency;
import by.tolkach.classifier.dto.Page;
import by.tolkach.classifier.dto.SimplePageable;

public interface ICurrencyService {
    Currency create(Currency currency);
    Page<Currency> read(SimplePageable pageable);
}
