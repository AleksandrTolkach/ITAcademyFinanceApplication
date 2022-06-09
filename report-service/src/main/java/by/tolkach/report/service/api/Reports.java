package by.tolkach.report.service.api;

import by.tolkach.report.dto.report.Param;
import by.tolkach.report.dto.report.Report;
import by.tolkach.report.dto.report.ReportStatus;
import by.tolkach.report.dto.report.ReportType;
import by.tolkach.report.service.helper.api.IOperationCategoryService;

import java.time.LocalDateTime;
import java.util.UUID;

public class Reports {

    private Reports() {
    }

    public static Report createProperties(Param param, ReportType reportType,
                                          IOperationCategoryService operationCategoryService) {
        LocalDateTime dtCreate = LocalDateTime.now();
        StringBuilder sb = new StringBuilder();
        switch (reportType) {
            case BALANCE:
                sb.append("Отчет по счетам:").append(" ");
                for (UUID accountId: param.getAccounts()) {
                    sb.append(accountId).append("; ");
                }
                break;
            case BY_DATE:
                sb.append("Отчет за период ").append(param.getFrom()).append(" - ").append(param.getTo());
                break;
            case BY_CATEGORY:
                sb.append("Отчет по категориям: ");
                for (UUID categoryId: param.getCategories()) {
                    sb.append(operationCategoryService.read(categoryId).getTitle()).append("; ");
                }
                break;
        }

        return Report.Builder.createBuilder()
                .setUuid(UUID.randomUUID())
                .setDtCreate(dtCreate)
                .setDtUpdate(dtCreate)
                .setDescription(sb.toString())
                .setStatus(ReportStatus.LOADED)
                .setParams(param)
                .setType(reportType)
                .build();
    }
}
