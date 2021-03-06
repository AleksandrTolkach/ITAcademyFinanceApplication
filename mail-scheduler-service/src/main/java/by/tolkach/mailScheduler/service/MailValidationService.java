package by.tolkach.mailScheduler.service;

import by.tolkach.mailScheduler.dto.exception.MultipleErrorsException;
import by.tolkach.mailScheduler.dto.exception.NotFoundException;
import by.tolkach.mailScheduler.dto.exception.SingleError;
import by.tolkach.mailScheduler.dto.scheduledMail.Mail;
import by.tolkach.mailScheduler.service.api.IValidationService;
import org.springframework.stereotype.Service;

@Service
public class MailValidationService implements IValidationService<Mail> {
    @Override
    public Mail validate(Mail mail) {
        MultipleErrorsException validationException = new MultipleErrorsException();
        if (mail == null) {
            throw new NotFoundException("Необходимо передать объект письма.");
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
