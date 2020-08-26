package com.bm.https.controller.tysl;

import com.alibaba.fastjson.JSONObject;
import com.bm.https.untils.TYSLHttpUtils;
import com.bm.https.untils.WebserviceUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(description  = "自来水统一受理接口类")
@RestController
@RequestMapping("/api")
public class ZLSTYSLInterfaceController {


        private Logger logger = LoggerFactory.getLogger(ZLSTYSLInterfaceController.class);

        @Autowired
        private TYSLHttpUtils tyslHttpUtils;
        @Autowired
        private WebserviceUtils webserviceUtils;



    @ApiOperation(value = "自来水收件分发接口" ,  notes="返回参数")
    @RequestMapping(value = "/zls/register",method = {RequestMethod.POST})
    public Object scRegister(@RequestParam(value = "baseInfoXml",required = true)String baseInfoXml,
                             @RequestParam(value = "attrXml",required = true)String attrXml,
                             @RequestParam(value = "formXml",required = false)String formXml,
                             @RequestParam(value = "apasPostXml",required = false)String apasPostXml){
        try{

            Map<String, String> map = new HashMap<>();
            map.put("baseInfoXml",baseInfoXml);
            map.put("attrXml",attrXml);
            map.put("formXml",formXml);
            map.put("apasPostXml",apasPostXml);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("请求自来水收件分发接口=========参数 ："+jsonString);
            logger.info("请求自来水收件分发接口=========url ： http://59.207.237.5:8089/DServices/api/register.json");
            Object bdcPost = tyslHttpUtils.sendPost2("http://59.207.237.5:8089/DServices/api/register.json",map);
            logger.info("请求自来水收件分发接口返回值====="+bdcPost);
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }








}
