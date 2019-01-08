package com.baizhi.controller;

import com.baizhi.entity.Menu;
import com.baizhi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuService menuService;
    @Autowired
            Logger a;



    @RequestMapping("/queryAll")
    public List<Menu> queryAll(){
        //判断当前角色是否是超级管理员 如果是，添加管理员框
        List<Menu> parentMenu = menuService.getParentMenu();
        //判断当前角色是否拥有超级管理员资源
        //如果有，查询一次，并添加到内部
        //如果没有，直接输出

     //   a.info("queryAll中查到的"+parentMenu);
        return parentMenu;
    }

    @RequestMapping("/queryAllchildrenByPid")
    public List<Menu> queryAllchildrenByPid(Integer pid){
        List<Menu> childMenu = menuService.getChildMenu(pid);
     //   a.info("queryAllChild中查到的"+childMenu);
        return childMenu;

    }
}
