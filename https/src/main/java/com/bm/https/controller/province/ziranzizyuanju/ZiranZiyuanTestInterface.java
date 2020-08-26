package com.bm.https.controller.province.ziranzizyuanju;


import com.alibaba.fastjson.JSONObject;
import com.bm.https.controller.province.zhufang.ZhuFangInterfaceTestController;
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
@RequestMapping("/zrzytest")
public class ZiranZiyuanTestInterface {

    private Logger logger = LoggerFactory.getLogger(ZiranZiyuanTestInterface.class);



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

    @ApiOperation(value = "自然资源厅地役权登记信息查询" ,  notes="返回参数")
    @RequestMapping(value = "/dyqdjxx",method = {RequestMethod.POST})
    public Object dyqdjxx(@RequestParam(value = "BDCZMH",required = true)String BDCZMH,@RequestParam(value = "XYDQLRMC",required = false)String XYDQLRMC,
                         @RequestParam(value = "XYDQLRZJZL",required = false)String XYDQLRZJZL,@RequestParam(value = "XYDQLRZJHM",required = false)String XYDQLRZJHM,
                         @RequestParam(value = "GYDQLRMC",required = false)String GYDQLRMC,@RequestParam(value = "GYDQLRZJZL",required = false)String GYDQLRZJZL,@RequestParam(value = "GYDQLRZJHM",required = false)String GYDQLRZJHM
    ){
        try{
            String appId = "97c158280fbd452198059553d2fde5ad";
            String appKey ="OTdjMTU4MjgwZmJkNDUyMTk4MDU5NTUzZDJmZGU1YWQ6MTIzNDU2";
            Map<String, String> map = new HashMap<>();
            map.put("BDCZMH",BDCZMH);
            map.put("XYDQLRMC",XYDQLRMC);
            map.put("MM","888888");
            map.put("XYDQLRZJZL",XYDQLRZJZL);
            map.put("XYDQLRZJHM",XYDQLRZJHM);
            map.put("GYDQLRMC",GYDQLRMC);
            map.put("GYDQLRZJZL",GYDQLRZJZL);
            map.put("GYDQLRZJHM",GYDQLRZJHM);
            map.put("YHM","00000047");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("自然资源厅地役权登记信息查询参数====="+jsonString);
            Object data = ProvinceUtils.httpDatapost(map, "http://59.207.107.18:5000/api/basequery/dyqdjxx",appId,appKey);
            logger.info("===============自然资源厅地役权登记信息查询返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @ApiOperation(value = "自然资源厅预告登记信息查询" ,  notes="返回参数")
    @RequestMapping(value = "/ygdjxx",method = {RequestMethod.POST})
    public Object ygdjxx(@RequestParam(value = "QLRMC",required = true)String QLRMC,@RequestParam(value = "BDCZMH",required = true)String BDCZMH,
                          @RequestParam(value = "QLRZJHM",required = true)String QLRZJHM,@RequestParam(value = "BDCDYH",required = false)String BDCDYH,
                          @RequestParam(value = "QLRZJZL",required = true)String QLRZJZL
    ){
        try{
            String appId = "e367b7bb731e479fb33b9942a164951b";
            String appKey ="ZTM2N2I3YmI3MzFlNDc5ZmIzM2I5OTQyYTE2NDk1MWI6MTIzNDU2";
            Map<String, String> map = new HashMap<>();
            map.put("QLRMC",QLRMC);
            map.put("BDCZMH",BDCZMH);
            map.put("MM","888888");
            map.put("QLRZJHM",QLRZJHM);
            map.put("BDCDYH",BDCDYH);
            map.put("QLRZJZL",QLRZJZL);

            map.put("YHM","00000047");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("自然资源厅预告登记信息查询参数====="+jsonString);
            Object data = ProvinceUtils.httpDatapost(map, "http://59.207.107.18:5000/api/basequery/ygdjxx",appId,appKey);
            logger.info("===============自然资源厅预告登记信息查询返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "自然资源厅抵押登记信息查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/dydjxx",method = {RequestMethod.POST})
    public Object dydjxx(@RequestParam(value = "BDCZMH",required = true)String BDCZMH,@RequestParam(value = "DYRMC",required = true)String DYRMC,
                         @RequestParam(value = "DYRZJZL",required = true)String DYRZJZL,@RequestParam(value = "DYRZJHM",required = true)String DYRZJHM,
                         @RequestParam(value = "BDCDYH",required = false)String BDCDYH
    ){
        try{
            String appId = "e367b7bb731e479fb33b9942a164951b";
            String appKey ="ZTM2N2I3YmI3MzFlNDc5ZmIzM2I5OTQyYTE2NDk1MWI6MTIzNDU2";
            Map<String, String> map = new HashMap<>();
            map.put("BDCDYH",BDCDYH);
            map.put("DYRZJZL",DYRZJZL);
            map.put("MM","888888");
            map.put("DYRZJHM",DYRZJHM);
            map.put("DYRMC",DYRMC);
            map.put("BDCZMH",BDCZMH);

            map.put("YHM","00000047");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("自然资源厅抵押登记信息查询接口参数====="+jsonString);
            Object data = ProvinceUtils.httpDatapost(map, "http://59.207.107.18:5000/api/basequery/dydjxx",appId,appKey);
            logger.info("===============自然资源厅抵押登记信息查询接口返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    @ApiOperation(value = "自然资源厅查封登记信息查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/cfdjxx",method = {RequestMethod.POST})
    public Object cfdjxx(@RequestParam(value = "CFWH",required = true)String CFWH,@RequestParam(value = "BDCDYH",required = false)String BDCDYH

    ){
        try{
            String appId = "97c158280fbd452198059553d2fde5ad";
            String appKey ="OTdjMTU4MjgwZmJkNDUyMTk4MDU5NTUzZDJmZGU1YWQ6MTIzNDU2";
            Map<String, String> map = new HashMap<>();
            map.put("BDCDYH",BDCDYH);
            map.put("CFWH",CFWH);
            map.put("MM","888888");
            map.put("YHM","00000047");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("自然资源厅查封登记信息查询接口参数====="+jsonString);
            Object data = ProvinceUtils.httpDatapost(map, "http://59.207.107.18:5000/api/basequery/cfdjxx",appId,appKey);
            logger.info("===============自然资源厅查封登记信息查询接口返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "自然资源厅异议登记信息查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/yydjxx",method = {RequestMethod.POST})
    public Object yydjxx(@RequestParam(value = "BDCZMH",required = true)String BDCZMH,@RequestParam(value = "BDCDYH",required = false)String BDCDYH
    ){
        try{
            String appId = "e367b7bb731e479fb33b9942a164951b";
            String appKey ="ZTM2N2I3YmI3MzFlNDc5ZmIzM2I5OTQyYTE2NDk1MWI6MTIzNDU2";
            Map<String, String> map = new HashMap<>();
            map.put("BDCDYH",BDCDYH);
            map.put("BDCZMH",BDCZMH);
            map.put("MM","888888");
            map.put("YHM","00000047");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("自然资源厅异议登记信息查询接口参数====="+jsonString);
            Object data = ProvinceUtils.httpDatapost(map, "http://59.207.107.18:5000/api/basequery/yydjxx",appId,appKey);
            logger.info("===============自然资源厅异议登记信息查询接口返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "自然资源厅不动产权登记信息查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/bdcqdjxx",method = {RequestMethod.POST})
    public Object bdcqdjxx(@RequestParam(value = "BDCQZH",required = true)String BDCQZH,
                           @RequestParam(value = "QLRMC",required = true)String QLRMC,
                           @RequestParam(value = "QLRZJZL",required = true)String QLRZJZL,
                           @RequestParam(value = "QLRZJHM",required = true)String QLRZJHM,
                           @RequestParam(value = "BDCDYH",required = false)String BDCDYH
    ){
        try{
            String appId = "97c158280fbd452198059553d2fde5ad";
            String appKey ="OTdjMTU4MjgwZmJkNDUyMTk4MDU5NTUzZDJmZGU1YWQ6MTIzNDU2";
            Map<String, String> map = new HashMap<>();
            map.put("BDCDYH",BDCDYH);
            map.put("BDCQZH",BDCQZH);
            map.put("QLRMC",QLRMC);
            map.put("QLRZJZL",QLRZJZL);
            map.put("QLRZJHM",QLRZJHM);
            map.put("MM","888888");
            map.put("YHM","00000047");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("自然资源厅不动产权登记信息查询接口参数====="+jsonString);
            Object data = ProvinceUtils.httpDatapost(map, "http://59.207.107.18:5000/api/basequery/bdcqdjxx",appId,appKey);
            logger.info("===============自然资源厅不动产权登记信息查询接口返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
