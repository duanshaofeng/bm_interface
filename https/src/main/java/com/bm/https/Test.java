package com.bm.https;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bm.https.untils.AllHttpUntil;
import com.bm.https.untils.HttpsConnect;
import com.bm.https.untils.ProvinceUtils;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class Test{


    public static void main(String[] args) throws Exception{


      /*  String appId = "e9ef7b9e20ba446a9adc16be35a5e96b";
        String appKey ="ZTllZjdiOWUyMGJhNDQ2YTlhZGMxNmJlMzVhNWU5NmI6MTIzNDU2";
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        map.put("zsbh","4111221903010101-SX-001");
        map2.put("arguments",map);
        String jsonString = JSONObject.toJSONString(map2);

        Object data = ProvinceUtils.httpDatapostjson (jsonString, "http://59.207.107.18:5000/api/inservicedemo/microinservice/direct/scjgxmxx/query",appId,appKey);*/
       /* String appId = "3e9f2898dfed45b4a4db4e81bf25db10";
        String appKey ="M2U5ZjI4OThkZmVkNDViNGE0ZGI0ZTgxYmYyNWRiMTA6MTIzNDU2";*/
        Map<String, String> map = new HashMap<>();
        map.put("jgdm","01");
        map.put("zxbm","01");
      /*  map.put("SQRMC","边秀琴");
        map.put("SQRZJHM","412801196003300849");
        map.put("SLBH","123456");
        map.put("CXYWLSH","123456");*/
        //String xml = "<?xml version='1.0' encoding='utf-8'?><MDEML templateVersion='1.0'><requestinfo><requestDate>2019-09-10 12:23:34</requestDate><requestOrgCode>22123456</requestOrgCode><requestOrgName>河南省数据共享交换平台</requestOrgName></requestinfo><body><data><GMSFZH>62230119891XX</GMSFZH><XM>XXX</XM></data></body></MDEML>";
        //map.put("ser:in2",xml);
        //String jsonString = JSONObject.toJSONString(map);
      //  Object data = ProvinceUtils.httpDatapostjson(jsonString, "http://59.207.107.18:5000/api/gszj/httpproxy",appId,appKey);
        //Object post = AllHttpUntil.sendGet("http://47.97.115.106:9857/WaterQueryService.asmx/GetAreaList");
        String post1 = HttpsConnect.post("https://59.207.219.23:7080/gjj/getJgxx2", map);
      //  String post = HttpsConnect.post("https://59.207.219.23:7080/bdc/GRZFQuery", map);
        System.out.print(post1);
















    }


    static void ByteToFile(byte[] bytes)throws Exception{
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        BufferedImage bi1 =ImageIO.read(bais);
        try {
            File w2 = new File("D:\\00000000003.jpg");//可以是jpg,png,gif格式
            ImageIO.write(bi1, "jpg", w2);//不管输出什么格式图片，此处不需改动
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            bais.close();
        }
    }

    public static void getData() throws Exception{

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;
        PreparedStatement preparedStatement4 = null;
        PreparedStatement preparedStatement5 = null;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection("jdbc:oracle:thin:interfaceDB/interfaceDB@59.207.219.23:1521:orcl", "interfaceDB", "interfaceDB");

        preparedStatement4 = conn.prepareStatement("select t.total from PUB_INTERFACERECORD t where t.interfacename = '住房公积金个人账户信息' order by t.updatetime desc");

        ResultSet resultSet4 = preparedStatement4.executeQuery();
        int total = 0;
        if(resultSet4.next()){
            total = resultSet4.getInt("total");
        }
        if(preparedStatement4!=null){
            preparedStatement4.close();
        }

        preparedStatement2 = conn.prepareStatement(" select cardid,id from ( select rownum r,t.cardid,t.id from PUB_RK_PARM t where rownum <= 15000 and id > '"+total+"' order by id ASC) t where r>0");
        try {
            ResultSet resultSet = preparedStatement2.executeQuery();
            int ii = 0;
            String idss = "";
            while(resultSet.next()){
                ii++;
                String id = resultSet.getString("cardid");
                String ids = resultSet.getString("id");
                Map map  = new HashMap<>();
                map.put("zjhm",id);
                map.put("zxbm","01");
                String s1 = HttpsConnect.post("https://59.207.219.23:7080/gjjZ/getGJTQCX", map);
                if (s1 != null) {
                    JSONObject jsonObject = JSONObject.parseObject(s1);
                    String data = jsonObject.getString("data");

                    List<Map<String, Object>> list = JSONArray.parseObject(data, List.class);
                    System.out.println(list.size());


                    if(list.size()>0){
                        for (Map<String, Object> maps : list) {
                            String sql = "INSERT INTO ZMD_GJJ_GRXX(";
                            String val = " values(";
                            Set<String> set = maps.keySet();
                            int i = 0;
                            for (String str :
                                    set) {
                                i++;
                                if (str.equals("size")) {
                                    continue;
                                }
                                if(str.equals("grjcjsHj")){
                                    continue;
                                }
                                if (i == set.size()) {
                                    sql += str + ") ";
                                    val += "'" + maps.get(str) + "')";
                                } else {
                                    sql += str + ",";
                                    val += "'" + maps.get(str) + "',";
                                }

                            }

                            System.out.println((sql + val));
                            preparedStatement = conn.prepareStatement((sql + val));
                            int i1 = preparedStatement.executeUpdate();
                            System.out.println(i1);
                            if(preparedStatement!=null){
                                preparedStatement.close();
                            }
                        }

                    }
                    idss = ids;






                }
            }
            preparedStatement5 = conn.prepareStatement("insert into PUB_INTERFACERECORD(interfacename,total,Dailytotal) values('住房公积金个人账户信息',"+idss+","+ii+")");
            preparedStatement5.executeUpdate();
            if(preparedStatement5!=null){
                preparedStatement5.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            conn.close();
            if(preparedStatement!=null){
                preparedStatement.close();
            }
            if(preparedStatement2!=null){
                preparedStatement2.close();
            }
            if(preparedStatement3!=null){
                preparedStatement3.close();
            }

        }


    }

    public static void getDwData() throws Exception{

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;
        PreparedStatement preparedStatement4 = null;
        PreparedStatement preparedStatement5 = null;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection("jdbc:oracle:thin:interfaceDB/interfaceDB@59.207.219.23:1521:orcl", "interfaceDB", "interfaceDB");

            preparedStatement4 = conn.prepareStatement("select t.total from PUB_INTERFACERECORD t where t.interfacename = '公积金单位信息' order by t.updatetime desc");

        ResultSet resultSet4 = preparedStatement4.executeQuery();
        int total = 0;
        if(resultSet4.next()){
            total = resultSet4.getInt("total");
        }
        if(preparedStatement4!=null){
            preparedStatement4.close();
        }

        preparedStatement2 = conn.prepareStatement("  select  orgname, id from ( select t.orgname as orgname,t.id as id ,rownum r from PUB_FR_PARM t where rownum <= 15000 and id > "+total+" order by id ASC  ) t where r>0");
        //" select  orgname, id from ( select t.orgname as orgname,t.id as id from PUB_FR_PARM t where rownum <= 15000 and id > '"+total+"' order by id ASC) t where r>0"
        try {
            ResultSet resultSet = preparedStatement2.executeQuery();
            int ii = 0;
            String idss = "";
            while(resultSet.next()){
                ii++;
                String orgname = resultSet.getString("orgname");
                String ids = resultSet.getString("id");
                Map map  = new HashMap<>();
                map.put("cxlb","15");
                map.put("dwxx",orgname);
                String s1 = HttpsConnect.post("https://59.207.219.23:7080/gjjZ/getCRM", map);
                if (s1 != null) {
                    JSONObject jsonObject = JSONObject.parseObject(s1);
                    String data = jsonObject.getString("data");

                    List<Map<String, Object>> list = JSONArray.parseObject(data, List.class);
                    System.out.println(list.size());


                    if(list.size()>0){
                        for (Map<String, Object> maps : list) {
                            String sql = "INSERT INTO ZMD_GJJ_DWXX(";
                            String val = " values(";
                            Set<String> set = maps.keySet();
                            int i = 0;
                            for (String str :
                                    set) {
                                i++;
                                if (str.equals("size")) {
                                    continue;
                                }
                                if(str.equals("grjcjsHj")){
                                    continue;
                                }
                                if (i == set.size()) {
                                    sql += str + ") ";
                                    val += "'" + maps.get(str) + "')";
                                } else {
                                    sql += str + ",";
                                    val += "'" + maps.get(str) + "',";
                                }

                            }

                            System.out.println((sql + val));
                            preparedStatement = conn.prepareStatement((sql + val));
                            int i1 = preparedStatement.executeUpdate();
                            System.out.println(i1);
                            if(preparedStatement!=null){
                                preparedStatement.close();
                            }
                        }

                    }
                    idss = ids;






                }
            }
            preparedStatement5 = conn.prepareStatement("insert into PUB_INTERFACERECORD(interfacename,total,Dailytotal) values('公积金单位信息',"+idss+","+ii+")");
            preparedStatement5.executeUpdate();
            if(preparedStatement5!=null){
                preparedStatement5.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            conn.close();
            if(preparedStatement!=null){
                preparedStatement.close();
            }
            if(preparedStatement2!=null){
                preparedStatement2.close();
            }
            if(preparedStatement3!=null){
                preparedStatement3.close();
            }

        }


    }



    public static void getGGzhData() throws Exception{

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;
        PreparedStatement preparedStatement4 = null;
        PreparedStatement preparedStatement5 = null;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection("jdbc:oracle:thin:interfaceDB/interfaceDB@59.207.219.23:1521:orcl", "interfaceDB", "interfaceDB");

        preparedStatement4 = conn.prepareStatement("select t.total from PUB_INTERFACERECORD t where t.interfacename = '公积金个人账户信息' order by t.updatetime desc");

        ResultSet resultSet4 = preparedStatement4.executeQuery();
        int total = 0;
        if(resultSet4.next()){
            total = resultSet4.getInt("total");
        }
        if(preparedStatement4!=null){
            preparedStatement4.close();
        }

        preparedStatement2 = conn.prepareStatement("  select t.cardid as cardid,t.id as id from PUB_rk_PARM t where id between "+total+" and "+(total+1)+" order by id ASC");
        try {
            ResultSet resultSet = preparedStatement2.executeQuery();
            int ii = 0;
            String idss = "";
            while(resultSet.next()){
                ii++;
                String cardid = resultSet.getString("cardid");
                String ids = resultSet.getString("id");
                Map map  = new HashMap<>();
                map.put("jkrzjh","412823198009101709");

                String s1 = HttpsConnect.post("https://59.207.219.23:7080/gjjZ/getGRDKCX", map);
                if (s1 != null) {
                    JSONObject jsonObject = JSONObject.parseObject(s1);
                    String data = jsonObject.getString("data");

                    List<Map<String, Object>> list = JSONArray.parseObject(data, List.class);
                    System.out.println(list.size());


                    if(list.size()>0){
                        for (Map<String, Object> maps : list) {
                            String sql = "INSERT INTO ZMD_GJJ_GRZHXX(";
                            String val = " values(";
                            Set<String> set = maps.keySet();
                            int i = 0;
                            for (String str :
                                    set) {
                                i++;
                                if (str.equals("size")) {
                                    continue;
                                }
                                if(str.equals("page")){
                                    continue;
                                }
                                if(str.equals("count")){
                                    continue;
                                }
                                if (i == set.size()) {
                                    sql += str + ") ";
                                    val += "'" + maps.get(str) + "')";
                                } else {
                                    sql += str + ",";
                                    val += "'" + maps.get(str) + "',";
                                }

                            }

                            System.out.println((sql + val));
                            preparedStatement = conn.prepareStatement((sql + val));
                            int i1 = preparedStatement.executeUpdate();
                            System.out.println(i1);
                            if(preparedStatement!=null){
                                preparedStatement.close();
                            }
                        }

                    }
                    idss = ids;






                }
            }
            preparedStatement5 = conn.prepareStatement("insert into PUB_INTERFACERECORD(interfacename,total,Dailytotal) values('公积金单位信息',"+idss+","+ii+")");
            preparedStatement5.executeUpdate();
            if(preparedStatement5!=null){
                preparedStatement5.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            conn.close();
            if(preparedStatement!=null){
                preparedStatement.close();
            }
            if(preparedStatement2!=null){
                preparedStatement2.close();
            }
            if(preparedStatement3!=null){
                preparedStatement3.close();
            }

        }


    }


}
 
 