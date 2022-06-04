package by.tolkach.bot.service.rest;

import by.tolkach.bot.dto.Currency;
import by.tolkach.bot.dto.OperationCategory;
import by.tolkach.bot.service.rest.api.IClassifierRestClientService;
import by.tolkach.bot.service.rest.object.CurrencyRestObject;
import by.tolkach.bot.service.rest.object.OperationCategoryRestObject;
import by.tolkach.bot.service.rest.object.converter.IRestObjectConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassifierRestClientService implements IClassifierRestClientService {

    private final RestTemplate restTemplate;
    private final IRestObjectConverter<Currency, CurrencyRestObject> currencyRestObjectConverter;
    private final IRestObjectConverter<OperationCategory, OperationCategoryRestObject> operationCategoryRestObjectConverter;
    @Value("${classifier.url}")
    private String url;

    public ClassifierRestClientService(RestTemplateBuilder restTemplateBuilder,
                                       IRestObjectConverter<Currency, CurrencyRestObject> currencyRestObjectConverter,
                                       IRestObjectConverter<OperationCategory,
                                               OperationCategoryRestObject> operationCategoryRestObjectConverter) {
        this.restTemplate = restTemplateBuilder.build();
        this.currencyRestObjectConverter = currencyRestObjectConverter;
        this.operationCategoryRestObjectConverter = operationCategoryRestObjectConverter;
    }

    @Override
    public List<OperationCategory> readCategory() {
        String uri = url + "/operation/category/all";
        OperationCategoryRestObject[] operationCategoryRestObjects
                = this.restTemplate.getForObject(uri, OperationCategoryRestObject[].class);
        List<OperationCategory> operationCategories = new ArrayList<>();
        for (OperationCategoryRestObject categoryRestObject: operationCategoryRestObjects) {
            operationCategories.add(this.operationCategoryRestObjectConverter.toDto(categoryRestObject));
        }
        return operationCategories;
    }

    @Override
    public OperationCategory readCategory(String title) {
        String uri = url + "/operation/category/title/{title}";
        OperationCategoryRestObject operationCategoryRestObject =
                this.restTemplate.getForObject(uri, OperationCategoryRestObject.class, title);
        return this.operationCategoryRestObjectConverter.toDto(operationCategoryRestObject);
    }

    @Override
    public List<Currency> readCurrency() {
        String uri = url + "/currency/all";
        CurrencyRestObject[] currencyRestObjects = this.restTemplate.getForObject(uri, CurrencyRestObject[].class);
        List<Currency> currencies = new ArrayList<>();
        for (CurrencyRestObject currencyRestObject: currencyRestObjects) {
            currencies.add(this.currencyRestObjectConverter.toDto(currencyRestObject));
        }
        return currencies;
    }

    @Override
    public Currency readCurrency(String title) {
        String uri = url + "/currency/title/{title}";
        CurrencyRestObject currencyRestObject = this.restTemplate.getForObject(uri, CurrencyRestObject.class, title);
        return this.currencyRestObjectConverter.toDto(currencyRestObject);
    }
}
