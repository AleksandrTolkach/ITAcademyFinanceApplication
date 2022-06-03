package by.tolkach.bot.service.rest;

import by.tolkach.bot.dto.Operation;
import by.tolkach.bot.service.rest.api.IOperationRestClientService;
import by.tolkach.bot.service.rest.object.OperationRestObject;
import by.tolkach.bot.service.rest.object.converter.IRestObjectConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

@Service
public class OperationRestClientService implements IOperationRestClientService {

    private final RestTemplate restTemplate;
    private final IRestObjectConverter<Operation, OperationRestObject> operationRestObjectConverter;
    @Value("${account.url}")
    private String accountUrl;

    public OperationRestClientService(RestTemplateBuilder restTemplateBuilder,
                                      IRestObjectConverter<Operation, OperationRestObject> operationRestObjectConverter) {
        this.restTemplate = restTemplateBuilder.build();
        this.operationRestObjectConverter = operationRestObjectConverter;
    }

    public String create(Operation operation) {
        String uri = accountUrl + "/{accountId}/operation";

        HttpHeaders headers = this.createHeader();

        OperationRestObject operationRestObject = this.operationRestObjectConverter.toRestObject(operation);
        operationRestObject.setDate(LocalDateTime.now());

        HttpEntity<OperationRestObject> entity = new HttpEntity<>(operationRestObject, headers);
        return this.restTemplate.postForObject(uri, entity, String.class, operation.getAccount());
    }

    @Override
    public Operation read(UUID operationId, UUID accountId) {
        String uri = accountUrl + "/{accountId}/operation/{operationId}";
        OperationRestObject operationRestObject = this.restTemplate.getForObject(uri, OperationRestObject.class,
                accountId, operationId);
        return this.operationRestObjectConverter.toDto(operationRestObject);
    }

    @Override
    public String update(Operation operation) {
        String uri = accountUrl + "/{accountId}/operation/{operationId}/dt_update/{dtUpdate}";

        OperationRestObject operationRestObject = this.operationRestObjectConverter
                .toRestObject(this.read(operation.getUuid(), operation.getAccount()));

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
