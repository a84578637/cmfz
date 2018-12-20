package com.baizhi.service;

import com.baizhi.entity.Menu;
import com.baizhi.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuMapper menuMapper;
    Logger a = Logger.getLogger("a");
    @Override
    public List<Menu> getParentMenu() {
        Example example = new Example(Menu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId", "-1");
        List<Menu> menus = menuMapper.selectByExample(example);

            a.info("MenuServiceImpl中查询到的父类Menu:"+menus);
        return menus;
    }

    @Override
    public List<Menu> getChildMenu(Integer pid) {
        Example example = new Example(Menu.class);
        Example.Criteria criteria = example.createCriteria();
        Example.Criteria id = criteria.andEqualTo("parentId", pid);
        List<Menu> menus = menuMapper.selectByExample(example);

        return menus;
    }
}
