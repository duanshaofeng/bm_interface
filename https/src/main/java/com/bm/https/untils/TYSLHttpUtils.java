package com.bm.https.untils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPublicKey;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class TYSLHttpUtils {


    private Logger logger = LoggerFactory.getLogger(TYSLHttpUtils.class);

    /**
     * 发送POST请求
     * @param url
     * @param params
     * @return JSON或者字符串
     * @throws Exception
     */
    public Object sendPost(String url, String params) throws Exception{
        JSONObject jsonObject = null;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try{
            /**
             *  创建一个httpclient对象
             */
            client = HttpClients.createDefault();
            /**
             * 创建一个post对象
             */
            HttpPost post = new HttpPost(url);
            /**
             * 包装成一个Entity对象
             */

            StringEntity entity = new StringEntity(params, "UTF-8");

            /**
             * 设置请求的内容
             */
            logger.info("请求开始了================参数："+params);
            post.setEntity(entity);
            logger.info("请求进行中================");
            /**
             * 设置请求的报文头部的编码
             */
            post.setHeader(new BasicHeader("Content-Type", "application/json; charset=utf-8"));
            /**
             * 设置请求的报文头部的编码
             */
            // post.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
            /**
             * 执行post请求
             */
            response = client.execute(post);
            /**
             * 获取响应码
             */
            int statusCode = response.getStatusLine().getStatusCode();
            logger.info("请求结束================请求状态码"+statusCode);
            if (200 == statusCode){
                /**
                 * 通过EntityUitls获取返回内容
                 */
                String result = EntityUtils.toString(response.getEntity(),"UTF-8");
                /**
                 * 转换成json,根据合法性返回json或者字符串
                 */

                return result;

            }else{
                return null;
            }
        }catch (Exception e){
            return null;
        }finally {
            if(response!=null){
                response.close();
            }
            if(client!=null){
                client.close();
            }

        }

    }



    /**
     * 发送POST请求
     * @param url
     * @param params
     * @return JSON或者字符串
     * @throws Exception
     */
    public Object sendPost2(String url, Map<String,String> params) throws Exception{
        JSONObject jsonObject = null;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try{
            /**
             *  创建一个httpclient对象
             */
            client = HttpClients.createDefault();
            /**
             * 创建一个post对象
             */
            HttpPost post = new HttpPost(url);
            /**
             * 包装成一个Entity对象
             */
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            StringBuffer paramsBuf = new StringBuffer() ;
            for(Map.Entry<String, String> e : params.entrySet()) {
                nvps.add(new BasicNameValuePair(e.getKey(), e.getValue()));
                paramsBuf.append("&").append(e.getKey()).append("=").append(e.getValue()) ;
            }
            post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));


            /**
             * 设置请求的内容
             */
            logger.info("请求开始了================参数："+params);
            logger.info("请求进行中================");
            /**
             * 设置请求的报文头部的编码
             */
            post.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
            /**
             * 设置请求的报文头部的编码
             */
            // post.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
            /**
             * 执行post请求
             */
            response = client.execute(post);
            /**
             * 获取响应码
             */
            int statusCode = response.getStatusLine().getStatusCode();
            logger.info("请求结束================请求状态码"+statusCode);
            if (200 == statusCode){
                /**
                 * 通过EntityUitls获取返回内容
                 */
                String result = EntityUtils.toString(response.getEntity(),"UTF-8");
                /**
                 * 转换成json,根据合法性返回json或者字符串
                 */

                return result;

            }else{
                return null;
            }
        }catch (Exception e){
            return null;
        }finally {
            if(response!=null){
                response.close();
            }
            if(client!=null){
                client.close();
            }

        }

    }

}
