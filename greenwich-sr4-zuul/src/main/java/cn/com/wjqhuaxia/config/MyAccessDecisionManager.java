package cn.com.wjqhuaxia.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import java.util.Collection;
import java.util.Iterator;

/**
 * 自定义权限决策管理器
 * 需要实现AccessDecisionManager 的 void decide(Authentication auth, Object object, Collection<ConfigAttribute> cas) 
 * 方法，在上面的过滤器中，我们已经得到了访问此url需要的权限；那么，decide方法，先查询此用户当前拥有的权限，然后与上面过滤器核查出来的权限列表作对比，
 * 以此判断此用户是否具有这个访问权限，决定去留！所以顾名思义为权限决策器
 * @author wjqhuaxia
 *
 */
@Service
public class MyAccessDecisionManager implements AccessDecisionManager {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * decide 方法是判定是否拥有权限的决策方法，authentication是CustomUserService
	 * 中循环添加到 GrantedAuthority 对象中的权限信息集合,object 包含客户端发起的请求的requset信息，
	 * 可转换为 HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
	 * configAttributes为MyFilterInvocationSecurityMetadataSource的getAttributes(Object object)
	 * 这个方法返回的结果.
	 * ----------------------------------------------------------------------------------------
	 * 取当前用户的权限与这次请求的这个url需要的权限作对比，决定是否放行
     * authentication 包含了当前的用户信息，包括拥有的权限,即之前UserDetailsService登录时候存储的用户对象
     * object 就是FilterInvocation对象，可以得到request等web资源。
     * configAttributes 是本次访问需要的权限。即上一步的 MyFilterInvocationSecurityMetadataSource 中查询核对得到的权限列表
	 * 
	 */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if(null== configAttributes || configAttributes.size() <=0) {
        	// 本次訪問不需要權限
            return;
        }
        ConfigAttribute c;
        String needRole;
        // 当前请求需要的权限
        logger.info("decide configAttributes: {}",JSONObject.toJSONString(configAttributes));
        for(Iterator<ConfigAttribute> iter = configAttributes.iterator(); iter.hasNext(); ) {
            c = iter.next();
            needRole = c.getAttribute();
            // authentication.getAuthorities()為当前用户所具有的权限 
            logger.info("decide authorities: {}",JSONObject.toJSONString(authentication.getAuthorities()));
            for(GrantedAuthority ga : authentication.getAuthorities()) {//authentication 为在注释1 中循环添加到 GrantedAuthority 对象中的权限信息集合
                if(needRole.trim().equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("no right");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}

