package cn.cat.springmvc.demo.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author: cat
 * @Date: Created in 2018/1/26
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * url授权
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

    }

    /**
     * 认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    }
}
