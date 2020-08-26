package com.bm.https.controller.bdc.fg.csapp.pojo;

/**
 * Created by PengHui on 2017/5/17.
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 请求数据中head信息
 */
public class RequestHead{
    public RequestHead(){
        super();
        //默认当前时间
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        request_date = formatter.format(currentTime);
        formatter = new SimpleDateFormat("mmssSSS");
        request_time = formatter.format(currentTime);

        trans_seq_no = UUID.randomUUID().toString();
    }

    //交易流水号
    private String trans_seq_no ;
    //请求日期
    private String request_date;
    //请求日期
    private String request_time;
    //系统识别号
    private String system_id = "" ;

    public String getTrans_seq_no() {
        return trans_seq_no;
    }

    public void setTrans_seq_no(String trans_seq_no) {
        this.trans_seq_no = trans_seq_no;
    }

    public String getRequest_date() {
        return request_date;
    }

    public void setRequest_date(String request_date) {
        this.request_date = request_date;
    }

    public String getRequest_time() {
        return request_time;
    }

    public void setRequest_time(String request_time) {
        this.request_time = request_time;
    }

    public String getSystem_id() {
        return system_id;
    }

    public void setSystem_id(String system_id) {
        this.system_id = system_id;
    }
}