package by.tolkach.report.service.report.api;

import by.tolkach.report.dto.report.Report;

import java.io.ByteArrayOutputStream;

public interface IReportFileService {
    ByteArrayOutputStream save(ByteArrayOutputStream bos, Report report);
    ByteArrayOutputStream read(Report report);
}
