package by.tolkach.classifier.service.classifier;

import by.tolkach.classifier.dao.api.ICurrencyStorage;
import by.tolkach.classifier.dao.api.entity.CurrencyEntity;
import by.tolkach.classifier.dao.api.entity.converter.IEntityConverter;
import by.tolkach.classifier.dto.Currency;
import by.tolkach.classifier.dto.Page;
import by.tolkach.classifier.dto.SimplePageable;
import by.tolkach.classifier.service.classifier.api.ICurrencyService;
import by.tolkach.classifier.service.classifier.api.IValidationService;
import by.tolkach.classifier.service.classifier.api.Pagination;
import by.tolkach.classifier.service.classifier.api.exception.NotFoundError;
import by.tolkach.classifier.service.rest.api.IReportRestClientService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CurrencyService implements ICurrencyService {

    private final ICurrencyStorage currencyStorage;
    private final IEntityConverter<Currency, CurrencyEntity> currencyEntityConverter;
    private final IValidationService<Currency> currencyValidationService;
    private final IReportRestClientService reportRestClientService;

    public CurrencyService(ICurrencyStorage currencyStorage,
                           IEntityConverter<Currency, CurrencyEntity> currencyEntityConverter,
                           IValidationService<Currency> currencyValidationService,
                           IReportRestClientService reportRestClientService) {
        this.currencyStorage = currencyStorage;
        this.currencyEntityConverter = currencyEntityConverter;
        this.currencyValidationService = currencyValidationService;
        this.reportRestClientService = reportRestClientService;
    }

    @Override
    public Currency create(Currency currency) {
        this.currencyValidationService.validate(currency);
        this.createCurrencyParameters(currency);
        CurrencyEntity currencyEntity = this.currencyEntityConverter.toEntity(currency);
        currency = this.currencyEntityConverter.toDto(this.currencyStorage.save(currencyEntity));
        this.reportRestClientService.sendCurrency(currency);
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

    @Override
    public List<Currency> read() {
        List<CurrencyEntity> currencyEntities = this.currencyStorage.findAllBy();
        List<Currency> currencies = new ArrayList<>();
        for (CurrencyEntity currencyEntity: currencyEntities) {
            currencies.add(this.currencyEntityConverter.toDto(currencyEntity));
        }
        return currencies;
    }

    @Override
    public Currency read(String title) {
        CurrencyEntity currencyEntity = this.currencyStorage.findByTitle(title);
        return this.currencyEntityConverter.toDto(currencyEntity);
    }

    public Currency createCurrencyParameters(Currency currency) {
        LocalDateTime createdTime = LocalDateTime.now();
        currency.setDtCreate(createdTime);
        currency.setDtUpdate(createdTime);
        return currency;
    }
}
