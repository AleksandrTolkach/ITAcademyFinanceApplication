package by.tolkach.report.service.report.api;

import by.tolkach.report.dto.report.ReportType;
import by.tolkach.report.dto.report.param.Param;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public interface IReportService {
    ByteArrayOutputStream create(Param param, ReportType reportType);
    ByteArrayOutputStream read(UUID reportId);
}
