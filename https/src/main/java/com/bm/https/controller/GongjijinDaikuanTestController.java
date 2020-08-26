package com.bm.https.controller;

import com.alibaba.fastjson.JSONArray;
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Api(description  = "公积金贷款测试接口类")
@RestController
@RequestMapping("/gjjZ")
public class GongjijinDaikuanTestController {

    private Logger logger = LoggerFactory.getLogger(GongjijinDaikuanTestController.class);


    @ApiOperation(value = "个人基本信息变更查询")
    @RequestMapping(value = "/jcrbgxxcx",method = {RequestMethod.POST})
    public Object jcrbgxxcx(@RequestParam(value = "zjhm",required = true)String zjhm,
                            @RequestParam(value = "ksrq",required = true)String ksrq,
                            @RequestParam(value = "jsrq",required = true)String jsrq
                            ){
        Map map  = new HashMap<>();
        map.put("zjhm",zjhm);
        map.put("ksrq",ksrq);
        map.put("jsrq",jsrq);
        map.put("zxbm","01");
        String param = JSONObject.toJSONString(map);
        logger.info("公积金接口 个人基本信息变更查询====方法名：jcrbgxxcx=======入参："+param);
        try{
            String url = "http://59.207.236.111:18185/GXPT/jcr/jcrbgxxcx.service";
            logger.info("公积金接口 个人基本信息变更查询====方法名：jcrbgxxcx=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, param);
            logger.info("公积金接口 个人基本信息变更查询====方法名：jcrbgxxcx=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }




    @ApiOperation(value = "个人对账单查询")
    @RequestMapping(value = "/grdzdcx",method = {RequestMethod.POST})
    public Object grdzdcx(@RequestParam(value = "zjhm",required = true)String zjhm){
        Map map  = new HashMap<>();
        map.put("zjhm",zjhm);
        String param = JSONObject.toJSONString(map);
        logger.info("公积金接口 个人对账单查询====方法名：grdzdcx=======入参："+param);
        try{
            String url = "http://59.207.236.111:18185/GXPT/jcr/grdzdcx.service";
            logger.info("公积金接口 个人对账单查询====方法名：grdzdcx=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, param);
            logger.info("公积金接口 个人对账单查询====方法名：grdzdcx=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }


    /**
     * 公积金贷款系列接口
     *
     */

    @ApiOperation(value = "个人贷款试算-贷款利率查询")
    @RequestMapping(value = "/dkllcx",method = {RequestMethod.POST})
    public Object dkllcx(@RequestParam(value = "dknx",required = true)String dknx,
                         @RequestParam(value = "dkcs",required = true)String dkcs,
                         @RequestParam(value = "gfts",required = true)String gfts
                         ){
        Map map  = new HashMap<>();
        map.put("dknx",dknx);
        map.put("dkcs",dkcs);
        map.put("gfts",gfts);
        String param = JSONObject.toJSONString(map);
        logger.info("公积金接口 个人贷款试算-贷款利率查询====方法名：dkllcx=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/jkr/dkllcx";
            logger.info("公积金接口 个人贷款试算-贷款利率查询====方法名：dkllcx=======请求url："+url);
            Object string = AllHttpUntil.sendPost2(url, map);
            logger.info("公积金接口 个人贷款试算-贷款利率查询====方法名：dkllcx=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }

    @ApiOperation(value = "个人贷款试算-月还金额查询")
    @RequestMapping(value = "/sydkjejs",method = {RequestMethod.POST})
    public Object sydkjejs(@RequestParam(value = "dkhkfs",required = true)String dkhkfs,
                           @RequestParam(value = "dkje",required = true)String dkje,
                           @RequestParam(value = "dkqx",required = true)String dkqx,
                           @RequestParam(value = "dkll",required = true)String dkll){
        Map map  = new HashMap<>();
        map.put("dkhkfs",dkhkfs);
        map.put("dkje",dkje);
        map.put("dkqx",dkqx);
        map.put("dkll",dkll);
        String param = JSONObject.toJSONString(map);
        logger.info("公积金接口 个人贷款试算-月还金额查询====方法名：sydkjejs=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/jkr/sydkjejs";
            logger.info("公积金接口 个人贷款试算-月还金额查询====方法名：sydkjejs=======请求url："+url);
            Object string = AllHttpUntil.sendPost2(url, map);
            logger.info("公积金接口 个人贷款试算-月还金额查询====方法名：sydkjejs=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }

    @ApiOperation(value = "个人贷款试算-还款计划和到期总利息查询")
    @RequestMapping(value = "/dkjsq",method = {RequestMethod.POST})
    public Object dkjsq(@RequestParam(value = "dkhkfs",required = true)String dkhkfs,
                        @RequestParam(value = "dkje",required = true)String dkje,
                        @RequestParam(value = "dkqx",required = true)String dkqx,
                        @RequestParam(value = "dklv",required = true)String dklv){
        Map map  = new HashMap<>();
        map.put("dkhkfs",dkhkfs);
        map.put("dkje",dkje);
        map.put("dkqx",dkqx);
        map.put("dklv",dklv);
        String param = JSONObject.toJSONString(map);
        logger.info("公积金接口 个人贷款试算-还款计划和到期总利息查询====方法名：dkjsq=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/jkr/dkjsq";
            logger.info("公积金接口 个人贷款试算-还款计划和到期总利息查询====方法名：dkjsq=======请求url："+url);
            Object string = AllHttpUntil.sendPost2(url, map);
            logger.info("公积金接口 个人贷款试算-还款计划和到期总利息查询====方法名：dkjsq=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }



    @ApiOperation(value = "个人贷款进度查询-贷款进度查询")
    @RequestMapping(value = "/jdcx",method = {RequestMethod.POST})
    public Object jdcx(@RequestParam(value = "jkrzjh",required = true)String jkrzjh){
        Map map  = new HashMap<>();
        map.put("jkrzjh",jkrzjh);
        String param = JSONObject.toJSONString(map);
        logger.info("公积金接口 个人贷款进度查询-贷款进度查询====方法名：jdcx=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/jkr/jdcx";
            logger.info("公积金接口 个人贷款进度查询-贷款进度查询====方法名：jdcx=======请求url："+url);
            Object string = AllHttpUntil.sendPost2(url, map);
            logger.info("公积金接口 个人贷款进度查询-贷款进度查询====方法名：jdcx=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }


    @ApiOperation(value = "个人贷款还款计划查询")
    @RequestMapping(value = "/dkhkjhcx",method = {RequestMethod.POST})
    public Object dkhkjhcx(@RequestParam(value = "jkrzjh",required = true)String jkrzjh){
        Map map  = new HashMap<>();
        map.put("jkrzjh",jkrzjh);
        String param = JSONObject.toJSONString(map);
        logger.info("公积金接口 个人贷款还款计划查询====方法名：dkhkjhcx=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/jkr/dkhkjhcx";
            logger.info("公积金接口 个人贷款还款计划查询====方法名：dkhkjhcx=======请求url："+url);
            Object string = AllHttpUntil.sendPost2(url, map);
            logger.info("公积金接口 个人贷款还款计划查询====方法名：dkhkjhcx=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }




    @ApiOperation(value = "还款卡变更--借款合同信息查询")
    @RequestMapping(value = "/jkhtxxcx",method = {RequestMethod.POST})
    public Object jkhtxxcx(@RequestParam(value = "jkrzjh",required = false)String jkrzjh,
                           @RequestParam(value = "jkhtbh",required = false)String jkhtbh,
                           @RequestParam(value = "grbh",required = false)String grbh){
        Map map  = new HashMap<>();
        map.put("jkrzjh",jkrzjh);
        map.put("jkhtbh",jkhtbh);
        map.put("grbh",grbh);
        String param = JSONObject.toJSONString(map);
        logger.info("公积金接口 还款卡变更--借款合同信息查询====方法名：jkhtxxcx=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/jkr/jkhtxxcx";
            logger.info("公积金接口 还款卡变更--借款合同信息查询====方法名：jkhtxxcx=======请求url："+url);
            Object string = AllHttpUntil.sendPost2(url, map);
            logger.info("公积金接口 还款卡变更--借款合同信息查询====方法名：jkhtxxcx=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }



    @ApiOperation(value = "还款卡变更--还款账号所属银行查询")
    @RequestMapping(value = "/xmwtyhcx",method = {RequestMethod.POST})
    public Object xmwtyhcx(){
        Map map  = new HashMap<>();
        map.put("cxlx"," ");
        String param = JSONObject.toJSONString(map);
        logger.info("公积金接口 还款卡变更--还款账号所属银行查询====方法名：xmwtyhcx=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/jkr/xmwtyhcx";
            logger.info("公积金接口 还款卡变更--还款账号所属银行查询====方法名：xmwtyhcx=======请求url："+url);
            Object string = AllHttpUntil.sendPost2(url, map);
            logger.info("公积金接口 还款卡变更--还款账号所属银行查询====方法名：xmwtyhcx=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }

    @ApiOperation(value = "还款卡变更--还款卡变更完成办理")
    @RequestMapping(value = "/hkzhbglcfq",method = {RequestMethod.POST})
    public Object hkzhbglcfq(@RequestParam(value = "jkhtbh",required = true)String jkhtbh,
                             @RequestParam(value = "xhkzhssyh",required = true)String xhkzhssyh,
                             @RequestParam(value = "xhkzh",required = true)String xhkzh,
                             @RequestParam(value = "kkrxm",required = true)String kkrxm,
                             @RequestParam(value = "kkrzjhm",required = true)String kkrzjhm,
                             @RequestParam(value = "jkrxm",required = true)String jkrxm,
                             @RequestParam(value = "jkrzjh",required = true)String jkrzjh){
        Map map  = new HashMap<>();
        map.put("jkrzjh",jkrzjh);
        map.put("jkrxm",jkrxm);
        map.put("kkrzjhm",kkrzjhm);
        map.put("kkrxm",kkrxm);
        map.put("xhkzh",xhkzh);
        map.put("xhkzhssyh",xhkzhssyh);
        map.put("jkhtbh",jkhtbh);
        String param = JSONObject.toJSONString(map);
        logger.info("公积金接口 还款卡变更--还款卡变更完成办理====方法名：hkzhbglcfq=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/jkr/hkzhbglcfq";
            logger.info("公积金接口 还款卡变更--还款卡变更完成办理====方法名：hkzhbglcfq=======请求url："+url);
            Object string = AllHttpUntil.sendPost2(url, map);
            logger.info("公积金接口 还款卡变更--还款卡变更完成办理====方法名：hkzhbglcfq=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }


    /**
     * 对冲还款协议签订 start
     */
    @ApiOperation(value = "对冲还款协议签订--签约对冲还贷支取清册查询")
    @RequestMapping(value = "/qydchdcx",method = {RequestMethod.POST})
    public Object qydchdcx(@RequestParam(value = "jkhtbh",required = true)String jkhtbh){
        Map map  = new HashMap<>();
        map.put("jkhtbh",jkhtbh);
        String param = JSONObject.toJSONString(map);
        logger.info("公积金接口 对冲还款协议签订--签约对冲还贷支取清册查询====方法名：qydchdcx=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/jkr/qydchdcx";
            logger.info("公积金接口 对冲还款协议签订--签约对冲还贷支取清册查询====方法名：qydchdcx=======请求url："+url);
            Object string = AllHttpUntil.sendPost2(url, map);
            logger.info("公积金接口 对冲还款协议签订--签约对冲还贷支取清册查询====方法名：qydchdcx=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }




    @ApiOperation(value = "对冲还款协议签订--签约对冲还贷完成办理")
    @RequestMapping(value = "/qydchdlcfqnew",method = {RequestMethod.POST})
    public Object qydchdlcfqnew(@RequestParam(value = "jkhtbh",required = true)String jkhtbh,
                             @RequestParam(value = "bglb",required = true)String bglb,
                             @RequestParam(value = "bgyy",required = true)String bgyy,
                             @RequestParam(value = "jkrzq",required = true)String jkrzq ,
                             @RequestParam(value = "pozq",required = true)String pozq ,
                             @RequestParam(value = "gthkr1zq",required = true)String gthkr1zq ,
                             @RequestParam(value = "Gthkr2zq",required = true)String Gthkr2zq,
                                @RequestParam(value = "Gthkr3zq",required = true)String Gthkr3zq ,
                                @RequestParam(value = "sqrq",required = true)String sqrq ,
                                @RequestParam(value = "jkrxm",required = true)String jkrxm ,
                                @RequestParam(value = "jkrzjh",required = true)String jkrzjh){
        Map map  = new HashMap<>();
        map.put("jkhtbh",jkhtbh);
        map.put("bglb",bglb);
        map.put("bgyy",bgyy);
        map.put("jkrzq",jkrzq);
        map.put("pozq",pozq);
        map.put("gthkr1zq",gthkr1zq);
        map.put("Gthkr2zq",Gthkr2zq);
        map.put("Gthkr3zq",Gthkr3zq);
        map.put("sqrq",sqrq);
        map.put("jkrxm",jkrxm);
        map.put("jkrzjh",jkrzjh);
        String param = JSONObject.toJSONString(map);
        logger.info("公积金接口 对冲还款协议签订--签约对冲还贷完成办理====方法名：qydchdlcfqnew=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/jkr/qydchdlcfqnew";
            logger.info("公积金接口 对冲还款协议签订--签约对冲还贷完成办理====方法名：qydchdlcfqnew=======请求url："+url);
            Object string = AllHttpUntil.sendPost2(url, map);
            logger.info("公积金接口 对冲还款协议签订--签约对冲还贷完成办理====方法名：qydchdlcfqnew=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }
    /**
     * 对冲还款协议签订 end
     */



    /**
     * 	提前还款试算器 start
     */
    @ApiOperation(value = "提前还款试算器-计算变更后月还款额和还款总次数")
    @RequestMapping(value = "/bghqccx",method = {RequestMethod.POST})
    public Object bghqccx(@RequestParam(value = "jkhtbh",required = true)String jkhtbh,
                        @RequestParam(value = "tqhkbj",required = true)String tqhkbj,
                        @RequestParam(value = "tqhbfs",required = true)String tqhbfs){
        Map map  = new HashMap<>();
        map.put("jkhtbh",jkhtbh);
        map.put("tqhkbj",tqhkbj);
        map.put("tqhbfs",tqhbfs);
        String param = JSONObject.toJSONString(map);
        logger.info("公积金接口 提前还款试算器-计算变更后月还款额和还款总次数====方法名：bghqccx=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/jkr/bghqccx";
            logger.info("公积金接口 提前还款试算器-计算变更后月还款额和还款总次数====方法名：bghqccx=======请求url："+url);
            Object string = AllHttpUntil.sendPost2(url, map);
            logger.info("公积金接口 提前还款试算器-计算变更后月还款额和还款总次数====方法名：bghqccx=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }


    @ApiOperation(value = "提前还款试算器-计算输入提前还本金额后的还款计划")
    @RequestMapping(value = "/jsqqccx",method = {RequestMethod.POST})
    public Object jsqqccx(@RequestParam(value = "sydkje",required = true)String sydkje,
                          @RequestParam(value = "sydkqx",required = true)String sydkqx,
                          @RequestParam(value = "dklv",required = true)String dklv,
                          @RequestParam(value = "dkhkfsbm",required = true)String dkhkfsbm,
                          @RequestParam(value = "dkffrq",required = true)String dkffrq,
                          @RequestParam(value = "bghyhe",required = true)String bghyhe){
        Map map  = new HashMap<>();
        map.put("sydkje",sydkje);
        map.put("sydkqx",sydkqx);
        map.put("dklv",dklv);
        map.put("dkhkfsbm",dkhkfsbm);
        map.put("dkffrq",dkffrq);
        map.put("bghyhe",bghyhe);
        String param = JSONObject.toJSONString(map);
        logger.info("公积金接口 提前还款试算器-计算输入提前还本金额后的还款计划====方法名：jsqqccx=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/jkr/jsqqccx";
            logger.info("公积金接口 提前还款试算器-计算输入提前还本金额后的还款计划====方法名：jsqqccx=======请求url："+url);
            Object string = AllHttpUntil.sendPost2(url, map);
            logger.info("公积金接口 提前还款试算器-计算输入提前还本金额后的还款计划====方法名：jsqqccx=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }

    /**
     * 提前还款试算器 end
     */

    /**
     * 提前部分还款start
     */
    @ApiOperation(value = "提前部分还款-提前还款校验接口")
    @RequestMapping(value = "/tqhkchk",method = {RequestMethod.POST})
    public Object tqhkchk(@RequestParam(value = "jkhtbh",required = true)String jkhtbh,
                          @RequestParam(value = "ywlx",required = true)String ywlx){
        Map map  = new HashMap<>();
        map.put("jkhtbh",jkhtbh);
        map.put("ywlx",ywlx);
        String param = JSONObject.toJSONString(map);
        logger.info("公积金接口 提前部分还款-提前还款校验接口====方法名：tqhkchk=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/jkr/tqhkchk";
            logger.info("公积金接口 提前部分还款-提前还款校验接口====方法名：tqhkchk=======请求url："+url);
            Object string = AllHttpUntil.sendPost2(url, map);
            logger.info("公积金接口 提前部分还款-提前还款校验接口====方法名：tqhkchk=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }


    @ApiOperation(value = "提前部分还款-提前还款页面支取清册查询")
    @RequestMapping(value = "/tqhkqccx",method = {RequestMethod.POST})
    public Object tqhkqccx(@RequestParam(value = "jkhtbh",required = true)String jkhtbh){
        Map map  = new HashMap<>();
        map.put("jkhtbh",jkhtbh);
        String param = JSONObject.toJSONString(map);
        logger.info("公积金接口 提前部分还款-提前还款页面支取清册查询====方法名：tqhkqccx=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/jkr/tqhkqccx";
            logger.info("公积金接口 提前部分还款-提前还款页面支取清册查询====方法名：tqhkqccx=======请求url："+url);
            Object string = AllHttpUntil.sendPost2(url, map);
            logger.info("公积金接口 提前部分还款-提前还款页面支取清册查询====方法名：tqhkqccx=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }

    @ApiOperation(value = "提前部分还款-计算提前归还本金和提前归还利息")
    @RequestMapping(value = "/tqhklxjs1",method = {RequestMethod.POST})
    public Object tqhklxjs1(@RequestParam(value = "jkhtbh",required = true)String jkhtbh,
                          @RequestParam(value = "bxhj",required = true)String bxhj,
                          @RequestParam(value = "ywlx",required = true)String ywlx){
        Map map  = new HashMap<>();
        map.put("jkhtbh",jkhtbh);
        map.put("bxhj",bxhj);
        map.put("ywlx",ywlx);
        String param = JSONObject.toJSONString(map);
        logger.info("公积金接口 提前部分还款-计算提前归还本金和提前归还利息====方法名：tqhklxjs1=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/jkr/tqhklxjs1";
            logger.info("公积金接口 提前部分还款-计算提前归还本金和提前归还利息====方法名：tqhklxjs1=======请求url："+url);
            Object string = AllHttpUntil.sendPost2(url, map);
            logger.info("公积金接口 提前部分还款-计算提前归还本金和提前归还利息====方法名：tqhklxjs1=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }


    @ApiOperation(value = "提前部分还款-提前还本完成办理")
    @RequestMapping(value = "/jkrtqhblcfq",method = {RequestMethod.POST})
    public Object jkrtqhblcfq(@RequestParam(value = "jkhtbh",required = true)String jkhtbh,
                            @RequestParam(value = "jkrxm",required = true)String jkrxm,
                            @RequestParam(value = "jkrzjh",required = true)String jkrzjh,
                              @RequestParam(value = "grbh",required = true)String grbh,
                              @RequestParam(value = "tqhkje",required = true)String tqhkje,
                              @RequestParam(value = "b056",required = false)String b056 ,
                              @RequestParam(value = "b057",required = false)String b057 ,
                              @RequestParam(value = "b058",required = false)String b058,
                              @RequestParam(value = "b059",required = false)String b059,
                              @RequestParam(value = "b060",required = false)String b060,
                              @RequestParam(value = "b022",required = true)String b022,
                              @RequestParam(value = "e111",required = true)String e111,
                              @RequestParam(value = "e110",required = true)String e110,
                              @RequestParam(value = "b067",required = true)String b067,
                              @RequestParam(value = "b031",required = true)String b031,
                              @RequestParam(value = "xj",required = true)String xj,
                              @RequestParam(value = "zqje",required = true)String zqje,
                              @RequestParam(value = "bxhj",required = true)String bxhj){
        Map map  = new HashMap<>();
        map.put("jkhtbh",jkhtbh);
        map.put("bxhj",bxhj);
        map.put("jkrxm",jkrxm);
        map.put("jkrzjh",jkrzjh);
        map.put("grbh",grbh);
        map.put("tqhkje",tqhkje);
        map.put("b056",b056);
        map.put("b057",b057);
        map.put("b058",b058);
        map.put("b059",b059);
        map.put("b060",b060);
        map.put("b022",b022);
        map.put("e111",e111);
        map.put("e110",e110);
        map.put("b067",b067);
        map.put("b031",b031);
        map.put("xj",xj);
        map.put("zqje",zqje);
        map.put("bxhj",bxhj);
        String param = JSONObject.toJSONString(map);
        logger.info("公积金接口 提前部分还款-提前还本完成办理====方法名：jkrtqhblcfq=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/jkr/jkrtqhblcfq";
            logger.info("公积金接口 提前部分还款-提前还本完成办理====方法名：jkrtqhblcfq=======请求url："+url);
            Object string = AllHttpUntil.sendPost2(url, map);
            logger.info("公积金接口 提前部分还款-提前还本完成办理====方法名：jkrtqhblcfq=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }

    /**
     * 提前部分还款end
     */


    /**
     * 提前结清start
     */

    @ApiOperation(value = "提前结清-提前归还利息和提前还款金额查询")
    @RequestMapping(value = "/tqhklxjs",method = {RequestMethod.POST})
    public Object tqhklxjs(@RequestParam(value = "jkhtbh",required = true)String jkhtbh,
                            @RequestParam(value = "tqhkbj",required = true)String tqhkbj,
                            @RequestParam(value = "ywlx",required = true)String ywlx){
        Map map  = new HashMap<>();
        map.put("jkhtbh",jkhtbh);
        map.put("tqhkbj",tqhkbj);
        map.put("ywlx",ywlx);
        String param = JSONObject.toJSONString(map);
        logger.info("公积金接口 提前结清-提前归还利息和提前还款金额查询====方法名：tqhklxjs=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/jkr/tqhklxjs";
            logger.info("公积金接口 提前结清-提前归还利息和提前还款金额查询====方法名：tqhklxjs=======请求url："+url);
            Object string = AllHttpUntil.sendPost2(url, map);
            logger.info("公积金接口 提前结清-提前归还利息和提前还款金额查询====方法名：tqhklxjs=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }


    @ApiOperation(value = "提前结清-提前结清完成办理")
    @RequestMapping(value = "/jkrtqjqlcfq",method = {RequestMethod.POST})
    public Object jkrtqjqlcfq(@RequestParam(value = "jkhtbh",required = true)String jkhtbh,
                              @RequestParam(value = "jkrxm",required = true)String jkrxm,
                              @RequestParam(value = "jkrzjh",required = true)String jkrzjh,
                              @RequestParam(value = "grbh",required = true)String grbh,
                              @RequestParam(value = "b056",required = false)String b056 ,
                              @RequestParam(value = "b057",required = false)String b057 ,
                              @RequestParam(value = "b058",required = false)String b058,
                              @RequestParam(value = "b059",required = false)String b059,
                              @RequestParam(value = "b060",required = false)String b060,
                              @RequestParam(value = "b022",required = true)String b022,
                              @RequestParam(value = "xj",required = true)String xj,
                              @RequestParam(value = "zqje",required = true)String zqje,
                              @RequestParam(value = "bxhj",required = true)String bxhj){
        Map map  = new HashMap<>();
        map.put("jkhtbh",jkhtbh);
        map.put("bxhj",bxhj);
        map.put("jkrxm",jkrxm);
        map.put("jkrzjh",jkrzjh);
        map.put("grbh",grbh);
        map.put("b056",b056);
        map.put("b057",b057);
        map.put("b058",b058);
        map.put("b059",b059);
        map.put("b060",b060);
        map.put("b022",b022);
        map.put("xj",xj);
        map.put("zqje",zqje);
        map.put("bxhj",bxhj);
        String param = JSONObject.toJSONString(map);
        logger.info("公积金接口 提前结清-提前结清完成办理====方法名：jkrtqjqlcfq=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/jkr/jkrtqjqlcfq";
            logger.info("公积金接口 提前结清-提前结清完成办理====方法名：jkrtqjqlcfq=======请求url："+url);
            Object string = AllHttpUntil.sendPost2(url, map);
            logger.info("公积金接口 提前结清-提前结清完成办理====方法名：jkrtqjqlcfq=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }

    /**
     * 提前结清end
     */



/**
 * 公积金贷款系列接口
 *
 */





}
