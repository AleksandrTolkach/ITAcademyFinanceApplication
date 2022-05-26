package by.tolkach.report.service;

import by.tolkach.report.dto.report.Param;
import by.tolkach.report.dto.report.ReportType;
import by.tolkach.report.service.api.IParamValidationService;
import by.tolkach.report.service.api.exception.MultipleErrorsException;
import by.tolkach.report.service.api.exception.NotFoundError;
import by.tolkach.report.service.api.exception.SingleError;
import org.springframework.stereotype.Service;

@Service
public class ParamValidationService implements IParamValidationService {

    private MultipleErrorsException validationException;

    @Override
    public Param validate(Param param, ReportType reportType) {
        param = this.validate(param);
        if (reportType == null) {
            throw new NotFoundError("Укажите тип отчета");
        }
        if (!reportType.equals(ReportType.BALANCE)) {
            if (param.getFrom() == null) {
                this.validationException.add(new SingleError("from", "Укажите дату начала."));
            }
            if (param.getTo() == null) {
                this.validationException.add(new SingleError("to", "Укажите дату окончания."));
            }
            if (param.getCategories() == null || param.getCategories().size() == 0) {
                this.validationException.add(
                        new SingleError("categories", "Укажите минимум одну категорию."));
            }
            if (param.getCategories().contains(null)) {
                validationException.add(new SingleError("categories", "ID категории не может быть пустым."));
            }
        }
        if (this.validationException.getErrors().size() > 0) {
            throw validationException;
        }
        return param;
    }

    @Override
    public Param validate(Param param) {
        this.validationException = new MultipleErrorsException();
        if (param == null) {
            throw new NotFoundError("Необходимо передать объект параметров.");
        }
        if (param.getAccounts() == null || param.getAccounts().size() == 0) {
            validationException.add(
                    new SingleError("accounts", "Необходимо передать хотя бы один ID счета."));
        }
        if (param.getAccounts().contains(null)) {
            validationException.add(new SingleError("accounts", "ID счета не может быть пустым."));
        }
        return param;
    }
}