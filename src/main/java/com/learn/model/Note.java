package com.learn.model;

import lombok.Data;

/**
 * @Description: TODO
 * @Date: 2022/4/7 13:56
 * @Author: Yang Yezhuang
 */
@Data
public class Note {
    Long note_id;
    int uid;
    String note;
    String date;
}
