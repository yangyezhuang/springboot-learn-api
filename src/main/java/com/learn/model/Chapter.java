package com.learn.model;

import lombok.Data;

/**
 * 章节内容实体类
 */
@Data
public class Chapter {

    private int course_id;
    //private String course_title;
    private String chapter_id;
    private String chapter_title;
    private int chapter;
    private String chapter_src;

}
