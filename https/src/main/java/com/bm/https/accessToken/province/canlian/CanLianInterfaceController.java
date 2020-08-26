package com.bm.https.accessToken.province.canlian;

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

import java.util.HashMap;
import java.util.Map;

@Api(description  = "残联接口类")
@RestController
@RequestMapping("/cl")
public class CanLianInterfaceController {
    private Logger logger = LoggerFactory.getLogger(CanLianInterfaceController.class);



    @ApiOperation(value = "残疾人信息接口" ,  notes="返回参数")
    @RequestMapping(value = "/canjiren",method = {RequestMethod.POST,RequestMethod.GET})
    public Object canjirenInfo(@RequestParam(value = "idcard",required = true)String idcard){
        Object data = null;
        try{
            String appId = "3e9f2898dfed45b4a4db4e81bf25db10";
            String appKey = "M2U5ZjI4OThkZmVkNDViNGE0ZGI0ZTgxYmYyNWRiMTA6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("idcard",idcard);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("===============残疾人信息接口参数=="+jsonString);
            data = ProvinceUtils.httpData2(map, "http://59.207.107.18:5000/api/DisabilitiesPeopleInformation",appId,appKey);
            logger.info("===============残疾人信息接口返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }









}
