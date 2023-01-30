package kr.co.everon.session.service;

import java.util.Map;
import kr.co.everon.session.exception.BadRequestException;
import kr.co.everon.session.exception.InternalErrorException;
import kr.co.everon.session.exception.NotFoundException;
import kr.co.everon.session.repository.RedisSession;
import kr.co.everon.session.repository.RedisSessionRepository;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class RedisSessionService {


	private final RedisTemplate redisTemplate;

	private final RedisSessionRepository redisSessionRepository;

	private final WebClient webClient;

	/*순서는
	 * 1. handshake 할때마다 인서트
	 * 2. 여기로 요청 날라오면 , clientId로 세션 찾음 그리고 해당 ip로 보냄 */

	public RedisSession save(RedisSession redisSession){
		redisSessionRepository.save(redisSession);
		return redisSession;
	}

	public JSONObject callById(String clientId, Map<String, Object> resBody){

		String url = redisSessionRepository.findSessionById(clientId).getAddress();
		System.out.println("url = " + url);
		if(url != null) {

			url = "http://" + url;

			var result = webClient.mutate().baseUrl(url).build().post().uri("/send?id=" + clientId)
				.body(Mono.just(resBody), Map.class)
				.retrieve()
				.onStatus(HttpStatusCode::is5xxServerError,
					clientResponse -> Mono.error(BadRequestException::new))
				.bodyToMono(JSONObject.class)
				.block();

			return result;

		}else
			throw new NotFoundException(clientId);

	}

}
