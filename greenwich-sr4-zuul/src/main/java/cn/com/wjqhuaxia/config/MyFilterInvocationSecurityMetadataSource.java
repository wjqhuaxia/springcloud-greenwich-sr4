package cn.com.wjqhuaxia.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;
/**
 * 自定义权限过滤器
 * FilterInvocationSecurityMetadataSource（权限资源过滤器接口）继承了 SecurityMetadataSource（权限资源接口）
 * Spring Security是通过SecurityMetadataSource来加载访问时所需要的具体权限；Metadata是元数据的意思。
 * 自定义权限资源过滤器，实现动态的权限验证
 * 它的主要责任就是当访问一个url时，返回这个url所需要的访问权限
 * @author wjqhuaxia
 *
 */
@Service
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	
	//@Autowired
	//private PermissionMapper permissionMapper;

	private HashMap<String, Collection<ConfigAttribute>> map = null;

	/**
	 * 加载权限表中所有权限
	 * 当前示例代码只做了认证，默认请求都需ROLE_LOGIN权限
	 */
	public void loadResourceDefine() {
		map = new HashMap<String, Collection<ConfigAttribute>>();

		/*log.info("loadResourceDefine permissionMapper: " +permissionMapper);
		List<Permission> permissions = permissionMapper.findAll();
		log.info("loadResourceDefine permissions: " +permissions);
		for (Permission permission : permissions) {
			if(StringUtils.isEmpty(permission.getPermissionname())){
				continue;
			}
			if(StringUtils.isEmpty(permission.getUrl())){
				continue;
			}
			ConfigAttribute cfg = new SecurityConfig(permission.getPermissionname());
			List<ConfigAttribute> list = new ArrayList<>();
			list.add(cfg);
			// TODO:如果一个url对应多个权限，这里有问题
			map.put(permission.getUrl(), list);
		}*/

	}

	/**
	 * 此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法， 用来判定用户
	 * 是否有此权限。如果不在权限表中则放行。
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		if (map == null) {
			loadResourceDefine();
		}
		// object 中包含用户请求的request的信息
		HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
		if(MapUtils.isEmpty(map)){
			return SecurityConfig.createList("ROLE_LOGIN");   
		}
		for (Entry<String, Collection<ConfigAttribute>> entry : map.entrySet()) {
			String url = entry.getKey();
			if (new AntPathRequestMatcher(url).matches(request)) {
				return map.get(url);
			}
		}
		/**
         * @Description: 如果本方法返回null的话，意味着当前这个请求不需要任何角色就能访问
         * 此处做逻辑控制，如果没有匹配上的，返回一个默认具体权限，防止漏缺资源配置
         **/
        //log.info("当前访问路径是{},这个url所需要的访问权限是{}", request.getRequestURL(), "ROLE_LOGIN");
        return SecurityConfig.createList("ROLE_LOGIN");//SecurityConfig.createList("ROLE_LOGIN");
	}
	/**
	 * 此处方法如果做了实现，返回了定义的权限资源列表，
     * Spring Security会在启动时校验每个ConfigAttribute是否配置正确，
     * 如果不需要校验，这里实现方法，方法体直接返回null即可
	 */
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}
	/**
	 * 方法返回类对象是否支持校验，
     * web项目一般使用FilterInvocation来判断，或者直接返回true
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
	
}
