package cn.com.wjqhuaxia.dao;

import org.apache.ibatis.annotations.Param;

import cn.com.wjqhuaxia.mode.dto.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);
    
    int insertUserRole(@Param("userId")int userId,@Param("roleId")int roleId);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}