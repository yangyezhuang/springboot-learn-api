package com.learn.controller;

import com.learn.model.Menu;
import com.learn.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: MenuController
 * @Date: 2022/3/13 17:31
 * @Created: by yyz
 */
@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/menus")
    public List<Menu> menus() {
        List<Menu> menus = menuService.menus();
        return menus;
    }
}
