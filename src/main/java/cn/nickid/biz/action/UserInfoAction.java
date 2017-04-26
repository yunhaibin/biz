/**   
* @Title: UserInfoAction.java
* @Package cn.nickid.biz.action
* @Description: TODO(用一句话描述该文件做什么)
* @author yunhaibin nickid_qq_com
* @date 2017年3月23日 下午7:06:18
* @version V1.0
*/
package cn.nickid.biz.action;

import cn.nickid.dbapi.leancloud.service.UserInfoService;
import cn.nickid.dbapi.req.UserReq;
import cn.nickid.dbapi.res.UserRes;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.avos.avoscloud.AVException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

/** 
* @ClassName: UserInfoAction
* @Description: TODO(这里用一句话描述这个类的作用)
* @author yunhaibin nickid_qq_com
* @date 2017年3月23日 下午7:06:18
*  
*/
@Path("userInfo")
@Component
public class UserInfoAction {
	private static final Logger logger = LogManager.getLogger(UserInfoAction.class);
	
	@Autowired
	private UserInfoService userInfoService;
	
	@OPTIONS
	@POST
	@Path("getUserInfo")
	@Consumes()
	@Produces(ContentType.APPLICATION_JSON_UTF_8)
	public UserRes getUserInfo(@Context HttpServletRequest request, @Context HttpServletResponse response,
			UserReq userReq) {
		String userName = userReq.getName();
		logger.info("userName:" + userName);
		UserRes userRes = new UserRes();
		try {
			userRes = userInfoService.getUserInfoByUserName(userReq);
		} catch (AVException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userRes;
	}
}
