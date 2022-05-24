package by.tolkach.report.service.api;

import by.tolkach.report.dto.Page;
import by.tolkach.report.dto.SimplePageable;
import by.tolkach.report.dto.report.Report;
import by.tolkach.report.dto.report.ReportType;
import by.tolkach.report.dto.report.Param;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public interface IReportService {
    ByteArrayOutputStream create(Param param, ReportType reportType);
    ByteArrayOutputStream read(UUID reportId);
    Page<Report> read(SimplePageable simplePageable);
}
