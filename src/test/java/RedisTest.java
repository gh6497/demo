import cn.cat.springmvc.demo.config.WebConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @Author: cat
 * @Date: Created in 2018/1/23
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
public class RedisTest {
    @Resource(name= "stringRedisTemplate")
    private StringRedisTemplate srt;

    @Test
    public void testSrt() {
        ValueOperations<String,String> operations = srt.opsForValue();
        operations.set("daf", "ddd");
        System.out.println(operations.get("daf"));
    }
}
