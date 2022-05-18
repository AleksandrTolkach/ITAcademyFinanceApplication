package by.tolkach.account.service.rest;

import by.tolkach.account.dto.operation.Operation;
import by.tolkach.account.service.rest.api.IReportRestClientService;
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

    public ReportRestClientService(RestTemplateBuilder restTemplateBuilder,
                                   IRestObjectConverter<Operation, OperationRestObject> operationRestObjectConverter) {
        this.restTemplate = restTemplateBuilder.build();
        this.operationRestObjectConverter = operationRestObjectConverter;
    }

    @Override
    public void sendOperation(Operation operation, UUID accountId) {
        String url = "http://localhost:8085/report/operation";

        HttpHeaders headers = this.createHeader();

        OperationRestObject operationRestObject = this.operationRestObjectConverter.toRestObject(operation);
        operationRestObject.setAccount(accountId);
        HttpEntity<OperationRestObject> entity = new HttpEntity<>(operationRestObject, headers);
        this.restTemplate.postForObject(url, entity, String.class);
    }

    @Override
    public void updateOperation(Operation operation, UUID accountId) {
        String url = "http://localhost:8085/report/operation/update";

        HttpHeaders headers = this.createHeader();

        OperationRestObject operationRestObject = this.operationRestObjectConverter.toRestObject(operation);
        operationRestObject.setAccount(accountId);
        HttpEntity<OperationRestObject> entity = new HttpEntity<>(operationRestObject, headers);
        this.restTemplate.put(url, entity);
    }

    @Override
    public void deleteOperation(UUID operationId, UUID accountId) {
        String url = "http://localhost:8085/report/account/{account_id}/operation/{operation_uuid}";
        this.restTemplate.delete(url, accountId, operationId);
    }

    private HttpHeaders createHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }
}
