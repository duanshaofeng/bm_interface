package com.bm.https.quartz;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MyScheduler {

    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;
    public void scheduleJobs() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
      startJob1(scheduler);
        /*  startJob2(scheduler);*/
      /*  startJob3(scheduler);*/
        //startJob4(scheduler);

    }
   private void startJob1(Scheduler scheduler) throws SchedulerException{
        JobDetail jobDetail = JobBuilder.newJob(ScheduledJob.class) .withIdentity("job1", "group1").build();
        //CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 0 *//*1 * ?");
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 20 * * ? ");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1") .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }
  /*  private void startJob2(Scheduler scheduler) throws SchedulerException{
        JobDetail jobDetail = JobBuilder.newJob(ScheduledJob2.class) .withIdentity("job2", "group2").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 18 * * ? ");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group2") .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }*/
  /* private void startJob3(Scheduler scheduler) throws SchedulerException{
        JobDetail jobDetail = JobBuilder.newJob(ScheduledJob3.class) .withIdentity("job3", "group3").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 1 * * ? ");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger3", "group3") .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }*/

   /* private void startJob4(Scheduler scheduler) throws SchedulerException{
        JobDetail jobDetail = JobBuilder.newJob(ScheduledJob4.class) .withIdentity("job4", "group4").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 1 * * ? ");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger4", "group4") .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }*/
}
