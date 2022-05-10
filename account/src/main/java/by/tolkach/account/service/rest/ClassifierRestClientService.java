package by.tolkach.account.service.rest;

import by.tolkach.account.dto.Currency;
import by.tolkach.account.dto.OperationCategory;
import by.tolkach.account.service.rest.api.IClassifierRestClientService;
import by.tolkach.account.service.rest.object.CurrencyRestObject;
import by.tolkach.account.service.rest.object.OperationCategoryRestObject;
import by.tolkach.account.service.rest.object.converter.IRestObjectConverter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class ClassifierRestClientService implements IClassifierRestClientService {

    private final RestTemplate restTemplate;
    private final IRestObjectConverter<Currency, CurrencyRestObject> currencyRestObjectConverter;
    private final IRestObjectConverter<OperationCategory, OperationCategoryRestObject> operationCategoryRestObjectConverter;

    public ClassifierRestClientService(RestTemplateBuilder restTemplateBuilder,
                                       IRestObjectConverter<Currency, CurrencyRestObject> currencyRestObjectConverter,
                                       IRestObjectConverter<OperationCategory, OperationCategoryRestObject> operationCategoryRestObjectConverter) {
        this.restTemplate = restTemplateBuilder.build();
        this.currencyRestObjectConverter = currencyRestObjectConverter;
        this.operationCategoryRestObjectConverter = operationCategoryRestObjectConverter;
    }

    @Override
    public Currency readCurrency(UUID currencyId) {
        String url = "http://localhost:8084/classifier/currency/{uuid}";
        CurrencyRestObject currencyRestObject = this.restTemplate.getForObject(url,
                CurrencyRestObject.class, currencyId);
        return this.currencyRestObjectConverter.toDto(currencyRestObject);
    }

    @Override
    public OperationCategory readOperationCategory(UUID operationCategoryId) throws HttpServerErrorException {
        String url = "http://localhost:8084/classifier/operation/category/{operationCategoryId}";
        OperationCategoryRestObject operationCategoryRestobject =
                this.restTemplate.getForObject(url, OperationCategoryRestObject.class, operationCategoryId);
        return this.operationCategoryRestObjectConverter.toDto(operationCategoryRestobject);
    }
}
