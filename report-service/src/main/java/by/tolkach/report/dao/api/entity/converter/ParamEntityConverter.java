package by.tolkach.report.dao.api.entity.converter;

import by.tolkach.report.dao.api.entity.AccountsEntity;
import by.tolkach.report.dao.api.entity.reportParam.ReportParamByBalanceEntity;
import by.tolkach.report.dto.reportParam.ReportParamByBalance;
import org.springframework.stereotype.Component;

@Component
public class ParamEntityConverter implements IEntityConverter<ReportParamByBalance, ReportParamByBalanceEntity> {
    @Override
    public ReportParamByBalanceEntity toEntity(ReportParamByBalance param) {
        return new ReportParamByBalanceEntity(new AccountsEntity(param.getAccounts()));
    }

    @Override
    public ReportParamByBalance toDto(ReportParamByBalanceEntity entity) {
        return new ReportParamByBalance(entity.getAccounts().getAccounts());
    }
}
