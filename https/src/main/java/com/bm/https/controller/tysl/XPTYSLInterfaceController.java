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

@Api(description  = "西平县统一受理接口类")
@RestController
@RequestMapping("/api")
public class XPTYSLInterfaceController {


        private Logger logger = LoggerFactory.getLogger(XPTYSLInterfaceController.class);

        @Autowired
        private TYSLHttpUtils tyslHttpUtils;
        @Autowired
        private WebserviceUtils webserviceUtils;



    @ApiOperation(value = "西平县房管局收件分发接口" ,  notes="返回参数")
    @RequestMapping(value = "/xp/register",method = {RequestMethod.POST})
    public Object xpRegister(@RequestParam(value = "baseInfoXml",required = true)String baseInfoXml,
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
            logger.info("请求西平县房管局收件分发接口=========参数 ："+jsonString);
            logger.info("请求西平县房管局收件分发接口=========url ：http://59.207.227.248:8795/api/register");
            Object bdcPost = tyslHttpUtils.sendPost2("http://59.207.227.248:8795/api/register",map);
            logger.info("请求西平县房管局收件分发接口返回值====="+bdcPost);
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    @ApiOperation(value = "西平县住房管理补齐补正结束接口" ,  notes="返回参数")
    @RequestMapping(value = "/xp/patchAccept",method = {RequestMethod.POST})
    public Object xpPatchAccept(@RequestParam(value = "patchEndXml",required = true)String patchEndXml,
                                @RequestParam(value = "attrXml",required = false)String attrXml){
        try{

            Map<String, String> map = new HashMap<>();

            map.put("patchEndXml",patchEndXml);
            map.put("attrXml",attrXml);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("请求西平县住房管理补齐补正结束接口=========参数 ："+jsonString);
            logger.info("请求西平县住房管理补齐补正结束接口=========url ： http://59.207.227.248:8795/api/patchAccept");
            Object bdcPost = tyslHttpUtils.sendPost2("http://59.207.227.248:8795/api/patchAccept",map);
            logger.info("请求西平县住房管理补齐补正结束接口返回值====="+bdcPost);
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }




    @ApiOperation(value = "西平县住房管理资金监管收件分发接口" ,  notes="返回参数")
    @RequestMapping(value = "/xp/zjjg/register",method = {RequestMethod.POST})
    public Object xpZjjgRegister(@RequestParam(value = "baseInfoXml",required = true)String baseInfoXml,
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
            logger.info("西平县住房管理资金监管收件分发接口=========参数 ："+jsonString);
            logger.info("西平县住房管理资金监管收件分发接口=========url ：http://59.207.226.162:9111/DataSyncSkipService.asmx?WSDL");
            Object syncRegister = WebserviceUtils.webserviceClient("http://59.207.226.162:9111/DataSyncSkipService.asmx?WSDL", "SyncRegister", baseInfoXml, attrXml,formXml,apasPostXml);
//            Object bdcPost = tyslHttpUtils.sendPost2("http://59.207.227.248:8795/api/register",map);
            logger.info("请求西平县住房管理资金监管收件分发接口返回值====="+syncRegister);
            return syncRegister;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
