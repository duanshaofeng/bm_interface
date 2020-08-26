package com.bm.https.untils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

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
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * 省调用接口工具类
 */
public class ProvinceUtils {

    /**
     * 密钥生成【核心网关】
     *
     * @param appKey 授权码
     * @param appId  请求者标识
     * @param rtime  请求时间戳
     * @return 加密后的 sign
     */
    public static String gatewaySignEncode(String appId, String appKey, String rtime)
            throws Exception {
        String inputString = appId + rtime;
        return encode(appKey, inputString);
    }

    private static String encode(String appKey, String inputStr) throws Exception {
        String sign;
        Mac hmacSha256 = Mac.getInstance("HmacSHA256");
        byte[] keyBytes = appKey.getBytes("UTF-8");
        hmacSha256.init(new SecretKeySpec(keyBytes, 0, keyBytes.length,
                "HmacSHA256"));
        byte[] hmacSha256Bytes = hmacSha256.doFinal(inputStr.getBytes("UTF-8"));
        sign = new String(Base64.encodeBase64(hmacSha256Bytes), "UTF-8");
        return sign;
    }


    /**
     * 加密 1.构造密钥生成器 2.根据 ecnodeRules 规则初始化密钥生成器 3.产生密钥 4.创建和初始化密码器 5.内容加密 6.返回字符串
     */
    public static String AESEncode(String encodeRules, String content) {
        // 初始化向量,必须 16 位
        String ivStr = "AESCBCPKCS5Paddi";
        try {
            // 1.构造密钥生成器，指定为 AES 算法,不区分大小写
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            //新增下面两行，处理 Linux 操作系统下随机数生成不一致的问题
            SecureRandom secureRandom =
                    SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(encodeRules.getBytes());
            keygen.init(128, secureRandom);
            // 3.产生原始对称密钥
            SecretKey original_key = keygen.generateKey();
            // 4.获得原始对称密钥的字节数组
            byte[] raw = original_key.getEncoded();
            // 5.根据字节数组生成 AES 密钥
            SecretKey key = new SecretKeySpec(raw, "AES");
            // 6.根据指定算法 AES 自成密码器
           Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            // 7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的 KEY
            ////指定一个初始化向量 (Initialization vector，IV)， IV 必须是 16 位
            cipher.init(Cipher.ENCRYPT_MODE, key, new
                    IvParameterSpec(ivStr.getBytes("UTF-8")));
            // 8.获取加密内容的字节数组(这里要设置为 utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte[] byte_encode = content.getBytes("utf-8");
            // 9.根据密码器的初始化方式--加密：将数据加密
            byte[] byte_AES = cipher.doFinal(byte_encode);
            // 10.将加密后的数据转换为字符串
            // 这里用 Base64Encoder 中会找不到包
            // 解决办法：
            // 在项目的 Build path 中先移除 JRE System Library，再添加库 JRE System
            // Library，重新编译后就一切正常了。
            String AES_encode = new String(new
                    BASE64Encoder().encode(byte_AES));
// 11.将字符串返回
            return AES_encode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        // 如果有错就返加 nulll
        return null;
    }

    /**
     * 解密 解密过程： 1.同加密 1-4 步 2.将加密后的字符串反纺成 byte[]数组 3.将加密内
     * 容解密
     */
    public static String AESDncode(String encodeRules, String content) {
        // 初始化向量,必须 16 位
        String ivStr = "AESCBCPKCS5Paddi";
        try {
            // 1.构造密钥生成器，指定为 AES 算法,不区分大小写
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            //新增下面两行，处理 Linux 操作系统下随机数生成不一致的问题
            SecureRandom secureRandom =
                    SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(encodeRules.getBytes());
            keygen.init(128, secureRandom);
            // 3.产生原始对称密钥
            SecretKey original_key = keygen.generateKey();
            // 4.获得原始对称密钥的字节数组
            byte[] raw = original_key.getEncoded();
            // 5.根据字节数组生成 AES 密钥
            SecretKey key = new SecretKeySpec(raw, "AES");
            // 6.根据指定算法 AES 自成密码器
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            // 7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的 KEY
            ////指定一个初始化向量 (Initialization vector，IV)， IV 必须是 16 位
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivStr.getBytes("UTF-8")));
            // 8.将加密并编码后的内容解码成字节数组
            byte[] byte_content = new BASE64Decoder().decodeBuffer(content);
            /*
             * 解密
             */
            byte[] byte_decode = cipher.doFinal(byte_content);
            String AES_decode = new String(byte_decode, "utf-8");
            return AES_decode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        // 如果有错就返加 nulll
        return null;
    }


    /**
     * http 请求过程
     *
     * @param requestUrl       请求的 url，在服务调用过程中 url 为获取 token 的 url（格式为 ip:port/auth/token）或者是服务调用的 url
     * @param requestMethod    获取 token 时，请求方法为 POST；调用服务是请求方法依据服务注册时定义。
     * @param appIdorSecretKey 获取 token 时该参数为 appId；抵用服务时该参数为
     *                         SecretKey
     * @param currTime         该参数为当前时间
     * @param sign             该参数为 head 参数 gateway_sig，由秘钥生成方法
     *                         gatewaySignEncode 生成。
     * @return
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod,
                                         String appIdorSecretKey,
                                         String currTime, String sign) throws Exception {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        InputStream inputStream = null;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConnection =
                    (HttpURLConnection) url.openConnection();
            httpUrlConnection.setRequestMethod(requestMethod);
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setDoInput(true);
            httpUrlConnection.setRequestProperty("gateway_appid",
                    appIdorSecretKey);
            httpUrlConnection.setRequestProperty("gateway_rtime",
                    currTime);
            httpUrlConnection.setRequestProperty("gateway_sig", sign);
            System.out.print(appIdorSecretKey+"  "+currTime+"    "+sign);
            httpUrlConnection.connect();
            inputStream = httpUrlConnection.getInputStream();
            InputStreamReader inputStreamReader = new
                    InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferReader = new
                    BufferedReader(inputStreamReader);
            String str = null;
            while ((str = bufferReader.readLine()) != null) {
                buffer.append(str);
            }
            String buffer2str = buffer.toString();
            jsonObject = JSONObject.parseObject(buffer2str);
            bufferReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            httpUrlConnection.disconnect();
        } catch (Exception ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return jsonObject;
    }


    public static String getLink(Map<String, Object> map, String url) {
        String link = "";
        if (map != null && map.size() > 0) {

            Set<String> keySet = map.keySet();
            for (String key :
                    keySet) {
                if (StringUtils.isEmpty(map.get(key))) {
                    continue;
                }
                link += key + "=" + map.get(key) + "&";
            }
            if (link != "") {
                link = "?" + link.substring(0, link.length() - 1);
            }

        }


        return url + link;
    }

    public static Object httpData(String httpMethod, Map<String, Object> map, String url, String appId, String appKey) throws Exception {
        // 参数设定

        String currTime = System.currentTimeMillis() + "";
        // 生成 access_token 秘钥的 url
        String generateTokenUrl = "http://59.207.107.18:5000/auth/token";
        // 服务调用的 url
        String invokingServiceUrl =
                getLink(map, url);
        System.out.println(invokingServiceUrl);
        // 服务调用的请求方法
        String invokingServiceRequestMethod = httpMethod;
        // 1.获取 access_token。
        String sign = gatewaySignEncode(appId, appKey, currTime);
        JSONObject jsonObject = httpRequest(generateTokenUrl, "POST", appId,
                currTime, sign);
        JSONObject jsonObjectBody =
                JSONObject.parseObject(jsonObject.getString("body"));
        if (jsonObjectBody != null) {
            String accessToken = jsonObjectBody.getString("access_token");
            // 2.在获得服务调用秘钥 access_token 后，根据自己的 appKey 使用AES解密算法对返回值进行解密，最终获得真正秘钥的过程。
            String secretKey = AESDncode(appKey, accessToken);
            // 3.调用服务，获取服务的 json 数据。
            String sign1 = gatewaySignEncode(appId, secretKey, currTime);
            JSONObject serviceJsonData = httpRequest(invokingServiceUrl,
                    invokingServiceRequestMethod, appId, currTime, sign1);
            System.out.println(serviceJsonData);
            String codeValue = serviceJsonData.getString("code");
            if (codeValue.equals("200")) {
                return serviceJsonData;
            } else {
                System.out.println("获取服务失败，请查看响应数据中的错误码和错误信");
                return serviceJsonData;
            }
        } else {
            System.out.println("获取 token 错误，请查看响应数据中的错误码和错误信息。");
            return jsonObjectBody;
        }


    }

    public static Object httpData2(Map<String, Object> map, String url, String appId, String appKey) {
        try {


            String currTime = System.currentTimeMillis() + "";
            String generateTokenUrl = "http://59.207.107.18:5000/auth/token";
            String sign = gatewaySignEncode(appId, appKey, currTime);
            JSONObject jsonObject = httpRequest(generateTokenUrl, "POST", appId, currTime, sign);
            JSONObject jsonObjectBody = JSONObject.parseObject(jsonObject.getString("body"));
            String invokingServiceUrl = getLink(map, url);
            System.out.println(invokingServiceUrl);
            if (jsonObjectBody != null) {
                String accessToken = jsonObjectBody.getString("access_token");
                System.out.println(accessToken);
                String secretKey = AESDncode(appKey, accessToken);
                String sign1 = gatewaySignEncode(appId, secretKey, currTime);
                Object o = HttpUtil.sendGet(invokingServiceUrl, appId, currTime, sign1);
                return o;

            }
            return jsonObjectBody;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    public static Object httpDatapost(Map<String, String> map, String url, String appId, String appKey) {
        try {


            String currTime = System.currentTimeMillis() + "";
            String generateTokenUrl = "http://59.207.107.18:5000/auth/token";
            String sign = gatewaySignEncode(appId, appKey, currTime);
            JSONObject jsonObject = httpRequest(generateTokenUrl, "POST", appId, currTime, sign);
            JSONObject jsonObjectBody = JSONObject.parseObject(jsonObject.getString("body"));

            if (jsonObjectBody != null) {
                String accessToken = jsonObjectBody.getString("access_token");
                System.out.println(accessToken);
                String secretKey = AESDncode(appKey, accessToken);
                String sign1 = gatewaySignEncode(appId, secretKey, currTime);
                Object o = sendPost2(url, map, appId, currTime, sign1);
                return o;

            }
            return jsonObjectBody;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    public static Object httpDatapostjson(String json, String url, String appId, String appKey) {
        try {


            String currTime = System.currentTimeMillis() + "";
            String generateTokenUrl = "http://59.207.107.18:5000/auth/token";
            String sign = gatewaySignEncode(appId, appKey, currTime);
            JSONObject jsonObject = httpRequest(generateTokenUrl, "POST", appId, currTime, sign);
            JSONObject jsonObjectBody = JSONObject.parseObject(jsonObject.getString("body"));

            if (jsonObjectBody != null) {
                String accessToken = jsonObjectBody.getString("access_token");
                System.out.println(accessToken);
                String secretKey = AESDncode(appKey, accessToken);
                String sign1 = gatewaySignEncode(appId, secretKey, currTime);
                Object o = sendPostByJson(url, json, appId, currTime, sign1);
                return o;

            }
            return jsonObjectBody;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    /**
     * 发送POST请求
     *
     * @param url
     * @param json
     * @return JSON或者字符串
     * @throws Exception
     */
    private static Object sendPostByJson(String url, String json, String appIdorSecretKey,
                                         String currTime, String sign) throws Exception {
        JSONObject jsonObject = null;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
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
            StringEntity entity = new StringEntity(json, "UTF-8");
            /**
             * 设置请求的内容
             */
            post.setEntity(entity);
            /**
             * 设置请求的报文头部的编码
             */
            post.setHeader(new BasicHeader("Content-Type", "application/json; charset=utf-8"));
            post.setHeader(new BasicHeader("gateway_appid", appIdorSecretKey));
            /**
             * 设置返回编码
             */
            post.setHeader(new BasicHeader("gateway_rtime", currTime));
            post.setHeader(new BasicHeader("gateway_sig", sign));
            post.setHeader(new BasicHeader("Basic", "Z3hzZDAxOjY2NjY2Ng=="));
            System.out.println("appId:" +
                    appIdorSecretKey+"   currTime:"+currTime+"   sign1:"+sign);
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
            System.out.println(statusCode);
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");


            return result;


        } catch (Exception e) {
            //LOGGER.error("HttpClientService-line: {}, Exception：{}", 149, e);
            e.printStackTrace();
        } finally {
            response.close();
            client.close();
        }
        return null;
    }

    /**
     * 发送POST请求
     *
     * @param url
     * @param params
     * @return JSON或者字符串
     * @throws Exception
     */
    public static Object sendPost2(String url, Map<String, String> params, String appIdorSecretKey,
                                   String currTime, String sign) throws Exception {
        JSONObject jsonObject = null;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            /**
             *  创建一个httpclient对象
             */
            client = HttpClients.createDefault();
            /**
             * 创建一个post对象
             */
            HttpPost post = new HttpPost(url);
            /**
             * 设置请求的内容
             */
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            StringBuffer paramsBuf = new StringBuffer();
            for (Map.Entry<String, String> e : params.entrySet()) {
                nvps.add(new BasicNameValuePair(e.getKey(), e.getValue()));
                paramsBuf.append("&").append(e.getKey()).append("=").append(e.getValue());
            }
            post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            /**
             * 设置请求的报文头部的编码
             */
            post.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
            /**
             * 设置请求头部编码
             */
            post.setHeader(new BasicHeader("gateway_appid", appIdorSecretKey));
            post.setHeader(new BasicHeader("gateway_rtime", currTime));
            post.setHeader(new BasicHeader("gateway_sig", sign));
            System.out.println(appIdorSecretKey+"    "+currTime+"  "+sign);
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

            /**
             * 通过EntityUitls获取返回内容
             */
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            /**
             * 转换成json,根据合法性返回json或者字符串
             */

            return result;


        } catch (Exception e) {
            return null;
        } finally {
            if (response != null) {
                response.close();
            }
            if (client != null) {
                client.close();
            }

        }

    }

    public static void main(String[] args) {
        try {




          /*  String appId = "3b20a5fec2ba4d8992d61810c9d66ff9";
            String appKey ="M2IyMGE1ZmVjMmJhNGQ4OTkyZDYxODEwYzlkNjZmZjk6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("uniscid","9141000031741675X6");
            map.put("entname","中原银行股份有限公司");
           // map.put("wsdl","");
            map.put("regno","410000000034311");
            map.put("ws-SecurityUsername","0982e70000");
            map.put("ws-SecurityPassword","49102c769499416cadedce79cfc921bf");

            String jsonString = JSONObject.toJSONString(map);
            Object data = ProvinceUtils.httpData2(map, "http://59.207.107.18:5000/api/sa/ws/soap/opensservice",appId,appKey);
            System.out.println(data);*/

            /*String invokingServiceUrl =
                    "http://59.207.107.18:5000/api/sa/ws/soap/opensservice?uniscid=914117256973478985&entname=河南恒盈油脂有限公司&ws-SecurityUsername=d53f844edcc147b3a9be2eef2479848c&ws-SecurityPassword=ZDUzZjg0NGVkY2MxNDdiM2E5YmUyZWVmMjQ3OTg0OGM6MTIzNDU2&regno=411725000001923";
            String appId = "d53f844edcc147b3a9be2eef2479848c";
            String appKey =
                    "ZDUzZjg0NGVkY2MxNDdiM2E5YmUyZWVmMjQ3OTg0OGM6MTIzNDU2";*/

         /*   String invokingServiceUrl =
                    "http://59.207.107.18:5000/api/sa/ws/soap/opensservice?wsdl&uniscid=914117256973478985&entname=河南恒盈油脂有限公司&ws-SecurityUsername=3b20a5fec2ba4d8992d61810c9d66ff9&ws-SecurityPassword=M2IyMGE1ZmVjMmJhNGQ4OTkyZDYxODEwYzlkNjZmZjk6MTIzNDU2&regno=411725000001923";

            String appId = "3b20a5fec2ba4d8992d61810c9d66ff9";
            String appKey ="M2IyMGE1ZmVjMmJhNGQ4OTkyZDYxODEwYzlkNjZmZjk6MTIzNDU2";
            String currTime = System.currentTimeMillis() + "";
            String generateTokenUrl = "http://59.207.107.18:5000/auth/token";
            String sign = gatewaySignEncode(appId, appKey, currTime);
            JSONObject jsonObject = httpRequest(generateTokenUrl, "POST", appId,currTime, sign);
            JSONObject jsonObjectBody = JSONObject.parseObject(jsonObject.getString("body"));
            if (jsonObjectBody != null) {
                String accessToken = jsonObjectBody.getString("access_token");
                System.out.println(accessToken);
                String secretKey = AESDncode(appKey, accessToken);
                String sign1 = gatewaySignEncode(appId, secretKey, currTime);
                Object o = HttpUtil.sendGet(invokingServiceUrl, appId, currTime, sign1);
                System.out.println("服务数据结果:" +
                        o);

            }*/
            // getdata();
            //getshenfen();
           //getSheBaoInfo();
            //getFDCQCXInfo();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /**
     * 定时调用招生厅身份信息接口
     *
     * @throws Exception
     */
    public static void getshenfen() throws Exception {


        String appId = "3b20a5fec2ba4d8992d61810c9d66ff9";
        String appKey = "M2IyMGE1ZmVjMmJhNGQ4OTkyZDYxODEwYzlkNjZmZjk6MTIzNDU2";

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;
        PreparedStatement preparedStatement4 = null;
        PreparedStatement preparedStatement5 = null;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection("jdbc:oracle:thin:interfaceDB/interfaceDB@59.207.219.23:1521:orcl", "interfaceDB", "interfaceDB");
        conn.setAutoCommit(true);
        preparedStatement4 = conn.prepareStatement("select t.total from PUB_INTERFACERECORD t where t.interfacename = '身份信息' order by t.updatetime desc");

        ResultSet resultSet4 = preparedStatement4.executeQuery();
        int total = 0;
        if (resultSet4.next()) {
            total = resultSet4.getInt("total");
        }
        if (preparedStatement4 != null) {
            preparedStatement4.close();
        }


        preparedStatement2 = conn.prepareStatement(" select cardid,id from ( select rownum r,t.cardid,t.id from PUB_RK_PARM t where rownum <= 20000 and id > " + total + " order by id ASC) t where r>0");

        ResultSet resultSet = preparedStatement2.executeQuery();

        String idss = "";
        int ii = 0;
       /* preparedStatement5 = conn.prepareStatement("insert into PUB_INTERFACERECORD(interfacename,total,Dailytotal,FLAG) values('身份信息'," + (total+15000) + "," + 15000 + ",'1')");
        preparedStatement5.executeUpdate();
        if (preparedStatement5 != null) {
            preparedStatement5.close();
        }*/
        while (resultSet.next()) {
            ii++;
            String id = resultSet.getString("cardid");
            String ids = resultSet.getString("id");
            idss = ids;

            String invokingServiceUrl =
                    "http://59.207.107.18:5000/api/apis-new/getRkMessVerificationByIdCardNoToken?idCard=" + id + "&userDeptCode=11412800MB160464XL&userName=驻马店市政务服务和大数据管理局";

            String currTime = System.currentTimeMillis() + "";
            String generateTokenUrl = "http://59.207.107.18:5000/auth/token";
            String sign = gatewaySignEncode(appId, appKey, currTime);
            JSONObject jsonObject = httpRequest(generateTokenUrl, "POST", appId, currTime, sign);
            JSONObject jsonObjectBody = JSONObject.parseObject(jsonObject.getString("body"));
            if (jsonObjectBody != null) {
                String accessToken = jsonObjectBody.getString("access_token");
                System.out.println(accessToken);
                String secretKey = AESDncode(appKey, accessToken);
                String sign1 = gatewaySignEncode(appId, secretKey, currTime);
                Object o = HttpUtil.sendGet(invokingServiceUrl, appId, currTime, sign1);
                System.out.println("服务数据结果:" +
                        o);
                if (o != null && o != "") {
                    JSONObject object = JSONObject.parseObject(o.toString());
                    if (object == null) {
                        continue;
                    }
                    Object data = object.get("resultData");
                    if (data != null) {
                        JSONObject jsonObject1 = JSONObject.parseObject(data.toString());
                        String sql = "insert into hns_zsb_sfxx (POPNAME,POPGENDER,POPNATION,POPBIRTHDATE,POPADDR,IDCARD,QFJGGAJGMC,YXQQSRQ,YXQJZRQ) values(?,?,?,?,?,?,?,?,?)";
                        preparedStatement = conn.prepareStatement(sql);
                        preparedStatement.setString(1, (String) jsonObject1.get("popName"));
                        preparedStatement.setString(2, (String) jsonObject1.get("popGender"));
                        preparedStatement.setString(3, (String) jsonObject1.get("popNation"));
                        preparedStatement.setString(4, (String) jsonObject1.get("popBirthDate"));
                        preparedStatement.setString(5, (String) jsonObject1.get("popAddr"));
                        preparedStatement.setString(6, (String) jsonObject1.get("idCard"));
                        preparedStatement.setString(7, (String) jsonObject1.get("qfjgGajgmc"));
                        preparedStatement.setString(8, (String) jsonObject1.get("yxqqsrq"));
                        preparedStatement.setString(9, (String) jsonObject1.get("yxqjzrq"));
                        preparedStatement.executeUpdate();
                        //conn.commit();

                        if (preparedStatement != null) {
                            preparedStatement.close();
                        }
                    }

                }


            }

        }


        preparedStatement5 = conn.prepareStatement("insert into PUB_INTERFACERECORD(interfacename,total,Dailytotal) values('身份信息'," + idss + "," + ii + ")");
        preparedStatement5.executeUpdate();
        if (preparedStatement5 != null) {
            preparedStatement5.close();
        }

        if (conn != null) {
            conn.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (preparedStatement2 != null) {
            preparedStatement2.close();
        }


    }

    /**
     * 定时调用民政厅婚姻信息接口
     *
     * @throws Exception
     */
    public static void getdata() throws Exception {


        Connection conn = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;
        PreparedStatement preparedStatement4 = null;
        PreparedStatement preparedStatement5 = null;

            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:interfaceDB/interfaceDB@59.207.219.23:1521:orcl", "interfaceDB", "interfaceDB");
            conn.setAutoCommit(false);
            preparedStatement4 = conn.prepareStatement("select t.total from PUB_INTERFACERECORD t where t.interfacename = '民政婚姻登记信息' order by t.updatetime desc");

            ResultSet resultSet4 = preparedStatement4.executeQuery();
            int total = 0;
            if (resultSet4.next()) {
                total = resultSet4.getInt("total");
            }
            if (preparedStatement4 != null) {
                preparedStatement4.close();
            }


            preparedStatement2 = conn.prepareStatement(" select cardid,id from ( select rownum r,t.cardid,t.id from PUB_RK_PARM t where rownum <= 15000 and id > " + total + " order by id ASC) t where r>0");

            ResultSet resultSet = preparedStatement2.executeQuery();

            String idss = "";
            int ii = 0;
            while (resultSet.next()) {

                try{
                ii++;
                String id = resultSet.getString("cardid");
                String ids = resultSet.getString("id");
                idss = ids;
                String womanIdcard = "null";
                String manid = "null";
                if ((Integer.parseInt(id.substring(16, 17)) % 2) == 0) {
                    womanIdcard = id;
                } else {
                    manid = id;
                }
                String invokingServiceUrl =
                        "http://59.207.107.18:5000/api/hyxxcx?womanIdcard=" + womanIdcard + "&manIdcard=" + manid;
// 参数设定
                String appId = "d53f844edcc147b3a9be2eef2479848c";
                String appKey =
                        "ZDUzZjg0NGVkY2MxNDdiM2E5YmUyZWVmMjQ3OTg0OGM6MTIzNDU2";
                String currTime = System.currentTimeMillis() + "";
// 生成 access_token 秘钥的 url
                String generateTokenUrl = "http://59.207.107.18:5000/auth/token";
// 服务调用的 url

// 服务调用的请求方法
                String invokingServiceRequestMethod = "POST";
// 1.获取 access_token。
                String sign = gatewaySignEncode(appId, appKey, currTime);
                JSONObject jsonObject = httpRequest(generateTokenUrl, "POST", appId,
                        currTime, sign);
                JSONObject jsonObjectBody =
                        JSONObject.parseObject(jsonObject.getString("body"));
                if (jsonObjectBody != null) {
                    String accessToken = jsonObjectBody.getString("access_token");
                    // 2.在获得服务调用秘钥 access_token 后，根据自己的 appKey 使用AES解密算法对返回值进行解密，最终获得真正秘钥的过程。
                    String secretKey = AESDncode(appKey, accessToken);
                    // 3.调用服务，获取服务的 json 数据。
                    String sign1 = gatewaySignEncode(appId, secretKey, currTime);
                    JSONObject serviceJsonData = httpRequest(invokingServiceUrl,
                            invokingServiceRequestMethod, appId, currTime, sign1);
                    String codeValue = serviceJsonData.getString("code");
                    if (codeValue.equals("200")) {
                        System.out.println("服务数据结果:" +
                                serviceJsonData.toString());
                        String body = serviceJsonData.getString("body");
                        JSONObject json = JSONObject.parseObject(body);
                        System.out.println("服务数据结果:" +
                                json.toString());
                        if(!json.containsKey("data")){
                            continue;
                        }
                        String data = json.getString("data");
                        JSONArray jsonArray = JSONArray.parseArray(data);
                        List<Map<String, Object>> list = JSONArray.parseObject(data, List.class);
                        System.out.println(jsonArray.size());

                        String sql = "insert into hns_mzj_hyxx (ywlb,wname,mname,wid,mid,widtype,midtype,djrq,bfdjrq) values(?,?,?,?,?,?,?,to_date(?,'yyyy-MM-dd hh24:mi:ss'),to_date(?,'yyyy-MM-dd hh24:mi:ss'))";
                        preparedStatement = conn.prepareStatement(sql);
                        for (Map<String, Object> str : list) {
                            preparedStatement.setString(1, (String) str.get("业务类别"));
                            preparedStatement.setString(2, (String) str.get("女方姓名"));
                            preparedStatement.setString(3, (String) str.get("男方姓名"));
                            preparedStatement.setString(4, (String) str.get("女方身份证件号码"));
                            preparedStatement.setString(5, (String) str.get("男方身份证件号码"));
                            preparedStatement.setString(6, (String) str.get("女方身份证件类型"));
                            preparedStatement.setString(7, (String) str.get("男方身份证件类型"));
                            if (str.get("登记日期") != null && !str.get("登记日期").equals("null")) {
                                preparedStatement.setString(8, (String) str.get("登记日期"));

                            } else {
                                preparedStatement.setString(8, "");
                            }
                            if (str.get("补发登记日期") != null && !str.get("补发登记日期").toString().equals("null")) {
                                preparedStatement.setString(9, (String) str.get("补发登记日期"));
                            } else {
                                preparedStatement.setString(9, "");
                            }
                           /* preparedStatement.setString(8,(String)str.get("登记日期"));
                            preparedStatement.setString(9,(String)str.get("补发登记日期"));*/

                            preparedStatement.addBatch();
                        }
                        preparedStatement.executeBatch();
                        conn.commit();

                        if (preparedStatement != null) {
                            preparedStatement.close();
                        }
                    }
                } else {
                    System.out.println("获取 token 错误，请查看响应数据中的错误码和错误信息。");
                }
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    continue;
                }
            }

            preparedStatement5 = conn.prepareStatement("insert into PUB_INTERFACERECORD(interfacename,total,Dailytotal) values('民政婚姻登记信息'," + idss + "," + ii + ")");
            preparedStatement5.executeUpdate();
            if (preparedStatement5 != null) {
                preparedStatement5.close();
            }

            if (conn != null) {
                conn.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (preparedStatement2 != null) {
                preparedStatement2.close();
            }

    }

    /**
     * 定时调用人社厅接口
     *
     * @throws Exception
     */
    public static void getSheBaoInfo() {


        Connection conn = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;

        PreparedStatement preparedStatement4 = null;
        PreparedStatement preparedStatement5 = null;
        String idss = "";
        int ii = 0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:interfaceDB/interfaceDB@59.207.219.23:1521:orcl", "interfaceDB", "interfaceDB");
            conn.setAutoCommit(false);
            preparedStatement4 = conn.prepareStatement("select t.total from PUB_INTERFACERECORD t where t.interfacename = '社会保险参保基本信息' order by t.updatetime desc");

            ResultSet resultSet4 = preparedStatement4.executeQuery();
            int total = 0;
            if (resultSet4.next()) {
                total = resultSet4.getInt("total");
            }
            if (preparedStatement4 != null) {
                preparedStatement4.close();
            }
            preparedStatement2 = conn.prepareStatement(" select cardid,id from ( select rownum r,t.cardid,t.id from PUB_RK_PARM t where rownum <= 1000 and id > " +total+ " order by id ASC) t where r>0");
            ResultSet resultSet = preparedStatement2.executeQuery();
            while (resultSet.next()) {
                ii++;
                String id = resultSet.getString("cardid");
                String ids = resultSet.getString("id");
                idss = ids;
                String appId = "41cd6ff5d79049b7a884dd2607d55475";
                String appKey = "NDFjZDZmZjVkNzkwNDliN2E4ODRkZDI2MDdkNTU0NzU6MTIzNDU2";
                Map<String, Object> map = new HashMap<>();
                map.put("req.token", "cd892e19db584f09a9604099db0504e7");
                map.put("req.subscribeId", "6daf341fc8a34573885cb7fe082517bc");
                map.put("req.userId", "a81013f8571e4a77801d34914f49c207");
                map.put("params[0].fieldCode", "SFZH");
                map.put("params[0].operateCode", "=");
                map.put("params[0].parameterValue", id);
                map.put("page.rows", "10");
                map.put("page.index", "1");
                Object data = httpData2(map, "http://59.207.107.18:5000/api/shbxcbxxcx", appId, appKey);
                JSONObject jsonObject = JSONObject.parseObject(data.toString());
                JSONObject data1 = JSONObject.parseObject(jsonObject.get("data").toString());
                JSONObject data2 = data1.getJSONObject("data");
                int num = Integer.parseInt(data2.get("total") + "");
                if (num > 0) {
                    Object list = data2.get("list");
                    List<Map<String, Object>> list2 = JSONArray.parseObject(list.toString(), List.class);
                    String sql = "insert into HNS_RST_SBJBXX (CBDXZQHDM,CBXZ,CBZT,DWBH,DWMC,GRBH,JFQZSS,SDC_BATCH_NO,SFZH,XM,JFNY) values(?,?,?,?,?,?,?,?,?,?,?)";
                    preparedStatement = conn.prepareStatement(sql);
                    for (Map<String, Object> str : list2) {
                        preparedStatement.setString(1, (String) str.get("CBDXZQHDM"));
                        preparedStatement.setString(2, (String) str.get("CBXZ"));
                        preparedStatement.setString(3, (String) str.get("CBZT"));
                        preparedStatement.setString(4, (String) str.get("DWBH"));
                        preparedStatement.setString(5, (String) str.get("DWMC"));
                        preparedStatement.setString(6, (String) str.get("GRBH"));
                        preparedStatement.setString(7, (String) str.get("JFQZSS"));
                        preparedStatement.setString(8, (String) str.get("SDC_BATCH_NO"));
                        preparedStatement.setString(9, (String) str.get("SFZH"));
                        preparedStatement.setString(10, (String) str.get("XM"));
                        preparedStatement.setString(11, (String) str.get("JFNY"));
                        preparedStatement.addBatch();
                    }
                    preparedStatement.executeBatch();
                    conn.commit();

                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement5 = conn.prepareStatement("insert into PUB_INTERFACERECORD(interfacename,total,Dailytotal) values('社会保险参保基本信息'," + idss + "," + ii + ")");
                preparedStatement5.executeUpdate();
                if (preparedStatement5 != null) {
                    preparedStatement5.close();
                }
                if (conn != null) {
                    conn.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    /**
     * 定时调用人社厅接口
     *
     * @throws Exception
     */
    public static void getFDCQCXInfo() {


        try {

           for(int i = 0;i<10;i++) {

               String appId = "f7d6a5a0fe3243419f0c649d0d72aed6";
               String appKey ="ZjdkNmE1YTBmZTMyNDM0MTlmMGM2NDlkMGQ3MmFlZDY6MTIzNDU2";
               Map<String, Object> map = new HashMap<>();
               map.put("QLRZJHM","412822199011193778");
               map.put("QLRMC","陈敬礼");
               map.put("MM","888888");
               map.put("BDCQZH","豫（2019）泌阳县不动产权第0002079号");
               map.put("QXDM","");
               map.put("QLRZJZL","1");
               map.put("YHM","00000047");
               Object data = httpData2(map, "http://59.207.107.18:5000/api/FDCQCX",appId,appKey);



           }
        }catch(Exception e)

        {
            e.printStackTrace();
        }
    }




}







