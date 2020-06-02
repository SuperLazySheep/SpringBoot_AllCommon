package com.kiki.quartz3;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import javax.sql.DataSource;

/**
 * @author kiki
 */
@Configuration
public class QuartzConfig {
    
    @Autowired
    private QuartzJobFactory quartzJobFactory;
    @Autowired
    private DataSource dataSource;
    
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        // 延长启动
        schedulerFactoryBean.setStartupDelay(1);
        //加载配置
        schedulerFactoryBean.setConfigLocation(new ClassPathResource("/quartz.properties"));
        schedulerFactoryBean.setDataSource(dataSource);
        schedulerFactoryBean.setJobFactory(quartzJobFactory);
        return schedulerFactoryBean;
    }

    @Bean
    public Scheduler scheduler(){
        return schedulerFactoryBean().getScheduler();
    }
}
