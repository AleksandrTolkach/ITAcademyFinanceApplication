package by.tolkach.report.service.api;

import by.tolkach.report.dto.report.Param;
import by.tolkach.report.dto.report.ReportType;

public interface IParamService {
    Param create(Param param, ReportType reportType);
}
