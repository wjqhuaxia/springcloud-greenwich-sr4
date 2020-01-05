package cn.com.wjqhuaxia.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cn.com.wjqhuaxia.clent.UserAuthClient;
import cn.com.wjqhuaxia.mode.dto.User;
import cn.com.wjqhuaxia.util.ThreadSessionUtil;

/**
 * 实现了UserDetailsService接口中的loadUserByUsername方法
 * 执行登录,构建Authentication对象必须的信息,
 * 如果用户不存在，则抛出UsernameNotFoundException异常
 * @author wjqhuaxia
 *
 */
@Service
public class UserServiceImpl implements UserDetailsService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	//@Autowired
	//private PermissionMapper permissionMapper;
	//@Autowired
	//private RoleMapper roleMapper;
	//@Autowired
	///private UserMapper userMapper;
	//@Autowired
	//private PasswordEncoder passwordEncoder;
	@Autowired
	private UserAuthClient UserAuthClient;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = UserAuthClient.selectByUsername(username);
		logger.info("loadUserByUsername user: {}", user);
		logger.info("loadUserByUsername ThreadSessionUtil: {}", (User)ThreadSessionUtil.get());
		if (user != null) {
			/**
			 * 说明：当前示例代码只做认证，默认给所有登陆成功的用户ROLE_LOGIN权限
			 */
			List<GrantedAuthority> grantedAuthorities = new ArrayList <>();
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_LOGIN");
			grantedAuthorities.add(grantedAuthority);
            /*List<Permission> permissions = permissionMapper.findByUserId(user.getId());
            for (Permission permission : permissions) {
                if (permission != null && permission.getPermissionname()!=null) {
                }
            }*/
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("username: " + username + " do not exist!");
        } 
	}
}
