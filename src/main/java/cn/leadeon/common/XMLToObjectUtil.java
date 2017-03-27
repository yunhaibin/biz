/*
 * 文 件 名:  XMLToObjectUtil.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014年12月9日,  All rights reserved  
 */
package cn.leadeon.common;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * xml转对象
 * 
 * @author  liudongdong
 * @version  [版本号, 2014年12月9日]
 * @since  [产品/模块版本]
 */
final public class XMLToObjectUtil{
public static Object convertXmlToObj(Class clazz,String xmlStr){
	try {  
        JAXBContext context = JAXBContext.newInstance(clazz);  
        Unmarshaller unmarshaller = context.createUnmarshaller();  
        return unmarshaller.unmarshal(new StringReader(xmlStr));  
    } catch (JAXBException e) {  
        e.printStackTrace();  
    }  
	return null;
}
}
