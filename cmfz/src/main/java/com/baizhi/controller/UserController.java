package com.baizhi.controller;

import com.baizhi.entity.dto.secUserDto;
import com.baizhi.service.SecUserService;
import com.baizhi.service.SecUserServiceImpl;
import com.baizhi.shiro.entity.secUser;
import com.baizhi.util.CreateValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {
@Autowired
SecUserService secUserService;

@RequestMapping("/del")
@ResponseBody
public String del(Integer id){
    System.out.println(id);
    String del = secUserService.del(id);
    return del;
}
@RequestMapping("/getUserInfo")
@ResponseBody
public secUserDto getUserInfo(Integer page,Integer rows){
    secUserDto secUserDto = secUserService.queryAll(page, rows);

    System.out.println(secUserDto);
    return secUserDto;
}
    @RequestMapping("/pic")

    public void pic(HttpSession session, HttpServletResponse response){

        CreateValidateCode cvc = new CreateValidateCode();
        String code = cvc.getCode();
        try {
            cvc.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        session.setAttribute("Vcode",code);

    }
    @RequestMapping("/checkVcode")
        @ResponseBody
    public String checkVcode(String vcode,HttpSession session){
        System.out.println("in check Vcode");
            //返回success表示成功
        //返回false表示失败
        System.out.println(vcode);
        System.out.println(session.getAttribute("Vcode"));
        String result = secUserService.checkVcode(vcode, session);
        return result;
    }
    @RequestMapping("/checkVerify")
    @ResponseBody
    public String checkVerify(String verify,String userPhone){
        System.out.println("in checkVerify");
        System.out.println(verify);
        System.out.println(userPhone+"----1");
        String result = secUserService.checkVerify(verify, userPhone);
        return result;
    }
    @RequestMapping("/checkEmail")
    @ResponseBody
    public String checkEmail(String email ){
        //如果返回success表示可以使用
        //返回false表示失败
        System.out.println("in checkPhone");
        String result = secUserService.checkPhone(email);
        System.out.println("controler-"+result);
        return result;
    }
    @RequestMapping("/regist")
    public String regist(String username,String password,String phone,HttpSession session){
        System.out.println(username);
        System.out.println(password);
        System.out.println(phone);
        session.setAttribute("userPhone",phone);
        session.setAttribute("username",username);
        session.setAttribute("password",password);
        //保存完毕后 发送短信 进入短信验证页面，
        secUserService.obtain(phone);
        return "login/verify_form";
    }

    @RequestMapping("/sregist")
    public String sregist(HttpSession session){
        //前置操作已经完成，删除清空session并进行登录

        secUserService.regist(session);
        return "login/login";
    }

    @RequestMapping("/Login")
    @ResponseBody
    public String Login(String userPhone,String password,String encode,HttpSession session){
        //首先判断验证码是否正确，如果不正确返回验证码错误
        //再次进行认证操作
        System.out.println(encode);
        System.out.println(userPhone);
        System.out.println(password);


        String Vcode = (String)session.getAttribute("Vcode");
        if(!Vcode.equals(encode)){
            return "验证码错误";
        }else{
            secUser secUser = new secUser();
            secUser.setPassword(password);
            secUser.setUserPhone(userPhone);
            String result = secUserService.checkLogin(secUser);

            return result;
        }
    }
    @RequestMapping("/Logout")
    public String Logout(HttpSession session){
        secUserService.Logout();
        //登出
        System.out.println("登出");

        return "login/login";
    }




}
