package com.bm.https.accessToken.province.zhufang;

import com.alibaba.fastjson.JSONArray;
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

@Api(description  = "住房和城乡建设厅接口类")
@RestController
@RequestMapping("/szf")
public class ZhuFangInterfaceController {
    private Logger logger = LoggerFactory.getLogger(ZhuFangInterfaceController.class);



    @ApiOperation(value = "市场监管公共服务项目信息查询" ,  notes="返回参数")
    @RequestMapping(value = "/scjgxmxx",method = {RequestMethod.POST})
    public Object scjgxmxx(@RequestParam(value = "zsbh",required = true)String zsbh){
        try{
            String appId = "e9ef7b9e20ba446a9adc16be35a5e96b";
            String appKey ="ZTllZjdiOWUyMGJhNDQ2YTlhZGMxNmJlMzVhNWU5NmI6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("zsbh",zsbh);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("市场监管公共服务项目信息查询参数====="+jsonString);
            Object data = ProvinceUtils.httpData2(map, "http://59.207.107.18:5000/api/inservicedemo/microinservice/direct/scjgxmxx/query",appId,appKey);
            logger.info("===============市场监管公共服务项目信息查询返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "建筑施工特种作业操作资格证书查询" ,  notes="返回参数")
    @RequestMapping(value = "/jzsgtzzyczzgzsxx",method = {RequestMethod.POST})
    public Object jzsgtzzyczzgzsxx(@RequestParam(value = "zsbh",required = true)String zsbh,@RequestParam(value = "sfzh",required = false)String sfzh){
        try{
            String appId = "d17afaff19f6499290199cc96af44a80";
            String appKey ="ZDE3YWZhZmYxOWY2NDk5MjkwMTk5Y2M5NmFmNDRhODA6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            map.put("zsbh",zsbh);
            map.put("sfzh",sfzh);
            String jsonString = JSONObject.toJSONString(map);
            logger.info("建筑施工特种作业操作资格证书查询参数====="+jsonString);
            Object data = ProvinceUtils.httpData2(map, "http://59.207.107.18:5000/api/inservicedemo/microinservice/direct/jzsgtzzyczzgzsxx",appId,appKey);
            logger.info("===============建筑施工特种作业操作资格证书查询返回=="+data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @ApiOperation(value = "建筑施工特种作业操作资格证书查询2---根据证书编号查询" ,  notes="返回参数")
    @RequestMapping(value = "/jzsgtzzyczzgzsxx2",method = {RequestMethod.POST})
    public Object jzsgtzzyczzgzsxx2(@RequestParam(value = "zsbh",required = true)String zsbh){
        Object data = null;
        try{
            String appId = "d17afaff19f6499290199cc96af44a80";
            String appKey ="ZDE3YWZhZmYxOWY2NDk5MjkwMTk5Y2M5NmFmNDRhODA6MTIzNDU2";
            Map<String, Object> map1 = new HashMap<>();
            Map<String, String> map = new HashMap<>();
            map.put("zsbh",zsbh);
            map1.put("arguments",map);
            String json = JSONObject.toJSONString(map1);
            logger.info("===============省住建厅_建筑施工特种作业操作资格证书查询 根据证书编号查询 参数：{}",json);
            data = ProvinceUtils.httpDatapostjson(json, "http://59.207.107.18:5000/api/api-gateway/gateway/kxwwbhm5/jzsgtzzyczzgzs1",appId,appKey);
            logger.info("===============省住建厅_建筑施工特种作业操作资格证书查询 根据证书编号查询 返回:{}",data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if(data!=null){
                JSONObject object = JSONObject.parseObject((String) data);
                if(object!=null&&object.containsKey("content")){
                    JSONArray content = JSONArray.parseArray(object.getString("content"));
                    if(content!=null&&content.size()>0){
                        JSONObject object2 =(JSONObject) content.get(0);
                        if(object2!=null&&object2.containsKey("resultSet")){
                            logger.info("===============省住建厅_建筑施工特种作业操作资格证书查询==数据开始入库");
                            AllHttpUntil.insertDBforInterfaceArray(object2.getString("resultSet"),"hns_zjj_jzsgtzzy");
                            logger.info("===============省住建厅_建筑施工特种作业操作资格证书查询==数据入库完毕");
                        }

                    }
                }
            }
        }
    }

    @ApiOperation(value = "建筑施工特种作业操作资格证书查询3--根据身份证号查询" ,  notes="返回参数")
    @RequestMapping(value = "/jzsgtzzyczzgzsxx3",method = {RequestMethod.POST})
    public Object jzsgtzzyczzgzsxx3(@RequestParam(value = "sfzh",required = false)String sfzh){
        Object data = null;
        try{
            String appId = "d17afaff19f6499290199cc96af44a80";
            String appKey ="ZDE3YWZhZmYxOWY2NDk5MjkwMTk5Y2M5NmFmNDRhODA6MTIzNDU2";
            Map<String, String> map = new HashMap<>();
            map.put("sfzh",sfzh);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("arguments",map);
            String json = JSONObject.toJSONString(map1);
            logger.info("===============省住建厅_建筑施工特种作业操作资格证书查询 根据身份证号查询 参数：{}",json);
            data = ProvinceUtils.httpDatapostjson(json, "http://59.207.107.18:5000/api/api-gateway/gateway/kxwwbhm5/jzsgtzzyryzsxx",appId,appKey);
            logger.info("===============省住建厅_建筑施工特种作业操作资格证书查询 根据身份证号查询 返回:{}",data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if(data!=null){
                JSONObject object = JSONObject.parseObject((String) data);
                if(object!=null&&object.containsKey("content")){
                    JSONArray content = JSONArray.parseArray(object.getString("content"));
                    if(content!=null&&content.size()>0){
                        JSONObject object2 =(JSONObject) content.get(0);
                        if(object2!=null&&object2.containsKey("resultSet")){
                            logger.info("===============省住建厅_建筑施工特种作业操作资格证书查询==数据开始入库");
                            JSONArray resultSet = JSONArray.parseArray(object2.getString("resultSet"));
                            if(resultSet!=null&&resultSet.size()>0){
                                for (int i = 0; i < resultSet.size() ;i++){
                                    JSONObject result =(JSONObject) resultSet.get(i);
                                    result.put("sfzh",sfzh);
                                    logger.info("===============省住建厅_建筑施工特种作业操作资格证书查询==数据入库 result:{}",result.toString());
                                    AllHttpUntil.insertDBforInterface(result.toString(),"hns_zjj_jzsgtzzy");
                                }
                            }
                            logger.info("===============省住建厅_建筑施工特种作业操作资格证书查询==数据入库完毕");
                        }

                    }
                }
            }
        }
    }



    @ApiOperation(value = "房地产估价师证书信息查询" ,  notes="返回参数")
    @RequestMapping(value = "/zcgjszsxx",method = {RequestMethod.POST})
    public Object zcgjszsxx(@RequestParam(value = "zjhm",required = true)String zjhm){
        Object data = null;
        try{
            String appId = "41cd6ff5d79049b7a884dd2607d55475";
            String appKey ="NDFjZDZmZjVkNzkwNDliN2E4ODRkZDI2MDdkNTU0NzU6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> map2 = new HashMap<>();
            map.put("zjhm",zjhm);
            map2.put("arguments",map);
            String jsonString = JSONObject.toJSONString(map2);
            logger.info("=============省住建厅_房地产估价师证书信息查询V1.0 参数:{}",jsonString);
            data = ProvinceUtils.httpDatapostjson(jsonString, "http://59.207.107.18:5000/api/api-gateway/gateway/kxwwbhm5/zcgjszsxx",appId,appKey);
            logger.info("=============省住建厅_房地产估价师证书信息查询 返回:{}",data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if(data!=null){
                JSONObject object = JSONObject.parseObject((String) data);
                if(object!=null&&object.containsKey("content")){
                    JSONArray content = JSONArray.parseArray(object.getString("content"));
                    if(content!=null&&content.size()>0){
                        JSONObject object2 =(JSONObject) content.get(0);
                        if(object2!=null&&object2.containsKey("resultSet")){
                            logger.info("===============省住建厅_房地产估价师证书信息查询==数据开始入库");
                            AllHttpUntil.insertDBforInterfaceArray(object2.getString("resultSet"),"HNS_ZJT_FDCGJS");
                            logger.info("===============省住建厅_房地产估价师证书信息查询==数据入库完毕");
                        }

                    }
                }
            }
        }
    }

    @ApiOperation(value = "工程造价咨询企业资质证书信息查询" ,  notes="返回参数")
    @RequestMapping(value = "/gczjzxzz",method = {RequestMethod.POST})
    public Object gczjzxzz(@RequestParam(value = "Corpcode",required = true)String Corpcode){
        Object data = null;
        try{
            String appId = "41cd6ff5d79049b7a884dd2607d55475";
            String appKey ="NDFjZDZmZjVkNzkwNDliN2E4ODRkZDI2MDdkNTU0NzU6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> map2 = new HashMap<>();
            map.put("Corpcode",Corpcode);
            map2.put("arguments",map);
            String jsonString = JSONObject.toJSONString(map2);
            logger.info("=============省住建厅_工程造价咨询企业资质证书信息查询 参数:{}",jsonString);
            data = ProvinceUtils.httpDatapostjson(jsonString, "http://59.207.107.18:5000/api/api-gateway/gateway/kxwwbhm5/gczjzxzz",appId,appKey);
            logger.info("=============省住建厅_工程造价咨询企业资质证书信息查询 返回:{}",data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if(data!=null){
                JSONObject object = JSONObject.parseObject((String) data);
                if(object!=null&&object.containsKey("content")){
                    JSONArray content = JSONArray.parseArray(object.getString("content"));
                    if(content!=null&&content.size()>0){
                        JSONObject object2 =(JSONObject) content.get(0);
                        if(object2!=null&&object2.containsKey("resultSet")){
                            logger.info("===============省住建厅_工程造价咨询企业资质证书信息==数据开始入库");
                            AllHttpUntil.insertDBforInterfaceArray(object2.getString("resultSet"),"HNS_ZJT_gczjzxqy");
                            logger.info("===============省住建厅_工程造价咨询企业资质证书信息==数据入库完毕");
                        }

                    }
                }
            }
        }
    }



    @ApiOperation(value = "二级建造师证书信息查询(通过证书编号查询)" ,  notes="返回参数")
    @RequestMapping(value = "/ejjzszczsxx2",method = {RequestMethod.POST})
    public Object ejjzszczsxx2(@RequestParam(value = "zcbh",required = true)String zcbh){
        try{
            String appId = "41cd6ff5d79049b7a884dd2607d55475";
            String appKey ="NDFjZDZmZjVkNzkwNDliN2E4ODRkZDI2MDdkNTU0NzU6MTIzNDU2";
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> map2 = new HashMap<>();
            map.put("zcbh",zcbh);
            map2.put("arguments",map);
            String jsonString = JSONObject.toJSONString(map2);
            logger.info("=============省住建厅_二级建造师证书信息查询(通过证书编号查询) 参数:{}",jsonString);
            Object data = ProvinceUtils.httpDatapostjson(jsonString, "http://59.207.107.18:5000/api/api-gateway/gateway/kxwwbhm5/ejjzszczsxx2",appId,appKey);
            logger.info("=============省住建厅_二级建造师证书信息查询(通过证书编号查询) 返回:{}",data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
