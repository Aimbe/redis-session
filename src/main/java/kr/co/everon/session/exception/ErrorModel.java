package kr.co.everon.session.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorModel {

	private String timestamp;
	private String message;
	private Integer status;
	private String error;
	private Integer code;
}
