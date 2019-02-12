package com.baizhi.shiro.mapper;


import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface shiroMapper  {
    public List<String> selectPermission(@Param("userPhone") String userPhone);
    public List<String> selectRole(@Param("userPhone")String userPhone);
}
