package com.bm.https.controller.province.minzhengbu;

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

@Api(description  = "民政部接口类")
@RestController
@RequestMapping("/mzbtest")
public class MingZhengBuInterfaceTestController {
    private Logger logger = LoggerFactory.getLogger(MingZhengBuInterfaceTestController.class);



    @ApiOperation(value = "民政部基金会名称变更登记记录查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/jjhmcbg",method = {RequestMethod.POST})
    public Object jjhmcbg(@RequestParam(value = "tyxydm",required = true)String tyxydm){
        try{
            String appId = "d53f844edcc147b3a9be2eef2479848c";
           // String appId = "d25203f68aed4bfe969f50f67196d88e";
            String appKey = "ZDUzZjg0NGVkY2MxNDdiM2E5YmUyZWVmMjQ3OTg0OGM6MTIzNDU2";
            //String appKey = "ZDI1MjAzZjY4YWVkNGJmZTk2OWY1MGY2NzE5NmQ4OGU6MTIzNDU2";
            Map<String, String> map = new HashMap<>();
            map.put("tyxydm",tyxydm);
            map.put("access_key","hngxpt");
            map.put("format","json");
            map.put("timestamp",System.currentTimeMillis()+"");
            map.put("sign","aaaa");
            String jsonString = JSONObject.toJSONString(map);
            map.put("biz_content",jsonString);
            logger.info("民政部基金会名称变更登记记录查询接口 参数：{}",jsonString);
            Object data = ProvinceUtils.httpDatapost( map, "http://59.207.107.18:5000/api/mzb_jjhmcbg",appId,appKey);
            logger.info("民政部基金会名称变更登记记录查询接口返回 ：{}",data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }




    @ApiOperation(value = "民办非企业名称变更登记详情查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/qybgdjlc",method = {RequestMethod.POST})
    public Object qybgdjlc(@RequestParam(value = "tyxydm",required = true)String tyxydm){
        try{
            String appId = "d25203f68aed4bfe969f50f67196d88e";
            // String appId = "d25203f68aed4bfe969f50f67196d88e";
            String appKey = "ZDI1MjAzZjY4YWVkNGJmZTk2OWY1MGY2NzE5NmQ4OGU6MTIzNDU2";
            Map<String, String> map = new HashMap<>();
            map.put("tyxydm",tyxydm);
            map.put("access_key","hngxpt");
            map.put("format","json");
            map.put("timestamp",System.currentTimeMillis()+"");
            map.put("sign","aaaa");
            String jsonString = JSONObject.toJSONString(map);
            map.put("biz_content",jsonString);

            logger.info("民办非企业名称变更登记详情查询接口 参数：{}",jsonString);
            Object data = ProvinceUtils.httpDatapost( map, "http://59.207.107.18:5000/api/mzb_qybgdjlc",appId,appKey);
            logger.info("民办非企业名称变更登记详情查询接口 返回 ：{}",data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "收养登记证信息（国内）查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/sydjxxgncx",method = {RequestMethod.POST})
    public Object sydjxxgncx(@RequestParam(value = "querytype",required = true)String querytype,
                             @RequestParam(value = "cert_num",required = true)String cert_num,
                             @RequestParam(value = "name",required = false)String name){
        try{
            String appId = "a1c838dd43e945119ce756a8d8f5b0d8";
            String appKey = "YTFjODM4ZGQ0M2U5NDUxMTljZTc1NmE4ZDhmNWIwZDg6MTIzNDU2";
            Map<String, String> map = new HashMap<>();
            map.put("querytype",querytype);
            map.put("cert_num",cert_num);
            map.put("name",name);
            map.put("access_key","hngxpt");
            map.put("format","json");
            map.put("timestamp",System.currentTimeMillis()+"");
            map.put("sign","aaaa");
            String jsonString = JSONObject.toJSONString(map);
            map.put("biz_content",jsonString);

            logger.info("收养登记证信息（国内）查询接口 参数：{}",jsonString);
            Object data = ProvinceUtils.httpDatapost( map, "http://59.207.107.18:5000/api/sydjxxgncx",appId,appKey);
            logger.info("收养登记证信息（国内）查询接口 返回 ：{}",data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    @ApiOperation(value = "婚姻登记信息核验(双方)" ,  notes="返回参数")
    @RequestMapping(value = "/hydjxxhysf",method = {RequestMethod.POST})
    public Object hydjxxhysf(@RequestParam(value = "cert_num_man",required = true)String cert_num_man,
                             @RequestParam(value = "name_woman",required = true)String name_woman,
                             @RequestParam(value = "name_man",required = true)String name_man,
                             @RequestParam(value = "cert_num_woman",required = true)String cert_num_woman){
        try{
            //String appId = "dbc5ae8b1d694d649b940417673065a5";
            //String appKey = "ZGJjNWFlOGIxZDY5NGQ2NDliOTQwNDE3NjczMDY1YTU6MTIzNDU2";
            String appId = "d53f844edcc147b3a9be2eef2479848c";
            String appKey = "ZDUzZjg0NGVkY2MxNDdiM2E5YmUyZWVmMjQ3OTg0OGM6MTIzNDU2";
            Map<String, String> map = new HashMap<>();
            map.put("cert_num_man",cert_num_man);
            map.put("name_woman",name_woman);
            map.put("name_man",name_man);
            map.put("cert_num_woman",cert_num_woman);
            map.put("clientName","中心");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("婚姻登记信息核验(双方) 参数：{}",jsonString);
            Object data = ProvinceUtils.httpDatapost( map, "http://59.207.107.18:5000/api/mzb_hydjxxhysf",appId,appKey);
            logger.info("婚姻登记信息核验(双方)查询接口 返回 ：{}",data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "民政部_婚姻登记信息核验(个人)" ,  notes="返回参数")
    @RequestMapping(value = "/hydjxxhygrf",method = {RequestMethod.POST})
    public Object hydjxxhygrf(@RequestParam(value = "cert_num_man",required = true)String cert_num_man,
                             @RequestParam(value = "name_man",required = true)String name_man){
        try{
            String appId = "dbc5ae8b1d694d649b940417673065a5";
            String appKey = "ZGJjNWFlOGIxZDY5NGQ2NDliOTQwNDE3NjczMDY1YTU6MTIzNDU2";
            //String appId = "d53f844edcc147b3a9be2eef2479848c";
            // String appKey = "ZDUzZjg0NGVkY2MxNDdiM2E5YmUyZWVmMjQ3OTg0OGM6MTIzNDU2";
            Map<String, String> map = new HashMap<>();
            map.put("cert_num_man",cert_num_man);
            map.put("name_man",name_man);
            //map.put("clientName","中心");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("民政部_婚姻登记信息核验(个人) 参数：{}",jsonString);
                Object data = ProvinceUtils.httpDatapost( map, "http://59.207.107.18:5000/api/mzb_hydjxxhygr",appId,appKey);
            logger.info("民政部_婚姻登记信息核验(个人) 返回 ：{}",data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    @ApiOperation(value = "全国留守儿童和困境儿童信息查询服务" ,  notes="返回参数")
    @RequestMapping(value = "/lsetxx",method = {RequestMethod.POST})
    public Object lsetxx(@RequestParam(value = "child_idcard",required = true)String child_idcard,
                              @RequestParam(value = "child_name",required = false)String child_name){
        try{
            String appId = "39ef144abf6e4ef6945a97d75a8083c2";
            String appKey = "MzllZjE0NGFiZjZlNGVmNjk0NWE5N2Q3NWE4MDgzYzI6MTIzNDU2";
            Map<String, String> map = new HashMap<>();
            map.put("child_idcard",child_idcard);
            map.put("child_name",child_name);
            map.put("clientName","中心");
            map.put("Authorization","中心");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("全国留守儿童和困境儿童信息查询服务 参数：{}",jsonString);
            Object data = ProvinceUtils.httpDatapost( map, "http://59.207.107.18:5000/api/mzb_lsetxx",appId,appKey);
            logger.info("全国留守儿童和困境儿童信息查询服务 返回 ：{}",data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "民办非企业单位登记证书查询" ,  notes="返回参数")
    @RequestMapping(value = "/mzb_mbfqyds",method = {RequestMethod.POST})
    public Object mzb_mbfqyds(@RequestParam(value = "usc_code",required = true)String usc_code,
                         @RequestParam(value = "org_name",required = true)String org_name,
                              @RequestParam(value = "clientName",required = true)String clientName,
                              @RequestParam(value = "start",required = true)String start,
                              @RequestParam(value = "limit",required = true)String limit){
        try{
            String appId = "d25203f68aed4bfe969f50f67196d88e";
            String appKey = "ZDI1MjAzZjY4YWVkNGJmZTk2OWY1MGY2NzE5NmQ4OGU6MTIzNDU2";
            Map<String, String> map = new HashMap<>();
            map.put("usc_code",usc_code);
            map.put("org_name",org_name);
            map.put("clientName",clientName);
            map.put("start",start);
            map.put("limit",limit);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("民办非企业单位登记证书查询 参数：{}",jsonString);
            Object data = ProvinceUtils.httpDatapost( map, "http://59.207.107.18:5000/api/mzb_mbfqyds",appId,appKey);
            logger.info("民办非企业单位登记证书查询 返回 ：{}",data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
