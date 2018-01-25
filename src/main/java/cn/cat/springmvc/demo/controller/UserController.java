package cn.cat.springmvc.demo.controller;

import cn.cat.springmvc.demo.pojo.User;
import cn.cat.springmvc.demo.service.UserService;
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
    private static int index = 1;
//    private static ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();
    @Autowired
    private UserService userService;
    @PostMapping("/users")
    public User add(User user) {
        user.setName("name" + index++);
        userService.save(user);
        return user;
    }

    @GetMapping("/users/{name}")
    public User get(@PathVariable("name") String name) {
        User user = userService.selectByKey(name);
        return user;
    }
/*
    @DeleteMapping("/users/{name}")
    public User delete(@PathVariable("name") String name) {
        User user = users.remove(name);
        System.out.println(user);

        return user;
    }

    @PutMapping("/users/{name}")
    public User udpate(@PathVariable("name") String name, User user) {
        users.remove(name);
        users.put(name, user);
        System.out.println(user);
        return user;
    }*/
}
