package by.tolkach.schedulerAccount.service.rest;

import by.tolkach.schedulerAccount.dto.Operation;
import by.tolkach.schedulerAccount.service.rest.api.IOperationRestClientService;
import by.tolkach.schedulerAccount.service.rest.object.OperationRestObject;
import by.tolkach.schedulerAccount.service.rest.object.converter.IRestObjectConverter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

@Service
public class OperationRestClientService implements IOperationRestClientService {

    private final RestTemplate restTemplate;
    private final IRestObjectConverter<Operation, OperationRestObject> operationRestObjectConverter;

    public OperationRestClientService(RestTemplateBuilder restTemplateBuilder,
                                      IRestObjectConverter<Operation, OperationRestObject> operationRestObjectConverter) {
        this.restTemplate = restTemplateBuilder.build();
        this.operationRestObjectConverter = operationRestObjectConverter;
    }

    public String create(Operation operation) {
        String url = "http://localhost:8082/account/{accountId}/operation";

        HttpHeaders headers = this.createHeader();

        OperationRestObject operationRestObject = this.operationRestObjectConverter.toRestObject(operation);
        operationRestObject.setDate(LocalDate.now());

        HttpEntity<OperationRestObject> entity = new HttpEntity<>(operationRestObject, headers);
        return this.restTemplate.postForObject(url, entity, String.class, operation.getAccount());
    }

    @Override
    public Operation read(UUID operationId, UUID accountId) {
        String url = "http://localhost:8082/account/{accountId}/operation/{operationId}";
        OperationRestObject operationRestObject = this.restTemplate.getForObject(url, OperationRestObject.class,
                accountId, operationId);
        return this.operationRestObjectConverter.toDto(operationRestObject);
    }

    @Override
    public String update(Operation operation) {
        String url = "http://localhost:8082/account/{accountId}/operation/{operationId}/dt_update/{dtUpdate}";

        OperationRestObject operationRestObject = this.operationRestObjectConverter
                .toRestObject(this.read(operation.getUuid(), operation.getAccount()));

        this.restTemplate.put(url, operation, operationRestObject.getAccount(),
                operationRestObject.getUuid(), operationRestObject.getDtUpdate());
        return "ok";
    }

    private HttpHeaders createHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }
}
