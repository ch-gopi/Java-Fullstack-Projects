package Bug.Tracking.System.Application.Bug.Tracking.Application.service;

import jakarta.mail.MessagingException;

public interface EmailInterfaceService {
        void sendEmail(String to, String subject, String body) throws MessagingException; }
