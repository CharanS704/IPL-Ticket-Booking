package in.ipl.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import in.ipl.entity.TicketDetails;

public interface ITicketRepository extends JpaRepository<TicketDetails, Integer> {
		
	@Modifying
	@Query("UPDATE TicketDetails SET seatsCount=:seatsCount WHERE matchId=:matchId")
	public int updateSeatsCountByMatchId(Integer seatsCount, Integer matchId);	
	

	
	

}
