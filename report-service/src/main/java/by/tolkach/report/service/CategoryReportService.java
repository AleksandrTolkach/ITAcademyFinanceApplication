package by.tolkach.report.service;

import by.tolkach.report.dto.Page;
import by.tolkach.report.dto.Report;
import by.tolkach.report.dto.SimplePageable;
import by.tolkach.report.dto.reportParam.ExtendedParam;
import by.tolkach.report.service.api.IReportService;
import org.springframework.stereotype.Service;

@Service
public class CategoryReportService implements IReportService {
    @Override
    public void create(ExtendedParam param) {

    }

    @Override
    public Page<Report> read(SimplePageable pageable) {
        return null;
    }
}
