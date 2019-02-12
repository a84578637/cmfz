package com.baizhi.service;

import com.baizhi.entity.User;

public interface AppService {
    public Object query(String uid,String type,String sub_type);
    public Object wenQuery(Integer id);
    public User login(String phone);
}
