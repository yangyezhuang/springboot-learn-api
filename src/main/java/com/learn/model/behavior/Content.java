package com.learn.model.behavior;

import lombok.Data;

@Data
public class Content {

    private int content_id;
    private String content;
    private String author;
    private String pudate;
    private int likeCnt;

    int downCnt;    //下载量
    double w;       //相似度(只在推荐时调用)


}
