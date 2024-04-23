package in.ipl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDetails {

	private Integer matchId;

	private Integer seatsCount;

	private Double perTicketPrice;



}
