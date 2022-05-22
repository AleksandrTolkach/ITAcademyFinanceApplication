package by.tolkach.report.service.report;

import by.tolkach.report.dao.api.IReportStorage;
import by.tolkach.report.dao.api.entity.ReportEntity;
import by.tolkach.report.dao.api.entity.converter.IEntityConverter;
import by.tolkach.report.dto.operation.Operation;
import by.tolkach.report.dto.report.Report;
import by.tolkach.report.dto.report.ReportStatus;
import by.tolkach.report.dto.report.ReportType;
import by.tolkach.report.dto.report.param.Param;
import by.tolkach.report.service.api.IBookService;
import by.tolkach.report.service.report.api.IParamService;
import by.tolkach.report.service.report.api.IReportFileService;
import by.tolkach.report.service.report.api.IReportService;
import by.tolkach.report.service.report.handler.api.IReportHandler;
import by.tolkach.report.service.report.handler.api.ReportHandlerFactory;
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

    public ReportService(ReportHandlerFactory reportHandlerFactory,
                         IReportStorage reportStorage,
                         IParamService paramService,
                         IBookService bookService,
                         IEntityConverter<Report, ReportEntity> reportEntityConverter,
                         IReportFileService reportFileService) {
        this.reportHandlerFactory = reportHandlerFactory;
        this.reportStorage = reportStorage;
        this.paramService = paramService;
        this.bookService = bookService;
        this.reportEntityConverter = reportEntityConverter;
        this.reportFileService = reportFileService;
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

    public Report createReportProperties(Param param, ReportType reportType) {
        LocalDateTime dtCreate = LocalDateTime.now();
        return Report.Builder.createBuilder()
                .setUuid(UUID.randomUUID())
                .setDtCreate(dtCreate)
                .setDtUpdate(dtCreate)
                .setDescription("Отчет")
                .setStatus(ReportStatus.LOADED)
                .setParams(param)
                .setType(reportType)
                .build();
    }
}
