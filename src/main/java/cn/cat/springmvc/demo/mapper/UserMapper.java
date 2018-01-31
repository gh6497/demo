package cn.cat.springmvc.demo.mapper;


import cn.cat.springmvc.demo.pojo.Role;
import cn.cat.springmvc.demo.pojo.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    User getByUsername(String username);

    List<Role> getRoles(int id);
}