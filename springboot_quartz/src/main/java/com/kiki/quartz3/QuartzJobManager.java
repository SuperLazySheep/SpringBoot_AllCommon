package com.kiki.quartz3;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author kiki
 */
@Component
@Slf4j
public class QuartzJobManager {

    private static QuartzJobManager jobUtil;

    @Autowired
    private Scheduler scheduler;

    public QuartzJobManager(){
        log.info("init jonManager");
        jobUtil = this;
    }

    public static QuartzJobManager getInstance(){
        log.info("init jobManager");
        return QuartzJobManager.jobUtil;
    }

    /**
     * 创建job 无参数
     * @param clazz           任务类
     * @param jobName         任务名称
     * @param jobGroupName    任务所在组名称
     * @param cronExpression  cron表达式
     */
    public void addJob(Class clazz, String jobName, String jobGroupName, String cronExpression){
        addJob(clazz,jobName,jobGroupName,cronExpression,null);
    }

    /**
     * 创建job  有参数
     * @param clazz           任务类
     * @param jobName         任务名称
     * @param jobGroupName    任务所在组名称
     * @param cronExpression  cron表达式
     * @param argsMap         map形式参数
     */
    public void addJob(Class clazz, String jobName, String jobGroupName, String cronExpression, Map<String,Object> argsMap){
        try {
            // 启动调度器
            scheduler.start();
            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(((Job)clazz.newInstance()).getClass()).withIdentity(jobName,jobGroupName).build();
            // 表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName).withSchedule(scheduleBuilder).build();
            //获得JobDataMap, 写入数据
            if(argsMap != null){
                trigger.getJobDataMap().putAll(argsMap);
            }
            scheduler.scheduleJob(jobDetail,trigger);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 暂停Job
     * @param jobName     任务名
     * @param groupName   任务所在组名称
     */
    public void pauseJob(String jobName, String groupName){
        try {
            scheduler.pauseJob(JobKey.jobKey(jobName, groupName));
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 恢复job
     * @param jobName    任务名
     * @param groupName  任务所在组名称
     */
    public void resumeJob(String jobName, String groupName){
        try {
            scheduler.resumeJob(JobKey.jobKey(jobName, groupName));
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 更新任务   频率
     * @param jobName       任务名称
     * @param groupName     任务所在组名称
     * @param cron          cron表达式
     */
    public void updataJob(String jobName, String groupName, String cron){
        this.updateJob(jobName,groupName,cron,null);
    }

    /**
     * 更新任务       参数    频率
     * @param jobName       任务名称
     * @param groupName     任务所在组名称
     * @param cron          cron表达式
     * @param argsMap       参数
     */
    public void updateJob(String jobName, String groupName, String cron, Map<String,Object> argsMap){
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, groupName);
            // 表达式构建调度器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
            CronTrigger trigger = (CronTrigger)scheduler.getTrigger(triggerKey);
            // 按照新的cron 重新构建trigger
            trigger  = trigger.getTriggerBuilder().withSchedule(scheduleBuilder).build();
            // 修改 map
            if(argsMap != null){
                trigger.getJobDataMap().putAll(argsMap);
            }
            scheduler.rescheduleJob(triggerKey,trigger);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     *
     * @param jobName
     * @param groupName
     * @param argsMap
     */
    public void updateJob(String jobName, String groupName, Map<String,Object> argsMap){
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, groupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            trigger.getJobDataMap().putAll(argsMap);
            scheduler.rescheduleJob(triggerKey,trigger);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 删除任务
     * @param jobName     任务名
     * @param groupName   任务所在组名称
     */
    public void deleteJob(String jobName, String groupName){
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobName, groupName));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobName, groupName));
            scheduler.deleteJob(JobKey.jobKey(jobName, groupName));
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 开启所有定时任务
     */
    public void startAllJobs(){
        try {
            scheduler.start();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 关闭所有定时任务
     */
    public void shutdownAllJobs(){
        try {
            if(!scheduler.isShutdown()){
                // 使用这个方法后，在次进行start会有异常，不能使用其他方法，  可以使用暂停
                //scheduler.shutdown();
                scheduler.standby();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Map<String,Object>> getAllJobs(){
        GroupMatcher<JobKey> matcher = GroupMatcher.anyGroup();
        List<Map<String,Object>> jobList = new ArrayList<>();
        try {
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            for (JobKey jobKey : jobKeys){
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers){
                    HashMap<String, Object> job = new HashMap<>();
                    job.put("jobName",jobKey.getName());
                    job.put("jobGroupName",jobKey.getGroup());
                    job.put("trigger",trigger.getKey());
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    job.put("jobStatus",triggerState.name());
                    if(trigger instanceof CronTrigger){
                        CronTrigger cronTrigger = (CronTrigger) trigger;
                        job.put("cronExpression",cronTrigger.getCronExpression());
                    }
                    jobList.add(job);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return jobList;
    }
}

























