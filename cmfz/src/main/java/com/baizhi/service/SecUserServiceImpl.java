package com.baizhi.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.baizhi.entity.dto.secUserDto;
import com.baizhi.shiro.entity.secUser;
import com.baizhi.shiro.mapper.SecUserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class SecUserServiceImpl implements SecUserService {
    static String requestUrl = "http://api.feige.ee/SmsService/Send";
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    SecUserMapper secUserMapper;

    @Override
    public String checkLogin(secUser user) {
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(user.getUserPhone(), user.getPassword());
        try {
            subject.login(token);
            secUser user2 = new secUser();
            user2.setUserPhone(user.getUserPhone());
            secUser user1 = secUserMapper.selectOne(user2);
            Session session = subject.getSession();

            session.setAttribute("username", user1.getUserName());
            session.setAttribute("userid", user1.getUserId());
            session.setAttribute("userphone", user1.getUserPhone());
            if (user1.getLoginTime() == null) {
                session.setAttribute("userlogintime", "00:00");
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                long time = user1.getLoginTime().getTime();
                java.sql.Date time1 = new java.sql.Date(time);
                String logintime = sdf.format(time1);
                /*  format.parse(user1.getLoginTime(),"yyyy-MM-dd HH:ss")
                 */
                session.setAttribute("userlogintime", logintime);
            }

            user1.setLoginTime(new Date());
            secUserMapper.updateByPrimaryKey(user1);
            return "success";

        } catch (UnknownAccountException e) {
            return "账号错误";
        } catch (IncorrectCredentialsException e) {
            return "密码错误";
        }

    }

    @Override
    public void regist(HttpSession session) {
        secUser user = new secUser();
        String userPhone = (String) session.getAttribute("userPhone");
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        //随机生成盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();

        Md5Hash hashPassword = new Md5Hash(password, salt, 1024);
        user.setPassword(hashPassword.toString());
        user.setCreatedTime(new Date());
        user.setSalt(salt);
        user.setUserName(username);
        user.setUserPhone(userPhone);
        secUserMapper.insert(user);
        //清空session


    }

    @Override
    public void update(secUser user) {

    }

    @Override
    public secUserDto queryAll(Integer page, Integer rows) {


        PageInfo<secUser> pageInfo = PageHelper.startPage(page, rows).doSelectPageInfo(() -> this.secUserMapper.selectAll());
        int count = secUserMapper.selectCount(new secUser());
        secUserDto secUserDto = new secUserDto(count, pageInfo.getList());
        return secUserDto;
    }

    @Override
    public String del(Integer id) {

        int i = secUserMapper.deleteByPrimaryKey(id);

        return "success";
    }

    @Override
    public secUser queryOneByPhone(String phone) {
        return null;
    }

    @Override
    public secUser queryOneByEmail(String email) {
        return null;
    }

    @Override
    public String checkVcode(String code, HttpSession session) {
        String Vcode = (String) session.getAttribute("Vcode");
        if (code.toLowerCase().equals(Vcode)) {
            return "success";
        } else {
            return "false";
        }

    }

    @Override
    public String checkPhone(String phone) {

       /* Example.Criteria criteria = example.createCriteria();
        Integer userid =(Integer)SecurityUtils.getSubject().getSession().getAttribute("userid");
        criteria.andEqualTo("userId",userid);*/


        Example example = new Example(secUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userPhone", phone);
        secUser user = secUserMapper.selectOneByExample(example);
        /*List<secUser> secUsers = secUserMapper.selectAll();
        for (secUser secUser : secUsers) {
            System.out.println(secUser.getUserPhone()+"-----"+phone);
            if(secUser.getUserPhone().equals(phone)){

                return "false";
            }
        }*/
        if (user == null) {
            return "success";
        } else {
            return "false";
        }


    }

    @Override
    public Object obtain(String phone) {
    /*    Random random = new Random();
        char[] str = "1234567890".toCharArray();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <=4; i++) {
            //随机生成0到str长度之间的数字做为下标
            int randomIndex = random.nextInt(str.length);
            //追加到sb对象
            sb.append(str[randomIndex]);
        }
        String code = sb.toString();
        ValueOperations<String, Object> forValue = redisTemplate.opsForValue();

        forValue.set(phone,code,5, TimeUnit.MINUTES);
        System.out.println(code);

        try {
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("Account","13213790930"));
            formparams.add(new BasicNameValuePair("Pwd","ec1509ca374e37a91acacd7e2"));
            formparams.add(new BasicNameValuePair("Content","您的验证码是"+code));
            formparams.add(new BasicNameValuePair("Mobile",phone));
            formparams.add(new BasicNameValuePair("SignId","91881"));
            Post(formparams);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;*/


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
        for (int i = 1; i <= 4; i++) {
            //随机生成0到str长度之间的数字做为下标
            int randomIndex = random.nextInt(str.length);
            //追加到sb对象
            sb.append(str[randomIndex]);
        }
        String code = sb.toString();
        ValueOperations<String, Object> forValue = redisTemplate.opsForValue();

        forValue.set(phone, code, 5, TimeUnit.MINUTES);
        System.out.println(code);

        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"code\":" + code + "}");
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
    public String checkVerify(String verify, String userPhone) {
        ValueOperations<String, Object> forValue = redisTemplate.opsForValue();

        String code = (String) forValue.get(userPhone);
        if (code == null) {
            return "false";
        } else if (code.equals(verify)) {
            return "success";
        } else {
            return "false";
        }


    }

    @Override
    public void Logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
   /* public static void Post( List<NameValuePair> formparams) throws Exception {
        CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();

        httpClient.start();

        HttpPost requestPost=new HttpPost(requestUrl);

        requestPost.setEntity(new UrlEncodedFormEntity(formparams,"utf-8"));

        httpClient.execute(requestPost, new FutureCallback<HttpResponse>() {

            public void failed(Exception arg0) {

                System.out.println("Exception: " + arg0.getMessage());
            }

            public void completed(HttpResponse arg0) {
                System.out.println("Response: " + arg0.getStatusLine());
                try {

                    InputStream stram= arg0.getEntity().getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stram));
                    System.out.println(	reader.readLine());

                } catch (UnsupportedOperationException e) {

                    e.printStackTrace();
                } catch (IOException e) {

                    e.printStackTrace();
                }


            }

            public void cancelled() {


            }
        }).get();



        System.out.println("Done");
    }*/

}
