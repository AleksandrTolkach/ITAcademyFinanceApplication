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
        String url = "http://localhost:8085/report/operation";

        HttpHeaders headers = this.createHeader();

        OperationRestObject operationRestObject = this.operationRestObjectConverter.toRestObject(operation);
        this.setOperationRestObjectParameters(operation, operationRestObject, accountId);
        HttpEntity<OperationRestObject> entity = new HttpEntity<>(operationRestObject, headers);
        this.restTemplate.postForObject(url, entity, String.class);
    }

    @Override
    public void updateOperation(Operation operation, UUID accountId) {
        String url = "http://localhost:8085/report/operation";

        HttpHeaders headers = this.createHeader();

        OperationRestObject operationRestObject = this.operationRestObjectConverter.toRestObject(operation);
        this.setOperationRestObjectParameters(operation, operationRestObject, accountId);
        HttpEntity<OperationRestObject> entity = new HttpEntity<>(operationRestObject, headers);
        this.restTemplate.put(url, entity);
    }

    @Override
    public void deleteOperation(UUID operationId, UUID accountId) {
        String url = "http://localhost:8085/report/operation/{operation_uuid}/account/{account_id}";
        this.restTemplate.delete(url, accountId, operationId);
    }

    @Override
    public void sendAccount(Account account) {
        String url = "http://localhost:8085/report/account";

        HttpHeaders headers = this.createHeader();

        AccountRestObject accountRestObject = this.accountRestObjectConverter.toRestObject(account);
        HttpEntity<AccountRestObject> entity = new HttpEntity<>(accountRestObject, headers);
        this.restTemplate.postForObject(url, entity, String.class);
    }

    @Override
    public void updateAccount(Account account) {
        String url = "http://localhost:8085/report/account";

        HttpHeaders headers = this.createHeader();

        AccountRestObject accountRestObject = this.accountRestObjectConverter.toRestObject(account);
        HttpEntity<AccountRestObject> entity = new HttpEntity<>(accountRestObject, headers);
        this.restTemplate.put(url, entity);
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
