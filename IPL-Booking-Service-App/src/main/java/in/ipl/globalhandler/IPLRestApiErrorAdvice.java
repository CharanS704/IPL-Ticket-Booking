package in.ipl.globalhandler;

import java.time.LocalDateTime;

import org.apache.http.protocol.HTTP;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.ipl.error.ErrorDetails;
import in.ipl.exceptions.CustomerNotFoundException;
import in.ipl.exceptions.TicketNotFoundException;

@RestControllerAdvice
public class IPLRestApiErrorAdvice {

	@ExceptionHandler(value = TicketNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleTicketNotFoundException(TicketNotFoundException te) {
		ErrorDetails errorDetails = new ErrorDetails(te.getMessage(), "404-Not Found", LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = CustomerNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleCustomerNotFoundException(CustomerNotFoundException ce) {
		ErrorDetails errorDetails = new ErrorDetails(ce.getMessage(), "404-Not Found", LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleAllException(Exception e) {
		ErrorDetails errorDetails = new ErrorDetails(e.getMessage(), "Problem is execution..", LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
