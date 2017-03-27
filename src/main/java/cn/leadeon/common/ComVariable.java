package cn.leadeon.common;

/**
 * 
 *公共变量定义
 * <功能详细描述>
 * 
 * @author  liujie
 * @version  [版本号, 2014-12-10]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ComVariable {
    
    public final static String DATEFORMAT = "yyyy-MM-dd HH:mm:ss:SSS"; //日期格式
    
    public final static String MM_DATEFORMAT = "yyyy-MM-dd HH:mm:ss"; //87业务日期格式
    public final static String UNIX_START_TIME = "1970-01-01 00:00:00";
    
    public final static String PHONE = "99999999999";//87业务TEL_NO为空时补充               推送业务(DF01401) 手机号码为空的默认值
    public final static String DEF_PROVINCE_CODE = "9999";
    public final static String DEF_IMEI = "XXXXXXXXXXXXXXX";
    public final static String SPACE = "|#$";
    public final static int PHONE_LEN = 11;//TEL_NO位数
    public final static String INLINE_LIST_MAX_CNT = "10"; //内层List最大个数

    public final static String DEF_CLIENT_VER = "default"; //各省特色业务配置默认版本配置定义标记
    
    public final static char SYSTYPE_MIN = '0';           // 系统类型--最小
    public final static char SYSTYPE_MAX = '9';           // 系统类型--最大
    public final static char SYSTYPE_R_MIN = 'A';         // 系统类型--最小
    public final static char SYSTYPE_R_MAX = 'Z';         // 系统类型--最大
    public final static char SYSTYPE_ANDROID = '1';       // 系统类型--ANDROID
    //public final static char SYSTYPE_IPHONE = '2';        // 系统类型--IPHONE
    // 客户端区分标识
    public final static String SYSTYPE_DISTINGUISH = "com.greenpoint.android.mc10086.activity";
    public final static int PROVINCE_NUM = 31;              // 省份个数
    // 省编码鉴权  20131014*/
    public final static int[] g_vaProv_code = {
                            290,280,200,571,100,210,220,230,240,250,
                            270,311,351,371,431,451,471,531,551,591,
                            731,771,791,851,871,891,898,931,951,971,
                            991};
    
    // 客户端发布审核方案&客户端强制升级方案 
    public final static char CHECK_VERSION_PASSED = 'P';
    public final static char CHECK_VERSION_WAIT = 'W';
    public final static char UPWAY_FORCE = '0';
    public final static char UPWAY_NOT_FORCE = '1';
    public final static char UPWAY_NOT_NEED = '2';
    public final static char UPWAY_DEF = 'a';
    
    // Redis根据功能分库
    public final static int REDIS_DATABASE_AREA = 0;
    public final static int REDIS_DATABASE_PASSWD_LIMIT = 1;
    public final static int REDIS_DATABASE_LOGIN = 2;
    public final static int REDIS_DATABASE_CACHEADMIN = 3;
    public final static int REDIS_DATABASE_AUTOLOGIN = 4;
    public final static int REDIS_DATABASE_DYNAMICPWD = 5;
    public final static int REDIS_DATABASE_FEEDBACK_UNREAD = 6;
    public final static int REDIS_DATABASE_UID = 7;
    public final static int REDIS_DATABASE_SERVICE_PWD_AUTH = 8;
    
    public final static int LOGIN_MAX_PASSWD_CNT = 10;
    public final static int RAND_STATUS_FREEZE = 1;
    public final static int RAND_STATUS_OK = 0;
    public final static int RAND_LIMIT_COUNT = 5;  
    public final static int RAND_LIMIT_TIME = 600; //10分钟（10*60）
    public final static int DYN_PASSWD_LIMIT_TIME = 300; //5分钟
    
    public final static String STATISTICAL_PRINT_TIME = "00:00:00"; //打印时间HH:mm:ss
    
    public static final String RIGHTTAG = "Right:";
    public static final String ERRORTAG = "Error:";
    
    /* BOSS请求类型 */
    public final static String BOSS_QUERY_REQ = "QryInfoReq"; 
    public final static String BOSS_TRANSACTION_REQ = "TransactionReq"; 
    public final static String BOSS_UNSUBSCRIBE_REQ = "UnsubscribeReq";
    public final static String BOSS_DETAILLIST_REQ = "TmemInfoReq";
    //public final static String BOSS_USERINFOMATION_REQ = "QryInfoReq";
    //public final static String BOSS_BUSINESS_REQ = "QryInfoReq";
	
	public final static int PASSWORD_6_LEN = 6;
	public final static int PASSWORD_8_LEN = 8;
	
	
	/* WLAN实时查询相关配置*/
    // Wlan基地连接信息
	public static String wlanPasswd = null;
	
    public final static String WLAN_PALT_CODE = "0000CMBH";
    
    public final static String WLAN_ACCOUNT = "CMBHQHP";
    
    public final static String WLAN_TEST_PASSWD = "CMBHQHP";
    
    public final static String WLAN_PRODUCT_PASSWD = "kZdDM2SB";
    
    // 加密访问WLAN基地账号及密码使用
    public final static String WLAN_ENCRYPTKEY = "huawei1@3$567*9!";
    
    // 访问wlan基地https使用
    public final static String WLAN_AUTH_KEY = "Hua@#123Wei";
    
    // 字符参数为空
    public final static String PARA_LENGTH_IS_NULL = "0";
    
    // 号码段长度
    public final static int NUMBER_PREFIX_LENGTH = 7;
    
    // 错误密码错误次数上限
    public final static int PASSWD_MAX_WRONG_CNT = 5;
    
    // 登陆提示信息(发送短信类型)
    public final static String SEND_SMS_LOGIN = "0";
    
    // 详单查询验证码
    public final static String SEND_SMS_DETAIL = "1";
    
    // 流量查询验证码
    public final static String SEND_SMS_GPRS = "2";
    
    // 详单查询提示信息
    public final static String SEND_SMS_DETAIL_TEXT = "3";
    
    // 流量查询提示信息
    public final static String SEND_SMS_GPRS_TEXT = "4";
    
    // 动态密码
    public final static String SEND_SMS_PWD = "5";
    
    //短信验证码
    public final static String MESSAGE_CODE = "6";
    
    // http响应ok
    public final static int HTTP_RESPONSE_STATUS_OK = 200;
    
    /* 单位换算类变量定义 */ 
    public final static int KILO = 1024;
    
    
    // 一二期迁移缓存二期操作标志
    public final static String FLAG_2_0 = "0";
    
    public final static String RECHARGE_CHANNEL_ID = "11";
    
    public final static String BIZ_CODE_STR = "code";
    
    public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss"; 

    public final static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    
    public final static String YYYYMM = "YYYYMM";
    
    public final static String XML_START_STR = "<?xml";
    
    public final static String XML_START_STR_ = "<ROOT";
    
    /**
     * 1小时3600秒
     */
	public static long HOUR_SEC_NUMBER = 3600L;

    /**
     * 1 天 24 * 3600 秒
     */
	public static long DAY_SEC_NUMBER = HOUR_SEC_NUMBER * 24;
	
	// 系统类型
	public final static String SYSTYPE_ANDDROID = "1";
	public final static String SYSTYPE_ANDDROID_BETA = "9";
	
	public final static String SYSTYPE_IPHONE = "2";
	public final static String SYSTYPE_IPHONE_BETA = "8";
	
	public final static String SYSTYPE_IPAD = "7";
	public final static String SYSTYPE_IPAD_BETA = "5";
	
	public final static String SYSTYPE_WINPHONE = "4";
	public final static String SYSTYPE_WINPHONE_BETA = "6";
	
    // 正确编码返回提示信息
    public final static String RSP_DESC_SUCCESS = "SUCCES";
    
    // 默认错误编码返回提示信息
    public final static String RSP_DESC_DEFAULT_ERR = "对不起，您的请求暂时无法受理";
    
    //业务退订bunessType种类
    public final static String BUNESSTYPE_01 = "01";//当bunessType为01的时候bunessCode必填
    public final static String BUNESSTYPE_02 = "02";//当bunessType为02的时候spid和bizCode必填
    public final static String SPECIAL_CHARACTERS = "#";//替换提示信息中的#为当前业务名称
    
    //Trace名称, 唯一标志名称，即流水号
    public final static String TRACE = "Trace";
    
    // 编码格式
    public final static String ENCODING_UTF8 = "UTF-8";
    public final static String CONTENTTYPE_JSON = "application/json;charset=UTF-8";
}

