package by.tolkach.mail.service.mail;

import by.tolkach.mail.dto.*;
import by.tolkach.mail.service.mail.api.IMailService;
import by.tolkach.mail.service.mail.api.IPostmanService;
import by.tolkach.mail.service.rest.api.IReportRestClientService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PostmanService implements IPostmanService {

    private final JavaMailSender mailSender;
    private final IMailService mailService;
    private final IReportRestClientService reportRestClientService;

    public PostmanService(JavaMailSender mailSender, IMailService mailService,
                          IReportRestClientService reportRestClientService) {
        this.mailSender = mailSender;
        this.mailService = mailService;
        this.reportRestClientService = reportRestClientService;
    }

    @Override
    public Mail send(Mail mail, Param param, ReportType reportType) {
        byte[] attachment = this.reportRestClientService.create(param, reportType);
        Mail createdMail = this.mailService.create(mail);
        this.sendMessage(createdMail, attachment);
        return createdMail;
    }

    @Override
    public Mail resend(UUID mailId, UUID reportId) {
        Mail mail = this.mailService.read(mailId);
        byte[] attachment = this.reportRestClientService.export(reportId);
        mail.setUuid(UUID.randomUUID());
        mail.setDate(LocalDateTime.now());
        this.sendMessage(mail, attachment);
        this.mailService.create(mail);
        return mail;
    }

    @Override
    public Page<Mail> read(SimplePageable simplePageable) {
        return this.mailService.read(simplePageable);
    }

    private void sendMessage(Mail mail, byte[] attachment) {
        MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setTo(mail.getAddress());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mail.getText());
            ByteArrayDataSource bads = new ByteArrayDataSource(attachment,
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            messageHelper.addAttachment("report.xlsx", bads);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
