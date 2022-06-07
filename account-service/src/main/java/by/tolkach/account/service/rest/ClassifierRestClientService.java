package by.tolkach.account.service.rest;

import by.tolkach.account.dto.Currency;
import by.tolkach.account.dto.operation.OperationCategory;
import by.tolkach.account.service.rest.api.IClassifierRestClientService;
import by.tolkach.account.service.rest.object.CurrencyRestObject;
import by.tolkach.account.service.rest.object.OperationCategoryRestObject;
import by.tolkach.account.service.rest.object.converter.IRestObjectConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class ClassifierRestClientService implements IClassifierRestClientService {

    private final RestTemplate restTemplate;
    private final IRestObjectConverter<Currency, CurrencyRestObject> currencyRestObjectConverter;
    private final IRestObjectConverter<OperationCategory, OperationCategoryRestObject> operationCategoryRestObjectConverter;
    private @Value("${classifier.url}") String url;

    public ClassifierRestClientService(RestTemplateBuilder restTemplateBuilder,
                                       IRestObjectConverter<Currency, CurrencyRestObject> currencyRestObjectConverter,
                                       IRestObjectConverter<OperationCategory, OperationCategoryRestObject> operationCategoryRestObjectConverter) {
        this.restTemplate = restTemplateBuilder.build();
        this.currencyRestObjectConverter = currencyRestObjectConverter;
        this.operationCategoryRestObjectConverter = operationCategoryRestObjectConverter;
    }

    @Override
    public Currency readCurrency(UUID currencyId) {
        String uri = url + "/currency/{uuid}";
        CurrencyRestObject currencyRestObject = this.restTemplate.getForObject(uri,
                CurrencyRestObject.class, currencyId);
        return this.currencyRestObjectConverter.toDto(currencyRestObject);
    }

    @Override
    public OperationCategory readOperationCategory(UUID operationCategoryId) {
        String uri = url + "/operation/category/{operationCategoryId}";
        OperationCategoryRestObject operationCategoryRestobject =
                this.restTemplate.getForObject(uri, OperationCategoryRestObject.class, operationCategoryId);
        return this.operationCategoryRestObjectConverter.toDto(operationCategoryRestobject);
    }
}
