package com.kiki.quartz2;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author kiki
 * 定义任务类
 */
public class JobDemo implements Job {

    @Autowired
    private TestDemo testDemo;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Execute   ：  " + new Date() + " ------------------");
        testDemo.add();
    }
}
