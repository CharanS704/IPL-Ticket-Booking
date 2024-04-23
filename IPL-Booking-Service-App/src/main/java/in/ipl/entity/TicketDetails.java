package in.ipl.entity;

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
@Table(name = "TICKET_DETAILS")
public class TicketDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MATCH_ID")
	private Integer matchId;

	@Column(name = "SEATS_COUNT")
	private Integer seatsCount;

	@Column(name = "PER_TICKET_PRICE")
	private Double perTicketPrice;



}
