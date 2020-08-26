package com.bm.https.controller.province.zhujian;


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

@Api(description  = "河南省住房城乡建设厅接口类")
@RestController
@RequestMapping("/test/zhujian")
public class ZhuJianTestInterface {

    private Logger logger = LoggerFactory.getLogger(ZhuJianTestInterface.class);



    @ApiOperation(value = "公积金运行统计情况查询接口查询" ,  notes="返回参数")
    @RequestMapping(value = "/gjjyxtjcx",method = {RequestMethod.POST})
    public Object gjjyxtjcx(@RequestParam(value = "year",required = true)String year){
        try{
            String appId = "cfb4acc170a54141b2ba54324941f27f";
            String appKey ="Y2ZiNGFjYzE3MGE1NDE0MWIyYmE1NDMyNDk0MWYyN2Y6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("year",year);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("公积金运行统计情况查询接口查询参数:{}",jsonString);
            Object data = ProvinceUtils.httpData2(map, "http://59.207.107.18:5000/api/zjt_gjjyxtjcx",appId,appKey);
            logger.info("公积金运行统计情况查询接口查询返回:{}",data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
