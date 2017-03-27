/*
 * 文 件 名:  ServerConfig.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014年6月25日,  All rights reserved  
 */
package cn.leadeon.common;

import java.io.IOException;
import java.util.Properties;


/**
 * 实名制配置类
 * @author  sunpeng
 * @version  [版本号, 1.0]
 * @since [中国移动手机营业厅BSS系统/实名认证模块]
 */
public class ServerConfig {

	//图片处理参数
	public final static  double pictureCreate = 1;
	public final static  double pictureCompression = 0.9;
	//图片控制大小
	public final static int pictureSize = 35840 ;
	//商户的APPKEY
	public final static String appkey = "f6143683996515cba2af72e808ab06e0" ;
	//签名认证参数（md5值）
	public final static String appSecret = "2191987658b31bd6fa52a37068338de5" ;
	//接口版本号
	public final static String ver = "1.0" ;
	//移动商城数据接收成功
	public final static String status = "0" ;
	//移动商城https密码
	public final static String httpsPassword = "lvdian88" ;
	//移动商城密钥库
	public final static String keyStorePath = "/opt/dependCfg/RealName/securety/ldclient.jks" ;
	//移动商城密钥库类型
	public final static String keyStoreType = "JKS" ;
	//移动商城信任库类型
	public final static String trustStoreType = "JKS" ;
	//移动商城信任库
	public final static String trustStorePath = "/opt/dependCfg/RealName/securety/ldclient.jks" ;
	//https请求连接超时时间
	public final static int connectTimeout = 15000 ;
	//https请求读数据超时时间
	public final static int readTimeout = 30000;
	//https请求发送数据编码
	public final static String charEncode = "UTF-8";
	//实名认证url
//	public static final String realNameVerifyUrl = "https://openapi.shop.10086.cn/mobile/realNameVerify.json" ;
//	public static final String realNameVerifyUrl = "http://192.168.6.31:8080/Client/mobile" ;
	public static String realNameVerifyUrl = null ;
    //查询用户办理表url
//	public static final String userStatusUrl = "https://openapi.shop.10086.cn/mobile/getStatus.json" ;
	public static String userStatusUrl = null ;
	//用户查询属性表url
//	public static final String getUserVerifyTypeUrl = "https://openapi.shop.10086.cn/mobile/getUserVerifyType.json" ;
	public static String getUserVerifyTypeUrl = null ;
    //数据库分隔符
	public final static String SPACE = "|#$";
	
	public static Integer l = 000000 ;
	
	public static Properties prop = null; // conf.properties
	static {
		prop = new Properties();
		try {
			prop.load(ServerConfig.class.getClassLoader().getResourceAsStream(
					"bizConf.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			prop.clone();
		}
	}
	
	
}
