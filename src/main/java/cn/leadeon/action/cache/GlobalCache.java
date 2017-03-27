///*
// * 文 件 名:  ReqFilter.java
// * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2015-2-3,  All rights reserved  
// */
//package cn.leadeon.action.cache;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
//import java.io.UnsupportedEncodingException;
//import java.util.HashMap;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ReadListener;
//import javax.servlet.ServletException;
//import javax.servlet.ServletInputStream;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.WriteListener;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpServletResponseWrapper;
//
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.FileSystemXmlApplicationContext;
//
//import cn.leadeon.cache.service.CachePersistenceService;
//import cn.leadeon.comm.base.ResultData;
//import cn.leadeon.comm.businesscode.BusinessCode;
//import cn.leadeon.comm.log.Log;
//import cn.leadeon.common.ComVariable;
//import cn.leadeon.common.DateTimeTool;
//import cn.leadeon.common.ResponseCode;
//import cn.leadeon.common.ResponseHeaderUtil;
//import cn.leadeon.resbody.ResBody;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.TypeReference;
//
///**
// * <全局缓存处理>
// * 
// * @author 董飞
// * @version [2.0, 2015-2-3]
// * @since [中国移动手机营业厅/模块版本]
// */
//public class GlobalCache implements Filter {
//	private static final Log logger = new Log(GlobalCache.class);
//	private static final String busCode = "cache";
//	private static final String PROJECTNAME = "/leadeon-cmcc-biz";
//	private static final int NOCACHE = 0;
//	private static final String SUCCESS = "0";
//	private static final String ERROR = "1";
//	
//	private CachePersistenceService cachePersistence;
//
//	// 缓存命名空间 <key: uri, value: bussCode>
//	private static HashMap<String, Integer> nameSpaceMap = new HashMap<String, Integer>();
//	// 业务缓存关键字 <key: bussCode, value: keyList>
//	private static HashMap<Integer, String[]> cacheKeyMap = new HashMap<Integer, String[]>();
//
//	static {
//		// 存储uri及命名空间映射,此处存在表示对应业务需要做全局缓存
//		nameSpaceMap.put(PROJECTNAME + "/cacheAdmin/getCacheAdminInfo",
//				Integer.parseInt(BusinessCode.CACHE_ADMIN));
//		nameSpaceMap.put(PROJECTNAME + "/clientUpdate/getclientUpdate",
//				Integer.parseInt(BusinessCode.CLIENT_UPDATE));
//		nameSpaceMap.put(PROJECTNAME + "/searchKeyService/getSearchKey",
//				Integer.parseInt(BusinessCode.SEARCH_KEY_SERVICE));
//		nameSpaceMap.put(PROJECTNAME + "/searchKey/getKeyInfo",
//				Integer.parseInt(BusinessCode.SEARCH_KEY));
//		nameSpaceMap.put(PROJECTNAME + "/businessDetails/getDatail",
//				Integer.parseInt(BusinessCode.BUSINESS_DETAILS));
//		nameSpaceMap.put(PROJECTNAME + "/roamSearchService/getRoamSearch",
//				Integer.parseInt(BusinessCode.ROAM_SEARCH));
//		nameSpaceMap.put(PROJECTNAME + "/nationalFlag/getNationalFlag",
//				Integer.parseInt(BusinessCode.NATIONAL_FLAG));
//		nameSpaceMap.put(PROJECTNAME + "/usualSms/getUsualSmsInfo",
//				Integer.parseInt(BusinessCode.USUAL_SM));
//		nameSpaceMap.put(PROJECTNAME + "/appSquare/getAppSquareInfo",
//				Integer.parseInt(BusinessCode.APP_SQUARE));
//		nameSpaceMap.put(PROJECTNAME + "/usualCellNum/getUsualCellNum",
//				Integer.parseInt(BusinessCode.USUAL_CELL_NUM));
//		nameSpaceMap.put(PROJECTNAME + "/roamHotAreaSearch/getRoamInfo",
//				Integer.parseInt(BusinessCode.ROAM_HOT_AREA));
//		nameSpaceMap.put(
//				PROJECTNAME + "/terminalCSListService/getTerminalCSList",
//				Integer.parseInt(BusinessCode.TERMINAL_CS_LIST));
//		nameSpaceMap
//				.put(PROJECTNAME + "/terminalCSDetailService/getTerminalCSDetail",
//						Integer.parseInt(BusinessCode.TERMINAL_CS_DETAIL));
//		/*nameSpaceMap.put(PROJECTNAME + "/getPwd/getPwdInfo",
//				Integer.parseInt(BusinessCode.GET_PWD));*/
//		nameSpaceMap.put(
//				PROJECTNAME + "/documentSharing/getDocumentSharingByFuncId",
//				Integer.parseInt(BusinessCode.DOCUMENT_SHARING));
//		nameSpaceMap.put(PROJECTNAME + "/welcomeAdvService/getWelcomeAdv",
//				Integer.parseInt(BusinessCode.WELCOME_ADV));
//		nameSpaceMap.put(PROJECTNAME + "/list/getList",
//				Integer.parseInt(BusinessCode.LIST));
//		nameSpaceMap.put(PROJECTNAME + "/faq/findFaqList",
//				Integer.parseInt(BusinessCode.FAQ));
//		nameSpaceMap.put(PROJECTNAME + "/channelGuide/getChannelGuide",
//				Integer.parseInt(BusinessCode.CHANNEL_GUIDE));
//		nameSpaceMap.put(
//				PROJECTNAME + "/serviceChannelGuideDetail/getChannel",
//				Integer.parseInt(BusinessCode.SERVICE_CHANNEL_GUIDE_DETAIL));
//		nameSpaceMap.put(
//				PROJECTNAME + "/marketingDetail/getMarketingDetail",
//				Integer.parseInt(BusinessCode.MARKINGDETAIL));
//		
//		nameSpaceMap.put(PROJECTNAME + "/homeSale/getsaleAdver",
//				Integer.parseInt(BusinessCode.HOME_SALE));
//		
//		//通栏广告
//		nameSpaceMap.put(PROJECTNAME + "/leaderBoard/getLeaderBoard",
//				Integer.parseInt(BusinessCode.LEADERBOARD));
//		
//		// 分省图标
//		nameSpaceMap.put(PROJECTNAME + "/proviceIconConfigure/getProvinceIconConfigure",
//						Integer.parseInt(BusinessCode.PROVICE_ICON_CONFIGURE));
//				
//		// 设置缓存key
//		/**********通栏广告**********/
//		cacheKeyMap.put(Integer.parseInt(BusinessCode.LEADERBOARD),
//				new String[] {"sp","provinceCode","cityCode","adverType"});
//		
//		cacheKeyMap.put(Integer.parseInt(BusinessCode.CACHE_ADMIN),
//				new String[] { PROJECTNAME + "/cacheAdmin/getCacheAdminInfo" });
//		cacheKeyMap.put(Integer.parseInt(BusinessCode.SEARCH_KEY_SERVICE),
//				new String[] { PROJECTNAME + "/searchKeyService/getSearchKey" });
//		/*********版本检测**********/
//		cacheKeyMap.put(Integer.parseInt(BusinessCode.CLIENT_UPDATE),
//				new String[] { "cv", "st" });
//		/*********国漫搜索**********/
//		cacheKeyMap.put(Integer.parseInt(BusinessCode.ROAM_SEARCH),
//		        new String[] { "searchKey" });
//		/*********国漫名称与国旗**********/
//		cacheKeyMap.put(Integer.parseInt(BusinessCode.NATIONAL_FLAG),
//		        new String[] { PROJECTNAME + "/nationalFlag/getNationalFlag" });
//		cacheKeyMap.put(Integer.parseInt(BusinessCode.FAQ), new String[]{PROJECTNAME + "/faq/findFaqList"});
//		
//		/*********常用号码查询**********/
//		cacheKeyMap.put(Integer.parseInt(BusinessCode.USUAL_CELL_NUM), new String[]{"page","unit"});
//		
//		/*********终端售后点列表查询**********/
//		cacheKeyMap.put(Integer.parseInt(BusinessCode.TERMINAL_CS_LIST), new String[]{"cityCode","type","page","unit"});
//		
//		/*********终端售后点详细内容查询**********/
//		cacheKeyMap.put(Integer.parseInt(BusinessCode.TERMINAL_CS_DETAIL), new String[]{"svcId","type"});
//		
//		/*********办理业务详细内容查询**********/
//		cacheKeyMap.put(Integer.parseInt(BusinessCode.BUSINESS_DETAILS), new String[]{"provinceCode", "prodId"});
//		
//		/*********分享文案**********/
//		cacheKeyMap.put(Integer.parseInt(BusinessCode.DOCUMENT_SHARING), new String[]{"funCode"});
//		
//		/*********首页营销**********/
//		cacheKeyMap.put(Integer.parseInt(BusinessCode.HOME_SALE), new String[]{"sp","provinceCode","cityCode","adverType"});
//		
//		/********详单类型列表**********/
//		cacheKeyMap.put(Integer.parseInt(BusinessCode.LIST), new String[]{PROJECTNAME + "/list/getList"});
//		
//		/********服务渠道指南详情**********/
//		cacheKeyMap.put(Integer.parseInt(BusinessCode.SERVICE_CHANNEL_GUIDE_DETAIL), new String[]{"svcChnlCode"});
//		
//		/********欢迎页广告**********/
//		cacheKeyMap.put(Integer.parseInt(BusinessCode.WELCOME_ADV), new String[]{"sp"});
//		
//		/********广告详情页**********/
//		cacheKeyMap.put(Integer.parseInt(BusinessCode.MARKINGDETAIL), new String[]{"markId"});
//		
//		/*搜索详细内容查询缓存key*/
//		cacheKeyMap.put(Integer.parseInt(BusinessCode.SEARCH_KEY),
//				new String[] { "inforId", "searchType" });
//		/*常用短信缓存key*/
//		cacheKeyMap.put(Integer.parseInt(BusinessCode.USUAL_SM),
//				new String[] { "provinceCode", "cityCode","page","unit" });
//		/*应用广场缓存key*/
//		cacheKeyMap.put(Integer.parseInt(BusinessCode.APP_SQUARE),
//				new String[] { "unit","scnType","page" });
//		/*国际漫游热点查询key*/
//		cacheKeyMap.put(Integer.parseInt(BusinessCode.ROAM_HOT_AREA),
//				new String[] {"page","unit" });
//		/*服务渠道指南列表key*/
//		cacheKeyMap.put(Integer.parseInt(BusinessCode.CHANNEL_GUIDE),
//				new String[] {PROJECTNAME +  "/channelGuide/getChannelGuide" });
//		/*分省图标key*/
//		cacheKeyMap.put(Integer.parseInt(BusinessCode.PROVICE_ICON_CONFIGURE),
//				new String[] {"provinceCode","cityCode","iconImgType","enterType"});
//	}
//
//	/**
//	 * 重载方法
//	 * 
//	 * @param request
//	 * @param response
//	 * @param chain
//	 * @throws IOException
//	 * @throws ServletException
//	 */
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response,
//			FilterChain chain) throws IOException, ServletException {
//		HttpServletRequest req = (HttpServletRequest) request;
//		HttpServletResponse rsp = (HttpServletResponse) response;
//
//		Long startTime = System.currentTimeMillis();
//		/* 获取trace */
//		String trace = (String) req.getHeader("Trace");
//
//		RequestWrapper reqWrapper = new RequestWrapper(req);
//		String reqJson = reqWrapper.getReqBody();
//
//		String uri = req.getRequestURI();
//
//		// 是否需要从业务接口获取响应数据标识
//		boolean getFromBuss = true;
//
//		// 如果对应业务属于一级缓存，尝试从缓存获取数据
//		int nameSpace = getNameSpace(uri);
//		String cacheKey = getCacheKey(nameSpace, uri, reqJson);
//		String rspData = "";
//		if ((nameSpace != NOCACHE) && !(StringUtils.isEmpty(cacheKey))) {
//			rspData = getRespData(trace, nameSpace, cacheKey);
//
//			// 从缓存成功获取数据，打印及返回给客户端
//			if (!StringUtils.isEmpty(rspData)) {
//				getFromBuss = false;
//				// 客户端请求参数打印
//				logger.reqPrint(Log.DB_SIGN, Log.CLIENT_REQUEST, trace,
//						String.valueOf(nameSpace), reqJson);
//
//				writeResponse(trace, rsp, rspData);
//				
//				// 响应客户端参数打印
//				logger.respPrint(Log.DB_SIGN, Log.CLIENT_RESPONSE,
//						trace, String.valueOf(nameSpace), System.currentTimeMillis() - startTime,
//						rspData);
//			}
//		}
//
//		if (getFromBuss) {
//			// 如果缓存无数据，调用接口查询结果
//			WapperedResponse rspWrapper = new WapperedResponse(rsp);
//			chain.doFilter(reqWrapper, rspWrapper);
//			rspData = rspWrapper.getResponseData();
//			
//			if(rspData != null){
//				@SuppressWarnings("rawtypes")
//				ResBody resObj = JSON.parseObject(rspData, ResBody.class);
//				
//				if(StringUtils.equals(resObj.getRetCode(),
//						ResponseCode.REQUEST_SUCCESS)){
//					rspWrapper.setHeader("RspCode", SUCCESS);
//					
//					if((nameSpace != NOCACHE) && !StringUtils.isEmpty(cacheKey)){
//						setRspData(trace, nameSpace, cacheKey, rspData);
//					}
//				}else{
//					rspWrapper.setHeader("RspCode", ERROR);
//				}
//			}else{
//				ResBody<Object> resObj = new ResBody<Object>();
//				resObj.setRetCode(ResponseCode.DEFALUT_ERROR);
//				resObj.setRetDesc(ComVariable.RSP_DESC_DEFAULT_ERR);
//				rspData = JSON.toJSONString(resObj);
//			}
//			
//			
//			writeResponse(trace, rsp, rspData);
//		}
//	}
//
//	private String getRespData(String trace, int nameSpace, String key) {
//		String respData = "";
//		try {
//			@SuppressWarnings("unchecked")
//			ResultData<String> rspData = cachePersistence.get(nameSpace, key);
//			if (!StringUtils.isEmpty(rspData.getResultData())) {
//				respData = rspData.getResultData()
//						.replaceAll(">\\s{1,}<", "><");
//			}
//		} catch (Exception e) {
//			logger.error(trace, e.getMessage());
//			return respData;
//		}
//
//		return respData;
//	}
//
//	private void setRspData(String trace, int nameSpace, String key,
//			String value) {
//		try {
//			cachePersistence.set(nameSpace, key, value, DateTimeTool.DAY_SEC_NUMBER);
//		} catch (Exception e) {
//			logger.error(trace, e);
//		}
//	}
//
//	private int getNameSpace(String uri) {
//		if (!uri.contains(PROJECTNAME)) {
//			uri = PROJECTNAME + uri;
//		}
//
//		int nameSpace = NOCACHE;
//		if (nameSpaceMap.containsKey(uri)) {
//			nameSpace = nameSpaceMap.get(uri);
//		}
//
//		return nameSpace;
//	}
//
//	@Override
//	public void destroy() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void init(FilterConfig arg0) throws ServletException {
//		ApplicationContext ac = new FileSystemXmlApplicationContext(
//				"classpath:filterContext.xml");
//		cachePersistence = (CachePersistenceService) ac
//				.getBean("cachePersistence");
//	}
//
//	class RequestWrapper extends HttpServletRequestWrapper {
//		private String reqBody;
//		private HttpServletRequest request;
//
//		/**
//		 * 设置 reqBody
//		 * 
//		 * @param 对reqBody进行赋值
//		 */
//		public void setReqBody(String reqBody) {
//			this.reqBody = reqBody;
//		}
//
//		public RequestWrapper(HttpServletRequest request) {
//			super(request);
//			this.request = request;
//		}
//
//		public String getReqBody() {
//			ServletInputStream stream = null;
//			try {
//				stream = request.getInputStream();
//				reqBody = IOUtils.toString(stream, ComVariable.ENCODING_UTF8).replaceAll(
//						">\\s{1,}<", "><");
//			} catch (IOException e) {
//				logger.error("", e);
//			} finally {
//				if (stream != null) {
//					try {
//						stream.close();
//					} catch (IOException e) {
//						logger.error("", e);
//					}
//				}
//			}
//
//			return reqBody;
//		}
//
//		public ServletInputStream getInputStream() {
//			byte[] buffer = null;
//			try {
//				buffer = reqBody.getBytes(ComVariable.ENCODING_UTF8);
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//			final ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
//
//			ServletInputStream newStream = new ServletInputStream() {
//
//				@Override
//				public int read() throws IOException {
//					return bais.read();
//				}
//
//				@Override
//				public boolean isFinished() {
//					// TODO Auto-generated method stub
//					return false;
//				}
//
//				@Override
//				public boolean isReady() {
//					// TODO Auto-generated method stub
//					return false;
//				}
//
//				@Override
//				public void setReadListener(ReadListener listener) {
//					// TODO Auto-generated method stub
//
//				}
//			};
//			return newStream;
//		}
//	}
//
//	class WapperedResponse extends HttpServletResponseWrapper {
//		private ByteArrayOutputStream buffer = null;
//		private ServletOutputStream out = null;
//		private PrintWriter writer = null;
//
//		public WapperedResponse(HttpServletResponse resp) throws IOException {
//			super(resp);
//			// 真正存储数据的流
//			buffer = new ByteArrayOutputStream();
//			out = new WapperedOutputStream(buffer);
//			writer = new PrintWriter(new OutputStreamWriter(buffer,
//					this.getCharacterEncoding()));
//		}
//
//		// 重载父类获取outputstream的方法
//		@Override
//		public ServletOutputStream getOutputStream() throws IOException {
//			return out;
//		}
//
//		// 重载父类获取writer的方法
//		@Override
//		public PrintWriter getWriter() throws UnsupportedEncodingException {
//			return writer;
//		}
//
//		// 重载父类获取flushBuffer的方法
//		@Override
//		public void flushBuffer() throws IOException {
//			if (out != null) {
//				out.flush();
//			}
//			if (writer != null) {
//				writer.flush();
//			}
//		}
//
//		@Override
//		public void reset() {
//			buffer.reset();
//		}
//
//		public String getResponseData() throws IOException {
//			// 将out、writer中的数据强制输出到WapperedResponse的buffer里面，否则取不到数据
//			flushBuffer();
//			return buffer.toString(ComVariable.ENCODING_UTF8);
//		}
//
//		// 内部类，对ServletOutputStream进行包装
//		private class WapperedOutputStream extends ServletOutputStream {
//			private ByteArrayOutputStream bos = null;
//
//			public WapperedOutputStream(ByteArrayOutputStream stream)
//					throws IOException {
//				bos = stream;
//			}
//
//			@Override
//			public void write(int b) throws IOException {
//				bos.write(b);
//			}
//
//			@Override
//			public boolean isReady() {
//				// TODO Auto-generated method stub
//				return false;
//			}
//
//			@Override
//			public void setWriteListener(WriteListener listener) {
//				// TODO Auto-generated method stub
//
//			}
//		}
//	}
//
//	// 获取缓存key
//	private String getCacheKey(int nameSpace, String uri, String reqJson){
//		StringBuffer cacheKey = new StringBuffer();
//		if(nameSpace != NOCACHE){
//			String[] keys = cacheKeyMap.get(nameSpace);
//			
//			// 解析请求报文
//			HashMap<String, Object> reqHeadMap = JSON.parseObject(reqJson,
//					new TypeReference<HashMap<String, Object>>() {
//					});
//			
//			Object reqBody = reqHeadMap.get("reqBody");
//			HashMap<String, Object> reqBodyMap = null;
//			if(reqBody != null){
//				reqBodyMap = JSON.parseObject(reqBody.toString(),
//						new TypeReference<HashMap<String, Object>>() {
//						});
//			}
//			
//			
//			Object keyValue;
//			for(String key: keys){
//				keyValue = reqHeadMap.get(key);
//				if(keyValue != null){
//					cacheKey.append("_").append(keyValue.toString());
//				}else if((reqBodyMap != null) && ((keyValue=reqBodyMap.get(key)) != null)){
//					cacheKey.append("_").append(keyValue.toString());
//				}else if(StringUtils.equals(key, uri) || StringUtils.equals(key, PROJECTNAME + uri)){
//					cacheKey.append("_").append(key);
//				}else{
//					logger.error("Error cache key: key=" + key + " reqJson=" + reqJson);
//					return "";
//				}
//			}
//		}
//		
//		return cacheKey.toString();
//	}
//	
//	private void writeResponse(String trace, HttpServletResponse rsp, String rspData){
//		ServletOutputStream out = null;
//		rsp.setHeader("Content-Type", ComVariable.CONTENTTYPE_JSON);
//		rsp.setHeader("DataEncoding", ComVariable.ENCODING_UTF8);
//		ResponseHeaderUtil.setH5CorsHeader(rsp);
//		
//		try {
//			out = rsp.getOutputStream();
//			out.write(rspData.getBytes(ComVariable.ENCODING_UTF8));
//			out.flush();
//			out.close();
//		} catch (IOException e) {
//			logger.error(trace, e);
//		} finally {
//			try {
//				if (out != null) {
//					out.close();
//				}
//			} catch (IOException e) {
//				logger.error(trace, e);
//			}
//		}
//	}
//}