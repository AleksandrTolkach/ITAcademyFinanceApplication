package by.tolkach.report.dao.api.entity.converter;

import by.tolkach.report.dao.api.entity.AccountsEntity;
import by.tolkach.report.dao.api.entity.reportParam.CategoriesEntity;
import by.tolkach.report.dao.api.entity.reportParam.ParamEntity;
import by.tolkach.report.dto.reportParam.Param;
import org.springframework.stereotype.Component;

@Component
public class ParamEntityConverter implements IEntityConverter<Param, ParamEntity> {
    @Override
    public ParamEntity toEntity(Param param) {
        return ParamEntity.Builder.createBuilder()
                .setUuid(param.getUuid())
                .setFrom(param.getFrom())
                .setTo(param.getTo())
                .setAccounts(new AccountsEntity(param.getAccounts()))
                .setCategories(new CategoriesEntity(param.getCategories()))
                .build();
    }

    @Override
    public Param toDto(ParamEntity entity) {
        return Param.Builder.createBuilder()
                .setUuid(entity.getUuid())
                .setFrom(entity.getFrom())
                .setTo(entity.getTo())
                .setAccounts(entity.getAccounts().getAccounts())
                .setCategories(entity.getCategories().getCategories())
                .build();
    }
}
