//package com.kiki.quartz2;
//
//import org.quartz.spi.TriggerFiredBundle;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
//import org.springframework.scheduling.quartz.SpringBeanJobFactory;
//import org.springframework.stereotype.Component;
//
///**
// * @author kiki
// */
//@Component
//public class QuartzJobFactory extends SpringBeanJobFactory {
//
//    @Autowired
//    private AutowireCapableBeanFactory capableBeanFactory;
//
//    /**
//     * 解决任务类注入空指针问题
//     * @param bundle
//     * @return
//     * @throws Exception
//     */
//    @Override
//    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
//        Object jobInstance = super.createJobInstance(bundle);
//        capableBeanFactory.autowireBean(jobInstance);
//        return jobInstance;
//    }
//}