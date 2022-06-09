package by.tolkach.mailScheduler.service;

import by.tolkach.mailScheduler.dto.exception.MultipleErrorsException;
import by.tolkach.mailScheduler.dto.exception.NotFoundException;
import by.tolkach.mailScheduler.dto.exception.SingleError;
import by.tolkach.mailScheduler.dto.scheduledMail.Param;
import by.tolkach.mailScheduler.dto.scheduledMail.ReportType;
import by.tolkach.mailScheduler.service.api.IParamValidationService;
import by.tolkach.mailScheduler.service.rest.api.IAccountRestClientService;
import by.tolkach.mailScheduler.service.rest.api.IClassifierRestClientService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.UUID;

@Service
public class ParamValidationService implements IParamValidationService {

    private MultipleErrorsException validationException;
    private final IAccountRestClientService accountRestClientService;
    private final IClassifierRestClientService classifierRestClientService;

    public ParamValidationService(IAccountRestClientService accountRestClientService,
                                  IClassifierRestClientService classifierRestClientService) {
        this.accountRestClientService = accountRestClientService;
        this.classifierRestClientService = classifierRestClientService;
    }

    @Override
    public Param validate(Param param, ReportType reportType) {
        param = this.validate(param);
        if (reportType == null) {
            throw new NotFoundException("Укажите тип отчета");
        }
        if (!reportType.equals(ReportType.BALANCE)) {
            List<UUID> categoriesId = param.getCategories();
            if (categoriesId == null || categoriesId.isEmpty()) {
                this.validationException.add(
                        new SingleError("categories", "Укажите минимум одну категорию."));
            } else if (categoriesId.contains(null)) {
                validationException.add(new SingleError("categories", "ID категории не может быть пустым."));
            } else {
                for (UUID categoryId: categoriesId) {
                    try {
                        this.classifierRestClientService.readOperationCategory(categoryId);
                    } catch (HttpClientErrorException e) {
                        validationException.add(new SingleError("categories", "Категории с Id "
                                + categoryId + " нет в базе."));
                    }
                }
            }
        }
        if (!this.validationException.getErrors().isEmpty()) {
            throw validationException;
        }
        return param;
    }

    @Override
    public Param validate(Param param) {
        this.validationException = new MultipleErrorsException();
        if (param == null) {
            throw new NotFoundException("Необходимо передать объект параметров.");
        }
        List<UUID> accountsId = param.getAccounts();
        if (accountsId == null || accountsId.isEmpty()) {
            validationException.add(
                    new SingleError("accountsId", "Необходимо передать хотя бы один ID счета."));
        } else if (accountsId.contains(null)) {
            validationException.add(new SingleError("accountsId", "ID счета не может быть пустым."));
        } else {
            for (UUID accountId: accountsId) {
                try {
                    this.accountRestClientService.readAccount(accountId);
                } catch (HttpClientErrorException e) {
                    validationException.add(new SingleError("accounts", "Счета с Id " +
                            accountId + " нет в базе."));
                }
            }
        }
        return param;
    }
}
