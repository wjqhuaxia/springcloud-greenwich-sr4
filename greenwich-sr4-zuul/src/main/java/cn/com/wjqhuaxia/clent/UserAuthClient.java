package cn.com.wjqhuaxia.clent;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cn.com.wjqhuaxia.hystrix.UserAuthClientFallback;
import cn.com.wjqhuaxia.mode.dto.User;

/**
 * 
 * @author wjqhuaxia
 *
 */
@FeignClient(name = "user-auth-service", fallback = UserAuthClientFallback.class)
public interface UserAuthClient {

	@GetMapping("/user/auth/user/getUserByUsername/{userName}")
	public User selectByUsername(@PathVariable(name = "userName") String userName);
}
