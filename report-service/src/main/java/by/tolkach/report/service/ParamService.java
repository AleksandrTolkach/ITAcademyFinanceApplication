package by.tolkach.report.service;

import by.tolkach.report.dao.api.IParamStorage;
import by.tolkach.report.dao.api.entity.converter.IEntityConverter;
import by.tolkach.report.dao.api.entity.param.ParamEntity;
import by.tolkach.report.dto.report.Param;
import by.tolkach.report.service.api.IParamService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ParamService implements IParamService {

    private final IEntityConverter<Param, ParamEntity> entityConverter;
    private final IParamStorage paramStorage;

    public ParamService(IEntityConverter<Param, ParamEntity> entityConverter,
                        IParamStorage paramStorage) {
        this.entityConverter = entityConverter;
        this.paramStorage = paramStorage;
    }

    @Override
    public Param create(Param param) {
        ParamEntity paramEntity = this.entityConverter.toEntity(param);
        paramEntity.setUuid(UUID.randomUUID());
        paramEntity = this.paramStorage.save(paramEntity);
        return this.entityConverter.toDto(paramEntity);
    }
}
