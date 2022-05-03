package by.tolkach.schedulerAccount.service.rest;

import by.tolkach.schedulerAccount.dto.Operation;
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

@Service
public class RestClientService {

    private final RestTemplate restTemplate;
    private final IRestObjectConverter<Operation, OperationRestObject> operationRestObjectConverter;

    public RestClientService(RestTemplateBuilder restTemplateBuilder,
                             IRestObjectConverter<Operation, OperationRestObject> operationRestObjectConverter) {
        this.restTemplate = restTemplateBuilder.build();
        this.operationRestObjectConverter = operationRestObjectConverter;
    }

    public String create(Operation operation) {
        String url = "http://localhost:8082/account/{accountId}/operation";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        OperationRestObject operationRestObject = this.operationRestObjectConverter.toRestObject(operation);
        operationRestObject.setDate(LocalDate.now());

        HttpEntity<OperationRestObject> entity = new HttpEntity<>(operationRestObject, headers);
        return this.restTemplate.postForObject(url, entity, String.class, operation.getAccount());
    }
}
