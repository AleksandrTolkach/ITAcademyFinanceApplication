package by.tolkach.mailScheduler.service;

import by.tolkach.mailScheduler.dto.exception.MultipleErrorsException;
import by.tolkach.mailScheduler.dto.exception.NotFoundException;
import by.tolkach.mailScheduler.dto.exception.SingleError;
import by.tolkach.mailScheduler.dto.scheduledMail.Param;
import by.tolkach.mailScheduler.dto.scheduledMail.ReportType;
import by.tolkach.mailScheduler.service.api.IParamValidationService;
import org.springframework.stereotype.Service;

@Service
public class ParamValidationService implements IParamValidationService {

    private MultipleErrorsException validationException;

    @Override
    public Param validate(Param param, ReportType reportType) {
        param = this.validate(param);
        if (reportType == null) {
            throw new NotFoundException("Укажите тип отчета");
        }
        if (!reportType.equals(ReportType.BALANCE)) {
            if (param.getCategories() == null || param.getCategories().isEmpty()) {
                this.validationException.add(
                        new SingleError("categories", "Укажите минимум одну категорию."));
            } else if (param.getCategories().contains(null)) {
                validationException.add(new SingleError("categories", "ID категории не может быть пустым."));
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
        return param;
    }
}
