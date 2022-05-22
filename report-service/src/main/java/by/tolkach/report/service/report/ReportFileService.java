package by.tolkach.report.service.report;
import by.tolkach.report.dao.api.IReportFileStorage;
import by.tolkach.report.dao.api.entity.ReportEntity;
import by.tolkach.report.dao.api.entity.ReportFileEntity;
import by.tolkach.report.dao.api.entity.converter.IEntityConverter;
import by.tolkach.report.dto.report.Report;
import by.tolkach.report.service.report.api.IReportFileService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

@Service
public class ReportFileService implements IReportFileService {

    private final IReportFileStorage reportFileStorage;
    private final IEntityConverter<Report, ReportEntity> reportEntityConverter;

    public ReportFileService(IReportFileStorage reportFileStorage,
                             IEntityConverter<Report, ReportEntity> reportEntityConverter) {
        this.reportFileStorage = reportFileStorage;
        this.reportEntityConverter = reportEntityConverter;
    }

    @Override
    public ByteArrayOutputStream save(ByteArrayOutputStream bos, Report report) {
        ReportFileEntity reportFileEntity = ReportFileEntity.Builder.createBuilder()
                .setUuid(UUID.randomUUID())
                .setReport(this.reportEntityConverter.toEntity(report))
                .setFile(bos)
                .build();
        reportFileEntity = this.reportFileStorage.save(reportFileEntity);
        return reportFileEntity.getFile();
    }

    @Override
    public ByteArrayOutputStream read(Report report) {
        ReportEntity reportEntity = this.reportEntityConverter.toEntity(report);
        ReportFileEntity reportFileEntity = this.reportFileStorage.findByReport(reportEntity);
        return reportFileEntity.getFile();
    }
}
