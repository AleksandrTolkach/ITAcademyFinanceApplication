package by.tolkach.report.dao.api;

import by.tolkach.report.dao.api.entity.ReportEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface IDateReportStorage extends PagingAndSortingRepository<ReportEntity, UUID> {
}
