package by.tolkach.report.service.report.handler.api;

import by.tolkach.report.dto.report.param.Param;

import java.io.ByteArrayOutputStream;

public interface INewReportHandler {
    ByteArrayOutputStream handle(Param param);
}
