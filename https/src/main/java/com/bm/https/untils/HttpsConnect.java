package com.bm.https.untils;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * 通过https访问服务器忽略证书没被认可也继续访问
 */
public class HttpsConnect  {
    private static String TAG = "HttpConnect";

    private static final class DefaultTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
    protected static byte[] getBytesFromStream(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] kb = new byte[1024];
        int len;
        while ((len = is.read(kb)) != -1) {
            baos.write(kb, 0, len);
        }
        byte[] bytes = baos.toByteArray();
        baos.close();
        is.close();
        return bytes;
    }

    protected static void setBytesToStream(OutputStream os, byte[] bytes) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        byte[] kb = new byte[1024];
        int len;
        while ((len = bais.read(kb)) != -1) {
            os.write(kb, 0, len);
        }
        os.flush();
        os.close();
        bais.close();
    }

    public static void main(String[] args) throws Exception {

        StringBuffer buffer = new StringBuffer();

        String url = "https://59.207.219.23:7080/bdc/qlzsData";
        Object o = sendPostByJson(url, "{\"bh\":null,\"cqzh\":\"豫（2020）驻马店市不动产权证第0000003号\",\"djzzbs\":null,\"djjg\":\"驻马店市不动产管理局\",\"djjgdm\":\"124128000059477385\",\"djsj\":\"2019-01-22\",\"qlr\":\"武东方\",\"qlrzjhm\":\"412828199203260013\",\"qlrzjhmlx\":\"1\",\"qlrlx\":\"10\",\"gyqk\":\"单独所有\",\"gyr\":null,\"gyrzjhm\":null,\"gybl\":null,\"yqlrgx\":null,\"fbczbs\":\"0\",\"dyh\":\"411702600021GB00001F00070027\",\"zl\":\"河南省驻马店市市辖区纬二路南侧置地·天中第一城36号楼东2单元第5层503\",\"yt\":\"住宅\",\"ytdm\":\"10\",\"mj\":\"140.90\",\"mjdw\":\"平方米\",\"mjdwdm\":\"1\",\"ftsm\":null,\"ft\":null,\"ftjzd\":null,\"qllx\":\"国有建设用地使用权 / 房屋所有权\",\"qllxdm\":\"4\",\"qlxz\":null,\"qlxzdm\":null,\"syqxq\":null,\"syqxz\":null,\"bz\":null,\"qlqtqk\":\"房屋结构：混合结构\\r\\n房屋总层数：7 所在层数：5\\r\\n房屋竣工时间：2012\",\"fj\":null,\"fjy\":null,\"fty\":null,\"xxewm\":\"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\",\"cxewm\":\"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\"}");//第一个参 地址   第二个参数json字符
        System.out.println(o);


    }
    private static HttpsURLConnection getHttpsURLConnection(String uri, String method) throws IOException {
        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        SSLSocketFactory ssf = ctx.getSocketFactory();

        URL url = new URL(uri);
        HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection();
        httpsConn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        httpsConn.setSSLSocketFactory(ssf);
        httpsConn.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        });
        httpsConn.setRequestMethod(method);
        if("post".equals(method.toLowerCase())) {
            httpsConn.setDoOutput(true);
            httpsConn.setDoInput(true);
        }
        return httpsConn;
    }

    public static byte[] doGet(String uri) throws IOException {
        HttpsURLConnection httpsConn = getHttpsURLConnection(uri, "GET");
        return getBytesFromStream(httpsConn.getInputStream());
    }

    public static byte[] doPost(String uri, String data) throws IOException {
        HttpsURLConnection httpsConn = getHttpsURLConnection(uri, "POST");
        setBytesToStream(httpsConn.getOutputStream(), data.getBytes("UTF-8"));
        return getBytesFromStream(httpsConn.getInputStream());
    }




    public static Object sendPostByJson(String url, String json) throws Exception {

        String result = null;
        CloseableHttpClient httpclient = createSSLClientDefault();
        //httpclient.
        //httpclient.
        BufferedReader in = null;
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        StringEntity entity = new StringEntity(json, "UTF-8");
        httpPost.setEntity(entity);
        httpPost.setHeader(new BasicHeader("Content-Type", "application/json; charset=utf-8"));
        httpPost.addHeader("accessToken", "ZMD-20200622052423007-111721-303063000097/00014966-303017");
        try {
//          报文参数27：&id=jn-3-767744&groupPlatProTerminalId=119667&extend=uwJZ8j3CkpGPL4rM5J6KJhjR99O7yAe3BAGLS8ooI8ijNqKHfzTaK6W9wQvjZEVOmWJ3HxFb2O9D
//          wDbe3++UiQ==&xxtCode=370000&terminalType=1&role=3&type=3
            System.out.println("post请求报文地址：" + url);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            InputStream content = response.getEntity().getContent();
            in = new BufferedReader(new InputStreamReader(content, "UTF-8"));
//          in = new BufferedReader(new InputStreamReader(content, "GBK"));
//          in = new BufferedReader(new InputStreamReader(content));
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();

            //  响应报文：{"ret":0,"msg":"成功","callbackurl":"https://edu.10086.cn/test-sso/login?service=http%3A%2F%2F112.35.7.169%3A9010%2Feducloud%2Flogin%2Flogin%3Ftype%3D3%26mode%3D1%26groupId%3D4000573%26provincePlatformId%3D54","accesstoken":"2467946a-bee9-4d8c-8cce-d30181073b75"}Í
            //记录报文日志


            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpclient.close();
        }
        return null;


    }







    private static  RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1000000).setConnectTimeout(10000).build();//设置请求


    public static String post(String httpUrl, Map<String, String> params) throws Exception {
        String result = null ;
        CloseableHttpClient httpclient = createSSLClientDefault();
        //httpclient.
        //httpclient.
        BufferedReader in = null ;
        HttpPost httpPost = new HttpPost(httpUrl);
        httpPost.setConfig(requestConfig);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        StringBuffer paramsBuf = new StringBuffer() ;
        for(Map.Entry<String, String> e : params.entrySet()) {
            nvps.add(new BasicNameValuePair(e.getKey(), e.getValue()));
            paramsBuf.append("&").append(e.getKey()).append("=").append(e.getValue()) ;
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        httpPost.addHeader("accessToken","ZMD-20200713102437029-718565-303010000089/00015529-303088");
        try {
//          报文参数27：&id=jn-3-767744&groupPlatProTerminalId=119667&extend=uwJZ8j3CkpGPL4rM5J6KJhjR99O7yAe3BAGLS8ooI8ijNqKHfzTaK6W9wQvjZEVOmWJ3HxFb2O9D
//          wDbe3++UiQ==&xxtCode=370000&terminalType=1&role=3&type=3
            System.out.println("post请求报文地址：" + httpUrl+"?"+paramsBuf.toString()) ;
            CloseableHttpResponse response = httpclient.execute(httpPost);
            InputStream content = response.getEntity().getContent() ;
            in = new BufferedReader(new InputStreamReader(content, "UTF-8"));
//          in = new BufferedReader(new InputStreamReader(content, "GBK"));
//          in = new BufferedReader(new InputStreamReader(content));
            StringBuilder sb = new StringBuilder();
            String line = "" ;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString() ;

            //  响应报文：{"ret":0,"msg":"成功","callbackurl":"https://edu.10086.cn/test-sso/login?service=http%3A%2F%2F112.35.7.169%3A9010%2Feducloud%2Flogin%2Flogin%3Ftype%3D3%26mode%3D1%26groupId%3D4000573%26provincePlatformId%3D54","accesstoken":"2467946a-bee9-4d8c-8cce-d30181073b75"}Í
            //记录报文日志


            return result ;
        } catch (Exception e) {
            e.printStackTrace() ;
        } finally {
            httpclient.close();
        }
        return null ;
    }



    public static CloseableHttpClient createSSLClientDefault(){

        try {
            //SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
            // 在JSSE中，证书信任管理器类就是实现了接口X509TrustManager的类。我们可以自己实现该接口，让它信任我们指定的证书。
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            //信任所有
            X509TrustManager x509mgr = new X509TrustManager() {

                //　　该方法检查客户端的证书，若不信任该证书则抛出异常
                public void checkClientTrusted(X509Certificate[] xcs, String string) {
                }
                // 　　该方法检查服务端的证书，若不信任该证书则抛出异常
                public void checkServerTrusted(X509Certificate[] xcs, String string) {
                }
                // 　返回受信任的X509证书数组。
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[] { x509mgr }, null);
            ////创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            //  HttpsURLConnection对象就可以正常连接HTTPS了，无论其证书是否经权威机构的验证，只要实现了接口X509TrustManager的类MyX509TrustManager信任该证书。
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();


        } catch (KeyManagementException e) {

            e.printStackTrace();

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();

        } catch (Exception e) {

            e.printStackTrace();

        }

        // 创建默认的httpClient实例.
        return  HttpClients.createDefault();

    }

}















