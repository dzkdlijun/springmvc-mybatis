package com.leeo.springdata.quartz;

import org.springframework.stereotype.Service;

/**
 * xml配置spring-task定时任务
 * Created by lijun on 2016/9/22.
 */
@Service
public class QuartzJob3 {

    public void doJob(){
        System.out.println("xml配置spring-task定时任务！！！！");
    }
}
