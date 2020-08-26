package com.bm.https.controller.gpsx;

import com.alibaba.fastjson.JSONObject;
import com.bm.https.untils.TYSLHttpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(description  = "高频事项接口类")
@RestController
@RequestMapping("/gpsxtest")
public class GPSXInterfaceTestController {
    private Logger logger = LoggerFactory.getLogger(GPSXInterfaceTestController.class);

    @Autowired
    private TYSLHttpUtils tyslHttpUtils;
    @ApiOperation(value = "上蔡县不动产中心不动产证明接口" ,  notes="返回参数")
    @RequestMapping(value = "/scbdczm",method = {RequestMethod.POST})
    public Object accept(@RequestParam(value = "zjhm",required = true)String zjhm){
        try{
            Map<String, String> map = new HashMap<>();

            map.put("zjhm",zjhm);
            String jsonString = JSONObject.toJSONString(map);
            logger.info(jsonString);
            Object bdcPost = tyslHttpUtils.sendPost2("http://59.207.229.249:8020/BDC_GXJH_NEW/HTTP/getBdczm.do",map);
            logger.info("参数值====="+bdcPost.toString());
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "上蔡县不动产中心国有建设用地首次登记接口" ,  notes="返回参数")
    @RequestMapping(value = "/scgyjsyddj",method = {RequestMethod.POST})
    public Object patchApply(@RequestParam(value = "zjhm",required = true)String zjhm){
        try{

            Map<String, String> map = new HashMap<>();

            map.put("zjhm",zjhm);
            String jsonString = JSONObject.toJSONString(map);
            logger.info(jsonString);
            Object bdcPost = tyslHttpUtils.sendPost2("http://59.207.229.249:8020/BDC_GXJH_NEW/HTTP/getBdcqzh.do",map);
            logger.info("参数值====="+bdcPost.toString());
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }





    @ApiOperation(value = "正阳县不动产中心不动产证明接口" ,  notes="返回参数")
    @RequestMapping(value = "/zybdczm",method = {RequestMethod.POST})
    public Object zybdczm(@RequestParam(value = "zjhm",required = true)String zjhm){
        try{
            Map<String, String> map = new HashMap<>();

            map.put("zjhm",zjhm);
            String jsonString = JSONObject.toJSONString(map);
            logger.info(jsonString);
            Object bdcPost = tyslHttpUtils.sendPost2("http://10.11.20.1:8181/BDC_GXJH_NEW/HTTP/getBdczm.do",map);
            logger.info("参数值====="+bdcPost.toString());
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "正阳县不动产中心国有建设用地首次登记接口" ,  notes="返回参数")
    @RequestMapping(value = "/zygyjsyddj",method = {RequestMethod.POST})
    public Object zygyjsyddj(@RequestParam(value = "zjhm",required = true)String zjhm){
        try{

            Map<String, String> map = new HashMap<>();

            map.put("zjhm",zjhm);
            String jsonString = JSONObject.toJSONString(map);
            logger.info(jsonString);
            Object bdcPost = tyslHttpUtils.sendPost2("http://10.11.20.1:8181/BDC_GXJH_NEW/HTTP/getBdcqzh.do",map);
            logger.info("参数值====="+bdcPost.toString());
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
