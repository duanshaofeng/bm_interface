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

@Api(description  = "确山县统一受理接口类")
@RestController
@RequestMapping("/api")
public class QSTYSLInterfaceController {


        private Logger logger = LoggerFactory.getLogger(QSTYSLInterfaceController.class);

        @Autowired
        private TYSLHttpUtils tyslHttpUtils;
        @Autowired
        private WebserviceUtils webserviceUtils;




        @ApiOperation(value = "确山县住房管理收件分发接口" ,  notes="返回参数")
        @RequestMapping(value = "/qs/register",method = {RequestMethod.POST})
        public Object qsRegister(@RequestParam(value = "baseInfoXml",required = true)String baseInfoXml,
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
            logger.info("请求确山县住房管理收件分发接口=========参数 ："+jsonString);
            logger.info("请求确山县住房管理收件分发接口=========url ： http://59.207.221.248:8001/api/register");
            Object bdcPost = tyslHttpUtils.sendPost2("http://59.207.221.248:8001/api/register",map);
            logger.info("请求确山县住房管理收件分发接口返回值====="+bdcPost);
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


        @ApiOperation(value = "确山县住房管理补齐补正结束接口" ,  notes="返回参数")
        @RequestMapping(value = "/qs/patchAccept",method = {RequestMethod.POST})
        public Object qsPatchAccept(@RequestParam(value = "patchEndXml",required = true)String patchEndXml,
            @RequestParam(value = "attrXml",required = false)String attrXml){
        try{

            Map<String, String> map = new HashMap<>();

            map.put("patchEndXml",patchEndXml);
            map.put("attrXml",attrXml);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("请求确山县住房管理补齐补正结束接口=========参数 ："+jsonString);
            logger.info("请求确山县住房管理补齐补正结束接口=========url ： http://59.207.221.248:8001/api/patchAccept");
            Object bdcPost = tyslHttpUtils.sendPost2("http://59.207.221.248:8001/api/patchAccept",map);
            logger.info("请求确山县住房管理补齐补正结束接口返回值====="+bdcPost);
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }




}
