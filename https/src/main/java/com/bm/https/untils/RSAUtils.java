package com.bm.https.untils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class RSAUtils {

    public static final String CHARSET = "UTF-8";
    public static final String RSA_ALGORITHM = "RSA";
    private static final String KEY_ALGORITHM = "RSA";
    private static final int MAX_ENCRYPT_BLOCK = 117;

    public static Map<String, String> createKeys(int keySize){
        //为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator kpg;
        try{
            kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        }catch(NoSuchAlgorithmException e){
            throw new IllegalArgumentException("No such algorithm-->[" + RSA_ALGORITHM + "]");
        }

        //初始化KeyPairGenerator对象,密钥长度
        kpg.initialize(keySize);
        //生成密匙对
        KeyPair keyPair = kpg.generateKeyPair();
        //得到公钥
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = Base64.encodeBase64URLSafeString(publicKey.getEncoded());
        //得到私钥
        Key privateKey = keyPair.getPrivate();
        System.out.println("私钥格式:"+privateKey.getFormat());
        String privateKeyStr = Base64.encodeBase64URLSafeString(privateKey.getEncoded());
        Map<String, String> keyPairMap = new HashMap<String, String>();
        keyPairMap.put("publicKey", publicKeyStr);
        keyPairMap.put("privateKey", privateKeyStr);

        return keyPairMap;
    }

    /**
     * 得到公钥
     * @param publicKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
        return key;
    }

    /**
     * 得到私钥
     * @param privateKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        return key;
    }

    /**
     * 公钥加密
     * @param data
     * @param publicKey
     * @return
     */
    public static String publicEncrypt(String data, RSAPublicKey publicKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), publicKey.getModulus().bitLength()));
        }catch(Exception e){
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 公钥加密
     *
     * @param data
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String data, RSAPublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(data.getBytes("UTF-8"));
        return Base64.encodeBase64String(bytes);
    }


    /**
     * 私钥解密
     * @param data
     * @param privateKey
     * @return
     */

    public static String privateDecrypt(String data, RSAPrivateKey privateKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), privateKey.getModulus().bitLength()), CHARSET);
        }catch(Exception e){
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }



    /**
     * 私钥加密
     * @param data
     * @param privateKey
     * @return
     */

    public static String privateEncrypt(String data, RSAPrivateKey privateKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), privateKey.getModulus().bitLength()));
        }catch(Exception e){
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 公钥解密
     * @param data
     * @param publicKey
     * @return
     */

    public static String publicDecrypt(String data, RSAPublicKey publicKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), publicKey.getModulus().bitLength()), CHARSET);
        }catch(Exception e){
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize){
        int maxBlock = 0;
        if(opmode == Cipher.DECRYPT_MODE){
            maxBlock = keySize / 8;
        }else{
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try{
            while(datas.length > offSet){
                if(datas.length-offSet > maxBlock){
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                }else{
                    buff = cipher.doFinal(datas, offSet, datas.length-offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        }catch(Exception e){
            throw new RuntimeException("加解密阀值为["+maxBlock+"]的数据时发生异常", e);
        }
        byte[] resultDatas = out.toByteArray();
        IOUtils.closeQuietly(out);
        return resultDatas;
    }


    public static void main(String[] args) throws Exception {
       /* RSAPublicKey publicKey = getPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsl92YFlzPDm+sLFaZ9JGfNDQ6wumDvoAD/LLXwaqvxziXc4SuZv9lKxo/+oMj76vnGvOenq/8hG8C36/NVrp+LRe679AZbPdDwYmNUtcDR4BOQLtCdfO8qZ5qqD4RKUtNc8Y3Lk8xg2NmhWFEE2/njIxvOS7Hu0WepGC0AXakp3Ku/zOElrf2hFBZU8Y363qSRRAxJygTqAO9nZ4Yi6FaT+rACxh/V/6n8Y3P5UCqsAkuxdOUkczgooLv1fnpDkYlV4zqD1WcyNhEPY5bsIWgvuAIONNy00bTRuqICWUfbMlwkln3QNpw4UJMLsJ0loTvcfXz2nkWgYwO9C2HtvF1wIDAQAB");
        String s = publicEncrypt("123456", publicKey);
        System.out.println(s);
*/
       // RSAPrivateKey privateKey = getPrivateKey("+gAP8stfBqq/HOJdzhK5m/2UrGj/6gyPvq+ca856er/yEbwLfr81Wun4tF7rv0Bls90PBiY1S1wNHgE5Au0J187ypnmqoPhEpS01zxjcuTzGDY2aFYUQTb+eMjG85Lse7RZ6kYLQBdqSncq7/M4SWt/aEUFlTxjfrepJFEDEnKBOoA72dnhiLoVpP6sALGH9X/qfxjc/lQKqwCS7F05SRzOCigu/V+ekORiVXjOoPVZzI2EQ9jluwhaC+4Ag403LTRtNG6ogJZR9syXCSWfdA2nDhQkwuwnSWhO9x9fPaeRaBjA70LYe28XXAgMBAAECggEAbS3FVUArKqflsl08CuRIRMnDtrrQj8YyF5DzLlz0UjopzGUATR/P3RqSwmHOAoYjE0lhftJ3foGgaiDQhKX7JEGy//V2Q0oOes22wVQ5KcY91VmXITk48Ik5W1lf3RSFUbRLcX7oVgmhrLiTU+Fi8j+i9g3DAzD3W1qJiS6sFKT/A3mMoZgl3zRWT0aKaCCJWS6TP0pAXFQGasF4D4FDKMOXXuf05/k+fZc64nKOL7sIgMkrwzV0N0y33YlQbJafUhg2VlBok7t3yO9xOhdx8T8g+L25avKP92oor8");
       String ss = "ZPviJ6bZdMs+jc+InEI7hdlAALT2FHABJE6CM6EyGgs9mE+deX/MAC3ZCYTcMDPStKR85Prb724emhtgyhGYXJPzp42YqZyUAEeI1QSeFIcLm/qgRE5JeNfgwq+Lb9/ZKmKrQJLHwKGQgrRmkWPQPtgObDDIauw8rYqfejde+5k=";
       //String ss = "B1K45h_4fh4Z0v-zYHOIUp2UJXfw5JpsXiHQhf012gHsOcJXQWUQRmYwa-DIKRz-R4SQ0EzoCefYPrw4wV6pT_OWV-R0wt-c35KbBhuB67wo1-ibhaxUI78B7GN_2TKVqBa0tafU6q8ne3yeBhZ3NhRKJKqwIXXV1dHRIizOAF8";
       // String ss ="C0xid72MgXFnpW2Rdko0Y-jglLRlc0EBgXR4aP-EEgNmfSZK4SpVhZAxnH5ZYwC7sAcvlw1tY8sIGXXJs7R62JcRc60SvDfgchDXVfy0FC2pMyeuQQ1jTZN_mTxUreqxP4BTmTysT0iOflESYXdgXSetZ--hEsSW0p2RR7GHBLgg5Qkr1dvzWO9rod4hbcqc697AhbzaqipiOjTeYCgChXkdijefzhXZ0G5O5bnp7Tgbpw13hWqjMynqBIpB992mQL8-3kpdaaI_RAjoAXyPN4xx152LUSSjZ3DYKdcLGvHQ5cNyu_a6hIEWnqByyxqzZneGxA_KUCK7Ku7zpNPuDA";
        //String ss ="B_WzWRxRkXUhuXqwEgFRuRXRKa_YYXsT5AMJs793xor92qzTYqmdrcRLbs9C_4jK92Qjm57V6JIKwpc-ohpR8lZqtWgqJGJ6ifxF_auFbraDphhg5LmAUmlfF2Pl699xmcrE-AMFQs5zbN5qUPltmSAL2CWN1PHUFugaL2YZRjA";
        RSAPrivateKey privateKey = getPrivateKey("MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIghBeFozfFv9fLSUv0NhvAqfGcXfWd52u8Ky7pGWFy5MWldub+aT78ZdD56IkPCisKvH5LIc+YEabtPZR2AROeIi616AArm24kUYC6bUO3dX3juLfhW8JpwuOWJAhov5gXJB8+44behvesMRyWQS2DlF0iy/3Ilg2d6OOKWq4OFAgMBAAECgYAL5Ma8wQltVNjqVFIH5gyqIywnXbgJOG5WgFz4c3j48P1ONXArO3JIQhMT+lvQC8lP5Tke/ACUUkJiqTcucqySZYsUI2ieh7joKa+Znt6KfeQ96P7IKNHuM8SuiyMx05TwbFBfs9+VP7HqJR4mQ7zv0JNsyxhvaQlQojfYLpFkqQJBAMBR5Z0jOi0Hv17pMGlydzed9W1gAFmT9zMRp3t8NDNbgFXdD3sOybA5ua9yNq0yGdaP5gMUER3mfdty716ySDMCQQC1NBKA/2wd6OU5AeNZS2bNJ+eYM2ewXrzquXguh43gVzIlm2pNDoUt6Pj75BJ63+RczW1N9Aei/Jpq+scHQq1nAkEAmZ9RqOnAyMONjET9FN4IePbGWy36WZOmPLb3b95Q3E1VAEFq4kN3vDsAJjM3lbWVihy8AO2Alr/M/QScTKpgnQJBAJHj2+YKP+UQ5sTwNThmkd0pfLg44wnILPga3Z0wvFTcP16x83MY9rcQ9K3xYcOWUYk6R//UMvXRxQ3O3MGGuI8CQBGKcab0u8niO2hIxQqQQN9X7HJowivsEjN2ms06pAWGmxeFnhBrFIBpDrs+gYPUIBJ0nOCxcy00w0hpctWF7yQ=");
       String s2 = "{\"MM\":\"870156\",\"BDCQZH\":\"\",\"QLRMC\":\"王秋芳\",\"QLRZJHM\":\"412821198908084444\",\"QLRZJZL\":\"1\",\"BDCDYH\":\"\",\"YHM\":\"00000003\"}";
        String s1 = privateDecrypt(ss, privateKey);
        String s = encryptByPublicKey(s2, getPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCIIQXhaM3xb/Xy0lL9DYbwKnxnF31nedrvCsu6RlhcuTFpXbm/mk+/GXQ+eiJDworCrx+SyHPmBGm7T2UdgETniIutegAK5tuJFGAum1Dt3V947i34VvCacLjliQIaL+YFyQfPuOG3ob3rDEclkEtg5RdIsv9yJYNnejjilquDhQIDAQAB"));
        System.out.println(s);
    }

    /** *//**
     * <p>
     * 公钥加密
     * </p>
     *
     * @param data 源数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey)
            throws Exception {
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

}
