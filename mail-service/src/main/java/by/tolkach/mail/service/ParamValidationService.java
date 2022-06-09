package by.tolkach.mail.service;

import by.tolkach.mail.dto.Param;
import by.tolkach.mail.dto.ReportType;
import by.tolkach.mail.dto.exception.MultipleErrorsException;
import by.tolkach.mail.dto.exception.NotFoundException;
import by.tolkach.mail.dto.exception.SingleError;
import by.tolkach.mail.service.api.IParamValidationService;
import by.tolkach.mail.service.rest.api.IAccountRestClientService;
import by.tolkach.mail.service.rest.api.IClassifierRestClientService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.UUID;

@Service
public class ParamValidationService implements IParamValidationService {

    private MultipleErrorsException validationException;
    private final IClassifierRestClientService classifierRestClientService;
    private final IAccountRestClientService accountRestClientService;

    public ParamValidationService(IClassifierRestClientService classifierRestClientService,
                                  IAccountRestClientService accountRestClientService) {
        this.classifierRestClientService = classifierRestClientService;
        this.accountRestClientService = accountRestClientService;
    }

    @Override
    public Param validate(Param param, ReportType reportType) {
        param = this.validate(param);
        if (reportType == null) {
            throw new NotFoundException("Укажите тип отчета");
        }
        if (!reportType.equals(ReportType.BALANCE)) {
            if (param.getFrom() == null) {
                this.validationException.add(new SingleError("from", "Укажите дату начала."));
            }
            if (param.getTo() == null) {
                this.validationException.add(new SingleError("to", "Укажите дату окончания."));
            }
            if (param.getCategories() == null || param.getCategories().isEmpty()) {
                this.validationException.add(
                        new SingleError("categories", "Укажите минимум одну категорию."));
            } else if (param.getCategories().contains(null)) {
                validationException.add(new SingleError("categories", "ID категории не может быть пустым."));
            }
            List<UUID> operationCategoriesId = param.getCategories();
            for (UUID operationCategoryId: operationCategoriesId) {
                try {
                    this.classifierRestClientService.readOperationCategory(operationCategoryId);
                } catch (HttpClientErrorException e) {
                    validationException.add(new SingleError("categories", "Категориис Id " +
                            operationCategoryId + " нет в базе."));
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
        if (param.getAccounts() == null || param.getAccounts().isEmpty()) {
            validationException.add(
                    new SingleError("accounts", "Необходимо передать хотя бы один ID счета."));
        } else if (param.getAccounts().contains(null)) {
            validationException.add(new SingleError("accounts", "ID счета не может быть пустым."));
        }
        List<UUID> accountsId = param.getAccounts();
        for (UUID accountId: accountsId) {
            try {
                this.accountRestClientService.readAccount(accountId);
            } catch (HttpClientErrorException e) {
                validationException.add(new SingleError("accounts", "Счета с Id " +
                        accountId + " нет в базе."));
            }
        }
        return param;
    }
}
