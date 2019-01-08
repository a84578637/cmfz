package com.baizhi.controller;

import com.baizhi.entity.ProvinceJson;
import com.baizhi.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;


    @RequestMapping("/userRegists")
    public Map<String, List<Integer>> userRegists() {

        Map<String, List<Integer>> userRegist = userService.getUserRegist();
        return userRegist;
    }

    @RequestMapping("/userProvinces")
    public Map<String, List<ProvinceJson>> userProvinces() {
        Map<String, List<ProvinceJson>> userProvince = userService.getUserProvince();
        return userProvince;

    }

    @RequestMapping("/loginUser")
    public void loginUser(String userName, String password, HttpServletResponse response) {
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(userName, password);
        try {
            subject.login(token);
            try {
                response.sendRedirect("/cmfz/main/main.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (UnknownAccountException e) {
            try {
                response.sendRedirect("/cmfz/login.jsp");
            } catch (IOException e2) {
                e.printStackTrace();
            }
        } catch (IncorrectCredentialsException e) {
            try {
                response.sendRedirect("/cmfz/login.jsp");
            } catch (IOException e3) {
                e.printStackTrace();
            }
        }



    }
}
