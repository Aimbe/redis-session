package kr.co.jay.session.repository;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("session")
@Data
public class RedisSession implements Serializable {
	@Id
	private String clientId;
	private String address;
	private LocalDateTime date = LocalDateTime.now();

}
