package com.learn.model;

import lombok.Data;

/**
 * Course实体类
 */
@Data
public class Course {

    private int id;
    private String title;
    private String type;
    private int hour;
    private int chapter;
    private int people;
    private String info;
    private String img;

}
