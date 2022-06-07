package by.tolkach.classifier.service.classifier;

import by.tolkach.classifier.dto.Currency;
import by.tolkach.classifier.dto.exception.MultipleErrorsException;
import by.tolkach.classifier.dto.exception.NotFoundException;
import by.tolkach.classifier.dto.exception.SingleError;
import by.tolkach.classifier.service.classifier.api.IValidationService;
import org.springframework.stereotype.Service;

@Service
public class CurrencyValidationService implements IValidationService<Currency> {
    @Override
    public Currency validate(Currency currency) {
        MultipleErrorsException validationException = new MultipleErrorsException();
        if (currency == null) {
            throw new NotFoundException("Необходимо передать объект валюты.");
        }
        if (this.nullOrEmpty(currency.getTitle())) {
            validationException.add(new SingleError("title", "Необходимо указать название валюты."));
        }

        if (this.nullOrEmpty(currency.getDescription())) {
            validationException.add(new SingleError("description", "Необходимо добавить описание."));
        }

        if (validationException.getErrorCount() > 0) {
            throw validationException;
        }
        return currency;
    }

    private boolean nullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
