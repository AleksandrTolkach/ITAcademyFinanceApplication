package by.tolkach.mailScheduler.service.rest;

import by.tolkach.mailScheduler.dto.scheduledMail.Mail;
import by.tolkach.mailScheduler.dto.scheduledMail.Param;
import by.tolkach.mailScheduler.dto.scheduledMail.ReportType;
import by.tolkach.mailScheduler.service.rest.api.IMailRestClientService;
import by.tolkach.mailScheduler.service.rest.object.MailParamWrapperRestObject;
import by.tolkach.mailScheduler.service.rest.object.MailRestObject;
import by.tolkach.mailScheduler.service.rest.object.ParamRestObject;
import by.tolkach.mailScheduler.service.rest.object.converter.IRestObjectConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class MailRestClientService implements IMailRestClientService {

    private final RestTemplate restTemplate;
    private final IRestObjectConverter<Mail, MailRestObject> mailRestObjectConverter;
    private final IRestObjectConverter<Param, ParamRestObject> paramRestObjectConverter;
    private @Value("${mail.url}") String mailUrl;

    public MailRestClientService(RestTemplateBuilder restTemplateBuilder,
                                 IRestObjectConverter<Mail, MailRestObject> mailRestObjectConverter,
                                 IRestObjectConverter<Param, ParamRestObject> paramRestObjectConverter) {
        this.restTemplate = restTemplateBuilder.build();
        this.mailRestObjectConverter = mailRestObjectConverter;
        this.paramRestObjectConverter = paramRestObjectConverter;
    }

    @Override
    public String create(Mail mail, Param param, ReportType reportType) {
        String uri = mailUrl + "/{type}";

        HttpHeaders headers = this.createHeader();

        MailRestObject mailRestObject = this.mailRestObjectConverter.toRestObject(mail);
        ParamRestObject paramRestObject = this.paramRestObjectConverter.toRestObject(param);
        MailParamWrapperRestObject wrapper = MailParamWrapperRestObject.wrap(mailRestObject, paramRestObject);
        HttpEntity<MailParamWrapperRestObject> entity = new HttpEntity<>(wrapper, headers);

        return this.restTemplate.postForObject(uri, entity, String.class, reportType);
    }

    private HttpHeaders createHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }
}
