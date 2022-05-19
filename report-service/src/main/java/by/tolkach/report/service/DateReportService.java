package by.tolkach.report.service;

import by.tolkach.report.dao.api.IDateReportStorage;
import by.tolkach.report.dao.api.IParamsStorage;
import by.tolkach.report.dao.api.IReportStorage;
import by.tolkach.report.dao.api.entity.ReportEntity;
import by.tolkach.report.dao.api.entity.converter.IEntityConverter;
import by.tolkach.report.dao.api.entity.reportParam.ParamEntity;
import by.tolkach.report.dto.*;
import by.tolkach.report.dto.reportParam.Param;
import by.tolkach.report.service.api.IOperationService;
import by.tolkach.report.service.api.IReportService;
import by.tolkach.report.service.api.OperationComparator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DateReportService implements IReportService {

    private final IOperationService operationService;
    private final BookByDateService bookByDateService;
    private final IReportStorage reportStorage;
    private final IParamsStorage paramsStorage;
    private final IEntityConverter<Report, ReportEntity> reportEntityConverter;
    private final IEntityConverter<Param, ParamEntity> paramEntityConverter;

    public DateReportService(IOperationService operationService,
                             BookByDateService bookByDateService,
                             IReportStorage reportStorage,
                             IParamsStorage paramsStorage,
                             IEntityConverter<Report, ReportEntity> reportEntityConverter,
                             IEntityConverter<Param, ParamEntity> paramEntityConverter) {
        this.operationService = operationService;
        this.bookByDateService = bookByDateService;
        this.reportStorage = reportStorage;
        this.paramsStorage = paramsStorage;
        this.reportEntityConverter = reportEntityConverter;
        this.paramEntityConverter = paramEntityConverter;
    }

    @Override
    public void create(Param param) {
        List<Operation> operations = this.operationService.read(param);
        List<Operation> filteredOperations = new ArrayList<>();
        for (Operation operation: operations) {
            if (isInInterval(param, operation)) {
                filteredOperations.add(operation);
            }
        }
        filteredOperations.sort(new OperationComparator());
        this.bookByDateService.createBook(filteredOperations);
        ParamEntity paramEntity = this.paramEntityConverter.toEntity(param);
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
                .setType(ReportType.BY_DATE)
                .build();
        this.reportStorage.save(reportEntity);
    }

    @Override
    public Page<Report> read(SimplePageable pageable) {
        return null;
    }

    public boolean isInInterval(Param param, Operation operation) {
        return operation.getDate().compareTo(param.getFrom()) > 0 && operation.getDate().compareTo(param.getTo()) < 0;
    }
}
