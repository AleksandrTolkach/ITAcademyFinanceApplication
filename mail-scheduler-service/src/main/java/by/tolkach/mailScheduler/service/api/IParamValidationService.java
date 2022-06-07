package by.tolkach.mailScheduler.service.api;

import by.tolkach.mailScheduler.dto.scheduledMail.Param;
import by.tolkach.mailScheduler.dto.scheduledMail.ReportType;

public interface IParamValidationService extends IValidationService<Param> {
    Param validate(Param param, ReportType reportType);
}
