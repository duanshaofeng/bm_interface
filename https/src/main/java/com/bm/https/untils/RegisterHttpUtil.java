package com.bm.https.untils;

import com.alibaba.fastjson.JSONObject;
import com.bm.https.register.PublicInterfaceController;
import jdk.nashorn.internal.runtime.logging.DebugLogger;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 统一注册接口工具类
 */


public class RegisterHttpUtil {



    private static Logger logger = LoggerFactory.getLogger(RegisterHttpUtil.class);

    public static Object sendPost(String params,String head,String url){
        try{
            logger.info("公共请求开始=========================");
            logger.info("公共请求 请求连接： {}", url);
            logger.info("公共请求 参数： {}", params);
            logger.info("公共请求 请求头： {}", head);

            Map param = JSONObject.parseObject(params, Map.class);
            Map heads = JSONObject.parseObject(head, Map.class);
            String contentType = (String) heads.get("Content-Type");
            if(contentType!=null){
                if(contentType.contains("json")){
                    logger.info("公共请求结束=========================json");
                    return sendPostByJson(url,params);
                }else{
                    logger.info("公共请求结束=========================form");
                    return sendPostByForm(url,param);
                }
            }else{
                logger.info("公共请求结束=========================form");
                return sendPostByForm(url,param);
            }

        }catch(Exception e){
            logger.error("公共请求异常 异常信息：{}",e.getMessage());
            return null;
        }

    }

    /**
     * 发送POST请求
     * @param url
     * @param params
     * @return JSON或者字符串
     * @throws Exception
     */
    private static Object sendPostByForm(String url, Map<String,String> params) throws Exception{
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
             * 设置请求的报文头部的编码
             */
            post.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
            /**
             * 设置请求的报文头部的编码
             */
             post.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
            /**
             * 执行post请求
             */
            response = client.execute(post);
            /**
             * 获取响应码
             */
            int statusCode = response.getStatusLine().getStatusCode();
            /**
             * 通过EntityUitls获取返回内容
             */
            String result = EntityUtils.toString(response.getEntity(),"UTF-8");
            /**
             * 转换成json,根据合法性返回json或者字符串
             */
            return result;
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

    private static String getLink(Map<String,Object> map,String url){
        String link = "";
        if(map!=null&&map.size()>0){

            Set<String> keySet = map.keySet();
            for (String key :
                    keySet) {
                if(StringUtils.isEmpty(map.get(key))){
                    continue;
                }
                link += key +  "=" + map.get(key) + "&";
            }
            if(link!=""){
                link = "?"+link.substring(0,link.length()-1);
            }

        }


        return url + link;
    }

    /**
     * 发送GET请求
     * @param url   请求url
     * @param
     * @return JSON或者字符串
     * @throws Exception
     */
    public static Object sendGet(String url,String params,String head) throws Exception{
        JSONObject jsonObject = null;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try{
            /**
             * 创建HttpClient对象
             */
            client = HttpClients.createDefault();
            /**
             * 创建HttpGet
             */
            Map<String,Object> map = JSONObject.parseObject(params, Map.class);
            Map<String,Object> heads = JSONObject.parseObject(head, Map.class);
            url = getLink(map,url);
            HttpGet httpGet = new HttpGet(url);
            /**
             * 请求服务
             */
            response = client.execute(httpGet);
            /**
             * 获取响应吗
             */
            int statusCode = response.getStatusLine().getStatusCode();

            HttpEntity entity = response.getEntity();
            /**
             * 通过EntityUitls获取返回内容
             */
            String result = EntityUtils.toString(entity,"UTF-8");
            return result;
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            response.close();
            client.close();
        }
        return null;
    }

    /**
     * 发送POST请求
     * @param url
     * @param json
     * @return JSON或者字符串
     * @throws Exception
     */
    private static Object sendPostByJson(String url, String json) throws Exception{
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
            StringEntity entity = new StringEntity(json,"UTF-8");
            /**
             * 设置请求的内容
             */
            post.setEntity(entity);
            /**
             * 设置请求的报文头部的编码
             */
            post.setHeader(new BasicHeader("Content-Type", "application/json; charset=utf-8"));
            post.setHeader(new BasicHeader("UID", "ZDZMD"));
            /**
             * 设置请求的报文头部的编码
             */
            post.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
            /**
             * 执行post请求
             */
            response = client.execute(post);
            /**
             * 获取响应码
             */
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println(statusCode);
            String result = EntityUtils.toString(response.getEntity(),"UTF-8");



            return result;


        }catch (Exception e){
            //LOGGER.error("HttpClientService-line: {}, Exception：{}", 149, e);
            e.printStackTrace();
        }finally {
            response.close();
            client.close();
        }
        return null;
    }

    /**
     * 组织请求参数{参数名和参数值下标保持一致}
     * @param params    参数名数组
     * @param values    参数值数组
     * @return 参数对象
     */
    private static List<NameValuePair> getParams(Object[] params, Object[] values){
        /**
         * 校验参数合法性
         */
        boolean flag = params.length>0 && values.length>0 &&  params.length == values.length;
        if (flag){
            List<NameValuePair> nameValuePairList = new ArrayList<>();
            for(int i =0; i<params.length; i++){
                nameValuePairList.add(new BasicNameValuePair(params[i].toString(),values[i].toString()));
            }
            return nameValuePairList;
        }else{
            // LOGGER.error("HttpClientService-line: {}, errorMsg：{}", 197, "请求参数为空且参数长度不一致");
        }
        return null;
    }

}
