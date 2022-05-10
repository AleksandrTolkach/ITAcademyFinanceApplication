package by.tolkach.schedulerAccount.service.rest;

import by.tolkach.schedulerAccount.dto.Currency;
import by.tolkach.schedulerAccount.service.rest.api.ICurrencyRestClientService;
import by.tolkach.schedulerAccount.service.rest.object.CurrencyRestObject;
import by.tolkach.schedulerAccount.service.rest.object.converter.IRestObjectConverter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class CurrencyRestClientService implements ICurrencyRestClientService {

    private final RestTemplate restTemplate;
    private final IRestObjectConverter<Currency, CurrencyRestObject> currencyRestObjectConverter;

    public CurrencyRestClientService(RestTemplateBuilder restTemplateBuilder,
                                     IRestObjectConverter<Currency, CurrencyRestObject> currencyRestObjectConverter) {
        this.restTemplate = restTemplateBuilder.build();
        this.currencyRestObjectConverter = currencyRestObjectConverter;
    }

    @Override
    public Currency read(UUID currencyId) {
        String url = "http://localhost:8084/classifier/currency/{uuid}";
        CurrencyRestObject currencyRestObject = this.restTemplate.getForObject(url,
                CurrencyRestObject.class, currencyId);
        return this.currencyRestObjectConverter.toDto(currencyRestObject);
    }
}
