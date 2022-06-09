package by.tolkach.mail.service.rest;

import by.tolkach.mail.dto.exception.NotFoundException;
import by.tolkach.mail.service.rest.api.IAccountRestClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
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
        try {
            this.restTemplate.getForObject(uri, String.class, accountId);
        } catch (HttpServerErrorException e) {
            throw new NotFoundException("Счета с Id + " + accountId + " не существует.");
        }
    }
}
