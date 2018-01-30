package cn.cat.springmvc.demo.service.impl;

import cn.cat.springmvc.demo.mapper.UserMapper;
import cn.cat.springmvc.demo.pojo.User;
import cn.cat.springmvc.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.swing.text.html.parser.Entity;
import java.util.List;

/**
 * @Author: cat
 * @Date: Created in 2018/1/24
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Override
    @Cacheable(key = "#id", value = "redis")
    public User get(Integer id) {
        User user = mapper.selectByPrimaryKey(id);
        System.out.println(user);
        return  user;
    }

    @Override
    public User getByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        List<User> users = mapper.select(user);
        if (users == null || users.size() > 1) {
            return null;
        }
        return users.get(0);
    }
}