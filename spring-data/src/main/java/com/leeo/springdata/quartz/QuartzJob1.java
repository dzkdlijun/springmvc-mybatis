package com.leeo.springdata.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by lijun on 2016/9/21.
 */
public class QuartzJob1 extends QuartzJobBean {

    private int timeout;
    private static int i = 0;

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("定时任务执行中。。。");
    }
}
