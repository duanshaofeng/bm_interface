package com.bm.https.untils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * dsf 连接数据库
 */
@Component
public class BDCZYUtil {



    private static Logger logger = LoggerFactory.getLogger(BDCZYUtil.class);
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

    public static  List<Map<String, Object>> queryList(String url,String name,String pwd,String sql,Object [] param,int [] paramType){
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
            List<Map<String, Object>> map = jdbcTemplate.queryForList(sql,param,paramType);
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

}
