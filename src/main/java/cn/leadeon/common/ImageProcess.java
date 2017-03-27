/*
 * 文 件 名:  ImageProcess.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014年6月25日  All rights reserved  
 */
package cn.leadeon.common;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import sun.misc.BASE64Encoder;

/**
 * 图片处理类
 * @author  sunpeng
 * @version  [版本号, 1.0]
 * @since [中国移动手机营业厅BSS系统/实名认证模块]
 */
public class ImageProcess {

	/**
	 * InputStream 图片 转byte[]
	 * @param input
	 * @return
	 * @throws IOException
	 */
	
	public static byte[] toByteArray(InputStream input) throws IOException {
	    ByteArrayOutputStream output = new ByteArrayOutputStream();
	    byte[] buffer = new byte[input.available()];
	    int n = 0;
	    while (-1 != (n = input.read(buffer))) {
	        output.write(buffer, 0, n);
	    }
	    byte [] bt = output.toByteArray() ;
	    input.close() ;
	    output.close() ;
	    return bt ;
	}
	
	
	/**
	 *  图片重构
	 * @param bt
	 * @param Proportion
	 * @return
	 */
	public static byte[] reCreatePic(byte[] bt , double Proportion) {
		byte[] data = null ;
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(bt);
			BufferedImage originalImage = ImageIO.read(in);
			in.close() ;
			int width = (int)(originalImage.getWidth(null) * Proportion) ;
			int height = (int)(originalImage.getHeight(null) * Proportion) ;
			BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = newImage.getGraphics();
			g.drawImage(originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0,null);
			g.dispose();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(newImage, "jpg", os);
			data = os.toByteArray() ;
		} catch (IOException io) {
			io.printStackTrace();
			return null ;
		}
		return data;
	}
	
	/**
	 * 接收图片流通过BASE64加密转为String
	 * 
	 * @param in
	 * @return
	 */

	public static String getImageStr(byte[] data) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data).replaceAll("\\s{1,}", "");
	}
	
}
