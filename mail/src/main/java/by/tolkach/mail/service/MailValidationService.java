package by.tolkach.mail.service;

import by.tolkach.mail.dto.Mail;
import by.tolkach.mail.service.api.IValidationService;
import by.tolkach.mail.service.api.exception.MultipleErrorsException;
import by.tolkach.mail.service.api.exception.NotFoundError;
import by.tolkach.mail.service.api.exception.SingleError;
import org.springframework.stereotype.Service;

@Service
public class MailValidationService implements IValidationService<Mail> {
    @Override
    public Mail validate(Mail mail) {
        MultipleErrorsException validationException = new MultipleErrorsException();
        if (mail == null) {
            throw new NotFoundError("Необходимо передать объект письма.");
        }
        if (mail.getAddress() == null || mail.getAddress().isEmpty()) {
            validationException.add(new SingleError("address","Необходимо указать адресата."));
        }
        if (validationException.getErrorCount() > 0) {
            throw validationException;
        }
        return mail;
    }
}
