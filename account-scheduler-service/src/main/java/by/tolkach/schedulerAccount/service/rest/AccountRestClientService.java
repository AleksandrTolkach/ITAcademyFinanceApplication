package by.tolkach.schedulerAccount.service.rest;

import by.tolkach.schedulerAccount.dto.exception.NotFoundException;
import by.tolkach.schedulerAccount.dto.scheduledOperation.Operation;
import by.tolkach.schedulerAccount.service.rest.api.IAccountRestClientService;
import by.tolkach.schedulerAccount.service.rest.object.OperationRestObject;
import by.tolkach.schedulerAccount.service.rest.object.converter.IRestObjectConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

@Service
public class AccountRestClientService implements IAccountRestClientService {

    private final RestTemplate restTemplate;
    private final IRestObjectConverter<Operation, OperationRestObject> operationRestObjectConverter;
    @Value("${account.url}")
    private String accountUrl;

    public AccountRestClientService(RestTemplateBuilder restTemplateBuilder,
                                    IRestObjectConverter<Operation, OperationRestObject> operationRestObjectConverter) {
        this.restTemplate = restTemplateBuilder.build();
        this.operationRestObjectConverter = operationRestObjectConverter;
    }

    @Override
    public void readAccount(UUID accountId) {
        String uri = accountUrl + "/{accountId}";
        try {
            this.restTemplate.getForObject(uri, String.class, accountId);
        } catch (HttpClientErrorException e) {
            throw new NotFoundException("Счета с Id " + accountId + " не существует.");
        }
    }

    public String createOperation(Operation operation) {
        String uri = accountUrl + "/{accountId}/operation";

        HttpHeaders headers = this.createHeader();

        OperationRestObject operationRestObject = this.operationRestObjectConverter.toRestObject(operation);
        operationRestObject.setDate(LocalDateTime.now());

        HttpEntity<OperationRestObject> entity = new HttpEntity<>(operationRestObject, headers);
        return this.restTemplate.postForObject(uri, entity, String.class, operation.getAccount());
    }

    @Override
    public Operation readOperation(UUID operationId, UUID accountId) {
        String uri = accountUrl + "/{accountId}/operation/{operationId}";
        OperationRestObject operationRestObject = this.restTemplate.getForObject(uri, OperationRestObject.class,
                accountId, operationId);
        return this.operationRestObjectConverter.toDto(operationRestObject);
    }

    @Override
    public String updateOperation(Operation operation) {
        String uri = accountUrl + "/{accountId}/operation/{operationId}/dt_update/{dtUpdate}";

        OperationRestObject operationRestObject = this.operationRestObjectConverter
                .toRestObject(this.readOperation(operation.getUuid(), operation.getAccount()));

        this.restTemplate.put(uri, operation, operationRestObject.getAccount(),
                operationRestObject.getUuid(), operationRestObject.getDtUpdate());
        return "Операция обновлена.";
    }

    private HttpHeaders createHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }
}
