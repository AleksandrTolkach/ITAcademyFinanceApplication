package by.tolkach.report.service;

import by.tolkach.report.dao.api.IReportStorage;
import by.tolkach.report.dao.api.entity.converter.IEntityConverter;
import by.tolkach.report.dao.api.entity.report.ReportEntity;
import by.tolkach.report.dto.Page;
import by.tolkach.report.dto.SimplePageable;
import by.tolkach.report.dto.exception.NotFoundException;
import by.tolkach.report.dto.operation.Operation;
import by.tolkach.report.dto.report.Param;
import by.tolkach.report.dto.report.Report;
import by.tolkach.report.dto.report.ReportStatus;
import by.tolkach.report.dto.report.ReportType;
import by.tolkach.report.service.api.*;
import by.tolkach.report.service.handler.api.IReportHandler;
import by.tolkach.report.service.handler.api.ReportHandlerFactory;
import by.tolkach.report.service.helper.api.IOperationCategoryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ReportService implements IReportService {

    private final ReportHandlerFactory reportHandlerFactory;
    private final IReportStorage reportStorage;
    private final IParamService paramService;
    private final IBookService bookService;
    private final IEntityConverter<Report, ReportEntity> reportEntityConverter;
    private final IReportFileService reportFileService;
    private final IOperationCategoryService operationCategoryService;
    private final IParamValidationService paramValidationService;

    public ReportService(ReportHandlerFactory reportHandlerFactory,
                         IReportStorage reportStorage,
                         IParamService paramService,
                         IBookService bookService,
                         IEntityConverter<Report, ReportEntity> reportEntityConverter,
                         IReportFileService reportFileService,
                         IOperationCategoryService operationCategoryService,
                         IParamValidationService paramValidationService) {
        this.reportHandlerFactory = reportHandlerFactory;
        this.reportStorage = reportStorage;
        this.paramService = paramService;
        this.bookService = bookService;
        this.reportEntityConverter = reportEntityConverter;
        this.reportFileService = reportFileService;
        this.operationCategoryService = operationCategoryService;
        this.paramValidationService = paramValidationService;
    }

    @Override
    public ByteArrayOutputStream create(Param param, ReportType reportType) {
        this.paramValidationService.validate(param, reportType);
        param = this.paramService.create(param, reportType);
        Report report = Reports.createProperties(param, reportType, this.operationCategoryService);
        ReportEntity reportEntity = this.reportEntityConverter.toEntity(report);
        reportEntity = this.reportStorage.save(reportEntity);
        IReportHandler reportHandler = this.reportHandlerFactory.getReportService(reportType);
        reportEntity = this.reportStorage.findById(reportEntity.getUuid()).orElse(null);
        if (reportEntity == null) {
            throw new NotFoundException("Отчета с таким Id не существует.");
        }
        reportEntity.setStatus(ReportStatus.PROGRESS);
        this.reportStorage.save(reportEntity);
        List<Operation> operations = reportHandler.getOperations(param);
        ByteArrayOutputStream book = this.bookService.createBook(operations, reportType);
        this.reportFileService.save(book, report);
        reportEntity = this.reportStorage.findById(reportEntity.getUuid()).orElse(null);
        if (reportEntity == null) {
            throw new NotFoundException("Отчета с таким Id не существует");
        }
        reportEntity.setStatus(ReportStatus.DONE);
        this.reportStorage.save(reportEntity);
        return book;
    }

    @Override
    public ByteArrayOutputStream read(UUID reportId) {
        if (reportId == null) {
            throw new NotFoundException("Укажите ID отчета.");
        }
        ReportEntity reportEntity = this.reportStorage.findById(reportId).orElse(null);
        if (reportEntity == null) {
            throw new NotFoundException("Отчета с таким ID не существует.");
        }
        if (!reportEntity.getStatus().equals(ReportStatus.DONE)) {
            throw new NotFoundException("Отчет ещё не сформирован.");
        }
        return this.reportFileService.read(this.reportEntityConverter.toDto(reportEntity));
    }

    @Override
    public Page<Report> read(SimplePageable simplePageable) {
        List<ReportEntity> reportEntities =
                this.reportStorage.findAllBy(PageRequest.of(simplePageable.getPage(), simplePageable.getSize()));
        return Pagination.pageOf(Report.class, ReportEntity.class).properties(reportEntities, simplePageable,
                this.reportStorage.count(), this.reportEntityConverter);
    }
}
