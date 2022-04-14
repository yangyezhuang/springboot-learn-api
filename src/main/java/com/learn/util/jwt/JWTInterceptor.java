package com.learn.util.jwt;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token 拦截器
 */
public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");

        System.out.println("token：" + token);

        if (StringUtils.isEmpty(token)) {
            System.out.println("token为空");
        }
        try {
            JWTUtils.verify(token);
        } catch (SignatureVerificationException e) {
            System.out.println("无效签名");
            return false;
        } catch (TokenExpiredException e) {
            System.out.println("token过");
            return false;
        } catch (AlgorithmMismatchException e) {
            System.out.println("token算法不一致");
            return false;
        } catch (Exception e) {
            System.out.println("token无效");
            return false;
        }
        return true;
    }
}
