package com.bm.https.quartz;

import com.bm.https.Test;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduledJob implements Job {
    private SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("HH:mm:ss");
    }
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            Test.getData();
            //System.out.println("Test.getData()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
