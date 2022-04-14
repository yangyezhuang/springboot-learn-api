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
 * @Description: UserController
 * @Date: 2022/3/13 17:31
 * @Created: by yyz
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/a")
    public String a(){
        return "a";
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @GetMapping("/all")
    public List<User> allUsers() {
        List<User> users = userService.allUsers();
        //log.info("全部用户：" + users);

        return users;
    }


    /**
     * 添加用户
     *
     * @return
     */
    @PostMapping("/add")
    public Result addUser(@RequestBody User user) {

        return userService.addUser(user);
    }


    /**
     * 删除用户
     *
     * @param uid
     * @return
     */
    @PostMapping("/del/{uid}")
    public String delUser(@PathVariable("uid") int uid) {
        //log.info("删除id为" + uid + "的用户");

        userService.delUser(uid);
        return "删除成功";
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
        //log.info("id：" + id + "\t状态：" + status);

        return Result.success(ResultCode.SUCCESS);
    }


    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("info/{uid}")
    public User getUserInfo(@PathVariable("uid") int uid) {
        User userInfo = userService.getUserInfo(uid);
        //log.info("用户信息：" + userInfo);

        return userInfo;
    }


    /**
     * 查询用户收藏的课程
     *
     * @param uid
     * @return
     */
    @GetMapping("/courses/{uid}")
    public List<Course> likes(@PathVariable("uid") int uid) {
        List<Course> likes = userService.userCourse(uid);
        //log.info("用户:" + uid + "\t收藏课程：" + likes);

        return likes;
    }

    /**
     * 查询用户收藏的课程数
     */
    @GetMapping("/course/total/{uid}")
    public int courseTotal(@PathVariable("uid") int uid) {
        return userService.courseTotal(uid);
    }


    /**
     * 添加收藏(待完善)
     *
     * @param uid
     * @param course_id
     * @return
     */
    @PostMapping("/{uid}/addCourse/{course_id}")
    public Result userAddCourse(@PathVariable("uid") int uid,
                                @PathVariable("course_id") int course_id) {
        int total = userService.isLike(uid, course_id);
        if (total == 0) {
            userService.userAddCourse(uid, course_id);
            //log.info("用户：" + uid + " 收藏课程：" + course_id);
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
        //log.info("用户：" + uid + "，取消收藏：" + course_id);

        return userService.userDelCourse(uid, course_id);
    }

}
