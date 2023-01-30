package kr.co.jay.session.exception;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class InternalErrorException extends RuntimeException {


	private ErrorModel error;

	public InternalErrorException(){
		ErrorModel error = new ErrorModel();
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		//error.setMessage(messageCode.getMessage());
		error.setError(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
		error.setTimestamp(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").format(LocalDateTime.now(ZoneId.of("Asia/Seoul"))));
		this.error = error;
	}
}