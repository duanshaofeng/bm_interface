package com.bm.https.controller.province.gupinban;

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

@Api(description  = "扶贫办接口类")
@RestController
@RequestMapping("/fpbtest")
public class FuPinBanInterfaceTestController {
    private Logger logger = LoggerFactory.getLogger(FuPinBanInterfaceTestController.class);



    @ApiOperation(value = "贫困村基本信息接口" ,  notes="返回参数")
    @RequestMapping(value = "/village",method = {RequestMethod.POST,RequestMethod.GET})
    public Object village(@RequestParam(value = "name",required = true)String name){
        try{
            String appId = "f025c62b819646e486fed86f990b7437";
            String appKey = "ZjAyNWM2MmI4MTk2NDZlNDg2ZmVkODZmOTkwYjc0Mzc6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("name",name);
            map.put("token","");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============贫困村基本信息接口参数=="+jsonString);
            Object data = ProvinceUtils.httpData2(map, "http://59.207.107.18:5000/api/village/get-info-name",appId,appKey);
            logger.info("===============贫困村基本信息接口返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }









}
