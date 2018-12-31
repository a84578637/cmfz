package com.baizhi.service;

import com.baizhi.entity.ProvinceJson;
import com.baizhi.entity.User;
import com.baizhi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    Logger logger;

    @Override
    public Map<String, List<Integer>> getUserRegist() {
        Integer date = 7;
        ArrayList<Integer> integers = new ArrayList<>();



        for(int i=0;i<10;i++){
            Integer userRegist = userMapper.getUserRegist(i * date, i * date + date);

            integers.add(userRegist);

        }

        Map<String, List<Integer>> map = new HashMap<>();

         map.put("data", integers);

         logger.info("map结构为："+map);
        return map;
    }



    @Override
    public Map<String, List<ProvinceJson>> getUserProvince() {
        List<ProvinceJson> userProvince = userMapper.getUserProvince();
        List<ProvinceJson> manProvince = userMapper.getUserProvinceMan();
        List<ProvinceJson> woManProvince = userMapper.getUserProvinceWoMan();

        HashMap<String, List<ProvinceJson>> map = new HashMap<>();
        map.put("data",userProvince);
        map.put("woman",woManProvince);
        map.put("man",manProvince);
        return map;
    }

    @Override
    public User getAppLogin(String phone) {
        User user = new User();
        user.setPhone(phone);
        User user1 = userMapper.selectOne(user);
        return user1;
    }
}
