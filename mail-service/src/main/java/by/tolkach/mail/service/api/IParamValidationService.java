package by.tolkach.mail.service.api;

import by.tolkach.mail.dto.Param;
import by.tolkach.mail.dto.ReportType;

public interface IParamValidationService extends IValidationService<Param> {
    Param validate(Param param, ReportType reportType);
}
