package com.bm.https.register;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册接口
 * 统一调用
 * 先编写http的接口注册程序
 */
@RestController
@RequestMapping("/api")
public class RegisterController {

    private Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    @Qualifier("secondJdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    @Value("${register.public.url}")
    private String resisterurl;
    @RequestMapping(value = "/register",method = {RequestMethod.GET,RequestMethod.POST})
    public Object registerHttp(@RequestParam("url")String url ,
                               @RequestParam("mehtod")String mehtod,
                               @RequestParam("head")String head){

        try{
            //注册接口必须的要素
            /**
             * 1.接口地址
             * 2.接口请求方式
             * 3.接口请求头信息
             *
             */
            String id = System.currentTimeMillis()+"";
            String newurl  = resisterurl+id;
            String sql = "insert into REGISTERINTERFACE(id,url,method,head,newurl) values('"+id+"','"+url+"','"+mehtod+"','"+head+"','"+newurl+"')";
            int i = jdbcTemplate.update(sql);
            return id;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

}
