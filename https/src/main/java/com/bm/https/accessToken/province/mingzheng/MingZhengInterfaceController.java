package com.bm.https.accessToken.province.mingzheng;

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

@Api(description  = "民政局接口类")
@RestController
@RequestMapping("/mzj")
public class MingZhengInterfaceController {
    private Logger logger = LoggerFactory.getLogger(MingZhengInterfaceController.class);



    @ApiOperation(value = "民政局婚姻证明查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/hunyin",method = {RequestMethod.POST,RequestMethod.GET})
    public Object hunyinInfo(@RequestParam(value = "manid",required = false)String manid,@RequestParam(value = "womanid",required = false)String womanid){
        Object data = null;
        try{
            String appId = "d53f844edcc147b3a9be2eef2479848c";
            String appKey = "ZDUzZjg0NGVkY2MxNDdiM2E5YmUyZWVmMjQ3OTg0OGM6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("manIdcard",manid);
            map.put("womanIdcard",womanid);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============民政婚姻登记信息参数：{}",jsonString);
            data = ProvinceUtils.httpData("GET", map, "http://59.207.107.18:5000/api/hyxxcx",appId,appKey);
            logger.info("===============民政婚姻登记信息返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }









}
