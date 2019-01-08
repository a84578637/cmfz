package com.baizhi.shiro.mapper;

import com.baizhi.shiro.entity.secUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface shiroMapper  {
    public List<String> selectPermission(@Param("username") String username);
    public List<String> selectRole(@Param("username")String username);
}
