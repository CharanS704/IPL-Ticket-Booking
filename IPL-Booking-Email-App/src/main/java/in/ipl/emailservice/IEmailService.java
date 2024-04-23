package in.ipl.emailservice;

import jakarta.mail.MessagingException;

public interface IEmailService {
	
	public void triggerConfirmationEmail(String msg, String toEmail) throws MessagingException;
	public void triggerCancellationEmail(String msg, String toEmail) throws MessagingException;

}
