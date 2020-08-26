package com.bm.https.controller.province.zhaoshengban;

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

@Api(description  = "招生办接口类")
@RestController
@RequestMapping("/zsbtest")
public class ZhaoshengBanInterfaceTestController {
    private Logger logger = LoggerFactory.getLogger(ZhaoshengBanInterfaceTestController.class);



    @ApiOperation(value = "身份证信息查询目录接口" ,  notes="返回参数")
    @RequestMapping(value = "/sfcx",method = {RequestMethod.POST,RequestMethod.GET})
    public Object hunyinInfo(@RequestParam(value = "idCard",required = false)String idCard,@RequestParam(value = "userName",required = false)String userName,@RequestParam(value = "userDeptCode",required = false)String userDeptCode){
        Object data = null;
        try{
            String appId = "3b20a5fec2ba4d8992d61810c9d66ff9";
            String appKey ="M2IyMGE1ZmVjMmJhNGQ4OTkyZDYxODEwYzlkNjZmZjk6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("idCard",idCard);
            map.put("userDeptCode","11412800MB160464XL");
            map.put("userName","驻马店市政务服务和大数据管理局");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("身份信息接口参数====="+jsonString);
            data = ProvinceUtils.httpData2(map, "http://59.207.107.18:5000/api/apis-new/getRkMessVerificationByIdCardNoToken",appId,appKey);
            logger.info("===============身份证信息查询目录返回=="+data);
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
                    JSONObject jsonObject1 = jsonObject.getJSONObject("resultData");
                    logger.info("插入数据中.....，插入数据为：{}",jsonObject1);
                    if (jsonObject1!=null) {
                        String sql = "insert into hns_zsb_sfxx (POPNAME,POPGENDER,POPNATION,POPBIRTHDATE,POPADDR,IDCARD,QFJGGAJGMC,YXQQSRQ,YXQJZRQ) values(?,?,?,?,?,?,?,?,?)";
                        preparedStatement = conn.prepareStatement(sql);
                        preparedStatement.setString(1, (String) jsonObject1.get("popName"));
                        preparedStatement.setString(2, (String) jsonObject1.get("popGender"));
                        preparedStatement.setString(3, (String) jsonObject1.get("popNation"));
                        preparedStatement.setString(4, (String) jsonObject1.get("popBirthDate"));
                        preparedStatement.setString(5, (String) jsonObject1.get("popAddr"));
                        preparedStatement.setString(6, (String) jsonObject1.get("idCard"));
                        preparedStatement.setString(7, (String) jsonObject1.get("qfjgGajgmc"));
                        preparedStatement.setString(8, (String) jsonObject1.get("yxqqsrq"));
                        preparedStatement.setString(9, (String) jsonObject1.get("yxqjzrq"));
                        preparedStatement.executeUpdate();
                        if (preparedStatement != null) {
                            preparedStatement.close();
                        }
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
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
