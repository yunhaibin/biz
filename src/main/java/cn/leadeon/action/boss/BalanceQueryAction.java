//package cn.leadeon.action.boss;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.MediaType;
//
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import cn.leadeon.boss.resultpojo.BalanceQueryBoss;
//import cn.leadeon.boss.service.BalanceQueryService;
//import cn.leadeon.cache.service.TimeOperationService;
//import cn.leadeon.comm.base.ResultData;
//import cn.leadeon.comm.log.Log;
//import cn.leadeon.common.BusinessCode;
//import cn.leadeon.common.CacheNameSpace;
//import cn.leadeon.common.DateTimeTool;
//import cn.leadeon.common.LogCommonTool;
//import cn.leadeon.common.ResponseCode;
//import cn.leadeon.common.ResponseEnum;
//import cn.leadeon.common.ValidationTool;
//import cn.leadeon.db.resultpojo.CodeMappingDb;
//import cn.leadeon.db.service.CellNumLocationService;
//import cn.leadeon.reqbody.BalanceQueryReq;
//import cn.leadeon.reqbody.ReqBody;
//import cn.leadeon.resbody.BalanceQueryRes;
//import cn.leadeon.resbody.ResBody;
//import cn.leadeon.service.CodeCacheService;
//
//import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
//import com.alibaba.fastjson.JSON;
//
///**
// * 
// * 话费余额查询 1.9 Code:701 2.0 Code:20001 <功能详细描述>
// * 
// * @author suwenbo
// * @version [版本号, 2014-12-10]
// * @see [相关类/方法]
// * @since [产品/模块版本]
// */
//@Path("balanceQueryService")
//@Component
//public class BalanceQueryAction {
//
//	//话费余额service接口
//	@Autowired
//	private BalanceQueryService balanceQueryService;
//	
//	//号码归属地接口Service
//	@Autowired
//	public CellNumLocationService cellNumLocationService;
//	
//	//缓存接口Service
//	@Autowired
//	public CodeCacheService codeCacheService;
//	
//	//系统日志输出
//	private static  Log logger = new Log(BalanceQueryAction.class);
//	
//	//统计日志输出
//	private static Log loggerStatic = new Log("BOS_QBLANCE_DTL");
//
//    @Autowired
//    public TimeOperationService timeOperationService;
//	/**
//	 * 话费余额入口
//	 * @param request
//	 * @param reqContent
//	 * @return
//	 */
//	@POST
//	@Path("getBalanceQuery")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(ContentType.APPLICATION_JSON_UTF_8)
//	public ResBody<BalanceQueryRes> getBalanceQuery(@Context HttpServletRequest request,ReqBody<BalanceQueryReq> reqContent) {
//		//定义业务请求的开始时间
//		Long startTime = System.currentTimeMillis();
//		/******** 返回客户端对象 ******/
//		ResBody<BalanceQueryRes> rsp = new ResBody<BalanceQueryRes>();
//		//当前时间
//		String time = DateTimeTool.getCurrentDate(DateTimeTool.LONGFORMAT);
//		ResultData<BalanceQueryBoss> bossResult = null;
//		//日志打印参数对象
//		Map<String,String> map = new HashMap<String,String>();
//		// 来源地址
//		map.put("SOURCEVAR", request.getRemoteAddr());
//		// 目标地址
//		map.put("DESTVAR", request.getLocalAddr());
//		// 客户端版本号
//		String clientVersion = reqContent.getCv();
//		map.put("CLIENTVERSION", clientVersion);
//		// 系统类型
//		String sysType = reqContent.getSt();
//		map.put("SYSTYPE", sysType);
//		
//		//获取id
//		String trace = request.getHeader("Trace");
//		
//		logger.reqPrint(Log.BOSS_SIGN, Log.CLIENT_REQUEST, trace,BusinessCode.BALANCE_QUERY, JSON.toJSONString(reqContent));
//		// 会话id
//		map.put("SSETIONIDVAR", trace);
//		//错误信息查询对象
//		CodeMappingDb cm = null;
//		try {
//			//验证参数是否符合要求,并把错误信息结果码设置到ResBody中
//			cm = ValidationTool.validationField(reqContent.getReqBody());
//			rsp.setRetCode(cm.getCodeNumber());
//			//如果校验失败的情况
//			if (!ResponseCode.REQUEST_SUCCESS.equals(rsp.getRetCode())) {
//				//针对号码非法提示动态信息
//        		if(ResponseCode.PHONENUMBER_ERROR.equals(rsp.getRetCode())){
//        			rsp.setRetDesc(codeCacheService.findDescByCode(rsp.getRetCode()).getCodeDesc());
//        		}else{
//        			rsp.setRetDesc(cm.getCodeDesc());
//        		}
//				// 业务处理量明细日志
//				LogCommonTool.businessMonitorLog(rsp.getRetCode(), map,BusinessCode.BALANCE_QUERY, time);
//				logger.error(trace, BusinessCode.BALANCE_QUERY, rsp.getRetDesc());
//				return rsp;
//			}
//			
//			
//			/**************by liujie 过滤boss业务*********************/
//    		if (!StringUtils.isEmpty(FilterBossReq.getBalanceCode())) {
//                try {
//                    String tel = reqContent.getReqBody().getCellNum() ;
//                    String start = timeOperationService.getTime(tel, CacheNameSpace.TIME_OPERATION_NAMESPACE);
//                    
//                    if(!StringUtils.isEmpty(start)){
//                        double endTime =Double.parseDouble(request.getHeader("T")) ;
//                        double startT =Double.parseDouble(start)/1000 ;
//                        int outTime = Integer.parseInt(FilterBossReq.BOSS_TIME_OPERATION) ;
//                        
//                        if((endTime-startT)<=outTime){
//                           rsp.setRetCode(FilterBossReq.getBalanceCode());
//                           rsp.setRetDesc(FilterBossReq.getBalanceDesc());
//                           
//                           logger.info("[OPERATIONTIME]" + " busCode:" + BusinessCode.BALANCE_QUERY
//               					+ " trace:" + trace + " data=" + rsp.toString());
//                           
//                           return rsp;
//                        }
//                    }
//                }catch (Exception e) {}
//    		}
//    		/******************************************************/
//			
//			
//			//设置电话号码
//			map.put("cellNum", reqContent.getReqBody().getCellNum());
//			
//			/* 调用boss provider服务平台 */
//			String cellNum = reqContent.getReqBody().getCellNum();
//			//和一级BOSS交互
//			bossResult = balanceQueryService.getBalanceQuery(cellNum,trace);
//			String retCode = bossResult.getResultData().getRspCode();
//			//和一级BOSS交互成功的情况
//			if (ResponseEnum.BOSS_RESCODE_RESULT_SUCCESSS.equals(retCode)) {
//				BalanceQueryRes bqr = new BalanceQueryRes();
//				String balance = bossResult.getResultData().getBalance().getBalance();
//				
//				//设置话费余额信息
//				bqr.setBalance(balance);
//				
//				rsp.setRspBody(bqr);
//				rsp.setRetCode(ResponseCode.REQUEST_SUCCESS);
//				rsp.setRetDesc(bossResult.getResultData().getRspDesc());
//				logger.info("provider success" + bossResult);
//			}
//			//和一级BOSS交互失败的情况
//			else{
//				//根据一级BOSS返回的码去查错误描述信息
//				cm = codeCacheService.findDescByBusCodeAndInte(BusinessCode.BALANCE_QUERY,retCode);
//				rsp.setRetCode(cm.getCodeNumber());
//				rsp.setRetDesc(cm.getCodeDesc());
//				logger.error(trace, BusinessCode.BALANCE_QUERY, cm.getCodeDesc());
//			}
//		} catch (Exception e) {
//			cm = codeCacheService.findDescByCode(ResponseCode.SERVER_FAILURE);
//			rsp.setRetDesc(cm.getCodeDesc());
//			rsp.setRetCode(cm.getCodeNumber());
//			bossResult.setException(e);
//			//打印错误日志信息
//			logger.error(trace, BusinessCode.BALANCE_QUERY, cm.getCodeDesc());
//		}
//		
//		//打印返回给客户端的信息日志
//		logger.respPrint(Log.BOSS_SIGN, Log.CLIENT_RESPONSE, trace,BusinessCode.BALANCE_QUERY, System.currentTimeMillis()-startTime, JSON.toJSONString(rsp));
//		
//		//打印统计日志信息
//		LogCommonTool.statisticDtl(bossResult.getResultData().getRspCode(), map, loggerStatic, BusinessCode.BALANCE_QUERY, time);
//		
//		// 业务处理量明细日志
//		LogCommonTool.businessMonitorLog(rsp.getRetCode(), map,BusinessCode.BALANCE_QUERY, time);
//		
//		//打印返回给客户端的数据
//		logger.info("back client object" + rsp);
//		return rsp;
//	}
//}
