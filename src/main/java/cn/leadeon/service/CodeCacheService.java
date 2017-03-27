//package cn.leadeon.service;
//
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//import org.apache.commons.lang.StringUtils;
//import org.jboss.netty.util.internal.ConcurrentHashMap;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import cn.leadeon.comm.base.ResultData;
//import cn.leadeon.comm.log.Log;
//import cn.leadeon.common.ComVariable;
//import cn.leadeon.common.DateTimeTool;
//import cn.leadeon.common.ResponseCode;
//import cn.leadeon.common.ResponseEnum;
//import cn.leadeon.db.constant.DBResCode;
//import cn.leadeon.db.resultpojo.CodeMappingDb;
//import cn.leadeon.db.service.CodeMappingService;
//
//@Component
//public class CodeCacheService implements  InitializingBean{
//	
//	private static Log logger = new Log(CodeCacheService.class);
//	
//
//	private static Map<String,CodeMappingDb>  CODE_CACHE_MAP  = new ConcurrentHashMap<>();
//	
//	private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
//	
//	@Autowired
//	private CodeMappingService codeMappingService;
//	
//	
//	/** 
//	* @Title: findDescByBusCodeAndInte 
//	* @Description: 通过后台业务编码查询描述
//	* @param @param busCode
//	* @param @param integCode
//	* @param @return   
//	* @return CodeMappingDb  
//	* @throws 
//	*/ 
//	public CodeMappingDb findDescByCode(String codeNumber){
//		return findDesc(null,null, codeNumber);
//	}
//	
//	/** 
//	* @Title: findDescByBusCodeAndInte 
//	* @Description: 通过页面编码,集成域返回码 查询描述
//	* @param @param busCode
//	* @param @param integCode
//	* @param @return   
//	* @return CodeMappingDb  
//	* @throws 
//	*/ 
//	public CodeMappingDb findDescByBusCodeAndInte(String busCode,String integCode){
//		return findDesc(busCode,integCode, null);
//	}
//	
//	
//	
//
//	private  CodeMappingDb findDesc(String busCode,String integCode,String codeNumber){
//		CodeMappingDb cm = new CodeMappingDb();
//		cm.setBusinessCode(busCode);
//		cm.setIntegrationCode(integCode);
//		cm.setCodeNumber(codeNumber);
//		if(CODE_CACHE_MAP.isEmpty()){
//			threadRun(0l);
//		}
//		
//		if(CODE_CACHE_MAP.get(createKey(cm)) != null){
//			cm = CODE_CACHE_MAP.get(createKey(cm));
//		}else{
//			// 调用查询动态提示信息方法，需要安装信息提示文档查询，按照文档不会走到此步，
//			// 此处容错处理
//			if(StringUtils.equals(ResponseCode.REQUEST_SUCCESS, codeNumber) 
//					|| StringUtils.equals(ResponseEnum.BOSS_RESCODE_RESULT_SUCCESSS, codeNumber)){
//				cm.setCodeNumber(ResponseCode.REQUEST_SUCCESS);
//				cm.setCodeDesc(ComVariable.RSP_DESC_SUCCESS);
//			}else{
//				logger.error("busCode=" + busCode + " integCode" + integCode + " codeNumber=" + codeNumber);
//				cm.setCodeNumber(ResponseCode.DEFALUT_ERROR);
//				cm.setCodeDesc(ComVariable.RSP_DESC_DEFAULT_ERR);
//			}
//		}
//		
//		return cm;
//	}
//	
//	
//	@Override
//	public void afterPropertiesSet() throws Exception {
//		logger.info("初始化加载动态返回码描述....");
//		threadRunLoop(60L*60L*24L);
//	}
//	
//	
//	private void updateCodeCache(){
//		logger.info(DateTimeTool.getCurrentDate(DateTimeTool.DEFAULTFORMAT) + ":开始执行动态返回码描述查询...");
//		ResultData<List<CodeMappingDb>> rd = codeMappingService.getAllCodeDes();
//		if(rd.getResultCode().getCode().equals(DBResCode.BOSS_RESCODE_SUCCESS.getCode())){
//			List<CodeMappingDb> list =  rd.getResultData();
//			for(CodeMappingDb code : list){
//				CODE_CACHE_MAP.put(createKey(code), code);
//			}
//			// threadRunLoop(60L*60L*24L);
//			//threadRun(60L*5);
//		}else{
//			threadRun(0l);
//			logger.info(DateTimeTool.getCurrentDate(DateTimeTool.DEFAULTFORMAT) + 
//					":动态返回码描述查询失败.." + rd);
//		}
//		logger.info(DateTimeTool.getCurrentDate(DateTimeTool.DEFAULTFORMAT) + ":执行动态返回码描述查询结束...");
//	}
//	
//	private String createKey(CodeMappingDb code){
//		StringBuilder strb = new StringBuilder();
//		if(StringUtils.isNotEmpty(code.getBusinessCode())){
//			strb.append(code.getBusinessCode()).append(code.getIntegrationCode());
//		}else{
//			strb.append(code.getCodeNumber());
//		}
//		return strb.toString();
//	}
//	
//	
//	private void threadRun(Long time){
//		executor.schedule(new Runnable() {
//			@Override
//			public void run() {
//				updateCodeCache();
//			}
//		}, time, TimeUnit.SECONDS);
//	}
//	
//	private void threadRunLoop(Long time){
//		executor.scheduleAtFixedRate(new Runnable() {
//			@Override
//			public void run() {
//				updateCodeCache();
//			}
//		}, 0, time, TimeUnit.SECONDS);
//	}
//}
