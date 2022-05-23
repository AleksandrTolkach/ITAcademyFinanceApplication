package by.tolkach.classifier.service.rest.object.converter;

import by.tolkach.classifier.dto.Currency;
import by.tolkach.classifier.service.rest.object.CurrencyRestObject;
import org.springframework.stereotype.Component;

@Component
public class CurrencyRestObjectConverter implements IRestObjectConverter<Currency, CurrencyRestObject> {
    @Override
    public CurrencyRestObject toRestObject(Currency currency) {
        return CurrencyRestObject.Builder.createBuilder()
                .setUuid(currency.getUuid())
                .setDtCreate(currency.getDtCreate())
                .setDtUpdate(currency.getDtUpdate())
                .setTitle(currency.getTitle())
                .setDescription(currency.getDescription())
                .build();
    }

    @Override
    public Currency toDto(CurrencyRestObject restObject) {
        return Currency.Builder.createBuilder()
                .setUuid(restObject.getUuid())
                .setDtCreate(restObject.getDtCreate())
                .setDtUpdate(restObject.getDtUpdate())
                .setTitle(restObject.getTitle())
                .setDescription(restObject.getDescription())
                .build();
    }
}
