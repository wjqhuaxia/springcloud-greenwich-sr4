package cn.com.wjqhuaxia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.wjqhuaxia.dao.IUserDao;
import cn.com.wjqhuaxia.dao.UserMapper;
import cn.com.wjqhuaxia.mode.ResultBean;
import cn.com.wjqhuaxia.mode.dto.User;
import cn.com.wjqhuaxia.model.UserEntity;
import cn.com.wjqhuaxia.util.ThreadSessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/user")
@Api(description = "用户管理接口")
public class UserManageController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserDao userDao;
	@Autowired
	private UserMapper userMapper;
	
	@ApiOperation(value = "获取用户列表", notes = "获取用户列表")
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public List<UserEntity> getUsers() {
		List<UserEntity> users=userDao.getAll();
		return users;
	}
	
	@ApiOperation(value = "根据用户id获取用户信息", notes = "根据用户id获取用户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "用户标识", required = true, paramType = "path", dataType = "Long") }
	)
    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
    public UserEntity getUser(@PathVariable Long id) {
    	UserEntity user=userDao.getOne(id);
        return user;
    }
	
	@ApiOperation(value = "根据用户名获取用户信息" ,  notes="根据用户名获取用户信息")
	@RequestMapping(value = "/getUserByUsername/{userName}", method = RequestMethod.GET)
    public User getUserByUsername(@PathVariable String userName) {
		logger.info("getUser selectByUsername userName: {}",userName);
    	User user = userMapper.selectByUsername(userName);
    	logger.info("getUser user user: {}",user);
        return user;
    }
	
	@ApiOperation(value = "获取当前登陆用户信息" ,  notes="获取当前登陆用户信息")
	@RequestMapping(value = "/getCurrentUser", method = RequestMethod.GET)
    public ResultBean<User> getCurrentUser() {
		User user = ThreadSessionUtil.get();
		logger.info("getCurrentUser user: {}", user);
        return ResultBean.success(user);
    }
    
	@ApiOperation(value = "新增用户" ,  notes="新增用户")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(@Validated @RequestBody UserEntity user) {
    	userDao.insert(user);
    	return "用户添加成功！";
    }
    
	@ApiOperation(value = "修改用户" ,  notes="修改用户")
    @RequestMapping(value="update", method = RequestMethod.POST)
    public void update(@Validated @RequestBody UserEntity user) {
    	userDao.update(user);
    }
    
	@ApiOperation(value = "删除用户" ,  notes="删除用户")
    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public void delete(@PathVariable("id") Long id) {
    	userDao.delete(id);
    }
}