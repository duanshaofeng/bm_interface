package com.bm.https.accessToken.province.ziranzizyuanju;


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

@Api(description  = "河南省自然资源确权登记局接口类")
@RestController
@RequestMapping("/zrzyj")
public class ZiranZiyuanInterface {

    private Logger logger = LoggerFactory.getLogger(ZiranZiyuanInterface.class);



    @ApiOperation(value = "自然资源厅不动产登记房地产权登记信息查询" ,  notes="返回参数")
    @RequestMapping(value = "/FDCQCX",method = {RequestMethod.POST})
    public Object FDCQCX(@RequestParam(value = "QLRZJHM",required = true)String QLRZJHM,@RequestParam(value = "BDCQZH",required = true)String BDCQZH,
                         @RequestParam(value = "QLRMC",required = true)String QLRMC,@RequestParam(value = "QXDM",required = false)String QXDM,
                         @RequestParam(value = "MM",required = false)String MM,@RequestParam(value = "QLRZJZL",required = true)String QLRZJZL,@RequestParam(value = "YHM",required = false)String YHM
                         ){
        try{
            String appId = "f7d6a5a0fe3243419f0c649d0d72aed6";
            String appKey ="ZjdkNmE1YTBmZTMyNDM0MTlmMGM2NDlkMGQ3MmFlZDY6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("QLRZJHM",QLRZJHM);
            map.put("QLRMC",QLRMC);
            map.put("MM","888888");
            map.put("BDCQZH",BDCQZH);
            map.put("QXDM",QXDM);
            map.put("QLRZJZL",QLRZJZL);
            map.put("YHM","00000047");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("自然资源厅不动产登记房地产权登记信息查询参数====="+jsonString);
            Object data = ProvinceUtils.httpData2(map, "http://59.207.107.18:5000/api/FDCQCX",appId,appKey);
            logger.info("===============自然资源厅不动产登记房地产权登记信息查询返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }




}
