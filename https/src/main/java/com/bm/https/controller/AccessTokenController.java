package com.bm.https.controller;

import com.alibaba.fastjson.JSONObject;
import com.bm.https.untils.ConnectUtil;
import com.bm.https.untils.IpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(description  = "刷新token类")
@RestController
@RequestMapping("/acceccToken")
public class AccessTokenController {
    private Logger logger = LoggerFactory.getLogger(AccessTokenController.class);


    @Autowired
    private ConnectUtil connectUtil;

    @ApiOperation(value = "刷新token" ,  notes="返回参数")
    @RequestMapping(value = "/fresh",method = {RequestMethod.POST})
    public Object fresh(@RequestParam("accessToken")String accessToken, HttpServletRequest request){
        Map<String, Object> mess = new HashMap<>();
        try{
            logger.info("驻马店刷新accessToken开始==========参数accessToken："+accessToken);
            String ipAddr = IpUtil.getIpAddr(request);//请求方ip
            logger.info("驻马店刷新accessToken开始==========请求方ip："+ipAddr);
            Map<String, Object> map = connectUtil.getIPWHITE(ipAddr);
            Boolean flag = (Boolean) map.get("flag");
            String deptcode = (String) map.get("deptcode");
            if(!flag){
                logger.info("驻马店刷新accessToken开始==========白名单验证不通过");
                mess.put("message","请确认添加ip白名单");
                mess.put("ip","请求ip："+ipAddr);
                mess.put("success",false);
                String jsonString = JSONObject.toJSONString(mess);
                logger.info("驻马店交换平台接口资源请求开始==========白名单验证不通过："+jsonString);
                return mess;
            }
            logger.info("驻马店刷新accessToken开始==========开始验证token");
            Map<String, Object> tokenCheck = connectUtil.accessTokenCheck(accessToken, deptcode);
            Boolean f = (Boolean) tokenCheck.get("flag");
            if(!f){
                mess.put("message","accessToken不存在或者与部门不符");
                mess.put("accessToken",accessToken);
                mess.put("success",false);
                String jsonString = JSONObject.toJSONString(mess);
                logger.info("驻马店交换平台接口资源请求==========token验证不通过："+jsonString);
                return mess;
            }
            Map<String, Object> updateToken = connectUtil.updateToken(accessToken, deptcode);
            logger.info("token 刷新成功===="+JSONObject.toJSONString(updateToken));
            return updateToken;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }


    }



    @ApiOperation(value = "个人业务明细信息")
    @RequestMapping(value = "/GJTQCXZHMX",method = {RequestMethod.POST})
    public String getGJTQCXZHMX(@RequestParam(value = "blqd",required = false)String blqd,@RequestParam(value = "ywbm",required = false)String ywbm,
                                @RequestParam(value = "userid",required = false)Long userid,@RequestParam(value = "zxbm",required = false)String zxbm,
                                @RequestParam(value = "dwzh",required = false)String dwzh,@RequestParam(value = "grxx",required = false)String grxx,
                                @RequestParam(value = "ksrq",required = false)String ksrq,@RequestParam(value = "jsrq",required = false)String jsrq,
                                @RequestParam(value = "jgbm",required = false)String jgbm,@RequestParam(value = "page",required = false)String page,
                                @RequestParam(value = "size",required = false)String size){
        String param = "{\"dwzh\":\" \",\"grxx\":\"00000712\",\"ksrq\":\"2019-07-01\",\"jsrq\":\"2020-06-30\",\"userid\":\"1\"}";
        logger.info("公积金接口请求个人业务明细信息====方法名：getGJTQCXZHMX=======入参："+param);
        try{
            String url = "https://59.207.236.111:443/GJTQCX/jcrcx/jcrxxcxzhmx.service";
            logger.info("公积金接口请求个人业务明细信息====方法名：getGJTQCXZHMX=======请求url："+url);
            String string = com.bm.https.untils.HttpsUtils.getServiceNetworkInformation(param, url);
            logger.info("公积金接口请求个人业务明细信息====方法名：getGJTQCXZHMX=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }

}
