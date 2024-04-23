package in.ipl.client;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import in.ipl.entity.Customer;

@RibbonClient(name = "IPL-BOOKING-REGISTRATION-SERVICE")
@FeignClient(name = "IPL-BOOKING-REGISTRATION-SERVICE")
public interface IBookTicketClient {

	@GetMapping("/api/ipl/fetch/{userId}")
	public ResponseEntity<?> fetchCustomerById(@PathVariable Integer userId);

	@PostMapping("/api/ipl/register")
	public ResponseEntity<String> bookTicket(@RequestBody Customer customer);

	@DeleteMapping("/api/ipl/cancel/{userId}")
	public ResponseEntity<String> deleteTicket(@PathVariable Integer userId);

}
