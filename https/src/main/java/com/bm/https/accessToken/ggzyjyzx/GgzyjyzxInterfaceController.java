package com.bm.https.accessToken.ggzyjyzx;

import com.alibaba.fastjson.JSONObject;
import com.bm.https.untils.AllHttpUntil;
import com.bm.https.untils.WebserviceUtils;
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
import java.util.Map;

@Api(description  = "公共资源交易中心接口类")
@RestController
@RequestMapping("/ggzyjyzx")
public class GgzyjyzxInterfaceController {
    private Logger logger = LoggerFactory.getLogger(GgzyjyzxInterfaceController.class);

    @Autowired
    private WebserviceUtils webserviceUtils;
    @Value("${ggzyjyzx.url.ws}")
    private String url;
    @Value("${ggzyjyzx.gcurl.ws}")
    private String url2;

    @ApiOperation(value = "获取土地公告信息接口" ,  notes="返回参数")
    @RequestMapping(value = "/tdgonggaoInfo",method = {RequestMethod.POST})
    public Object tdgonggaoInfo(@RequestParam(value = "StartDate",required = true)String StartDate,
                                @RequestParam(value = "EndDate",required = true)String EndDate,
                                @RequestParam(value = "CategoryName",required = true)String CategoryName
    ){
        Object o = null;
        try{

            Map<String, Object> map = new HashMap<>();
            map.put("StartDate",StartDate);
            map.put("EndDate",EndDate);
            map.put("CategoryName",CategoryName);
            map.put("Token","zmdggzy@123");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("获取公告信息接口 查询URL：{}",url);
            logger.info("获取公告信息接口 查询参数：{}",jsonString);
            o = AllHttpUntil.getWebservice(url, StartDate,EndDate,CategoryName,"zmdggzy@123","");
            logger.info("获取公告信息接口 返回信息：{}",o);
            return o;
        }catch (Exception e){
            e.printStackTrace();
            return "error:服务器异常";
        }/*finally {
            List<String> cols = new ArrayList<>();
            cols.add("GongGaoContent");
            JSONObject jsonObject = JSONObject.parseObject(o.toString());
            String gongGaoInfo = jsonObject.getString("GongGaoInfo");
            AllHttpUntil.insertDB(gongGaoInfo,"ZMD_GGZYJYZX_GONGGAOINFO",cols);
        }*/
    }

    @ApiOperation(value = "获取公告信息接口" ,  notes="返回参数")
    @RequestMapping(value = "/gonggaoInfo",method = {RequestMethod.POST})
    public Object gonggaoInfo(@RequestParam(value = "StartDate",required = true)String StartDate,
                              @RequestParam(value = "EndDate",required = true)String EndDate,
                              @RequestParam(value = "CategoryName",required = true)String CategoryName
    ){
        Object o = null;
        try{

            Map<String, Object> map = new HashMap<>();
            map.put("StartDate",StartDate);
            map.put("EndDate",EndDate);
            map.put("CategoryName",CategoryName);
            map.put("Token","zmdggzy@123");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("获取公告信息接口 查询URL：{}",url2);
            logger.info("获取公告信息接口 查询参数：{}",jsonString);
            o = AllHttpUntil.getWebservice(url2, StartDate,EndDate,CategoryName,"zmdggzy@123","epo:");
            logger.info("获取公告信息接口 返回信息：{}",o);
            return o;
        }catch (Exception e){
            e.printStackTrace();
            return "error:服务器异常";
        }/*finally {
            List<String> cols = new ArrayList<>();
            cols.add("GongGaoContent");
            JSONObject jsonObject = JSONObject.parseObject(o.toString());
            String gongGaoInfo = jsonObject.getString("GongGaoInfo");
            AllHttpUntil.insertDB(gongGaoInfo,"ZMD_GGZYJYZX_GONGGAOINFO",cols);
        }*/
    }



}
