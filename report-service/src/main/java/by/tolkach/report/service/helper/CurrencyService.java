package by.tolkach.report.service.helper;

import by.tolkach.report.dao.api.helper.ICurrencyStorage;
import by.tolkach.report.dao.api.helper.entity.CurrencyEntity;
import by.tolkach.report.dao.api.entity.converter.IEntityConverter;
import by.tolkach.report.dto.operation.Currency;
import by.tolkach.report.service.helper.api.ICurrencyService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CurrencyService implements ICurrencyService {

    private final ICurrencyStorage currencyStorage;
    private final IEntityConverter<Currency, CurrencyEntity> currencyEntityConverter;

    public CurrencyService(ICurrencyStorage currencyStorage,
                           IEntityConverter<Currency, CurrencyEntity> currencyEntityConverter) {
        this.currencyStorage = currencyStorage;
        this.currencyEntityConverter = currencyEntityConverter;
    }

    @Override
    public Currency save(Currency currency) {
        CurrencyEntity currencyEntity = this.currencyEntityConverter.toEntity(currency);
        currencyEntity = this.currencyStorage.save(currencyEntity);
        return this.currencyEntityConverter.toDto(currencyEntity);
    }

    @Override
    public Currency read(UUID currencyId) {
        CurrencyEntity currencyEntity = this.currencyStorage.findById(currencyId).orElse(null);
        return this.currencyEntityConverter.toDto(currencyEntity);
    }

    @Override
    public Currency read(String title) {
        CurrencyEntity currencyEntity = this.currencyStorage.findByTitle(title);
        return this.currencyEntityConverter.toDto(currencyEntity);
    }

    @Override
    public Currency update(Currency currency) {
        CurrencyEntity currencyEntity = this.currencyEntityConverter.toEntity(currency);
        this.updateCurrencyParameters(currency, currencyEntity);
        currencyEntity = this.currencyStorage.save(currencyEntity);
        return this.currencyEntityConverter.toDto(currencyEntity);
    }

    private CurrencyEntity updateCurrencyParameters(Currency currency, CurrencyEntity currencyEntity) {
        currencyEntity.setDtCreate(currency.getDtCreate());
        currencyEntity.setDtUpdate(currency.getDtUpdate());
        currencyEntity.setTitle(currency.getTitle());
        currencyEntity.setDescription(currency.getDescription());
        return currencyEntity;
    }
}
