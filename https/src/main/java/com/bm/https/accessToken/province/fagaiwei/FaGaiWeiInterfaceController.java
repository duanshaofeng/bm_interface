package com.bm.https.accessToken.province.fagaiwei;

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

@Api(description  = "发改委接口类")
@RestController
@RequestMapping("/fgw")
public class FaGaiWeiInterfaceController {
    private Logger logger = LoggerFactory.getLogger(FaGaiWeiInterfaceController.class);



    @ApiOperation(value = "个人信用目录查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/xymlInfo",method = {RequestMethod.POST,RequestMethod.GET})
    public Object hunyinInfo(@RequestParam(value = "text",required = false)String text){
        try{
            String appId = "d53f844edcc147b3a9be2eef2479848c";
            String appKey =
                    "ZDUzZjg0NGVkY2MxNDdiM2E5YmUyZWVmMjQ3OTg0OGM6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("text",text);
            String jsonString = JSONObject.toJSONString(map);
            logger.info(jsonString);
            Object data = ProvinceUtils.httpData("GET", map, "http://59.207.107.18:5000/api/setComplain",appId,appKey);

            logger.info("===============个人信用目录查询返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }









}
