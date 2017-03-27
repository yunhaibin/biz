/*
 * 文 件 名:  ResponseEnum.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014-4-14,  All rights reserved  
 */
package cn.leadeon.common;

/**
 * 
 * 响应错误编码
 * <功能详细描述>
 * 
 * @author  liujie
 * @version  [版本号, 2014-12-10]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ResponseEnum {

    public final static String ERR_REDIS_RET = "-1";                       // redis处理失败
	
    public final static String RESCODE_SUCCESSS = "0";                     // 处理请求成功
    
    public final static String RESCODE_ERROR = "1";                        // 处理请求失败
    
    public final static String RESCODE_MESSAGE_CODE = "1";                 // 处理请求返回messageCode错误码
    
    public final static int    ERR_WRONG_PWD_COUNT_LIMIT = 5;              // 服务密码错误次数上限及服务密码修改错误次数
    
    public final static String RESCODE_REQUEST_ERROR = "-3";               // 发送请求失败
    
    public final static String RESPONSE_RIGHT = "200";                     // 正常
    
    public final static String BOSS_RESCODE_SUCCESS = "000000";             // 处理成功
    
    public final static String BOSS_RESCODE_FAILED = "111111";             // 处理失败
    
    public final static String BOSS_RESCODE_BIZ_FAILED = "111112";             // 交互失败
    
    public final static String BOSS_RESCODE_RESULT_SUCCESSS = "0000";             // 处理成功

    public final static String PARAM_OK = "00000";                          // 参数监测正确

    public final static String ERR_SESSION_FAIL = "0001";				   // 会话校验失败（会话服务器校验使用，此处占位）
    
    
    // 0001 至 0100 为和其他相关部门保持一致预留错误码，请勿使用。
    
    public final static String RESPONSE_CONNECT_TIMEOUT_ERROR = "0101";

    public final static String ERR_SEARCH_KEY_NULL = "100001";               // 搜索关键字为空
    
    public final static String ERR_DB_RET_NULL = "100002";                   // 数据库返回结果为空
    
    public final static String ERR_LOCALCONN_FAILURE = "100003";             // 数据库连接获取失败
    
    public final static String ERR_CATCHOTL_ERROR = "100004";                // DB请求异常
    
    public final static String ERR_CACHE_CLEAN_FAIL = "100005";			   // 缓存清理失败
    
    public final static String ERR_CACHE_NAMASPACE = "100006";			   // 缓存命名空间非法
    
    public final static String ERR_SYSTYPE_ERROR = "100007"  ;               // 系统类型错误
    
    public final static String ERR_SYSTYPE_NULL = "100008"  ;                // 系统类型为空
    
    public final static String ERR_TEL_NULL = "100009";                      // 电话号码为空
    
    public final static String ERR_TEL_LEN_ERROR = "100010";                 // 移动用户服务码长度错误
    
    public final static String ERR_TEL = "100011";                           // 移动用户服务码错误

    public final static String RESPONSE_REPEAT_SESSION_ERROR = "100012";

    public final static String ERR_PS_NULL = "100013";                       // 登陆服务密码为空
    
    public final static String ERR_PS_ERROR = "100014";                      // 登陆服务密码为错误
    
    public final static String ERR_BIZCODE_NULL = "100015";                  // 业务CODE为空
    
    public final static String ERR_BIZCODE_ERROR = "100016";                 // 业务CODE错误

    public final static String ERR_LONGITUDE_NULL = "100017" ;               // 经度为空
    
    public final static String ERR_LATITUDE_NULL = "100018" ;                // 纬度为空

    public final static String ERR_SEARCHKEY_NULL = "100019" ;               // 搜索关键字为空
    
    public final static String ERR_PRODUCT_ID_NULL = "100020" ;              // 商品目录信息编码为空
    
    public final static String ERR_PRODUCT_ID_ERROR = "100021" ;             // 商品目录信息编码错误
    
    public final static String ERR_FLAG_ERROR = "100022" ;                   // 所传递的查询地图类型错误
    
    public final static String ERR_MESSTYPE_NULL = "100023" ;                // 反馈信息类型为空
    
    public final static String ERR_FEEDCONTENT_NULL = "100024" ;             // 反馈信息内容为空
    
    public final static String ERR_PROVINCE_CODE_NULL = "100025";            // 省份编码为空
    
    public final static String ERR_CITY_CODE_NULL = "100026";                // 地市编码为空

    public final static String ERR_PAGE_NULL = "100027"  ;                   // 请求页数为空

    public final static String ERR_SEARCHKEY_TYPE_ERROR = "100028" ;         // 搜索类型错误
    
    public final static String ERR_BSNSHALL_ID_NULL = "100029" ;             // 营业厅编号为空
    
    public final static String ERR_SECH_TYPE_NULL = "100030" ;               // 搜索类型为空
    
    public final static String ERR_PAGE_ERROR = "100031"  ;                  // 请求页数错误
    
    public final static String ERR_LONGLATI_DIFFER_ERROR = "100032" ;        // 经纬度不同时错误

    public final static String ERR_ADVCODE_ERROR = "100033";                 // 广告错误
    
    public final static String ERR_CNTYID_NULL = "100034" ;                  // 国家ID为空

    public final static String ERR_CARDPWD_NULL = "100035";                  // 充值卡密码为空
    
    public final static String ERR_GUID_FLAG_NULL = "100036" ;               // 交叉引导标示为空
    
    public final static String ERR_QURMONTH_ERROR = "100037";				   // 月份错误

    public final static String ERR_DOWN_TYPE_ERROR = "100038" ;              // 下载方式错误
    
    public final static String ERR_PRODUCT_TYPE_ERROR = "100039";            // 商品编码类型错误
    
    public final static String ERR_PROVINCE_CODE_ERROR = "100040" ;          // 省份编码错误
    
    public final static String ERR_UNIT_NULL = "100041"  ;                   // 页数单位为空
    
    public final static String ERR_ID_NULL = "100042"  ;                     // 终端id为空
    
    public final static String ERR_TYPE_NULL = "100043"  ;                   // 终端类型为空 系统类型为空
    
    public final static String ERR_PROVINCE_NAME_NULL = "100044"  ;          // 省份名称为空
    
    public final static String ERR_CITY_NAME_NULL = "100045"  ;              // 市名称为空

    public final static String ERR_SYS_VER_NULL = "100046"  ;                // 系统版本号为空

    public final static String ERR_CLIENT_VER_NULL = "100047"  ;             // 客户端版本号为空

    public final static String ERR_GENERATE_UID_ERROR = "100048"  ;          // 唯一ID生成失败
    
    public final static String ERR_UNIT_ERROR = "100049";                    // 页数单位错误
    
    public final static String ERR_TYPE_ERROR = "100050";                    // 终端类型错误
    
    public final static String RESPONSE_XML_PARSE_ERROR = "100051";         // xml 或 json解析失败
    
    public final static String WLANCNETER_KEY_NO_CITY = "100052";            // keywords query CITY_CODE can't be null!
    
    public final static String WLANCNETER_RECOMMEND_NO_CITY = "100053";      // recommend query citycode can't be null!
    
    public final static String WLANCNETER_MAP_TYPE_ERROR = "100054";         // MAP_TYPE is wrong! must be baidu map or gaode map
    
    public final static String REQUEST_XML_LACK_ELEMENT = "100055";          // Request Lack of element
    
    public final static String MONTH_LENGTH_WRONG = "100056"; 				
    
    public final static String DETIAL_LIST_TYPE_WRONG = "100057";            // 详单类型不对
    
    public final static String REQUIRED_FIELD_IS_EMPTY = "100058";           //必选字段为空
    
    public final static String EXTENAL_RESPONSE_IS_EMPTY = "100059";         // 响应报文为空或响应报文业务码错误
    
    public final static String CREATE_REQUEST_XML_FAIL = "100060";
    
    public final static String OTHER_ERROR = "100061";                       // 异常错误
    
    public final static String ERR_AREA_ERROR = "100062";                    // 通过号段获取地域信息错误

    public final static String ERR_FUN_CODE_NULL = "100063";                 // 功能编码为空
    
    public final static String ERR_SCN_TYPE_NULL = "100064";                 // 屏幕类型为空
    
    public final static String ERR_SEND_MSG = "100065";                      // 发送短信失败
    
    public final static String ERR_PROD_ID_NULL = "100066";                  // 活动ID为空
    
    public final static String ERR_PROD_ID_ERR = "100067";                   // 活动ID错误
    
    public final static String ERR_SCN_TYPE_ERROR = "100068";                // 屏幕类型错误
    
    public final static String ERR_CACHE_NUM_ERROR = "100069";               // 缓存管理版本个数错误

    public final static String ERR_WLANBASE_SERVICE_ERROR = "100070";        // WLAN基地服务异常

    public final static String ERR_MOBILE_MALL_ERROR = "100071";             // 移动商城服务异常
    
    public final static String ERR_MONEY_ERROR = "100072";           		   // 移动商城服务异常
    
    public final static String BOSS_DATA_EMPTY = "100073";           		   // BOSS查询数据为空
    
    public final static String RESPONSE_XML_PARSE_EXCEPRION = "100074";      // 解析异常
    
    public final static String RESPONSE_XML_NULL_EXCEPRION = "100075";       // xml为空异常
    
    public final static String ERR_SQL_ERROR = "100076";                     // sql异常
    
    public final static String ERR_JSON_ERROR = "100077";                    // JSON异常
    
    public final static String BOSS_RECORD_IS_ZERO = "100078";               // 详单查询查询结果为空
    
    public final static String BOSS_RECORD_IS_ZERO_ = "100079";              // 详单查询查询结果为空

    public final static String ERR_RECHARGE_TYPE = "100080";                 // 充值类型错误,正确取值: 0为银行卡充值，1为充值卡充值
    
    public final static String ERR_DATE_FORMAT = "100081";             	   // 日期格式错误
    
    public final static String ERR_ORDER_ID_ERROR = "100082";                // 订单号错误

    public final static String ERR_QUERY_TYPE = "100083";                    // 查询类型错误
    
    public final static String ERR_PASSWORD_REACH_LIMIT = "100084";          // 服务密码错误次数达到上限
    
    public final static String ERR_DYNAMICPWD_FREEZE = "100085";             // 动态密码超过上限次数，冻结

    public final static String ERR_DYNAMICPWD_ERROR = "100086";              // 动态密码错误
    
    public final static String ERR_DYNAMICPWD_OVERTIME = "100087";           // 动态密码超时
    
    public final static String ERR_DYNAMICPWD_NULL = "100088";               // 动态密码为空
    
    public final static String ERR_PROVINCE_FUNCTION = "100089";             // 本省不支持此功能
    
	public final static String NOT_FOUND = "100090";                         // token 不存在
	
	public final static String REDIS_TIMEOUT = "100091";                     // Redis 响应超时
	    
    public final static String TOKEN_ERROR = "100092";                       // token 解析错误
    
    public final static String TOKEN_NO_MATCH = "100093";                    // IMEI,IMSI 和 token 不匹配
    
    public final static String ERR_PASSWORD_WRONG = "100094";                // 服务密码错误
    
    public final static String ERR_ACCOUNT_CACELED = "100095";               // 号码已销户
    
    public final static String ERR_ACCOUNT_PRECACELED = "100096";            // 号码已预销户
    
    public final static String ERR_PASSWD_WRONG_SECOND = "100097";           // 服務密碼二期錯誤
    
    public final static String ERR_REACH_WRONG_LIMIT = "100098";             // 超过服务密码修改上限
    
    public final static String SPID_ERROR = "100099";                        // 企业代码错误
    
    public final static String PRODUCT_ID_ERROR = "100100";                  // 产品代码错误（数据库中没有此产品编码）
    
    public final static String FIXEDEXP_LIST_NO_DATA = "100101";             // 套餐及固定费详单无数据
    
    public final static String CALL_LIST_NO_DATA = "100102";                 // 通话详单无数据
    
    public final static String MESSAGE_LIST_NO_DATA = "100103";              // 短信/彩信详单无数据
    
    public final static String NETPLAY_LIST_NO_DATA = "100104";              // 上网详单无数据
    
    public final static String VALUEADDED_LIST_NO_DATA = "100105";           // 增值业务扣费详单无数据
    
    public final static String GENERATION_LIST_NO_DATA = "100106";           // 代收费业务扣费详单无数据
    
    public final static String OTHEREXP_LIST_NO_DATA = "100107";             // 其他费用扣费详单无数据
    
    public final static String RESPONSE_COUNT_EXCEED_LIMIT = "100108";       // 详单查询记录数超过上限

    public final static String ERR_VERIFYCODE_ERR = "100109";                // 动态验证码错误
    
    public final static String ERR_VERIFYCODE_OVERTIME = "100110";           // 动态验证码超时失效
    
    public final static String ERR_VERIFYCODE_FREEZE = "100111";             // 动态验证码超过规定次数冻结
    
    public final static String ERR_VERIFYCODE_NULL = "100112";               // 动态验证码为空
    
    public final static String ERR_VERIFYPWD_NULL = "100113";                // 服务密码为空
    
    public final static String ERR_TEL_NOT_NUMBER = "100114";                      // 电话号码为非数字
    
    public final static String ERR_TEL_LENGTH_NOT_11 = "100115";                      // 电话号码为非11位
    
    public final static String RSQBODY_EMPTY_ERROR = "100116";				//请求报文为空错误	
    
    public final static String ERR_RECHANGEPWD_NULL = "100117"; 			//充值卡密码为空
    
    public final static String ERR_RECHANGEPWD_NOTNUM = "100118"; 			//充值卡密码为非数字
    
    public final static String ERR_NOTNUM = "100119"; 			//数字节点为非数值错误
    
    public final static String PRODUCT_TYPE_NULL = "100120";    //productType参数空
  
    public final static String ERR_QUERY_DATE = "100121";                    // 查询日期错误
    
    public final static String ERR_LOC_CITY = "100122";             // 请求参数  城市编码 为空
    
    public final static String ERR_LOC_PROVICEN = "100123";             // 请求参数  省份编码 为空
    
    public final static String SCN_TYPE_NUM_ERROR = "100124";    //ScnType非数字
    
    public final static String ADVERID_NULL_ERROR = "100125";    //adverId为空错误
    
    public final static String GET_PROVIDER_RESULT_EXCEPTION = "100126"; //获取provider结果异常
    
    public final static String GET_REQPARAMLEN_EXCEPTION = "100127"; //参数长度非法
    
    public final static String PARAM_CHANGE_EXCEPTION = "100128";  //参数封装错误
    
    public final static String BOSS_FUNCODE_ERROR = "100129" ;     //国家ID为错误
    
    public final static String ERR_NET_TYPE_NULL = "100130" ;     //网络类型为空
    
    public final static String ERR_NET_TYPE_ERROR = "100131" ;     //网络类型错误
    
    public final static String THE_RESULT_EMPTY = "100132"; //没有要查找的数据

    public final static String ERR_CID_NULL = "100133" ;     //cid为空
    
    public final static String ERR_IMEI_NULL = "100134" ;     //imei为空
    
    public final static String QRYMONTH_INVALID_FORMATE = "100135"; //为空或格式非法
    
    public final static String NO_RECORDS_OF_CONSUMPTION = "100136"; //当月无消费记录
    
    public final static String ERR_CLIENT_IMAGE_TYPE_NULL = "100137"; //图片类型为空
    
    public final static String ERR_ID_CAED_TYPE_NULL = "100138"; //身份证类型为空
    
    public final static String ERR_CLIENT_IMAGE_FRONT_NULL = "100139"; //实名制登记前图片为空
    
    public final static String ERR_CLIENT_IMAGE_BACK_NULL = "100140"; //实名制登记后图片为空
    
    public final static String ERR_NOT_PICTURE = "100141"; //实名制登记提交的文件不是图片类型
    
    public final static String ERR_PICTURE_PROCESS_FAILED = "100142"; //实名制登记图片处理失败
    
    public final static String ERR_SHOP_FAILED = "100143"; //实名制登记往移动商城提交失败
    
    public final static String ERR_IMSI_NULL = "100135" ;     //imsi为空
    
    public final static String ERR_TOKEN_NULL = "100136" ;     //imsi为空
    
    public final static String ERR_SP_NULL = "100137" ;     //imsi为空
    
    public final static String ERR_TEL_ERROR = "1219";
    
    public final static String PARAM_TYPE_ERROR = "100144";  //参数类型错误
    
    public final static String IS_NOT_CHINA_MOBILE = "100145"; //非移动号码
    
    public final static String PARAM_ARE_NULL = "100146"; //参数为空错误
    
    public final static String QRY_MONTH_ERROR = "100147"; //请求月份不是当前月份
    
    public final static String ERR_PASSWD_WRONG = "210005"; // 服務密碼二期錯誤
    
    // boss返回密码验证错误
 	public final static String PASSWORDERRORCODE = "2036";
 	// boss返回密码验证错误
  	public final static String PASSWORDERRORCODES = "2028";
  	
    public final static String TOKEN_NO_FOUND = "100148";                    // Token未找到
    
}