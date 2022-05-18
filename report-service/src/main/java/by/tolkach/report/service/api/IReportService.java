package by.tolkach.report.service.api;

import by.tolkach.report.dto.Page;
import by.tolkach.report.dto.Report;
import by.tolkach.report.dto.SimplePageable;
import by.tolkach.report.dto.reportParam.ExtendedParam;

public interface IReportService {
    void create(ExtendedParam param);
    Page<Report> read(SimplePageable pageable);
}
