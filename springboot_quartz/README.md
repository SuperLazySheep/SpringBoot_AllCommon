
# Quartz（定时任务框架）（文档待完成）

[TOC]

#### 1.什么Quartz

> ​	1.job - 任务 - 你要什么事？
>
> ​	2.Trigger - 触发器 - 你什么时候去做？
>
> ​	3.Scheduler - 任务调度 - 你什么时候需要做什么事？



#### 2.为什么学习Quartz

#### 3.Quartz解决了什么问题

#### 4.使Quartz应用在项目

> 首先在企业级应用中，一般会有3个类：
>
> QuartzConfig（结合我们的Quartz.properties来考虑的一个配置类。①创建SchedulerFactoryBean对象，加载我们的配置。②根据上面的bean对象来创建Scheduler对象）。
>
> QuartzJobFactory（这个类是解决我们的注入某些Taskr任务类空指针问题，在创建这个Job是注入它）。
>
> QuartzJobmanager（是进行任务管理，而我们的触发器Trigger是管理我们的任务，注入Scheduler，构建job信息，根据cron创建表达式构建器，再创建Trigger触发器，最后调度我们的Scheduler）

#### 5.学习了quartz，可以应对那些面试













































































