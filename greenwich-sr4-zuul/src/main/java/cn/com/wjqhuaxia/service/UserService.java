package cn.com.wjqhuaxia.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
	void userRegister(String username,String password);

}
