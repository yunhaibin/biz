package cn.leadeon.common;

/**   
* @Title: ResponseCode.java 
*
* @Package cn.leadeon.common 
*
* @author gavin   
*
* @date 2015-1-12 下午3:55:15 
*
* @Description: 后台业务返回码
*
* @version 1.0-SNAPSHOT   
*/ 
public class ResponseCode {

	
	public static final String REQUEST_SUCCESS="000000"; //成功
	
	public static final String DEFALUT_ERROR="110000"; // 后台默认错误
	
	public static final String SERVER_FAILURE="110001"; // 后台服务异常
	
	public static final String CACHE_FAILURE="110002"; // 缓存操作异常
	
	public static final String REQUEST_ERROR="310000";  //请求异常 请求参数不合法或不合理
	
	public static final String NOTNULL_ERROR = "310001"; //参数为空
	
	public static final String LENGTH_ERROR = "310002"; //参数长度错误
	
	public static final String ISNUMBER_ERROR = "310003"; //参数不是数字
	
	public static final String ISNOTILLEGAL_ERROR = "310004"; //参数有非法字符
	
	public static final String ENUM_ERROR = "310005"; //参数不在枚举类型内
	
	public static final String IDDOUBLE_ERROR = "310005"; //参数不是double类型
	

	public static final String PHONENUMBER_ERROR="312000";  //手机号码不是移动号码
	
	public static final String SERVERPSW_ERROR="312001";//当日累计10次服务密码不正确
	
	public static final String CMCCPSW_ERROR="312002";//输入短信密码不正确
	
	public static final String CMCCPSW_REPETITION="312003";//短信密码5分钟内获取多次，而登录时不是以最后一次的获取短信密码登录，
	
	public static final String CMCCPSW_DISABLED="312004"; //短信密码以最后一次获取的为准，有效时间为5分钟，超过5分钟时
	
	public static final String CMCCPSW_TIMEOUT="312005"; //10分钟内获取超过5次
	
	public static final String NUMBERCODE_ERROR="312006"; //如果输入的号码在数据库中无法查到，则在查询结果区域信息提示
	
	public static final String MONTH_ONLYONE_ERROR="312007"; //查询的月份只有一位错误。比如:需要传入01月，却传入的1。就报这个错。
	
	
	public static final String ERR_REACH_WRONG_LIMIT="210011"; //客服密码修改今天已超过上限。
	
	public static final String ERR_LOGOUT="210002"; //已销户的手机号码登录
	
	public static final String ERR_READY_LOGOUT="210003"; //预销户的手机号码登录
	
	public static final String DB_RET_NULL = "600001";
	
	public static final String ERR_DB_RET_NULL = "600010"; //移动公告无数据
	
	public static final String BUSINESS_DB_RET_NULL = "600003"; //商品详情无数据
	
	public static final String PESPONSE_DB_RET_NULL = "600004"; //个性化列表数为空
	
	public static final String TERMINAL_DB_RET_NULL = "600005"; //终端售后数据库无数据
	
	public static final String NEWQUERY_DB_RET_NULL = "600009"; //数据库中商品数为空
}
