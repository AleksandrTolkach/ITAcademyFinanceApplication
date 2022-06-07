package by.tolkach.report.service.api;

import by.tolkach.report.dto.operation.Operation;
import by.tolkach.report.dto.report.ReportType;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface IBookService {
    ByteArrayOutputStream createBook(List<Operation> operations, ReportType reportType);
}
