package in.ipl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ipl.entity.TicketDetails;
import in.ipl.exceptions.TicketNotFoundException;
import in.ipl.repository.ITicketRepository;

@Service
public class TicketDetailsServiceImpl implements ITicketDetailsService {

	@Autowired
	private ITicketRepository ticketRepo;

	@Override
	public String insertTicket(TicketDetails ticketDetails) {
		int matchId = ticketRepo.save(ticketDetails).getMatchId();
		return "Successfully added ticket for match Id: " + matchId;
	}

	@Override
	public List<TicketDetails> getTicketDetails() {
		List<TicketDetails> tickets = ticketRepo.findAll();
		if (tickets.isEmpty()) {
			throw new TicketNotFoundException("No tickets found! Please update new tickets");
		} else {
			return tickets;
		}
	}

	@Override
	public String updateTicketDetails(TicketDetails ticketDetails) {
		int matchId = ticketRepo.save(ticketDetails).getMatchId();
		return "Updated the ticket details for matchId: " + matchId;
	}

	@Override
	public String deleteTicket(Integer matchId) {
		Optional<TicketDetails> optional = ticketRepo.findById(matchId);
		if (optional.isPresent()) {
			ticketRepo.deleteById(matchId);
			return "Deleted tickets for match Id: " + matchId;
		} else {
			throw new TicketNotFoundException("No tickets found for deletion with match Id: " + matchId);
		}
	}

}
