package com.bm.https.controller.province.rensheting;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bm.https.untils.ProvinceUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description  = "人社厅接口类")
@RestController
@RequestMapping("/renshetest")
public class RenSheTingInterfaceTestController {
    private Logger logger = LoggerFactory.getLogger(RenSheTingInterfaceTestController.class);



    @ApiOperation(value = "人社厅社保信息查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/shehuibaoxianInfo",method = {RequestMethod.POST})
    public Object shehuibaoxianInfo(
                                    @RequestParam(value = "idcard",required = false) String idcard,
                                    @RequestParam(value = "name",required = false) String name,
                                    @RequestParam(value = "page",required = false) String page,
                                    @RequestParam(value = "size",required = false) String size){
        try{
            String appId = "41cd6ff5d79049b7a884dd2607d55475";
            String appKey = "NDFjZDZmZjVkNzkwNDliN2E4ODRkZDI2MDdkNTU0NzU6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("req.token","cd892e19db584f09a9604099db0504e7");
            map.put("req.subscribeId","6daf341fc8a34573885cb7fe082517bc");
            map.put("req.userId","a81013f8571e4a77801d34914f49c207");
            Boolean flag = true;
            if(!StringUtils.isEmpty(idcard) && !StringUtils.isEmpty(name)){
                map.put("params[0].fieldCode","SFZH");
                map.put("params[0].operateCode","=");
                map.put("params[0].parameterValue",idcard);

                map.put("params[1].fieldCode","XM");
                map.put("params[1].operateCode","=");
                map.put("params[1].parameterValue",name);
                flag = false;
            }
            if(flag){
                if(!StringUtils.isEmpty(idcard)){
                    map.put("params[0].fieldCode","SFZH");
                    map.put("params[0].operateCode","=");
                    map.put("params[0].parameterValue",idcard);
                }

                if(!StringUtils.isEmpty(name)){
                    map.put("params[0].fieldCode","XM");
                    map.put("params[0].operateCode","=");
                    map.put("params[0].parameterValue",name);
                }
            }
            if(!StringUtils.isEmpty(page)){
                map.put("page.index",page);
            }else {
                map.put("page.index","1");
            }
            if(!StringUtils.isEmpty(size)){
                map.put("page.rows",size);
            }else {
                map.put("page.rows","10");
            }

            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============人社厅社保信息查询参数==：{}",jsonString);
            Object data = ProvinceUtils.httpData2(map , "http://59.207.107.18:5000/api/shbxcbxxcx",appId,appKey);
            if(data!=null){
                JSONObject jsonObject = JSONObject.parseObject(data.toString());
                JSONObject data1 = JSONObject.parseObject(jsonObject.get("data").toString());
                JSONObject data2 = data1.getJSONObject("data");
                if(data2.containsKey("columns")){
                    data2.remove("columns") ;
                }
                logger.info("===============人社厅社保信息返回信息：{}",data2);
                return  data2;

            }
            logger.info("===============人社厅社保信息结束");
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "城乡居民基本养老保险待遇领取信息查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/CXJMJBYLBXLQInfo",method = {RequestMethod.POST})
    public Object CXJMJBYLBXLQInfo(
            @RequestParam(value = "idcard",required = false) String idcard,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "page",required = false) String page,
            @RequestParam(value = "size",required = false) String size){
        try{
            String appId = "2f2e10bda3534d6abfb8835d317e8d3d";
            String appKey = "MmYyZTEwYmRhMzUzNGQ2YWJmYjg4MzVkMzE3ZThkM2Q6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("req.token","703d7c620f2d46299c2d819601dbb960");
            map.put("req.subscribeId","65ac13816f35432e859c1bd837b6097f");
            map.put("req.userId","fb3c365b2b764565b2f545870a844df9");

            Boolean flag = true;
            if(!StringUtils.isEmpty(idcard) && !StringUtils.isEmpty(name)){
                map.put("params[0].fieldCode","SFZH");
                map.put("params[0].operateCode","=");
                map.put("params[0].parameterValue",idcard);

                map.put("params[1].fieldCode","XM");
                map.put("params[1].operateCode","=");
                map.put("params[1].parameterValue",name);
                flag = false;
            }
            if(flag){
                if(!StringUtils.isEmpty(idcard)){
                    map.put("params[0].fieldCode","SFZH");
                    map.put("params[0].operateCode","=");
                    map.put("params[0].parameterValue",idcard);
                }

                if(!StringUtils.isEmpty(name)){
                    map.put("params[0].fieldCode","XM");
                    map.put("params[0].operateCode","=");
                    map.put("params[0].parameterValue",name);
                }
            }




            if(!StringUtils.isEmpty(page)){
                map.put("page.index",page);
            }else {
                map.put("page.index","1");
            }
            if(!StringUtils.isEmpty(size)){
                map.put("page.rows",size);
            }else {
                map.put("page.rows","10");
            }
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============城乡居民基本养老保险待遇领取信息查询接口查询参数==：{}",jsonString);
            Object data = ProvinceUtils.httpData2(map , "http://59.207.107.18:5000/api/cxjmjbylbxdylqxx",appId,appKey);
            if(data!=null){
                JSONObject jsonObject = JSONObject.parseObject(data.toString());
                JSONObject data1 = JSONObject.parseObject(jsonObject.get("data").toString());
                JSONObject data2 = data1.getJSONObject("data");
                if(data2.containsKey("columns")){
                    data2.remove("columns") ;
                }
                logger.info("===============城乡居民基本养老保险待遇领取信息查询接口返回信息：{}",data2);
                return  data2;

            }
            logger.info("===============城乡居民基本养老保险待遇领取信息查询接口结束");
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "城镇企业职工基本养老保险待遇领取信息查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/CZQYZGJBYLBXDYLQInfo",method = {RequestMethod.POST})
    public Object CZQYZGJBYLBXDYLQInfo(@RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "idcard",required = false) String idcard,
                                       @RequestParam(value = "page",required = false) String page,
                                       @RequestParam(value = "size",required = false) String size){
        try{
            String appId = "2f2e10bda3534d6abfb8835d317e8d3d";
            String appKey = "MmYyZTEwYmRhMzUzNGQ2YWJmYjg4MzVkMzE3ZThkM2Q6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("req.token","1ca833ada750493981fe6e4e80c9806a");
            map.put("req.subscribeId","e5923eb652fb4201a3b53ff77735f659");
            map.put("req.userId","d202407f5c664b35a8090ccdfc831e42");
            Boolean flag = true;
            if(!StringUtils.isEmpty(idcard) && !StringUtils.isEmpty(name)){
                map.put("params[0].fieldCode","AAC002");
                map.put("params[0].operateCode","=");
                map.put("params[0].parameterValue",idcard);

                map.put("params[1].fieldCode","AAC003");
                map.put("params[1].operateCode","=");
                map.put("params[1].parameterValue",name);
                flag = false;
            }
            if(flag){
                if(!StringUtils.isEmpty(idcard)){
                    map.put("params[0].fieldCode","AAC002");
                    map.put("params[0].operateCode","=");
                    map.put("params[0].parameterValue",idcard);
                }

                if(!StringUtils.isEmpty(name)){
                    map.put("params[0].fieldCode","AAC003");
                    map.put("params[0].operateCode","=");
                    map.put("params[0].parameterValue",name);
                }
            }






            if(!StringUtils.isEmpty(page)){
                map.put("page.index",page);
            }else {
                map.put("page.index","1");
            }
            if(!StringUtils.isEmpty(size)){
                map.put("page.rows",size);
            }else {
                map.put("page.rows","10");
            }
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============城镇企业职工基本养老保险待遇领取信息查询接口查询参数==：{}",jsonString);
            Object data = ProvinceUtils.httpData2(map , "http://59.207.107.18:5000/api/dpxPayment",appId,appKey);
            if(data!=null){
                JSONObject jsonObject = JSONObject.parseObject(data.toString());
                JSONObject data1 = JSONObject.parseObject(jsonObject.get("data").toString());
                JSONObject data2 = data1.getJSONObject("data");
                if(data2.containsKey("columns")){
                    data2.remove("columns") ;
                }
                logger.info("===============城镇企业职工基本养老保险待遇领取信息查询接口返回信息：{}",data2);
                return  data2;

            }
            logger.info("===============城镇企业职工基本养老保险待遇领取信息查询接口结束");
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "城镇居民医保参保状态信息查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/CZJMYBCBZTInfo",method = {RequestMethod.POST})
    public Object CZJMYBCBZTInfo(
            @RequestParam(value = "idcard",required = false) String idcard,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "page",required = false) String page,
            @RequestParam(value = "size",required = false) String size){
        try{
            String appId = "2f2e10bda3534d6abfb8835d317e8d3d";
            String appKey = "MmYyZTEwYmRhMzUzNGQ2YWJmYjg4MzVkMzE3ZThkM2Q6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("req.token","b6a7bb45c51449ba854bc0668d5e38a1");
            map.put("req.subscribeId","0bd07ff7a4a24b088dbccc51ec85a956");
            map.put("req.userId","fb3c365b2b764565b2f545870a844df9");

            Boolean flag = true;
            if(!StringUtils.isEmpty(idcard) && !StringUtils.isEmpty(name)){
                map.put("params[0].fieldCode","SHBZHM");
                map.put("params[0].operateCode","=");
                map.put("params[0].parameterValue",idcard);

                map.put("params[1].fieldCode","XM");
                map.put("params[1].operateCode","=");
                map.put("params[1].parameterValue",name);
                flag = false;
            }
            if(flag){
                if(!StringUtils.isEmpty(idcard)){
                    map.put("params[0].fieldCode","SHBZHM");
                    map.put("params[0].operateCode","=");
                    map.put("params[0].parameterValue",idcard);
                }

                if(!StringUtils.isEmpty(name)){
                    map.put("params[0].fieldCode","XM");
                    map.put("params[0].operateCode","=");
                    map.put("params[0].parameterValue",name);
                }
            }



            if(!StringUtils.isEmpty(page)){
                map.put("page.index",page);
            }else {
                map.put("page.index","1");
            }
            if(!StringUtils.isEmpty(size)){
                map.put("page.rows",size);
            }else {
                map.put("page.rows","10");
            }
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============城镇居民医保参保状态信息查询接口查询参数==：{}",jsonString);
            Object data = ProvinceUtils.httpData2(map , "http://59.207.107.18:5000/api/czjmylbxcbzt",appId,appKey);
            if(data!=null){
                JSONObject jsonObject = JSONObject.parseObject(data.toString());
                JSONObject data1 = JSONObject.parseObject(jsonObject.get("data").toString());
                JSONObject data2 = data1.getJSONObject("data");
                if(data2.containsKey("columns")){
                    data2.remove("columns") ;
                }
                logger.info("===============城镇居民医保参保状态信息查询接口返回信息：{}",data2);
                return  data2;

            }
            logger.info("===============城镇居民医保参保状态信息查询接口结束");
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "企业职工养老保险个人缴费信息查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/QYZGYLBXGRJFInfo",method = {RequestMethod.POST})
    public Object QYZGYLBXGRJFInfo(
            @RequestParam(value = "idcard",required = false) String idcard,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "page",required = false) String page,
            @RequestParam(value = "size",required = false) String size){
        try{
            String appId = "2f2e10bda3534d6abfb8835d317e8d3d";
            String appKey = "MmYyZTEwYmRhMzUzNGQ2YWJmYjg4MzVkMzE3ZThkM2Q6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("req.token","6c63fd719dd345e793af8ecf15d73979");
            map.put("req.subscribeId","d241b03cdc0042848e7460e0149a1a94");
            map.put("req.userId","a81013f8571e4a77801d34914f49c207");
            Boolean flag = true;
            if(!StringUtils.isEmpty(idcard) && !StringUtils.isEmpty(name)){
                map.put("params[0].fieldCode","AAC002");
                map.put("params[0].operateCode","=");
                map.put("params[0].parameterValue",idcard);

                map.put("params[1].fieldCode","AACOO3");
                map.put("params[1].operateCode","=");
                map.put("params[1].parameterValue",name);
                flag = false;
            }
            if(flag){
                if(!StringUtils.isEmpty(idcard)){
                    map.put("params[0].fieldCode","AAC002");
                    map.put("params[0].operateCode","=");
                    map.put("params[0].parameterValue",idcard);
                }

                if(!StringUtils.isEmpty(name)){
                    map.put("params[0].fieldCode","AACOO3");
                    map.put("params[0].operateCode","=");
                    map.put("params[0].parameterValue",name);
                }
            }



            if(!StringUtils.isEmpty(page)){
                map.put("page.index",page);
            }else {
                map.put("page.index","1");
            }
            if(!StringUtils.isEmpty(size)){
                map.put("page.rows",size);
            }else {
                map.put("page.rows","10");
            }
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============企业职工养老保险个人缴费信息查询接口查询参数==：{}",jsonString);
            Object data = ProvinceUtils.httpData2(map , "http://59.207.107.18:5000/api/medicalinfo",appId,appKey);
            if(data!=null){
                JSONObject jsonObject = JSONObject.parseObject(data.toString());
                JSONObject data1 = JSONObject.parseObject(jsonObject.get("data").toString());
                JSONObject data2 = data1.getJSONObject("data");
                if(data2.containsKey("columns")){
                    data2.remove("columns") ;
                }
                logger.info("===============企业职工养老保险个人缴费信息查询接口返回信息：{}",data2);
                return  data2;

            }
            logger.info("===============企业职工养老保险个人缴费信息查询接口结束");
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "城乡居民养老保险参保基本信息查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/CXJMYLBXCBInfo",method = {RequestMethod.POST})
    public Object CXJMYLBXCBInfo(
            @RequestParam(value = "idcard",required = false) String idcard,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "page",required = false) String page,
            @RequestParam(value = "size",required = false) String size){
        try{
            String appId = "2f2e10bda3534d6abfb8835d317e8d3d";
            String appKey = "MmYyZTEwYmRhMzUzNGQ2YWJmYjg4MzVkMzE3ZThkM2Q6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("req.token","4220f62556d6439699630d8c578724e5");
            map.put("req.subscribeId","c21b73ddcfdb480cad84efd0bbb7d691");
            map.put("req.userId","fb3c365b2b764565b2f545870a844df9");
            Boolean flag = true;
            if(!StringUtils.isEmpty(idcard) && !StringUtils.isEmpty(name)){
                map.put("params[0].fieldCode","SFZH");
                map.put("params[0].operateCode","=");
                map.put("params[0].parameterValue",idcard);

                map.put("params[1].fieldCode","XM");
                map.put("params[1].operateCode","=");
                map.put("params[1].parameterValue",name);
                flag = false;
            }
            if(flag){
                if(!StringUtils.isEmpty(idcard)){
                    map.put("params[0].fieldCode","SFZH");
                    map.put("params[0].operateCode","=");
                    map.put("params[0].parameterValue",idcard);
                }

                if(!StringUtils.isEmpty(name)){
                    map.put("params[0].fieldCode","XM");
                    map.put("params[0].operateCode","=");
                    map.put("params[0].parameterValue",name);
                }
            }



            if(!StringUtils.isEmpty(page)){
                map.put("page.index",page);
            }else {
                map.put("page.index","1");
            }
            if(!StringUtils.isEmpty(size)){
                map.put("page.rows",size);
            }else {
                map.put("page.rows","10");
            }
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============城乡居民养老保险参保基本信息查询接口查询参数==：{}",jsonString);
            Object data = ProvinceUtils.httpData2(map , "http://59.207.107.18:5000/api/cxjmylbxjbxx",appId,appKey);
            if(data!=null){
                JSONObject jsonObject = JSONObject.parseObject(data.toString());
                JSONObject data1 = JSONObject.parseObject(jsonObject.get("data").toString());
                JSONObject data2 = data1.getJSONObject("data");
                if(data2.containsKey("columns")){
                    data2.remove("columns") ;
                }
                logger.info("===============城乡居民养老保险参保基本信息查询接口返回信息：{}",data2);
                return  data2;

            }
            logger.info("===============城乡居民养老保险参保基本信息查询接口结束");
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "城乡居民养老保险缴费信息查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/CXJMYLBXJFInfo",method = {RequestMethod.POST})
    public Object CXJMYLBXJFInfo(
            @RequestParam(value = "idcard",required = false) String idcard,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "page",required = false) String page,
            @RequestParam(value = "size",required = false) String size){
        try{
            String appId = "2f2e10bda3534d6abfb8835d317e8d3d";
            String appKey = "MmYyZTEwYmRhMzUzNGQ2YWJmYjg4MzVkMzE3ZThkM2Q6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("req.token","9b9595559f104453a835b2a162ddf3aa");
            map.put("req.subscribeId","a1380a19c6c744d2968f7c46c3284680");
            map.put("req.userId","fb3c365b2b764565b2f545870a844df9");
            Boolean flag = true;
            if(!StringUtils.isEmpty(idcard) && !StringUtils.isEmpty(name)){
                map.put("params[0].fieldCode","SFZH");
                map.put("params[0].operateCode","=");
                map.put("params[0].parameterValue",idcard);

                map.put("params[1].fieldCode","XM");
                map.put("params[1].operateCode","=");
                map.put("params[1].parameterValue",name);
                flag = false;
            }
            if(flag){
                if(!StringUtils.isEmpty(idcard)){
                    map.put("params[0].fieldCode","SFZH");
                    map.put("params[0].operateCode","=");
                    map.put("params[0].parameterValue",idcard);
                }

                if(!StringUtils.isEmpty(name)){
                    map.put("params[0].fieldCode","XM");
                    map.put("params[0].operateCode","=");
                    map.put("params[0].parameterValue",name);
                }
            }
            if(!StringUtils.isEmpty(page)){
                map.put("page.index",page);
            }else {
                map.put("page.index","1");
            }
            if(!StringUtils.isEmpty(size)){
                map.put("page.rows",size);
            }else {
                map.put("page.rows","10");
            }
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============城乡居民养老保险缴费信息查询接口查询参数==：{}",jsonString);
            Object data = ProvinceUtils.httpData2(map , "http://59.207.107.18:5000/api/cxjmylbxjfxx",appId,appKey);
            if(data!=null){
                JSONObject jsonObject = JSONObject.parseObject(data.toString());
                JSONObject data1 = JSONObject.parseObject(jsonObject.get("data").toString());
                JSONObject data2 = data1.getJSONObject("data");
                if(data2.containsKey("columns")){
                    data2.remove("columns") ;
                }
                logger.info("===============城乡居民养老保险缴费信息查询接口返回信息：{}",data2);
                return  data2;

            }
            logger.info("===============城乡居民养老保险缴费信息查询接口结束");
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "参保人员社会保险缴费信息查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/CBRYSHBXJFInfoold",method = {RequestMethod.POST})
    public Object CBRYSHBXJFInfo(
            @RequestParam(value = "idcard",required = false) String idcard,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "page",required = false) String page,
            @RequestParam(value = "size",required = false) String size){
        try{
            String appId = "2f2e10bda3534d6abfb8835d317e8d3d";
            String appKey = "MmYyZTEwYmRhMzUzNGQ2YWJmYjg4MzVkMzE3ZThkM2Q6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("req.token","3995fa1634a7495ab2ec63511de9dfb1");
            map.put("req.subscribeId","e7f5f0a3da7e46a3b6f40a4489a9cfe5");
            map.put("req.userId","c2d862ac730f4c5f9a89e31ddd6df7a8");

            Boolean flag = true;
            if(!StringUtils.isEmpty(idcard) && !StringUtils.isEmpty(name)){
                map.put("params[0].fieldCode","AAC002");
                map.put("params[0].operateCode","=");
                map.put("params[0].parameterValue",idcard);

                map.put("params[1].fieldCode","AAC003");
                map.put("params[1].operateCode","=");
                map.put("params[1].parameterValue",name);
                flag = false;
            }
            if(flag){
                if(!StringUtils.isEmpty(idcard)){
                    map.put("params[0].fieldCode","AAC002");
                    map.put("params[0].operateCode","=");
                    map.put("params[0].parameterValue",idcard);
                }

                if(!StringUtils.isEmpty(name)){
                    map.put("params[0].fieldCode","AAC003");
                    map.put("params[0].operateCode","=");
                    map.put("params[0].parameterValue",name);
                }
            }


            if(!StringUtils.isEmpty(page)){
                map.put("page.index",page);
            }else {
                map.put("page.index","1");
            }
            if(!StringUtils.isEmpty(size)){
                map.put("page.rows",size);
            }else {
                map.put("page.rows","10");
            }
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============参保人员社会保险缴费信息查询接口查询参数==：{}",jsonString);
            Object data = ProvinceUtils.httpData2(map , "http://59.207.107.18:5000/api/cbrysbjfxx",appId,appKey);
            if(data!=null){
                JSONObject jsonObject = JSONObject.parseObject(data.toString());
                JSONObject data1 = JSONObject.parseObject(jsonObject.get("data").toString());
                JSONObject data2 = data1.getJSONObject("data");
                if(data2.containsKey("columns")){
                    data2.remove("columns") ;
                }
                logger.info("===============参保人员社会保险缴费信息查询接口返回信息：{}",data2);
                return  data2;

            }
            logger.info("===============参保人员社会保险缴费信息查询接口结束");
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "参保人员社会保险缴费信息查询接口2" ,  notes="返回参数")
    @RequestMapping(value = "/CBRYSHBXJFInfoNew",method = {RequestMethod.POST})
    public Object CBRYSHBXJFInfoNew(
            @RequestParam(value = "idcard",required = false) String idcard,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "page",required = false) String page,
            @RequestParam(value = "size",required = false) String size){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Object data = null;
        try{
            String appId = "3e9f2898dfed45b4a4db4e81bf25db10";
            String appKey = "M2U5ZjI4OThkZmVkNDViNGE0ZGI0ZTgxYmYyNWRiMTA6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("req.token","e6263d7e3f084d059a9f8358554229d3");
            map.put("req.subscribeId","fb98ed70e02c4da4a49fb21ba905e6ce");
            map.put("req.userId","48c210f8f4ee4f1fa3796a8667bcdd83");

            Boolean flag = true;
            if(!StringUtils.isEmpty(idcard) && !StringUtils.isEmpty(name)){
                map.put("params[0].fieldCode","AAC002");
                map.put("params[0].operateCode","=");
                map.put("params[0].parameterValue",idcard);

                map.put("params[1].fieldCode","AAC003");
                map.put("params[1].operateCode","=");
                map.put("params[1].parameterValue",name);
                flag = false;
            }
            if(flag){
                if(!StringUtils.isEmpty(idcard)){
                    map.put("params[0].fieldCode","AAC002");
                    map.put("params[0].operateCode","=");
                    map.put("params[0].parameterValue",idcard);
                }

                if(!StringUtils.isEmpty(name)){
                    map.put("params[0].fieldCode","AAC003");
                    map.put("params[0].operateCode","=");
                    map.put("params[0].parameterValue",name);
                }
            }


            if(!StringUtils.isEmpty(page)){
                map.put("page.index",page);
            }else {
                map.put("page.index","1");
            }
            if(!StringUtils.isEmpty(size)){
                map.put("page.rows",size);
            }else {
                map.put("page.rows","10");
            }
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============参保人员社会保险缴费信息查询接口new查询参数==：{}",jsonString);
            data = ProvinceUtils.httpData2(map , "http://59.207.107.18:5000/api/cbrysbjfxxNew",appId,appKey);
            if(data!=null){
                JSONObject jsonObject = JSONObject.parseObject(data.toString());
                if(jsonObject.get("data")==null){
                    return data;
                }
                JSONObject data1 = JSONObject.parseObject(jsonObject.get("data").toString());
                JSONObject data2 = data1.getJSONObject("data");
                if(data2.containsKey("columns")){
                    data2.remove("columns") ;
                }
                logger.info("===============参保人员社会保险缴费信息查询接口new返回信息：{}",data2);
                return  data2;

            }
            logger.info("===============参保人员社会保险缴费信息查询接口new结束");
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            try {
                JSONObject jsonObject = JSONObject.parseObject(data.toString());
                if(jsonObject.get("data")!=null){
                    JSONObject data1 = JSONObject.parseObject(jsonObject.get("data").toString());
                    JSONObject data2 = data1.getJSONObject("data");
                    int num = Integer.parseInt(data2.get("total") + "");
                    if (num > 0) {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        conn = DriverManager.getConnection("jdbc:oracle:thin:interfaceDB/interfaceDB@59.207.219.23:1521:orcl", "interfaceDB", "interfaceDB");
                        conn.setAutoCommit(false);
                        Object list = data2.get("list");
                        List<Map<String, Object>> list2 = JSONArray.parseObject(list.toString(), List.class);
                        String sql = "insert into hns_rst_sbxx (dwbh,dwmc,grbh,xzlx,idcard,xm,jfny,jfzt,jfjs,areacode,batchno) values(?,?,?,?,?,?,?,?,?,?,?)";
                        preparedStatement = conn.prepareStatement(sql);
                        for (Map<String, Object> str : list2) {
                            preparedStatement.setString(1, (String) str.get("AAB001"));
                            preparedStatement.setString(2, (String) str.get("AAB004"));
                            preparedStatement.setString(3, (String) str.get("AAC001"));
                            preparedStatement.setString(4, (String) str.get("AAE140"));
                            preparedStatement.setString(5, (String) str.get("AAC002"));
                            preparedStatement.setString(6, (String) str.get("AAC003"));
                            preparedStatement.setString(7, (String) str.get("AAE041"));
                            preparedStatement.setString(8, (String) str.get("AAC037"));
                            preparedStatement.setString(9, (String) str.get("AAE180"));
                            preparedStatement.setString(10, (String) str.get("AAB301"));
                            preparedStatement.setString(11, (String) str.get("SDC_BATCH_NO"));
                            preparedStatement.addBatch();
                        }
                        preparedStatement.executeBatch();
                        conn.commit();

                        if (preparedStatement != null) {
                            preparedStatement.close();
                        }
                    }
                }

            } catch (Exception e) {
            } finally {
                try {

                    if (conn != null) {
                        conn.close();
                    }
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @ApiOperation(value = "参保单位社会保险缴费信息查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/CBDWSHBXJFInfo",method = {RequestMethod.POST})
    public Object CBDWSHBXJFInfo(
            @RequestParam(value = "company",required = false) String company,
            @RequestParam(value = "bh",required = false) String bh,
            @RequestParam(value = "page",required = false) String page,
            @RequestParam(value = "size",required = false) String size){
        try{
            String appId = "2f2e10bda3534d6abfb8835d317e8d3d";
            String appKey = "MmYyZTEwYmRhMzUzNGQ2YWJmYjg4MzVkMzE3ZThkM2Q6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("req.token","3d9748103c844014b6c6c383c7538b3c");
            map.put("req.subscribeId","2d0ed01f32eb4f54a5d40ca72a5196a7");
            map.put("req.userId","c2d862ac730f4c5f9a89e31ddd6df7a8");

            Boolean flag = true;
            if(!StringUtils.isEmpty(company) && !StringUtils.isEmpty(bh)){
                map.put("params[0].fieldCode","AAB004");
                map.put("params[0].operateCode","=");
                map.put("params[0].parameterValue",company);

                map.put("params[1].fieldCode","AAB001");
                map.put("params[1].operateCode","=");
                map.put("params[1].parameterValue",bh);
                flag = false;
            }
            if(flag){
                if(!StringUtils.isEmpty(company)){
                    map.put("params[0].fieldCode","AAB004");
                    map.put("params[0].operateCode","=");
                    map.put("params[0].parameterValue",company);
                }

                if(!StringUtils.isEmpty(bh)){
                    map.put("params[0].fieldCode","AAB001");
                    map.put("params[0].operateCode","=");
                    map.put("params[0].parameterValue",bh);
                }
            }



            if(!StringUtils.isEmpty(page)){
                map.put("page.index",page);
            }else {
                map.put("page.index","1");
            }
            if(!StringUtils.isEmpty(size)){
                map.put("page.rows",size);
            }else {
                map.put("page.rows","10");
            }
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============参保单位社会保险缴费信息查询接口查询参数==：{}",jsonString);
            Object data = ProvinceUtils.httpData2(map , "http://59.207.107.18:5000/api/cbdwsbjfxx",appId,appKey);
            if(data!=null){
                JSONObject jsonObject = JSONObject.parseObject(data.toString());
                JSONObject data1 = JSONObject.parseObject(jsonObject.get("data").toString());
                JSONObject data2 = data1.getJSONObject("data");
                if(data2.containsKey("columns")){
                    data2.remove("columns") ;
                }
                logger.info("===============参保单位社会保险缴费信息查询接口返回信息：{}",data2);
                return  data2;

            }
            logger.info("===============参保单位社会保险缴费信息查询接口结束");
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "离退休人员养老保险退休信息查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/LTXRYYLBXTXInfo",method = {RequestMethod.POST})
    public Object LTXRYYLBXTXInfo(
            @RequestParam(value = "idcard",required = false) String idcard,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "page",required = false) String page,
            @RequestParam(value = "size",required = false) String size
            ){
        try{
            String appId = "2f2e10bda3534d6abfb8835d317e8d3d";
            String appKey = "MmYyZTEwYmRhMzUzNGQ2YWJmYjg4MzVkMzE3ZThkM2Q6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("req.token","dbb5c194f5bf4a54a00650e51147d0f4");
            map.put("req.subscribeId","9507c99af34245ac9af035b6ae58137a");
            map.put("req.userId","a81013f8571e4a77801d34914f49c207");

            Boolean flag = true;
            if(!StringUtils.isEmpty(idcard) && !StringUtils.isEmpty(name)){
                map.put("params[0].fieldCode","AAC002");
                map.put("params[0].operateCode","=");
                map.put("params[0].parameterValue",idcard);

                map.put("params[1].fieldCode","AAC003");
                map.put("params[1].operateCode","=");
                map.put("params[1].parameterValue",name);
                flag = false;
            }
            if(flag){
                if(!StringUtils.isEmpty(idcard)){
                    map.put("params[0].fieldCode","AAC002");
                    map.put("params[0].operateCode","=");
                    map.put("params[0].parameterValue",idcard);
                }

                if(!StringUtils.isEmpty(name)){
                    map.put("params[0].fieldCode","AAC003");
                    map.put("params[0].operateCode","=");
                    map.put("params[0].parameterValue",name);
                }
            }




            if(!StringUtils.isEmpty(page)){
                map.put("page.index",page);
            }else {
                map.put("page.index","1");
            }
            if(!StringUtils.isEmpty(size)){
                map.put("page.rows",size);
            }else {
                map.put("page.rows","10");
            }
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============离退休人员养老保险退休信息查询接口查询参数==：{}",jsonString);
            Object data = ProvinceUtils.httpData2(map , "http://59.207.107.18:5000/api/ylbxtxqk",appId,appKey);
            if(data!=null){
                JSONObject jsonObject = JSONObject.parseObject(data.toString());
                JSONObject data1 = JSONObject.parseObject(jsonObject.get("data").toString());
                JSONObject data2 = data1.getJSONObject("data");
                if(data2.containsKey("columns")){
                    data2.remove("columns") ;
                }
                logger.info("===============离退休人员养老保险退休信息查询接口返回信息：{}",data2);
                return  data2;

            }
            logger.info("===============离退休人员养老保险退休信息查询接口结束");
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    @ApiOperation(value = "企业养老保险个人缴费信息查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/QYYLBXGRJFInfo",method = {RequestMethod.POST})
    public Object QYYLBXGRJFInfo(
            @RequestParam(value = "idcard",required = false) String idcard,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "page",required = false) String page,
            @RequestParam(value = "size",required = false) String size
            ){
        try{
            String appId = "2f2e10bda3534d6abfb8835d317e8d3d";
            String appKey = "MmYyZTEwYmRhMzUzNGQ2YWJmYjg4MzVkMzE3ZThkM2Q6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("req.token","7e5d15d6b3aa4b599dad7cf60b0bbcc1");
            map.put("req.subscribeId","2c0cbd27d6a64af2a189b38d026a28f9");
            map.put("req.userId","d202407f5c664b35a8090ccdfc831e42");

            Boolean flag = true;
            if(!StringUtils.isEmpty(idcard) && !StringUtils.isEmpty(name)){
                map.put("params[0].fieldCode","AAC002");
                map.put("params[0].operateCode","=");
                map.put("params[0].parameterValue",idcard);

                map.put("params[1].fieldCode","AAC003");
                map.put("params[1].operateCode","=");
                map.put("params[1].parameterValue",name);
                flag = false;
            }
            if(flag){
                if(!StringUtils.isEmpty(idcard)){
                    map.put("params[0].fieldCode","AAC002");
                    map.put("params[0].operateCode","=");
                    map.put("params[0].parameterValue",idcard);
                }

                if(!StringUtils.isEmpty(name)){
                    map.put("params[0].fieldCode","AAC003");
                    map.put("params[0].operateCode","=");
                    map.put("params[0].parameterValue",name);
                }
            }



            if(!StringUtils.isEmpty(page)){
                map.put("page.index",page);
            }else {
                map.put("page.index","1");
            }
            if(!StringUtils.isEmpty(size)){
                map.put("page.rows",size);
            }else {
                map.put("page.rows","10");
            }
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============企业养老保险个人缴费信息查询接口查询参数==：{}",jsonString);
            Object data = ProvinceUtils.httpData2(map , "http://59.207.107.18:5000/api/qyylgrjfxx",appId,appKey);
            if(data!=null){
                JSONObject jsonObject = JSONObject.parseObject(data.toString());
                JSONObject data1 = JSONObject.parseObject(jsonObject.get("data").toString());
                JSONObject data2 = data1.getJSONObject("data");
                if(data2.containsKey("columns")){
                    data2.remove("columns") ;
                }
                logger.info("===============企业养老保险个人缴费信息查询接口返回信息：{}",data2);
                return  data2;

            }
            logger.info("===============企业养老保险个人缴费信息查询接口结束");
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @ApiOperation(value = "企业养老保险缴费信息查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/QYYLBXJFInfo",method = {RequestMethod.POST})
    public Object QYYLBXJFInfo(
            @RequestParam(value = "company",required = false) String company,
            @RequestParam(value = "bh",required = false) String bh,
            @RequestParam(value = "page",required = false) String page,
            @RequestParam(value = "size",required = false) String size){
        try{
            String appId = "2f2e10bda3534d6abfb8835d317e8d3d";
            String appKey = "MmYyZTEwYmRhMzUzNGQ2YWJmYjg4MzVkMzE3ZThkM2Q6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("req.token","03bf38a56afe4bafa850e63ad8221fdb");
            map.put("req.subscribeId","9fa966cd1ef24b95b7ed423a0f56c0a0");
            map.put("req.userId","d202407f5c664b35a8090ccdfc831e42");
            Boolean flag = true;
            if(!StringUtils.isEmpty(company) && !StringUtils.isEmpty(bh)){
                map.put("params[0].fieldCode","AAB004");
                map.put("params[0].operateCode","=");
                map.put("params[0].parameterValue",company);

                map.put("params[1].fieldCode","AAB001");
                map.put("params[1].operateCode","=");
                map.put("params[1].parameterValue",bh);
                flag = false;
            }
            if(flag){
                if(!StringUtils.isEmpty(company)){
                    map.put("params[0].fieldCode","AAB004");
                    map.put("params[0].operateCode","=");
                    map.put("params[0].parameterValue",company);
                }

                if(!StringUtils.isEmpty(bh)){
                    map.put("params[0].fieldCode","AAB001");
                    map.put("params[0].operateCode","=");
                    map.put("params[0].parameterValue",bh);
                }
            }
            if(!StringUtils.isEmpty(page)){
                map.put("page.index",page);
            }else {
                map.put("page.index","1");
            }
            if(!StringUtils.isEmpty(size)){
                map.put("page.rows",size);
            }else {
                map.put("page.rows","10");
            }


            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============企业养老保险缴费信息查询接口查询参数==：{}",jsonString);
            Object data = ProvinceUtils.httpData2(map , "http://59.207.107.18:5000/api/qyyljfxx",appId,appKey);
            if(data!=null){
                JSONObject jsonObject = JSONObject.parseObject(data.toString());
                JSONObject data1 = JSONObject.parseObject(jsonObject.get("data").toString());
                JSONObject data2 = data1.getJSONObject("data");
                if(data2.containsKey("columns")){
                    data2.remove("columns") ;
                }
                logger.info("===============企业养老保险缴费信息查询接口返回信息：{}",data2);
                return  data2;

            }
            logger.info("===============企业养老保险缴费信息查询接口结束");
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
