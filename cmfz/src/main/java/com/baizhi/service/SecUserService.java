package com.baizhi.service;

import com.baizhi.entity.dto.secUserDto;
import com.baizhi.shiro.entity.secUser;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface SecUserService {
    //登录
    String checkLogin(secUser user);

    //注册
    void regist( HttpSession session);

    //修改信息
    void update(secUser user);

    //查所有
    secUserDto queryAll(Integer page,Integer rows);

    String del(Integer id);

    //查一个
    secUser queryOneByPhone(String phone);

    secUser queryOneByEmail(String email);

    String checkVcode(String code, HttpSession session);

    String checkPhone(String phone);

    Object obtain(String phone);
    String checkVerify(String verify,String userPhone );
    void Logout();

}
