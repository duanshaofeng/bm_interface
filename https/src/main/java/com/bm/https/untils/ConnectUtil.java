package com.bm.https.untils;

import com.alibaba.fastjson.JSONObject;
import com.bm.https.controller.HttpsController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * dsf 连接数据库
 */
@Component
public class ConnectUtil {

    private static Logger logger = LoggerFactory.getLogger(ConnectUtil.class);

    @Value("${connect.explat.driver}")
    private String driver;
    @Value("${connect.explat.name}")
    private String name;
    @Value("${connect.explat.password}")
    private String password;
    @Value("${connect.explat.url}")
    private String url;
    @Value("${connect.explat.ipWhiteSql}")
    private String ipWhiteSql;


    @Value("${connect.zyml.name}")
    private String zname;
    @Value("${connect.zyml.password}")
    private String zpassword;
    @Value("${connect.zyml.url}")
    private String zurl;
    @Value("${connect.zyml.getAccessToken}")
    private String getAccessToken;
    @Value("${connect.zyml.updateToken}")
    private String updateToken;

    @Value("${jdbc.bdc.url}")
    private String bdcurl;
    @Value("${jdbc.bdc.name}")
    private String bdcname;
    @Value("${jdbc.bdc.pwd}")
    private String bdcpwd;
    @Value("${jdbc.bdc.clfzjjgsql}")
    private String clfzjjgsql;
    @Value("${jdbc.bdc.spfzjjgsql}")
    private String spfzjjgsql;
    @Value("${jdbc.bdc.wxzjjgsql}")
    private String wxzjjgsql;
    @Value("${jdbc.bdc.spfzjjgqtsql}")
    private String spfzjjgqtsql;






    public Object getBDCZJJGData(String zjhm,String ywlb,String flag){


            Connection conn = null;
            PreparedStatement preparedStatement = null;
            long start = System.currentTimeMillis();
            try{
                logger.info("资源目录数据库连接======= url:"+bdcurl+"   name:"+bdcname+"   password:"+bdcpwd);
                Map<String, Object> map = new HashMap<>();
                Class.forName(driver);
                conn = DriverManager.getConnection(bdcurl, bdcname, bdcpwd);
                String sql = "";
                if(flag.equals("clf") ){
                    sql = clfzjjgsql;
                }else if(flag.equals("spf")){
                    sql = spfzjjgsql;
                }else if(flag.equals("spfqt")){
                    sql = spfzjjgqtsql;
                }else{
                    sql = wxzjjgsql;
                }
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1,zjhm);
                preparedStatement.setString(2,ywlb);
                ResultSet resultSet = preparedStatement.executeQuery();
                ResultSetMetaData md = resultSet.getMetaData();// 得到结果集列的属性
                int columnCount = md.getColumnCount();
                List<Map<String,Object>> list = new ArrayList<>();
                while(resultSet.next()){
                    Map<String, Object> maps = new HashMap<>();
                    for (int i = 0; i < columnCount; i++) {
                        if("BATCHNO".equals(md.getColumnName(i + 1))||"S_GUID".equals(md.getColumnName(i + 1))||"OPERSTATUS".equals(md.getColumnName(i + 1))||"CTRTIME".equals(md.getColumnName(i + 1))){
                            continue;
                        }
                        maps.put(md.getColumnName(i + 1),resultSet.getString(md.getColumnName(i + 1)));
                    }
                    list.add(maps);
                }

                if(list.size()>0){
                    map.put("code","0");
                    map.put("message","查询成功，查询出"+list.size()+"条数据");
                    map.put("data",list);
                }else{
                    map.put("code","1");
                    map.put("message","查询成功，未查询出数据");
                    map.put("data",list);
                }
                 logger.info("===============不动产资源监管查询结束查询==使用"+(System.currentTimeMillis()-start)/1000+"秒");
                return map;


            }catch(Exception e){
            e.printStackTrace();
        }finally {
                try {
                    if(conn!=null)
                    {
                        conn.close();
                    }
                    if(preparedStatement!=null)
                    {
                        preparedStatement.close();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        return null;

    }



    public Map<String, Object> getIPWHITE(String ip){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            logger.info("交换平台数据库连接=======driver:"+driver+"  url:"+url+"   name:"+name+"   password:"+password);
            Map<String, Object> map = new HashMap<>();
            Class.forName(driver);
            conn = DriverManager.getConnection(url, name, password);
            preparedStatement = conn.prepareStatement(ipWhiteSql);
            preparedStatement.setString(1,ip);
            ResultSet resultSet = preparedStatement.executeQuery();
            map.put("flag",false);
            String wip = "";
            String deptcode = "";
            String deptname= "";
            while (resultSet.next()){
                wip = resultSet.getString("IP");
                deptcode = resultSet.getString("ORGCODE");
                deptname = resultSet.getString("ORGNAME");
                map.put("flag",true);
                map.put("wip",wip);
                map.put("deptcode",deptcode);
                map.put("deptname",deptname);
            }
            return map;
        }catch(Exception e){
           e.printStackTrace();
           return null;
        }finally {
            try {
                conn.close();
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    public Map<String, Object> accessTokenCheck(String accessToken,String code){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            logger.info("资源目录数据库连接=======driver:"+driver+"  url:"+zurl+"   name:"+zname+"   password:"+zpassword);
            Map<String, Object> map = new HashMap<>();
            Class.forName(driver);
            conn = DriverManager.getConnection(zurl, zname, zpassword);
            preparedStatement = conn.prepareStatement(getAccessToken);
            preparedStatement.setString(1,accessToken);
            ResultSet resultSet = preparedStatement.executeQuery();
            map.put("flag",false);
            String wip = "";
            Timestamp updatetime = null;
            while (resultSet.next()){
                updatetime = resultSet.getTimestamp("UPDATETIME");
                map.put("flag",true);
                map.put("updatetime",updatetime);
            }

            return map;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally {
            try {
                conn.close();
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public Map<String, Object> updateToken(String accessToken, String deptcode) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            logger.info("资源目录数据库连接=======driver:"+driver+"  url:"+zurl+"   name:"+zname+"   password:"+zpassword);
            Map<String, Object> map = new HashMap<>();
            String[] tokens = accessToken.split("-");
            String format = new SimpleDateFormat("yyyyMMddhh24mmsss").format(new Date());
            int random = (int)((Math.random()*9+1)*100000);
            String accessTokenNew =  "ZMD-" + format + "-" + random + "-" + tokens[3] + "-" +  tokens[4];
            Class.forName(driver);
            conn = DriverManager.getConnection(zurl, zname, zpassword);
            preparedStatement = conn.prepareStatement(updateToken);
            preparedStatement.setString(1,accessTokenNew);
            preparedStatement.setString(2,deptcode);
            preparedStatement.setString(3,accessToken);
            int i = preparedStatement.executeUpdate();
            if(i>0){
                map.put("success",true);
                map.put("accessToken",accessTokenNew);
                return map;
            }
            map.put("success",false);
            map.put("message","刷新token失败");
            return map;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally {
            try {
                conn.close();
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }



    }
}
