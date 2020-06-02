package com.kiki.quartz1;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @author kiki
 * 定义任务类
 */
public class JobDemo implements Job {

    /**
     * 任务被执行所触发的方法
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Execute   ：  " + new Date() + " ------------------");
    }
}
