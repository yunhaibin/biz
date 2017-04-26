package cn.nickid.tomcat;

import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

/**
 * Tomcat Embed
 *
 * @author Jerry
 * @create 2017-04-20 18:39
 **/
public class TomcatEmbed {
    static final int port = 8080;
    static final String docBase = "E:/tmp/tomcat";
    static final String appBase = "E:/Github/biz/target/biz";
    static final String contextPath = "/biz";
    static final String webxml = "/WEB-INF/web.xml";

    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.setBaseDir(docBase);
        tomcat.getHost().setAutoDeploy(false);
        tomcat.getHost().setAppBase(appBase);

        // Add AprLifecycleListener
        StandardServer server = (StandardServer) tomcat.getServer();
        AprLifecycleListener listener = new AprLifecycleListener();
        server.addLifecycleListener(listener);
        File contextFile = new File(appBase + webxml);
        tomcat.addWebapp(contextPath, appBase).setConfigFile(contextFile.toURL());
        tomcat.start();
        tomcat.getServer().await();
    }
}
