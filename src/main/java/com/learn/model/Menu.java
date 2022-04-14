package com.learn.model;

import lombok.Data;

/**
 * 菜单实体类
 */
@Data
public class Menu {

    private int id;
    private String authName;
    private String childrenPath;
    private int pid;

}
