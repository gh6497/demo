package cn.cat.springmvc.demo.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.annotation.JmsListeners;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.BeanFactoryDestinationResolver;
import org.springframework.jms.support.destination.DestinationResolver;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * @Author: cat
 * @Date: Created in 2018/1/29
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */

//@EnableJms
public class JmsConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        return jmsTemplate;
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean("testTopic")
    public ActiveMQTopic activeMQTopic() {
        ActiveMQTopic activeMQQueue = new ActiveMQTopic("testTopic");

        return activeMQQueue;
    }
    @Bean("testQueue")
    public ActiveMQQueue activeMQQueue() {
        ActiveMQQueue activeMQQueue = new ActiveMQQueue("testQueue");

        return activeMQQueue;
    }
    @JmsListener(destination = "testTopic")
    @JmsListener(destination = "testQueue")
    public void processMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println(textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("message is not a TextMessage!");
        }
    }
    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setDestinationResolver(destinationResolver());
        factory.setConcurrency("3-10");
        return factory;
    }

    @Bean
    public DestinationResolver destinationResolver() {
        BeanFactoryDestinationResolver destinationResolver =
                new BeanFactoryDestinationResolver(applicationContext);
        return destinationResolver;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory mqConnectionFactory = new ActiveMQConnectionFactory();
        mqConnectionFactory.setBrokerURL("tcp://localhost:61616");
        return mqConnectionFactory;
    }

}
