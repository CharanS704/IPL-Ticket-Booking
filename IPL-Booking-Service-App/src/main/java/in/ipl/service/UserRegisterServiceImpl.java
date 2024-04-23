package in.ipl.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ipl.entity.Customer;
import in.ipl.entity.TicketDetails;
import in.ipl.exceptions.CustomerNotFoundException;
import in.ipl.exceptions.TicketNotFoundException;
import in.ipl.repository.ITicketRepository;
import in.ipl.repository.IUserRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserRegisterServiceImpl implements IUserRegisterService {

	@Autowired
	private ITicketRepository ticketRepo;

	@Autowired
	private IUserRepository userRepo;

	@Override
	public String bookTicket(Customer customer) {
		Optional<TicketDetails> optional = ticketRepo.findById(customer.getMatchId());
		if (optional.isPresent()) {
			if (optional.get().getSeatsCount() >= customer.getSeatsCount()) {
				if (customer.getTicketPrice() == (customer.getSeatsCount() * optional.get().getPerTicketPrice())) {
					int affectedUsers = userRepo.insertUser(customer.getUserName(), customer.getEmailId(),
							customer.getPhoneNo(), customer.getSeatsCount(), customer.getMatchId(),
							customer.getTicketPrice(), LocalDateTime.now());
					int remainingSeats = optional.get().getSeatsCount() - customer.getSeatsCount();
					TicketDetails newTicketDetails = new TicketDetails(optional.get().getMatchId(),remainingSeats,optional.get().getPerTicketPrice());
					ticketRepo.save(newTicketDetails);
//					int affectedMatches = ticketRepo.updateSeatsCountByMatchId(optional.get().getMatchId(),
//							remainingSeats);
					return "Booking successfull for user "+customer.getUserName()+" for match Id "+customer.getMatchId()+" and amount paid is "+customer.getTicketPrice();

				} else {
					return "Please provide ticket Price: " + customer.getSeatsCount() * optional.get().getPerTicketPrice()+" Rs";
				}
			} else {
				return "Requested number of seats not available!";
			}
		} else {
			return "Seats cannot be booked for the requested match, kindly try again by providing valid match id..";
		}
	}

	@Override
	public Customer fetchUser(Integer userId) {
		Optional<Customer> optional = userRepo.findById(userId);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new CustomerNotFoundException("No customer records found for the requested Id: " + userId);
		}
	}

	@Override
	public String updateTicketByUserId(Integer userId, Integer matchId, Integer seatsCount, Double totalPrice) {
		Customer customer = userRepo.findById(userId).orElseThrow(
				() -> new CustomerNotFoundException("No customer records for the requested Id: " + userId));
		if(ticketRepo.findById(matchId).isPresent()) {
			int affectedUserRecords = userRepo.updatePriceByCountry(userId, seatsCount, totalPrice);
		}else {
			throw new TicketNotFoundException("All the tickets are sold out");
		}
	
		
		return null;
	}

	@Override
	public String cancelTicketByUserId(Integer userId) {
		Customer customer = userRepo.findById(userId).orElseThrow(
				() -> new CustomerNotFoundException("No ticket history found for cancellation for the requested Id: " + userId));
		userRepo.deleteById(userId);
		Integer updatedSeatsCount = ticketRepo.findById(customer.getMatchId()).get().getSeatsCount() + customer.getSeatsCount();
		ticketRepo.updateSeatsCountByMatchId(updatedSeatsCount, customer.getMatchId());
		return "Booking cancelletion successfull for user "+customer.getUserName()+" and amount to be refunded " +customer.getTicketPrice()+" and emailId "+customer.getEmailId();
	}

}
