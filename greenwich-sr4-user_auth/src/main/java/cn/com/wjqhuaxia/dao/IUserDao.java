package cn.com.wjqhuaxia.dao;

import java.util.List;

import cn.com.wjqhuaxia.model.UserEntity;

public interface IUserDao {
	
	List<UserEntity> getAll();
	
	UserEntity getOne(Long id);

	void insert(UserEntity user);

	void update(UserEntity user);

	void delete(Long id);

}