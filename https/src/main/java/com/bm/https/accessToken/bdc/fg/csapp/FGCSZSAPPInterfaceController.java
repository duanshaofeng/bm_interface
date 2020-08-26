package com.bm.https.accessToken.bdc.fg.csapp;

import com.alibaba.fastjson.JSONObject;
import com.bm.https.controller.bdc.fg.csapp.ClientCAUtil;
import com.bm.https.untils.BDCHttpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Api(description  = "房管城市门户app接口类")
@RestController
@RequestMapping("/fg")
public class FGCSZSAPPInterfaceController {
    private Logger logger = LoggerFactory.getLogger(FGCSZSAPPInterfaceController.class);

    @Autowired
    private BDCHttpUtils bdcHttpUtils;
    @Autowired
    private ClientCAUtil clientCAUtil;
    @Value("${ca.setting.cer.password}")
    private String password;
    @Value("${ca.setting.server.publickey}")
    private String publickey;
    @Value("${ca.setting.cer.file}")
    private String file;

    @ApiOperation(value = "期房合同列表接口" ,  notes="返回参数")
    @RequestMapping(value = "/QFWQHTLB",method = {RequestMethod.POST})
    public Object QFWQHTLB(@RequestParam(value = "QFHTH",required = true)String QFHTH,@RequestParam(value = "SRF",required = true)String SRF
                           ){
        try{
            String url = "http://59.207.237.1:8795/bitsADI/QFWQHTLB";
            HashMap<String,Object> requestParamJsonMap = new HashMap<>();
            requestParamJsonMap.put("QFHTH",QFHTH);
            requestParamJsonMap.put("SRF",SRF);
            String jsonString = JSONObject.toJSONString(requestParamJsonMap);
            logger.info("期房合同列表接口 查询参数：{}",jsonString);
            String getResult = clientCAUtil.sendRequest("GET",url,requestParamJsonMap,"client");
            logger.info("期房合同列表接口请求结果：{}",getResult);
            return getResult;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }






    @ApiOperation(value = "现房合同列表接口" ,  notes="返回参数")
    @RequestMapping(value = "/XFWQHTLB",method = {RequestMethod.POST})
    public Object XFWQHTLB(@RequestParam(value = "XFHTH",required = true)String XFHTH,@RequestParam(value = "SRF",required = true)String SRF){
        try{
            String url = "http://59.207.237.1:8795/bitsADI/XFWQHTLB";
            HashMap<String,Object> requestParamJsonMap = new HashMap<>();
            requestParamJsonMap.put("XFHTH",XFHTH);
            requestParamJsonMap.put("SRF",SRF);
            String jsonString = JSONObject.toJSONString(requestParamJsonMap);
            logger.info("现房合同列表接口 查询参数：{}",jsonString);
            String getResult = clientCAUtil.sendRequest("GET",url,requestParamJsonMap,"client");
            logger.info("现房合同列表接口请求结果：{}",getResult);
            return getResult;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "业务办理进度接口" ,  notes="返回参数")
    @RequestMapping(value = "/YWBLJD",method = {RequestMethod.POST})
    public Object YWBLJD(@RequestParam(value = "YWDLBH",required = true)String YWDLBH,@RequestParam(value = "YWBH",required = true)String YWBH,@RequestParam(value = "SRF",required = true)String SRF){
        try{
            String url = "http://59.207.237.1:8795/bitsADI/YWBLJD";
            HashMap<String,Object> requestParamJsonMap = new HashMap<>();
            requestParamJsonMap.put("YWDLBH",YWDLBH);
            requestParamJsonMap.put("YWBH",YWBH);
            requestParamJsonMap.put("SRF",SRF);
            String jsonString = JSONObject.toJSONString(requestParamJsonMap);
            logger.info("业务办理进度接口 查询参数：{}",jsonString);
            String getResult = clientCAUtil.sendRequest("GET",url,requestParamJsonMap,"client");
            logger.info("业务办理进度接口请求结果：{}",getResult);
            return getResult;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "预售许可证列表接口" ,  notes="返回参数")
    @RequestMapping(value = "/YSXKZLB",method = {RequestMethod.POST})
    public Object YSXKZLB(@RequestParam(value = "KFQY",required = false)String KFQY,
                          @RequestParam(value = "XMMC",required = false)String XMMC,
                          @RequestParam(value = "YSZH",required = false)String YSZH){
        try{
            String url = "http://59.207.237.1:8795/bitsADI/YSXKZLB";
            HashMap<String,Object> requestParamJsonMap = new HashMap<>();
            requestParamJsonMap.put("KFQY",KFQY);
            requestParamJsonMap.put("XMMC",XMMC);
            requestParamJsonMap.put("YSZH",YSZH);
            String jsonString = JSONObject.toJSONString(requestParamJsonMap);
            logger.info("预售许可证列表接口 查询参数：{}",jsonString);
            String getResult = clientCAUtil.sendRequest("GET",url,requestParamJsonMap,"client");
            logger.info("预售许可证列表接口请求结果：{}",getResult);
            return getResult;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
