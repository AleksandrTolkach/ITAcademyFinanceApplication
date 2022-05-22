package by.tolkach.report.service.report.handler.api;

import by.tolkach.report.dto.operation.Operation;
import by.tolkach.report.dto.report.param.Param;

import java.util.List;

public interface IReportHandler {
    List<Operation> getOperations(Param param);
}
