package com.bm.https.accessToken.chengguan.zilaishui;

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

@Api(description  = "泌阳自来水资源对接接口类")
@RestController
@RequestMapping("/byzls")
public class BYZaiShuiResourceInfoController {
    private Logger logger = LoggerFactory.getLogger(BYZaiShuiResourceInfoController.class);




    @ApiOperation(value = "泌阳自来水水费查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/sfcx",method = {RequestMethod.POST})
    public Object sfxinfo(@RequestParam("customerCode") String customerCode){
        Map<String, String> map = new HashMap<>();
        try{
            map.put("customerCode",customerCode);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============泌阳自来水水费查询接口参数:  "+jsonString);
            Object post = AllHttpUntil.sendGet("http://47.97.115.106:9857/WaterQueryService.asmx/GetCustomerBillByCustomerCode?customerCode="+customerCode);
            logger.info("===============泌阳自来水水费查询接口查询:返回  "+JSONObject.toJSONString(post));
            return praseXml(post);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "泌阳自来水水价查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/sjcx",method = {RequestMethod.POST})
    public Object sjcxinfo(){
        Map<String, String> map = new HashMap<>();
        try{
            logger.info("===============泌阳自来水水价查询接口参数:  无");
            Object post = AllHttpUntil.sendGet("http://47.97.115.106:9857/WaterQueryService.asmx/GetPriceTypeList");
            logger.info("===============泌阳自来水水价查询接口查询:返回  "+JSONObject.toJSONString(post));
            return praseXml(post);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "泌阳自来水服务网点查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/wdcx",method = {RequestMethod.POST})
    public Object wdcxinfo(){
        Map<String, String> map = new HashMap<>();
        try{
            logger.info("===============泌阳自来水服务网点查询接口参数:  无");
            Object post = AllHttpUntil.sendGet("http://47.97.115.106:9857/WaterQueryService.asmx/GetAreaList");
            logger.info("===============泌阳自来水服务网点查询接口查询:返回  "+JSONObject.toJSONString(post));
            return praseXml(post);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "泌阳自来水户号查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/hhcx",method = {RequestMethod.POST})
    public Object hhcxinfo(@RequestParam(value="idcardNumber",required = true) String idcardNumber){
        Map<String, String> map = new HashMap<>();
        try{
            logger.info("===============泌阳自来水户号查询接口参数:  "+idcardNumber);
            Object post = AllHttpUntil.sendGet("http://47.97.115.106:9857/WaterQueryService.asmx/GetCustomerBillByIDCardNumber?idcardNumber="+idcardNumber);
            logger.info("===============泌阳自来水户号查询接口查询:返回  "+JSONObject.toJSONString(post));
            return praseXml(post);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args)throws Exception{
        Object post = AllHttpUntil.sendGet("http://47.97.115.106:9857/WaterQueryService.asmx/GetCustomerBillByIDCardNumber?idcardNumber=412822199311091175");
        if(post!=null&&!post.equals("")){
            Document doc= DocumentHelper.parseText((String) post);
            Element rootElement = doc.getRootElement();
            Object data = rootElement.getData();
            System.out.println(data);
        }
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
