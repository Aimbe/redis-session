package kr.co.everon.session.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@EqualsAndHashCode(callSuper = false)
@ResponseStatus(HttpStatus.NOT_FOUND)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotFoundException extends RuntimeException {

	private ErrorModel error;

	public NotFoundException(String clientId) {
		ErrorModel error = new ErrorModel();
		error.setStatus(404);
		error.setMessage("해당 충전기를 찾을 수 없습니다.  " + clientId);
		error.setError(String.valueOf(HttpStatus.NOT_FOUND));
		error.setTimestamp(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").format(LocalDateTime.now(ZoneId.of("Asia/Seoul"))));
		this.error = error;
	}

}