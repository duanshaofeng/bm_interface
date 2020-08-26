package com.bm.https.controller.province.tyzy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bm.https.untils.ProvinceUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description  = "统一资源库接口类")
@RestController
@RequestMapping("/tyzytest")
public class TongYiZiYuanInterfaceTestController {
    private Logger logger = LoggerFactory.getLogger(TongYiZiYuanInterfaceTestController.class);



    @ApiOperation(value = "新冠肺炎_可能密切接触者数据查询服务接口" ,  notes="返回参数")
    @RequestMapping(value = "/queryContacter",method = {RequestMethod.POST,RequestMethod.GET})
    public Object queryContacter(@RequestParam(value = "xm",required = false)String xm,@RequestParam(value = "sfzhm",required = false)String sfzhm){
        Object data = null;
        try{
            String appId = "41cd6ff5d79049b7a884dd2607d55475";
            String appKey = "NDFjZDZmZjVkNzkwNDliN2E4ODRkZDI2MDdkNTU0NzU6MTIzNDU2";
            Map<String, String> map = new HashMap<>();
            map.put("xm",xm);
            map.put("sfzhm",sfzhm);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============新冠肺炎_可能密切接触者数据查询服务接口参数=="+jsonString);
            data = ProvinceUtils.httpDatapost(map , "http://59.207.107.18:5000/api/queryContacter",appId,appKey);
            logger.info("===============新冠肺炎_可能密切接触者数据查询服务接口返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "新冠肺炎_确认和疑似病例数据查询服务接口" ,  notes="返回参数")
    @RequestMapping(value = "/queryPatient",method = {RequestMethod.POST,RequestMethod.GET})
    public Object queryPatient(@RequestParam(value = "xm",required = false)String xm,@RequestParam(value = "sfzhm",required = false)String sfzhm){
        Object data = null;
        try{
            String appId = "41cd6ff5d79049b7a884dd2607d55475";
            String appKey = "NDFjZDZmZjVkNzkwNDliN2E4ODRkZDI2MDdkNTU0NzU6MTIzNDU2";
            Map<String, String> map = new HashMap<>();
            map.put("xm",xm);
            map.put("sfzhm",sfzhm);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============新冠肺炎_确认和疑似病例数据查询服务接口参数=="+jsonString);
            data = ProvinceUtils.httpDatapost(map , "http://59.207.107.18:5000/api/queryPatient",appId,appKey);
            logger.info("===============新冠肺炎_确认和疑似病例数据查询服务接口返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }






}
