package by.tolkach.mail.service.mail;

import by.tolkach.mail.dto.Mail;
import by.tolkach.mail.service.mail.api.IPostmanService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

@Service
public class PostmanService implements IPostmanService {

    private final JavaMailSender mailSender;

    public PostmanService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(Mail mail, byte[] attachment) {
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
