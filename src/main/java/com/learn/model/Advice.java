package com.learn.model;

import lombok.Data;

/**
 * @Description: TODO
 * @Date: 2022/4/8 16:12
 * @Author: Yang Yezhuang
 */
@Data
public class Advice {
    Long id;
    String title;
    String content;
    String name;
    String phone;
    String date;
}
