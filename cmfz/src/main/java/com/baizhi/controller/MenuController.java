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
        List<Menu> parentMenu = menuService.getParentMenu();
        a.info("queryAll中查到的"+parentMenu);
        return parentMenu;
    }

    @RequestMapping("/queryAllchildrenByPid")
    public List<Menu> queryAllchildrenByPid(Integer pid){
        List<Menu> childMenu = menuService.getChildMenu(pid);
        a.info("queryAllChild中查到的"+childMenu);
        return childMenu;

    }
}
