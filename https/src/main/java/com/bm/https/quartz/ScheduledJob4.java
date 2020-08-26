package com.bm.https.quartz;

import com.bm.https.untils.ProvinceUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;

public class ScheduledJob4 implements Job {
    private SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("HH:mm:ss");
    }
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            ProvinceUtils.getdata();
            System.out.println("婚姻接口定时任务=====");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
