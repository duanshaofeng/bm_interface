package com.bm.https.controller;

import com.alibaba.fastjson.JSONObject;
import com.bm.https.untils.AllHttpUntil;
import com.bm.https.untils.gjj.TestCryptographForGjj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(description  = "公积金贷款0727测试接口类")
@RestController
@RequestMapping("/gjjZ2")
public class GongjijinDaikuanTest2Controller {

    private Logger logger = LoggerFactory.getLogger(GongjijinDaikuanTest2Controller.class);







    @ApiOperation(value = "个人对账单查询")
    @RequestMapping(value = "/SEL_StatementAccount",method = {RequestMethod.POST})
    public Object SEL_StatementAccount(@RequestParam(value = "zxbm",required = true)String zxbm,
                                       @RequestParam(value = "zjhm",required = true)String zjhm,
                                       @RequestParam(value = "xingming",required = true)String xingming,
                                       @RequestParam(value = "jkhtbh",required = false)String jkhtbh,
                                       @RequestParam(value = "ksrq",required = true)String ksrq,
                                       @RequestParam(value = "jsrq",required = true)String jsrq,
                                       @RequestParam(value = "page",required = false)String page,
                                       @RequestParam(value = "size",required = false)String size){
        Map map  = new HashMap<>();
        map.put("zxbm",zxbm);
        map.put("zjhm",zjhm);
        map.put("xingming",xingming);
        map.put("jkhtbh",jkhtbh);
        map.put("ksrq",ksrq);
        map.put("jsrq",jsrq);
        map.put("page",page);
        map.put("size",size);
        String param = JSONObject.toJSONString(map);
        String o = TestCryptographForGjj.crypForGjj(param);
        logger.info("公积金接口 个人对账单查询====方法名：grdzdcx=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/SEL_StatementAccount";
            logger.info("公积金接口 个人对账单查询====方法名：grdzdcx=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, o);
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




    @ApiOperation(value = "个人贷款进度查询-贷款进度查询")
    @RequestMapping(value = "/SEL_PersonalLoanProgress",method = {RequestMethod.POST})
    public Object SEL_PersonalLoanProgress(@RequestParam(value = "zxbm",required = true)String zxbm,
                                           @RequestParam(value = "zjhm",required = true)String zjhm,
                                           @RequestParam(value = "xingming",required = true)String xingming,
                                           @RequestParam(value = "jkhtbh",required = false)String jkhtbh){
        Map map  = new HashMap<>();
        map.put("zxbm",zxbm);
        map.put("zjhm",zjhm);
        map.put("xingming",xingming);
        map.put("jkhtbh",jkhtbh);
        String param = JSONObject.toJSONString(map);
        String o = TestCryptographForGjj.crypForGjj(param);
        logger.info("公积金接口 个人贷款进度查询-贷款进度查询====方法名：jdcx=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/SEL_PersonalLoanProgress";
            logger.info("公积金接口 个人贷款进度查询-贷款进度查询====方法名：jdcx=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, o);
            logger.info("公积金接口 个人贷款进度查询-贷款进度查询====方法名：jdcx=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }


    @ApiOperation(value = "个人贷款还款计划查询")
    @RequestMapping(value = "/SEL_PersonalPayPlan",method = {RequestMethod.POST})
    public Object SEL_PersonalPayPlan(@RequestParam(value = "zxbm",required = true)String zxbm,
                                      @RequestParam(value = "zjhm",required = true)String zjhm,
                                      @RequestParam(value = "xingming",required = true)String xingming,
                                      @RequestParam(value = "jkhtbh",required = false)String jkhtbh,
                                      @RequestParam(value = "ksrq",required = true)String ksrq,
                                      @RequestParam(value = "jsrq",required = true)String jsrq,
                                      @RequestParam(value = "page",required = false)String page,
                                      @RequestParam(value = "size",required = false)String size){
        Map map  = new HashMap<>();
        map.put("zxbm",zxbm);
        map.put("zjhm",zjhm);
        map.put("xingming",xingming);
        map.put("jkhtbh",jkhtbh);
        map.put("ksrq",ksrq);
        map.put("jsrq",jsrq);
        map.put("page",page);
        map.put("size",size);
        String param = JSONObject.toJSONString(map);
        String o = TestCryptographForGjj.crypForGjj(param);
        logger.info("公积金接口 个人贷款还款计划查询====方法名：dkhkjhcx=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/SEL_PersonalPayPlan";
            logger.info("公积金接口 个人贷款还款计划查询====方法名：dkhkjhcx=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, o);
            logger.info("公积金接口 个人贷款还款计划查询====方法名：dkhkjhcx=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }


    /**
     *还款卡变更 start
     */

    @ApiOperation(value = "还款卡变更--业务办理验证")
    @RequestMapping(value = "/Alt_PayBankAccountVerify",method = {RequestMethod.POST})
    public Object Alt_PayBankAccountVerify(@RequestParam(value = "zxbm",required = true)String zxbm,
                           @RequestParam(value = "zjhm",required = true)String zjhm,
                           @RequestParam(value = "xingming",required = true)String xingming){
        Map map  = new HashMap<>();
        map.put("zxbm",zxbm);
        map.put("zjhm",zjhm);
        map.put("xingming",xingming);
        String param = JSONObject.toJSONString(map);
        String o = TestCryptographForGjj.crypForGjj(param);
        logger.info("公积金接口 还款卡变更--业务办理验证====方法名：jkhtxxcx=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/Alt_PayBankAccountVerify";
            logger.info("公积金接口 还款卡变更--业务办理验证====方法名：jkhtxxcx=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, o);
            logger.info("公积金接口 还款卡变更--业务办理验证====方法名：jkhtxxcx=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }



    @ApiOperation(value = "还款卡变更--还款卡变更完成办理")
    @RequestMapping(value = "/Alt_PayBankAccountAction",method = {RequestMethod.POST})
    public Object Alt_PayBankAccountAction(@RequestParam(value = "zxbm",required = true)String zxbm,
                             @RequestParam(value = "zjhm",required = true)String zjhm,
                             @RequestParam(value = "xingming",required = true)String xingming,
                             @RequestParam(value = "hkkh",required = true)String hkkh,
                             @RequestParam(value = "sjhm",required = true)String sjhm){
        Map map  = new HashMap<>();
        map.put("zxbm",zxbm);
        map.put("zjhm",zjhm);
        map.put("xingming",xingming);
        map.put("hkkh",hkkh);
        map.put("sjhm",sjhm);
        String param = JSONObject.toJSONString(map);
        String o = TestCryptographForGjj.crypForGjj(param);
        logger.info("公积金接口 还款卡变更--还款卡变更完成办理====方法名：hkzhbglcfq=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/Alt_PayBankAccountAction";
            logger.info("公积金接口 还款卡变更--还款卡变更完成办理====方法名：hkzhbglcfq=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, o);
            logger.info("公积金接口 还款卡变更--还款卡变更完成办理====方法名：hkzhbglcfq=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }

    /**
     * 还款卡变更end
     */

    /**
     * 对冲还款协议签订 start
     */
    @ApiOperation(value = "对冲还款协议签订--业务办理验证")
    @RequestMapping(value = "/Ex_HedgingAgreementVerify",method = {RequestMethod.POST})
    public Object Ex_HedgingAgreementVerify(@RequestParam(value = "jkhtbh",required = false)String jkhtbh,
                                            @RequestParam(value = "zxbm",required = true)String zxbm,
                                            @RequestParam(value = "zjhm",required = true)String zjhm,
                                            @RequestParam(value = "xingming",required = true)String xingming){
        Map map  = new HashMap<>();
        map.put("jkhtbh",jkhtbh);
        map.put("zxbm",zxbm);
        map.put("zjhm",zjhm);
        map.put("xingming",xingming);
        String param = JSONObject.toJSONString(map);
        String o = TestCryptographForGjj.crypForGjj(param);
        logger.info("公积金接口 对冲还款协议签订--业务办理验证====方法名：qydchdcx=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/Ex_HedgingAgreementVerify";
            logger.info("公积金接口 对冲还款协议签订--业务办理验证====方法名：qydchdcx=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, o);
            logger.info("公积金接口 对冲还款协议签订--业务办理验证====方法名：qydchdcx=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }




    @ApiOperation(value = "对冲还款协议签订--签约对冲还贷完成办理")
    @RequestMapping(value = "/Ex_EndSectionLoanSign",method = {RequestMethod.POST})
    public Object Ex_EndSectionLoanSign(@RequestParam(value = "zxbm",required = true)String zxbm,
                             @RequestParam(value = "dkjg",required = true)String dkjg,
                             @RequestParam(value = "jkrxm",required = true)String jkrxm,
                             @RequestParam(value = "jkrzjh",required = true)String jkrzjh ,
                             @RequestParam(value = "sjhm",required = true)String sjhm ,
                             @RequestParam(value = "dwmc",required = true)String dwmc ,
                             @RequestParam(value = "grzh",required = true)String grzh,
                                @RequestParam(value = "poxm",required = false)String poxm ,
                                @RequestParam(value = "pozjhm",required = false)String pozjhm ,
                                @RequestParam(value = "posjhm",required = false)String posjhm ,
                                @RequestParam(value = "podwmc",required = false)String podwmc,

                                @RequestParam(value = "pogrzh",required = false)String pogrzh ,
                                @RequestParam(value = "jkhtbh",required = true)String jkhtbh,
                                @RequestParam(value = "yhbx",required = true)String yhbx ,
                                @RequestParam(value = "ydhkr",required = true)String ydhkr ,
                                @RequestParam(value = "yhmc",required = true)String yhmc ,
                                @RequestParam(value = "dkzh",required = true)String dkzh,

                                @RequestParam(value = "podkzh",required = false)String podkzh ,
                                @RequestParam(value = "kksx",required = true)String kksx,
                                @RequestParam(value = "xynr",required = true)String xynr    ,
                                @RequestParam(value = "qyr",required = true)String qyr ,
                                @RequestParam(value = "qyrzjhm",required = false)String qyrzjhm ,
                                @RequestParam(value = "qysj",required = true)String qysj){
        Map map  = new HashMap<>();
        map.put("zxbm",zxbm);
        map.put("dkjg",dkjg);
        map.put("jkrxm",jkrxm);
        map.put("jkrzjh",jkrzjh);
        map.put("sjhm",sjhm);
        map.put("dwmc",dwmc);
        map.put("grzh",grzh);
        map.put("poxm",poxm);
        map.put("pozjhm",pozjhm);
        map.put("posjhm",posjhm);
        map.put("podwmc",podwmc);
        map.put("pogrzh",pogrzh);
        map.put("jkhtbh",jkhtbh);
        map.put("yhbx",yhbx);
        map.put("ydhkr",ydhkr);
        map.put("yhmc",yhmc);
        map.put("dkzh",dkzh);
        map.put("podkzh",podkzh);
        map.put("kksx",kksx);
        map.put("xynr",xynr);
        map.put("qyr",qyr);
        map.put("qyrzjhm",qyrzjhm);
        map.put("qysj",qysj);
        String param = JSONObject.toJSONString(map);
        String o = TestCryptographForGjj.crypForGjj(param);
        logger.info("公积金接口 对冲还款协议签订--签约对冲还贷完成办理====方法名：qydchdlcfqnew=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/Ex_EndSectionLoanSign";
            logger.info("公积金接口 对冲还款协议签订--签约对冲还贷完成办理====方法名：qydchdlcfqnew=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, o);
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



    @ApiOperation(value = "个人提前还款试算-个人提前还款试算")
    @RequestMapping(value = "/SEL_AdvancePayReckon",method = {RequestMethod.POST})
    public Object SEL_AdvancePayReckon(@RequestParam(value = "zxbm",required = true)String zxbm,
                          @RequestParam(value = "zjhm",required = true)String zjhm,
                          @RequestParam(value = "xingming",required = true)String xingming,
                          @RequestParam(value = "jkhtbh",required = true)String jkhtbh,
                          @RequestParam(value = "tqhkbj",required = true)String tqhkbj){
        Map map  = new HashMap<>();
        map.put("zxbm",zxbm);
        map.put("zjhm",zjhm);
        map.put("xingming",xingming);
        map.put("jkhtbh",jkhtbh);
        map.put("tqhkbj",tqhkbj);
        String param = JSONObject.toJSONString(map);
        String o = TestCryptographForGjj.crypForGjj(param);
        logger.info("公积金接口 个人提前还款试算-个人提前还款试算====方法名：jsqqccx=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/SEL_AdvancePayReckon";
            logger.info("公积金接口 个人提前还款试算-个人提前还款试算====方法名：jsqqccx=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, o);
            logger.info("公积金接口 个人提前还款试算-个人提前还款试算====方法名：jsqqccx=======返回值："+string);
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
    @RequestMapping(value = "/Ex_EndSectionLoanVerify",method = {RequestMethod.POST})
    public Object Ex_EndSectionLoanVerify(@RequestParam(value = "zxbm",required = true)String zxbm,
                                          @RequestParam(value = "xingming",required = true)String xingming,
                                            @RequestParam(value = "zjhm",required = true)String zjhm){
        Map map  = new HashMap<>();
        map.put("zxbm",zxbm);
        map.put("xingming",xingming);
        map.put("zjhm",zjhm);
        String param = JSONObject.toJSONString(map);
        String o = TestCryptographForGjj.crypForGjj(param);
        logger.info("公积金接口 提前部分还款-提前还款校验接口====方法名：tqhkchk=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/Ex_EndSectionLoanVerify";
            logger.info("公积金接口 提前部分还款-提前还款校验接口====方法名：tqhkchk=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, o);
            logger.info("公积金接口 提前部分还款-提前还款校验接口====方法名：tqhkchk=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }





    @ApiOperation(value = "提前部分还款-提前部分还款")
    @RequestMapping(value = "/Ex_EndSectionLoanAction",method = {RequestMethod.POST})
    public Object Ex_EndSectionLoanAction(@RequestParam(value = "jkhtbh",required = true)String jkhtbh,
                            @RequestParam(value = "jkrxm",required = true)String jkrxm,
                            @RequestParam(value = "jkrzjh",required = true)String jkrzjh,
                              @RequestParam(value = "jkje",required = true)String jkje,
                              @RequestParam(value = "dkqs",required = true)String dkqs,
                              @RequestParam(value = "sybj",required = true)String sybj ,
                              @RequestParam(value = "syqs",required = true)String syqs ,
                              @RequestParam(value = "hzny",required = true)String hzny,
                              @RequestParam(value = "swtyhmc",required = true)String swtyhmc,
                              @RequestParam(value = "hkzh",required = true)String hkzh,
                              @RequestParam(value = "tqhkbj",required = true)String tqhkbj,
                              @RequestParam(value = "yhlx",required = true)String yhlx,
                              @RequestParam(value = "yqbj",required = true)String yqbj,
                              @RequestParam(value = "yqlx",required = true)String yqlx,
                              @RequestParam(value = "yqfx",required = true)String yqfx,
                              @RequestParam(value = "bjhj",required = true)String bjhj,
                              @RequestParam(value = "lxhj",required = true)String lxhj,
                              @RequestParam(value = "jehj",required = true)String jehj,
                                          @RequestParam(value = "sjhm",required = true)String sjhm,
                                          @RequestParam(value = "gjjhkje",required = true)String gjjhkje,
                                          @RequestParam(value = "yhkhkje",required = true)String yhkhkje){
        Map map  = new HashMap<>();
        map.put("jkhtbh",jkhtbh);
        map.put("jkrxm",jkrxm);
        map.put("jkrzjh",jkrzjh);
        map.put("jkje",jkje);
        map.put("dkqs",dkqs);
        map.put("sybj",sybj);
        map.put("syqs",syqs);
        map.put("hzny",hzny);
        map.put("swtyhmc",swtyhmc);
        map.put("hkzh",hkzh);
        map.put("tqhkbj",tqhkbj);
        map.put("yhlx",yhlx);
        map.put("yqbj",yqbj);
        map.put("yqlx",yqlx);
        map.put("yqfx",yqfx);
        map.put("bjhj",bjhj);
        map.put("lxhj",lxhj);
        map.put("jehj",jehj);
        map.put("sjhm",sjhm);
        map.put("gjjhkje",gjjhkje);
        map.put("yhkhkje",yhkhkje);
        String param = JSONObject.toJSONString(map);
        String o = TestCryptographForGjj.crypForGjj(param);
        logger.info("公积金接口 提前部分还款-提前部分还款====方法名：jkrtqhblcfq=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/Ex_EndSectionLoanAction";
            logger.info("公积金接口 提前部分还款-提前部分还款====方法名：jkrtqhblcfq=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, o);
            logger.info("公积金接口 提前部分还款-提前部分还款====方法名：jkrtqhblcfq=======返回值："+string);
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

    @ApiOperation(value = "提前结清-业务办理验证")
    @RequestMapping(value = "/Ex_EndLoanVerify",method = {RequestMethod.POST})
    public Object Ex_EndLoanVerify(@RequestParam(value = "zxbm",required = true)String zxbm,
                            @RequestParam(value = "zjhm",required = true)String zjhm,
                            @RequestParam(value = "xingming",required = true)String xingming){
        Map map  = new HashMap<>();
        map.put("zxbm",zxbm);
        map.put("zjhm",zjhm);
        map.put("xingming",xingming);
        String param = JSONObject.toJSONString(map);
        String o = TestCryptographForGjj.crypForGjj(param);
        logger.info("公积金接口 提前结清-业务办理验证====方法名：tqhklxjs=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/Ex_EndLoanVerify";
            logger.info("公积金接口 提前结清-业务办理验证====方法名：tqhklxjs=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, o);
            logger.info("公积金接口 提前结清-业务办理验证====方法名：tqhklxjs=======返回值："+string);
            return string;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error:"+param;
        }
    }



    @ApiOperation(value = "提前结清-提前结清完成办理")
    @RequestMapping(value = "/Ex_EndLoanAction",method = {RequestMethod.POST})
    public Object Ex_EndLoanAction(@RequestParam(value = "jkhtbh",required = true)String jkhtbh,
                              @RequestParam(value = "jkrxm",required = true)String jkrxm,
                              @RequestParam(value = "jkrzjh",required = true)String jkrzjh,
                              @RequestParam(value = "jkje",required = true)String jkje,
                              @RequestParam(value = "dkqs",required = true)String dkqs ,
                              @RequestParam(value = "sybj",required = true)String sybj ,
                              @RequestParam(value = "syqs",required = true)String syqs,
                              @RequestParam(value = "hzny",required = true)String hzny,
                              @RequestParam(value = "swtyhmc",required = true)String swtyhmc,
                              @RequestParam(value = "hkzh",required = true)String hkzh,
                              @RequestParam(value = "yqbj",required = true)String yqbj,
                              @RequestParam(value = "yqlx",required = true)String yqlx,
                              @RequestParam(value = "yhlx",required = true)String yhlx,
                                   @RequestParam(value = "yqfx",required = true)String yqfx,
                                   @RequestParam(value = "bjhj",required = true)String bjhj,
                                   @RequestParam(value = "lxhj",required = true)String lxhj,
                                   @RequestParam(value = "jehj",required = true)String jehj,
                                   @RequestParam(value = "grzhye",required = true)String grzhye,
                                   @RequestParam(value = "sjhm",required = true)String sjhm,
                                   @RequestParam(value = "gjjhkje",required = true)String gjjhkje,
                                   @RequestParam(value = "yhkhkje",required = true)String yhkhkje


    ){
        Map map  = new HashMap<>();
        map.put("jkhtbh",jkhtbh);
        map.put("swtyhmc",swtyhmc);
        map.put("hkzh",hkzh);
        map.put("jkrxm",jkrxm);
        map.put("jkrzjh",jkrzjh);
        map.put("jkje",jkje);
        map.put("dkqs",dkqs);
        map.put("sybj",sybj);
        map.put("syqs",syqs);
        map.put("hzny",hzny);
        map.put("yqbj",yqbj);
        map.put("yqlx",yqlx);
        map.put("yhlx",yhlx);
        map.put("yqfx",yqfx);
        map.put("bjhj",bjhj);
        map.put("lxhj",lxhj);

        map.put("jehj",jehj);
        map.put("grzhye",grzhye);
        map.put("sjhm",sjhm);
        map.put("gjjhkje",gjjhkje);
        map.put("yhkhkje",yhkhkje);
        String param = JSONObject.toJSONString(map);
        String o = TestCryptographForGjj.crypForGjj(param);
        logger.info("公积金接口 提前结清-提前结清完成办理====方法名：jkrtqjqlcfq=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/Ex_EndLoanAction";
            logger.info("公积金接口 提前结清-提前结清完成办理====方法名：jkrtqjqlcfq=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, o);
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
