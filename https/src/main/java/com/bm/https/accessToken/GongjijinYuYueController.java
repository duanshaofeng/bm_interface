package com.bm.https.accessToken;

import com.alibaba.fastjson.JSONObject;
import com.bm.https.untils.AllHttpUntil;
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

@Api(description  = "公积金预约接口类")
@RestController
@RequestMapping("/gjj")
public class GongjijinYuYueController {

    private Logger logger = LoggerFactory.getLogger(GongjijinYuYueController.class);



    @ApiOperation(value = "办事大厅查询")
    @RequestMapping(value = "/getJgxx2",method = {RequestMethod.POST})
    public Object getJgxx2(){
        Map map  = new HashMap<>();
        map.put("jgbm","01");
        map.put("zxbm","01");
        String param = JSONObject.toJSONString(map);
        logger.info("公积金接口 办事大厅查询====方法名：getJgxx2=======入参："+param);
        try{
            String url = "http://59.207.236.111:18080/HFB/common/getJgxx2.service";
            logger.info("公积金接口 办事大厅查询====方法名：getJgxx2=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, param);
            logger.info("公积金接口 办事大厅查询====方法名：getJgxx2=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }




    @ApiOperation(value = "可预约日期查询")
    @RequestMapping(value = "/kyyrqcx1",method = {RequestMethod.POST})
    public Object kyyrqcx1(@RequestParam(value = "ywfl",required = true) String ywfl,
                           @RequestParam(value = "blqd",required = true) String blqd,
                           @RequestParam(value = "yywd",required = true) String yywd){
        Map map  = new HashMap<>();
        map.put("jgbm","01");
        map.put("zxbm","01");
        map.put("userid","1");
        map.put("ywfl",ywfl);
        map.put("blqd",blqd);
        map.put("yywd",yywd);
        String param = JSONObject.toJSONString(map);
        logger.info("公积金接口 可预约日期查询====方法名：kyyrqcx1=======入参："+param);
        try{
            String url = "http://59.207.236.111:8011/SJJC/yysz/kyyrqcx1.service";
            logger.info("公积金接口 可预约日期查询====方法名：kyyrqcx1=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, param);
            logger.info("公积金接口 可预约日期查询====方法名：kyyrqcx1=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }



    @ApiOperation(value = "可预约时间查询")
    @RequestMapping(value = "/kyysjcx1",method = {RequestMethod.POST})
    public Object kyysjcx1(@RequestParam(value = "ywfl",required = true) String ywfl,
                           @RequestParam(value = "blqd",required = true) String blqd,
                           @RequestParam(value = "yyrq",required = true) String yyrq,
                           @RequestParam(value = "yywd",required = true) String yywd){
        Map map  = new HashMap<>();
        map.put("jgbm","01");
        map.put("zxbm","01");
        map.put("userid","1");
        map.put("ywfl",ywfl);
        map.put("yyrq",yyrq);
        map.put("blqd",blqd);
        map.put("yywd",yywd);
        String param = JSONObject.toJSONString(map);
        logger.info("公积金接口 可预约时间查询====方法名：kyysjcx1=======入参："+param);
        try{
            String url = "http://59.207.236.111:8011/SJJC/yysz/kyysjcx1.service";
            logger.info("公积金接口 可预约时间查询====方法名：kyysjcx1=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, param);
            logger.info("公积金接口 可预约时间查询====方法名：kyysjcx1=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }





    @ApiOperation(value = "业务预约录入")
    @RequestMapping(value = "/ywyyadd1",method = {RequestMethod.POST})
    public Object ywyyadd1(@RequestParam(value = "ywfl",required = true) String ywfl,
                           @RequestParam(value = "zxjgbm",required = true) String zxjgbm,
                           @RequestParam(value = "zjbzxbm",required = true) String zjbzxbm,
                           @RequestParam(value = "blqd",required = true) String blqd,
                           @RequestParam(value = "yyrq",required = true) String yyrq,
                           @RequestParam(value = "yysj",required = true) String yysj,
                           @RequestParam(value = "yywd",required = true) String yywd,
                           @RequestParam(value = "zjhm",required = true) String zjhm,
                           @RequestParam(value = "xingming",required = true) String xingming,
                           @RequestParam(value = "sjhm",required = true) String sjhm){
        Map map  = new HashMap<>();
        map.put("jgbm","01");
        map.put("zxbm","01");
        map.put("userid","1");
        map.put("ywfl",ywfl);
        map.put("blqd",blqd);
        map.put("zxjgbm",zxjgbm);
        map.put("zjbzxbm",zjbzxbm);
        map.put("yyrq",yyrq);
        map.put("yywd",yywd);
        map.put("yysj",yysj);
        map.put("zjhm",zjhm);
        map.put("xingming",xingming);
        map.put("sjhm",sjhm);
        String param = JSONObject.toJSONString(map);
        logger.info("公积金接口 业务预约录入====方法名：ywyyadd1=======入参："+param);
        try{
            String url = "http://59.207.236.111:18080/HFSC/common/ywyyadd1.service";
            logger.info("公积金接口 业务预约录入====方法名：ywyyadd1=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, param);
            logger.info("公积金接口 业务预约录入====方法名：ywyyadd1=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }


    @ApiOperation(value = "已预约情况查询")
    @RequestMapping(value = "/yyyqkcx1",method = {RequestMethod.POST})
    public Object yyyqkcx1(@RequestParam(value = "zjhm",required = true) String zjhm,
                           @RequestParam(value = "xingming",required = true) String xingming,
                           @RequestParam(value = "sjhm",required = true) String sjhm,
                           @RequestParam(value = "ksrq",required = true) String ksrq,
                           @RequestParam(value = "jsrq",required = true) String jsrq,
                           @RequestParam(value = "zjbzxbm",required = true) String zjbzxbm,
                           @RequestParam(value = "blqd",required = true) String blqd,
                           @RequestParam(value = "grzh",required = true) String grzh){
        Map map  = new HashMap<>();
        map.put("jgbm","01");
        map.put("zxbm","01");
        map.put("userid","1");
        map.put("zjhm",zjhm);
        map.put("blqd",blqd);
        map.put("xingming",xingming);
        map.put("sjhm",sjhm);
        map.put("ksrq",ksrq);
        map.put("jsrq",jsrq);
        map.put("zjbzxbm",zjbzxbm);
        map.put("grzh",grzh);
        String param = JSONObject.toJSONString(map);
        logger.info("公积金接口 已预约情况查询====方法名：yyyqkcx1=======入参："+param);
        try{
            String url = "http://59.207.236.111:8011/SJJC/yysz/yyyqkcx1.service";
            logger.info("公积金接口 已预约情况查询====方法名：yyyqkcx1=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, param);
            logger.info("公积金接口 已预约情况查询====方法名：yyyqkcx1=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }



    @ApiOperation(value = "业务预约取消")
    @RequestMapping(value = "/ywyydel",method = {RequestMethod.POST})
    public Object ywyydel(@RequestParam(value = "ywfl",required = true) String ywfl,
                          @RequestParam(value = "ywbm",required = true) String ywbm,
                          @RequestParam(value = "id",required = true) String id,
                          @RequestParam(value = "zxjgbm",required = true) String zxjgbm,
                          @RequestParam(value = "zjbzxbm",required = true) String zjbzxbm,
                          @RequestParam(value = "blqd",required = true) String blqd){
        Map map  = new HashMap<>();
        map.put("jgbm","01");
        map.put("zxbm","01");
        map.put("userid","1");
        map.put("ywfl",ywfl);
        map.put("blqd",blqd);
        map.put("ywbm",ywbm);
        map.put("id",id);
        map.put("zxjgbm",zxjgbm);
        map.put("zjbzxbm",zjbzxbm);
        String param = JSONObject.toJSONString(map);
        logger.info("公积金接口 业务预约取消====方法名：ywyydel=======入参："+param);
        try{
            String url = "http://59.207.236.111:18080/HFSC/common/ywyydel.service";
            logger.info("公积金接口 业务预约取消====方法名：ywyydel=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, param);
            logger.info("公积金接口 业务预约取消====方法名：ywyydel=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }


}
