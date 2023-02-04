package kr.co.jay.session.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import kr.co.jay.session.repository.RedisSession;
import kr.co.jay.session.repository.RedisSessionRepository;
import kr.co.jay.session.service.RedisSessionService;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RedisController {

	private final RedisTemplate redisTemplate;

	private final RedisSessionRepository redisSessionRepository;
	private final RedisSessionService redisSessionService;


/*순서는
* 1. handshake 할때마다 인서트
* 2. 여기로 요청 날라오면 , clientId로 세션 찾음 그리고 해당 ip로 보냄 */



	@PostMapping("/save-session")
	public RedisSession save(@RequestBody RedisSession redisSession){
		return redisSessionService.save(redisSession);
	}

	@PostMapping("/send")
	public JSONObject send(@RequestParam String id,@RequestBody Map<String, Object> map){

		return redisSessionService.callById(id,map);

	}
	@GetMapping("/find/{chargerId}")
	public RedisSession FindOne(@PathVariable String chargerId) {
		return redisSessionRepository.findSessionById(chargerId);

	}

	@GetMapping("/allChargers")
	public List<RedisSession> FindAll(){
		return redisSessionRepository.finalAll();
	}


    @PostMapping("/save")
    public void save() {
        String clientId;
        String ip;
        String ip1 = "111.222.333.444";
        String ip2 = "777.888.999.666";
        LocalDateTime currentDateTime;

        RedisClient redisClient = RedisClient.create("redis://pw@localhost.com/");
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> syncCommands = connection.sync();
        try {
            for (int i = 1; i < 3001; i++) {
                if (i % 2 == 0) ip = ip2;
                else ip = ip1;
                clientId = "client-" + String.format("%05d", i);
                currentDateTime = LocalDateTime.now();

                syncCommands.hset(clientId, "ipAddress", ip);
                syncCommands.hset(clientId, "createdAt", currentDateTime.toString());
            }
        } catch (Exception e){
            System.out.println("e = " + e);
        } finally {
            connection.close();
        }
    }

    @GetMapping("")
    public String  FindClientId(@PathVariable String clientId, @PathVariable String ipAddress) {
        RedisClient redisClient = RedisClient.create("");
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> syncCommands = connection.sync();

        return syncCommands.hget(clientId, ipAddress);
    }

}
