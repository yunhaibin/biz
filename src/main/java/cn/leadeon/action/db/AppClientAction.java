///*
// * 文 件 名:  AppClientAction.java
// * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014-12-16,  All rights reserved  
// */
//package cn.leadeon.action.db;
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
//import cn.leadeon.comm.base.ResultData;
//import cn.leadeon.comm.log.Log;
//import cn.leadeon.common.BusinessCode;
//import cn.leadeon.common.ComVariable;
//import cn.leadeon.common.DateTimeTool;
//import cn.leadeon.common.LogCommonTool;
//import cn.leadeon.common.ResponseCode;
//import cn.leadeon.common.ResponseEnum;
//import cn.leadeon.common.ServerConfig;
//import cn.leadeon.common.ValidationTool;
//import cn.leadeon.db.constant.DBResCode;
//import cn.leadeon.db.parampojo.AppClientParam;
//import cn.leadeon.db.resultpojo.CellNumLocationDb;
//import cn.leadeon.db.resultpojo.CodeMappingDb;
//import cn.leadeon.db.service.AppClientService;
//import cn.leadeon.reqbody.AppClientReq;
//import cn.leadeon.reqbody.ReqBody;
//import cn.leadeon.resbody.ResBody;
//import cn.leadeon.service.CodeCacheService;
//
//import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
//import com.alibaba.fastjson.JSON;
//
///**
// * 生成cid和uid的映射关系和月末推送关系
// * 30028
// * @author sunpeng
// * @version [版本号, 2014-12-16]
// * @version [2.0, 2014年12月18日 下午1:13:49]
// * @since [中国移动手机营业厅BSS系统/pushServer业务模块]
// */
//@Path("appClient")
//@Component
//public class AppClientAction {
//    
//    private static Log loggerSysOut = new Log(AppClientAction.class);
//    
//    @Autowired
//    private AppClientService appClientService;
//    
//    /********动态提示对象********/
//    private CodeMappingDb resultDb = null;
//    
//	/**
//	 * 业务返回码转换服务
//	 */
//	@Autowired
//	private CodeCacheService codeService;
//	
//    @POST
//    @Path("getAppClient")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(ContentType.APPLICATION_JSON_UTF_8)
//    public ResBody<?> getAppClient(@Context HttpServletRequest request,ReqBody<AppClientReq> reqContent) {
//        long start = System.currentTimeMillis();
//        ResBody<?> appClient = new ResBody<>();
//        CodeMappingDb codeDB = null;
//        //当前时间
//  		String time = DateTimeTool.getCurrentDate(DateTimeTool.LONGFORMAT);
//        String  sessionId = request.getHeader("Trace");//获取流水号并打印
//        Map<String,String> map = this.getMap(request, reqContent, sessionId);
//        try {
//            // 客户端请求参数打印
//            loggerSysOut.reqPrint(Log.DB_SIGN, Log.CLIENT_REQUEST, sessionId,BusinessCode.APP_CLIENT, JSON.toJSONString(reqContent));
//    		String plat = this.getSysType(reqContent.getSt()) ;
//    		reqContent.setSt(plat);
//            // 参数验证 --------开始
//            appClient = checkRequest(reqContent,sessionId);
//            if (!ResponseEnum.BOSS_RESCODE_SUCCESS.equals(appClient.getRetCode())) {
//            	// 业务处理量明细日志
//				LogCommonTool.businessMonitorLog(appClient.getRetCode(), map,BusinessCode.APP_CLIENT, time);
//				// 响应客户端参数打印
//                loggerSysOut.respPrint(Log.DB_SIGN, Log.CLIENT_RESPONSE,sessionId,BusinessCode.APP_CLIENT, System.currentTimeMillis()-start, JSON.toJSONString(appClient));
//                return appClient;
//            }
//            // 参数验证  -------结束
//            AppClientParam param = getParamBean(reqContent);
//            // 处理逻辑
//            ResultData<?> list = appClientService.getAppClient(param,sessionId);
//            // 处理完逻辑，根据返回码设置返回结果
//            if (list.getResultCode().equals(DBResCode.BOSS_RESCODE_SUCCESS)) {
//                appClient.setRetCode(ResponseCode.REQUEST_SUCCESS);
//                appClient.setRetDesc("SUCCESS");
//            }else {
//            	codeDB = codeService.findDescByCode(ResponseCode.SERVER_FAILURE);
//                appClient.setRetCode(codeDB.getCodeNumber());
//                appClient.setRetDesc(codeDB.getCodeDesc());
//            }
//        }catch (Exception e) { //处理异常
//            codeDB = codeService.findDescByCode(ResponseCode.SERVER_FAILURE);
//            appClient.setRetCode(codeDB.getCodeNumber());
//            appClient.setRetDesc(codeDB.getCodeDesc());
//            loggerSysOut.error(sessionId, BusinessCode.APP_CLIENT, e);
//        } finally {
//            // 业务处理量明细日志
//            LogCommonTool.businessMonitorLog(appClient.getRetCode(), map,BusinessCode.APP_CLIENT, time);
//            // 响应客户端参数打印
//            loggerSysOut.respPrint(Log.DB_SIGN, Log.CLIENT_RESPONSE, sessionId,BusinessCode.APP_CLIENT, System.currentTimeMillis()-start, JSON.toJSONString(appClient));
//        }
//        return appClient;
//    }
//    
//    private Map<String, String> getMap(HttpServletRequest request,ReqBody<AppClientReq> reqContent,String sessionId){
//        Map<String,String> map = new HashMap<String,String>();
//        // 来源地址
//        map.put("SOURCEVAR", request.getRemoteAddr());
//        // 目标地址
//        map.put("DESTVAR", request.getLocalAddr());
//        // 客户端版本号
//        String clientVersion = reqContent.getCv();
//        map.put("CLIENTVERSION", clientVersion);
//        // 系统类型
//        String sysType = reqContent.getSt();
//        map.put("SYSTYPE", sysType);
//        // 会话id
//        map.put("SSETIONIDVAR", sessionId);
//        return map ;
//    }
//    
//    /**
//     * 校验参数
//     * @param reqcontent 请求报文实体
//     * @return 响应结果
//     */
//    public ResBody<?> checkRequest(ReqBody<AppClientReq> reqcontent,String serialNumber) {
//        ResBody<?> resCon = new ResBody<>();
//        String telNo = reqcontent.getReqBody().getCellNum() ;
//        String brandId = reqcontent.getReqBody().getBrandId() ;
//        String platForm = reqcontent.getSt() ;
//        CodeMappingDb codeDB = null ;
//        // 客户端参数校验
//        codeDB = ValidationTool.validationField(reqcontent.getReqBody());
//        if (!ResponseCode.REQUEST_SUCCESS.equals(codeDB.getCodeNumber())) {// 如果请求参数正确，进行业务处理
//            resCon.setRetCode(codeDB.getCodeNumber());
//            resCon.setRetDesc(codeDB.getCodeDesc());
//            return resCon;
//        }
//		if(!ComVariable.PHONE.equals(telNo)){
//	        ResultData<CellNumLocationDb> local = appClientService.getLocal(telNo,serialNumber);
//	        /*运营商校验*/
//            if (StringUtils.isEmpty(local.getResultData().getProvCode())) {
//                resultDb = codeService.findDescByCode(ResponseCode.PHONENUMBER_ERROR);
//                //与服务交互失败
//                resCon.setRetDesc(resultDb.getCodeDesc());
//                //设置失败返回码
//                resCon.setRetCode(resultDb.getCodeNumber());
//                return resCon;
//            }
//		}
//        /* monthSwitch校验 */
//        if (!"0".equals(reqcontent.getReqBody().getMonthSwitch())&&!"1".equals(reqcontent.getReqBody().getMonthSwitch())) {
//            resCon.setRetCode(ResponseCode.REQUEST_ERROR);
//            resCon.setRetDesc("FiledName: monthSwitch is not illeagl!");
//            return resCon;
//        }
//        /* nightSwitch校验 */
//        if (!"0".equals(reqcontent.getReqBody().getNightSwitch())&&!"1".equals(reqcontent.getReqBody().getNightSwitch())) {
//            resCon.setRetCode(ResponseCode.REQUEST_ERROR);
//            resCon.setRetDesc("FiledName: nightSwitch is not illeagl!");
//            return resCon;
//        }
//        /* pushSwitch校验 */
//        if (!"0".equals(reqcontent.getReqBody().getPushSwitch())&&!"1".equals(reqcontent.getReqBody().getPushSwitch())) {
//            resCon.setRetCode(ResponseCode.REQUEST_ERROR);
//            resCon.setRetDesc("FiledName: pushSwitch is not illeagl!");
//            return resCon;
//        }
//        if(brandId!=null&&!"".equals(brandId)){
//            /* brandId校验 */
//            if (!"01".equals(brandId)&&!"02".equals(brandId)&&!"03".equals(brandId)&&!"09".equals(brandId)) {
//                resCon.setRetCode(ResponseCode.REQUEST_ERROR);
//                resCon.setRetDesc("FiledName: brandId is not illeagl!");
//                return resCon;
//            }
//        }
//        if(platForm==null || "".equals(platForm)){
//            resCon.setRetCode(ResponseCode.REQUEST_ERROR);
//            resCon.setRetDesc("FiledName: head st is not null!");
//            return resCon;
//        }
//        resCon.setRetCode(ResponseEnum.BOSS_RESCODE_SUCCESS);
//        resCon.setRetDesc("");
//        return resCon;
//    }
//    
//    /* 获得参数 */
//    public AppClientParam getParamBean(ReqBody<AppClientReq> reqcontent) {
//        AppClientParam param = new AppClientParam();
//        String sysType = reqcontent.getSt();
//        param.setBrandId(reqcontent.getReqBody().getBrandId());
//        param.setCellNum(reqcontent.getReqBody().getCellNum());
//        param.setCid(reqcontent.getReqBody().getCid());
//        param.setCityCode(reqcontent.getReqBody().getCityCode());
//        param.setClientVer(reqcontent.getCv());
//        param.setMbosvesrion(reqcontent.getSv());
//        param.setMonthSwitch(reqcontent.getReqBody().getMonthSwitch());
//        param.setNightSwitch(reqcontent.getReqBody().getNightSwitch());
//        param.setProvinceCode(reqcontent.getReqBody().getProvinceCode());
//        param.setPushSwitch(reqcontent.getReqBody().getPushSwitch());
//        param.setSysType(sysType);
//        param.setDeviceId(reqcontent.getReqBody().getDeviceId());
//        return param;
//    }
//    
//    //获得系统类型对应的参数  1：Android  2：iPhone  3：iPad  4：wp
//    private String getSysType(String sysType){
//        
//        try {
//            if(ServerConfig.prop.containsKey(sysType)){
//                return ServerConfig.prop.getProperty(sysType) ;
//            }
//        }catch (Exception e) {
//            return null ;
//        }
//        return null ;
//    }
//    
//}
