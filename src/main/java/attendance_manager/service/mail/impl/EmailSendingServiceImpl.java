package attendance_manager.service.mail.impl;

import attendance_manager.service.mail.EmailSendingService;
import com.google.common.collect.Lists;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;

@Service
public class EmailSendingServiceImpl implements EmailSendingService {

    private final EmailService emailService;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Value("${mail.sender.name}")
    private String senderName;

    @Autowired
    public EmailSendingServiceImpl(final EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void sendEmail(final String subject, final String message, final String recipient) {
        final Email email;
        try {
            email = DefaultEmail.builder()
                    .from(new InternetAddress(senderEmail, senderName))
                    .to(Lists.newArrayList(new InternetAddress(recipient, null)))
                    .subject(subject)
                    .body(message)
                    .encoding("UTF-8").build();
            emailService.send(email);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
