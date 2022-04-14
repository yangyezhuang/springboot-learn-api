package com.learn.util.jwt;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.learn.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Calendar;


@Slf4j
@Service
public class JWTUtils {
    /**
     * 生成token
     *
     * @param u user
     * @return token
     */
    public static String getToken(User u) {

        Calendar instance = Calendar.getInstance();
        //默认令牌过期时间7天
        instance.add(Calendar.DATE, 7);

        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("uid", u.getUid())
                .withClaim("username", u.getUsername())
                .withClaim("role", u.getRole());

        return builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(u.getPassword()));
    }


    /**
     * 验证token合法性 成功返回token
     */
    //public static DecodedJWT verify(String token) throws MyException {
    public static DecodedJWT verify(String token) {
        //if(StringUtils.isEmpty(token)){
        //    throw new MyException("token不能为空");
        //}
        if (StringUtils.isEmpty(token)) {
            System.out.println("token不能为空");
        }

        //获取登录用户真正的密码假如数据库查出来的是123456
        String password = "admin";
        JWTVerifier build = JWT.require(Algorithm.HMAC256(password)).build();
        return build.verify(token);
    }

   /* public static void main(String[] args) {
        DecodedJWT verify = verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTcxMDg1MDAsInVzZXJuYW1lIjoiYWRtaW4ifQ.geBEtpluViRUg66_P7ZisN3I_d4e32Wms8mFoBYM5f0");
        System.out.println(verify.getClaim("password").asString());
    }*/

}
