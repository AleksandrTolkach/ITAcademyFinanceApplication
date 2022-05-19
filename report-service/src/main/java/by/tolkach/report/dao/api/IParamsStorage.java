package by.tolkach.report.dao.api;

import by.tolkach.report.dao.api.entity.reportParam.ParamEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface IParamsStorage extends PagingAndSortingRepository<ParamEntity, UUID> {
}
