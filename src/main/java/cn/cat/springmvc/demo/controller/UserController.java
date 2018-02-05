package cn.cat.springmvc.demo.controller;

import cn.cat.springmvc.demo.exception.UserInfoException;
import cn.cat.springmvc.demo.pojo.User;
import cn.cat.springmvc.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: cat
 * @Date: Created in 2018/1/23
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */
@RestController
public class UserController {

//    @Autowired
//    private MethodValidationPostProcessor postProcessor;
    @Autowired
    private UserService userService;
    @PostMapping("/users")
//    @Validated
    public User add(@Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> map = new HashMap<>(1);
             result.getFieldErrors().stream().forEach(error -> {
                 String defaultMessage = error.getDefaultMessage();

                 String objectName = error.getField();
                 map.put(objectName, defaultMessage);
             });
            throw new UserInfoException("错误", map);
        }
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
