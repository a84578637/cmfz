package com.baizhi.conf;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class ShiroFilterConf {



    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        HashMap<String, String> map = new HashMap<>();
        map.put("/**","anon");
 /*       map.put("/css/**","anon");
        map.put("/js/**","anon");
        map.put("/img/**","anon");
        map.put("/main/**","anon");
        map.put("/script/**","anon");
        map.put("/themes/**","anon");
        map.put("/admin/**","anon");
       */

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);


        return  shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager getSecurityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        return  defaultWebSecurityManager ;
    }




}
