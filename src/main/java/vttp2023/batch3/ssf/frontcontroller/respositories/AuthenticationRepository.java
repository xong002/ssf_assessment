package vttp2023.batch3.ssf.frontcontroller.respositories;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthenticationRepository {

	@Autowired
	RedisTemplate<String, String> template;

	public void disableLogin(String username) {
		if (template.hasKey(username)) {
			Integer loginAttempts = Integer.parseInt(template.opsForValue().get(username)) + 1;
			if (loginAttempts >= 3) {
				// template.opsForValue().set(username, String.valueOf(3), Duration.ofSeconds(10));
				template.expire(username,Duration.ofSeconds(10));
			}
			template.opsForValue().set(username, loginAttempts.toString());
		} else
			template.opsForValue().set(username, String.valueOf(1));
	}

	public boolean checkDisabled(String username) {
		if (template.hasKey(username)) {
			if (Integer.parseInt(template.opsForValue().get(username)) >= 3)
				return true;
		}
		return false;
	}

	// TODO Task 5
	// Use this class to implement CRUD operations on Redis
	public String getResource(String resource){
		//get protected resource from Redis
		return resource;
	}
}
