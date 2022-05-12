package by.tolkach.schedulerAccount.service.rest;

import by.tolkach.schedulerAccount.dto.Currency;
import by.tolkach.schedulerAccount.dto.scheduledOperation.OperationCategory;
import by.tolkach.schedulerAccount.service.rest.api.IClassifierRestClientService;
import by.tolkach.schedulerAccount.service.rest.object.CurrencyRestObject;
import by.tolkach.schedulerAccount.service.rest.object.OperationCategoryRestObject;
import by.tolkach.schedulerAccount.service.rest.object.converter.IRestObjectConverter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
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
    public OperationCategory readOperationCategory(UUID operationCategoryId) {
        String url = "http://localhost:8084/classifier/operation/category/{operationCategoryId}";
        OperationCategoryRestObject operationCategoryRestobject =
                this.restTemplate.getForObject(url, OperationCategoryRestObject.class, operationCategoryId);
        return this.operationCategoryRestObjectConverter.toDto(operationCategoryRestobject);
    }
}
