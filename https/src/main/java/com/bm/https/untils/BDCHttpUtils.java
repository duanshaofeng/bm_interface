package com.bm.https.untils;

import com.alibaba.fastjson.JSONObject;
import com.bm.https.controller.bdc.BDCInterfaceController;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Key;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class BDCHttpUtils {



    private Logger logger = LoggerFactory.getLogger(BDCHttpUtils.class);

    @Value("${bdc.privatekey}")
    private String privatekey;
    @Value("${bdc.publickey}")
    private String publickey;
    @Value("${bdc.encry}")
    private String entry;




    public Object bdcPost(String url ,String params){
        try{
          return  sendPost(url,params);
           /* System.out.println(entry);
            return */
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public String getFormat(){
        try{
            String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            return entry+format;
        }catch (Exception e){
            e.printStackTrace();
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
    public Object sendPost(String url, String params) throws Exception{
        JSONObject jsonObject = null;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;

        try{

            String encrypt = RsaUtil.encryptByPublicKey(publickey,getFormat());
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
            logger.info(encrypt);
            post.addHeader("BDCDJXXGX",encrypt);



            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            StringBuffer paramsBuf = new StringBuffer() ;
//"ZPviJ6bZdMs+jc+InEI7hdlAALT2FHABJE6CM6EyGgs9mE+deX/MAC3ZCYTcMDPStKR85Prb724emhtgyhGYXJPzp42YqZyUAEeI1QSeFIcLm/qgRE5JeNfgwq+Lb9/ZKmKrQJLHwKGQgrRmkWPQPtgObDDIauw8rYqfejde+5k="
            nvps.add(new BasicNameValuePair("QD", RsaUtil.encryptByPublicKey(publickey,params)));
           // paramsBuf.append("&").append("QD").append("=").append(RSAUtils.encryptByPublicKey(params, publicKey)) ;
            logger.info(RsaUtil.encryptByPublicKey(publickey,params));
            post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

            /**
                    * 设置请求的内容
                    */

            /**
             * 设置请求的报文头部的编码
             */
             post.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
            /**
             * 设置请求的报文头部的编码
             */
            //  post.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
            /**
             * 执行post请求
             */
            response = client.execute(post);
            /**
             * 获取响应码
             */
            int statusCode = response.getStatusLine().getStatusCode();
           /* HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            StringBuilder sb = new StringBuilder();
            String line;

            BufferedReader br = new BufferedReader(new InputStreamReader(content));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String str = sb.toString();
            logger.info(str+"fanhui");*/
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
                //LOGGER.error("HttpClientService-line: {}, errorMsg：{}", 146, "POST请求失败！");
                return "error"+statusCode;
            }
        }catch (Exception e){
            //LOGGER.error("HttpClientService-line: {}, Exception：{}", 149, e);
            e.printStackTrace();
            return "error";
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
