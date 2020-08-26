package com.bm.https.untils.gjj;

import com.alibaba.fastjson.JSONObject;
import com.cryptography.neu.sm.SM4Util;


public class TestCryptographSM4 {
	
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
		
		System.out.println("国密sm4加解密测试开始");
		
		System.out.println("生成密钥开始");
		byte[] key = SM4Util.generateKey();
		SM4Util.setSecretKey(key);
		System.out.println("生产的密钥(byte[]) ：" + key);
		System.out.println("生成密钥对结束");
		
		System.out.println("ECB加密前的数据 ：" + plainText);
		String encryptData = SM4Util.encryptData_ECB(plainText);
		System.out.println("ECB加密后的数据 ：" + encryptData);
		System.out.println("--------------------------------------------------");
		
		System.out.println("ECB解密前的数据 ：" + encryptData);
		String decryptData = SM4Util.decryptData_ECB(encryptData);
		System.out.println("ECB解密后的数据 ：" + decryptData);
		System.out.println("--------------------------------------------------");
		
		System.out.println("CBC加密前的数据 ：" + plainText);
		encryptData = SM4Util.encryptData_CBC(plainText);
		System.out.println("CBC加密后的数据 ：" + encryptData);
		System.out.println("--------------------------------------------------");
		
		System.out.println("CBC解密前的数据 ：" + encryptData);
		decryptData = SM4Util.decryptData_CBC(encryptData);
		System.out.println("CBC解密后的数据 ：" + decryptData);
		System.out.println("--------------------------------------------------");
		
		System.out.println("国密sm4加解密测试结束");
	}
}
