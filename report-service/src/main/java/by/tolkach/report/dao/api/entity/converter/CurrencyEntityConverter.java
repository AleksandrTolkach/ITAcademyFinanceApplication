package by.tolkach.report.dao.api.entity.converter;

import by.tolkach.report.dao.api.entity.CurrencyEntity;
import by.tolkach.report.dto.operation.Currency;
import org.springframework.stereotype.Component;

@Component
public class CurrencyEntityConverter implements IEntityConverter<Currency, CurrencyEntity> {

    @Override
    public CurrencyEntity toEntity(Currency currency) {
        return CurrencyEntity.Builder.createBuilder()
                .setUuid(currency.getUuid())
                .setDtCreate(currency.getDtCreate())
                .setDtUpdate(currency.getDtUpdate())
                .setTitle(currency.getTitle())
                .setDescription(currency.getDescription())
                .build();
    }

    @Override
    public Currency toDto(CurrencyEntity entity) {
        return Currency.Builder.createBuilder()
                .setUuid(entity.getUuid())
                .setDtCreate(entity.getDtCreate())
                .setDtUpdate(entity.getDtUpdate())
                .setTitle(entity.getTitle())
                .setDescription(entity.getDescription())
                .build();
    }
}
