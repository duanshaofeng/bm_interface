package com.bm.https.accessToken.chengguan.zilaishui;

import com.alibaba.fastjson.JSONObject;
import com.bm.https.untils.AllHttpUntil;
import com.bm.https.untils.ConnectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description  = "正阳自来水资源对接接口类")
@RestController
@RequestMapping("/zyzls")
public class ZYResourceInfoController {
    private Logger logger = LoggerFactory.getLogger(ZYResourceInfoController.class);

    @Autowired
    private ConnectUtil connectUtil;
    @ApiOperation(value = "正阳自来水网点查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/wdcx",method = {RequestMethod.POST})
    public Object wdcx(){
        Map<String, Object> map = new HashMap<>();
        try{

            map.put("status","0");
            map.put("mess","查询成功");
            List<Object> data = new ArrayList<>();
            //中原银行、中国农业银行、中国工商银行、郑州银行 市区各营业网点
            data.add("网点地址：正阳县西大街32号 网点电话：0396-8901234");
            map.put("data",data);
            logger.info("===============正阳自来水网点查询接口:返回  "+JSONObject.toJSONString(data));
            return map;
        }catch (Exception e){
            e.printStackTrace();
            map.put("status","1");
            map.put("mess","查询失败："+e.getMessage());
            map.put("data",null);
            return map;
        }
    }

    /**
     * 是否将上传下载的报文写入日志中
     */
    @ApiOperation(value = "正阳自来水水价查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/sjcx", method = { RequestMethod.POST, RequestMethod.GET })
    public ResponseEntity<byte[]> spfzjjg(String filename, HttpServletRequest request, HttpServletResponse response)
    {

        try{

            logger.info("===============正阳自来水水价接口查询:返回  ");
            File file =new File("/home/interface_new/file/zywaterprice.jpg");
            HttpHeaders httpHeader=new HttpHeaders();
            httpHeader.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            httpHeader.setContentDispositionFormData("attachment","zywaterprice.jpg");
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),httpHeader, HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;


    }



    @ApiOperation(value = "正阳自来水水费查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/sfcx",method = {RequestMethod.POST})
    public Object sfxinfo(@RequestParam("customerCode") String customerCode){
        Map<String, String> map = new HashMap<>();
        try{
            map.put("customerCode",customerCode);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============自来水水费查询接口查询:参数：{}",JSONObject.toJSONString(map));
            Object post = AllHttpUntil.sendGet("http://221.176.222.206:11000/PublicWebAPI/GetZYUserInfo.ashx?customerCode="+customerCode);
            logger.info("===============自来水水费查询接口查询:返回 {} ",JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }




}
