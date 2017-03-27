//package cn.leadeon.common;
//
//import java.lang.reflect.Field;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.apache.commons.lang.StringUtils;
//
//import cn.leadeon.action.annotion.IllegalPhoneNo;
//import cn.leadeon.action.annotion.IsDouble;
//import cn.leadeon.action.annotion.IsInteger;
//import cn.leadeon.action.annotion.IsMail;
//import cn.leadeon.action.annotion.StrVerify;
//import cn.leadeon.db.resultpojo.CodeMappingDb;
//
//public  class  ValidationTool {
//
//	
//	
//	/** 
//     * 判断是否为浮点数或者整数 
//     * @param str 
//     * @return true Or false 
//     */  
//    public static boolean isNumeric(String str){  
//          if( !str.matches("^(-?\\d+)(\\.\\d+)?$") ){  
//                return false;  
//          }  
//          return true;  
//    }  
//      
//    /** 
//     * 判断是否为正确的邮件格式 
//     * @param str 
//     * @return boolean 
//     */  
//    public static boolean isEmail(String str){  
//        if(StringUtils.isNotBlank(str))  
//            return false;  
//        return str.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");  
//    }  
//      
//    /** 
//     * 判断字符串是否为移动手机号 
//     * @param str 
//     * @return boolean 
//     */  
//    public static boolean isCMCCMobile(String str){  
//        if(StringUtils.isNotBlank(str))  
//            return false;  
//        return str.matches("^0{0,1}(13[4-9]|15[7-9]|15[0-2]|18[7-8]|18[2])[0-9]{8}$");  
//    }  
//      
//    
//    
//    /** 
//     * 判断字符串是否为手机号 
//     * @param str 
//     * @return boolean 
//     */  
//    public static boolean isMobile(String str){  
//        if(StringUtils.isBlank(str))  
//            return false;  
//        return str.matches("^1[3-8][0-9]{9}$");  
//    }  
//    
//    /** 
//     * 判断是否为数字 
//     * @param str 
//     * @return 
//     */  
//    public static boolean isNumber(String str) {
//    	if(StringUtils.isNumeric(str))
//    		return true;
//    	return false;
//    }  
//      
//  
//    /**
//	 * 判断是否为UTF8的中文字符
//	 * 
//	 * @param str
//	 * @return
//	 */
//	public static boolean isUTF8Chinese(String str) {
//		if (str == null) {
//			return false;
//		}
//		char UTR8_MAX_VALUE = '\u9fa5';
//		int sz = str.length();
//		for (int i = 0; i < sz; i++) {
//			if (str.charAt(i) > UTR8_MAX_VALUE) {
//				return false;
//			}
//		}
//		return true;
//	}
//	
//	 /** 
//     * 判断字符串是否非法字符
//     * @param str 
//     * @return boolean 
//     */  
//    public static boolean isIllegal(String str){  
//    	String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
//		Pattern p = Pattern.compile(regEx); 
//		 Matcher m = p.matcher(str);
//		 if (m.find()) {
//			 return true;
//		 }else {
//			 return false;
//		 }
//    }
//
//    /** 
//     * 判断字符串是否为double类型 
//     * @param str 
//     * @return boolean 
//     */  
//    public static boolean isDoubleStr(String str){  
//        if(StringUtils.isBlank(str))  
//            return false;  
//        return str.matches("[-+]?[0-9]+(\\.[0-9]+)?");   
//    }  
//	public static CodeMappingDb validationField(Object obj){
//		CodeMappingDb codeDb = new CodeMappingDb();
//		Field[] fields =  obj.getClass().getDeclaredFields();
//		for(Field field : fields){
//			field.setAccessible(true);
//			Object o=null;
//			try {
//				o = field.get(obj);
//			} catch (Exception e1) {
//			}
//			
//			try{
//				IsInteger isint = field.getAnnotation(IsInteger.class);
//				if(isint!=null){
//					if(!isNumber(o.toString())){
//						codeDb.setCodeNumber(ResponseCode.ISNUMBER_ERROR);
//						codeDb.setCodeDesc("FiledName: " + field.getName() + " is not number!");
//						return codeDb;
//					}
//					
//					if (StringUtils.isEmpty(o.toString())) {
//						codeDb.setCodeNumber(ResponseCode.NOTNULL_ERROR);
//						codeDb.setCodeDesc("FiledName: " + field.getName() + " is null!");
//						return codeDb;
//					}
//					
//					if(Integer.parseInt(o.toString())<isint.min()){
//						codeDb.setCodeNumber(ResponseCode.LENGTH_ERROR);
//						codeDb.setCodeDesc("FiledName: " + field.getName() + " length is illeagl! ");
//						return codeDb;
//					}
//					if(Integer.parseInt(o.toString())>isint.max()){
//						codeDb.setCodeNumber(ResponseCode.LENGTH_ERROR);
//						codeDb.setCodeDesc("FiledName: " + field.getName() + " length is illeagl! ");
//						return codeDb;
//					}
//				}
//				StrVerify sv = field.getAnnotation(StrVerify.class);
//				if(sv!=null){
//					if(sv.isNotNull()){
//						if(StringUtils.isBlank(o.toString())){
//							codeDb.setCodeNumber(ResponseCode.NOTNULL_ERROR);
//							codeDb.setCodeDesc("FiledName: " + field.getName() + " is null!");
//							return codeDb;
//						}
//					}
//					if(o!=null &&  StringUtils.isNotBlank(o.toString())){
//						if(sv.isNotIllegal() && isIllegal(o.toString())){
//							codeDb.setCodeNumber(ResponseCode.ISNOTILLEGAL_ERROR);
//							codeDb.setCodeDesc("FiledName: " + field.getName() + " is illeagl! ");
//							return codeDb;
//						}
//						if(o.toString().length() < sv.minLength()){
//							codeDb.setCodeNumber(ResponseCode.LENGTH_ERROR);
//							codeDb.setCodeDesc("FiledName: " + field.getName() + " length is illeagl! ");
//							return codeDb;
//						}
//						if(o.toString().length() > sv.maxLength()){
//							codeDb.setCodeNumber(ResponseCode.LENGTH_ERROR);
//							codeDb.setCodeDesc("FiledName: " + field.getName() + " length is illeagl! ");
//							return codeDb;
//						}
//						
//						if(sv.isNumber() && !StringUtils.isNumeric(o.toString())){
//							codeDb.setCodeNumber(ResponseCode.ISNUMBER_ERROR);
//							codeDb.setCodeDesc("FiledName: " + field.getName() + " is not number!");
//							return codeDb;
//						}
//						if(sv.isNumber() && !o.toString().matches("^[0-9]*[1-9][0-9]*$") && sv.isPage()){
//							codeDb.setCodeNumber(ResponseCode.ISNUMBER_ERROR);
//							codeDb.setCodeDesc("FiledName: " + field.getName() + " is not decimal part or zero!");
//							return codeDb;
//						}
//					}
//				}
//				if(field.isAnnotationPresent(IsMail.class)){
//					if(!isEmail(o.toString())){
//						codeDb.setCodeNumber(ResponseCode.ISNOTILLEGAL_ERROR);
//						codeDb.setCodeDesc("FiledName: " + field.getName() + " email partten is illeagl! ");
//						return codeDb;
//					}
//				}
//				
//				if(field.isAnnotationPresent(IllegalPhoneNo.class)){
//					if(!isMobile(o.toString())){
//						codeDb.setCodeNumber(ResponseCode.PHONENUMBER_ERROR);
//						codeDb.setCodeDesc("FiledName: " + field.getName() + "is not telNum! ");
//						return codeDb;
//					}
//				}
//				IsDouble isDouble = field.getAnnotation(IsDouble.class);
//				if((isDouble!=null) && (isDouble.isNotNull())){
//					if((o!=null) && !(StringUtils.isEmpty(o.toString()))){
//						if(StringUtils.isBlank(o.toString())){
//							codeDb.setCodeNumber(ResponseCode.NOTNULL_ERROR);
//							codeDb.setCodeDesc("FiledName: " + field.getName() + " is null!");
//							return codeDb;
//						}
//						
//						if(isDouble.isLegal() && o.toString().matches("[\\pP|~|$|^|<|>|\\||=]*")){
//							codeDb.setCodeNumber(ResponseCode.ISNOTILLEGAL_ERROR);
//							codeDb.setCodeDesc("FiledName: " + field.getName() + " is illeagl! ");
//							return codeDb;
//						}
//						
//						if(o.toString().length() > isDouble.maxLength()){
//							codeDb.setCodeNumber(ResponseCode.LENGTH_ERROR);
//							codeDb.setCodeDesc("FiledName: " + field.getName() + " length is illeagl! ");
//							return codeDb;
//						}
//						
//						if(!isDoubleStr(o.toString())){
//							codeDb.setCodeNumber(ResponseCode.IDDOUBLE_ERROR);
//							codeDb.setCodeDesc("FiledName: " + field.getName() + " is not double! ");
//							return codeDb;
//						}
//					}
//				}
//			}catch(Exception e){
//				codeDb.setCodeNumber(ResponseCode.REQUEST_ERROR);
//				codeDb.setCodeDesc("please rationalliy request!");
//				return codeDb;
//			}
//		}
//		codeDb.setCodeNumber(ResponseCode.REQUEST_SUCCESS);
//		codeDb.setCodeDesc("SUCCESS");
//		return codeDb;
//	}
//	
//	
//
//    private ValidationTool(){} 
//    
//}
