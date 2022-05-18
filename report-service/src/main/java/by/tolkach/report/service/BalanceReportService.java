package by.tolkach.report.service;

import by.tolkach.report.dao.api.IBalanceReportStorage;
import by.tolkach.report.dao.api.entity.converter.IEntityConverter;
import by.tolkach.report.dao.api.entity.reportParam.ExtendedParamEntity;
import by.tolkach.report.dto.*;
import by.tolkach.report.dto.reportParam.ExtendedParam;
import by.tolkach.report.service.api.IBookService;
import by.tolkach.report.service.api.IOperationService;
import by.tolkach.report.service.api.IReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceReportService implements IReportService {

    private final IBalanceReportStorage balanceReportStorage;
    private final IEntityConverter<ExtendedParam, ExtendedParamEntity> entityConverter;
    private final IOperationService operationService;
    private final IBookService bookService;

    public BalanceReportService(IBalanceReportStorage balanceReportStorage,
                                IEntityConverter<ExtendedParam, ExtendedParamEntity> entityConverter,
                                IOperationService operationService,
                                IBookService bookService) {
        this.balanceReportStorage = balanceReportStorage;
        this.entityConverter = entityConverter;
        this.operationService = operationService;
        this.bookService = bookService;
    }

    @Override
    public void create(ExtendedParam param) {
        List<Operation> operations = this.operationService.readByBalance(param);
        this.bookService.createBook(operations);
    }

    @Override
    public Page<Report> read(SimplePageable pageable) {
        return null;
    }
}
