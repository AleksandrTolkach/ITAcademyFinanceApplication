package by.tolkach.mail.service.rest;

import by.tolkach.mail.dto.Param;
import by.tolkach.mail.dto.ReportType;
import by.tolkach.mail.service.rest.api.IReportRestClientService;
import by.tolkach.mail.service.rest.object.ParamRestObject;
import by.tolkach.mail.service.rest.object.converter.IRestObjectConverter;
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
    private final IRestObjectConverter<Param, ParamRestObject> restObjectConverter;

    public ReportRestClientService(RestTemplateBuilder restTemplateBuilder,
                                   IRestObjectConverter<Param, ParamRestObject> restObjectConverter) {
        this.restTemplate = restTemplateBuilder.build();
        this.restObjectConverter = restObjectConverter;
    }

    @Override
    public byte[] create(Param param, ReportType reportType) {
        String url = "http://localhost:8085/report/{type}/mail";

        HttpHeaders headers = this.createHeader();

        ParamRestObject paramRestObject = this.restObjectConverter.toRestObject(param);
        HttpEntity<ParamRestObject> entity = new HttpEntity<>(paramRestObject, headers);

        return this.restTemplate.postForObject(url, entity, byte[].class, reportType);
    }

    @Override
    public byte[] export(UUID reportId) {
        String url = "http://localhost:8085/report/{uuid}/export";
        return this.restTemplate.getForObject(url, byte[].class, reportId);
    }

    private org.springframework.http.HttpHeaders createHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }
}
