/*
 * 文 件 名:  FileType.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014年6月25日  All rights reserved  
 */
package cn.leadeon.common;


/**
 * 文件类型
 * @author  sunpeng
 * @version  [版本号, 1.0]
 * @since [中国移动手机营业厅BSS系统/实名认证模块]
 */
public enum FileType {

	/**
	 * JEPG.
	 */
	JPEG("FFD8FF"),

	/**
	 * PNG.
	 */
	PNG("89504E47"),

	/**
	 * GIF.
	 */
	GIF("47494638"),

	/**
	 * Windows Bitmap.
	 */
	BMP("424D");
	
	private String value = "";

	/**
	 * Constructor.
	 * 
	 * @param type
	 */
	private FileType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
