package com.bm.https.pointcut;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

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

/**
 * @Description:服务切面
 * @Author: dsf
 * @datetime:2019/8/24
 *
 */

@Aspect
@Component
public class ServerAspect {

    @Autowired
    private ConnectUtil connectUtil;
    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    private final Logger logger = LoggerFactory.getLogger(ServerAspect.class);
    @Around("execution(public * com.bm.https.accessToken..*.*(..))")
    public Object controllerLog(ProceedingJoinPoint pdj) throws Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String accessToken = request.getHeader("accessToken");
        Map<String, Object> mess = new HashMap<>();
        long startTime = System.currentTimeMillis();//开始时间
        logger.info("驻马店交换平台接口资源请求===========start");
        String postUrl = request.getRequestURL().toString();//请求的url
        logger.info("驻马店交换平台接口资源请求==========请求的url: "+postUrl);
        String ipAddr = IpUtil.getIpAddr(request);//请求方ip
        logger.info("驻马店交换平台接口资源请求==========ip: "+ipAddr);
        Map<String, Object> map = connectUtil.getIPWHITE(ipAddr);
        Boolean flag = (Boolean) map.get("flag");
        String deptname = (String) map.get("deptname");
        String deptcode = (String) map.get("deptcode");
        if(!flag){
            logger.info("驻马店交换平台接口资源请求==========白名单验证不通过");
            mess.put("message","请确认添加ip白名单");
            mess.put("ip","请求ip："+ipAddr);
            mess.put("flag",false);
            String jsonString = JSONObject.toJSONString(mess);
            logger.info("驻马店交换平台接口资源请求==========白名单验证不通过："+jsonString);
             return jsonString;
        }

        logger.info("驻马店交换平台接口资源请求==========请求头的accessToken: "+accessToken+"   deptcode:"+deptcode);
        if(StringUtils.isEmpty(accessToken)){
            mess.put("message","请确认头信息accessToken");
            mess.put("flag",false);
            String jsonString = JSONObject.toJSONString(mess);
            logger.info("驻马店交换平台接口资源请求开始==========accessToken验证不通过："+jsonString);
            return jsonString;
        }else{
            Map<String, Object> tokenCheck = connectUtil.accessTokenCheck(accessToken, deptcode);
            Boolean flag2 = (Boolean) tokenCheck.get("flag");
            if(!flag2){
                logger.info("驻马店交换平台接口资源请求==========accessToken验证不通过");
                mess.put("message","accessToken不存在或与部门不符");
                mess.put("flag",false);
                String jsonString = JSONObject.toJSONString(mess);
                logger.info("驻马店交换平台接口资源请求开始==========accessToken验证不通过："+jsonString);
                return jsonString;
            }
        }
        Object[] args = pdj.getArgs();//获取参数数组
        nullToEmpty(args);
        logger.info("驻马店交换平台接口资源请求==========请求的参数: "+Arrays.toString(args));
        Object ret = pdj.proceed(args);//执行原方法并获取返回结果
        long endTime = System.currentTimeMillis();//结束时间
        logger.info("驻马店交换平台接口资源请求==========请求的响应耗时: " + (endTime - startTime) + "毫秒");
        logger.info("驻马店交换平台接口资源请求===========insert log");
       jdbcTemplate.execute(
                "insert into web_info(ip,orgcode,rescode,inparam,outparam,CRTDATE) values (?,?,?,?,?,?)", new AbstractLobCreatingPreparedStatementCallback(new DefaultLobHandler() ){
                    protected void setValues(PreparedStatement pstmt, LobCreator lobCreator) throws SQLException {
                        pstmt.setString(1,ipAddr);
                        pstmt.setString(2,deptcode);
                        String[] str = accessToken.split("-");
                        pstmt.setString(3,str[3]);
                        InputStream inputStream   =   new ByteArrayInputStream(ret.toString().getBytes());
                        InputStream inputStream2   =   new ByteArrayInputStream(Arrays.toString(args).getBytes());
                        lobCreator.setBlobAsBinaryStream(pstmt,4,inputStream2,Arrays.toString(args).length());
                        lobCreator.setBlobAsBinaryStream(pstmt,5,inputStream,ret.toString().length());
                        pstmt.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    }
                });
        logger.info("驻马店交换平台接口资源请求===========insert log end");
        logger.info("驻马店交换平台接口资源请求===========end");
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

    public static void main(String[] args) {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        String[] str = time.split(" ");
        String[] str1 = str[0].split("-");
        String[] str2 = str[1].split(":");
        int year = Integer.parseInt(str1[0]);
        int day1 = Integer.parseInt(str1[2]);
        int min1 = Integer.parseInt(str2[0]);
        Calendar now = Calendar.getInstance();
        int day2 = now.get(Calendar.DAY_OF_MONTH);
        int min2 = now.get(Calendar.HOUR_OF_DAY);
        int year2 = now.get(Calendar.YEAR);
        System.out.println(year+"==="+year2);
        System.out.println(day1+"==="+day2);
        System.out.println(min1+"==="+min2);

    }

}