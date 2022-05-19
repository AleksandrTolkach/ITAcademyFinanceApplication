package by.tolkach.report.dao.api;

import by.tolkach.report.dao.api.entity.ReportEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IBalanceReportStorage extends PagingAndSortingRepository<ReportEntity, UUID> {
}
