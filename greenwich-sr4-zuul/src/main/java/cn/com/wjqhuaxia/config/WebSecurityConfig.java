package cn.com.wjqhuaxia.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import cn.com.wjqhuaxia.service.impl.UserServiceImpl;
/**
 * spring-security权限管理的核心配置
 * @author wjqhuaxia
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) //全局
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserServiceImpl userService;//实现了UserDetailsService接口
    @Autowired
    private MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;//权限过滤器（当前url所需要的访问权限）
    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;//权限决策器
    @Autowired
    private AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;//自定义错误(403)返回数据

    /**
     * 自定义的加密算法
     * @return
     */
    @Bean
    public PasswordEncoder myPasswordEncoder() {
    	return new MyPasswordEncoder(); 
    }
    /**
     *  配置userDetails的数据源，密码加密格式
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(myPasswordEncoder());
    }
    /**
     * 配置放行的资源
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
           .antMatchers("/index.html", "/static/**","/loginPage","/register")
           // 给 swagger 放行；不需要权限能访问的资源
           .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/images/**", "/webjars/**", "/v2/api-docs",
        		   "/configuration/ui", "/configuration/security", "/swagger-ui.html#!");
    }
    
    /**
     * 这段配置，我认为就是配置Security的认证策略, 每个模块配置使用and结尾。
		authorizeRequests()配置路径拦截，表明路径访问所对应的权限，角色，认证信息。
		formLogin()对应表单认证相关的配置
		logout()对应了注销相关的配置
		httpBasic()可以配置basic登录
     */
    /**
     * HttpSecurity包含了原数据（主要是url）
     * 1.通过withObjectPostProcessor将MyFilterInvocationSecurityMetadataSource和MyAccessDecisionManager注入进来
     * 2.此url先被MyFilterInvocationSecurityMetadataSource处理，然后 丢给 MyAccessDecisionManager处理
     * 3.如果不匹配，返回 MyAccessDeniedHandler
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    		// authorizeRequests()配置路径拦截，表明路径访问所对应的权限，角色，认证信息
        	http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
                        o.setAccessDecisionManager(myAccessDecisionManager);
                        return o;
                    }
                })
                .and()
            // formLogin()对应表单认证相关的配置
            .formLogin()
            	//.loginPage("/loginPage.html")
            	.loginProcessingUrl("/login")
            	.usernameParameter("username")
            	.passwordParameter("password")
            	.permitAll()
            .failureHandler(new AuthenticationFailureHandler() {
	            @Override
	            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
	            	logger.error("onAuthenticationFailure error. e: {}", e);
	            	httpServletResponse.setContentType("application/json;charset=utf-8");
	                PrintWriter out = httpServletResponse.getWriter();
	                StringBuffer sb = new StringBuffer();
	                sb.append("{\"status\":\"error\",\"msg\":\"");
	                if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
	                    sb.append("用户名或密码输入错误，登录失败!");
	                } else {
	                    sb.append("登录失败!");
	                }
	                sb.append("\"}");
	                out.write(sb.toString());
	                out.flush();
	                out.close();
	            }
            }).successHandler(new AuthenticationSuccessHandler() {
            @Override
	            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
	                httpServletResponse.setContentType("application/json;charset=utf-8");
	                PrintWriter out = httpServletResponse.getWriter();
	                String s = "{\"status\":\"success\",\"msg\":\"登陆成功\"}";
	                out.write(s);
	                out.flush();
	                out.close();
	            }
            }).and()
            // logout()对应了注销相关的配置
            .logout()
            	.permitAll()
            	.and()
            	.csrf()
            	.disable()
        	.exceptionHandling()
        		.accessDeniedHandler(authenticationAccessDeniedHandler);
    }
}