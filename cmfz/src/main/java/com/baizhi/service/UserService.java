package com.baizhi.service;

import com.baizhi.entity.ProvinceJson;
import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService  {
    public Map<String , List<Integer>> getUserRegist();
    public Map<String , List<ProvinceJson>> getUserProvince();
    public User getAppLogin(String phone);
    public Integer registApp(User user);
    public Object updateApp(User user);
    public List<User> memberApp(Integer uid);
}
