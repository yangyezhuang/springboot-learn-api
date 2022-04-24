package com.learn.model.behavior;

import lombok.Data;

@Data
public class Likes {

    private int like_id; //发起喜欢的用户
    private int user_id; //被喜欢的文章
    private int content_id; //该喜欢行为的id

}
