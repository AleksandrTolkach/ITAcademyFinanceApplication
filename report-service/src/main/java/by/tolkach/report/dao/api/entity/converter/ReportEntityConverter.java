package by.tolkach.report.dao.api.entity.converter;

import by.tolkach.report.dao.api.entity.ReportEntity;
import by.tolkach.report.dao.api.entity.reportParam.ExtendedParamEntity;
import by.tolkach.report.dto.Report;

public class ReportEntityConverter implements IEntityConverter<Report, ReportEntity> {
    @Override
    public ReportEntity toEntity(Report report) {
        return ReportEntity.Builder.createBuilder()
                .setUuid(report.getUuid())
                .setDtCreate(report.getDtCreate())
                .setDtUpdate(report.getDtUpdate())
                .setStatus(report.getStatus())
                .setType(report.getType())
                .setDescription(report.getDescription())
                .setParams(new ExtendedParamEntity())
                .build();
    }

    @Override
    public Report toDto(ReportEntity entity) {
        return null;
    }
}
