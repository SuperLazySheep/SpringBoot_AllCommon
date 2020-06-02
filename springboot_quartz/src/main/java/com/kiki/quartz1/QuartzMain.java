//package com.kiki.quartz1;
//
//import org.quartz.*;
//import org.quartz.impl.StdSchedulerFactory;
//
///**
// * @author kiki
// */
//public class QuartzMain {
//    public static void main(String[] args) throws SchedulerException {
//        // 1. 创建 job对象： 你要什么事？
//        JobDetail job = JobBuilder.newJob(JobDemo.class).build();
//
//        /**
//         * 简单的trigger出触发时间： 通过Quartz提供一个方法来完成简单的重复调用
//         * corn Trigger： 按照cron的表达式来给定触发的时间
//         */
//        //2. 创建Trigger对象： 在什么时间做？
//        // 现在使用的就是trigger自带的触发时间  repeatSecondlyForever() 每秒执行一.次
//      //  SimpleTrigger trigger = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.repeatSecondlyForever()).build();
//        // 现在用的cron表达式 每两秒执行一次
//        CronTrigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?")).build();
//
//        //3. 创建scheduler对象 什么时间做什么事。
//        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//        scheduler.scheduleJob(job,trigger);
//        // 4. 启动
//        scheduler.start();
//    }
//
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
