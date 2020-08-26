package com.bm.https.controller.zilaishui;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description  = "遂平自来水资源ZS对接接口类")
@RestController
@RequestMapping("/zs/spzls")
public class SPZaiShuiResourceInfoZSController {
    private Logger logger = LoggerFactory.getLogger(SPZaiShuiResourceInfoZSController.class);




    @ApiOperation(value = "遂平自来水查询客户信息接口" ,  notes="返回参数")
    @RequestMapping(value = "/getcustcodeinfo",method = {RequestMethod.POST})
    public Object getcustcodeinfo(@RequestParam("usercode") String usercode,
                                  @RequestParam("watercode") String watercode,
                                  @RequestParam("seltype") String seltype){
        Map<String, String> map = new HashMap<>();
        try{
            String logincode = "ZW_463100";
            String loginpass = "03207A79622359C2CF1727B251CBD2B5";
            map.put("logincode",logincode);
            map.put("loginpass",loginpass);
            map.put("watercode",watercode);
            map.put("usercode",usercode);
            map.put("seltype",seltype);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============遂平自来水查询客户信息接口参数:  "+jsonString);
            Object post = AllHttpUntil.webserviceClient("http://118.190.180.133/services/PayService?wsdl","getcustcodeinfo",jsonString);
            logger.info("===============遂平自来水查询客户信息接口查询:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "遂平自来水缴费返回信息接口" ,  notes="返回参数")
    @RequestMapping(value = "/payment",method = {RequestMethod.POST})
    public Object payment(@RequestParam("userid") String userid,
                          @RequestParam("order") String order,
                          @RequestParam("date") String date,
                          @RequestParam("money") String money,
                          @RequestParam("watercode") String watercode){
        Map<String, String> map = new HashMap<>();
        try{

            String logincode = "ZW_463100";
            String loginpass = "03207A79622359C2CF1727B251CBD2B5";
            map.put("userid",userid);
            map.put("order",order);
            map.put("date",date);
            map.put("money",money);
            map.put("watercode",watercode);
            map.put("logincode",logincode);
            map.put("loginpass",loginpass);
            String jsonString1 = JSONObject.toJSONString(map);
            logger.info("===============遂平自来水缴费返回信息接口参数:  "+jsonString1);
            Object post1 = AllHttpUntil.webserviceClient("http://118.190.180.133/services/PayService?wsdl","getcustwaterrate",jsonString1);
            logger.info("===============遂平自来水缴费返回信息接口查询:返回  "+JSONObject.toJSONString(post1));
            return post1;
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }



    @ApiOperation(value = "遂平自来水查询应缴信息接口" ,  notes="返回参数")
    @RequestMapping(value = "/getcustwaterrate",method = {RequestMethod.POST})
    public Object getcustwaterrate(@RequestParam("userid") String userid,
                                   @RequestParam("watercode") String watercode){
        Map<String, String> map = new HashMap<>();
        try{

            String logincode = "ZW_463100";
            String loginpass = "03207A79622359C2CF1727B251CBD2B5";
            map.put("userid",userid);
            map.put("watercode",watercode);
            map.put("logincode",logincode);
            map.put("loginpass",loginpass);
            String jsonString1 = JSONObject.toJSONString(map);
            logger.info("===============遂平自来水查询应缴信息接口参数:  "+jsonString1);
            Object post1 = AllHttpUntil.webserviceClient("http://118.190.180.133/services/PayService?wsdl","getcustwaterrate",jsonString1);
            logger.info("===============遂平自来水查询应缴信息接口查询:返回  "+JSONObject.toJSONString(post1));
            return post1;
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }


    @ApiOperation(value = "遂平自来水历史水费查询信息接口" ,  notes="返回参数")
    @RequestMapping(value = "/getcustlastwaterrecord",method = {RequestMethod.POST})
    public Object getcustlastwaterrecord(@RequestParam("watercode") String watercode,
                                         @RequestParam("startdate") String startdate,
                                         @RequestParam("enddate") String enddate,
                                         @RequestParam("userid") String userid){
        Map<String, String> map = new HashMap<>();
        try{
            String logincode = "ZW_463100";
            String loginpass = "03207A79622359C2CF1727B251CBD2B5";
            map.put("watercode",watercode);
            map.put("logincode",logincode);
            map.put("loginpass",loginpass);
            map.put("userid",userid);
            map.put("enddate",enddate);
            map.put("startdate",startdate);
            String jsonString1 = JSONObject.toJSONString(map);
            logger.info("===============遂平自来水历史水费查询信息接口参数:  "+jsonString1);
            Object post1 = AllHttpUntil.webserviceClient("http://118.190.180.133/services/PayService?wsdl","getcustlastwaterrecord",jsonString1);
            logger.info("===============遂平自来水历史水费查询信息接口返回:  "+post1);
            logger.info("===============遂平自来水历史水费查询信息查询:集合返回  "+JSONObject.toJSONString(post1));
            return post1;

        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }




    @ApiOperation(value = "遂平自来水历史缴费记录查询信息接口" ,  notes="返回参数")
    @RequestMapping(value = "/getcustlastpayment",method = {RequestMethod.POST})
    public Object getcustlastpayment(@RequestParam("userid") String userid,
                                     @RequestParam("startdate") String startdate,
                                     @RequestParam("enddate") String enddate,
                                     @RequestParam("watercode") String watercode){
        Map<String, String> map = new HashMap<>();
        try{
            String logincode = "ZW_463100";
            String loginpass = "03207A79622359C2CF1727B251CBD2B5";
            map.put("logincode",logincode);
            map.put("loginpass",loginpass);
            map.put("watercode",watercode);
            map.put("userid",userid);
            map.put("enddate",enddate);
            map.put("startdate",startdate);
            String jsonString1 = JSONObject.toJSONString(map);
            logger.info("===============遂平自来水历史缴费记录查询信息接口参数:  "+jsonString1);
            Object post1 = AllHttpUntil.webserviceClient("http://118.190.180.133/services/PayService?wsdl","getcustlastpayment",jsonString1);
            logger.info("===============遂平自来水历史缴费记录查询信息接口返回:  "+post1);
            logger.info("===============遂平自来水历史缴费记录查询信息查询:集合返回  "+JSONObject.toJSONString(post1));
            return post1;
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }



    @ApiOperation(value = "遂平自来水查询交易汇总信息接口" ,  notes="返回参数")
    @RequestMapping(value = "/getpaycollect",method = {RequestMethod.POST})
    public Object getpaycollect(@RequestParam("usercode") String usercode,
                                         @RequestParam("paydate") String paydate,
                                         @RequestParam("seltype") String seltype){
        Map<String, String> map = new HashMap<>();
        try{
            String logincode = "ZW_463100";
            String loginpass = "03207A79622359C2CF1727B251CBD2B5";
            String watercode = "46310001";
            map.put("logincode",logincode);
            map.put("loginpass",loginpass);
            map.put("watercode",watercode);
            map.put("usercode",usercode);
            map.put("seltype",seltype);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============查询客户信息参数:  "+jsonString);
            Object post = AllHttpUntil.webserviceClient("http://118.190.180.133/services/PayService?wsdl","getcustcodeinfo",jsonString);
            logger.info("===============查询客户信息返回:  "+post);
            if(post!=null){
                List<Object> objects = new ArrayList<>();
                map.remove("usercode");
                map.remove("seltype");
                List<Map> maps = JSONObject.parseArray((String) post, Map.class);
                String userid = "";
                for(int i = 0;i < maps.size();i++){
                    Map map1 = maps.get(i);
                    String bmcode = (String)map1.get("bmcode");
                    String custcode = (String)map1.get("custcode");
                    String sbcode = (String)map1.get("sbcode");
                    userid = bmcode + custcode + sbcode ;
                    map.put("userid",userid);
                    map.put("paydate",paydate);
                    String jsonString1 = JSONObject.toJSONString(map);
                    logger.info("===============遂平自来水查询交易汇总信息接口参数:  "+jsonString1);
                    Object post1 = AllHttpUntil.webserviceClient("http://118.190.180.133/services/PayService?wsdl","getpaycollect",jsonString1);
                    logger.info("===============遂平自来水查询交易汇总信息接口返回:  "+post1);
                    objects.add(post1);
                }
                logger.info("===============遂平自来水查询交易汇总信息查询:集合返回  "+JSONObject.toJSONString(objects));
                return objects;
            }


            return "error";
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    public static void main(String[] args)throws Exception{
        Map<String, String> map = new HashMap<>();
        String logincode = "ZW_463100";
        String loginpass = "03207A79622359C2CF1727B251CBD2B5";
        String watercode = "46310001";
        map.put("logincode",logincode);
        map.put("loginpass",loginpass);
        map.put("watercode",watercode);
        map.put("usercode","010001");
        map.put("seltype","0");
       /* map.put("userid","101000101");
        map.put("enddate","2013-06-01");
        map.put("startdate","2013-01-01");*/
        String jsonString = JSONObject.toJSONString(map);
        System.out.println("===============遂平自来水查询客户信息接口参数:  "+jsonString);
        Object post = AllHttpUntil.webserviceClient("http://118.190.180.133/services/PayService?wsdl","getcustcodeinfo",jsonString);
        System.out.println("===============遂平自来水查询客户信息接口查询:返回  "+JSONObject.toJSONString(post));

    }


}
