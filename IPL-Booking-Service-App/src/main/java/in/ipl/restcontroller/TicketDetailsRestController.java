package in.ipl.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ipl.entity.TicketDetails;
import in.ipl.service.ITicketDetailsService;

@RestController
@RequestMapping("/api/ticket")
public class TicketDetailsRestController {

	@Autowired
	private ITicketDetailsService ticketService;

	@GetMapping("/fetchAllTickets")
	public ResponseEntity<?> fetchTicketDetails() {
		List<TicketDetails> tickets;


			tickets = ticketService.getTicketDetails();
			return new ResponseEntity<List<TicketDetails>>(tickets, HttpStatus.OK);

	}

	@PostMapping("/insertTicket")
	public ResponseEntity<String> insertTicket(@RequestBody TicketDetails ticketDetails) {
		String msg;

		msg = ticketService.insertTicket(ticketDetails);

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@PutMapping("/updateTicket")
	public ResponseEntity<String> updateTicket(@RequestBody TicketDetails ticketDetails) {
		String msg;

		msg = ticketService.updateTicketDetails(ticketDetails);

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@DeleteMapping("/deleteTicket/{matchId}")
	public ResponseEntity<String> deleteTicket(@PathVariable Integer matchId) {
		String msg;

		msg = ticketService.deleteTicket(matchId);

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
}
