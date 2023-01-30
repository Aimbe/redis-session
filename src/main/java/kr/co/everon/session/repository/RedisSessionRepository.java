package kr.co.everon.session.repository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisSessionRepository {

	private final RedisTemplate redisTemplate;

	private final String key = "session";

	public RedisSession save(RedisSession redisSession){
		redisTemplate.opsForHash().put(key,redisSession.getClientId(),redisSession);
		return redisSession;
	}

	public List<RedisSession> finalAll(){
		return redisTemplate.opsForHash().values(key);
	}

	public RedisSession findSessionById(String clientId){
		return (RedisSession) redisTemplate.opsForHash().get(key,clientId);
	}

	public String findSession2ById(String clientId){
		return (String) redisTemplate.opsForHash().get(key,clientId);
	}

	public String deleteProduct(int id){
		return String.valueOf(redisTemplate.opsForHash().delete(key,id));
	}

}
