package com.bm.https.controller.bdc.fg.csapp;


import com.bm.https.controller.bdc.fg.csapp.pojo.RequestData;
import com.bm.https.controller.bdc.fg.csapp.pojo.RequestJSONObject;
import com.bm.https.controller.bdc.fg.csapp.pojo.ResponseData;
import com.bm.https.controller.bdc.fg.csapp.pojo.ResponseJSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by PengHui on 2017/5/16.
 */
@Component
public class ClientCAUtil {

    static Logger log = LoggerFactory.getLogger(ClientCAUtil.class);




    /**
     * 发送请求
     * @param
     * @param method
     * @param url
     * @param requestParamJsonMap
     * @param systemId
     * @return
     * @throws Exception
     */
    public  String sendRequest( String method, String url, Map<String,Object> requestParamJsonMap, String systemId) throws Exception {
        ClientCer clientCer = new ClientCer();
        clientCer.setPfxFileClassPath("/pfx/client.pfx");  //或者使用 clientCer.setPfxFilePath("d:/client.pfx");
        clientCer.setPassword("123456");
        clientCer.setServerPublicKeyStr("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDRaN5cHk59Kmiz/pAUfg9LcnH4OzYt409Jw8EU8ir+VDyi9vZeBxZJ5p/NEC5dkIdAVCLA7Gh5aXDRFDXa86Fv167g6/yk7P+UvESbufVsrnJwpPNfYAGvHjRkRJcyjijdIubsQqL0Fy3MNKdmK1YtD6P8rCac1TKUcRQDXrr4kQIDAQAB");

        ObjectMapper objectMapper = new ObjectMapper();

        RequestJSONObject requestJSONObject = new RequestJSONObject();
        RequestData requestData = new RequestData();
        requestData.setBody(requestParamJsonMap);
        requestData.getHead().setSystem_id(systemId);


        String requestDataJson = objectMapper.writeValueAsString(requestData);
        log.info("请求数据明文:{}", requestDataJson);

        //加密
        requestDataJson = Base64.encode(clientCer.encrypt(requestDataJson.getBytes()));
        log.info("请求数据密文:{}", requestDataJson);
        //签名
        String requestSignStr = Base64.encode(clientCer.sign(requestDataJson.getBytes()));
        log.info("请求签名字符串：{}", requestSignStr);

        //生成总的JSON
        requestJSONObject.setData(requestDataJson);
        requestJSONObject.setSign(requestSignStr);
        String requestJson = "";
        try {
            requestJson = objectMapper.writeValueAsString(requestJSONObject);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info("请求JSON:{}", requestJson);


        //发送请求获取返回JSON
        String responseJson = "";
        if (method.equalsIgnoreCase("GET")) {
            responseJson = sendHttpGetRequest(clientCer,requestJson, url);
        } else if (method.equalsIgnoreCase("POST")) {
            responseJson = sendHttpPostRequest(requestJson, url);
        }


        String sha1 = clientCer.sha1(requestJson);
        log.info("请求JSON摘要：{}", sha1);


        ResponseJSONObject responseJSONObject = null;
        responseJSONObject = objectMapper.readValue(responseJson, ResponseJSONObject.class);


        String responseSignStr = clientCer.urlDecode(responseJSONObject.getSign());
        String responseDataJson = clientCer.urlDecode(responseJSONObject.getData());

        log.info("响应签名字符串：{}", responseSignStr);
        log.info("响应数据密文:{}", responseDataJson);

        boolean verifySignResult = clientCer.verify(responseDataJson.getBytes(), clientCer.base64Decode(responseSignStr));

        log.info("验证结果：{}", verifySignResult);

        if (!verifySignResult) {
            //验签失败
            throw new Exception("响应信息验证签名未通过");
        }

        //数据解密
        String dataJsonPlainText = new String(clientCer.decrypt(clientCer.base64Decode(responseDataJson)));
        log.info("响应数据明文：{}", dataJsonPlainText);

        ResponseData responseData = null;


        responseData = objectMapper.readValue(dataJsonPlainText,ResponseData.class);
        String responseSHA1 = responseData.getHead().getSha1();
        if (!sha1.equalsIgnoreCase(responseSHA1)) {
            throw new Exception("响应信息中原请求摘要不正确");
        }
        return objectMapper.writeValueAsString(responseData.getBody());
    }

    public  String sendHttpGetRequest(ClientCer clientCer, String requestJson, String url) {

        String newUrl = url.contains("?") ? url : url + "?";
        //去除末尾的 &
        while (newUrl.endsWith("&")) {
            newUrl = newUrl.substring(0, newUrl.length() - 1);
        }
        if (!newUrl.endsWith("?")) {
            newUrl += "&&";
        }

        //将数据写进行URLConde转码，并写道URL上用于发送请求
        newUrl += "requestJSON" + "=" + clientCer.urlEncode(requestJson);

        return new RestTemplate().getForObject(newUrl, String.class);
    }

    public   String sendHttpPostRequest(String requestJson, String url) {
        HttpHeaders httpHeaders = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json");
        httpHeaders.setContentType(type);
        HttpEntity<String> entity = new HttpEntity<>(requestJson, httpHeaders);
        String result = new RestTemplate()
                .postForObject(url, entity, String.class);
        return result;
    }

}
