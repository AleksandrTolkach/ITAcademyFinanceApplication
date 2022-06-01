package by.tolkach.schedulerAccount.service.rest;

import by.tolkach.schedulerAccount.dto.Currency;
import by.tolkach.schedulerAccount.dto.scheduledOperation.OperationCategory;
import by.tolkach.schedulerAccount.service.rest.api.IClassifierRestClientService;
import by.tolkach.schedulerAccount.service.rest.object.CurrencyRestObject;
import by.tolkach.schedulerAccount.service.rest.object.OperationCategoryRestObject;
import by.tolkach.schedulerAccount.service.rest.object.converter.IRestObjectConverter;
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
    private @Value("${classifier.currency.url}") String currencyUrl;
    private @Value("${classifier.category.url}") String categoryUrl;

    public ClassifierRestClientService(RestTemplateBuilder restTemplateBuilder,
                                       IRestObjectConverter<Currency, CurrencyRestObject> currencyRestObjectConverter,
                                       IRestObjectConverter<OperationCategory, OperationCategoryRestObject> operationCategoryRestObjectConverter) {
        this.restTemplate = restTemplateBuilder.build();
        this.currencyRestObjectConverter = currencyRestObjectConverter;
        this.operationCategoryRestObjectConverter = operationCategoryRestObjectConverter;
    }

    @Override
    public Currency readCurrency(UUID currencyId) {
        String uri = currencyUrl + "/{uuid}";
        CurrencyRestObject currencyRestObject = this.restTemplate.getForObject(uri,
                CurrencyRestObject.class, currencyId);
        return this.currencyRestObjectConverter.toDto(currencyRestObject);
    }

    @Override
    public OperationCategory readOperationCategory(UUID operationCategoryId) {
        String uri = categoryUrl + "/{operationCategoryId}";
        OperationCategoryRestObject operationCategoryRestobject =
                this.restTemplate.getForObject(uri, OperationCategoryRestObject.class, operationCategoryId);
        return this.operationCategoryRestObjectConverter.toDto(operationCategoryRestobject);
    }
}
