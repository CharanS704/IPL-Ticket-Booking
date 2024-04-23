package in.ipl.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ipl.entity.Customer;
import in.ipl.service.IUserRegisterService;

@RestController
@RequestMapping("/api/ipl")
public class UserController {

	@Autowired
	private IUserRegisterService userService;

	@GetMapping("/fetch/{userId}")
	public ResponseEntity<?> fetchCustomerById(@PathVariable Integer userId) {
		Customer customer;
		customer = userService.fetchUser(userId);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<String> bookTicket(@RequestBody Customer customer) {
		String msg;
		msg = userService.bookTicket(customer);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@PatchMapping("/pmodify/{userId}/{seatsCount}/{totalPrice}")
	public ResponseEntity<String> updateTicket(@PathVariable Integer userId, @PathVariable Integer seatsCount,
			@PathVariable Double totalPrice) {
//		String msg;
//		try {
//			msg = userService.updateTicketByUserId(userId, seatsCount, totalPrice);
//		} catch (Exception e) {
//			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
//		}
//		return new ResponseEntity<String>(msg, HttpStatus.OK);
		return null;
	}

	@DeleteMapping("/cancel/{userId}")
	public ResponseEntity<String> deleteTicket(@PathVariable Integer userId) {
		String msg;
		msg = userService.cancelTicketByUserId(userId);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

}
