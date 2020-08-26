package com.bm.https.controller.zilaishui;

import com.alibaba.fastjson.JSONObject;
import com.bm.https.untils.AllHttpUntil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description  = "平舆自来水资源对接接口类")
@RestController
@RequestMapping("/test/pyzls")
public class PYZaiShuiResourceInfoTestController {
    private Logger logger = LoggerFactory.getLogger(PYZaiShuiResourceInfoTestController.class);




    @ApiOperation(value = "平舆自来水查询客户信息接口" ,  notes="返回参数")
    @RequestMapping(value = "/getcustcodeinfo",method = {RequestMethod.POST})
    public Object getcustcodeinfo(@RequestParam("usercode") String usercode,
                                  @RequestParam("watercode") String watercode,
                                  @RequestParam("seltype") String seltype){
        Map<String, String> map = new HashMap<>();
        try{
            String logincode = "ZW_463400";
            String loginpass = "2B92330FF0930FF53F531F34500C8892";
            map.put("logincode",logincode);
            map.put("loginpass",loginpass);
            map.put("watercode",watercode);
            map.put("usercode",usercode);
            map.put("seltype",seltype);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============平舆自来水查询客户信息接口参数:  "+jsonString);
            Object post = AllHttpUntil.webserviceClient("http://118.190.180.133/services/PayService?wsdl","getcustcodeinfo",jsonString);
            logger.info("===============平舆自来水查询客户信息接口查询:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "平舆自来水缴费返回信息接口" ,  notes="返回参数")
    @RequestMapping(value = "/payment",method = {RequestMethod.POST})
    public Object payment(@RequestParam("userid") String userid,
                          @RequestParam("order") String order,
                          @RequestParam("date") String date,
                          @RequestParam("money") String money,
                          @RequestParam("watercode") String watercode){
        Map<String, String> map = new HashMap<>();
        try{

            String logincode = "ZW_463400";
            String loginpass = "2B92330FF0930FF53F531F34500C8892";
            map.put("userid",userid);
            map.put("order",order);
            map.put("date",date);
            map.put("money",money);
            map.put("watercode",watercode);
            map.put("logincode",logincode);
            map.put("loginpass",loginpass);
            String jsonString1 = JSONObject.toJSONString(map);
            logger.info("===============平舆自来水缴费返回信息接口参数:  "+jsonString1);
            Object post1 = AllHttpUntil.webserviceClient("http://118.190.180.133/services/PayService?wsdl","getcustwaterrate",jsonString1);
            logger.info("===============平舆自来水缴费返回信息接口查询:返回  "+JSONObject.toJSONString(post1));
            return post1;
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }



    @ApiOperation(value = "平舆自来水查询应缴信息接口" ,  notes="返回参数")
    @RequestMapping(value = "/getcustwaterrate",method = {RequestMethod.POST})
    public Object getcustwaterrate(@RequestParam("userid") String userid,
                                   @RequestParam("watercode") String watercode){
        Map<String, String> map = new HashMap<>();
        try{

            String logincode = "ZW_463400";
            String loginpass = "2B92330FF0930FF53F531F34500C8892";
            map.put("userid",userid);
            map.put("watercode",watercode);
            map.put("logincode",logincode);
            map.put("loginpass",loginpass);
            String jsonString1 = JSONObject.toJSONString(map);
            logger.info("===============平舆自来水查询应缴信息接口参数:  "+jsonString1);
            Object post1 = AllHttpUntil.webserviceClient("http://118.190.180.133/services/PayService?wsdl","getcustwaterrate",jsonString1);
            logger.info("===============平舆自来水查询应缴信息接口查询:返回  "+JSONObject.toJSONString(post1));
            return post1;
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }


    @ApiOperation(value = "平舆自来水历史水费查询信息接口" ,  notes="返回参数")
    @RequestMapping(value = "/getcustlastwaterrecord",method = {RequestMethod.POST})
    public Object getcustlastwaterrecord(@RequestParam("watercode") String watercode,
                                         @RequestParam("startdate") String startdate,
                                         @RequestParam("enddate") String enddate,
                                         @RequestParam("userid") String userid){
        Map<String, String> map = new HashMap<>();
        try{
            String logincode = "ZW_463400";
            String loginpass = "2B92330FF0930FF53F531F34500C8892";
            map.put("watercode",watercode);
            map.put("logincode",logincode);
            map.put("loginpass",loginpass);
            map.put("userid",userid);
            map.put("enddate",enddate);
            map.put("startdate",startdate);
            String jsonString1 = JSONObject.toJSONString(map);
            logger.info("===============平舆自来水历史水费查询信息接口参数:  "+jsonString1);
            Object post1 = AllHttpUntil.webserviceClient("http://118.190.180.133/services/PayService?wsdl","getcustlastwaterrecord",jsonString1);
            logger.info("===============平舆自来水历史水费查询信息接口返回:  "+post1);
            logger.info("===============平舆自来水历史水费查询信息查询:集合返回  "+JSONObject.toJSONString(post1));
            return post1;

        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }




    @ApiOperation(value = "平舆自来水历史缴费记录查询信息接口" ,  notes="返回参数")
    @RequestMapping(value = "/getcustlastpayment",method = {RequestMethod.POST})
    public Object getcustlastpayment(@RequestParam("userid") String userid,
                                     @RequestParam("startdate") String startdate,
                                     @RequestParam("enddate") String enddate,
                                     @RequestParam("watercode") String watercode){
        Map<String, String> map = new HashMap<>();
        try{
            String logincode = "ZW_463400";
            String loginpass = "2B92330FF0930FF53F531F34500C8892";
            map.put("logincode",logincode);
            map.put("loginpass",loginpass);
            map.put("watercode",watercode);
            map.put("userid",userid);
            map.put("enddate",enddate);
            map.put("startdate",startdate);
            String jsonString1 = JSONObject.toJSONString(map);
            logger.info("===============平舆自来水历史缴费记录查询信息接口参数:  "+jsonString1);
            Object post1 = AllHttpUntil.webserviceClient("http://118.190.180.133/services/PayService?wsdl","getcustlastpayment",jsonString1);
            logger.info("===============平舆自来水历史缴费记录查询信息接口返回:  "+post1);
            logger.info("===============平舆自来水历史缴费记录查询信息查询:集合返回  "+JSONObject.toJSONString(post1));
            return post1;
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }



    @ApiOperation(value = "平舆自来水查询交易汇总信息接口" ,  notes="返回参数")
    @RequestMapping(value = "/getpaycollect",method = {RequestMethod.POST})
    public Object getpaycollect(@RequestParam("usercode") String usercode,
                                @RequestParam("paydate") String paydate,
                                @RequestParam("seltype") String seltype){
        Map<String, String> map = new HashMap<>();
        try{
            String logincode = "ZW_463400";
            String loginpass = "2B92330FF0930FF53F531F34500C8892";
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
                    logger.info("===============平舆自来水查询交易汇总信息接口参数:  "+jsonString1);
                    Object post1 = AllHttpUntil.webserviceClient("http://118.190.180.133/services/PayService?wsdl","getpaycollect",jsonString1);
                    logger.info("===============平舆自来水查询交易汇总信息接口返回:  "+post1);
                    objects.add(post1);
                }
                logger.info("===============平舆自来水查询交易汇总信息查询:集合返回  "+JSONObject.toJSONString(objects));
                return objects;
            }


            return "error";
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    public static void main(String[] args) throws Exception {
        Map<String, String> map = new HashMap<>();
        String logincode = "ZW_463400";
        String loginpass = "2B92330FF0930FF53F531F34500C8892";
        String watercode = "46340001";
        map.put("logincode", logincode);
        map.put("loginpass", loginpass);
        map.put("watercode", watercode);
        map.put("userid", "1307001701");
       // map.put("seltype", "0");
        String jsonString = JSONObject.toJSONString(map);
        System.out.println("===============平舆自来水查询客户信息接口参数:  " + jsonString);
        Object post = AllHttpUntil.webserviceClient("http://118.190.180.133/services/PayService?wsdl", "getcustlastwaterrecord", jsonString);
        System.out.println("===============平舆自来水查询客户信息接口查询:返回  " + JSONObject.toJSONString(post));

    }


    /**
     * 是否将上传下载的报文写入日志中
     */
    @ApiOperation(value = "平舆自来水水价查询接口", notes = "返回参数")
    @RequestMapping(value = "/sjcx", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<byte[]> spfzjjg(String filename, HttpServletRequest request) {

        try {

            logger.info("===============平舆自来水水价接口查询:返回  ");
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


    @ApiOperation(value = "平舆自来水网点查询接口", notes = "返回参数")
    @RequestMapping(value = "/wdcx", method = {RequestMethod.POST})
    public Object wdcx() {
        Map<String, Object> map = new HashMap<>();
        try {

            map.put("status", "0");
            map.put("mess", "查询成功");
            List<Object> data = new ArrayList<>();
            //中原银行、中国农业银行、中国工商银行、郑州银行 市区各营业网点
            data.add("网点名称：收费大厅；网点地址：东皇大道219号；电话：5022652");
            data.add("网点名称：市民之家；网点地址：健康路与阳城大道交叉口东100米路北市民之家政务服务中心大厅；电话 ：无");
            map.put("data", data);
            logger.info("===============平舆自来水网点查询接口:返回  " + JSONObject.toJSONString(data));
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
