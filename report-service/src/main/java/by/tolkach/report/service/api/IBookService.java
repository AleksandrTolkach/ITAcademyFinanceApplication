package by.tolkach.report.service.api;

import by.tolkach.report.dto.Operation;

import java.util.List;

public interface IBookService {
    void createBook(List<Operation> operations);
}
