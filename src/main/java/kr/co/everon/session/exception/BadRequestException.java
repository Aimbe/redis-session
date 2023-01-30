package kr.co.everon.session.exception;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class BadRequestException extends RuntimeException {

	private ErrorModel error;

	public BadRequestException() {
		ErrorModel error = new ErrorModel();
		error.setStatus(400);
		error.setMessage("연결되어 있지 않은 충전기입니다. ");
		error.setError(String.valueOf(HttpStatus.BAD_REQUEST));
		error.setTimestamp(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").format(LocalDateTime.now(ZoneId.of("Asia/Seoul"))));
		this.error = error;
	}
}