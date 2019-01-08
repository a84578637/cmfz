package com.baizhi.controller;

import com.aliyuncs.http.HttpResponse;
import com.baizhi.util.CreateValidateCode;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Msg;
import com.baizhi.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    Logger a;
    @RequestMapping("/test")
    public Admin test(){
        Admin regist = adminService.regist();


        a.info("Controller中接收成功");
        return regist;
    }


    @RequestMapping("/Login")
    public Msg login(String username ,String password ,String encode,HttpSession session){
        Object code = session.getAttribute("Code");
        if(!encode.equalsIgnoreCase(code.toString())
        ){
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

        }
        return msg;
    }

    @RequestMapping("/logout")
        public void logout(HttpServletResponse response){
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
        boolean permitted = subject.isPermitted("admin:delete");
        if(permitted){
            System.out.println("拥有输出权限");
        }else{
            System.out.println("没有输出权限");
        }
        System.out.println("登出");

        try {
            response.sendRedirect("/cmfz/login.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }

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
