package com.bm.https.accessToken.bdc.csapp;

import com.alibaba.fastjson.JSONObject;
import com.bm.https.untils.BDCHttpUtils;
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

@Api(description  = "不动产城市门户app接口类")
@RestController
@RequestMapping("/bdc")
public class BDCZScsAppInterfaceController {
    private Logger logger = LoggerFactory.getLogger(BDCZScsAppInterfaceController.class);

    @Autowired
    private BDCHttpUtils bdcHttpUtils;
    @ApiOperation(value = "不动产权利证书查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/ZSQuery",method = {RequestMethod.POST})
    public Object ZSQuery(@RequestParam(value = "SQRMC",required = true)String SQRMC,
                          @RequestParam(value = "SLBH",required = false)String SLBH,
                           @RequestParam(value = "SQRZJHM",required = true)String SQRZJHM,
                          @RequestParam(value = "CXYWLSH",required = true)String CXYWLSH){
        try{
            Map<String, Object> map = new HashMap<>();
            map.put("SQRMC",SQRMC);
            map.put("SQRZJHM",SQRZJHM);
            map.put("SLBH",SLBH);
            map.put("CXYWLSH",CXYWLSH);
            map.put("YHM","00000002");
            map.put("MM","911218");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("不动产权利证书查询接口 查询参数：{}",jsonString);
            Object bdcPost = bdcHttpUtils.bdcPost("http://39.164.193.7:8081/Api/YZZZCX/ZSQuery",jsonString);
            logger.info("不动产权利证书查询接口 返回结果：{}",bdcPost);
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "不动产登记证明（预抵押、预告登记、抵押、异议、地役权）查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/ZMQuery",method = {RequestMethod.POST})
    public Object ZMQuery(@RequestParam(value = "SQRMC",required = true)String SQRMC,
                          @RequestParam(value = "SLBH",required = false)String SLBH,
                          @RequestParam(value = "SQRZJHM",required = true)String SQRZJHM,
                          @RequestParam(value = "CXYWLSH",required = true)String CXYWLSH){
        try{

            Map<String, Object> map = new HashMap<>();
            map.put("SQRMC",SQRMC);
            map.put("SQRZJHM",SQRZJHM);
            map.put("SLBH",SLBH);
            map.put("CXYWLSH",CXYWLSH);
            map.put("YHM","00000002");
            map.put("MM","911218");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("不动产登记证明接口 查询参数：{}",jsonString);
            Object bdcPost = bdcHttpUtils.bdcPost("http://39.164.193.7:8081/Api/YZZZCX/ZMQuery",jsonString);
            logger.info("不动产登记证明接口 返回结果：{}",bdcPost);
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "网上签约信息查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/HTQuery",method = {RequestMethod.POST})
    public Object HTQuery(@RequestParam(value = "SQRMC",required = true)String SQRMC,
                          @RequestParam(value = "SLBH",required = false)String SLBH,
                          @RequestParam(value = "SQRZJHM",required = true)String SQRZJHM,
                          @RequestParam(value = "CXYWLSH",required = true)String CXYWLSH){
        try{

            Map<String, Object> map = new HashMap<>();
            map.put("SQRMC",SQRMC);
            map.put("SQRZJHM",SQRZJHM);
            map.put("SLBH",SLBH);
            map.put("CXYWLSH",CXYWLSH);
            map.put("YHM","00000002");
            map.put("MM","911218");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("网上签约信息查询接口 查询参数：{}",jsonString);
            Object bdcPost = bdcHttpUtils.bdcPost("http://39.164.193.7:8081/Api/YZZZCX/HTQuery",jsonString);
            logger.info("网上签约信息查询接口 返回结果：{}",bdcPost);
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "个人住房信息查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/GRZFQuery",method = {RequestMethod.POST})
    public Object GRZFQuery(@RequestParam(value = "SQRMC",required = true)String SQRMC,
                            @RequestParam(value = "SLBH",required = false)String SLBH,
                          @RequestParam(value = "SQRZJHM",required = true)String SQRZJHM,
                            @RequestParam(value = "CXYWLSH",required = true)String CXYWLSH){
        try{

            Map<String, Object> map = new HashMap<>();
            map.put("SQRMC",SQRMC);
            map.put("SQRZJHM",SQRZJHM);
            map.put("SLBH",SLBH);
            map.put("CXYWLSH",CXYWLSH);
            map.put("YHM","00000002");
            map.put("MM","911218");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("个人住房信息查询接口 查询参数：{}",jsonString);
            Object bdcPost = bdcHttpUtils.bdcPost("http://39.164.193.7:8081/Api/YZZZCX/GRZFQuery",jsonString);
            logger.info("个人住房信息查询接口 返回结果：{}",bdcPost);
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "业务进度查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/YWJDQuery",method = {RequestMethod.POST})
    public Object YWJDQuery(@RequestParam(value = "SQRMC",required = true)String SQRMC,
                            @RequestParam(value = "SLBH",required = false)String SLBH,
                          @RequestParam(value = "SQRZJHM",required = true)String SQRZJHM,
                            @RequestParam(value = "CXYWLSH",required = true)String CXYWLSH){
        try{

            Map<String, Object> map = new HashMap<>();
            map.put("SQRMC",SQRMC);
            map.put("SQRZJHM",SQRZJHM);
            map.put("SLBH",SLBH);
            map.put("CXYWLSH",CXYWLSH);
            map.put("YHM","00000002");
            map.put("MM","911218");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("业务进度查询接口 查询参数：{}",jsonString);
            Object bdcPost = bdcHttpUtils.bdcPost("http://39.164.193.7:8081/Api/YZZZCX/YWJDQuery",jsonString);
            logger.info("业务进度查询接口 返回结果：{}",bdcPost);
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
