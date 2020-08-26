package com.bm.https.controller.tysl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bm.https.untils.BDCHttpUtils;
import com.bm.https.untils.TYSLHttpUtils;
import com.bm.https.untils.WebserviceUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(description  = "市统一受理接口类")
@RestController
@RequestMapping("/api")
public class TYSLInterfaceController {
    private Logger logger = LoggerFactory.getLogger(TYSLInterfaceController.class);

    @Autowired
    private TYSLHttpUtils tyslHttpUtils;
    @Autowired
    private WebserviceUtils webserviceUtils;
    @ApiOperation(value = "市一窗受理接口" ,  notes="返回参数")
    @RequestMapping(value = "/accept",method = {RequestMethod.POST})
    public Object accept(@RequestParam(value = "acceptInfoXml",required = true)String acceptInfoXml,
                           @RequestParam(value = "attrXml",required = false)String attrXml){
        try{

            Map<String, String> map = new HashMap<>();

            map.put("acceptInfoXml",acceptInfoXml);
            map.put("attrXml",attrXml);
            String jsonString = JSONObject.toJSONString(map);
            logger.info(jsonString);
            Object bdcPost = tyslHttpUtils.sendPost2("http://59.207.219.195:8083/api/accept",map);
            logger.info("参数值====="+bdcPost.toString());
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "市一窗收件接口" ,  notes="返回参数")
    @RequestMapping(value = "/register",method = {RequestMethod.POST})
    public Object register(@RequestParam(value = "baseInfoXml",required = true)String baseInfoXml,
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
            logger.info(jsonString);
            Object bdcPost = tyslHttpUtils.sendPost2("http://59.207.219.195:8083/api/register",map);
            logger.info("参数值====="+bdcPost);
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "市一窗补齐补正结束接口" ,  notes="返回参数")
    @RequestMapping(value = "/patchAccept",method = {RequestMethod.POST})
    public Object patchAccept(@RequestParam(value = "patchEndXml",required = true)String patchEndXml,
                              @RequestParam(value = "attrXml",required = false)String attrXml){
        try{

            Map<String, String> map = new HashMap<>();
            map.put("patchEndXml",patchEndXml);
            map.put("attrXml",attrXml);
            String jsonString = JSONObject.toJSONString(map);
            logger.info(jsonString);
            Object bdcPost = tyslHttpUtils.sendPost2("http://59.207.219.195:8083/api/patchAccept",map);
            logger.info("参数值====="+bdcPost);
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "市一窗办理环节接口" ,  notes="返回参数")
    @RequestMapping(value = "/nodeinfo",method = {RequestMethod.POST})
    public Object nodeinfo(@RequestParam(value = "projid",required = true)String projid,
                         @RequestParam(value = "nodeTransXml",required = true)String nodeTransXml){
        try{

            Map<String, String> map = new HashMap<>();

            map.put("projid",projid);
            map.put("nodeTransXml",nodeTransXml);
            String jsonString = JSONObject.toJSONString(map);
            logger.info(jsonString);
            Object bdcPost = tyslHttpUtils.sendPost2("http://59.207.219.195:8083/api/nodeinfo",map);
            logger.info("参数值====="+bdcPost);
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "市一窗补齐补正告知接口" ,  notes="返回参数")
    @RequestMapping(value = "/patchApply",method = {RequestMethod.POST})
    public Object patchApply(@RequestParam(value = "patchXml",required = true)String patchXml,
                           @RequestParam(value = "patchAttrXml",required = true)String patchAttrXml){
        try{

            Map<String, String> map = new HashMap<>();

            map.put("patchXml",patchXml);
            map.put("patchAttrXml",patchAttrXml);
            String jsonString = JSONObject.toJSONString(map);
            logger.info(jsonString);
            Object bdcPost = tyslHttpUtils.sendPost2("http://59.207.219.195:8083/api/patchApply",map);
            logger.info("参数值====="+bdcPost.toString());
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "市一窗特别程序开始接口" ,  notes="返回参数")
    @RequestMapping(value = "/specialBegin",method = {RequestMethod.POST})
    public Object specialBegin(@RequestParam(value = "specialStartXml",required = true)String specialStartXml){
        try{

            Map<String, String> map = new HashMap<>();

            map.put("specialStartXml",specialStartXml);
            String jsonString = JSONObject.toJSONString(map);
            logger.info(jsonString);
            Object bdcPost = tyslHttpUtils.sendPost2("http://59.207.219.195:8083/api/specialBegin",map);
            logger.info("参数值====="+bdcPost.toString());
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "市一窗特别程序结束接口" ,  notes="返回参数")
    @RequestMapping(value = "/specialEnd",method = {RequestMethod.POST})
    public Object  specialEnd(@RequestParam(value = "specialEndXml",required = true)String specialEndXml){
        try{

            Map<String, String> map = new HashMap<>();

            map.put("specialEndXml",specialEndXml);
            String jsonString = JSONObject.toJSONString(map);
            logger.info(jsonString);
            Object bdcPost = tyslHttpUtils.sendPost2("http://59.207.219.195:8083/api/specialEnd",map);
            logger.info("参数值====="+bdcPost.toString());
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "市一窗办结接口" ,  notes="返回参数")
    @RequestMapping(value = "/transact",method = {RequestMethod.POST})
    public Object transact(@RequestParam(value = "transactXml",required = true)String transactXml,
                           @RequestParam(value = "attrXml",required = false)String attrXml){
        try{

            Map<String, String> map = new HashMap<>();

            map.put("transactXml",transactXml);
            map.put("attrXml",attrXml);
            String jsonString = JSONObject.toJSONString(map);
            logger.info(jsonString);
            Object bdcPost = tyslHttpUtils.sendPost2("http://59.207.219.195:8083/api/transact",map);
            logger.info("参数值====="+bdcPost);
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "市房管局收件分发接口" ,  notes="返回参数")
    @RequestMapping(value = "/sfg/register",method = {RequestMethod.POST})
    public Object sfgRegister(@RequestParam(value = "baseInfoXml",required = true)String baseInfoXml,
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
            logger.info("请求市房管局收件分发接口=========参数 ："+jsonString);
            logger.info("请求市房管局收件分发接口=========url ： http://59.207.237.13:9001/api/register");
            Object bdcPost = tyslHttpUtils.sendPost2("http://59.207.237.13:9001/api/register",map);
            logger.info("请求市房管局收件分发接口返回值====="+bdcPost);
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "市房管局补齐补正结束接口" ,  notes="返回参数")
    @RequestMapping(value = "/sfg/patchAccept",method = {RequestMethod.POST})
    public Object sfgPatchAccept(@RequestParam(value = "patchEndXml",required = true)String patchEndXml,
                              @RequestParam(value = "attrXml",required = false)String attrXml){
        try{

            Map<String, String> map = new HashMap<>();

            map.put("patchEndXml",patchEndXml);
            map.put("attrXml",attrXml);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("请求市房管局补齐补正结束接口=========参数 ："+jsonString);
            logger.info("请求市房管局补齐补正结束接口=========url ： http://59.207.237.13:9001/api/patchAccept");
            Object bdcPost = tyslHttpUtils.sendPost2("http://59.207.237.13:9001/api/patchAccept",map);
            logger.info("请求市房管局补齐补正结束接口返回值====="+bdcPost);
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
