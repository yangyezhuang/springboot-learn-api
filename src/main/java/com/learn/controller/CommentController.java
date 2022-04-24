package com.learn.controller;

import com.learn.model.Comment;
import com.learn.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Yang Yezhuang
 * @date: 2022/3/13
 */
@Slf4j
@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;


    /**
     * 获取全部评论
     *
     * @return
     */
    @GetMapping()
    public List<Comment> listComments() {
        List<Comment> comments = commentService.listComments();

        return comments;
    }


    /**
     * 根据课程id获取评论
     *
     * @param course_id
     * @return
     */
    @GetMapping("/course/{course_id}")
    public List<Comment> getComments(@PathVariable("course_id") int course_id) {
        List<Comment> comments = commentService.getComment(course_id);

        return comments;
    }


    /**
     * 添加评论
     *
     * @param token
     * @param comment
     * @return
     */
    @PostMapping()
    public int insertComment(@RequestHeader("token") String token,
                             @RequestBody Comment comment) {
        // log.info("token:" + token);

        return commentService.insertComment(comment);
    }


    /**
     * 删除评论
     *
     * @param comment_id
     * @return
     */
    @DeleteMapping("/{comment_id}")
    public int deleteComment(@PathVariable("comment_id") Double comment_id) {
        return commentService.deleteComment(comment_id);
    }


    /**
     * 获取用户评论总数
     *
     * @param uid
     * @return
     */
    @GetMapping("/total/user/{uid}")
    public int countComments(@PathVariable("uid") int uid) {
        return commentService.countComments(uid);
    }

}
