package com.leeo.mq;

import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author lijun
 * @since 2017/7/21 18:26
 */
@Component
public class Consumer implements InitializingBean {

    @Value("${amqp.username}")
    String username;
    @Value("${amqp.password}")
    String password;


    public void handle(String msg){
        System.out.println("pring mqMessage :"+msg);
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("username:"+username);
        System.out.println("password:"+password);
    }
}
