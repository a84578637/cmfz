package com.baizhi.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.baizhi.entity.Album;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Msg;
import com.baizhi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class AppServiceImpl implements AppService {

@Autowired
BannerService bannerService;
@Autowired
AlbumService albumService;
@Autowired
UserService userService;
@Autowired
    RedisTemplate<String,Object> redisTemplate;


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

    @Override
    public Object obtain(String phone) {
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
//初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
//替换成你的AK
        final String accessKeyId = "LTAIFBm4MggRQCE6";//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "QvZuybTABR8Xmrkt2QH3cmex5Nqs2Q";//你的accessKeySecret，参考本文档步骤2
//初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("何腾飞");
        //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
        request.setTemplateCode("SMS_141606919");

        char[] str = "1234567890".toCharArray();

        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 1; i <=4; i++) {
            //随机生成0到str长度之间的数字做为下标
            int randomIndex = random.nextInt(str.length);
            //追加到sb对象
            sb.append(str[randomIndex]);
        }
        String code = sb.toString();
        ValueOperations<String, Object> forValue = redisTemplate.opsForValue();

        forValue.set(phone,code,5, TimeUnit.MINUTES);

        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"code\":"+code+"}");
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
//请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
//请求成功
        }

        return null;
    }

    @Override
    public Object check(String phone, String code) {
        if(phone==null||code==null){
            return new Msg("参数不能为空","-200");
        }else{
            ValueOperations<String, Object> forValue = redisTemplate.opsForValue();
            Object b = forValue.get(phone);
            if(code.equals(b.toString())){
                return new Msg("success","true");
            }else{
                return new Msg("fail","-200");
            }
        }

    }

}
