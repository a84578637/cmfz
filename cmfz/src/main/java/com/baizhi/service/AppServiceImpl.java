package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Msg;
import com.baizhi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class AppServiceImpl implements AppService {

@Autowired
BannerService bannerService;
@Autowired
AlbumService albumService;
@Autowired
UserService userService;

    @Override
    public Object query(String uid, String type, String sub_type) {
        String type_all="all";
        String type_wen="wen";
        String type_si="si";
        String sub_type_ssyj="ssyj";
        String sub_type_xmfy="xmfy";
        Integer banner_count=5;
        Integer Album_count=6;
        String article_gutu="上师仁波切";
        HashMap<String, Object> map = new HashMap<>();
        if(type_all.equals(type)){
            List<Banner> appBanner = bannerService.getAppBanner(banner_count);
            map.put("banner",appBanner);
            List<Album> appAlbum = albumService.getAppAlbum(Album_count);
            map.put("album",appAlbum);
            //甘露妙宝两篇文章
            map.put("artical",null);

        }else if(type_wen.equals(type)){
            List<Album> allAppAlbum = albumService.getAllAppAlbum();
            map.put("album",allAppAlbum);
        }else if(type_si.equals(type)){
                if(sub_type_ssyj.equals(sub_type)){
                    //查询article_gutu的所有文章
                map.put("artical",null);
                }else if(sub_type_xmfy.equals(sub_type)){
                    //查询所有显密法要文章
                    map.put("artical",null);
                }else{
                    return new Msg("分支类型错误，无此类型","false");
                }
        }else{
                    return new Msg("类型错误，无此类型","false");
        }

        return map;
    }

    @Override
    public Object wenQuery(Integer id) {
        Album one = albumService.getOne(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("introduction",one);
        map.put("list",one.getChildren());
        return map;
    }



    @Override
    public User login(String phone) {
        User appLogin = userService.getAppLogin(phone);

        return appLogin;
    }

    @Override
    public Object regist(String phone, String password) {
        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        user.setSalt("000000");
        user.setStatus("-1");
        user.setRegDate(new Date());
        Integer id = userService.registApp(user);
        if(id==null){
            return null;
        }else{
            user.setId(id);
            return user;
        }

    }

    @Override
    public Object account(Integer uid, String gender, String photo, String location, String description, String nickname, String province, String city, String password) {
        User user = new User();
        user.setId(uid);
        user.setStatus("1");
        user.setCity(city);
        user.setPassword(password);
        user.setName(nickname);
        user.setProvince(province);
        user.setHeadPic(photo);
        user.setSign(description);
        user.setDharma(location);
        Object o = userService.updateApp(user);
        if(o==null){
            return null;
        }else{
            return o;
        }
    }

    @Override
    public List<User> member(Integer uid) {
        List<User> o = userService.memberApp(uid);
        return o;
    }

}
