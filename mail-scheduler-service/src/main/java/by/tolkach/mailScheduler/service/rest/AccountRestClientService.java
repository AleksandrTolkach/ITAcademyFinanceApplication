package by.tolkach.mailScheduler.service.rest;

import by.tolkach.mailScheduler.service.rest.api.IAccountRestClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class AccountRestClientService implements IAccountRestClientService {

    private final RestTemplate restTemplate;
    @Value("${account.url}")
    private String accountUrl;

    public AccountRestClientService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public void readAccount(UUID accountId) {
        String uri = accountUrl + "/{accountId}/";
        this.restTemplate.getForObject(uri, String.class, accountId);
    }
}
