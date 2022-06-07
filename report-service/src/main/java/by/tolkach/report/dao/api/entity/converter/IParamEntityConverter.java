package by.tolkach.report.dao.api.entity.converter;

import by.tolkach.report.dao.api.entity.param.ParamEntity;
import by.tolkach.report.dto.report.Param;
import by.tolkach.report.dto.report.ReportType;

public interface IParamEntityConverter extends IEntityConverter<Param, ParamEntity> {
    IParamEntityConverter setType(ReportType reportType);
}
