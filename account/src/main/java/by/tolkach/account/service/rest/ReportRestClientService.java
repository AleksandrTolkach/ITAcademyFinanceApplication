package by.tolkach.account.service.rest;

import by.tolkach.account.dto.Currency;
import by.tolkach.account.dto.account.Account;
import by.tolkach.account.dto.operation.Operation;
import by.tolkach.account.dto.operation.OperationCategory;
import by.tolkach.account.service.rest.api.IClassifierRestClientService;
import by.tolkach.account.service.rest.api.IReportRestClientService;
import by.tolkach.account.service.rest.object.AccountRestObject;
import by.tolkach.account.service.rest.object.OperationRestObject;
import by.tolkach.account.service.rest.object.converter.IRestObjectConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.UUID;

@Service
public class ReportRestClientService implements IReportRestClientService {

    private final RestTemplate restTemplate;
    private final IRestObjectConverter<Operation, OperationRestObject> operationRestObjectConverter;
    private final IRestObjectConverter<Account, AccountRestObject> accountRestObjectConverter;
    private final IClassifierRestClientService classifierRestClientService;
    private @Value("${rep_operation_url}") String operationUrl;
    private @Value("${rep_account_url}") String accountUrl;

    public ReportRestClientService(RestTemplateBuilder restTemplateBuilder,
                                   IRestObjectConverter<Operation, OperationRestObject> operationRestObjectConverter,
                                   IRestObjectConverter<Account, AccountRestObject> accountRestObjectConverter,
                                   IClassifierRestClientService classifierRestClientService) {
        this.restTemplate = restTemplateBuilder.build();
        this.operationRestObjectConverter = operationRestObjectConverter;
        this.accountRestObjectConverter = accountRestObjectConverter;
        this.classifierRestClientService = classifierRestClientService;
    }

    @Override
    public void sendOperation(Operation operation, UUID accountId) {
        HttpHeaders headers = this.createHeader();

        OperationRestObject operationRestObject = this.operationRestObjectConverter.toRestObject(operation);
        this.setOperationRestObjectParameters(operation, operationRestObject, accountId);
        HttpEntity<OperationRestObject> entity = new HttpEntity<>(operationRestObject, headers);
        this.restTemplate.postForObject(operationUrl, entity, String.class);
    }

    @Override
    public void updateOperation(Operation operation, UUID accountId) {
        HttpHeaders headers = this.createHeader();

        OperationRestObject operationRestObject = this.operationRestObjectConverter.toRestObject(operation);
        this.setOperationRestObjectParameters(operation, operationRestObject, accountId);
        HttpEntity<OperationRestObject> entity = new HttpEntity<>(operationRestObject, headers);
        this.restTemplate.put(operationUrl, entity);
    }

    @Override
    public void deleteOperation(UUID operationId, UUID accountId) {
        String uri = operationUrl + "/{operation_uuid}/account/{account_id}";
        this.restTemplate.delete(uri, accountId, operationId);
    }

    @Override
    public void sendAccount(Account account) {
        HttpHeaders headers = this.createHeader();
        AccountRestObject accountRestObject = this.accountRestObjectConverter.toRestObject(account);
        HttpEntity<AccountRestObject> entity = new HttpEntity<>(accountRestObject, headers);
        this.restTemplate.postForObject(accountUrl, entity, String.class);
    }

    @Override
    public void updateAccount(Account account) {
        HttpHeaders headers = this.createHeader();
        AccountRestObject accountRestObject = this.accountRestObjectConverter.toRestObject(account);
        HttpEntity<AccountRestObject> entity = new HttpEntity<>(accountRestObject, headers);
        this.restTemplate.put(accountUrl, entity);
    }

    private HttpHeaders createHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }

    private OperationRestObject setOperationRestObjectParameters(Operation operation,
                                                                OperationRestObject operationRestObject,
                                                                UUID accountId) {
        OperationCategory operationCategory =
                this.classifierRestClientService.readOperationCategory(operation.getCategory());
        Currency currency = this.classifierRestClientService.readCurrency(operation.getCurrency());
        operationRestObject.setAccount(accountId);
        operationRestObject.setCategory(operationCategory.getTitle());
        operationRestObject.setCurrency(currency.getTitle());
        return operationRestObject;
    }
}
