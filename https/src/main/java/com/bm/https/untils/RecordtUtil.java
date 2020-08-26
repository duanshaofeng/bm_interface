package com.bm.https.untils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * dsf 连接数据库
 */
@Component
public class RecordtUtil {

    private static Logger logger = LoggerFactory.getLogger(RecordtUtil.class);
    private static final String sql2 = "select T.DBURL as url,T.DBNAME as name,T.DBPWD as pwd ,T.DBDEPT AS dname,T.DBSQL as sql   from BANJIANDB T where t.datatype = '1'";
    private static final String sql3 = "select T.DBURL as url,T.DBNAME as name,T.DBPWD as pwd ,T.DBDEPT AS dname,T.DBSQL as sql   from BANJIANDB T where t.datatype = '2'";
    private static final String sql1 = "insert into record () values(?,?,?,?)";

    private static   Connection conn = null;
    public static Map<String, Object> query(String url,String name,String pwd,String sql){
        JdbcTemplate jdbcTemplate = null;
        DriverManagerDataSource dataSource = null;
        long start = System.currentTimeMillis();
        try{
                dataSource = new DriverManagerDataSource();
                dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
                dataSource.setUrl(url);
                dataSource.setUsername(name);
                dataSource.setPassword(pwd);
                jdbcTemplate = new JdbcTemplate(dataSource);
                logger.info("数据库连接======= url:"+url+"   name:"+name+"   password:"+pwd);
                Map<String, Object> map = jdbcTemplate.queryForMap(sql);
                return map;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally {
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }

    public static Map<String, Object> queryforbanjian(String url,String name,String pwd,String sql,String date){
        JdbcTemplate jdbcTemplate = null;
        DriverManagerDataSource dataSource = null;
        long start = System.currentTimeMillis();
        try{
            dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
            dataSource.setUrl(url);
            dataSource.setUsername(name);
            dataSource.setPassword(pwd);
            jdbcTemplate = new JdbcTemplate(dataSource);
            logger.info("数据库连接======= url:"+url+"   name:"+name+"   password:"+pwd);
            //String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            Map<String, Object> map = jdbcTemplate.queryForMap(sql,new Object[]{date+" 00:00:00",date+" 23:59:59"},new int[]{12,12});
            map.put("date",date);
            return map;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    public static Map<String, Object> queryforbanjian(String url,String name,String pwd,String sql){
        JdbcTemplate jdbcTemplate = null;
        DriverManagerDataSource dataSource = null;
        long start = System.currentTimeMillis();
        try{
            dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
            dataSource.setUrl(url);
            dataSource.setUsername(name);
            dataSource.setPassword(pwd);
            jdbcTemplate = new JdbcTemplate(dataSource);
            logger.info("数据库连接======= url:"+url+"   name:"+name+"   password:"+pwd);
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            Map<String, Object> map = jdbcTemplate.queryForMap(sql,new Object[]{date+" 00:00:00",date+" 23:59:59"},new int[]{12,12});
            map.put("date",date);
            return map;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static Map<String, Object> queryOneForAll(String driver,String url,String name,String pwd,String sql,Object[] args,int[] argstype){
        JdbcTemplate jdbcTemplate = null;
        DriverManagerDataSource dataSource = null;
        long start = System.currentTimeMillis();
        try{
            dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
            dataSource.setUrl(url);
            dataSource.setUsername(name);
            dataSource.setPassword(pwd);
            jdbcTemplate = new JdbcTemplate(dataSource);
            logger.info("数据库连接======= url:"+url+"   name:"+name+"   password:"+pwd);
            Map<String, Object> map = jdbcTemplate.queryForMap(sql,args,argstype);
            return map;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Map<String, Object> queryOneForAll(String url,String name,String pwd,String sql,Object[] args,int[] argstype){
        JdbcTemplate jdbcTemplate = null;
        DriverManagerDataSource dataSource = null;
        long start = System.currentTimeMillis();
        try{
            dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
            dataSource.setUrl(url);
            dataSource.setUsername(name);
            dataSource.setPassword(pwd);
            jdbcTemplate = new JdbcTemplate(dataSource);
            logger.info("数据库连接======= url:"+url+"   name:"+name+"   password:"+pwd);
            Map<String, Object> map = jdbcTemplate.queryForMap(sql,args,argstype);
            return map;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static  List<Map<String, Object>> queryList(String url,String name,String pwd,String sql){
        JdbcTemplate jdbcTemplate = null;
        DriverManagerDataSource dataSource = null;
        long start = System.currentTimeMillis();
        try{
            dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
            dataSource.setUrl(url);
            dataSource.setUsername(name);
            dataSource.setPassword(pwd);
            jdbcTemplate = new JdbcTemplate(dataSource);
            logger.info("数据库连接======= url:"+url+"   name:"+name+"   password:"+pwd);
            List<Map<String, Object>> map = jdbcTemplate.queryForList(sql);
            return map;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static  List<Map<String, Object>> queryListForInterFace(String url,String name,String pwd,String sql){
        JdbcTemplate jdbcTemplate = null;
        DriverManagerDataSource dataSource = null;
        long start = System.currentTimeMillis();
        try{
            dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
            dataSource.setUrl(url);
            dataSource.setUsername(name);
            dataSource.setPassword(pwd);
            jdbcTemplate = new JdbcTemplate(dataSource);
            logger.info("数据库连接======= url:"+url+"   name:"+name+"   password:"+pwd);
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            List<Map<String, Object>> map = jdbcTemplate.queryForList(sql,new Object[]{date+" 00:00:00",date+" 23:59:59"},new int[]{12,12});
            return map;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static  List<Map<String, Object>> queryListForInterFace(String url,String name,String pwd,String sql,String date){
        JdbcTemplate jdbcTemplate = null;
        DriverManagerDataSource dataSource = null;
        long start = System.currentTimeMillis();
        try{
            dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
            dataSource.setUrl(url);
            dataSource.setUsername(name);
            dataSource.setPassword(pwd);
            jdbcTemplate = new JdbcTemplate(dataSource);
            logger.info("数据库连接======= url:"+url+"   name:"+name+"   password:"+pwd);
            //String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            List<Map<String, Object>> map = jdbcTemplate.queryForList(sql,new Object[]{date+" 00:00:00",date+" 23:59:59"},new int[]{12,12});
            return map;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static  List<Map<String, Object>> queryListForAll(String url,String name,String pwd,String sql,Object[] args , int [] argstype){
        JdbcTemplate jdbcTemplate = null;
        DriverManagerDataSource dataSource = null;
        long start = System.currentTimeMillis();
        try{
            dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
            dataSource.setUrl(url);
            dataSource.setUsername(name);
            dataSource.setPassword(pwd);
            jdbcTemplate = new JdbcTemplate(dataSource);
            logger.info("数据库连接======= url:"+url+"   name:"+name+"   password:"+pwd);
            List<Map<String, Object>> map = jdbcTemplate.queryForList(sql,args,argstype);
            return map;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static  List<Map<String, Object>> queryListForAll(String driver,String url,String name,String pwd,String sql,Object[] args , int [] argstype){
        JdbcTemplate jdbcTemplate = null;
        DriverManagerDataSource dataSource = null;
        long start = System.currentTimeMillis();
        try{
            dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(driver);
            dataSource.setUrl(url);
            dataSource.setUsername(name);
            dataSource.setPassword(pwd);
            jdbcTemplate = new JdbcTemplate(dataSource);
            logger.info("数据库连接======= url:"+url+"   name:"+name+"   password:"+pwd);
            List<Map<String, Object>> map = jdbcTemplate.queryForList(sql,args,argstype);
            return map;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void banjianDataForQuazt(){
        String mysqlurl="jdbc:mysql://localhost:3306/dsf?useUnicode=true&amp;characterEncoding=UTF8&amp;useSSL=true";
        String mysqlname="root";
        String mysqlpwd="root";
        try{
            logger.info("BJDataRecord");
            List<Map<String,Object>> list1 = RecordtUtil.queryList("jdbc:oracle:thin:interfaceDB/interfaceDB@59.207.219.23:1521:orcl", "interfaceDB", "interfaceDB", sql2);
            if(list1!=null||list1.size()>0){
                for (Map<String,Object> mm:
                        list1) {
                    Map<String, Object> query = RecordtUtil.queryforbanjian((String) mm.get("url"), (String) mm.get("name"), (String) mm.get("pwd"), (String) mm.get("sql"));
                    if(query!=null||query.size()>0){
                        Object num = query.get("total");
                        Object dailynum = query.get("dailynum");
                        Object[] arg = new Object[]{num,dailynum,(String) mm.get("dname"),query.get("date")};
                        int[] argtype = new int[]{12,12,12,12};
                        int i = RecordtUtil.insertDataForBanjian(mysqlurl, mysqlname, mysqlpwd, sql1, arg, argtype);
                        logger.info("插入办件每日更新数量==="+i);
                    }
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }


    public static void licenseDataForQuazt(){
        String mysqlurl="jdbc:mysql://localhost:3306/dsf?useUnicode=true&amp;characterEncoding=UTF8&amp;useSSL=true";
        String mysqlname="root";
        String mysqlpwd="root";
        try{
            logger.info("BJDataRecord");
            List<Map<String,Object>> list1 = RecordtUtil.queryList("jdbc:oracle:thin:interfaceDB/interfaceDB@59.207.219.23:1521:orcl", "interfaceDB", "interfaceDB", sql3);
            if(list1!=null||list1.size()>0){
                for (Map<String,Object> mm:
                        list1) {
                    Map<String, Object> query = RecordtUtil.queryforbanjian((String) mm.get("url"), (String) mm.get("name"), (String) mm.get("pwd"), (String) mm.get("sql"));
                    if(query!=null||query.size()>0){
                        Object num = query.get("total");
                        Object dailynum = query.get("dailynum");
                        Object[] arg = new Object[]{num,dailynum,(String) mm.get("dname"),query.get("date")};
                        int[] argtype = new int[]{12,12,12,12};
                        int i = RecordtUtil.insertDataForBanjian(mysqlurl, mysqlname, mysqlpwd, sql1, arg, argtype);
                        logger.info("插入办件每日更新数量==="+i);
                    }
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }


    public static  int insertDataForBanjian(String url,String name,String pwd,String sql,Object[] args,int[] argtype){
        JdbcTemplate jdbcTemplate = null;
        DriverManagerDataSource dataSource = null;
        long start = System.currentTimeMillis();
        try{
            dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl(url);
            dataSource.setUsername(name);
            dataSource.setPassword(pwd);
            jdbcTemplate = new JdbcTemplate(dataSource);
            logger.info("数据库连接======= url:"+url+"   name:"+name+"   password:"+pwd);
            int i = jdbcTemplate.update(sql, args, argtype);
            return i;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }finally {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static  int insertDataForBanjian(String url,String driver,String name,String pwd,String sql,Object[] args,int[] argtype){
        JdbcTemplate jdbcTemplate = null;
        DriverManagerDataSource dataSource = null;
        long start = System.currentTimeMillis();
        try{
            dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(driver);
            dataSource.setUrl(url);
            dataSource.setUsername(name);
            dataSource.setPassword(pwd);
            jdbcTemplate = new JdbcTemplate(dataSource);
            logger.info("数据库连接======= url:"+url+"   name:"+name+"   password:"+pwd);
            int i = jdbcTemplate.update(sql, args, argtype);
            return i;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }finally {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Boolean checkConn(String dbURL,String userName, String userpwd){
        String driverName="oracle.jdbc.OracleDriver";//这是要连接的数据库加载器
        try {
            Class.forName(driverName);
            System.out.println("加载驱动成功");
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("加载驱动失败");
            return false;
        }
        try {
            Connection dbConn= DriverManager.getConnection(dbURL,userName,userpwd);
            System.out.println("连接数据库成功");
            dbConn.close();
            return true;
        }catch (Exception e) {
            System.out.println("数据库连接失败");
            return false;
        }

    }




    public static void main(String args[]) {
        Boolean aBoolean = checkConn("jdbc:oracle:thin:esplatform/esplatform@59.207.219.135:1521:orcl", "esplatform", "esplatform");
        System.out.println(aBoolean);
    }
}
