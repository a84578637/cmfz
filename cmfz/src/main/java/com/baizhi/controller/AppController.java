package com.baizhi.controller;

import com.baizhi.entity.Msg;
import com.baizhi.entity.User;
import com.baizhi.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    AppService    appService;

    @RequestMapping("/first_page")
    public Object first_page(String uid,String type,String sub_type){
                String type_si="si";
            if(uid==null || type==null){
                return new Msg("参数不能为空","false");
            }else if(type_si.equals(type)&&sub_type==null){
            return new Msg("参数不能为空","false");
            }else{
                Object query = appService.query(uid, type, sub_type);
                return query;
            }
    }

    @RequestMapping("/detail")
    public Object detail(Integer id,Integer uid){
        if(id==null){
            return new Msg("文章ID不能为空","false");
        }else{
            //根据文章ID查询该文章的信息
            return null;
        }
    }

    @RequestMapping("/wen")
    public Object wen(Integer id,Integer uid){
    if(id==null){
        return new Msg("专辑ID不能为空","false");
    }else{
        Object o = appService.wenQuery(id);
        return o;
    }

    }

    @RequestMapping("/login")
    public Object login(String phone,String password,String code){
        if(phone==null){
            return new Msg("账号不能为空","-200");
        }else if(password==null&&code==null){
            return new Msg("请输入密码或验证码","-200");
        }else{
            if(code!=null){

                //此处判断短信验证码是否正确
                if(true){
                    //跳转到判断短信验证码
                    //如果验证码正确，根据账号查询所有信息
                    User login = appService.login(phone);
                    if(login==null){
                        return new Msg("用户名不存在","-201");
                    }else{
                        return login;
                    }

                }else{

                    //如果验证码错误，返回错误信息;
                    return new Msg("验证码输入错误","-203");
                }

            }else{
                //跳转到判断密码
                User login = appService.login(phone);
                if(login==null){
                    return new Msg("用户名不存在","-201");
                }else{
                    //根据账号查询所有信息，如果密码正确，返回信息
                    //如果密码不正确，返回错误信息;
                    if(password.equals(login.getPassword())){
                        return login;
                    }else{
                        return new Msg("密码错误","-202");
                    }
                }


            }
        }

    }
    @RequestMapping("/regist")
    public void regist(String phone,String password){

    }

    @RequestMapping("/modify")
    public Object modify(Integer uid,String gender,Byte photo,String location,String description,String nickname,String province,String city,String password){

        return null;
    }




    @RequestMapping("/obtain")
    public void obtain(String phone){
    //根据手机号发送短信


    }

    @RequestMapping("/check")
    public void check(String phone,String code){
        //根据手机号和验证码验证


    }

    @RequestMapping("/member")
    public Object member(String uid){

        return null;
    }


}
