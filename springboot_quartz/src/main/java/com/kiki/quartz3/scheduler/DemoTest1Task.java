package com.kiki.quartz3.scheduler;

import com.kiki.quartz3.controller.DemoTest1;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class DemoTest1Task implements Job {

    @Autowired
    private DemoTest1 demoTest1;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        demoTest1.save();
    }
}
