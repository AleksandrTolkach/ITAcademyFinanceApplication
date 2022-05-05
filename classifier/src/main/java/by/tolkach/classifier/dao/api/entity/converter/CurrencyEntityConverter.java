package by.tolkach.classifier.dao.api.entity.converter;

import by.tolkach.classifier.dao.api.entity.CurrencyEntity;
import by.tolkach.classifier.dao.api.entity.builder.CurrencyEntityBuilder;
import by.tolkach.classifier.dto.Currency;
import by.tolkach.classifier.dto.builder.CurrencyBuilder;
import org.springframework.stereotype.Component;

@Component
public class CurrencyEntityConverter implements IEntityConverter<Currency, CurrencyEntity> {

    @Override
    public CurrencyEntity toEntity(Currency currency) {
        return CurrencyEntityBuilder.createBuilder()
                .setUuid(currency.getUuid())
                .setDtCreate(currency.getDtCreate())
                .setDtUpdate(currency.getDtUpdate())
                .setTitle(currency.getTitle())
                .build();
    }

    @Override
    public Currency toDto(CurrencyEntity entity) {
        return CurrencyBuilder.createBuilder()
                .setUuid(entity.getUuid())
                .setDtCreate(entity.getDtCreate())
                .setDtUpdate(entity.getDtUpdate())
                .setTitle(entity.getTitle())
                .build();
    }
}
