package com.bm.https.controller.jck;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bm.https.untils.BDCHttpUtils;
import com.bm.https.untils.WebserviceUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(description  = "基础库接口类")
@RestController
@RequestMapping("/test/jck")
public class JCKTestInterfaceController {
    private Logger logger = LoggerFactory.getLogger(JCKTestInterfaceController.class);

    @Autowired
    private WebserviceUtils webserviceUtils;

    @ApiOperation(value = "人口基本信息查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/personInfo",method = {RequestMethod.POST})
    public Object personInfo(@RequestParam(value = "sfzh",required = true)String sfzh,@RequestParam(value = "name",required = true)String name){
        try{
            Map<String, Object> maps = new HashMap<>();
            Map<String, Object> map = new HashMap<>();
            map.put("FULLNAME",name);
            map.put("CARDNUM",sfzh);
            maps.put("params",map);
            maps.put("cmd","301001");
            String jsonString = JSONObject.toJSONString(maps);
            logger.info("人口基本信息查询接口 查询参数：{}",jsonString);
            Object client = webserviceClient("http://59.207.219.132:8084/exbase-webservice/share/dataList/30010?wsdl", "process",jsonString);
            logger.info("人口基本信息查询接口 返回信息：{}",client);
            return client;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "法人基本信息查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/farenInfo",method = {RequestMethod.POST})
    public Object farenInfo(@RequestParam(value = "orgname",required = true)String orgname){
        try{
            Map<String, Object> maps = new HashMap<>();
            Map<String, Object> map = new HashMap<>();
            map.put("ORGNAME",orgname);
            maps.put("params",map);
            maps.put("cmd","301001");
            String jsonString = JSONObject.toJSONString(maps);
            logger.info("法人基本信息查询接口 查询参数：{}",jsonString);
            Object client = webserviceClient("http://59.207.219.132:8084/exbase-webservice/share/dataList/31010?wsdl", "process",jsonString);
            logger.info("法人基本信息查询接口 返回信息：{}",client);
            return client;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Object webserviceClient(String url,String method,String json){
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(url);

        Object[] objects = new Object[0];
        try {

            objects = client.invoke(method, json);
            System.out.println("返回数据:" + objects[0]);
            return objects[0];

        } catch (java.lang.Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

}
