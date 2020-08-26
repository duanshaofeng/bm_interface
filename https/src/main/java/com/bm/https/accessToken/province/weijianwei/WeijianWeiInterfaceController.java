package com.bm.https.accessToken.province.weijianwei;

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

@Api(description  = "卫健委接口类")
@RestController
@RequestMapping("/wjw")
public class WeijianWeiInterfaceController {
    private Logger logger = LoggerFactory.getLogger(WeijianWeiInterfaceController.class);

    @ApiOperation(value = "计划生育查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/jhsyInfo",method = {RequestMethod.POST})
    public Object jhsyInfo(@RequestParam(value = "xml",required = false)String xml){
        try{
            String appId = "d53f844edcc147b3a9be2eef2479848c";
            String appKey = "ZDUzZjg0NGVkY2MxNDdiM2E5YmUyZWVmMjQ3OTg0OGM6MTIzNDU2";
            Map<String, String> map = new HashMap<>();
            map.put("xml",xml);
            map.put("username","GJCSZM");
            map.put("password","888888");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============计划生育查询接口参数"+jsonString);
            Object data = ProvinceUtils.httpDatapost( map, "http://59.207.107.18:5000/api/getCsrkInfo",appId,appKey);
            logger.info("===============计划生育查询接口返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    @ApiOperation(value = "全员人口数据数据接口" ,  notes="返回参数")
    @RequestMapping(value = "/qyrkService",method = {RequestMethod.POST})
    public Object qyrkService(@RequestParam(value = "xml",required = false)String xml){
        try{
            String appId = "d53f844edcc147b3a9be2eef2479848c";
            String appKey = "ZDUzZjg0NGVkY2MxNDdiM2E5YmUyZWVmMjQ3OTg0OGM6MTIzNDU2";
            Map<String, String> map = new HashMap<>();
            map.put("xmlInPut",xml);
            map.put("username","wjw");
            map.put("password","123456");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============全员人口数据数据接口参数"+jsonString);
            Object data = ProvinceUtils.httpDatapost( map, "http://59.207.107.18:5000/api/qyrkService",appId,appKey);
            logger.info("===============全员人口数据数据接口返回值=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }








}
