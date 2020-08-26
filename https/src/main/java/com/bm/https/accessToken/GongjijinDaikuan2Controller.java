package com.bm.https.accessToken;

import com.alibaba.fastjson.JSONObject;
import com.bm.https.pojo.GjjRequestData;
import com.bm.https.untils.AllHttpUntil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(description  = "公积金贷款0728正式接口类")
@RestController
@RequestMapping("/gjj")
public class GongjijinDaikuan2Controller {

    private Logger logger = LoggerFactory.getLogger(GongjijinDaikuan2Controller.class);







    @ApiOperation(value = "个人对账单查询")
    @RequestMapping(value = "/SEL_StatementAccount",method = {RequestMethod.POST})
    public Object SEL_StatementAccount(@Valid @RequestBody GjjRequestData gjjData, BindingResult bindingResult){
        String param = JSONObject.toJSONString(gjjData);
        if(bindingResult.hasErrors()){
            String errors = "";
            for(ObjectError error : bindingResult.getAllErrors()){
                errors += error.getDefaultMessage()+" ";
            }
            return com.bm.https.pojo.ResponseBody.failure(errors);
        }
        logger.info("公积金接口 个人对账单查询====方法名：grdzdcx=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/SEL_StatementAccount";
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




    @ApiOperation(value = "个人贷款进度查询-贷款进度查询")
    @RequestMapping(value = "/SEL_PersonalLoanProgress",method = {RequestMethod.POST})
    public Object SEL_PersonalLoanProgress(@Valid @RequestBody GjjRequestData gjjData, BindingResult bindingResult){
        String param = JSONObject.toJSONString(gjjData);
        if(bindingResult.hasErrors()){
            String errors = "";
            for(ObjectError error : bindingResult.getAllErrors()){
                errors += error.getDefaultMessage()+" ";
            }
            return com.bm.https.pojo.ResponseBody.failure(errors);
        }
        logger.info("公积金接口 个人贷款进度查询-贷款进度查询====方法名：jdcx=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/SEL_PersonalLoanProgress";
            logger.info("公积金接口 个人贷款进度查询-贷款进度查询====方法名：jdcx=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, param);
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
    public Object SEL_PersonalPayPlan(@Valid @RequestBody GjjRequestData gjjData, BindingResult bindingResult){
        String param = JSONObject.toJSONString(gjjData);
        if(bindingResult.hasErrors()){
            String errors = "";
            for(ObjectError error : bindingResult.getAllErrors()){
                errors += error.getDefaultMessage()+" ";
            }
            return com.bm.https.pojo.ResponseBody.failure(errors);
        }
        logger.info("公积金接口 个人贷款还款计划查询====方法名：dkhkjhcx=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/SEL_PersonalPayPlan";
            logger.info("公积金接口 个人贷款还款计划查询====方法名：dkhkjhcx=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, param);
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
    public Object Alt_PayBankAccountVerify(@Valid @RequestBody GjjRequestData gjjData, BindingResult bindingResult){
        String param = JSONObject.toJSONString(gjjData);
        if(bindingResult.hasErrors()){
            String errors = "";
            for(ObjectError error : bindingResult.getAllErrors()){
                errors += error.getDefaultMessage()+" ";
            }
            return com.bm.https.pojo.ResponseBody.failure(errors);
        }
        logger.info("公积金接口 还款卡变更--业务办理验证====方法名：jkhtxxcx=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/Alt_PayBankAccountVerify";
            logger.info("公积金接口 还款卡变更--业务办理验证====方法名：jkhtxxcx=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, param);
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
    public Object Alt_PayBankAccountAction(@Valid @RequestBody GjjRequestData gjjData, BindingResult bindingResult){
        String param = JSONObject.toJSONString(gjjData);
        if(bindingResult.hasErrors()){
            String errors = "";
            for(ObjectError error : bindingResult.getAllErrors()){
                errors += error.getDefaultMessage()+" ";
            }
            return com.bm.https.pojo.ResponseBody.failure(errors);
        }
        logger.info("公积金接口 还款卡变更--还款卡变更完成办理====方法名：hkzhbglcfq=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/Alt_PayBankAccountAction";
            logger.info("公积金接口 还款卡变更--还款卡变更完成办理====方法名：hkzhbglcfq=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, param);
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
    public Object Ex_HedgingAgreementVerify(@Valid @RequestBody GjjRequestData gjjData, BindingResult bindingResult){
        String param = JSONObject.toJSONString(gjjData);
        if(bindingResult.hasErrors()){
            String errors = "";
            for(ObjectError error : bindingResult.getAllErrors()){
                errors += error.getDefaultMessage()+" ";
            }
            return com.bm.https.pojo.ResponseBody.failure(errors);
        }
        logger.info("公积金接口 对冲还款协议签订--业务办理验证====方法名：qydchdcx=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/Ex_HedgingAgreementVerify";
            logger.info("公积金接口 对冲还款协议签订--业务办理验证====方法名：qydchdcx=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, param);
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
    public Object Ex_EndSectionLoanSign(@Valid @RequestBody GjjRequestData gjjData, BindingResult bindingResult){
        String param = JSONObject.toJSONString(gjjData);
        if(bindingResult.hasErrors()){
            String errors = "";
            for(ObjectError error : bindingResult.getAllErrors()){
                errors += error.getDefaultMessage()+" ";
            }
            return com.bm.https.pojo.ResponseBody.failure(errors);
        }
        logger.info("公积金接口 对冲还款协议签订--签约对冲还贷完成办理====方法名：qydchdlcfqnew=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/Ex_EndSectionLoanSign";
            logger.info("公积金接口 对冲还款协议签订--签约对冲还贷完成办理====方法名：qydchdlcfqnew=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, param);
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
    public Object SEL_AdvancePayReckon(@Valid @RequestBody GjjRequestData gjjData, BindingResult bindingResult){
        String param = JSONObject.toJSONString(gjjData);
        if(bindingResult.hasErrors()){
            String errors = "";
            for(ObjectError error : bindingResult.getAllErrors()){
                errors += error.getDefaultMessage()+" ";
            }
            return com.bm.https.pojo.ResponseBody.failure(errors);
        }
        logger.info("公积金接口 个人提前还款试算-个人提前还款试算====方法名：jsqqccx=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/SEL_AdvancePayReckon";
            logger.info("公积金接口 个人提前还款试算-个人提前还款试算====方法名：jsqqccx=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, param);
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
    public Object Ex_EndSectionLoanVerify(@Valid @RequestBody GjjRequestData gjjData, BindingResult bindingResult){
        String param = JSONObject.toJSONString(gjjData);
        if(bindingResult.hasErrors()){
            String errors = "";
            for(ObjectError error : bindingResult.getAllErrors()){
                errors += error.getDefaultMessage()+" ";
            }
            return com.bm.https.pojo.ResponseBody.failure(errors);
        }
        logger.info("公积金接口 提前部分还款-提前还款校验接口====方法名：tqhkchk=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/Ex_EndSectionLoanVerify";
            logger.info("公积金接口 提前部分还款-提前还款校验接口====方法名：tqhkchk=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, param);
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
    public Object Ex_EndSectionLoanAction(@Valid @RequestBody GjjRequestData gjjData, BindingResult bindingResult){
        String param = JSONObject.toJSONString(gjjData);
        if(bindingResult.hasErrors()){
            String errors = "";
            for(ObjectError error : bindingResult.getAllErrors()){
                errors += error.getDefaultMessage()+" ";
            }
            return com.bm.https.pojo.ResponseBody.failure(errors);
        }
        logger.info("公积金接口 提前部分还款-提前部分还款====方法名：jkrtqhblcfq=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/Ex_EndSectionLoanAction";
            logger.info("公积金接口 提前部分还款-提前部分还款====方法名：jkrtqhblcfq=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, param);
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
    public Object Ex_EndLoanVerify(@Valid @RequestBody GjjRequestData gjjData, BindingResult bindingResult){
        String param = JSONObject.toJSONString(gjjData);
        if(bindingResult.hasErrors()){
            String errors = "";
            for(ObjectError error : bindingResult.getAllErrors()){
                errors += error.getDefaultMessage()+" ";
            }
            return com.bm.https.pojo.ResponseBody.failure(errors);
        }
        logger.info("公积金接口 提前结清-业务办理验证====方法名：tqhklxjs=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/Ex_EndLoanVerify";
            logger.info("公积金接口 提前结清-业务办理验证====方法名：tqhklxjs=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, param);
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
    public Object Ex_EndLoanAction(@Valid @RequestBody GjjRequestData gjjData, BindingResult bindingResult){
        String param = JSONObject.toJSONString(gjjData);
        if(bindingResult.hasErrors()){
            String errors = "";
            for(ObjectError error : bindingResult.getAllErrors()){
                errors += error.getDefaultMessage()+" ";
            }
            return com.bm.https.pojo.ResponseBody.failure(errors);
        }
        logger.info("公积金接口 提前结清-提前结清完成办理====方法名：jkrtqjqlcfq=======入参："+param);
        try{
            String url = "http://59.207.236.111:81/zw-web/Ex_EndLoanAction";
            logger.info("公积金接口 提前结清-提前结清完成办理====方法名：jkrtqjqlcfq=======请求url："+url);
            Object string = AllHttpUntil.sendPostByJson(url, param);
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
