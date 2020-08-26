package com.bm.https.controller.province.dashuju;

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

@Api(description  = "大数据局接口类")
@RestController
@RequestMapping("/dashujujutest")
public class DashujuInterfaceTestController {
    private Logger logger = LoggerFactory.getLogger(DashujuInterfaceTestController.class);



    @ApiOperation(value = "企业基本信息查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/qiyeinfo",method = {RequestMethod.POST})
    public Object qiyeinfo(@RequestParam(value = "uniscid",required = true)String uniscid,
                               @RequestParam(value = "entname",required = true)String entname
                               ){
        Object data = null;
        try{
            String appId = "3e9f2898dfed45b4a4db4e81bf25db10";
            String appKey = "M2U5ZjI4OThkZmVkNDViNGE0ZGI0ZTgxYmYyNWRiMTA6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("uniscid",uniscid);
            map.put("entname",entname);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============省大数据局_企业基本信息查询接口 参数:{}",jsonString);
            data = ProvinceUtils.httpDatapostjson(jsonString, "http://59.207.107.18:5000/api/gszj/httpproxy",appId,appKey);
            logger.info("===============省大数据局_企业基本信息查询接口 返回:{}",data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }









}
