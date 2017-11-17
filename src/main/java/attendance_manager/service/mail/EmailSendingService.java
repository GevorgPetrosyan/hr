package attendance_manager.service.mail;

public interface EmailSendingService {

    void sendEmail(final String subject, final String message, final String recipient);

}
