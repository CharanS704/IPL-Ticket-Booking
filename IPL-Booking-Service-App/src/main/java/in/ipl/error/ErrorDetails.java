package in.ipl.error;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetails {
	
	String message;
	String status;
	LocalDateTime dateTime;

}
