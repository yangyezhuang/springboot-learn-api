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

@Slf4j
@Service
@CacheConfig(cacheNames = "user")
public class UserService {

    @Autowired
    private UserMapper userMapper;

    // 获取用户权限角色
    public User queryRole(int uid) {
        return userMapper.queryRole(uid);
    }


    // 查询全部用户
    @Cacheable(key = "'users'")
    public List<User> allUsers() {
        return userMapper.allUsers();
    }


    // 用户注册
    //@CachePut(key = "'allUsers'", unless = "#result==null")
    public Result addUser(User user) {
        int uid = (int) ((Math.random() * 9 + 1) * 100000);
        String username = user.getUsername();
        String password = user.getPassword();
        String phoneNum = user.getPhoneNum();
        String email = user.getEmail();

        log.info("注册用户：" + uid + "," + username + "," + password + "," + phoneNum + "," + email);

        userMapper.addUser(uid, username, password, phoneNum, email);
        return Result.success("注册成功");
    }


    // 删除用户
    //@CacheEvict(key = "'allUsers'")
    public int delUser(int uid) {
        return userMapper.delUser(uid);
    }


    // 修改用户状态
    //@CachePut(key = "'allUsers'", unless = "#result==null")
    public void updateUserStatus(boolean status, int userId) {
        userMapper.updateUserStatus(status, userId);
    }


    // 根据id查找用户（查看用户是否存在）
    public int findUserByEmail(String email) {
        int total = userMapper.findUserByEmail(email);
        return total;
    }


    // 用户登录
    public User selectByEmail(String email) {
        return userMapper.selectByEmail(email);
    }


    // 产看用户信息
    public User getUserInfo(int uid) {
        return userMapper.getUserInfo(uid);
    }


    // 修改密码
    @Transactional
    public User updatePasswd(String username, String password) {
        return userMapper.updatePasswd(username, password);
    }


    // 检查用户是否有该课程
    public int isLike(int uid, int course_id) {
        int total = userMapper.isLike(uid, course_id);
        return total;
    }


    // 用户添加课程
    public void userAddCourse(int uid, int course_id) {
        userMapper.addUserCourse(uid, course_id);
    }


    // 用户取消收藏
    public int userDelCourse(int uid, int course_id) {
        return userMapper.delUserCourse(uid, course_id);
    }


    // 用户全部课程详情
    public List<Course> userCourse(int uid) {
        return userMapper.userCourses(uid);
    }

    // 用户全部课程数量
    public int courseTotal(int uid) {
        return userMapper.courseTotal(uid);
    }
}



