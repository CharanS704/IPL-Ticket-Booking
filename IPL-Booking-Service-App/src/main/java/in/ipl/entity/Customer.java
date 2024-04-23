package in.ipl.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Integer userId;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	@Column(name="PHONE_NO")
	private Long phoneNo;
	
	@Column(name = "SEATS_COUNT")
	private Integer seatsCount;
	
	@Column(name = "MATCH_ID")
	private Integer matchId;

	@Column(name = "TICKET_PRICE")
	private Double ticketPrice;
	
	@Column(name="UPDATE_TS")
	private LocalDateTime dateTime;

}
