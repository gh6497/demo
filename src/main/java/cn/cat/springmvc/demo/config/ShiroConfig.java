package cn.cat.springmvc.demo.config;

import cn.cat.springmvc.demo.pojo.Role;
import cn.cat.springmvc.demo.service.RoleService;
import cn.cat.springmvc.demo.shiro.CustomCredentialsMatcher;
import cn.cat.springmvc.demo.shiro.MyRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: cat
 * @Date: Created in 2018/1/30
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */

/**
 * 认证的过程
 * Subject#login --> Sercuriy#login --> Authenticator#authenticate --> Realm#getAuthenticationInfo
 * --> Realm#doGetAuthenticationInfo(即我们自定义的域)
 */

public class ShiroConfig {


    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {

        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor attributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor attributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        attributeSourceAdvisor.setSecurityManager(securityManager);

        return attributeSourceAdvisor;
    }

    /**
     * 这个是主要的配置项
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, RoleService roleService) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setLoginUrl("/login");
        shiroFilter.setSuccessUrl("/");
        shiroFilter.setSecurityManager(securityManager);
        List<Role> roles = roleService.getAll();

        Map<String, String> map = new HashMap<>(0);
        /*
        /css/** = anon
				/images/** = anon
				/js/** = anon
         */
        System.out.println("ddddddddddddddddd");
        String anon = "anon";
        map.put("/css/**", anon);
        map.put("/js/**", anon);

        map.put("/", anon);
        map.put("/libs/**", anon);

        map.put("/*", "authc");

        shiroFilter.setFilterChainDefinitionMap(map);
        return shiroFilter;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultWebSecurityManager securityManager(AuthorizingRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);

        return securityManager;
    }

    @Bean
    public AuthorizingRealm realm(CredentialsMatcher credentialsMatcher) {

        AuthorizingRealm realm = new MyRealm();
        realm.setCredentialsMatcher(credentialsMatcher);
        return realm;
    }

    @Bean
    public CredentialsMatcher credentialsMatcher() {
        return new CustomCredentialsMatcher();
    }

}
