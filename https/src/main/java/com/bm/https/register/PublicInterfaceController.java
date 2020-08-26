package com.bm.https.register;


import com.bm.https.untils.RegisterHttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 注册接口
 * 统一调用
 * 先编写http的接口注册程序
 */
@RestController
@RequestMapping("/api")
public class PublicInterfaceController {

    private Logger logger = LoggerFactory.getLogger(PublicInterfaceController.class);

    @Autowired
    @Qualifier("secondJdbcTemplate")
    private JdbcTemplate jdbcTemplate;



    @RequestMapping(value = "/request",method = {RequestMethod.GET,RequestMethod.POST})
    public Object registerHttp(@RequestParam("id")String id ,@RequestParam("params")String params){

        try{
            //注册接口必须的要素
            /**
             * 1.接口地址
             * 2.接口请求方式
             * 3.接口请求头信息
             * 4. params 示列
             *       {
             *           "name":"张三",
             *           "sex":"女"
             *       }
             */


            String sql = "select * from REGISTERINTERFACE where id = '"+id+"'";
            Map<String, Object> maps = jdbcTemplate.queryForMap(sql, new Object[]{}, new int[]{});
            String url = maps.get("URL").toString();
            String method = maps.get("METHOD").toString();
            String head = maps.get("HEAD").toString();
            method = method.toUpperCase();
            switch (method){
                case "POST" :
                    return RegisterHttpUtil.sendPost(params,head,url);
                case "GET" :
                    return RegisterHttpUtil.sendGet(url,params,head);
                default:
                    return RegisterHttpUtil.sendPost(params,head,url);
            }








        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

}
