package com.learn.service;


import com.learn.mapper.MenuMapper;
import com.learn.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public List<Menu> menus() {
        return menuMapper.menu();
    }

}
