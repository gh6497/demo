import cn.cat.springmvc.demo.config.JmsConfig;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.*;

/**
 * @Author: cat
 * @Date: Created in 2018/1/29
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JmsConfig.class)
public class JmsTest {
    @Autowired
    private JmsTemplate jmsTemplate;
   /* @Autowired
    private Destination destination;*/

    @Test
    public void testSendQueue() {
        Destination destination1 = new ActiveMQTopic("testTopic");
        jmsTemplate.send(destination1, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                 TextMessage textMessage = session.createTextMessage("这是2一个测试消息");

                return textMessage;
            }
        });
    }
}
