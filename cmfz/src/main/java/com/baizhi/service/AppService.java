package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.List;

public interface AppService {
    public Object query(String uid, String type, String sub_type);
    public Object wenQuery(Integer id);
    public User login(String phone);
    public Object regist(String phone,String password);
    public Object account(Integer uid,String gender,String photo,String location,String description,String nickname,String province,String city,String password);
    public List<User> member(Integer uid);
}
