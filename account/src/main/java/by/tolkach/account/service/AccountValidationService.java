package by.tolkach.account.service;

import by.tolkach.account.dto.Account;
import by.tolkach.account.dto.AccountType;
import by.tolkach.account.service.api.IValidationService;
import by.tolkach.account.service.api.exception.ValidationError;
import by.tolkach.account.service.api.exception.ValidationException;
import org.springframework.stereotype.Service;

@Service
public class AccountValidationService implements IValidationService<Account> {

    @Override
    public Account validate(Account account) {
        ValidationException validationException = new ValidationException();
        if (nullOrEmpty(account.getTitle())) {
            validationException.add(new ValidationError("title", "Необходимо заполнить заголовок."));
        }
        if (nullOrEmpty(account.getDescription())) {
            validationException.add(new ValidationError("description", "Необходимо добавить описание."));
        }
        if (nullOrEmpty(account.getType())) {
            validationException.add(new ValidationError("type", "Неверный тип."));
        }
        if (nullOrEmpty(account.getCurrency())) {
            validationException.add(new ValidationError("currency", "Неверная валюта."));
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
