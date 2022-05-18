package by.tolkach.report.dao.api.entity.converter;

import by.tolkach.report.dao.api.entity.AccountsEntity;
import by.tolkach.report.dao.api.entity.CategoriesEntity;
import by.tolkach.report.dao.api.entity.reportParam.ExtendedParamEntity;
import by.tolkach.report.dto.reportParam.ExtendedParam;
import org.springframework.stereotype.Component;

@Component
public class ReportParamEntityConverter implements IEntityConverter<ExtendedParam, ExtendedParamEntity> {
    @Override
    public ExtendedParamEntity toEntity(ExtendedParam paramBalance) {
        return ExtendedParamEntity.Builder.createBuilder()
                .setAccounts(new AccountsEntity(paramBalance.getAccounts()))
                .setFrom(paramBalance.getFrom())
                .setTo(paramBalance.getTo())
                .setCategories(new CategoriesEntity(paramBalance.getCategories()))
                .build();
    }

    @Override
    public ExtendedParam toDto(ExtendedParamEntity entity) {
        return ExtendedParam.Builder.createBuilder()
                .setAccounts(entity.getAccounts().getAccounts())
                .setFrom(entity.getFrom())
                .setTo(entity.getTo())
                .setCategories(entity.getCategories().getCategories())
                .build();
    }
}
