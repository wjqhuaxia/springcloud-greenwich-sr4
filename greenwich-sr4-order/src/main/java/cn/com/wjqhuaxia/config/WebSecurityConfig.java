/*package cn.com.wjqhuaxia.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

*//**
 * 安全认证
 * @author wjqhuaxia
 *
 *//*
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		http.csrf().disable(); // 关闭csrf
		http.authorizeRequests().anyRequest().authenticated().and().httpBasic(); // 开启认证
	}
}*/