package com.learn.service;

import com.learn.mapper.UserMapper;
import com.learn.model.Course;
import com.learn.model.User;
import com.learn.util.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Yang Yezhuang
 * @date: 2022/3/13
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "user")
public class UserService {

    @Autowired
    private UserMapper userMapper;


    /**
     * 获取用户权限角色
     *
     * @param uid
     * @return
     */
    public User queryRole(int uid) {
        return userMapper.queryRole(uid);
    }


    /**
     * 查询全部用户
     *
     * @return
     */
    //@Cacheable(key = "'users'")
    public List<User> listUsers() {
        return userMapper.allUsers();
    }


    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    //@CachePut(key = "'allUsers'", unless = "#result==null")
    public Result insertUser(User user) {
        int uid = (int) ((Math.random() * 9 + 1) * 100000);
        String username = user.getUsername();
        String password = user.getPassword();
        String phoneNum = user.getPhoneNum();
        String email = user.getEmail();

        //log.info("注册用户：" + uid + "," + username + "," + password + "," + phoneNum + "," + email);

        userMapper.addUser(uid, username, password, phoneNum, email);
        return Result.success("注册成功");
    }


    /**
     * 删除用户
     *
     * @param uid
     * @return
     */
    // @CacheEvict(key = "'allUsers'")
    public int deleteUser(int uid) {
        return userMapper.delUser(uid);
    }


    /**
     * 修改用户状态
     *
     * @param status
     * @param userId
     */
    // @CachePut(key = "'allUsers'", unless = "#result==null")
    public void updateUserStatus(boolean status, int userId) {
        userMapper.updateUserStatus(status, userId);
    }


    /**
     * 根据id查找用户（查看用户是否存在）
     *
     * @param email
     * @return
     */
    public int findUserByEmail(String email) {
        int total = userMapper.findUserByEmail(email);
        return total;
    }


    /**
     * 用户登录
     *
     * @param email
     * @return
     */
    public User selectByEmail(String email) {
        return userMapper.selectByEmail(email);
    }


    /**
     * 用户信息
     *
     * @param uid
     * @return
     */
    public User getUserInfo(int uid) {
        return userMapper.getUserInfo(uid);
    }


    /**
     * 修改密码
     *
     * @param user
     * @return
     */
    @Transactional
    public User updatePasswd(User user) {
        return userMapper.updatePasswd(user.getUsername(), user.getPassword());
    }


    /**
     * 检查用户是否有该课程
     *
     * @param uid
     * @param courseId
     * @return
     */
    public int isLike(int uid, int courseId) {
        int total = userMapper.isLike(uid, courseId);
        return total;
    }


    /**
     * 用户添加课程
     *
     * @param uid
     * @param course_id
     */
    public void userAddCourse(int uid, int course_id) {
        userMapper.addUserCourse(uid, course_id);
    }


    /**
     * 用户取消收藏
     *
     * @param uid
     * @param course_id
     * @return
     */
    public int userDelCourse(int uid, int course_id) {
        return userMapper.delUserCourse(uid, course_id);
    }


    /**
     * 用户全部课程详情
     *
     * @param uid
     * @return
     */
    public List<Course> listUserLikeCourse(int uid) {
        return userMapper.userCourses(uid);
    }


    /**
     * 用户全部课程数量
     *
     * @param uid
     * @return
     */
    public int countCourses(int uid) {
        return userMapper.courseTotal(uid);
    }
}



