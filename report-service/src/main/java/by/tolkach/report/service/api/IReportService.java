package by.tolkach.report.service.api;

import by.tolkach.report.dto.Page;
import by.tolkach.report.dto.Report;
import by.tolkach.report.dto.SimplePageable;
import by.tolkach.report.dto.reportParam.Param;

public interface IReportService {
    void create(Param param);
    Page<Report> read(SimplePageable pageable);
}
