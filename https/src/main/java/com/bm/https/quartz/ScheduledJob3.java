package com.bm.https.quartz;

import com.bm.https.untils.ProvinceUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduledJob3 implements Job {
    private SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("HH:mm:ss");
    }
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            ProvinceUtils.getshenfen();
            //System.out.println("ProvinceUtils.getshenfen();");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
