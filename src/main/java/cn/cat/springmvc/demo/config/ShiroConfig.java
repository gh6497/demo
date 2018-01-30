package cn.cat.springmvc.demo.config;

import cn.cat.springmvc.demo.shiro.CustomCredentialsMatcher;
import cn.cat.springmvc.demo.shiro.MyRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @Author: cat
 * @Date: Created in 2018/1/30
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */

/**
 * 认证的过程
 * Subject#login --> Sercuriy#login --> Authenticator#authenticate --> Realm#getAuthenticationInfo
 *  --> Realm#doGetAuthenticationInfo(即我们自定义的域)
 */
public class ShiroConfig implements WebApplicationInitializer{
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
        delegatingFilterProxy.setTargetFilterLifecycle(true);
        FilterRegistration.Dynamic filter = servletContext.addFilter("delegatingFilter", delegatingFilterProxy);
        filter.addMappingForUrlPatterns(null, false, "/*");
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();

        return null;
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
        return null;
    }

    @Bean
    public CredentialsMatcher credentialsMatcher() {
        return new CustomCredentialsMatcher();
    }

}
