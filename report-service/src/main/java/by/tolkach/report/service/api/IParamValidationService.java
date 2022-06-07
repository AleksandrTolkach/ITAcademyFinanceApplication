package by.tolkach.report.service.api;

import by.tolkach.report.dto.report.Param;
import by.tolkach.report.dto.report.ReportType;

public interface IParamValidationService extends IValidationService<Param> {
    Param validate(Param param, ReportType reportType);
}
