package by.tolkach.report.dao.api.entity.converter;

import by.tolkach.report.dao.api.entity.param.ParamEntity;
import by.tolkach.report.dao.api.entity.report.ReportEntity;
import by.tolkach.report.dto.report.Param;
import by.tolkach.report.dto.report.Report;
import org.springframework.stereotype.Component;

@Component
public class ReportEntityConverter implements IEntityConverter<Report, ReportEntity> {

    private final IEntityConverter<Param, ParamEntity> paramEntityConverter;

    public ReportEntityConverter(IEntityConverter<Param, ParamEntity> paramEntityConverter) {
        this.paramEntityConverter = paramEntityConverter;
    }

    @Override
    public ReportEntity toEntity(Report report) {
        return ReportEntity.Builder.createBuilder()
                .setUuid(report.getUuid())
                .setDtCreate(report.getDtCreate())
                .setDtUpdate(report.getDtUpdate())
                .setStatus(report.getStatus())
                .setType(report.getType())
                .setDescription(report.getDescription())
                .setParams(this.paramEntityConverter.toEntity(report.getParams()))
                .build();
    }

    @Override
    public Report toDto(ReportEntity entity) {
        return Report.Builder.createBuilder()
                .setUuid(entity.getUuid())
                .setDtCreate(entity.getDtCreate())
                .setDtUpdate(entity.getDtUpdate())
                .setStatus(entity.getStatus())
                .setType(entity.getType())
                .setDescription(entity.getDescription())
                .setParams(this.paramEntityConverter.toDto(entity.getParams()))
                .build();
    }
}
