package com.bm.https.controller.bdc.fg.csapp.pojo;

/**
 * Created by PengHui on 2017/5/17.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 请求数据
 */
public class RequestData{

    private RequestHead head = new RequestHead();

    private Map<String,Object> body = new HashMap<>();

    public RequestHead getHead() {
        return head;
    }

    public void setHead(RequestHead head) {
        this.head = head;
    }

    public Map<String,Object> getBody() {
        return body;
    }

    public void setBody(Map<String,Object> body) {
        this.body = body;
    }



}