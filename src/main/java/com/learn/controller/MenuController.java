package com.learn.controller;

import com.learn.model.Menu;
import com.learn.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Yang Yezhuang
 * @date: 2022/3/13
 */
@RestController
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 获取菜单
     *
     * @return menu
     */
    @GetMapping()
    public List<Menu> listMenu() {
        List<Menu> menu = menuService.getMenu();
        return menu;
    }
}
