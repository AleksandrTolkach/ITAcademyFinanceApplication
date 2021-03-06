package by.tolkach.mail.service.rest;

import by.tolkach.mail.service.rest.api.IClassifierRestClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class ClassifierRestClientService implements IClassifierRestClientService {

    private final RestTemplate restTemplate;
    private @Value("${classifier.url}") String url;

    public ClassifierRestClientService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public void readOperationCategory(UUID operationCategoryId) {
        String uri = url + "/operation/category/{operationCategoryId}";
        this.restTemplate.getForObject(uri, String.class, operationCategoryId);
    }
}
