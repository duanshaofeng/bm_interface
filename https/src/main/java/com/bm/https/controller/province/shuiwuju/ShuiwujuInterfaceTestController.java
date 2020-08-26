package com.bm.https.controller.province.shuiwuju;

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

@Api(description  = "税务局测试接口类")
@RestController
@RequestMapping("/test/shuiwuju")
public class ShuiwujuInterfaceTestController {
    private Logger logger = LoggerFactory.getLogger(ShuiwujuInterfaceTestController.class);



    @ApiOperation(value = "企业信用评价接口" ,  notes="返回参数")
    @RequestMapping(value = "/qyxypjInfo",method = {RequestMethod.POST,RequestMethod.GET})
    public Object qyxypjInfo(@RequestParam(value = "SHXYDM",required = true)String SHXYDM,
                             @RequestParam(value = "NSRMC",required = false)String NSRMC){
        Object data = null;
        try{
            String appId = "79161eaef873494cb8cbef7cb130c8f2";
            String appKey = "NzkxNjFlYWVmODczNDk0Y2I4Y2JlZjdjYjEzMGM4ZjI6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("shxydm",SHXYDM);
            map.put("nsrmc",NSRMC);
            map.put("methodName","/getQyxypjInfo/1.0.0");
            map.put("access_key_secret","92d2841414b1730a6826974bf98461be");
            map.put("access_key_id","59b22897-061f-4717-9ec1-121afad5b60c");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============企业信用评价接口参数:{}",jsonString);
            data = ProvinceUtils.httpData2(map, "http://59.207.107.18:5000/api/getQyxypjInfo",appId,appKey);
            logger.info("===============企业信用评价接口返回:{}",data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "企业违法违章信息接口" ,  notes="返回参数")
    @RequestMapping(value = "/gxptqywfwzxx",method = {RequestMethod.POST,RequestMethod.GET})
    public Object gxptqywfwzxx(@RequestParam(value = "mz",required = true)String mz,
                               @RequestParam(value = "qymc",required = true)String qymc,
                               @RequestParam(value = "fddbr",required = true)String fddbr,
                               @RequestParam(value = "xb",required = true)String xb,
                               @RequestParam(value = "yxqz",required = true)String yxqz,
                               @RequestParam(value = "shxydm",required = true)String shxydm,
                               @RequestParam(value = "sfzhm",required = true)String sfzhm,
                               @RequestParam(value = "yxqq",required = true)String yxqq){
        Object data = null;
        try{
            String appId = "79161eaef873494cb8cbef7cb130c8f2";
            String appKey = "NzkxNjFlYWVmODczNDk0Y2I4Y2JlZjdjYjEzMGM4ZjI6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("mz",mz);
            map.put("qymc",qymc);
            map.put("fddbr",fddbr);
            map.put("xb",xb);
            map.put("yxqz",yxqz);
            map.put("shxydm",shxydm);
            map.put("sfzhm",sfzhm);
            map.put("yxqq",yxqq);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============企业违法违章信息接口参数:{}",jsonString);
            data = ProvinceUtils.httpData2(map, "http://59.207.107.18:5000/api/gxptqywfwzxx",appId,appKey);
            logger.info("===============企业违法违章信息接口返回:{}",data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "行政许可信息接口" ,  notes="返回参数")
    @RequestMapping(value = "/getXzxkInfo",method = {RequestMethod.POST,RequestMethod.GET})
    public Object getXzxkInfo(@RequestParam(value = "nsrmc",required = false)String nsrmc,
                               @RequestParam(value = "shxydm",required = true)String shxydm){
        Object data = null;
        try{
            String appId = "79161eaef873494cb8cbef7cb130c8f2";
            String appKey = "NzkxNjFlYWVmODczNDk0Y2I4Y2JlZjdjYjEzMGM4ZjI6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("nsrmc",nsrmc);
            map.put("shxydm",shxydm);
            map.put("methodName","/getXzxkInfo/1.0.0");
            map.put("access_key_secret","92d2841414b1730a6826974bf98461be");
            map.put("access_key_id","59b22897-061f-4717-9ec1-121afad5b60c");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============行政许可信息接口接口参数:{}",jsonString);
            data = ProvinceUtils.httpData2(map, "http://59.207.107.18:5000/api/getXzxkInfo",appId,appKey);
            logger.info("===============行政许可信息接口接口返回:{}",data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }




    @ApiOperation(value = "行政处罚信息接口" ,  notes="返回参数")
    @RequestMapping(value = "/getXzcfIndo",method = {RequestMethod.POST,RequestMethod.GET})
    public Object getXzcfIndo(@RequestParam(value = "bcfrmc",required = false)String bcfrmc,
                              @RequestParam(value = "zjhm",required = false)String zjhm,
                              @RequestParam(value = "shxydm",required = true)String shxydm){
        Object data = null;
        try{
            String appId = "79161eaef873494cb8cbef7cb130c8f2";
            String appKey = "NzkxNjFlYWVmODczNDk0Y2I4Y2JlZjdjYjEzMGM4ZjI6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("bcfrmc",bcfrmc);
            map.put("shxydm",shxydm);
            map.put("zjhm",zjhm);
            map.put("methodName","/getXzcfInfo/1.0.0");
            map.put("access_key_secret","92d2841414b1730a6826974bf98461be");
            map.put("access_key_id","59b22897-061f-4717-9ec1-121afad5b60c");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============行政处罚信息接口参数:{}",jsonString);
            data = ProvinceUtils.httpData2(map, "http://59.207.107.18:5000/api/getXzxkInfo",appId,appKey);
            logger.info("===============行政处罚信息接口返回:{}",data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "授权信息录入接口" ,  notes="返回参数")
    @RequestMapping(value = "/sqxxlr",method = {RequestMethod.POST,RequestMethod.GET})
    public Object sqxxlr(@RequestParam(value = "nsrsbh",required = true)String nsrsbh,
                              @RequestParam(value = "shxydm",required = true)String shxydm,
                         @RequestParam(value = "yxsjq",required = true)String yxsjq,
                         @RequestParam(value = "nsrmc",required = true)String nsrmc,
                         @RequestParam(value = "yxsjz",required = true)String yxsjz,
                         @RequestParam(value = "fddbrxm",required = true)String fddbrxm,
                         @RequestParam(value = "sjqxz",required = true)String sjqxz,
                         @RequestParam(value = "xtbm",required = true)String xtbm,
                         @RequestParam(value = "sjqxq",required = true)String sjqxq,
                         @RequestParam(value = "qmxx",required = true)String qmxx,
                         @RequestParam(value = "fddbrsfzjhm",required = true)String fddbrsfzjhm,
                         @RequestParam(value = "sqrmc",required = true)String sqrmc,
                         @RequestParam(value = "bmbm",required = true)String bmbm,
                         @RequestParam(value = "ckxx",required = false)String ckxx){
        Object data = null;
        try{
            String appId = "79161eaef873494cb8cbef7cb130c8f2";
            String appKey = "NzkxNjFlYWVmODczNDk0Y2I4Y2JlZjdjYjEzMGM4ZjI6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("nsrsbh",nsrsbh);
            map.put("yxsjq",yxsjq);
            map.put("nsrmc",nsrmc);
            map.put("yxsjz",yxsjz);
            map.put("fddbrxm",fddbrxm);
            map.put("sqrmc",sqrmc);
            map.put("sjqxz",sjqxz);
            map.put("xtbm",xtbm);
            map.put("sjqxq",sjqxq);
            map.put("qmxx",qmxx);
            map.put("fddbrsfzjhm",fddbrsfzjhm);
            map.put("bmbm",bmbm);
            map.put("ckxx",ckxx);
            map.put("shxydm",shxydm);
            map.put("methodName","/addNsrsqInfo/1.0.0");
            map.put("access_key_secret","92d2841414b1730a6826974bf98461be");
            map.put("access_key_id","59b22897-061f-4717-9ec1-121afad5b60c");
            map.put("caxx","all");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============授权信息录入接口参数:{}",jsonString);
            data = ProvinceUtils.httpData2(map, "http://59.207.107.18:5000/api/sqxxlr",appId,appKey);
            logger.info("===============授权信息录入接口返回:{}",data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
