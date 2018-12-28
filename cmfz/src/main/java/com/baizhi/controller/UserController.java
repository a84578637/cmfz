package com.baizhi.controller;

import com.baizhi.entity.ProvinceJson;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/userRegists")
    public Map<String , List<Integer>> userRegists(){

        Map<String, List<Integer>> userRegist = userService.getUserRegist();
        return userRegist;
    }

    @RequestMapping("/userProvinces")
    public Map<String,List<ProvinceJson>> userProvinces(){
        Map<String, List<ProvinceJson>> userProvince = userService.getUserProvince();
        return  userProvince;

    }
}