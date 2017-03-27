/*
 * 文 件 名:  ResponseHeaderUtil.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2015-1-13,  All rights reserved  
 */
package cn.leadeon.common;

import javax.servlet.http.HttpServletResponse;

import org.jboss.resteasy.spi.CorsHeaders;

/**
 * <ResponseHeader设置工具类>
 * <功能详细描述>
 * 
 * @author  yunhaibin
 * @version  [版本号, 2015-1-13]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ResponseHeaderUtil {
	
	/**
	 * ACCESS_ORIGIN_ALL 内网接收所有跨域来源
	 */
	public static final String ACCESS_ORIGIN_ALL = "*";
	
	/**
	 * ALLOW_HEADERS 复杂跨域请求允许header中设置其他属性
	 */
	public static final String ALLOW_HEADERS = "Origin, X-Requested-With, Content-Type, Accept";
	
	/**
	 * MAX_AGE 客户端在此时间内不重复发送跨域探测请求
	 */
	public static final String MAX_AGE = "3600";
	
	/** 
	 * <设置H5跨域Cors头部属性>
	 * <功能详细描述>
	 * @param response
	 * @see [类、类#方法、类#成员]
	 */
	public static void setH5CorsHeader(HttpServletResponse response) {
		response.addHeader(CorsHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, ACCESS_ORIGIN_ALL);
	    response.addHeader(CorsHeaders.ACCESS_CONTROL_ALLOW_HEADERS, ALLOW_HEADERS);
	    response.addHeader(CorsHeaders.ACCESS_CONTROL_MAX_AGE, MAX_AGE);
	}
}
