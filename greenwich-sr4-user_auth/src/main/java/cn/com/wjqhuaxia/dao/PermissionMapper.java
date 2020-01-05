package cn.com.wjqhuaxia.dao;

import java.util.List;

import cn.com.wjqhuaxia.mode.dto.Permission;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    
    List<Permission> findByUserId(int userId);
    List<Permission> findAll();
}