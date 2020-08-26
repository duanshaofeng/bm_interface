package com.bm.https.pointcut;

import com.alibaba.fastjson.JSONObject;
import com.bm.https.untils.ConnectUtil;
import com.bm.https.untils.IpUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description:服务切面
 * @Author: dsf
 * @datetime:2019/8/24
 *
 */

@Aspect
@Component
public class ServerAspectTest {

    @Autowired
    private ConnectUtil connectUtil;

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    private final Logger logger = LoggerFactory.getLogger(ServerAspectTest.class);
    @Around("execution(public * com.bm.https.controller..*.*(..))")
    public Object controllerLog(ProceedingJoinPoint pdj) throws Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, Object> mess = new HashMap<>();
        logger.info("驻马店交换平台接口资源请求===========jdbcTemplate"+jdbcTemplate);
        long startTime = System.currentTimeMillis();//开始时间
        logger.info("驻马店交换平台接口资源请求===========start");
        String postUrl = request.getRequestURL().toString();//请求的url
        logger.info("驻马店交换平台接口资源请求==========请求的url: "+postUrl);
        String ipAddr = IpUtil.getIpAddr(request);//请求方ip
        logger.info("驻马店交换平台接口资源请求==========ip: "+ipAddr);

        mess.put("ip","请求ip："+ipAddr);

        Map<String, Object> map = connectUtil.getIPWHITE(ipAddr);
        logger.info("驻马店交换平台接口资源请求==========map: "+map.toString());
        Object[] args = pdj.getArgs();//获取参数数组
        logger.info("驻马店交换平台接口资源请求==========请求的参数: "+Arrays.toString(args));
        nullToEmpty(args);
        Object ret = pdj.proceed(args);//执行原方法并获取返回结果
        logger.info("驻马店交换平台接口资源请求==========返回值: "+ret);
        long endTime = System.currentTimeMillis();//结束时间
        mess.put("data",ret);
/* jdbcTemplate.execute(
                "insert into web_info(ip,orgcode,rescode,inparam,outparam,CRTDATE) values (?,?,?,?,?,?)", new AbstractLobCreatingPreparedStatementCallback(new DefaultLobHandler() ){
                    protected void setValues(PreparedStatement pstmt, LobCreator lobCreator) throws SQLException {
                        pstmt.setString(1,ipAddr);
                        pstmt.setString(2,"300000");
                        pstmt.setString(3,"test");
                        InputStream inputStream   =   new ByteArrayInputStream(ret.toString().getBytes());
                        InputStream inputStream2   =   new ByteArrayInputStream(Arrays.toString(args).getBytes());
                        lobCreator.setBlobAsBinaryStream(pstmt,4,inputStream2,Arrays.toString(args).length());
                        lobCreator.setBlobAsBinaryStream(pstmt,5,inputStream,ret.toString().length());
                        pstmt.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    }
                });*/

        logger.info("驻马店交换平台接口资源请求==========请求的响应耗时: " + (endTime - startTime) + "毫秒");
        logger.info("驻马店交换平台接口资源请求===========end");
        String jsonString = JSONObject.toJSONString(mess);
        return ret;
    }

    public void nullToEmpty(Object[] orgs){
        try{
            if(orgs != null && orgs.length > 0){
                for (int i = 0;i<orgs.length;i++){
                    if(orgs[i]==null){
                        orgs[i] = "";
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

}