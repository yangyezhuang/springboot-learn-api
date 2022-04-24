package com.learn.service;

import com.learn.mapper.CommentMapper;
import com.learn.model.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author: Yang Yezhuang
 * @date: 2022/3/13
 */
@Slf4j
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;


    /**
     * 获取全部评论
     *
     * @return
     */
    public List<Comment> listComments() {
        return commentMapper.allComments();
    }


    /**
     * 获取评论
     *
     * @param course_id
     * @return
     */
    public List<Comment> getComment(int course_id) {
        return commentMapper.getComment(course_id);
    }


    /**
     * 发表评论
     *
     * @param comment
     * @return
     */
    public int insertComment(Comment comment) {
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


    /**
     * 删除评论
     *
     * @param comment_id
     * @return
     */
    public int deleteComment(double comment_id) {
        return commentMapper.delComment(comment_id);
    }


    /**
     * 获取用户评论总数
     *
     * @param uid
     * @return
     */
    public int countComments(int uid) {
        return commentMapper.commentCount(uid);
    }
}
