package cn.cat.springmvc.demo.controller;

import cn.cat.springmvc.demo.pojo.User;
import cn.cat.springmvc.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: cat
 * @Date: Created in 2018/1/23
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/users")

    public User add(User user) {
        userService.save(user);
        return user;
    }

    @GetMapping("/users/{id}")
    public User get(@PathVariable("id") Integer id) {
        User user = userService.get(id);
        return user;
    }

    @PostMapping("/users/login")
    public String login(@RequestParam("username")String username,
                        @RequestParam("password")String password) {
        // 检查用户名和密码 略
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return "index";
    }
}
