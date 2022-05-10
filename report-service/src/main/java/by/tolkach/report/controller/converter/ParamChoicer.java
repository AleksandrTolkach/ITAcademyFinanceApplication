package by.tolkach.report.controller.converter;

import by.tolkach.report.dto.reportParam.ExtendedParam;
import by.tolkach.report.dto.reportParam.ReportParamByBalance;
import by.tolkach.report.dto.reportParam.ReportParamByCategory;
import by.tolkach.report.dto.reportParam.ReportParamByDate;

public class ParamChoicer {

    public ReportParamByBalance createByBalance(ExtendedParam extendedParam) {
        return new ReportParamByBalance(extendedParam.getAccounts());
    }

    public ReportParamByDate createByDate(ExtendedParam extendedParam) {
        return ReportParamByDate.Builder.createBuilder()
                .setAccounts(extendedParam.getAccounts())
                .setFrom(extendedParam.getFrom())
                .setTo(extendedParam.getTo())
                .setCategories(extendedParam.getCategories())
                .build();
    }

    public ReportParamByCategory createByCategory(ExtendedParam extendedParam) {
        return ReportParamByCategory.Builder.createBuilder()
                .setAccounts(extendedParam.getAccounts())
                .setFrom(extendedParam.getFrom())
                .setTo(extendedParam.getTo())
                .setCategories(extendedParam.getCategories())
                .build();
    }
}
