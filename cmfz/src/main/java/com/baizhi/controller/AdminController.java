package com.baizhi.controller;

import com.baizhi.conf.CreateValidateCode;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Msg;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Logger;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    Logger a = Logger.getLogger("a");
    @RequestMapping("/test")
    public Admin test(){
        Admin regist = adminService.regist();


        a.info("Controller中接收成功");
        return regist;
    }

    @RequestMapping("/login")
    public Msg login(Admin admin){
        a.info("Controller - login 方法中接收参数："+admin);
        Msg msg = adminService.CheckLogin(admin);
        a.info("Controller - login方法中用户判断成功："+msg);
        return msg;
    }

    @RequestMapping("/pic")
    public void getPic(HttpSession session, HttpServletResponse response) throws IOException {

        CreateValidateCode cvc = new CreateValidateCode();
        String code = cvc.getCode();
        session.setAttribute("code",code);
        cvc.write(response.getOutputStream());
        a.info(code);
    }
}
