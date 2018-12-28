package com.baizhi.mapper;

import com.baizhi.entity.ProvinceJson;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
        public void addList(List<User> users);
        public Integer getUserRegist(@Param("small") Integer small,@Param("big") Integer big);
        public List<ProvinceJson> getUserProvince();
        public List<ProvinceJson> getUserProvinceWoMan();
        public List<ProvinceJson> getUserProvinceMan();

}