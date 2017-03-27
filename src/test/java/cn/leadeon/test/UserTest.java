//package cn.leadeon.test;
//
//import org.junit.Test;
//import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import com.ice.utils.SignUtils;
//
//
//public class UserTest extends AbstractJUnit4SpringContextTests {
//	
//	
//	public static final String SERVER_URL = "http://localhost:8080/";
//
//
//	//@Test
//	public void login() {
//
//		RestTemplate restTemplate = new RestTemplate();
//		MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
//
//		form.add("method", "user.login");
//		//开发者账号
//		form.add("appKey", "10000");
//		form.add("v", "1.0");
//		form.add("format", "json");
//		//时间戳
//		form.add("timestamp", String.valueOf(System.currentTimeMillis()));
//		form.add("accountName", "8888888");
//		form.add("password", "3D4F2BF07DC1BE38B20CD6E46949A1071F9D0E3D");
//		
//		//用开发者密钥签名SHA1
//		String sign = SignUtils.sign(form.toSingleValueMap(),"F661DC8AC32D448FAB31C68787497A64");
//		
//		form.add("sign", sign);
//		
//		String response = restTemplate.postForObject(SERVER_URL, form, String.class);
//		
//		System.out.println("response:\n" + response);
//
//	}
//
//	@Test
//	public void info() {
// 
//		RestTemplate restTemplate = new RestTemplate();
//		MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
//
//		form.add("method", "user.info");
//		//开发者账号
//		form.add("appKey", "10000");
//		form.add("v", "1.0");
//		//时间戳
//		form.add("timestamp", String.valueOf(System.currentTimeMillis()));
//		//登录方法返回的sessionId
//		form.add("sessionId", "91F809F84C6E41B9BF58ED19653D81FF");
//		
//		
//		//用开发者密钥签名SHA1
//		String sign = SignUtils.sign(form.toSingleValueMap(),"3D4F2BF07DC1BE38B20CD6E46949A1071F9D0E3D");
//		
//		form.add("sign", sign);
//		
//		String response = restTemplate.postForObject(SERVER_URL, form, String.class);
//		
//		System.out.println("response:\n" + response);
//
//	}
//}
