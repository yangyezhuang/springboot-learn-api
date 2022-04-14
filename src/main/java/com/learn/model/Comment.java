package com.learn.model;

import lombok.Data;

/**
 * 评论实体类
 */
@Data
public class Comment {

    private int uid;
    private String username;
    private int course_id;
    private String date;
    private String comment;
    private Double comment_id;

}
