package by.tolkach.report.service.handler.api;

import by.tolkach.report.dto.report.ReportType;
import by.tolkach.report.service.handler.BalanceReportHandler;
import by.tolkach.report.service.handler.ByCategoryReportHandler;
import by.tolkach.report.service.handler.ByDateReportHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ReportHandlerFactory {

    private final ApplicationContext applicationContext;

    public ReportHandlerFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public IReportHandler getReportService(ReportType reportType) {
        switch (reportType) {
            case BALANCE:
                return applicationContext.getBean(BalanceReportHandler.class);
            case BY_DATE:
                return applicationContext.getBean(ByDateReportHandler.class);
            case BY_CATEGORY:
                return applicationContext.getBean(ByCategoryReportHandler.class);
            default: throw new RuntimeException();
        }
    }
}
