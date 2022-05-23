package by.tolkach.classifier.service.rest;

import by.tolkach.classifier.dto.Currency;
import by.tolkach.classifier.dto.OperationCategory;
import by.tolkach.classifier.service.rest.api.IReportRestClientService;
import by.tolkach.classifier.service.rest.object.CurrencyRestObject;
import by.tolkach.classifier.service.rest.object.OperationCategoryRestObject;
import by.tolkach.classifier.service.rest.object.converter.IRestObjectConverter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class ReportRestClient implements IReportRestClientService {

    private final RestTemplate restTemplate;
    private final IRestObjectConverter<Currency, CurrencyRestObject> currencyRestObjectConverter;
    private final IRestObjectConverter<OperationCategory, OperationCategoryRestObject> operationCategoryRestObjectConverter;

    public ReportRestClient(RestTemplateBuilder restTemplateBuilder,
                            IRestObjectConverter<Currency, CurrencyRestObject> currencyRestObjectConverter,
                            IRestObjectConverter<OperationCategory, OperationCategoryRestObject> operationCategoryRestObjectConverter) {
        this.restTemplate = restTemplateBuilder.build();
        this.currencyRestObjectConverter = currencyRestObjectConverter;
        this.operationCategoryRestObjectConverter = operationCategoryRestObjectConverter;
    }

    @Override
    public void sendCurrency(Currency currency) {
        String url = "http://localhost:8085/report/classifier/currency";

        HttpHeaders headers = this.createHeader();

        CurrencyRestObject currencyRestObject = this.currencyRestObjectConverter.toRestObject(currency);
        HttpEntity<CurrencyRestObject> entity = new HttpEntity<>(currencyRestObject, headers);
        this.restTemplate.postForObject(url, entity, String.class);
    }

    @Override
    public void updateCurrency(Currency currency) {
        String url = "http://localhost:8085/report/classifier/currency";

        HttpHeaders headers = this.createHeader();

        CurrencyRestObject currencyRestObject = this.currencyRestObjectConverter.toRestObject(currency);
        HttpEntity<CurrencyRestObject> entity = new HttpEntity<>(currencyRestObject, headers);
        this.restTemplate.put(url, entity);
    }

    @Override
    public void sendOperationCategory(OperationCategory operationCategory) {
        String url = "http://localhost:8085/report/classifier/operation_category";

        HttpHeaders headers = this.createHeader();

        OperationCategoryRestObject operationCategoryRestObject =
                this.operationCategoryRestObjectConverter.toRestObject(operationCategory);
        HttpEntity<OperationCategoryRestObject> entity = new HttpEntity<>(operationCategoryRestObject, headers);
        this.restTemplate.postForObject(url, entity, String.class);
    }

    @Override
    public void updateOperationCategory(OperationCategory operationCategory) {
        String url = "http://localhost:8085/report/classifier/operation_category";

        HttpHeaders headers = this.createHeader();

        OperationCategoryRestObject operationCategoryRestObject =
                this.operationCategoryRestObjectConverter.toRestObject(operationCategory);
        HttpEntity<OperationCategoryRestObject> entity = new HttpEntity<>(operationCategoryRestObject, headers);
        this.restTemplate.put(url, entity);
    }

    private HttpHeaders createHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }
}
