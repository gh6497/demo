package cn.cat.springmvc.demo.jpa;

import cn.cat.springmvc.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: cat
 * @Date: Created in 2018/3/6
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    User getUserById(Integer id);
}
