package com.example.salary.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public ShiroDialect getShiroDialect()
    {
        return new ShiroDialect();
    }
    @Bean
    public HashedCredentialsMatcher getHashedCredentialsMatcher()
    {
        HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();

        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);

        return matcher;
    }


    @Bean
    public MyRealm getMyRealm(HashedCredentialsMatcher matcher){
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(matcher);
        return myRealm;
    }
    @Bean
    public DefaultWebSessionManager getDefaultWebSessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        return sessionManager;
    }



    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(MyRealm myRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);
        securityManager.setSessionManager(getDefaultWebSessionManager());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();

        filter.setSecurityManager(securityManager);
        //设置shiro的拦截规则
        //anon匿名用户可访问
        //authc认证用户可访问
        //user使用RememberMe的用户看访问
        //perms[]对应权限可访问

        Map<String,String> filterMap = new HashMap<>();

        filterMap.put("/","anon");
        filterMap.put("/layui/**","anon");
        filterMap.put("/echarts/**","anon");
        filterMap.put("/html/onload.html","anon");
        filterMap.put("/html/mainpage.html","authc");
        filterMap.put("/html/modifyPage.html","authc");
        filterMap.put("/html/boardManage.html","perms[sys:rs:board]");
        filterMap.put("/html/boardPage.html","authc");
        filterMap.put("/html/salaryPage.html","authc");
        filterMap.put("/html/salaryManagePage.html","perms[sys:cw:wage]");
        filterMap.put("/html/stuffManagePage.html","perms[sys:rs:stuff]");
        filterMap.put("/html/powerPage.html","perms[sys:admin]");


        filter.setFilterChainDefinitionMap(filterMap);
        filter.setLoginUrl("/html/onload.html");
        filter.setUnauthorizedUrl("/html/onload.html");

        return filter;
    }
}
