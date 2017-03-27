//package cn.nickid.biz.action;
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
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
//import com.alibaba.fastjson.JSON;
//import com.google.common.base.Strings;
//
//import cn.nickid.biz.redis.Redis;
//import cn.nickid.biz.req.UserInfoReq;
//import cn.nickid.biz.res.UserInfoRes;
//import cn.nickid.biz.util.H5CORSUtil;
//import cn.nickid.dbapi.req.UserReq;
//import cn.nickid.dbapi.res.UserRes;
//import cn.nickid.dbapi.service.UserService;
//
//@Path("user")
//@Component
//public class UserAction {
//	private static final Logger logger = LogManager.getLogger(UserAction.class);
//	
//	@Autowired
//	private UserService userService;
//	
//	@Autowired
//	private Redis redis;
//	
//	@OPTIONS
//	@POST
//	@Path("getUserInfo")
//	@Consumes()
//	@Produces(ContentType.APPLICATION_JSON_UTF_8)
//	public UserInfoRes getUserInfo(@Context HttpServletRequest request, @Context HttpServletResponse response,
//			String query) {
//		// 允许Ajax跨域请求
//		H5CORSUtil.setH5CorsHeader(response);
//		System.out.println(query);
//		UserInfoReq userInfoReq = JSON.parseObject(query, UserInfoReq.class);
//		
//		UserInfoRes userInfoRes = new UserInfoRes();
//		// 读取缓存
//		String username = redis.get(String.valueOf(userInfoReq.getId()));
//		
//		if(!Strings.isNullOrEmpty(username)) {
//			userInfoRes.setName(username);
//		}else {
//			UserReq userReq = new UserReq();
//			userReq.setId(userInfoReq.getId());
//			userReq.setName(userInfoReq.getName());
//			UserRes userRes = userService.getUser(userReq);
//			userInfoRes.setId(userRes.getId());
//			userInfoRes.setName(userRes.getName());
//			userInfoRes.setAge(userRes.getAge());
//			logger.info("User info id:" + userRes.getId() + ", name:" + userRes.getName() + ", age:" + userRes.getAge() + ".");
//			
//			// 数据库读取成功回写缓存
//			redis.set(String.valueOf(userInfoReq.getId()), userRes.getName());
//		}
//		
//		return userInfoRes;
//	}
//	
//}
