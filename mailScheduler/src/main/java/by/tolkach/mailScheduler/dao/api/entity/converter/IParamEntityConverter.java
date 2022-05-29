package by.tolkach.mailScheduler.dao.api.entity.converter;

import by.tolkach.mailScheduler.dao.api.entity.ParamEntity;
import by.tolkach.mailScheduler.dto.scheduledMail.Param;
import by.tolkach.mailScheduler.dto.scheduledMail.ReportType;

public interface IParamEntityConverter extends IEntityConverter<Param, ParamEntity> {
    IParamEntityConverter setType(ReportType reportType);
}
