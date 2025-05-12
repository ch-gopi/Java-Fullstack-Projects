package Bug.Tracking.System.Application.Bug.Tracking.Application.service.bugServiceImpl;


import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import jakarta.mail.internet.MimeMessage;
import jakarta.mail.MessagingException;

@Service
@AllArgsConstructor
public class EmailServiceImpl {

    private final JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true); // HTML format

        mailSender.send(message);
    }
}
