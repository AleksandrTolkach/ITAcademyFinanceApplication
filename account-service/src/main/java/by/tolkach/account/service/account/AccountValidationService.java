package by.tolkach.account.service.account;

import by.tolkach.account.dto.account.Account;
import by.tolkach.account.dto.exception.MultipleErrorsException;
import by.tolkach.account.dto.exception.NotFoundException;
import by.tolkach.account.dto.exception.SingleError;
import by.tolkach.account.service.api.IValidationService;
import by.tolkach.account.service.rest.api.IClassifierRestClientService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class AccountValidationService implements IValidationService<Account> {

    private final IClassifierRestClientService classifierRestClientService;

    public AccountValidationService(IClassifierRestClientService classifierRestClientService) {
        this.classifierRestClientService = classifierRestClientService;
    }

    @Override
    public Account validate(Account account) {
        if (account == null) {
            throw new NotFoundException("Необходимо передать объект счета");
        }
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

        try {
            this.classifierRestClientService.readCurrency(account.getCurrency());
        } catch (HttpClientErrorException e) {
            validationException.add(new SingleError("currency", "Указанной валюты нет в базе"));
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
        return value == null || value.name().equals("UNKNOWN");
    }
}
