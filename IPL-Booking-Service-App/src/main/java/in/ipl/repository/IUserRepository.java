package in.ipl.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import in.ipl.entity.Customer;

public interface IUserRepository extends JpaRepository<Customer, Integer> {

	
	@Modifying
	@Query("INSERT INTO Customer(userName,emailId,phoneNo,seatsCount,matchId,ticketPrice,dateTime) values(:userName,:emailId,:phoneNo,:seatsCount,:matchId,:ticketPrice,:dateTime)")
	public int insertUser(String userName, String emailId,Long phoneNo, Integer seatsCount, Integer matchId, Double ticketPrice, LocalDateTime dateTime);

	@Modifying
	@Query("UPDATE Customer SET seatsCount=:seatsCount, ticketPrice=:ticketPrice  WHERE userId=:userId")
	public int updatePriceByCountry(Integer userId, Integer seatsCount, Double ticketPrice);
	
}
