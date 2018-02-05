import cn.cat.springmvc.demo.config.BeanConfig;
import cn.cat.springmvc.demo.pojo.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: cat
 * @Date: Created in 2018/2/4
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */
public class BeanTest {
    public static void main(String[] args) {
        System.out.println("234");
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfig.class);
        User user = (User) ctx.getBean("user");
        System.out.println(user);
    }
}
