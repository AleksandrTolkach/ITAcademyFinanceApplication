package by.tolkach.mailScheduler.dao.api.entity.converter;

import by.tolkach.mailScheduler.dao.api.entity.AccountsEntity;
import by.tolkach.mailScheduler.dao.api.entity.CategoriesEntity;
import by.tolkach.mailScheduler.dao.api.entity.ParamEntity;
import by.tolkach.mailScheduler.dto.scheduledMail.Param;
import by.tolkach.mailScheduler.dto.scheduledMail.ReportType;
import org.springframework.stereotype.Component;

@Component
public class ParamEntityConverter implements IParamEntityConverter {

    private ReportType reportType = ReportType.BALANCE;

    @Override
    public ParamEntity toEntity(Param param) {
        ParamEntity.Builder builder = ParamEntity.Builder.createBuilder()
                .setUuid(param.getUuid())
                .setAccounts(new AccountsEntity(param.getAccounts()));
        if (this.reportType.equals(ReportType.BALANCE)) {
            return builder.build();
        }
        if (param.getFrom() != null && param.getTo() != null) {
            builder.setFrom(param.getFrom())
                    .setTo(param.getTo());
        }
        if (param.getCategories().size() > 0) {
            builder.setCategories(new CategoriesEntity(param.getCategories()));
        }
        return builder.build();
    }

    @Override
    public Param toDto(ParamEntity entity) {
        Param.Builder builder = Param.Builder.createBuilder()
                .setUuid(entity.getUuid())
                .setAccounts(entity.getAccounts().getAccounts());
        if (this.reportType.equals(ReportType.BALANCE)) {
            return builder.build();
        }
        if (entity.getFrom() != null && entity.getTo() != null) {
                 builder.setFrom(entity.getFrom())
                         .setTo(entity.getTo());
        }
        if (entity.getCategories() != null) {
            builder.setCategories(entity.getCategories().getCategories());
        }
        return builder.build();
    }

    @Override
    public ParamEntityConverter setType(ReportType reportType) {
        this.reportType = reportType;
        return this;
    }
}
