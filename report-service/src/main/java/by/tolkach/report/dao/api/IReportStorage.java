package by.tolkach.report.dao.api;

import by.tolkach.report.dao.api.entity.report.ReportEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IReportStorage extends PagingAndSortingRepository<ReportEntity, UUID> {
    List<ReportEntity> findAllBy(Pageable pageable);
}
