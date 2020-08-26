package com.bm.https.center;


import com.alibaba.fastjson.JSONObject;
import com.bm.https.untils.AllHttpUntil;
import com.bm.https.untils.TransDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;


@Controller
@RequestMapping("/center")
public class CenterController {

    private Logger logger = LoggerFactory.getLogger(CenterController.class);

    @RequestMapping(value = "/index",method = {RequestMethod.GET,RequestMethod.POST})
    public String getHttpTest(){
        logger.info("跳页面");
       return "index";
    }

    @RequestMapping(value = "/index2",method = {RequestMethod.GET,RequestMethod.POST})
    public String getHttpTest3(){
        logger.info("跳页面2");
        return "index";
    }


    @RequestMapping(value = "/publicdata",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String publicdata(@RequestParam("sourceurl") String sourceurl,
                             @RequestParam("sourcename") String sourcename,
                             @RequestParam("sourcepwd") String sourcepwd,
                             @RequestParam("sourcedriver") String sourcedriver,
                             @RequestParam("sourcesql") String sourcesql,
                             @RequestParam("needurl") String needurl,
                             @RequestParam("needname") String needname,
                             @RequestParam("needpwd") String needpwd,
                             @RequestParam("needdriver") String needdriver,
                             @RequestParam("needsql") String needsql,
                             @RequestParam("nums") Integer nums){


        Map<String, Object> sourcemap = new LinkedHashMap<>();
        Map<String, Object> needmap = new LinkedHashMap<>();
        sourcemap.put("sourceurl",sourceurl);
        sourcemap.put("sourcename",sourcename);
        sourcemap.put("sourcepassword",sourcepwd);
        sourcemap.put("sourcedriver",sourcedriver);
        sourcemap.put("sourcesql",sourcesql);
        needmap.put("needurl",needurl);
        needmap.put("needname",needname);
        needmap.put("needpassword",needpwd);
        needmap.put("needdriver",needdriver);
        needmap.put("needsql",needsql);

        logger.info("开始执行数据迁移。。。。。。。。");
        String s = TransDemo.publicDataTrans(sourcemap, needmap, nums);
        logger.info("结束数据迁移。。。。。。。。");

        return "1";
    }

    @RequestMapping(value = "/index3/{username}",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String getHttpTest2(@PathVariable String username){
        StringBuffer sb=new StringBuffer();
        sb.append(username);

        synchronized(sb.toString().intern()) {
            System.out.println(username);
            System.out.println("锁等待========"+username);
            try {
                //延时3秒执行

            } catch (Exception ie) {
                ie.printStackTrace();
            }

            String username2 = username + "运算结果:::::: ";
            System.out.println(username2);
            sb.append(username2);
            System.out.println("锁等待结束========"+username);
        }
        return "你好！世界" + sb;
    }



    @RequestMapping(value = "/Testfile",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object Testfile(@RequestParam("name") String username,@RequestParam("filename") String filename,@RequestParam("jsonString") String jsonString){
        long start = System.currentTimeMillis();
        logger.info("测试文件接口开始=========={}",start);
        Map<String, Object> map = new HashMap<>();
        try{
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            Map map1 = JSONObject.parseObject(jsonString, Map.class);
            map.put("usrname",username);
            map.putAll(map1);
            byte[] bytes = AllHttpUntil.File2byte(new File(filename));
            String s = AllHttpUntil.ioToBase64(bytes);
            map.put("file",bytes);
            long end = System.currentTimeMillis();
            logger.info("测试文件接口结束==========用时{}豪秒",(end-start));
            return map;

        }catch (Exception e){
            return "你好！世界" ;
        }
    }
}
