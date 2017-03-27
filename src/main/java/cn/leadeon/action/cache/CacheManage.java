///*
// * 文 件 名:  CacheManage.java
// * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2015-2-27,  All rights reserved  
// */
//package cn.leadeon.action.cache;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.OPTIONS;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.Context;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import cn.leadeon.cache.service.CachePersistenceService;
//import cn.leadeon.comm.base.ResultData;
//import cn.leadeon.comm.log.Log;
//import cn.leadeon.common.ComVariable;
//import cn.leadeon.common.ResponseCode;
//import cn.leadeon.reqbody.NameSpace;
//import cn.leadeon.reqbody.NameSpaceCleanReq;
//import cn.leadeon.resbody.ResBody;
//
//import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
//
///**
// * <缓存操作>
// * 
// * @author 董飞
// * @version [2.0, 2015-2-27]
// * @since [中国移动手机营业厅/模块版本]
// */
//@Path("cacheManage")
//@Component
//public class CacheManage {
//	private static final Log logger = new Log(CacheManage.class);
//	@Autowired
//	private CachePersistenceService cachePersistence;
//
//	@OPTIONS
//	@POST
//	@Path("delNameSpace")
//	@Consumes()
//	@Produces(ContentType.APPLICATION_JSON_UTF_8)
//	@SuppressWarnings("rawtypes")
//	public ResBody delNameSpace(@Context HttpServletRequest request,
//			@Context HttpServletResponse response, NameSpaceCleanReq reqObj) {
//		ResBody resObj = new ResBody();
//		try{
//			/*// 解析请求报文
//			HashMap<String, String> reqHeadMap = JSON.parseObject(reqJson,
//					new TypeReference<HashMap<String, String>>() {
//					});
//			*/
//			
//			for(NameSpace nameSpace : reqObj.getNameSpaces()){
//				ResultData delResult = cachePersistence.delNameSpace(nameSpace.getNameSpace());
//				final String CACHESUCESS = "_000";
//				if(!delResult.getResultCode().getCode().equals(CACHESUCESS)){
//					logger.error("clean nameSpace error! ErrorInfo:" + delResult.getDesc());
//					resObj.setRetCode(ResponseCode.CACHE_FAILURE);
//					resObj.setRetDesc(delResult.getDesc());
//					return resObj;
//				}
//			}
//			
//			resObj.setRetCode(ResponseCode.REQUEST_SUCCESS);
//			resObj.setRetDesc(ComVariable.RSP_DESC_SUCCESS);
//		}catch(Exception e){
//			logger.error("clean nameSpace error! " + e);
//			resObj.setRetCode(ResponseCode.CACHE_FAILURE);
//			resObj.setRetDesc("clean nameSpace error!");
//		}
//		
//		return resObj;
//	}
//}
