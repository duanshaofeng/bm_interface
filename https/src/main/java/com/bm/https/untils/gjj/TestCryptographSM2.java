package com.bm.https.untils.gjj;

import com.alibaba.fastjson.JSONObject;
import com.cryptography.neu.sm.SM2Result;
import com.cryptography.neu.sm.SM2Util;
import com.cryptography.neu.sm.Sm2KeyPair;

public class TestCryptographSM2 {
	
	private static String plainText = "";
 
	public static void main(String[] args) throws Exception {
		
		// 把业务数据按照json格式进行组织，然后转化成字符串。
		JSONObject sampleData = new JSONObject();
		
		// 中心编码
		String zxbm = "C41000";
		sampleData.put("zxbm", zxbm);
		
		// 证件号码
		String zjhm = "410922198404300019";
		sampleData.put("zjhm", zjhm);
		
		// 姓名
		String xingming = "姓名01";
		sampleData.put("xingming", xingming);
		
		plainText = sampleData.toJSONString();
		System.out.println("样例：个人账户信息查询");
		System.out.println("中心编码：" + zxbm);
		System.out.println("证件号码：" + zjhm);
		System.out.println("姓名：01"  + xingming);
		System.out.println("把业务数据按照json格式进行组织，然后转化成字符串。");
		System.out.println(plainText);
		System.out.println("--------------------------------------------------");
		
		System.out.println("国密sm2加解密测试开始");
		
		System.out.println("生成密钥对开始");
		Sm2KeyPair sm2KeyPair = SM2Util.generateKeyPair("gjj");
		System.out.println("生产的私钥 ：" + sm2KeyPair.getPriKey());
		System.out.println("生产的公钥 ：" + sm2KeyPair.getPubKey());
		System.out.println("生成密钥对结束");
		
		System.out.println("加密前的数据 ：" + plainText);
		String encryptData = SM2Util.encrypt(sm2KeyPair.getPubKey(), plainText);
		System.out.println("加密后的数据 ：" + encryptData);
		System.out.println("--------------------------------------------------");
		
		System.out.println("解密前的数据 ：" + encryptData);
		String decryptData = SM2Util.decrypt(sm2KeyPair.getPriKey(), encryptData);
		System.out.println("解密后的数据 ：" + new String(decryptData));
		System.out.println("--------------------------------------------------");
		
		System.out.println("签名前的数据 ：" + plainText);
		SM2Result sm2Result = SM2Util.Sm2Sign(sm2KeyPair.getPriKey(), plainText);
		System.out.println("签名后的数据(r)：" + sm2Result.str_r);
		System.out.println("签名后的数据(s)：" + sm2Result.str_s);
		System.out.println("--------------------------------------------------");
		
		boolean verifySignResult2 = SM2Util.Sm2Verify(sm2KeyPair.getPubKey(), plainText, sm2Result.str_r, sm2Result.str_s);
		System.out.println("验签结果 ：" + verifySignResult2);
		
		System.out.println("国密sm2加解密测试结束");
	}
}
