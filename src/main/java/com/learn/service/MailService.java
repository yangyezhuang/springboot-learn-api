package com.learn.service;

import com.learn.model.User;
import com.learn.util.result.Result;
import com.learn.util.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserService userService;

    //application.properties中已配置的值
    @Value("${spring.mail.username}")
    private String from;


    /**
     * 发送验证码
     *
     * @param email
     * @return
     */
    public boolean sendVerifyCode(String email, HttpSession session) {
        try {
            // 生成六位数验证码
            int verifyCode = (int) ((Math.random() * 9 + 1) * 100000);

            //将验证码放到session中
            session.setAttribute("email", email);
            session.setAttribute("code", verifyCode);

            // 打印sessionID，以确保两次请求来自同意浏览器会话
            log.info("sessionID：" + session.getId());

            // 发送邮件
            //SimpleMailMessage mailMessage = new SimpleMailMessage();
            //mailMessage.setSubject("在线学习平台注册");
            //mailMessage.setText("您的注册验证码是：" + verifyCode);
            //mailMessage.setTo(email);
            //mailMessage.setFrom(from);
            //mailSender.send(mailMessage);

            log.info("注册验证码：" + verifyCode);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 检验验证码是否一致
     *
     * @param session
     * @return
     */
    public Result registered(User user, HttpSession session) {
        //获取session中的验证信息
        String email_ = (String) session.getAttribute("email");
        int code_ = (int) session.getAttribute("code");

        // 打印sessionID，以确保两次请求来自同意浏览器会话
        log.info("sessionID：" + session.getId());

        //获取表单中的提交的验证信息
        String email = user.getEmail();
        int code = user.getVerifyCode();

        //如果email数据为空，或者不一致，注册失败
        if (email == null || email.isEmpty() || !email.equals(email_)) {
            return Result.failure(ResultCode.FAILURE, "邮箱不一致");
        } else if (code != code_) {
            return Result.failure(ResultCode.FAILURE, "验证码错误");
        }

        // 判断用户是否存在
        int total = userService.findUserByEmail(user.getEmail());

        if (total == 0) {
            int uid = (int) ((Math.random() * 9 + 1) * 100000);
            String username = user.getUsername();
            String password = user.getPassword();
            String phoneNum = user.getPhoneNum();
            log.info("注册用户：" + uid + "," + username + "," + password + "," + phoneNum + "," + email);

            userService.addUser(user);
            return Result.success("注册成功");
        }
        return Result.failure(ResultCode.FAILURE, "用户已存在");
    }

}
