package com.learn.controller;

import com.learn.model.User;
import com.learn.util.jwt.JWTUtils;
import com.learn.util.result.Result;
import com.learn.util.result.ResultCode;
import com.learn.service.MailService;
import com.learn.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Description: AccountController
 * @Date: 2022/3/13 17:31
 * @Created: by yyz
 */
@Slf4j
@RestController
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;


    /**
     * 用户登录
     *
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody User u) {
        // 获取当前用户
        Subject subject = SecurityUtils.getSubject();
        // 封装用户登陆数据
        UsernamePasswordToken token = new UsernamePasswordToken(u.getEmail(), u.getPassword());
        // 使用shiro进行登录验证
        try {
            subject.login(token);
            User user = (User) subject.getPrincipal();
            // log.info("subject.getPrincipal:" + subject.getPrincipal());
            String uToken = JWTUtils.getToken(user);    //获取token
            //log.info("token=>" + uToken);
            //返回给客户端保存
            return Result.success(uToken);
        } catch (UnknownAccountException e) {
            log.info("账号错误");
            return Result.failure(ResultCode.FAILURE, "账号错误");
        } catch (IncorrectCredentialsException e) {
            log.info("密码错误");
            return Result.failure(ResultCode.FAILURE, "密码错误");
        }
    }



    /**
     * 用户注册
     *
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user, HttpSession session) {
        return mailService.registered(user, session);
    }


    /**
     * 发送验证码
     *
     * @return
     */
    @PostMapping("/sendVerifyCode/{email}")
    public String sendEmail(@PathVariable("email") String email, HttpSession httpSession) {
        mailService.sendVerifyCode(email, httpSession);
        return "sucess";
    }


    /**
     * 修改密码
     * TODO
     *
     * @param user
     * @return
     */
    @PutMapping(value = "/user/{username}")
    public Result changePassword(@RequestBody User user) {
        userService.updatePasswd(user.getEmail(), user.getPassword());

        return Result.success();
    }
}
