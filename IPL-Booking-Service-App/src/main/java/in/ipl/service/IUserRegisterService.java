package in.ipl.service;

import in.ipl.entity.Customer;

public interface IUserRegisterService {

	public String bookTicket(Customer customer);

	public Customer fetchUser(Integer userId);

	public String updateTicketByUserId(Integer userId, Integer matchId, Integer seatsCount, Double totalPrice);

	public String cancelTicketByUserId(Integer userId);

}
