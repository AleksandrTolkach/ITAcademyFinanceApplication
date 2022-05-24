package by.tolkach.report.service;

import by.tolkach.report.dao.api.IReportStorage;
import by.tolkach.report.dao.api.entity.report.ReportEntity;
import by.tolkach.report.dao.api.entity.converter.IEntityConverter;
import by.tolkach.report.dto.Page;
import by.tolkach.report.dto.SimplePageable;
import by.tolkach.report.dto.operation.Operation;
import by.tolkach.report.dto.report.Report;
import by.tolkach.report.dto.report.ReportStatus;
import by.tolkach.report.dto.report.ReportType;
import by.tolkach.report.dto.report.Param;
import by.tolkach.report.service.api.*;
import by.tolkach.report.service.handler.api.IReportHandler;
import by.tolkach.report.service.handler.api.ReportHandlerFactory;
import by.tolkach.report.service.helper.api.IOperationCategoryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ReportService implements IReportService {

    private final ReportHandlerFactory reportHandlerFactory;
    private final IReportStorage reportStorage;
    private final IParamService paramService;
    private final IBookService bookService;
    private final IEntityConverter<Report, ReportEntity> reportEntityConverter;
    private final IReportFileService reportFileService;
    private final IOperationCategoryService operationCategoryService;

    public ReportService(ReportHandlerFactory reportHandlerFactory,
                         IReportStorage reportStorage,
                         IParamService paramService,
                         IBookService bookService,
                         IEntityConverter<Report, ReportEntity> reportEntityConverter,
                         IReportFileService reportFileService,
                         IOperationCategoryService operationCategoryService) {
        this.reportHandlerFactory = reportHandlerFactory;
        this.reportStorage = reportStorage;
        this.paramService = paramService;
        this.bookService = bookService;
        this.reportEntityConverter = reportEntityConverter;
        this.reportFileService = reportFileService;
        this.operationCategoryService = operationCategoryService;
    }

    @Override
    public ByteArrayOutputStream create(Param param, ReportType reportType) {
        param = this.paramService.create(param);
        Report report = this.createReportProperties(param, reportType);
        ReportEntity reportEntity = this.reportEntityConverter.toEntity(report);
        this.reportStorage.save(reportEntity);
        IReportHandler reportHandler = this.reportHandlerFactory.getReportService(reportType);
        List<Operation> operations = reportHandler.getOperations(param);
        ByteArrayOutputStream book = this.bookService.createBook(operations, reportType);
        this.reportFileService.save(book, report);
        return book;
    }

    @Override
    public ByteArrayOutputStream read(UUID reportId) {
        ReportEntity reportEntity = this.reportStorage.findById(reportId).orElse(null);
        return this.reportFileService.read(this.reportEntityConverter.toDto(reportEntity));
    }

    @Override
    public Page<Report> read(SimplePageable simplePageable) {
        List<ReportEntity> reportEntities =
                this.reportStorage.findAllBy(PageRequest.of(simplePageable.getPage(), simplePageable.getSize()));
        return Pagination.pageOf(Report.class, ReportEntity.class).properties(reportEntities, simplePageable,
                reportEntities.size(), this.reportEntityConverter);
    }

    public Report createReportProperties(Param param, ReportType reportType) {
        LocalDateTime dtCreate = LocalDateTime.now();
        StringBuilder sb = new StringBuilder();
        switch (reportType) {
            case BALANCE:
                sb.append("Отчет по счетам:").append(" ");
                for (UUID accountId: param.getAccounts()) {
                    sb.append(accountId).append("; ");
                }
                break;
            case BY_DATE:
                sb.append("Отчет за период ").append(param.getFrom()).append(" - ").append(param.getTo());
                break;
            case BY_CATEGORY:
                sb.append("Отчет по категориям: ");
                for (UUID categoryId: param.getCategories()) {
                    sb.append(this.operationCategoryService.read(categoryId).getTitle()).append("; ");
                }
                break;
        }

        return Report.Builder.createBuilder()
                .setUuid(UUID.randomUUID())
                .setDtCreate(dtCreate)
                .setDtUpdate(dtCreate)
                .setDescription(sb.toString())
                .setStatus(ReportStatus.LOADED)
                .setParams(param)
                .setType(reportType)
                .build();
    }
}
