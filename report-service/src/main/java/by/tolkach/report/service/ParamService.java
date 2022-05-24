package by.tolkach.report.service;

import by.tolkach.report.dao.api.IParamStorage;
import by.tolkach.report.dao.api.entity.converter.IParamEntityConverter;
import by.tolkach.report.dao.api.entity.param.ParamEntity;
import by.tolkach.report.dto.report.Param;
import by.tolkach.report.dto.report.ReportType;
import by.tolkach.report.service.api.IParamService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ParamService implements IParamService {

    private final IParamEntityConverter entityConverter;
    private final IParamStorage paramStorage;

    public ParamService(IParamEntityConverter entityConverter,
                        IParamStorage paramStorage) {
        this.entityConverter = entityConverter;
        this.paramStorage = paramStorage;
    }

    @Override
    public Param create(Param param, ReportType reportType) {
        ParamEntity paramEntity = this.entityConverter.setType(reportType).toEntity(param);
        paramEntity.setUuid(UUID.randomUUID());
        paramEntity = this.paramStorage.save(paramEntity);
        return this.entityConverter.setType(reportType).toDto(paramEntity);
    }
}
