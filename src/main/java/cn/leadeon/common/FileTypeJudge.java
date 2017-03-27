/*
 * 文 件 名:  FileTypeJudge.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014年6月25日  All rights reserved  
 */
package cn.leadeon.common;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 文件类型判断类
 * @author  sunpeng
 * @version  [版本号, 1.0]
 * @since [中国移动手机营业厅BSS系统/实名认证模块]
 */

public final class FileTypeJudge {

	/**
	 * Constructor
	 */
	private FileTypeJudge() {}

	/**
	 * 将文件头转换成16进制字符串
	 * 
	 * @param 原生byte
	 * @return 16进制字符串
	 */
	private static String bytesToHexString(byte[] src) {

		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < 28; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 判断文件类型
	 * 
	 * @param filePath  文件路径
	 * @return 			文件类型
	 */
	public static FileType getType(byte[] bt) throws IOException {
		if(isImage(bt)){
			String fileHead = bytesToHexString(bt);
			if (fileHead == null || fileHead.length() == 0) {
				return null;
			}
			fileHead = fileHead.toUpperCase();
			FileType[] fileTypes = FileType.values();
			for (FileType type : fileTypes) {
				if (fileHead.startsWith(type.getValue())) {
					return type;
				}
			}
		}
		return null;
	}

	/**
	 * 判断是否是图片
	 * @param file
	 * @return true 是 | false 否
	 */
	public static final boolean isImage(byte[] bt) {
		boolean flag = false;
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(bt);
			BufferedImage bufreader = ImageIO.read(in);
			int width = bufreader.getWidth();
			int height = bufreader.getHeight();
			if (width == 0 || height == 0) {
				flag = false;
			} else {
				flag = true;
			}
		} catch (IOException e) {
			flag = false;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
}
