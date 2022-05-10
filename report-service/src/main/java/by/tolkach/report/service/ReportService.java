package by.tolkach.report.service;

import by.tolkach.report.controller.converter.ParamChoicer;
import by.tolkach.report.dao.api.IReportStorage;
import by.tolkach.report.dao.api.entity.converter.IEntityConverter;
import by.tolkach.report.dao.api.entity.reportParam.ExtendedParamEntity;
import by.tolkach.report.dto.Page;
import by.tolkach.report.dto.Report;
import by.tolkach.report.dto.ReportType;
import by.tolkach.report.dto.SimplePageable;
import by.tolkach.report.dto.reportParam.ExtendedParam;
import by.tolkach.report.dto.reportParam.ReportParamByBalance;
import by.tolkach.report.service.api.IReportService;
import org.springframework.stereotype.Service;

@Service
public class ReportService implements IReportService {

    private final IReportStorage reportStorage;
    private final IEntityConverter<ExtendedParam, ExtendedParamEntity> entityConverter;

    public ReportService(IReportStorage reportStorage,
                         IEntityConverter<ExtendedParam, ExtendedParamEntity> entityConverter) {
        this.reportStorage = reportStorage;
        this.entityConverter = entityConverter;
    }

    @Override
    public void create(ExtendedParam param, ReportType type) {
        ParamChoicer paramChoicer = new ParamChoicer();
        switch (type) {
            case BALANCE:
                ReportParamByBalance byBalance = paramChoicer.createByBalance(param);
                break;
            case BY_DATE:
                break;
            case BY_CATEGORY:
                break;
        }
    }

    @Override
    public Page<Report> read(SimplePageable pageable) {
        return null;
    }
}
