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
public class BadRequestException extends RuntimeException {

	private ErrorModel error;

	public BadRequestException() {
		ErrorModel error = new ErrorModel();
		error.setStatus(400);
		error.setMessage("연결되어 있지 않은 상태입니다. ");
		error.setError(String.valueOf(HttpStatus.BAD_REQUEST));
		error.setTimestamp(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").format(LocalDateTime.now(ZoneId.of("Asia/Seoul"))));
		this.error = error;
	}
}