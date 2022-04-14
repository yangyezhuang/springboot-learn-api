package com.learn.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 */
@Configuration
public class ShiroConfig {

    // ShiroFilterFactoryBean；第三步
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager getDefaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        bean.setSecurityManager(getDefaultWebSecurityManager);
        // 添加shiro内置过滤器
        /*
            anon	不需要登录即可访问；一般用于静态资源过滤；示例“/static/**=anon”
            authc	必须认证才能访问
            user	用户拦截器，必须拥有 记住我 才能使用
            role    拥有某个角色权限才能访问
            perms   拥有对某个资源的权限才能使用
         */
        // 拦截
        Map<String, String> filterMap = new LinkedHashMap<>();
        // 授权
        //filterMap.put("/user/add", "perms[user:add]");
        //filterMap.put("/user/*", "authc");
        bean.setFilterChainDefinitionMap(filterMap);

        // 设置登录的请求
        //bean.setLoginUrl("/login");
        // 未授权页面
        //bean.setUnauthorizedUrl("/noauth");

        return bean;
    }

    // DefaultWebSecurityManager；第二步
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联 UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    // 创建 realm 对象，需要自定义类；第一步
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

}
