package by.tolkach.account.service;

import by.tolkach.account.dto.Account;
import by.tolkach.account.service.api.IValidationService;
import by.tolkach.account.service.api.exception.SingleError;
import by.tolkach.account.service.api.exception.MultipleErrorsException;
import org.springframework.stereotype.Service;

@Service
public class AccountValidationService implements IValidationService<Account> {

    @Override
    public Account validate(Account account) {
        MultipleErrorsException validationException = new MultipleErrorsException();
        if (nullOrEmpty(account.getTitle())) {
            validationException.add(new SingleError("title", "Необходимо заполнить заголовок."));
        }
        if (nullOrEmpty(account.getDescription())) {
            validationException.add(new SingleError("description", "Необходимо добавить описание."));
        }
        if (nullOrEmpty(account.getType())) {
            validationException.add(new SingleError("type", "Неверный тип."));
        }
        if (nullOrEmpty(account.getCurrency())) {
            validationException.add(new SingleError("currency", "Неверная валюта."));
        }

        if (validationException.getErrorCount() > 0) {
            throw validationException;
        }
        return account;
    }

    private boolean nullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    private boolean nullOrEmpty(Enum<?> value) {
        return value.name().equals("UNKNOWN");
    }
}
