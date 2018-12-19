package com.baizhi.service;

import com.baizhi.entity.Admin;
import com.baizhi.entity.Msg;

public interface AdminService {
    public Admin regist();
    public Msg CheckLogin(Admin admin);
}
