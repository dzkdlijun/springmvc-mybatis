package com.leeo.mq;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lijun
 * @since 2017/7/21 18:26
 */
public class Producer {
    public static void main(String[] args) {
//        ConnectionFactory cf = new CachingConnectionFactory();
//        RabbitAdmin admin = new RabbitAdmin(cf);
//        Queue queue = new Queue("myQueue");
//        admin.declareQueue(queue);
//        TopicExchange exchange = new TopicExchange("myExchange");
//        admin.declareExchange(exchange);
//        admin.declareBinding(
//                BindingBuilder.bind(queue).to(exchange).with("foo.*"));
//
//        // set up the listener and container
//        SimpleMessageListenerContainer container =
//                new SimpleMessageListenerContainer(cf);
//        Object listener = new Object() {
//            public void handleMessage(String foo) {
//                System.out.println(foo);
//            }
//        };
//        MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
//        container.setMessageListener(adapter);
//        container.setQueueNames("myQueue");
//        container.start();

        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        RabbitTemplate template = ctx.getBean(RabbitTemplate.class);
        for(int i=0;i<10;i++){
            UserMessage message = new UserMessage("lijun",20+i);
            template.convertAndSend(new MqMessage("test",message));
        }
    }
}
