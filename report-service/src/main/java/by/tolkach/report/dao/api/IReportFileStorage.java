package by.tolkach.report.dao.api;

import by.tolkach.report.dao.api.entity.report.ReportEntity;
import by.tolkach.report.dao.api.entity.report.ReportFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IReportFileStorage extends JpaRepository<ReportFileEntity, UUID> {
    ReportFileEntity findByReport(ReportEntity report);
}
