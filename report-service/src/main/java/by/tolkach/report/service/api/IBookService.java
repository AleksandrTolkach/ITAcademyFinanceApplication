package by.tolkach.report.service.api;

import by.tolkach.report.dto.operation.Operation;
import by.tolkach.report.dto.ReportType;

import java.util.List;

public interface IBookService {
    void createBook(List<Operation> operations, ReportType reportType);
}
