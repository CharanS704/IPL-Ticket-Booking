package in.ipl.service;

import java.util.List;

import in.ipl.entity.TicketDetails;

public interface ITicketDetailsService {
	
	public String insertTicket(TicketDetails ticketDetails);
	
	public List<TicketDetails> getTicketDetails();
	
	public String updateTicketDetails(TicketDetails ticketDetails);
	
	public String deleteTicket(Integer matchId);
	

}
