package com.learn.service;

import com.learn.mapper.CommentMapper;
import com.learn.model.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    // 获取评论
    public List<Comment> allComments() {
        return commentMapper.allComments();
    }


    // 获取评论
    public List<Comment> getComment(int course_id) {
        return commentMapper.getComment(course_id);
    }


    // 发表评论
    public int addComment(Comment comment) {
        int uid = comment.getUid();
        String username = comment.getUsername();
        int course_id = comment.getCourse_id();
        String comment_ = comment.getComment();

        // 生成comment_id
        double comment_id = ((Math.random() * 9 + 1) * 10000000);

        // 生成时间
        String time = "";
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        time = dateFormat.format(date);

        //log.info("添加评论：" + uid + "-" + username + "-" + course_id + "-" + comment);
        return commentMapper.addComment(uid, username, course_id, time, comment_, comment_id);
    }


    // 发表评论
    public int delComment(double comment_id) {
        return commentMapper.delComment(comment_id);
    }


    // 获取用户评论总数
    public int commentCount(int uid) {
        return commentMapper.commentCount(uid);
    }
}
