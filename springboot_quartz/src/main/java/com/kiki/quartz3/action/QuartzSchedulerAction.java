package com.kiki.quartz3.action;

import com.kiki.quartz3.QuartzJobManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kiki
 */
@RestController
public class QuartzSchedulerAction {

    @Autowired
    private QuartzJobManager quartzJobManager;

    @GetMapping("/addJob")
    public void addJob() throws ClassNotFoundException {
        Map map = new HashMap<>();
        map.put("ni","hao");
        quartzJobManager.addJob(Class.forName("com.kiki.quartz3.scheduler.DemoTest1Task"),"demoTest1Task","newspaper","0/5 * * * * ?");
    }

    @GetMapping("/pauseJob")
    public void pauseJob(){
        quartzJobManager.pauseJob("demoTest1Task","newspaper");
    }

    @GetMapping("/resumeJob")
    public void resumeJob(){
        quartzJobManager.resumeJob("demoTest1Task","newspaper");
    }

    @GetMapping("/deleteJob")
    public void deleteJob(){
        quartzJobManager.deleteJob("demoTest1Task","newspaper");
    }

    @GetMapping("/startAllJObs")
    public void startAllJobs(){
        quartzJobManager.startAllJobs();
    }

    @GetMapping("/shutdownAllJobs")
    public void shutsdownAlljobs(){
        quartzJobManager.shutdownAllJobs();
    }

    @GetMapping("/updateJob")
    public void updateJob(){
        quartzJobManager.updataJob("demoTest1Task","newspaper","0/2 * * * * ?");
    }

    @GetMapping("/getAllJobs")
    public void getAllJobs(){
        List<Map<String, Object>> jobs = quartzJobManager.getAllJobs();
        jobs.stream().forEach(job -> System.out.println(job));
    }
}
