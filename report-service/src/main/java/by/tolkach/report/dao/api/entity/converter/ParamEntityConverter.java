package by.tolkach.report.dao.api.entity.converter;

import by.tolkach.report.dao.api.entity.param.AccountsEntity;
import by.tolkach.report.dao.api.entity.param.CategoriesEntity;
import by.tolkach.report.dao.api.entity.param.ParamEntity;
import by.tolkach.report.dto.report.Param;
import org.springframework.stereotype.Component;

@Component
public class ParamEntityConverter implements IEntityConverter<Param, ParamEntity> {
    @Override
    public ParamEntity toEntity(Param param) {
        ParamEntity.Builder builder = ParamEntity.Builder.createBuilder()
                .setUuid(param.getUuid())
                .setAccounts(new AccountsEntity(param.getAccounts()));
        if (param.getFrom() != null && param.getTo() != null && param.getCategories().size() > 0) {
            builder.setFrom(param.getFrom())
                    .setTo(param.getTo())
                    .setCategories(new CategoriesEntity(param.getCategories()));
        }
        return builder.build();
    }

    @Override
    public Param toDto(ParamEntity entity) {
        Param.Builder builder = Param.Builder.createBuilder()
                .setUuid(entity.getUuid())
                .setAccounts(entity.getAccounts().getAccounts());
        if (entity.getFrom() != null && entity.getTo() != null && entity.getCategories().getCategories().size() > 0) {
                 builder.setFrom(entity.getFrom())
                         .setTo(entity.getTo())
                         .setCategories(entity.getCategories().getCategories());
        }
        return builder.build();
    }
}
