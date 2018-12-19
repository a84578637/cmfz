package com.baizhi.controller;

import com.baizhi.conf.CreateValidateCode;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Msg;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    @RequestMapping("/Login")
    public Msg login(String username ,String password ,String encode,HttpSession session){
        Object code = session.getAttribute("Code");
        if(!code.equals(encode)){
            a.info("AdminController Login方法中 判断验证码"+code+"--"+encode);
            return new Msg("验证码错误","false");
        }
        Admin admin = new Admin(null, username, password);
        a.info("Controller - login 方法中接收参数："+admin);
        a.info("Controller - login方法中接收的ENCODE："+encode);
        Msg msg = adminService.CheckLogin(admin);
        a.info("Controller - login方法中用户判断成功："+msg);
        if(msg.getFlag().equals("true")){
            session.setAttribute("Admin",admin);
            a.info("放入了"+session.getAttribute("Admin"));
        }
        return msg;
    }

    @RequestMapping("/lo")
    public void lo(String username){
        a.info(username);
    }

    @RequestMapping("/pic")
    public void getPic(HttpSession session, HttpServletResponse response) throws IOException {

        CreateValidateCode cvc = new CreateValidateCode();
        String code = cvc.getCode();

        cvc.write(response.getOutputStream());
        session.setAttribute("Code",code);
        a.info(code);
    }
}
