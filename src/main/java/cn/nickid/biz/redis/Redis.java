/*
 * 文 件 名:  Redis.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2015-10-14,  All rights reserved  
 */
package cn.nickid.biz.redis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * <Redis基础服务>
 * <功能详细描述>
 * 
 * @author  yunhaibin
 * @version  [版本号, 2015-10-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class Redis {
	/**
	 * logger 日志接口
	 */
	private static final Logger logger = LogManager.getLogger(Redis.class);
	
	@Autowired
	private JedisPool jedisPool;
	
	/** 
	 * <添加缓存>
	 * <功能详细描述>
	 * @param key
	 * @param value
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public boolean set(String key, String value) {
		Jedis jedis = null;
		boolean result = false;
		try {
			jedis = jedisPool.getResource();
			result = "OK".equals(jedis.set(key, value)) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[set(key:" + new String(key) + ")] jedis Exception:" + e.getMessage());
		} finally {
			jedis.close();
		}
		return result;
	}
	
	public String get(String key) {
		Jedis jedis = null;
		String value = null;
		try {
			jedis = jedisPool.getResource();
			value = jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[getTicketPool(key:" + new String(key) + ")] jedis Exception:" + e.getMessage());
		} finally {
			jedis.close();
		}
		return value;
	}
}
