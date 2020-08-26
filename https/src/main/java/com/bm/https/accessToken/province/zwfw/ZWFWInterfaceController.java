package com.bm.https.accessToken.province.zwfw;

import com.alibaba.fastjson.JSONObject;
import com.bm.https.untils.AllHttpUntil;
import com.bm.https.untils.ProvinceUtils;
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

@Api(description  = "政务服务接口类")
@RestController
@RequestMapping("/zwfw")
public class ZWFWInterfaceController {
    private Logger logger = LoggerFactory.getLogger(ZWFWInterfaceController.class);



    @ApiOperation(value = "政务服务_调用查询服务接口" ,  notes="返回参数")
    @RequestMapping(value = "/zwfw_dy",method = {RequestMethod.POST,RequestMethod.GET})
    public Object zwfw_dy(@RequestParam(value = "hello",required = false)String hello){
        Object data = null;
        try{
            String appId = "3e9f2898dfed45b4a4db4e81bf25db10";
            String appKey = "M2U5ZjI4OThkZmVkNDViNGE0ZGI0ZTgxYmYyNWRiMTA6MTIzNDU2";
            Map<String, String> map = new HashMap<>();
            map.put("hello",hello);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============政务服务_调用查询服务接口参数=="+jsonString);
            data = ProvinceUtils.httpDatapost(map , "http://59.207.107.18:5000/api/zwfw_dy",appId,appKey);
            logger.info("===============政务服务_调用查询服务接口返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "河南政务服务网_办事指南链接地址查询" ,  notes="返回参数")
    @RequestMapping(value = "/getZxblUrlByNsid",method = {RequestMethod.POST,RequestMethod.GET})
    public Object getZxblUrlByNsid(@RequestParam(value = "nsid",required = false)String nsid){
        Object data = null;
        try{
            String appId = "3e9f2898dfed45b4a4db4e81bf25db10";
            String appKey = "M2U5ZjI4OThkZmVkNDViNGE0ZGI0ZTgxYmYyNWRiMTA6MTIzNDU2";
            Map<String, String> map = new HashMap<>();
            map.put("nsid",nsid);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============河南政务服务网_办事指南链接地址查询参数=="+jsonString);
            data = ProvinceUtils.httpDatapost(map , "http://59.207.107.18:5000/api/sxcx/bsfw/getZxblUrlByNsid.do",appId,appKey);
            logger.info("===============河南政务服务网_办事指南链接地址查询返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "法人实名认证接口" ,  notes="返回参数")
    @RequestMapping(value = "/getFrRealNameNoToken",method = {RequestMethod.POST})
    public Object getFrRealNameNoToken(@RequestParam(value = "parameterType",required = true)String parameterType,
                                       @RequestParam(value = "entname ",required = true)String entname,
                                       @RequestParam(value = "uscc",required = false)String uscc,
                                       @RequestParam(value = "regno",required = false)String regno
                                       ){
        Object data = null;
        try{
            String appId = "3e9f2898dfed45b4a4db4e81bf25db10";
            String appKey = "M2U5ZjI4OThkZmVkNDViNGE0ZGI0ZTgxYmYyNWRiMTA6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("parameterType",parameterType);
            map.put("entname",entname);
            map.put("uscc",uscc);
            map.put("regno",regno);
            map.put("userName","sjzy_admin");
            map.put("userDeptCode","001");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============河南政务服务网_法人实名认证接口查询参数=="+jsonString);
            data = ProvinceUtils.httpData2(map , "http://59.207.107.18:5000/api/getFrRealNameNoToken",appId,appKey);
            logger.info("===============河南政务服务网_法人实名认证接口查询返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "法人预设名称接口" ,  notes="返回参数")
    @RequestMapping(value = "/getTFrPresetNameNoToken",method = {RequestMethod.POST})
    public Object getTFrPresetNameNoToken(@RequestParam(value = "pageNumber",required = true)String pageNumber,
                                       @RequestParam(value = "pageSize",required = true)String pageSize,
                                          @RequestParam(value = "entname",required = true)String entname
    ){
        Object data = null;
        try{
            String appId = "3e9f2898dfed45b4a4db4e81bf25db10";
            String appKey = "M2U5ZjI4OThkZmVkNDViNGE0ZGI0ZTgxYmYyNWRiMTA6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("pageNumber",pageNumber);
            map.put("pageSize",pageSize);
            map.put("entname",entname);
            map.put("userName","sjzy_admin");
            map.put("userDeptCode","001");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============河南政务服务网_法人预设名称接口参数=="+jsonString);
            data = ProvinceUtils.httpData2(map , "http://59.207.107.18:5000/api/getTFrPresetNameNoToken",appId,appKey);
            logger.info("===============河南政务服务网_法人预设名称接口查询返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "法人信息核验接口" ,  notes="返回参数")
        @RequestMapping(value = "/getFrMessVerificationByUsccOrRegnoNoToken",method = {RequestMethod.POST})
    public Object getFrMessVerificationByUsccOrRegnoNoToken(@RequestParam(value = "uscc",required = true)String uscc,
                                          @RequestParam(value = "regno",required = true)String regno
    ){
        Object data = null;
        try{
            String appId = "3e9f2898dfed45b4a4db4e81bf25db10";
            String appKey = "M2U5ZjI4OThkZmVkNDViNGE0ZGI0ZTgxYmYyNWRiMTA6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("uscc",uscc);
            map.put("regno",regno);
            map.put("userName","sjzy_admin");
            map.put("userDeptCode","001");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============河南政务服务网_法人信息核验接口参数=="+jsonString);
            data = ProvinceUtils.httpData2(map , "http://59.207.107.18:5000/api/getFrMessVerificationByUsccOrRegnoNoToken",appId,appKey);
            logger.info("===============河南政务服务网_法人信息核验接口查询返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if(data!=null){
                JSONObject object = JSONObject.parseObject((String) data);
                if(object.containsKey("resultData")){
                    logger.info("===============河南政务服务网_法人信息核验接口==数据开始入库");
                    AllHttpUntil.insertDBforInterface(object.getString("resultData"),"hns_zwfw_frxx");
                    logger.info("===============河南政务服务网_法人信息核验接口==数据入库完毕");

                }
            }
        }
    }

    @ApiOperation(value = "法人信息补全接口" ,  notes="返回参数")
    @RequestMapping(value = "/getTFrMessCompleteNoToken",method = {RequestMethod.POST})
    public Object getTFrMessCompleteNoToken(@RequestParam(value = "parameterType",required = true)String parameterType,
                                             @RequestParam(value = "regno",required = false)String regno,
                                            @RequestParam(value = "entname",required = false)String entname,
                                            @RequestParam(value = "uscc",required = false)String uscc
    ){
        Object data = null;
        try{
            String appId = "3e9f2898dfed45b4a4db4e81bf25db10";
            String appKey = "M2U5ZjI4OThkZmVkNDViNGE0ZGI0ZTgxYmYyNWRiMTA6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("uscc",uscc);
            map.put("parameterType",parameterType);
            map.put("entname",entname);
            map.put("regno",regno);
            map.put("userName","sjzy_admin");
            map.put("userDeptCode","001");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============河南政务服务网_法人信息补全接口参数=="+jsonString);
            data = ProvinceUtils.httpData2(map , "http://59.207.107.18:5000/api/getTFrMessCompleteNoToken",appId,appKey);
            logger.info("===============河南政务服务网_法人信息补全接口查询返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if(data!=null){
                JSONObject object = JSONObject.parseObject((String) data);
                if(object.containsKey("resultData")){
                    logger.info("===============河南政务服务网_法人信息补全接口==数据开始入库");
                    AllHttpUntil.insertDBforInterface(object.getString("resultData"),"hns_zwfw_frbqxx");
                    logger.info("===============河南政务服务网_法人信息补全接口==数据入库完毕");

                }
            }
        }
    }



    @ApiOperation(value = "法人实名认证查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/getFrRealNameByLerepNoToken",method = {RequestMethod.POST})
    public Object getFrRealNameByLerepNoToken(@RequestParam(value = "name",required = true)String name,
                                            @RequestParam(value = "idCard",required = true)String idCard,
                                            @RequestParam(value = "entname",required = true)String entname,
                                            @RequestParam(value = "uscc",required = true)String uscc
    ){
        Object data = null;
        try{
            String appId = "3e9f2898dfed45b4a4db4e81bf25db10";
            String appKey = "M2U5ZjI4OThkZmVkNDViNGE0ZGI0ZTgxYmYyNWRiMTA6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("uscc",uscc);
            map.put("name",name);
            map.put("entname",entname);
            map.put("idCard",idCard);
            map.put("userName","sjzy_admin");
            map.put("userDeptCode","001");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============河南政务服务网_法人实名认证查询接口参数=="+jsonString);
            data = ProvinceUtils.httpData2(map , "http://59.207.107.18:5000/api/getFrRealNameByLerepNoToken",appId,appKey);
            logger.info("===============河南政务服务网_法人实名认证查询接口查询返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    @ApiOperation(value = "法人身份信息查询" ,  notes="返回参数")
    @RequestMapping(value = "/getFrRealNameAndLerepNoToken",method = {RequestMethod.POST})
    public Object getFrRealNameAndLerepNoToken(@RequestParam(value = "parameterType",required = true)String parameterType,
                                              @RequestParam(value = "regno",required = false)String regno,
                                              @RequestParam(value = "entname",required = true)String entname,
                                              @RequestParam(value = "uscc",required = false)String uscc
    ){
        Object data = null;
        try{
            String appId = "3e9f2898dfed45b4a4db4e81bf25db10";
            String appKey = "M2U5ZjI4OThkZmVkNDViNGE0ZGI0ZTgxYmYyNWRiMTA6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("uscc",uscc);
            map.put("parameterType",parameterType);
            map.put("entname",entname);
            map.put("regno",regno);
            map.put("userName","sjzy_admin");
            map.put("userDeptCode","001");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============河南政务服务网_法人身份信息查询接口参数=="+jsonString);
            data = ProvinceUtils.httpData2(map , "http://59.207.107.18:5000/api/getFrRealNameAndLerepNoToken",appId,appKey);
            logger.info("===============河南政务服务网_法人身份信息查询接口查询返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "人口重名查询接口" ,  notes="返回参数")
    @RequestMapping(value = "/getAreaReptNameCountNoToken",method = {RequestMethod.POST})
    public Object getAreaReptNameCountNoToken(@RequestParam(value = "popName",required = true)String popName
    ){
        Object data = null;
        try{
            String appId = "3e9f2898dfed45b4a4db4e81bf25db10";
            String appKey = "M2U5ZjI4OThkZmVkNDViNGE0ZGI0ZTgxYmYyNWRiMTA6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("popName",popName);
            map.put("userName","sjzy_admin");
            map.put("userDeptCode","001");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============河南政务服务网_人口重名查询接口参数=="+jsonString);
            data = ProvinceUtils.httpData2(map , "http://59.207.107.18:5000/api/getAreaReptNameCountNoToken",appId,appKey);
            logger.info("===============河南政务服务网_人口重名查询接口查询返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "人口信息核验接口" ,  notes="返回参数")
    @RequestMapping(value = "/getRkMessVerificationByIdCardNoToken",method = {RequestMethod.POST})
    public Object getRkMessVerificationByIdCardNoToken(@RequestParam(value = "idCard",required = true)String idCard
    ){
        Object data = null;
        try{
            String appId = "3e9f2898dfed45b4a4db4e81bf25db10";
            String appKey = "M2U5ZjI4OThkZmVkNDViNGE0ZGI0ZTgxYmYyNWRiMTA6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("idCard",idCard);
            map.put("userName","sjzy_admin");
            map.put("userDeptCode","001");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============河南政务服务网_人口信息核验接口参数=="+jsonString);
            data = ProvinceUtils.httpData2(map , "http://59.207.107.18:5000/api/getRkMessVerificationByIdCardNoToken",appId,appKey);
            logger.info("===============河南政务服务网_人口信息核验接口查询返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if(data!=null){
                JSONObject object = JSONObject.parseObject((String) data);
                if(object.containsKey("resultData")){
                   // JSONObject object2 = JSONObject.parseObject(object.getString("resultData"));
                   // if(object2!=null&&object2.containsKey("baseInfo")){
                    logger.info("===============河南政务服务网_人口信息核验接口==数据开始入库");
                    AllHttpUntil.insertDBforInterface(object.getString("resultData"),"hns_gat_sfxx");
                    logger.info("===============河南政务服务网_人口信息核验接口==数据入库完毕");
                  // }
                }
            }
        }
    }


    @ApiOperation(value = "人口信息补全接口" ,  notes="返回参数")
    @RequestMapping(value = "/getTRkMessCompleteNoToken",method = {RequestMethod.POST})
    public Object getTRkMessCompleteNoToken(@RequestParam(value = "idCard",required = true)String idCard
    ){
        Object data = null;
        try{
            String appId = "3e9f2898dfed45b4a4db4e81bf25db10";
            String appKey = "M2U5ZjI4OThkZmVkNDViNGE0ZGI0ZTgxYmYyNWRiMTA6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("idCard",idCard);
            map.put("userName","sjzy_admin");
            map.put("userDeptCode","001");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============河南政务服务网_人口信息补全接口参数=="+jsonString);
            data = ProvinceUtils.httpData2(map , "http://59.207.107.18:5000/api/getTRkMessCompleteNoToken",appId,appKey);
            logger.info("===============河南政务服务网_人口信息补全接口查询返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if(data!=null){
                JSONObject object = JSONObject.parseObject((String) data);
                if(object.containsKey("resultData")){
                    JSONObject object2 = JSONObject.parseObject(object.getString("resultData"));
                     if(object2!=null&&object2.containsKey("baseInfo")){
                         logger.info("===============河南政务服务网_人口信息补全接口参数==数据开始入库");
                         AllHttpUntil.insertDBforInterfaceArray(object2.getString("baseInfo"),"hns_gat_rkbqxx");
                         logger.info("===============河南政务服务网_人口信息补全接口参数==数据入库完毕");
                     }
                }
            }
        }
    }


    @ApiOperation(value = "人口实名认证接口" ,  notes="返回参数")
    @RequestMapping(value = "/getRkRealName",method = {RequestMethod.POST})
    public Object getRkRealName(@RequestParam(value = "idCard",required = true)String idCard,
                                @RequestParam(value = "popName",required = true)String popName

    ){
        Object data = null;
        try{
            String appId = "3e9f2898dfed45b4a4db4e81bf25db10";
            String appKey = "M2U5ZjI4OThkZmVkNDViNGE0ZGI0ZTgxYmYyNWRiMTA6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("idCard",idCard);
            map.put("popName",popName);
            map.put("userName","sjzy_admin");
            map.put("userDeptCode","001");
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============河南政务服务网_人口实名认证接口参数=="+jsonString);
            data = ProvinceUtils.httpData2(map , "http://59.207.107.18:5000/api/getRkRealName",appId,appKey);
            logger.info("===============河南政务服务网_人口实名认证接口查询返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }








}
