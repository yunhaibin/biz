/*
 * 文 件 名:  CreateSig.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014年6月25日  All rights reserved  
 */
package cn.leadeon.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

/**
 * 生成sig
 * @author  sunpeng
 * @version  [版本号, 1.0]
 * @since [中国移动手机营业厅BSS系统/实名认证模块]
 */
public class CreateSig {

	
    /**
     * 生成签名
     *
     * @param params    请求参数
     * @param appSecret App Secret
     * @return 生成成功返回Signature
     * @throws CmopException
     */
    public static String makeSignature(Map<String, String> params, String appSecret) throws Exception {
        String source = makeSource(params, appSecret);

        return md5Encode(source);
    }
    
    /**
     * 拼装验证签名的参数
     *
     * @param params    请求的参数
     * @param appSecret App Secret
     * @return 返回拼装好的字符串
     */
    private static String makeSource(Map<String, String> params, String appSecret) throws Exception {
        Object[] keys = params.keySet().toArray();
        Arrays.sort(keys);
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < keys.length; i++) {
            builder.append( keys[i] + "=" + params.get(keys[i]) ) ;
        }
        builder.append(appSecret);

        return builder.toString();
    }
    
    /**
     * MD5加码 生成32位md5码 
     * 
     * @param originStr md5加密字段
     * @return
     */
	public static String md5Encode(String originStr) {
		String md5String = "";
		StringBuffer buffer = new StringBuffer();
		try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(originStr.getBytes("UTF-8"));

            for (byte b : bytes) {
                buffer.append(Integer.toHexString((b & 0xf0) >>> 4));
                buffer.append(Integer.toHexString(b & 0x0f));
            }
            md5String = buffer.toString();
        } catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace(); 
        } catch (UnsupportedEncodingException usee) {
            usee.printStackTrace(); 
		}
        
		return md5String;
	}
    
}
