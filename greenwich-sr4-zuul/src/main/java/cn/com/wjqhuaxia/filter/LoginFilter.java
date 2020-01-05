package cn.com.wjqhuaxia.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import cn.com.wjqhuaxia.util.ThreadSessionUtil;

public class LoginFilter extends ZuulFilter{
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Object run() throws ZuulException {
		try {
			RequestContext ctx = RequestContext.getCurrentContext();  
	        HttpServletRequest request = ctx.getRequest();  
	        logger.info(String.format("ZuulFilter: %s AccessUserNameFilter request to %s", request.getMethod(), request.getRequestURL().toString())); 
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        if(authentication != null){
	        	// 获取spring security中的当前登陆用户信息
	        	User user = (User)authentication.getPrincipal();
	        	logger.info("ZuulFilter user: {}",user);
	        	cn.com.wjqhuaxia.mode.dto.User realUser = new cn.com.wjqhuaxia.mode.dto.User();
	        	ThreadSessionUtil.set(realUser);
	        }
		} catch (Exception e) {
			logger.error("ZuulFilter run error! e: {}", e);
		}
        return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

}
