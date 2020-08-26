package com.bm.https.controller.province.shengshijianju;

import com.alibaba.fastjson.JSONObject;
import com.bm.https.controller.province.mingzheng.MingZhengInterfaceTestController;
import com.bm.https.untils.ProvinceUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description  = "省市场监督管理局接口类")
@RestController
@RequestMapping("/sjjtest")
public class ShengShiJianJuTestInterface {

    private Logger logger = LoggerFactory.getLogger(ShengShiJianJuTestInterface.class);

    @Value("${jdbc.company.url}")
    private String url;
    @Value("${jdbc.company.name}")
    private String name;
    @Value("${jdbc.company.pwd}")
    private String pwd;
    @ApiOperation(value = "企业基本信息查询" ,  notes="返回参数")
    @RequestMapping(value = "/FDCQCX",method = {RequestMethod.POST})
    public Object FDCQCX(@RequestParam(value = "uniscid",required = true)String uniscid,@RequestParam(value = "regno",required = true)String regno,
                         @RequestParam(value = "entname",required = true)String entname){
        try{
            String appId = "3b20a5fec2ba4d8992d61810c9d66ff9";
            String appKey ="M2IyMGE1ZmVjMmJhNGQ4OTkyZDYxODEwYzlkNjZmZjk6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("uniscid",uniscid);
            map.put("entname",entname);
            map.put("wsdl","");
            map.put("regno",regno);
            map.put("ws-SecurityUsername","0982e70000");
            map.put("ws-SecurityPassword","49102c769499416cadedce79cfc921bf");

            String jsonString = JSONObject.toJSONString(map);
            logger.info("企业基本信息查询参数====="+jsonString);
            Object data = ProvinceUtils.httpData2(map, "http://59.207.107.18:5000/api/sa/ws/soap/opensservice",appId,appKey);
            logger.info("===============企业基本信息查询返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "企业基本信息数据查询" ,  notes="返回参数")
    @RequestMapping(value = "/company",method = {RequestMethod.POST})
    public Object company(@RequestParam(value = "uniscid",required = true)String uniscid,@RequestParam(value = "regno",required = false)String regno,
                          @RequestParam(value = "entname",required = false)String entname){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        logger.info("企业基本信息查询参数=====统一信用代码： "+uniscid);
        logger.info("企业基本信息查询参数=====企业名称： "+entname);
        logger.info("企业基本信息查询参数=====注册码： "+regno);
        long start = System.currentTimeMillis();
        logger.info("企业基本信息查询参数=====开始查询： "+start);
        Map<String, Object> map = new HashMap<>();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, name, pwd);
            String sql  = "select * from HNS_E_BASEINFO t where t.uniscid = '"+uniscid+"' or t.entname = '"+entname+"' or t.regno = '"+regno+"'";
            logger.info("企业基本信息查询参数=====开始查询sql： "+sql);
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet4 = preparedStatement.executeQuery();
            ResultSetMetaData md = resultSet4.getMetaData();// 得到结果集列的属性
            int columnCount = md.getColumnCount();
            List<Map<String,Object>> list = new ArrayList<>();
            while(resultSet4.next()){
                Map<String, Object> maps = new HashMap<>();
                for (int i = 0; i < columnCount; i++) {
                    if("BATCHNO".equals(md.getColumnName(i + 1))||"S_GUID".equals(md.getColumnName(i + 1))||"OPERSTATUS".equals(md.getColumnName(i + 1))||"CTRTIME".equals(md.getColumnName(i + 1))){
                        continue;
                    }
                    maps.put(md.getColumnName(i + 1),resultSet4.getString(md.getColumnName(i + 1)));
                }
                list.add(maps);
            }

            if(list.size()>0){
                map.put("code","0");
                map.put("message","查询成功，查询出"+list.size()+"条数据");
                map.put("data",list);
            }else{
                map.put("code","1");
                map.put("message","查询成功，未查询出数据");
                map.put("data",list);
            }
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============企业基本信息查询返回=="+jsonString);
            logger.info("===============企业基本信息查询结束查询==使用"+(System.currentTimeMillis()-start)/1000+"秒");
            return map;
        }catch (Exception e){
            e.printStackTrace();

            map.put("code","2");
            map.put("message","查询失败，原因："+e.getMessage());
            map.put("data",null);
            return map;
        }finally {
            try {
                if(conn!=null){
                    conn.close();
                }
                if(preparedStatement!=null){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
