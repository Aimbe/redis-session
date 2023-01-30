package kr.co.everon.session.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Slf4j
@RestControllerAdvice
public class SessionExceptionHandler {


	@ExceptionHandler({InternalErrorException.class})
	public ResponseEntity<ErrorModel> InternalErrorException(InternalErrorException ex) {
		return new ResponseEntity<>(ex.getError(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler({BadRequestException.class})
	public ResponseEntity<ErrorModel> handleNotFoundException(BadRequestException ex) {
		return new ResponseEntity<>(ex.getError(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorModel> handleNotFoundException(NotFoundException ex) {
		return new ResponseEntity<>(ex.getError(), HttpStatus.NOT_FOUND);
	}


}
