package com.bm.https;

import com.bm.https.scoket.SocketController;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@EnableScheduling
@SpringBootApplication
@ServletComponentScan
public class HttpsApplication  {

    public static void main(String[] args) {

        SpringApplication.run(HttpsApplication.class, args);
       /* ApplicationContext applicationContext =  SpringApplication.run(HttpsApplication.class, args);
        SocketController bean = applicationContext.getBean(SocketController .class);
        bean.start();*/  //TODO
        //在spring容器启动后，取到已经初始化的SocketServer，启动Socket服务
    }

   /* @Bean
    public Connector connector(){
        Connector connector=new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8087);
        connector.setSecure(false);
        connector.setRedirectPort(7080);
        return connector;
    }
*/


    // 配置静态资源文件路径
   /* @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        super.addResourceHandlers(registry);
    }*/



}
