package cn.cat.springmvc.demo.config;

import cn.cat.springmvc.demo.pojo.User;
import org.springframework.context.annotation.Bean;

/**
 * @Author: cat
 * @Date: Created in 2018/2/4
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */
public class BeanConfig {
    @Bean
    public User user() {
        User user = new User();
        user.setUsername("username1");
        user.setPassword("username2");
        return user;
    }
}
