package by.tolkach.report.dao.api;

import by.tolkach.report.dao.api.entity.param.ParamEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface IParamStorage extends PagingAndSortingRepository<ParamEntity, UUID> {
}
