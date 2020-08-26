package com.bm.https.untils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import javax.xml.bind.DatatypeConverter;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.concurrent.TimeUnit;

public class HttpsUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpsUtils.class);
	/**
	 * 获得KeyStore.
	 *
	 * @param keyStorePath 密钥库路径
	 * @param password     密码
	 * @return 密钥库
	 * @throws Exception
	 */
	public static KeyStore getP12KeyStore(String password, String keyStorePath)
			throws Exception {
		// 实例化密钥库
		KeyStore ks = KeyStore.getInstance("PKCS12");
		File file = new File(keyStorePath);
		System.out.println("秘钥文件名称：file.name:" + file.getName() + "   file.size:" + file.length());
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
	 *
	 * @param password       密码
	 * @param keyStorePath   密钥库路径
	 * @param trustStorePath 信任库路径
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


	public static String getHKVesselTrip(String urls, String param) {
		HttpsURLConnection con = null;
		BufferedReader red = null;
		try {
			KeyStore clientStore = KeyStore.getInstance("PKCS12");

			clientStore.load(new FileInputStream("/home/explat/ESB/226256.p12"), "clientatwasoft".toCharArray());
			System.out.println("读取226256.p12============");
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());

			kmf.init(clientStore, "clientatwasoft".toCharArray());
			System.out.println("init 226256.p12============");
			KeyManager[] kms = kmf.getKeyManagers();

			KeyStore trustStore = KeyStore.getInstance("JKS");

			trustStore.load(new FileInputStream("/home/explat/jdk8/jdk1.8.0_221/jre/lib/security/cacerts"), "changeit".toCharArray());
			System.out.println("init cacerts============");
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

			tmf.init(trustStore);

			TrustManager[] tms = tmf.getTrustManagers();

			SSLContext sslContext = null;

			sslContext = SSLContext.getInstance("TLS");

			sslContext.init(kms, tms, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
			URL url = new URL(urls);
			System.out.println("url============");
			con = (HttpsURLConnection) url.openConnection();

			con.addRequestProperty("Content-type", "application/json; charset=utf-8");
			con.addRequestProperty("Accept", "application/json");
			con.addRequestProperty("usr", "{\"zjbzxbm\":\"c41170\",\"blqd\":\"ywgl\"}");
			con.setDoOutput(true); //获取返回数据需要设置为true 默认false
			con.setDoInput(true); //发送数据需要设置为true 默认false
			con.setReadTimeout(5000);
			con.setConnectTimeout(5000);
			con.setRequestMethod("POST");

			con.connect();
			DataOutputStream out = new DataOutputStream(con.getOutputStream());
			if (param != null) {
				param = URLEncoder.encode(param, "utf-8");//url编码防止中文乱码
				out.writeBytes(param);
			}
			System.out.println("发送请求中============");
			out.flush();
			out.close();
			red = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = red.readLine()) != null) {
				sb.append(line);
			}
			System.out.println("out====================" + sb);
			con.disconnect();
			red.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}


		}

	}


	public static String inputStream2String(InputStream is) throws IOException {

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


	public static SSLConnectionSocketFactory getSocketFactoryPEM(String pemPath) throws Exception {
		byte[] certAndKey = fileToBytes(new File(pemPath));

		byte[] certBytes = parseDERFromPEM(certAndKey, "-----BEGIN CERTIFICATE-----", "-----END CERTIFICATE-----");
		byte[] keyBytes = parseDERFromPEM(certAndKey, "-----BEGIN PRIVATE KEY-----", "-----END PRIVATE KEY-----");

		X509Certificate cert = generateCertificateFromDER(certBytes);
		RSAPrivateKey key = generatePrivateKeyFromDER(keyBytes);

		KeyStore keystore = KeyStore.getInstance("JKS");
		keystore.load(null);
		keystore.setCertificateEntry("cert-alias", cert);
		keystore.setKeyEntry("key-alias", key, "<password>".toCharArray(), new Certificate[]{cert});

		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		kmf.init(keystore, "<password>".toCharArray());

		KeyManager[] km = kmf.getKeyManagers();

		SSLContext context = SSLContext.getInstance("TLS");
		context.init(km, null, null);
		SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(context, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		return socketFactory;
	}

	public static byte[] parseDERFromPEM(byte[] pem, String beginDelimiter, String endDelimiter) {
		String data = new String(pem);
		String[] tokens = data.split(beginDelimiter);
		tokens = tokens[1].split(endDelimiter);
		return DatatypeConverter.parseBase64Binary(tokens[0]);
	}

	public static RSAPrivateKey generatePrivateKeyFromDER(byte[] keyBytes) throws InvalidKeySpecException, NoSuchAlgorithmException {
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);

		KeyFactory factory = KeyFactory.getInstance("RSA");

		return (RSAPrivateKey) factory.generatePrivate(spec);
	}

	public static X509Certificate generateCertificateFromDER(byte[] certBytes) throws CertificateException {
		CertificateFactory factory = CertificateFactory.getInstance("X.509");

		return (X509Certificate) factory.generateCertificate(new ByteArrayInputStream(certBytes));
	}

	public static byte[] fileToBytes(File file) {
		try {
			byte[] buffer = null;
			if (file != null) {
				FileInputStream fis = new FileInputStream(file);
				ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
				byte[] b = new byte[1000];
				int n;
				while ((n = fis.read(b)) != -1) {
					bos.write(b, 0, n);
				}
				fis.close();
				bos.close();
				buffer = bos.toByteArray();
			}
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}




	private static SSLSocketFactory createSSLSocketFactory() throws Exception {
		javax.net.ssl.TrustManager[] trustAllCerts = {new X509TrustManager(){


			@Override
			public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
				return;
			}


			@Override
			public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
				return;
			}


			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		}};


		javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, null);
		return sc.getSocketFactory();

	}

	public static String getServiceNetworkInformation(String xmlstr,String url) {

		String password = "clientatwasoft";
		// 密钥库
		String keyStorePath =  "/home/explat/ESB/226256.p12";
		// 信任库
		String trustStorePath = "/home/explat/ESB/zmd.jks";
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		// Increase max total connection to 200
		cm.setMaxTotal(200);
		// Increase default max connection per route to 20
		cm.setDefaultMaxPerRoute(20);
		// 关闭空闲两分钟的连接
		cm.closeIdleConnections(120, TimeUnit.SECONDS);
		SSLContext sslContext = null;
		CloseableHttpResponse response = null;
		String result = "";
		try {
			sslContext = HttpsUtils.getSSLContext(password,keyStorePath, trustStorePath);
			logger.info("驻马店交换平台接口资源请求==========开始发送对外请求读取226256.p12  jks");
			SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext,SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			CloseableHttpClient httpsClientSSLAuth = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
			HttpPost httpPost = new HttpPost(url);
			logger.info("驻马店交换平台接口资源请求==========开始发送对外请求 url:"+url);
			// 加载配置信息
			StringEntity reqEntity = new StringEntity(xmlstr, "UTF-8");
			httpPost.addHeader("Content-type", "application/json; charset=utf-8");
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader(
					"usr",
					"{\"userid\":\"0\",\"ywlb\":\"\",\"zjbzxbm\":\"C41170\",\"zhbh\":\"\",\"khbh\":\"\",\"blqd\":\"ywgl\",\"ffbm\":\"01\",\"ywfl\":\"\"}");
			httpPost.setEntity(reqEntity);
			logger.info("驻马店交换平台接口资源请求==========开始发送对外请求=======");
			// 发出请求
			response = httpsClientSSLAuth.execute(httpPost);
			result = EntityUtils.toString(response.getEntity(), "UTF-8");
			logger.info("驻马店交换平台接口资源请求==========开始发送对外请求 返回值："+result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}

	}

}
