package by.tolkach.report.service;

import by.tolkach.report.dto.exception.MultipleErrorsException;
import by.tolkach.report.dto.exception.NotFoundException;
import by.tolkach.report.dto.exception.SingleError;
import by.tolkach.report.dto.report.Param;
import by.tolkach.report.dto.report.ReportType;
import by.tolkach.report.service.api.IParamValidationService;
import by.tolkach.report.service.helper.api.IAccountService;
import by.tolkach.report.service.helper.api.IOperationCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParamValidationService implements IParamValidationService {

    private MultipleErrorsException validationException;
    private final IOperationCategoryService operationCategoryService;
    private final IAccountService accountService;

    public ParamValidationService(IOperationCategoryService operationCategoryService,
                                  IAccountService accountService) {
        this.operationCategoryService = operationCategoryService;
        this.accountService = accountService;
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
            List<UUID> categoriesId = param.getCategories();
            if (categoriesId == null || categoriesId.isEmpty()) {
                this.validationException.add(
                        new SingleError("categories", "Укажите минимум одну категорию."));
            } else if (categoriesId.contains(null)) {
                validationException.add(new SingleError("categories", "ID категории не может быть пустым."));
            } else {
                for (UUID categoryId: categoriesId) {
                    this.operationCategoryService.read(categoryId);
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
                this.accountService.read(accountId);
            }
        }

        return param;
    }
}
