/*
 * 文 件 名:  HttpsPost.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014年6月25日  All rights reserved  
 */
package cn.leadeon.common;
/**
 * 生成流水号类
 * @author  sunpeng
 * @version  [版本号, 1.0]
 * @since [中国移动手机营业厅BSS系统/实名认证模块]
 */
import java.text.SimpleDateFormat;
import java.util.Date;

public class SerialNumber {

	public static Integer l = ServerConfig.l;
	
	/**
	 * 生成流水号
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @return
	 */
	public synchronized static  String getSerialNumber() {
		String fileNum = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = df.format(new Date());
		if (l > 999999 || l == 999999) {
			l = 000000;
		} else {
			l++;
		}
		String str = String.format("%1$06d", l) ;

		fileNum = "MCST" + date + str;
		return fileNum;
	}

}
