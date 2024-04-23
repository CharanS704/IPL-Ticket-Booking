package in.ipl.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ipl.client.IBookTicketClient;
import in.ipl.emailservice.IEmailService;
import in.ipl.entity.Customer;
import jakarta.mail.MessagingException;

@RestController
public class BookTicketRestController {

	@Autowired
	private IBookTicketClient client;

	@Autowired
	private IEmailService emailService;

	@GetMapping("/iplinfo/fetch/{userId}")
	public ResponseEntity<?> getCustomerBy(@PathVariable Integer userId) {

		ResponseEntity<?> responseEntity = null;

		try {
			responseEntity = client.fetchCustomerById(userId);
			int statusCode = responseEntity.getStatusCode().value();

			if (statusCode == 200) {
				Object customer = responseEntity.getBody();
				responseEntity = new ResponseEntity<Object>(customer, HttpStatus.OK);
			}
		} catch (Exception e) {
			responseEntity = new ResponseEntity<String>("Error while fetching customer details",
					HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

	@PostMapping("/bookticket")
	public ResponseEntity<String> bookTicket(@RequestBody Customer customer) throws MessagingException {
		ResponseEntity<String> responseEntity;
		try {
			responseEntity = client.bookTicket(customer);
			int statusCode = responseEntity.getStatusCode().value();

			if (statusCode == 200) {
				String msg = (String) responseEntity.getBody();
				responseEntity = new ResponseEntity<String>(msg, HttpStatus.OK);
				System.out.println(msg.substring(0, 18));
				if (msg.substring(0, 19).equals("Booking successfull")) {
					emailService.triggerConfirmationEmail(msg, customer.getEmailId());
				}
			}
		} catch (Exception e) {
			responseEntity = new ResponseEntity<String>("Error while booking ticket", HttpStatus.OK);
		}

		return responseEntity;
	}

	@DeleteMapping("/cancelticket/{userId}")
	public ResponseEntity<String> cancelTicket(@PathVariable Integer userId) {
		ResponseEntity<String> responseEntity;
		String userName="";
		String userAmount="";
		String userEmailId="";

		try {
			responseEntity = client.deleteTicket(userId);
			int statusCode = responseEntity.getStatusCode().value();

			if (statusCode == 200) {
				String msg = (String) responseEntity.getBody();
				responseEntity = new ResponseEntity<String>(msg, HttpStatus.OK);
				if (msg.contains("Booking cancelletion successfull")) {
					String[] userMessage = msg.split(" ");
					for(int i=0; i<=14;i++) {
						userName=userMessage[5];
						userAmount=userMessage[11];
						userEmailId= userMessage[14];
					}
					String emailMsg = "Booking cancellation successfull for user "+userName+" and amount "+userAmount+" will be refunded within 6-7 business days.";
					emailService.triggerCancellationEmail(emailMsg, userEmailId);
				}
			}
		} catch (Exception e) {
			responseEntity = new ResponseEntity<String>("Error while cancelling ticket", HttpStatus.OK);
		}

		return responseEntity;

	}

}
