package com.bm.https;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.security.SecureRandom;

public class HttpsUtils {
	/** 
     * 获得KeyStore. 
     * @param keyStorePath 
     *            密钥库路径 
     * @param password 
     *            密码 
     * @return 密钥库 
     * @throws Exception 
     */  
    public static KeyStore getP12KeyStore(String password, String keyStorePath)  
            throws Exception {  
        // 实例化密钥库  
        KeyStore ks = KeyStore.getInstance("PKCS12");  
        File file = new File(keyStorePath);
        System.out.println("秘钥文件名称：file.name:"+file.getName()+"   file.size:"+file.length());
        // 获得密钥库文件流  
        FileInputStream is = new FileInputStream(keyStorePath);  
        // 加载密钥库  
        ks.load(is, password.toCharArray());  
        // 关闭密钥库文件流  
        is.close();  
        return ks;  
    }
    
    public static KeyStore getJKSKeyStore(String password, String keyStorePath)  
            throws Exception {  
        // 实例化密钥库  
        KeyStore ks = KeyStore.getInstance("JKS");  
        // 获得密钥库文件流  
        FileInputStream is = new FileInputStream(keyStorePath);  
        // 加载密钥库  
        ks.load(is, password.toCharArray());  
        // 关闭密钥库文件流  
        is.close();  
        return ks;  
    }  
  
    /** 
     * 获得SSLSocketFactory. 
     * @param password 
     *            密码 
     * @param keyStorePath 
     *            密钥库路径 
     * @param trustStorePath 
     *            信任库路径 
     * @return SSLSocketFactory 
     * @throws Exception 
     */  
    public static SSLContext getSSLContext(String password,  
            String keyStorePath, String trustStorePath) throws Exception {  
    	
    	
    	TrustManager[] trustAllCerts = new TrustManager[1];
    	TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
    	
    	
        // 实例化密钥库  
        KeyManagerFactory keyManagerFactory = KeyManagerFactory  
                .getInstance(KeyManagerFactory.getDefaultAlgorithm());  
        // 获得密钥库  
        KeyStore keyStore = getP12KeyStore(password, keyStorePath);  
        // 初始化密钥工厂  
        keyManagerFactory.init(keyStore, password.toCharArray());  
  
        // 实例化信任库  
        //TrustManagerFactory trustManagerFactory = TrustManagerFactory  
                //.getInstance(TrustManagerFactory.getDefaultAlgorithm());  
        // 获得信任库  
        //KeyStore trustStore = getJKSKeyStore(password, trustStorePath);  
        // 初始化信任库  
        //trustManagerFactory.init(trustStore);  
        // 实例化SSL上下文  
        SSLContext ctx = SSLContext.getInstance("TLS");  
        // 初始化SSL上下文
        ctx.init(keyManagerFactory.getKeyManagers(),
        		trustAllCerts, null);  
       // ctx.init(keyManagerFactory.getKeyManagers(),  
               // trustManagerFactory.getTrustManagers(), null);  
        // 获得SSLSocketFactory  
        return ctx;  
    }  
    
    
    
    public static String getHKVesselTrip(String urls,String param)  {
    	HttpsURLConnection con = null;
    	BufferedReader red = null;
    	try{
    		KeyStore clientStore = KeyStore.getInstance("PKCS12");

    		clientStore.load(new FileInputStream("/home/explat/ESB/226256.p12"),"clientatwasoft".toCharArray());

    		KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());

    		kmf.init(clientStore,"clientatwasoft".toCharArray());

    		KeyManager[] kms = kmf.getKeyManagers();

    		KeyStore trustStore = KeyStore.getInstance("JKS");

    		trustStore.load(new FileInputStream("/usr/java/jdk1.7.0_79/jre/lib/security/cacerts"),"changeit".toCharArray());

    		TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

    		tmf.init(trustStore);

    		TrustManager[] tms = tmf.getTrustManagers();
    		TrustManager[] trustAllCerts = new TrustManager[1];
    		TrustManager tm = new miTM();
    		trustAllCerts[0]  = tm;
    		SSLContext sslContext = null;

    		sslContext= SSLContext.getInstance("TLS");

    		sslContext.init(null,trustAllCerts, null);
    		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}
			}
	        );
    		HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

    		URL url = new URL(urls);

    		con = (HttpsURLConnection) url.openConnection();

    		
    		con.setDoOutput(true); //获取返回数据需要设置为true 默认false
            con.setDoInput(true); //发送数据需要设置为true 默认false
            con.setReadTimeout(5000);
            con.setConnectTimeout(5000);
            con.setRequestMethod("POST");
            con.connect();
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            if (param != null) {
                 param = URLEncoder.encode(param,"utf-8");//url编码防止中文乱码
                 out.writeBytes(param);
            }
            out.flush();
            out.close();
             red = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = red.readLine()) != null) {
                 sb.append(line);
            }
            System.out.println("out===================="+sb);
            con.disconnect();
			 red.close();
    		return sb.toString();
    	}catch(Exception e){
    		e.printStackTrace();
    		return e.getMessage();
    	}finally{
    		try {
				
			} catch (Exception e) {
				e.printStackTrace();
			}
    		
    		
    	}

	}



	public static String inputStream2String(InputStream is)throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		int i = -1;

		while ((i = is.read()) != -1) {

			baos.write(i);

		}

		return baos.toString();

	}
    
    
    
    
    
    
    
    
    
    
    
    
	static class miTM implements TrustManager,
			javax.net.ssl.X509TrustManager {
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public boolean isServerTrusted(
				java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public boolean isClientTrusted(
				java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public void checkServerTrusted(
				java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}

		public void checkClientTrusted(
				java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}
	}
     
}
