//package com.kiki.quartz2;
//
//import org.quartz.Scheduler;
//import org.quartz.SchedulerException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
//import org.springframework.scheduling.quartz.JobDetailFactoryBean;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//
///**
// * @author kiki
// */
//@Configuration
//public class QuartzConfig {
//
//    /**
//     * 创建job对象
//     */
//    @Bean
//    public JobDetailFactoryBean jobDetailFactoryBean(){
//        JobDetailFactoryBean factory = new JobDetailFactoryBean();
//        // 关联我们自己的人物类
//        factory.setJobClass(JobDemo.class);
//        return factory;
//    }
//
//    /**
//     * 创建Trigger对象
//     */
//    @Bean
//    public CronTriggerFactoryBean cronTriggerFactoryBean(JobDetailFactoryBean jobDetail){
//        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
//        trigger.setCronExpression("0/2 * * * * ?");
//        trigger.setJobDetail(jobDetail.getObject());
//        return trigger;
//    }
//
//    /**
//     * 创建scheduler对象
//     */
//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean(CronTriggerFactoryBean trigger, QuartzJobFactory quartzJobFactory){
//        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
//        scheduler.setTriggers(trigger.getObject());
//        scheduler.setJobFactory(quartzJobFactory);
//        return scheduler;
//    }
//
//    @Autowired
//    private Scheduler scheduler;
//    public void test() throws SchedulerException {
//        scheduler.addJob(null,true);
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
