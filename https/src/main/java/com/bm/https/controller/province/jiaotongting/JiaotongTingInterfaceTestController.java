package com.bm.https.controller.province.jiaotongting;

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

import java.util.HashMap;
import java.util.Map;

@Api(description  = "省交通运输厅接口类")
@RestController
@RequestMapping("/jtttest")
public class JiaotongTingInterfaceTestController {
    private Logger logger = LoggerFactory.getLogger(JiaotongTingInterfaceTestController.class);


    //TODO
    @ApiOperation(value = "省交通运输厅大件收件分发接口" ,  notes="返回参数")
    @RequestMapping(value = "/djsjff",method = {RequestMethod.POST})
    public Object sjjtjdjsjff(@RequestParam(value = "baseInfoXml",required = true)String baseInfoXml,@RequestParam(value = "attrXml",required = false   )String attrXml,
                              @RequestParam(value = "formXml",required = false)String formXml,@RequestParam(value = "apasPostXml",required = false)String apasPostXml){
        try{
            //String appId = "d2c8b7e3dcae4925b51d7b0b14377927";
            //String appKey ="ZDJjOGI3ZTNkY2FlNDkyNWI1MWQ3YjBiMTQzNzc5Mjc6MTIzNDU2";
            String appId = "bd6a71d754414e5fa6ac7f7d2b96afac";
            String appKey ="YmQ2YTcxZDc1NDQxNGU1ZmE2YWM3ZjdkMmI5NmFmYWM6MTIzNDU2";
            Map<String, String> map = new HashMap<>();
            map.put("baseInfoXml",baseInfoXml);
            map.put("formXml",formXml);
            map.put("attrXml",attrXml);
            map.put("apasPostXml",apasPostXml);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("省交通运输厅大件收件分发参数====="+jsonString);
            Object data = ProvinceUtils.httpDatapost(map, "http://59.207.107.18:5000/api/sjjtj_djsjff",appId,appKey);
            logger.info("===============省交通运输厅大件收件分发返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "省交通厅_大件补齐补正结束接口" ,  notes="返回参数")
    @RequestMapping(value = "/djbqbz",method = {RequestMethod.POST})
    public Object djbqbz(@RequestParam(value = "attrXml",required = false)String attrXml,@RequestParam(value = "patchEndXml",required = true   )String patchEndXml){
        try{
            String appId = "d2c8b7e3dcae4925b51d7b0b14377927";
            String appKey ="ZDJjOGI3ZTNkY2FlNDkyNWI1MWQ3YjBiMTQzNzc5Mjc6MTIzNDU2";
            Map<String, String> map = new HashMap<>();
            map.put("patchEndXml",patchEndXml);
            map.put("attrXml",attrXml);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("省交通厅_大件补齐补正结束参数====="+jsonString);
            Object data = ProvinceUtils.httpDatapost(map, "http://59.207.107.18:5000/api/sjjtj_djbqbz",appId,appKey);
            logger.info("===============省交通厅_大件补齐补正结束返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "省交通厅_高速特别程序结束接口" ,  notes="返回参数")
    @RequestMapping(value = "/gstbcxjs",method = {RequestMethod.POST})
    public Object gstbcxjs(@RequestParam(value = "onSpecialEnd",required = false)String onSpecialEnd){
        try{
            String appId = "bd6a71d754414e5fa6ac7f7d2b96afac";
            String appKey ="YmQ2YTcxZDc1NDQxNGU1ZmE2YWM3ZjdkMmI5NmFmYWM6MTIzNDU2";
            Map<String, String> map = new HashMap<>();
            map.put("onSpecialEnd",onSpecialEnd);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("省交通厅_高速特别程序结束参数====="+jsonString);
            Object data = ProvinceUtils.httpDatapost(map, "http://59.207.107.18:5000/api/sjtth_gstbcxjs",appId,appKey);
            logger.info("===============省交通厅_高速特别程序结束返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "省交通厅_高速大件收件分发接口" ,  notes="返回参数")
    @RequestMapping(value = "/sjtthdjsjff",method = {RequestMethod.POST})
    public Object sjtthdjsjff(@RequestParam(value = "baseInfoXml",required = false)String baseInfoXml,@RequestParam(value = "attrXml",required = false   )String attrXml,
                              @RequestParam(value = "formXml",required = false)String formXml,@RequestParam(value = "apasPostXml",required = false)String apasPostXml){
        try{
            String appId = "bd6a71d754414e5fa6ac7f7d2b96afac";
            String appKey ="YmQ2YTcxZDc1NDQxNGU1ZmE2YWM3ZjdkMmI5NmFmYWM6MTIzNDU2";
            Map<String, String> map = new HashMap<>();
            map.put("baseInfoXml",baseInfoXml);
            map.put("formXml",formXml);
            map.put("attrXml",attrXml);
            map.put("apasPostXml",apasPostXml);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("省交通厅_高速大件收件分发参数====="+jsonString);
            Object data = ProvinceUtils.httpDatapost(map, "http://59.207.107.18:5000/api/sjtth_djsjff",appId,appKey);
            logger.info("===============省交通厅_高速大件收件分发返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "省交通厅_高速大件特别程序结束接口" ,  notes="返回参数")
    @RequestMapping(value = "/djtbcxjs",method = {RequestMethod.POST})
    public Object djtbcxjs(@RequestParam(value = "attrXml",required = false)String attrXml,
                           @RequestParam(value = "patchEndXml",required = false)String patchEndXml
                           ){
        try{
            String appId = "bd6a71d754414e5fa6ac7f7d2b96afac";
            String appKey ="YmQ2YTcxZDc1NDQxNGU1ZmE2YWM3ZjdkMmI5NmFmYWM6MTIzNDU2";
            Map<String, String> map = new HashMap<>();
            map.put("attrXml",attrXml);
            map.put("patchEndXml",patchEndXml);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("省交通厅_高速大件特别程序结束参数====="+jsonString);
            Object data = ProvinceUtils.httpDatapost(map, "http://59.207.107.18:5000/api/sjtth_djtbcxjs",appId,appKey);
            logger.info("===============省交通厅_高速大件特别程序结束返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    @ApiOperation(value = "省交通厅-涉路收件分发接口" ,  notes="返回参数")
    @RequestMapping(value = "/slsjff",method = {RequestMethod.POST})
    public Object slsjff(@RequestParam(value = "baseInfoXml",required = true)String baseInfoXml,@RequestParam(value = "attrXml",required = false   )String attrXml,
                              @RequestParam(value = "formXml",required = false)String formXml,@RequestParam(value = "apasPostXml",required = false)String apasPostXml){
        try{
            String appId = "bd6a71d754414e5fa6ac7f7d2b96afac";
            String appKey ="YmQ2YTcxZDc1NDQxNGU1ZmE2YWM3ZjdkMmI5NmFmYWM6MTIzNDU2";
            Map<String, String> map = new HashMap<>();
            map.put("baseInfoXml",baseInfoXml);
            map.put("formXml",formXml);
            map.put("attrXml",attrXml);
            map.put("apasPostXml",apasPostXml);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("省交通厅-涉路收件分发参数====="+jsonString);
            Object data = ProvinceUtils.httpDatapost(map, "http://59.207.107.18:5000/api/sjjtj_slsjff",appId,appKey);
            logger.info("===============省交通厅-涉路收件分发返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    @ApiOperation(value = "省交通厅-涉路补齐补正结束接口" ,  notes="返回参数")
    @RequestMapping(value = "/slbqbz",method = {RequestMethod.POST})
    public Object slbqbz(@RequestParam(value = "attrXml",required = true)String attrXml,@RequestParam(value = "patchEndXml",required = true   )String patchEndXml){
        try{
            String appId = "d2c8b7e3dcae4925b51d7b0b14377927";
            String appKey ="ZDJjOGI3ZTNkY2FlNDkyNWI1MWQ3YjBiMTQzNzc5Mjc6MTIzNDU2";
            Map<String, String> map = new HashMap<>();
            map.put("patchEndXml",patchEndXml);
            map.put("attrXml",attrXml);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("省交通厅-涉路补齐补正结束参数====="+jsonString);
            Object data = ProvinceUtils.httpDatapost(map, "http://59.207.107.18:5000/api/sjjtj_slbqbz",appId,appKey);
            logger.info("===============省交通厅-涉路补齐补正结束返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }







}
