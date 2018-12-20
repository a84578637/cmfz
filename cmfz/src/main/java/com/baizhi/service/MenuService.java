package com.baizhi.service;

import com.baizhi.entity.Menu;

import java.util.List;

public interface MenuService {
    public List<Menu> getParentMenu();
    public List<Menu> getChildMenu(Integer pid);
}
