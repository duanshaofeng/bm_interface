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

@Api(description  = "泌阳县统一受理接口类")
@RestController
@RequestMapping("/api")
public class BYTYSLInterfaceController {


        private Logger logger = LoggerFactory.getLogger(BYTYSLInterfaceController.class);

        @Autowired
        private TYSLHttpUtils tyslHttpUtils;
        @Autowired
        private WebserviceUtils webserviceUtils;



    @ApiOperation(value = "泌阳县房管局收件分发接口" ,  notes="返回参数")
    @RequestMapping(value = "/by/register",method = {RequestMethod.POST})
    public Object byRegister(@RequestParam(value = "baseInfoXml",required = true)String baseInfoXml,
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
            logger.info("请求泌阳县房管局收件分发接口=========参数 ："+jsonString);
            logger.info("请求泌阳县房管局收件分发接口=========url ：http://59.207.223.248:8080?wsdl");
            Object client = webserviceUtils.webserviceClient("http://59.207.223.248:8080?wsdl",jsonString );
            Map<String, Object> hashMap = new HashMap<>();
            if(client!=null){
                logger.info("请求泌阳县房管局收件分发接口返回值====="+client);
                return client;
            }else{
                hashMap.put("result",false);
                hashMap.put("resultmsg","失败");
                return hashMap;
            }


        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }





}
