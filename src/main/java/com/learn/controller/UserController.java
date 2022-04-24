package com.learn.controller;

import com.learn.model.Course;
import com.learn.model.User;
import com.learn.util.result.Result;
import com.learn.util.result.ResultCode;
import com.learn.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Yang Yezhuang
 * @date: 2022/3/13
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    /**
     * 查询所有用户
     *
     * @return
     */
    @GetMapping()
    public List<User> allUsers() {
        List<User> users = userService.listUsers();

        return users;
    }


    /**
     * 添加用户
     *
     * @return
     */
    @PostMapping()
    public Result addUser(@RequestBody User user) {
        return userService.insertUser(user);
    }


    /**
     * 删除用户
     *
     * @param uid
     * @return
     */
    @DeleteMapping("/{uid}")
    public int delUser(@PathVariable("uid") int uid) {
       return userService.deleteUser(uid);
    }


    /**
     * 修改用户状态
     *
     * @param id
     * @param status
     * @return
     */
    @PutMapping("/{userId}/state/{status}")
    public Result changeState(@PathVariable("userId") int id,
                              @PathVariable("status") boolean status) {
        userService.updateUserStatus(status, id);

        return Result.success(ResultCode.SUCCESS);
    }


    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/{uid}")
    public User getUserInfo(@PathVariable("uid") int uid) {
        User userInfo = userService.getUserInfo(uid);

        return userInfo;
    }


    /**
     * 查询用户收藏的课程
     *
     * @param uid
     * @return
     */
    @GetMapping("/{uid}/courses")
    public List<Course> likes(@PathVariable("uid") int uid) {
        List<Course> likes = userService.listUserLikeCourse(uid);

        return likes;
    }

    /**
     * 查询用户收藏的课程数
     */
    @GetMapping("/course/total/{uid}")
    public int courseTotal(@PathVariable("uid") int uid) {
        return userService.countCourses(uid);
    }


    /**
     * 添加收藏
     *
     * @param uid
     * @param courseId
     * @return
     */
    @PostMapping("/{uid}/addCourse/{course_id}")
    public Result userAddCourse(@PathVariable("uid") int uid,
                                @PathVariable("course_id") int courseId) {
        int total = userService.isLike(uid, courseId);
        if (total == 0) {
            userService.userAddCourse(uid, courseId);
            return Result.success(ResultCode.SUCCESS);
        } else {
            return Result.failure(ResultCode.FAILURE);
        }
    }


    /**
     * 取消收藏
     *
     * @param uid
     * @param course_id
     * @return
     */
    @DeleteMapping("{uid}/course/{course_id}")
    public int userDelCourse(@PathVariable("uid") int uid,
                             @PathVariable("course_id") int course_id) {

        return userService.userDelCourse(uid, course_id);
    }

}
