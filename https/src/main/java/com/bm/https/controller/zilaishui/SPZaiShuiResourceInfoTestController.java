package com.bm.https.controller.zilaishui;

import com.alibaba.fastjson.JSONObject;
import com.bm.https.untils.AllHttpUntil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description  = "遂平自来水资源对接接口类")
@RestController
@RequestMapping("/test/spzls")
public class SPZaiShuiResourceInfoTestController {
    private Logger logger = LoggerFactory.getLogger(SPZaiShuiResourceInfoTestController.class);


    @ApiOperation(value = "遂平自来水查询客户信息接口", notes = "返回参数")
    @RequestMapping(value = "/getcustcodeinfo", method = {RequestMethod.POST})
    public Object getcustcodeinfo(@RequestParam("usercode") String usercode,
                                  @RequestParam("seltype") String seltype) {
        Map<String, String> map = new HashMap<>();
        try {
            String logincode = "4331010743214";
            String loginpass = "E10ADC3949BA59ABBE56E057F20F883E";
            String watercode = "43310101";
            map.put("logincode", logincode);
            map.put("loginpass", loginpass);
            map.put("watercode", watercode);
            map.put("usercode", usercode);
            map.put("seltype", seltype);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============遂平自来水查询客户信息接口参数:  " + jsonString);
            Object post = AllHttpUntil.webserviceClient("http://42.96.168.152:8086/ccpay/services/PayService?wsdl", "getcustcodeinfo", jsonString);
            logger.info("===============遂平自来水查询客户信息接口查询:返回  " + JSONObject.toJSONString(post));
            return post;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "遂平自来水查询应缴信息接口", notes = "返回参数")
    @RequestMapping(value = "/getcustwaterrate", method = {RequestMethod.POST})
    public Object getcustwaterrate(@RequestParam("usercode") String usercode,
                                   @RequestParam("seltype") String seltype) {
        Map<String, String> map = new HashMap<>();
        try {
            String logincode = "4331010743214";
            String loginpass = "E10ADC3949BA59ABBE56E057F20F883E";
            String watercode = "43310101";
            map.put("logincode", logincode);
            map.put("loginpass", loginpass);
            map.put("watercode", watercode);
            map.put("usercode", usercode);
            map.put("seltype", seltype);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============查询客户信息参数:  " + jsonString);
            Object post = AllHttpUntil.webserviceClient("http://42.96.168.152:8086/ccpay/services/PayService?wsdl", "getcustcodeinfo", jsonString);
            logger.info("===============查询客户信息返回:  " + post);
            if (post != null) {
                List<Object> objects = new ArrayList<>();
                map.remove("usercode");
                map.remove("seltype");
                List<Map> maps = JSONObject.parseArray((String) post, Map.class);
                String userid = "";
                for (int i = 0; i < maps.size(); i++) {
                    Map map1 = maps.get(i);
                    String bmcode = (String) map1.get("bmcode");
                    String custcode = (String) map1.get("custcode");
                    String sbcode = (String) map1.get("sbcode");
                    userid = bmcode + custcode + sbcode;
                    map.put("userid", userid);
                    String jsonString1 = JSONObject.toJSONString(map);
                    logger.info("===============遂平自来水查询应缴信息接口参数:  " + jsonString1);
                    Object post1 = AllHttpUntil.webserviceClient("http://42.96.168.152:8086/ccpay/services/PayService?wsdl", "getcustwaterrate", jsonString1);
                    objects.add(post1);
                }
                logger.info("===============遂平自来水查询应缴信息接口查询:返回  " + JSONObject.toJSONString(objects));
                return objects;
            }


            return "error";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


    @ApiOperation(value = "遂平自来水历史水费查询信息接口", notes = "返回参数")
    @RequestMapping(value = "/getcustlastwaterrecord", method = {RequestMethod.POST})
    public Object getcustlastwaterrecord(@RequestParam("usercode") String usercode,
                                         @RequestParam("startdate") String startdate,
                                         @RequestParam("enddate") String enddate,
                                         @RequestParam("seltype") String seltype) {
        Map<String, String> map = new HashMap<>();
        try {
            String logincode = "4331010743214";
            String loginpass = "E10ADC3949BA59ABBE56E057F20F883E";
            String watercode = "43310101";
            map.put("logincode", logincode);
            map.put("loginpass", loginpass);
            map.put("watercode", watercode);
            map.put("usercode", usercode);
            map.put("seltype", seltype);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============查询客户信息参数:  " + jsonString);
            Object post = AllHttpUntil.webserviceClient("http://42.96.168.152:8086/ccpay/services/PayService?wsdl", "getcustcodeinfo", jsonString);
            logger.info("===============查询客户信息返回:  " + post);
            if (post != null) {
                List<Object> objects = new ArrayList<>();
                map.remove("usercode");
                map.remove("seltype");
                List<Map> maps = JSONObject.parseArray((String) post, Map.class);
                String userid = "";
                for (int i = 0; i < maps.size(); i++) {
                    Map map1 = maps.get(i);
                    String bmcode = (String) map1.get("bmcode");
                    String custcode = (String) map1.get("custcode");
                    String sbcode = (String) map1.get("sbcode");
                    userid = bmcode + custcode + sbcode;
                    map.put("userid", userid);
                    map.put("enddate", enddate);
                    map.put("startdate", startdate);
                    String jsonString1 = JSONObject.toJSONString(map);
                    logger.info("===============遂平自来水历史水费查询信息接口参数:  " + jsonString1);
                    Object post1 = AllHttpUntil.webserviceClient("http://42.96.168.152:8086/ccpay/services/PayService?wsdl", "getcustlastwaterrecord", jsonString1);
                    logger.info("===============遂平自来水历史水费查询信息接口返回:  " + post1);
                    objects.add(post1);
                }
                logger.info("===============遂平自来水历史水费查询信息查询:集合返回  " + JSONObject.toJSONString(objects));
                return objects;
            }


            return "error";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


    @ApiOperation(value = "遂平自来水历史缴费记录查询信息接口", notes = "返回参数")
    @RequestMapping(value = "/getcustlastpayment", method = {RequestMethod.POST})
    public Object getcustlastpayment(@RequestParam("usercode") String usercode,
                                     @RequestParam("startdate") String startdate,
                                     @RequestParam("enddate") String enddate,
                                     @RequestParam("seltype") String seltype) {
        Map<String, String> map = new HashMap<>();
        try {
            String logincode = "4331010743214";
            String loginpass = "E10ADC3949BA59ABBE56E057F20F883E";
            String watercode = "43310101";
            map.put("logincode", logincode);
            map.put("loginpass", loginpass);
            map.put("watercode", watercode);
            map.put("usercode", usercode);
            map.put("seltype", seltype);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============查询客户信息参数:  " + jsonString);
            Object post = AllHttpUntil.webserviceClient("http://42.96.168.152:8086/ccpay/services/PayService?wsdl", "getcustcodeinfo", jsonString);
            logger.info("===============查询客户信息返回:  " + post);
            if (post != null) {
                List<Object> objects = new ArrayList<>();
                map.remove("usercode");
                map.remove("seltype");
                List<Map> maps = JSONObject.parseArray((String) post, Map.class);
                String userid = "";
                for (int i = 0; i < maps.size(); i++) {
                    Map map1 = maps.get(i);
                    String bmcode = (String) map1.get("bmcode");
                    String custcode = (String) map1.get("custcode");
                    String sbcode = (String) map1.get("sbcode");
                    userid = bmcode + custcode + sbcode;
                    map.put("userid", userid);
                    map.put("enddate", enddate);
                    map.put("startdate", startdate);
                    String jsonString1 = JSONObject.toJSONString(map);
                    logger.info("===============遂平自来水历史缴费记录查询信息接口参数:  " + jsonString1);
                    Object post1 = AllHttpUntil.webserviceClient("http://42.96.168.152:8086/ccpay/services/PayService?wsdl", "getcustlastpayment", jsonString1);
                    logger.info("===============遂平自来水历史缴费记录查询信息接口返回:  " + post1);
                    objects.add(post1);
                }
                logger.info("===============遂平自来水历史缴费记录查询信息查询:集合返回  " + JSONObject.toJSONString(objects));
                return objects;
            }


            return "error";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


    @ApiOperation(value = "遂平自来水查询交易汇总信息接口", notes = "返回参数")
    @RequestMapping(value = "/getpaycollect", method = {RequestMethod.POST})
    public Object getpaycollect(@RequestParam("usercode") String usercode,
                                @RequestParam("paydate") String paydate,
                                @RequestParam("seltype") String seltype) {
        Map<String, String> map = new HashMap<>();
        try {
            String logincode = "4331010743214";
            String loginpass = "E10ADC3949BA59ABBE56E057F20F883E";
            String watercode = "43310101";
            map.put("logincode", logincode);
            map.put("loginpass", loginpass);
            map.put("watercode", watercode);
            map.put("usercode", usercode);
            map.put("seltype", seltype);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============查询客户信息参数:  " + jsonString);
            Object post = AllHttpUntil.webserviceClient("http://42.96.168.152:8086/ccpay/services/PayService?wsdl", "getcustcodeinfo", jsonString);
            logger.info("===============查询客户信息返回:  " + post);
            if (post != null) {
                List<Object> objects = new ArrayList<>();
                map.remove("usercode");
                map.remove("seltype");
                List<Map> maps = JSONObject.parseArray((String) post, Map.class);
                String userid = "";
                for (int i = 0; i < maps.size(); i++) {
                    Map map1 = maps.get(i);
                    String bmcode = (String) map1.get("bmcode");
                    String custcode = (String) map1.get("custcode");
                    String sbcode = (String) map1.get("sbcode");
                    userid = bmcode + custcode + sbcode;
                    map.put("userid", userid);
                    map.put("paydate", paydate);
                    String jsonString1 = JSONObject.toJSONString(map);
                    logger.info("===============遂平自来水查询交易汇总信息接口参数:  " + jsonString1);
                    Object post1 = AllHttpUntil.webserviceClient("http://42.96.168.152:8086/ccpay/services/PayService?wsdl", "getpaycollect", jsonString1);
                    logger.info("===============遂平自来水查询交易汇总信息接口返回:  " + post1);
                    objects.add(post1);
                }
                logger.info("===============遂平自来水查询交易汇总信息查询:集合返回  " + JSONObject.toJSONString(objects));
                return objects;
            }


            return "error";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    public static void main(String[] args) throws Exception {
        Map<String, String> map = new HashMap<>();
        String logincode = "4331010743214";
        String loginpass = "E10ADC3949BA59ABBE56E057F20F883E";
        String watercode = "43310101";
        map.put("logincode", logincode);
        map.put("loginpass", loginpass);
        map.put("watercode", watercode);
        map.put("usercode", "010001");
        map.put("seltype", "0");
        String jsonString = JSONObject.toJSONString(map);
        System.out.println("===============遂平自来水查询客户信息接口参数:  " + jsonString);
        Object post = AllHttpUntil.webserviceClient("http://42.96.168.152:8086/ccpay/services/PayService?wsdl", "getcustcodeinfo", jsonString);
        System.out.println("===============遂平自来水查询客户信息接口查询:返回  " + JSONObject.toJSONString(post));

    }


    /**
     * 是否将上传下载的报文写入日志中
     */
    @ApiOperation(value = "遂平自来水水价查询接口", notes = "返回参数")
    @RequestMapping(value = "/sjcx", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<byte[]> spfzjjg(String filename, HttpServletRequest request) {

        try {

            logger.info("===============遂平自来水水价接口查询:返回  ");
            File file = new File("/home/interface_new/file/spwaterprice.PDF");
            //File file =new File("D:\\zywaterprice.jpg");
            InputStream in = new FileInputStream(file);//将该文件加入到输入流之中
            byte[] body = null;
            body = new byte[in.available()];// 返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
            in.read(body);//读入到输入流里面
            HttpHeaders headers = new HttpHeaders();//设置响应头
            headers.add("Content-Disposition", "attachment;filename=spwaterprice.PDF");
            HttpStatus statusCode = HttpStatus.OK;//设置响应吗
            ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;


    }


    @ApiOperation(value = "遂平自来水网点查询接口", notes = "返回参数")
    @RequestMapping(value = "/wdcx", method = {RequestMethod.POST})
    public Object wdcx() {
        Map<String, Object> map = new HashMap<>();
        try {

            map.put("status", "0");
            map.put("mess", "查询成功");
            List<Object> data = new ArrayList<>();
            //中原银行、中国农业银行、中国工商银行、郑州银行 市区各营业网点
            data.add("网点名称：建设路收费厅；网点地址：遂平县建设路中段（老城建局楼下）路南");
            data.add("网点名称：国槐路收费厅；网点地址：遂平县国槐路与开源路交叉口路北");
            map.put("data", data);
            logger.info("===============遂平自来水网点查询接口:返回  " + JSONObject.toJSONString(data));
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "1");
            map.put("mess", "查询失败：" + e.getMessage());
            map.put("data", null);
            return map;
        }

    }
}
