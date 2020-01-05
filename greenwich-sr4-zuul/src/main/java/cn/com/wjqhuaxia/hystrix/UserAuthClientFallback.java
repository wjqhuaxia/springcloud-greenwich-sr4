package cn.com.wjqhuaxia.hystrix;

import org.springframework.stereotype.Component;

import cn.com.wjqhuaxia.clent.UserAuthClient;
import cn.com.wjqhuaxia.mode.dto.User;

@Component
public class UserAuthClientFallback implements UserAuthClient{

	@Override
	public User selectByUsername(String userName) {
		return null;
	}
}
