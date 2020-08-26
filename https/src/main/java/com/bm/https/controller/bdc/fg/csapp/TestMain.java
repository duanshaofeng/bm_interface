package com.bm.https.controller.bdc.fg.csapp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

/**
 * Created by PengHui on 2018/1/22.
 */
@SpringBootApplication
public class TestMain {

    public static Logger log = LoggerFactory.getLogger(TestMain.class);
    public static void main(String[] args) throws Exception {
        //配置
        ClientCer clientCer = new ClientCer();
       clientCer.setPfxFileClassPath("/pfx/client.pfx");  //或者使用 clientCer.setPfxFilePath("d:/client.pfx");
       // clientCer.setPfxFilePath("d:/client.pfx");
        clientCer.setPassword("123456");
        clientCer.setServerPublicKeyStr("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDRaN5cHk59Kmiz/pAUfg9LcnH4OzYt409Jw8EU8ir+VDyi9vZeBxZJ5p/NEC5dkIdAVCLA7Gh5aXDRFDXa86Fv167g6/yk7P+UvESbufVsrnJwpPNfYAGvHjRkRJcyjijdIubsQqL0Fy3MNKdmK1YtD6P8rCac1TKUcRQDXrr4kQIDAQAB");

        //请求参数
        HashMap<String,Object> requestParamJsonMap = new HashMap<>();
        requestParamJsonMap.put("YSZH","驻房字第20110061号");
        String url = "http://59.207.237.1:8795/bitsADI/YSXKZLB";

        String getResult =""; /*ClientCAUtil.sendRequest(clientCer,"GET",url,requestParamJsonMap,"client");*/
        log.info("GET请求结果：{}",getResult);
//        String postResult = ClientCAUtil.sendRequest(clientCer,"POST",url,requestParamJsonMap,"client");
//        log.info("POST请求结果：{}",postResult);

//            // 获取公钥 会报异常，属于正常
//        getPFXPublicKey();
    }


    public  static void getPFXPublicKey(){
        ClientCer clientCer = new ClientCer();
        clientCer.setPfxFileClassPath("/pfx/client.pfx");  //或者使用 clientCer.setPfxFilePath("d:/client.pfx");
        clientCer.setPassword("123456");
        System.out.println("client.pfx公钥为："+clientCer.getSelfPublickeyString());
    }

}
