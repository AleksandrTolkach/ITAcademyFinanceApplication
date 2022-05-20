package by.tolkach.report.service;

import by.tolkach.report.dao.api.IReportStorage;
import by.tolkach.report.dao.api.IParamsStorage;
import by.tolkach.report.dao.api.entity.ReportEntity;
import by.tolkach.report.dao.api.entity.converter.IEntityConverter;
import by.tolkach.report.dao.api.entity.reportParam.ParamEntity;
import by.tolkach.report.dto.*;
import by.tolkach.report.dto.operation.Operation;
import by.tolkach.report.dto.reportParam.Param;
import by.tolkach.report.service.api.IBookService;
import by.tolkach.report.service.api.IOperationService;
import by.tolkach.report.service.api.IReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BalanceReportService implements IReportService {

    private final IReportStorage balanceReportStorage;
    private final IParamsStorage paramsStorage;
    private final IEntityConverter<Param, ParamEntity> entityConverter;
    private final IOperationService operationService;
    private final IBookService bookService;

    public BalanceReportService(IReportStorage balanceReportStorage,
                                IParamsStorage paramsStorage,
                                IEntityConverter<Param, ParamEntity> entityConverter,
                                IOperationService operationService,
                                IBookService bookService) {
        this.balanceReportStorage = balanceReportStorage;
        this.paramsStorage = paramsStorage;
        this.entityConverter = entityConverter;
        this.operationService = operationService;
        this.bookService = bookService;
    }

    @Override
    public void create(Param param) {
        List<Operation> operations = this.operationService.read(param);
        this.bookService.createBook(operations, ReportType.BALANCE);
        ParamEntity paramEntity = this.entityConverter.toEntity(param);
        paramEntity.setUuid(UUID.randomUUID());
        paramEntity = this.paramsStorage.save(paramEntity);
        LocalDateTime dtCreate = LocalDateTime.now();
        ReportEntity reportEntity = ReportEntity.Builder.createBuilder()
                .setUuid(UUID.randomUUID())
                .setDtCreate(dtCreate)
                .setDtUpdate(dtCreate)
                .setDescription("Отчет")
                .setStatus(ReportStatus.LOADED)
                .setParams(paramEntity)
                .setType(ReportType.BALANCE)
                .build();
        this.balanceReportStorage.save(reportEntity);

    }

    @Override
    public Page<Report> read(SimplePageable pageable) {
        return null;
    }
}
