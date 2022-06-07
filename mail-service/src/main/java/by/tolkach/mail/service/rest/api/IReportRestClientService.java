package by.tolkach.mail.service.rest.api;

import by.tolkach.mail.dto.Param;
import by.tolkach.mail.dto.ReportType;

import java.util.UUID;

public interface IReportRestClientService {
    byte[] create(Param param, ReportType reportType);
    byte[] export(UUID reportId);
}
