package cn.nickid.biz.util;

import java.io.File;

import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;

public class StartUp {
	
	public static void main(String[] args) throws Exception {
		String appBase = "E:/workspace//leadeon-cmcc-biz/target/leadeon-cmcc-biz";
		Integer port = 80;
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(port);
		tomcat.setBaseDir(".");
		tomcat.getHost().setAppBase(appBase);
		String contextPath = "/";
		// Add AprLifecycleListener
		StandardServer server = (StandardServer) tomcat.getServer();
		AprLifecycleListener listener = new AprLifecycleListener();
		server.addLifecycleListener(listener);
		File contextFile = new File(appBase + "/WEB-INF/web.xml");
		tomcat.addWebapp(contextPath, appBase).setConfigFile(contextFile.toURL());
		tomcat.start();
		tomcat.getServer().await();
	}
}
