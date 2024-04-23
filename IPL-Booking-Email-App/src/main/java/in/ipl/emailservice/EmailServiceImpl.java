package in.ipl.emailservice;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements IEmailService {

	@Autowired
	private JavaMailSender sender;

	@Value("${spring.mail.username}")
	private String fromEmail;

	@Override
	public void triggerConfirmationEmail(String msg, String toEmail) throws MessagingException {

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		String emailMessage = msg + " on " + LocalDate.now() + ".";

		helper.setFrom(fromEmail);
		helper.setCc(toEmail);
		helper.setSubject("IPL ticket booking status");
		helper.setSentDate(new Date());
		helper.setText(emailMessage);
		helper.addAttachment("ipl.JPG", new ClassPathResource("ipl.JPG"));
		sender.send(message);

	}

	@Override
	public void triggerCancellationEmail(String msg, String toEmail) throws MessagingException {

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		helper.setFrom(fromEmail);
		helper.setCc(toEmail);
		helper.setSubject("IPL Ticket Refund Mail");
		helper.setSentDate(new Date());
		helper.setText(msg);
		helper.addAttachment("cancelled.JPG", new ClassPathResource("cancelled.JPG"));

		sender.send(message);

	}

}
