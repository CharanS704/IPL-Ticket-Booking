package in.ipl.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
	private Integer userId;
	
	private String userName;
	
	private String emailId;
	
	private Long phoneNo;
	
	private Integer seatsCount;
	
	private Integer matchId;

	private Double ticketPrice;
	
	private LocalDateTime dateTime;

}
