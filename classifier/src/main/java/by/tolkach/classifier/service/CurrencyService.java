package by.tolkach.classifier.service;

import by.tolkach.classifier.dao.api.ICurrencyStorage;
import by.tolkach.classifier.dao.api.entity.CurrencyEntity;
import by.tolkach.classifier.dao.api.entity.converter.IEntityConverter;
import by.tolkach.classifier.dto.Currency;
import by.tolkach.classifier.dto.Page;
import by.tolkach.classifier.dto.SimplePageable;
import by.tolkach.classifier.service.api.ICurrencyService;
import by.tolkach.classifier.service.api.IValidationService;
import by.tolkach.classifier.service.api.Pagination;
import by.tolkach.classifier.service.api.exception.NotFoundError;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CurrencyService implements ICurrencyService {

    private final ICurrencyStorage currencyStorage;
    private final IEntityConverter<Currency, CurrencyEntity> currencyEntityConverter;
    private final IValidationService<Currency> currencyValidationService;

    public CurrencyService(ICurrencyStorage currencyStorage,
                           IEntityConverter<Currency, CurrencyEntity> currencyEntityConverter,
                           IValidationService<Currency> currencyValidationService) {
        this.currencyStorage = currencyStorage;
        this.currencyEntityConverter = currencyEntityConverter;
        this.currencyValidationService = currencyValidationService;
    }

    @Override
    public Currency create(Currency currency) {
        this.currencyValidationService.validate(currency);
        this.createCurrencyParameters(currency);
        CurrencyEntity currencyEntity = this.currencyEntityConverter.toEntity(currency);
        return this.currencyEntityConverter.toDto(this.currencyStorage.save(currencyEntity));
    }

    @Override
    public Page<Currency> read(SimplePageable pageable) {
        List<CurrencyEntity> currencyEntities = this.currencyStorage.findAllBy(PageRequest.of(pageable.getPage(), pageable.getSize()));
        return Pagination.pageOf(Currency.class, CurrencyEntity.class).properties(currencyEntities, pageable,
                this.currencyStorage.count(), this.currencyEntityConverter);
    }

    @Override
    public Currency read(UUID currencyId) {
        CurrencyEntity currencyEntity = this.currencyStorage.findById(currencyId).orElse(null);
        if (currencyEntity == null) {
            throw new NotFoundError("Валюты с таким ID не существует.");
        }
        return this.currencyEntityConverter.toDto(currencyEntity);
    }

    public Currency createCurrencyParameters(Currency currency) {
        LocalDateTime createdTime = LocalDateTime.now();
        currency.setDtCreate(createdTime);
        currency.setDtUpdate(createdTime);
        return currency;
    }
}
