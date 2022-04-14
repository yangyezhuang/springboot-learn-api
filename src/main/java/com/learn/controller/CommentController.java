package com.learn.controller;

import com.learn.model.Comment;
import com.learn.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: CommentController
 * @Date: 2022/3/13 17:31
 * @Created: by yyz
 */
@Slf4j
@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;


    /**
     * 获取全部评论
     */
    @GetMapping("/all")
    public List<Comment> Comments() {
        List<Comment> comments = commentService.allComments();
        log.info("全部评论：" + comments);

        return comments;
    }


    /**
     * 根据课程id获取评论
     */
    @GetMapping("/course/{course_id}")
    public List<Comment> getComments(@PathVariable("course_id") int course_id) {
        List<Comment> comments = commentService.getComment(course_id);
        log.info("课程评论：" + comments);

        return comments;
    }


    /**
     * 添加评论
     */
    @PostMapping("/add")
    public int addComment(@RequestHeader("token") String token,
                          @RequestBody Comment comment) {
        log.info("token:" + token);

        return commentService.addComment(comment);
    }


    /**
     * 删除评论
     */
    @DeleteMapping("/del/{comment_id}")
    public int delComment(@PathVariable("comment_id") Double comment_id) {
        log.info("删除id为" + comment_id + "的评论");

        return commentService.delComment(comment_id);
    }


    /**
     * 获取用户评论总数
     */
    @GetMapping("/total/user/{uid}")
    public int commentCount(@PathVariable("uid") int uid) {
        return commentService.commentCount(uid);
    }

}
