<<<<<<< HEAD
package Bug.Tracking.System.Application.Bug.Tracking.Application.controller;


import Bug.Tracking.System.Application.Bug.Tracking.Application.service.EmailInterfaceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


import jakarta.mail.MessagingException;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/email")
@AllArgsConstructor
public class EmailController extends AbstractController{

    private final EmailInterfaceService emailService;



    @PostMapping("/send")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
        try {
            emailService.sendEmail(to, subject, body);
            return "Email sent successfully!";
        } catch (MessagingException e) {
            return "Failed to send email: " + e.getMessage();
        }
    }
}
=======
package Bug.Tracking.System.Application.Bug.Tracking.Application.controller;


import Bug.Tracking.System.Application.Bug.Tracking.Application.service.bugServiceImpl.EmailServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


import jakarta.mail.MessagingException;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/email")
@AllArgsConstructor
public class EmailController {

    private final EmailServiceImpl emailService;



    @PostMapping("/send")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
        try {
            emailService.sendEmail(to, subject, body);
            return "Email sent successfully!";
        } catch (MessagingException e) {
            return "Failed to send email: " + e.getMessage();
        }
    }
}
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
