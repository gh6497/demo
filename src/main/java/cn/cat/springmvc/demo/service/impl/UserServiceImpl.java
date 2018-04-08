package cn.cat.springmvc.demo.service.impl;

import cn.cat.springmvc.demo.jpa.UserRepository;
import cn.cat.springmvc.demo.pojo.User;
import cn.cat.springmvc.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: cat
 * @Date: Created in 2018/1/24
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    private UserRepository userRepository;
    /*@Override
    @Cacheable(key = "#id", value = "redis")
    public User get(Integer id) {
        User user = mapper.selectByPrimaryKey(id);
        System.out.println(user);
        return  user;
    }*/

    public User get(Integer id) {
        return null;
    }


    public User getByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        List<User> users = mapper.select(user);
        if (users == null || users.size() > 1) {
            return null;
        }
        return users.get(0);

    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.getUserById(id);
    }
}