package com.leeo.springdata.quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 注解实现spring-task定时任务
 * Created by lijun on 2016/9/22.
 */
@Component("taskJob4")
public class QuartzJob4 {
    @Scheduled(fixedRate = 3000)
    public void doJob(){
        System.out.println("注解实现spring-task定时任务");
    }
}
