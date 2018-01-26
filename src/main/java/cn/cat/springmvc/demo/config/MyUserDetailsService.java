package cn.cat.springmvc.demo.config;

import cn.cat.springmvc.demo.pojo.User;
import cn.cat.springmvc.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @Author: cat
 * @Date: Created in 2018/1/26
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在！");
        }
        return user;
    }
}
