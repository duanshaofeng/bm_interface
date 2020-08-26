package com.bm.https.controller.province.mingzheng;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bm.https.untils.ProvinceUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description  = "民政局接口类")
@RestController
@RequestMapping("/mzjtest")
public class MingZhengInterfaceTestController {
    private Logger logger = LoggerFactory.getLogger(MingZhengInterfaceTestController.class);



    @ApiOperation(value = "民政局婚姻证明查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/hunyin",method = {RequestMethod.POST,RequestMethod.GET})
    public Object hunyinInfo(@RequestParam(value = "manid",required = false)String manid,@RequestParam(value = "womanid",required = false)String womanid){
        Object data = null;
        try{
            String appId = "d53f844edcc147b3a9be2eef2479848c";
            String appKey =
                    "ZDUzZjg0NGVkY2MxNDdiM2E5YmUyZWVmMjQ3OTg0OGM6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("manIdcard",manid);
            map.put("womanIdcard",womanid);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============民政婚姻登记信息参数=="+jsonString);
            data = ProvinceUtils.httpData("GET", map, "http://59.207.107.18:5000/api/hyxxcx",appId,appKey);
            logger.info("===============民政婚姻登记信息返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:interfaceDB/interfaceDB@59.207.219.23:1521:orcl", "interfaceDB", "interfaceDB");
            if(data!=null && !data.equals("")) {
                JSONObject jsonObject = JSONObject.parseObject(data.toString());
                JSONObject body = jsonObject.getJSONObject("body");
                String data1 = body.getString("data");
                List<Map<String, Object>> list = JSONArray.parseObject(data1, List.class);
                if (list.size() > 0) {
                    String sql = "insert into hns_mzj_hyxx (ywlb,wname,mname,wid,mid,widtype,midtype,djrq,bfdjrq) values(?,?,?,?,?,?,?,to_date(?,'yyyy-MM-dd hh24:mi:ss'),to_date(?,'yyyy-MM-dd hh24:mi:ss'))";
                    preparedStatement = conn.prepareStatement(sql);
                    for (Map<String, Object> str : list) {
                        preparedStatement.setString(1, (String) str.get("业务类别"));
                        preparedStatement.setString(2, (String) str.get("女方姓名"));
                        preparedStatement.setString(3, (String) str.get("男方姓名"));
                        preparedStatement.setString(4, (String) str.get("女方身份证件号码"));
                        preparedStatement.setString(5, (String) str.get("男方身份证件号码"));
                        preparedStatement.setString(6, (String) str.get("女方身份证件类型"));
                        preparedStatement.setString(7, (String) str.get("男方身份证件类型"));
                        if (str.get("登记日期") != null && !str.get("登记日期").equals("null")) {
                            preparedStatement.setString(8, (String) str.get("登记日期"));

                        } else {
                            preparedStatement.setString(8, "");
                        }
                        if (str.get("补发登记日期") != null && !str.get("补发登记日期").toString().equals("null")) {
                            preparedStatement.setString(9, (String) str.get("补发登记日期"));
                        } else {
                            preparedStatement.setString(9, "");
                        }
                        preparedStatement.addBatch();
                    }
                    preparedStatement.executeBatch();
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                }
            }
            }catch(Exception e){
            }finally {
                try{
                    if(conn!=null)conn.close();
                    if(preparedStatement!=null) preparedStatement.close();
                }catch (Exception e){
                }
            }
        }
    }









}
