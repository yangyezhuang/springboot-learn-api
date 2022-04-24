package com.learn.model;

import lombok.Data;

import java.util.List;

/**
 * 菜单实体类
 */
@Data
public class Menu {

    private int id;
    private int pid;
    private String name;
    private String path;
    private String icon;
    private List<Menu> children;

}
