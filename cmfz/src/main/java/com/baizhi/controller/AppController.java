package com.baizhi.controller;

import com.alibaba.excel.util.FileUtil;
import com.baizhi.entity.Msg;
import com.baizhi.entity.User;
import com.baizhi.service.AppService;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/app")
public class AppController {
@Autowired
FastFileStorageClient fastFileStorageClient;
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
    public Object regist(String phone,String password){
        if(phone==null&&password==null){
            return new Msg("手机号或密码为空","-200");
        }else{

            Object regist = appService.regist(phone, password);
            if (regist!=null){
                return regist;
            }else{
                return new Msg("手机号已被注册","-205");
            }

        }

    }

    @RequestMapping("/account")
    public Object account(Integer uid,String gender,byte[] photo,String location,String description,String nickname,String province,String city,String password){
            if(uid==null){
                return new Msg("用户ID不能为空","-206");
            }else{
                FileInputStream fis =null;
                try {
                   fis =  new FileInputStream("fis");
                    fis.read(photo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                /*//将Byte字节数组转化为文件转存
                BufferedOutputStream bos = null;
                         FileOutputStream fos = null;
                         File file = null;
                FileInputStream fis=null;
                     try {
                         File dir = new File("./src/main/webapp/img");
        if(!dir.exists()&&dir.isDirectory()){
            dir.mkdirs();
        }
        file=new File("./src/main/webapp/img"+"\\"+"default.jpg");
        fos = new FileOutputStream(file);
        bos=new BufferedOutputStream(fos);
        bos.write(photo);
        //写完成
           } catch (Exception e) {
                         e.printStackTrace();
              }finally {
                         if(bos!=null){
                             try {
                                 bos.close();
                             }catch (IOException e1){
                                 e1.printStackTrace();
                             }
                         }
                         if(fos!=null){
                             try {
                                 fos.close();
                             }catch (IOException e2){
                                 e2.printStackTrace();
                             }
                         }
                     }*/
                //写完成,将头像转存到FDFS

                StorePath path = fastFileStorageClient.uploadFile(fis, 12580, "jpg", null);
                String path1 = path.getPath();

                Object account = appService.account(uid, gender, path1, location, description, nickname, province, city, password);
                if(account==null){
                    return new Msg("该用户不存在","-200");
                }else{
                    return account;
                }
            }

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
    public List<User> member(Integer uid){
        List<User> member = appService.member(uid);

        return member;
    }


}
