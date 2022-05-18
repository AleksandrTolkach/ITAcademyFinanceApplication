package by.tolkach.report.service.api;

import by.tolkach.report.dto.ReportType;
import by.tolkach.report.service.BalanceReportService;
import by.tolkach.report.service.CategoryReportService;
import by.tolkach.report.service.DateReportService;
import org.springframework.stereotype.Component;

@Component
public class ChoiceReport {

    private final BalanceReportService balanceReportService;
    private final DateReportService dateReportService;
    private final CategoryReportService categoryReportService;

    public ChoiceReport(BalanceReportService balanceReportService,
                        DateReportService dateReportService,
                        CategoryReportService categoryReportService) {
        this.balanceReportService = balanceReportService;
        this.dateReportService = dateReportService;
        this.categoryReportService = categoryReportService;
    }

    public IReportService getReportService(ReportType reportType) {
        switch (reportType) {
            case BALANCE:
                return this.balanceReportService;
            case BY_DATE:
                return this.dateReportService;
            case BY_CATEGORY:
                return this.categoryReportService;
            default: throw new RuntimeException();
        }
    }

}
