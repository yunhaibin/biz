/*
 * 文 件 名:  BizType.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014年6月25日  All rights reserved  
 */
package cn.leadeon.common;

/**
 * 手机标识类型枚举类
 * 
 * @author sunpeng
 * @version [版本号, 1.0]
 * @since [中国移动手机营业厅BSS系统/实名认证模块]
 */
public enum IdType {

	/**
	 * 手机号
	 */
	mobile("01"),

	/**
	 * 座机号
	 */
	landline("02");

	
    private final String value;
    public String getValue() {
        return value;
    }
    //构造器默认也只能是private, 从而保证构造函数只能在内部使用
    IdType(String value) {
        this.value = value;
    }

}
