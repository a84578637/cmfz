package com.baizhi.service;

import com.baizhi.entity.Admin;
import com.baizhi.entity.Msg;
import com.baizhi.mapper.AdminMapper;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    
    @Autowired
    AdminMapper adminMapper;

    static Logger logger = Logger.getLogger(AdminServiceImpl.class);

    @Override
    public Admin regist() {

        Admin testAdmin = new Admin();
        testAdmin.setPassword("123456");
        testAdmin.setUsername("root");
        adminMapper.insert(testAdmin);
        logger.info("创建成功");
        return testAdmin;

    }
    /**
        @author 根据Controller传回来Admin对象的用户名，去到数据库里查询
                如果是空，则返回Msg对象 flag=false 提示：无此用户
                如果不是空，但是密码不对， flag=false 提示：密码错误
                如果不是空，密码正确， flag = true 提示：登录成功
     */
    @Override
    public Msg CheckLogin(Admin admin) {




        Example example = new Example(Admin.class,true,true);
        Example.Criteria criteria = example.createCriteria();
        logger.info("AdminServiceImpl中 CheckLogin方法准备查询用户");
        criteria.andEqualTo("Username", admin.getUsername());
        List<Admin> admins = adminMapper.selectByExample(example);
            Msg msg = new Msg();
        //根据用户名判断是否有此用户
        if(admins.isEmpty()){
            logger.info("AdminServiceImpl中 CheckLogin方法 没有查到用户");

            msg.setFlag("false");
            msg.setMessage("无此用户");

        }else{
            logger.info("AdminServiceImpl中 CheckLogin方法，根据用户名查询出的对象有"+admins);
            //如果有此用户，判断密码是否错误
            if(admin.getPassword().equals(admins.get(0).getPassword())){
                msg.setFlag("true");
                msg.setMessage("登录成功");
                logger.info("AdminServiceImpl中 CheckLogin方法，验证成功");
            }else {
                msg.setFlag("false");
                msg.setMessage("密码 错误");
                logger.info("AdminServiceImpl中 CheckLogin方法，验证失败，密码错误");

            }
        }


        return msg;
    }
}
