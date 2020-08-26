package com.bm.https.controller.zilaishui;

import com.alibaba.fastjson.JSONObject;
import com.bm.https.untils.AllHttpUntil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(description  = "汝南自来水资源对接接口类")
@RestController
@RequestMapping("/test/rnzls")
public class RNZaiShuiResourceInfoTestController {
    private Logger logger = LoggerFactory.getLogger(RNZaiShuiResourceInfoTestController.class);




    @ApiOperation(value = "汝南自来水水费查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/sfcx",method = {RequestMethod.POST})
    public Object sfxinfo(@RequestParam("customerCode") String customerCode){
        Map<String, String> map = new HashMap<>();
        try{
            map.put("customerCode",customerCode);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============汝南自来水水费查询接口参数:  "+jsonString);
            Object post = AllHttpUntil.sendGet("http://61.136.64.54:1880/zhengwu/GetCustomerBill.asq?customerCode="+customerCode);
            logger.info("===============汝南自来水水费查询接口查询:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "汝南自来水水价查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/sjcx",method = {RequestMethod.POST})
    public Object sjcxinfo(){
        Map<String, String> map = new HashMap<>();
        try{
            logger.info("===============汝南自来水水价查询接口参数:  无");
            Object post = AllHttpUntil.sendGet("http://61.136.64.54:1880/zhengwu/GetPriceTypeList.asq");
            logger.info("===============汝南自来水水价查询接口查询:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "汝南自来水服务网点查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/wdcx",method = {RequestMethod.POST})
    public Object wdcxinfo(){
        Map<String, String> map = new HashMap<>();
        try{
            logger.info("===============汝南自来水服务网点查询接口参数:  无");
            Object post = AllHttpUntil.sendGet("http://61.136.64.54:1880/zhengwu/GetAreaList.asq");
            logger.info("===============汝南自来水服务网点查询接口查询:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    public static void main(String[] args)throws Exception{
        Object post = AllHttpUntil.sendGet("http://61.136.64.54:1880/zhengwu/GetCustomerBill.asq?customerCode=123");
        System.out.println(post);

    }

    public static Object praseXml(Object xmlstr){
        try{
            if(xmlstr!=null&&!xmlstr.equals("")){
                Document doc= DocumentHelper.parseText((String) xmlstr);
                Element rootElement = doc.getRootElement();
                Object data = rootElement.getData();
                return data;
            }else{
                return xmlstr;
            }

        }catch(Exception e){
            e.printStackTrace();
            return "error";
        }
    }

}
