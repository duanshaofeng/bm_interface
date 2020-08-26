package com.bm.https.controller.bdc.ziyuan;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bm.https.untils.BDCHttpUtils;
import com.bm.https.untils.BDCZYUtil;
import com.bm.https.untils.ConnectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description  = "不动产资源对接接口类")
@RestController
@RequestMapping("/bdc/test")
public class BDCResourceInfoTestController {
    private Logger logger = LoggerFactory.getLogger(BDCResourceInfoTestController.class);


    @Value("${connect.bdc.name}")
    private String bdcname;
    @Value("${connect.bdc.password}")
    private String bdcpassword;
    @Value("${connect.bdc.url}")
    private String bdcurl;
    @Value("${bdc.fdcqdj.sql}")
    private String fdcqdjsql;
    @Value("${bdc.lq.sql}")
    private String lqsql;
    @Value("${bdc.nyddj.sql}")
    private String nyddj;
    @Value("${bdc.jsydsyq.sql}")
    private String jsydsyq;
    @Value("${bdc.cfdj.sql}")
    private String cfdj;
    @Value("${bdc.ygdj.sql}")
    private String ygdj;
    @Value("${bdc.dydj.sql}")
    private String dydj;


    @Autowired
    private ConnectUtil connectUtil;
    @ApiOperation(value = "不动产存量房资金监管接口" ,  notes="返回参数")
    @RequestMapping(value = "/clzjjg",method = {RequestMethod.POST})
    public Object clzjjg(@RequestParam(value = "zjhm",required = true)String zjhm,@RequestParam(value = "ywlb",required = true)String ywlb){
        try{
            String flag = "clf";//代表是存量房的资金监管
            Map<String, Object> map = new HashMap<>();
            map.put("zjhm",zjhm);
            map.put("ywlb",ywlb);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("不动产存量房资金监管接口:参数  "+jsonString);
            Object bdczjjgData = connectUtil.getBDCZJJGData(zjhm, ywlb,flag);
            String json = JSONObject.toJSONString(bdczjjgData);
            logger.info("===============不动产存量房资金监管接口查询:返回  "+json);
            return bdczjjgData;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }





    @ApiOperation(value = "不动产商品房资金监管接口" ,  notes="返回参数")
    @RequestMapping(value = "/spfzjjg",method = {RequestMethod.POST})
    public Object spfzjjg(@RequestParam(value = "zjhm",required = true)String zjhm,@RequestParam(value = "ywlb",required = true)String ywlb){
        try{
            Object bdczjjgData = null;

            Map<String, Object> map = new HashMap<>();
            map.put("zjhm",zjhm);
            map.put("ywlb",ywlb);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("不动产商品房资金监管接口:参数  "+jsonString);
            if(ywlb.equals("商品房预售资金申请退款")){
                String flag = "spf";//代表是商品房的资金监管
                 bdczjjgData = connectUtil.getBDCZJJGData(zjhm, ywlb,flag);
            }else{
                String flag = "spfqt";//代表是商品房的资金监管
                 bdczjjgData = connectUtil.getBDCZJJGData(zjhm, ywlb,flag);
            }

            String json = JSONObject.toJSONString(bdczjjgData);
            logger.info("===============不动产商品房资金监管接口查询:返回  "+json);
            return bdczjjgData;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "不动产维修资金监管接口" ,  notes="返回参数")
    @RequestMapping(value = "/wxzjjg",method = {RequestMethod.POST})
    public Object wxzjjg(@RequestParam(value = "zjhm",required = true)String zjhm,@RequestParam(value = "ywlb",required = true)String ywlb){
        try{
            String flag = "wx";//代表是维修的资金监管
            Map<String, Object> map = new HashMap<>();
            map.put("zjhm",zjhm);
            map.put("ywlb",ywlb);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("不动产维修资金监管接口:参数  "+jsonString);
            Object bdczjjgData = connectUtil.getBDCZJJGData(zjhm, ywlb,flag);
            String json = JSONObject.toJSONString(bdczjjgData);
            logger.info("===============不动产维修资金监管接口查询:返回  "+json);
            return bdczjjgData;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    @ApiOperation(value = "不动产房地产权登记接口" ,  notes="返回参数")
    @RequestMapping(value = "/fdcq",method = {RequestMethod.POST})
    public Object fdcq(@RequestParam(value = "zjhm",required = true)String zjhm,
                       @RequestParam(value = "name",required = true)String name,
                       @RequestParam(value = "djlx",required = false)String djlx,
                       @RequestParam(value = "bdcqzh",required = false)String bdcqzh,
                       @RequestParam(value = "bdcdyh",required = false)String bdcdyh){
        Map<String, Object> msg = new HashMap<>();
        try{
            Object [] param = null;
            int [] paramType = null;
            String sql = "";
            Map<String, Object> map = new HashMap<>();
            map.put("zjhm",zjhm);
            map.put("name",name);
            String jsonString = JSONObject.toJSONString(map);
            if(org.apache.commons.lang3.StringUtils.isNotEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdyh)){
                sql = fdcqdjsql + " and t2.DJLX = ? ";
                param = new Object[]{zjhm,name,djlx};
                paramType = new int[]{12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isNotEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdyh)){
                sql = fdcqdjsql + " and t2.DJLX = ? and t.BDCQZH = ? ";
                param = new Object[]{zjhm,name,djlx,bdcqzh};
                paramType = new int[]{12,12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isNotEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdyh)){
                sql = fdcqdjsql + " and t2.DJLX = ? and t.BDCDYH = ?";
                param = new Object[]{zjhm,name,djlx,bdcdyh};
                paramType = new int[]{12,12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdyh)){
                sql = fdcqdjsql + " and t.BDCQZH = ? and t.BDCDYH = ?";
                param = new Object[]{zjhm,name,bdcqzh,bdcdyh};
                paramType = new int[]{12,12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isNotEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdyh)){
                sql = fdcqdjsql + " and t2.DJLX = ? and t.BDCQZH = ? and t.BDCDYH = ?";
                param = new Object[]{zjhm,name,djlx,bdcqzh,bdcdyh};
                paramType = new int[]{12,12,12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdyh)){
                sql = fdcqdjsql + " and t.BDCDYH = ?";
                param = new Object[]{zjhm,name,bdcdyh};
                paramType = new int[]{12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdyh)){
                sql = fdcqdjsql + " and t.BDCQZH = ?";
                param = new Object[]{zjhm,name,bdcqzh};
                paramType = new int[]{12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdyh)){
                sql = fdcqdjsql ;
                param = new Object[]{zjhm,name};
                paramType = new int[]{12,12};
            }

            logger.info("===============不动产房地产权登记接口:参数  "+jsonString);
            List<Map<String, Object>> maps = BDCZYUtil.queryList(bdcurl, bdcname, bdcpassword, sql, param, paramType);
            String json = JSONObject.toJSONString(maps);
            logger.info("===============不动产房地产权登记接口:返回  "+json);
            msg.put("success",true);
            msg.put("msg","查询成功");
            msg.put("data",maps);
            return msg;
        }catch (Exception e){
            logger.error(e.getMessage());
            msg.put("success",false);
            msg.put("msg",e.getMessage());
            msg.put("data","");
            return msg;
        }
    }



    @ApiOperation(value = "不动产林权登记接口" ,  notes="返回参数")
    @RequestMapping(value = "/lq",method = {RequestMethod.POST})
    public Object lq(@RequestParam(value = "zjhm",required = true)String zjhm,
                     @RequestParam(value = "name",required = true)String name,
                     @RequestParam(value = "djlx",required = false)String djlx,
                     @RequestParam(value = "bdcqzh",required = false)String bdcqzh,
                     @RequestParam(value = "bdcdyh",required = false)String bdcdyh){
        Map<String, Object> msg = new HashMap<>();
        try{
            Object [] param = null;
            int [] paramType = null;
            String sql = "";
            Map<String, Object> map = new HashMap<>();
            map.put("zjhm",zjhm);
            map.put("name",name);
            String jsonString = JSONObject.toJSONString(map);
            if(org.apache.commons.lang3.StringUtils.isNotEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdyh)){
                sql = lqsql + " and t2.DJLX = ? ";
                param = new Object[]{zjhm,name,djlx};
                paramType = new int[]{12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isNotEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdyh)){
                sql = lqsql + " and t2.DJLX = ? and t.BDCQZH = ? ";
                param = new Object[]{zjhm,name,djlx,bdcqzh};
                paramType = new int[]{12,12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isNotEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdyh)){
                sql = lqsql + " and t2.DJLX = ? and t.BDCDYH = ?";
                param = new Object[]{zjhm,name,djlx,bdcdyh};
                paramType = new int[]{12,12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdyh)){
                sql = lqsql + " and t.BDCQZH = ? and t.BDCDYH = ?";
                param = new Object[]{zjhm,name,bdcqzh,bdcdyh};
                paramType = new int[]{12,12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isNotEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdyh)){
                sql = lqsql + " and t2.DJLX = ? and t.BDCQZH = ? and t.BDCDYH = ?";
                param = new Object[]{zjhm,name,djlx,bdcqzh,bdcdyh};
                paramType = new int[]{12,12,12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdyh)){
                sql = lqsql + " and t.BDCDYH = ?";
                param = new Object[]{zjhm,name,bdcdyh};
                paramType = new int[]{12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdyh)){
                sql = lqsql + " and t.BDCQZH = ?";
                param = new Object[]{zjhm,name,bdcqzh};
                paramType = new int[]{12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdyh)){
                sql = lqsql ;
                param = new Object[]{zjhm,name};
                paramType = new int[]{12,12};
            }

            logger.info("===============不动产林权登记接口:参数  "+jsonString);
            List<Map<String, Object>> maps = BDCZYUtil.queryList(bdcurl, bdcname, bdcpassword, sql, param, paramType);
            String json = JSONObject.toJSONString(maps);
            logger.info("===============不动产林权登记接口:返回  "+json);
            msg.put("success",true);
            msg.put("msg","查询成功");
            msg.put("data",maps);
            return msg;
        }catch (Exception e){
            logger.error(e.getMessage());
            msg.put("success",false);
            msg.put("msg",e.getMessage());
            msg.put("data","");
            return msg;
        }
    }


    @ApiOperation(value = "不动产农用地登记接口" ,  notes="返回参数")
    @RequestMapping(value = "/nyddj",method = {RequestMethod.POST})
    public Object nyddj(@RequestParam(value = "zjhm",required = true)String zjhm,
                        @RequestParam(value = "name",required = true)String name,
                        @RequestParam(value = "djlx",required = false)String djlx,
                        @RequestParam(value = "bdcqzh",required = false)String bdcqzh,
                        @RequestParam(value = "bdcdyh",required = false)String bdcdyh){
        Map<String, Object> msg = new HashMap<>();
        try{
            Object [] param = null;
            int [] paramType = null;
            String sql = "";
            Map<String, Object> map = new HashMap<>();
            map.put("zjhm",zjhm);
            map.put("name",name);
            String jsonString = JSONObject.toJSONString(map);
            if(org.apache.commons.lang3.StringUtils.isNotEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdyh)){
                sql = nyddj + " and t2.DJLX = ? ";
                param = new Object[]{zjhm,name,djlx};
                paramType = new int[]{12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isNotEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdyh)){
                sql = nyddj + " and t2.DJLX = ? and t.BDCQZH = ? ";
                param = new Object[]{zjhm,name,djlx,bdcqzh};
                paramType = new int[]{12,12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isNotEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdyh)){
                sql = nyddj + " and t2.DJLX = ? and t.BDCDYH = ?";
                param = new Object[]{zjhm,name,djlx,bdcdyh};
                paramType = new int[]{12,12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdyh)){
                sql = nyddj + " and t.BDCQZH = ? and t.BDCDYH = ?";
                param = new Object[]{zjhm,name,bdcqzh,bdcdyh};
                paramType = new int[]{12,12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isNotEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdyh)){
                sql = nyddj + " and t2.DJLX = ? and t.BDCQZH = ? and t.BDCDYH = ?";
                param = new Object[]{zjhm,name,djlx,bdcqzh,bdcdyh};
                paramType = new int[]{12,12,12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdyh)){
                sql = nyddj + " and t.BDCDYH = ?";
                param = new Object[]{zjhm,name,bdcdyh};
                paramType = new int[]{12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdyh)){
                sql = nyddj + " and t.BDCQZH = ?";
                param = new Object[]{zjhm,name,bdcqzh};
                paramType = new int[]{12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdyh)){
                sql = nyddj ;
                param = new Object[]{zjhm,name};
                paramType = new int[]{12,12};
            }

            logger.info("===============不动产农用地登记接口:参数  "+jsonString);
            List<Map<String, Object>> maps = BDCZYUtil.queryList(bdcurl, bdcname, bdcpassword, sql, param, paramType);
            String json = JSONObject.toJSONString(maps);
            logger.info("===============不动产农用地登记接口:返回  "+json);
            msg.put("success",true);
            msg.put("msg","查询成功");
            msg.put("data",maps);
            return msg;
        }catch (Exception e){
            logger.error(e.getMessage());
            msg.put("success",false);
            msg.put("msg",e.getMessage());
            msg.put("data","");
            return msg;
        }
    }



    @ApiOperation(value = "不动产建设用地使用权、宅基地使用权登记接口" ,  notes="返回参数")
    @RequestMapping(value = "/jsydsyq",method = {RequestMethod.POST})
    public Object jsydsyq(@RequestParam(value = "zjhm",required = true)String zjhm,
                          @RequestParam(value = "name",required = true)String name,
                          @RequestParam(value = "djlx",required = false)String djlx,
                          @RequestParam(value = "bdcqzh",required = false)String bdcqzh,
                          @RequestParam(value = "bdcdyh",required = false)String bdcdyh){
        Map<String, Object> msg = new HashMap<>();
        try{
            Object [] param = null;
            int [] paramType = null;
            String sql = "";
            Map<String, Object> map = new HashMap<>();
            map.put("zjhm",zjhm);
            map.put("name",name);
            String jsonString = JSONObject.toJSONString(map);
            if(org.apache.commons.lang3.StringUtils.isNotEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdyh)){
                sql = jsydsyq + " and t2.DJLX = ? ";
                param = new Object[]{zjhm,name,djlx};
                paramType = new int[]{12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isNotEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdyh)){
                sql = jsydsyq + " and t2.DJLX = ? and t.BDCQZH = ? ";
                param = new Object[]{zjhm,name,djlx,bdcqzh};
                paramType = new int[]{12,12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isNotEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdyh)){
                sql = jsydsyq + " and t2.DJLX = ? and t.BDCDYH = ?";
                param = new Object[]{zjhm,name,djlx,bdcdyh};
                paramType = new int[]{12,12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdyh)){
                sql = jsydsyq + " and t.BDCQZH = ? and t.BDCDYH = ?";
                param = new Object[]{zjhm,name,bdcqzh,bdcdyh};
                paramType = new int[]{12,12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isNotEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdyh)){
                sql = jsydsyq + " and t2.DJLX = ? and t.BDCQZH = ? and t.BDCDYH = ?";
                param = new Object[]{zjhm,name,djlx,bdcqzh,bdcdyh};
                paramType = new int[]{12,12,12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdyh)){
                sql = jsydsyq + " and t.BDCDYH = ?";
                param = new Object[]{zjhm,name,bdcdyh};
                paramType = new int[]{12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdyh)){
                sql = jsydsyq + " and t.BDCQZH = ?";
                param = new Object[]{zjhm,name,bdcqzh};
                paramType = new int[]{12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isEmpty(djlx)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcqzh)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdyh)){
                sql = jsydsyq ;
                param = new Object[]{zjhm,name};
                paramType = new int[]{12,12};
            }

            logger.info("===============不动产建设用地使用权、宅基地使用权登记接口:参数  "+jsonString);
            List<Map<String, Object>> maps = BDCZYUtil.queryList(bdcurl, bdcname, bdcpassword, sql, param, paramType);
            String json = JSONObject.toJSONString(maps);
            logger.info("===============不动产建设用地使用权、宅基地使用权登记接口:返回  "+json);
            msg.put("success",true);
            msg.put("msg","查询成功");
            msg.put("data",maps);
            return msg;
        }catch (Exception e){
            logger.error(e.getMessage());
            msg.put("success",false);
            msg.put("msg",e.getMessage());
            msg.put("data","");
            return msg;
        }
    }




    @ApiOperation(value = "不动产查封登记接口" ,  notes="返回参数")
    @RequestMapping(value = "/cfdj",method = {RequestMethod.POST})
    public Object cfdj(@RequestParam(value = "cfwh",required = true)String cfwh,
                       @RequestParam(value = "cfjg",required = false)String cfjg,
                       @RequestParam(value = "cflx",required = false)String cflx,
                       @RequestParam(value = "bdcdyh",required = false)String bdcdyh){
        Map<String, Object> msg = new HashMap<>();
        try{
            Object [] param = null;
            int [] paramType = null;
            String sql = "";
            Map<String, Object> map = new HashMap<>();
            map.put("cfwh",cfwh);
            map.put("cfjg",cfjg);
            String jsonString = JSONObject.toJSONString(map);
            if(org.apache.commons.lang3.StringUtils.isNotEmpty(cfjg)&& org.apache.commons.lang3.StringUtils.isEmpty(cflx)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdyh)){
                sql = cfdj + " and t1.cfjg = ? ";
                param = new Object[]{cfwh,cfjg};
                paramType = new int[]{12,12};
            }else if (org.apache.commons.lang3.StringUtils.isNotEmpty(cfjg)&& org.apache.commons.lang3.StringUtils.isNotEmpty(cflx)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdyh)){
                sql = cfdj + " and t1.cfjg = ?  and t1.cflx = ? ";
                param = new Object[]{cfwh,cfjg,cflx};
                paramType = new int[]{12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isNotEmpty(cfjg)&& org.apache.commons.lang3.StringUtils.isEmpty(cflx)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdyh)){
                sql = cfdj + " and t1.cfjg = ? and t1.bdcdyh = ? ";
                param = new Object[]{cfwh,cfjg,bdcdyh};
                paramType = new int[]{12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isEmpty(cfjg)&& org.apache.commons.lang3.StringUtils.isNotEmpty(cflx)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdyh)){
                sql = cfdj + " and t1.cflx = ? and t1.bdcdyh = ? ";
                param = new Object[]{cfwh,cflx,bdcdyh};
                paramType = new int[]{12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isNotEmpty(cfjg)&& org.apache.commons.lang3.StringUtils.isNotEmpty(cflx)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdyh)){
                sql = cfdj + " and t1.cfjg = ? and t1.cflx = ? and t1.bdcdyh = ? ";
                param = new Object[]{cfwh,cfjg,cflx,bdcdyh};
                paramType = new int[]{12,12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isEmpty(cfjg)&& org.apache.commons.lang3.StringUtils.isEmpty(cflx)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdyh)){
                sql = cfdj + " and t1.bdcdyh = ? ";
                param = new Object[]{cfwh,bdcdyh};
                paramType = new int[]{12,12};
            }else if (org.apache.commons.lang3.StringUtils.isEmpty(cfjg)&& org.apache.commons.lang3.StringUtils.isNotEmpty(cflx)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdyh)){
                sql = cfdj + " and t1.cflx = ? ";
                param = new Object[]{cfwh,cflx};
                paramType = new int[]{12,12};
            }else if (org.apache.commons.lang3.StringUtils.isEmpty(cfjg)&& org.apache.commons.lang3.StringUtils.isEmpty(cflx)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdyh)){
                sql = cfdj ;
                param = new Object[]{cfwh};
                paramType = new int[]{12};
            }

            logger.info("===============不动产查封登记接口:参数  "+jsonString);
            List<Map<String, Object>> maps = BDCZYUtil.queryList(bdcurl, bdcname, bdcpassword, sql, param, paramType);
            String json = JSONObject.toJSONString(maps);
            logger.info("===============不动产查封登记接口:返回  "+json);
            msg.put("success",true);
            msg.put("msg","查询成功");
            msg.put("data",maps);
            return msg;
        }catch (Exception e){
            logger.error(e.getMessage());
            msg.put("success",false);
            msg.put("msg",e.getMessage());
            msg.put("data","");
            return msg;
        }
    }


    /**
     * 包含预告登记产权和抵押权登记
     * @param name
     * @param zjhm
     * @param ygdjzl
     * @param bdcdyh
     * @return
     */
    @ApiOperation(value = "不动产预告登记接口" ,  notes="返回参数")
    @RequestMapping(value = "/ygdj",method = {RequestMethod.POST})
    public Object ygdj(@RequestParam(value = "name",required = true)String name,
                       @RequestParam(value = "zjhm",required = true)String zjhm,
                       @RequestParam(value = "ygdjzl",required = false)String ygdjzl,
                       @RequestParam(value = "bdcdjzmh",required = false)String bdcdjzmh,
                       @RequestParam(value = "bdcdyh",required = false)String bdcdyh){
        Map<String, Object> msg = new HashMap<>();
        try{
            Object [] param = null;
            int [] paramType = null;
            String sql = "";
            Map<String, Object> map = new HashMap<>();
            map.put("zjhm",zjhm);
            map.put("name",name);
            String jsonString = JSONObject.toJSONString(map);
            if(org.apache.commons.lang3.StringUtils.isNotEmpty(ygdjzl)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdjzmh)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdyh)){
                sql = ygdj + " and t1.ygdjzl = ? ";
                param = new Object[]{zjhm,name,ygdjzl};
                paramType = new int[]{12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isNotEmpty(ygdjzl)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdjzmh)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdyh)){
                sql = ygdj + " and t1.ygdjzl = ? and t1.bdcdjzmh = ? ";
                param = new Object[]{zjhm,name,ygdjzl,bdcdjzmh};
                paramType = new int[]{12,12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isNotEmpty(ygdjzl)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdjzmh)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdyh)){
                sql = ygdj + " and t1.ygdjzl = ? and t1.BDCDYH = ?";
                param = new Object[]{zjhm,name,ygdjzl,bdcdyh};
                paramType = new int[]{12,12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isEmpty(ygdjzl)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdjzmh)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdyh)){
                sql = ygdj + " and t1.bdcdjzmh = ? and t1.BDCDYH = ?";
                param = new Object[]{zjhm,name,bdcdjzmh,bdcdyh};
                paramType = new int[]{12,12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isNotEmpty(ygdjzl)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdjzmh)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdyh)){
                sql = ygdj + " and t1.ygdjzl = ? and t1.bdcdjzmh = ? and t1.BDCDYH = ?";
                param = new Object[]{zjhm,name,ygdjzl,bdcdjzmh,bdcdyh};
                paramType = new int[]{12,12,12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isEmpty(ygdjzl)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdjzmh)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdyh)){
                sql = ygdj + " and t1.BDCDYH = ?";
                param = new Object[]{zjhm,name,bdcdyh};
                paramType = new int[]{12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isEmpty(ygdjzl)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdjzmh)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdyh)){
                sql = ygdj + " and t1.bdcdjzmh = ?";
                param = new Object[]{zjhm,name,bdcdjzmh};
                paramType = new int[]{12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isEmpty(ygdjzl)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdjzmh)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdyh)){
                sql = ygdj ;
                param = new Object[]{zjhm,name};
                paramType = new int[]{12,12};
            }

            logger.info("===============不动产预告登记接口:参数  "+jsonString);
            List<Map<String, Object>> maps = BDCZYUtil.queryList(bdcurl, bdcname, bdcpassword, sql, param, paramType);
            String json = JSONObject.toJSONString(maps);
            logger.info("===============不动产预告登记接口:返回  "+json);
            msg.put("success",true);
            msg.put("msg","查询成功");
            msg.put("data",maps);
            return msg;
        }catch (Exception e){
            logger.error(e.getMessage());
            msg.put("success",false);
            msg.put("msg",e.getMessage());
            msg.put("data","");
            return msg;
        }
    }


    @ApiOperation(value = "不动产抵押登记接口" ,  notes="返回参数")
    @RequestMapping(value = "/dydj",method = {RequestMethod.POST})
    public Object dydj(@RequestParam(value = "name",required = true)String name,
                       @RequestParam(value = "zjhm",required = true)String zjhm,
                       @RequestParam(value = "bdcdjzmh",required = false)String bdcdjzmh,
                       @RequestParam(value = "bdcdyh",required = false)String bdcdyh){
        Map<String, Object> msg = new HashMap<>();
        try{
            Object [] param = null;
            int [] paramType = null;
            String sql = "";
            Map<String, Object> map = new HashMap<>();
            map.put("zjhm",zjhm);
            map.put("name",name);
            String jsonString = JSONObject.toJSONString(map);
            if(org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdjzmh)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdyh)){
                sql = dydj + " and t1.bdcdjzmh = ? ";
                param = new Object[]{zjhm,name,bdcdjzmh};
                paramType = new int[]{12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isEmpty(bdcdjzmh)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdyh)){
                sql = dydj + " and t1.bdcdyh = ?  ";
                param = new Object[]{zjhm,name,bdcdyh};
                paramType = new int[]{12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdjzmh)&& org.apache.commons.lang3.StringUtils.isNotEmpty(bdcdyh)){
                sql = dydj + " and t1.bdcdjzmh = ? and t1.BDCDYH = ?";
                param = new Object[]{zjhm,name,bdcdjzmh,bdcdyh};
                paramType = new int[]{12,12,12,12};
            }else if (org.apache.commons.lang3.StringUtils.isEmpty(bdcdjzmh)&& org.apache.commons.lang3.StringUtils.isEmpty(bdcdyh)){
                sql = dydj ;
                param = new Object[]{zjhm,name};
                paramType = new int[]{12,12};
            }

            logger.info("===============不动产抵押登记接口:参数  "+jsonString);
            List<Map<String, Object>> maps = BDCZYUtil.queryList(bdcurl, bdcname, bdcpassword, sql, param, paramType);
            String json = JSONObject.toJSONString(maps);
            logger.info("===============不动产抵押登记接口:返回  "+json);
            msg.put("success",true);
            msg.put("msg","查询成功");
            msg.put("data",maps);
            return msg;
        }catch (Exception e){
            e.printStackTrace();
            msg.put("success",false);
            msg.put("msg",e.getMessage());
            msg.put("data","");
            return msg;
        }
    }

}
