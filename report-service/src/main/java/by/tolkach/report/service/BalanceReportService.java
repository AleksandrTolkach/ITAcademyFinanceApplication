package by.tolkach.report.service;

import by.tolkach.report.dao.api.IBalanceReportStorage;
import by.tolkach.report.dto.*;
import by.tolkach.report.dto.reportParam.ExtendedParam;
import by.tolkach.report.service.api.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BalanceReportService implements IReportService {

    private IBalanceReportStorage balanceReportStorage;
//    private IEntityConverter<ExtendedParam, ExtendedParamEntity> entityConverter;


    public BalanceReportService() {
    }

    @Autowired
    public void setBalanceReportStorage(IBalanceReportStorage balanceReportStorage) {
        this.balanceReportStorage = balanceReportStorage;
    }

//    @Autowired
//    public void setEntityConverter(IEntityConverter<ExtendedParam, ExtendedParamEntity> entityConverter) {
//        this.entityConverter = entityConverter;
//    }


    @Override
    public void create(ExtendedParam param, ReportType type) {
        List<Operation> operations = new ArrayList<>();

        operations.size();
    }

    @Override
    public Page<Report> read(SimplePageable pageable) {
        return null;
    }
}
