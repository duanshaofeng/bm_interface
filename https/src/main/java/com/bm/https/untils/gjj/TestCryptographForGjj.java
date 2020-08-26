package com.bm.https.untils.gjj;

import com.alibaba.fastjson.JSONObject;
import com.cryptography.neu.sm.SM2Result;
import com.cryptography.neu.sm.SM2Util;
import com.cryptography.neu.sm.SM4Util;
import com.cryptography.neu.sm.Sm2KeyPair;

public class TestCryptographForGjj {
	
	private static String plainText = "";
 
	public static void main(String[] args) throws Exception {
		
		// 1. 把业务数据按照json格式进行组织，然后转化成字符串。
		JSONObject sampleData = new JSONObject();
		// 证件号码
		String zjhm = "412826199010157527";
		sampleData.put("zjhm", zjhm);
		// 中心编码
		String zxbm = "C41000";
		sampleData.put("zxbm", zxbm);
		

		
		// 姓名
		String xingming = "梁丹丹";
		sampleData.put("xingming", xingming);

		// 姓名
		String jkhtbh = "411701012019000003";
		sampleData.put("jkhtbh", jkhtbh);

		// 姓名
		String ksrq = "2020-06-01";
		sampleData.put("ksrq", ksrq);

		// 姓名
		String jsrq = "2020-12-01";
		sampleData.put("jsrq", jsrq);

		// 姓名
		String page = "1";
		sampleData.put("page", page);

		// 姓名
		String size = "15";
		sampleData.put("size", size);
		
		plainText = sampleData.toJSONString();
		plainText = "{\"zxbm\":\"C41170\",\"zjhm\":\"412801198905250819\",\"xingming\":\"刘润霖\",\"jkhtbh\":\"411701012019001305\",\"ksrq\":\"2019-11-01\",\"jsrq\":\"2020-07-24\"}";
		System.out.println("样例：个人账户信息查询");
		System.out.println("中心编码：" + zxbm);
		System.out.println("证件号码：" + zjhm);
		System.out.println("姓名："  + xingming);
		System.out.println("把业务数据按照json格式进行组织，然后转化成字符串。");
		System.out.println(plainText);
		System.out.println("--------------------------------------------------");
		
		// 2.使用sdk中提供的函数生成本次调用使用的对称密钥（国密SM4）。
		System.out.println("生成本次调用使用的对称密钥（国密SM4）开始");
		byte[] key = SM4Util.generateKey();
		SM4Util.setSecretKey(key);
		System.out.println("生产的密钥(byte[]) ：" + key);
		System.out.println("生成本次调用使用的对称密钥（国密SM4）结束");
		
		// 3.使用步骤2生成的对称密钥对步骤1生成的字符串加密。
		System.out.println("ECB加密前的数据 ：" + plainText);
		String encryptData = SM4Util.encryptData_ECB(plainText);
		System.out.println("ECB加密后的数据 ：" + encryptData);
		System.out.println("--------------------------------------------------");
		
		// 4.使用大数据局提供的私钥对骤1生成的字符串签名。
		// TODO
		Sm2KeyPair sm2KeyPair = SM2Util.generateKeyPair("gjj");
		System.out.println("生产的私钥 ：" + sm2KeyPair.getPriKey());
		System.out.println("生产的公钥 ：" + sm2KeyPair.getPubKey());
		
		System.out.println("签名前的数据 ：" + plainText);
		String priKey = sm2KeyPair.getPriKey(); // 大数据局提供的私钥
		SM2Result sm2Result = SM2Util.Sm2Sign(priKey, plainText);
		System.out.println("签名后的数据(r)：" + sm2Result.str_r);
		System.out.println("签名后的数据(s)：" + sm2Result.str_s);
		
		boolean verifySignResult = SM2Util.Sm2Verify(sm2KeyPair.getPubKey(), plainText, sm2Result.str_r, sm2Result.str_s);
		System.out.println("验签的结果 ：" + verifySignResult);
	
		// 5.使用大数据局提供的大数据局的公钥对步骤2生成的对称密钥进行加密。
		System.out.println("加密对称密钥（国密SM4） ：" + key);
		// TODO
		String pubKey = sm2KeyPair.getPubKey(); // 大数据局提供的公钥
		String encryptKey = SM2Util.encrypt(pubKey, new String(key));
		System.out.println("加密后的对称密钥（国密SM4）：" + encryptKey);
		System.out.println("--------------------------------------------------");
		
		// 6.按如下格式给大数据局返回数据
		JSONObject returnData = new JSONObject();
		returnData.put("stateCode", "100000");
		returnData.put("message", "成功");
		returnData.put("sign_r", sm2Result.str_r);
		returnData.put("sign_s", sm2Result.str_s);
		returnData.put("skey", encryptKey);
		returnData.put("ciphertext", encryptData);
		returnData.put("pubkeyId", sm2KeyPair.getPubKey());
		System.out.println("按如下格式给大数据局返回数据：");
		System.out.println(returnData.toJSONString());
		System.out.println("--------------------------------------------------");
		
		System.out.println("信息安全加密样例代码完成。");
	}


	public static String crypForGjj(String plainText){
		try{
			// 1. 把业务数据按照json格式进行组织，然后转化成字符串。
			JSONObject sampleData = new JSONObject();
			System.out.println("把业务数据按照json格式进行组织，然后转化成字符串。");
			System.out.println(plainText);
			System.out.println("--------------------------------------------------");

			// 2.使用sdk中提供的函数生成本次调用使用的对称密钥（国密SM4）。
			System.out.println("生成本次调用使用的对称密钥（国密SM4）开始");
			byte[] key = SM4Util.generateKey();
			SM4Util.setSecretKey(key);
			System.out.println("生产的密钥(byte[]) ：" + key);
			System.out.println("生成本次调用使用的对称密钥（国密SM4）结束");

			// 3.使用步骤2生成的对称密钥对步骤1生成的字符串加密。
			System.out.println("ECB加密前的数据 ：" + plainText);
			String encryptData = SM4Util.encryptData_ECB(plainText);
			System.out.println("ECB加密后的数据 ：" + encryptData);
			System.out.println("--------------------------------------------------");

			// 4.使用大数据局提供的私钥对骤1生成的字符串签名。
			// TODO
			Sm2KeyPair sm2KeyPair = SM2Util.generateKeyPair("gjj");
			System.out.println("生产的私钥 ：" + sm2KeyPair.getPriKey());
			System.out.println("生产的公钥 ：" + sm2KeyPair.getPubKey());

			System.out.println("签名前的数据 ：" + plainText);
			String priKey = sm2KeyPair.getPriKey(); // 大数据局提供的私钥
			SM2Result sm2Result = SM2Util.Sm2Sign(priKey, plainText);
			System.out.println("签名后的数据(r)：" + sm2Result.str_r);
			System.out.println("签名后的数据(s)：" + sm2Result.str_s);

			boolean verifySignResult = SM2Util.Sm2Verify(sm2KeyPair.getPubKey(), plainText, sm2Result.str_r, sm2Result.str_s);
			System.out.println("验签的结果 ：" + verifySignResult);

			// 5.使用大数据局提供的大数据局的公钥对步骤2生成的对称密钥进行加密。
			System.out.println("加密对称密钥（国密SM4） ：" + key);
			// TODO
			String pubKey = sm2KeyPair.getPubKey(); // 大数据局提供的公钥
			String encryptKey = SM2Util.encrypt(pubKey, new String(key));
			System.out.println("加密后的对称密钥（国密SM4）：" + encryptKey);
			System.out.println("--------------------------------------------------");

			// 6.按如下格式给大数据局返回数据
			JSONObject returnData = new JSONObject();
			returnData.put("stateCode", "100000");
			returnData.put("message", "成功");
			returnData.put("sign_r", sm2Result.str_r);
			returnData.put("sign_s", sm2Result.str_s);
			returnData.put("skey", encryptKey);
			returnData.put("ciphertext", encryptData);
			returnData.put("pubkeyId", sm2KeyPair.getPubKey());
			System.out.println("按如下格式给大数据局返回数据：");
			System.out.println(returnData.toJSONString());
			System.out.println("--------------------------------------------------");

			System.out.println("信息安全加密样例代码完成。");
			return returnData.toJSONString();
		}catch (Exception e){
			e.printStackTrace();
			return "请求异常";
		}
	}

}
