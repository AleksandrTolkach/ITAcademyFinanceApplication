package by.tolkach.mailScheduler.service.scheduledMail;

import by.tolkach.mailScheduler.dao.api.IParamStorage;
import by.tolkach.mailScheduler.dao.api.entity.ParamEntity;
import by.tolkach.mailScheduler.dao.api.entity.converter.IParamEntityConverter;
import by.tolkach.mailScheduler.dto.scheduledMail.Param;
import by.tolkach.mailScheduler.dto.scheduledMail.ReportType;
import by.tolkach.mailScheduler.service.scheduledMail.api.IParamService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ParamService implements IParamService {

    private final IParamStorage paramStorage;
    private final IParamEntityConverter paramEntityConverter;

    public ParamService(IParamStorage paramStorage, IParamEntityConverter paramEntityConverter) {
        this.paramStorage = paramStorage;
        this.paramEntityConverter = paramEntityConverter;
    }

    @Override
    public Param create(Param param, ReportType reportType) {
        ParamEntity paramEntity = this.paramEntityConverter.setType(reportType).toEntity(param);
        paramEntity.setUuid(UUID.randomUUID());
        paramEntity = this.paramStorage.save(paramEntity);
        return this.paramEntityConverter.toDto(paramEntity);
    }

    @Override
    public Param read(UUID paramId) {
        ParamEntity paramEntity = this.paramStorage.findById(paramId).orElse(null);
        return this.paramEntityConverter.toDto(paramEntity);
    }

    @Override
    public Param update(UUID paramId, Param param) {
        ParamEntity paramEntity = this.paramStorage.findById(paramId).orElse(null);
        Param currentParam = this.paramEntityConverter.toDto(paramEntity);
        this.updateParam(currentParam, param);
        paramEntity = this.paramEntityConverter.toEntity(currentParam);
        paramEntity = this.paramStorage.save(paramEntity);
        return this.paramEntityConverter.toDto(paramEntity);
    }

    private Param updateParam(Param currentParam, Param newParam) {
        currentParam.setFrom(newParam.getFrom());
        currentParam.setTo(newParam.getTo());
        currentParam.setAccounts(newParam.getAccounts());
        currentParam.setCategories(newParam.getCategories());
        return currentParam;
    }
}
