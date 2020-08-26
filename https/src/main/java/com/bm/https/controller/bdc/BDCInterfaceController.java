package com.bm.https.controller.bdc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bm.https.controller.HttpsController;
import com.bm.https.pojo.*;
import com.bm.https.untils.AllHttpUntil;
import com.bm.https.untils.BDCHttpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Api(description  = "不动产高频事项接口类")
@RestController
@RequestMapping("/bdc/test")
public class BDCInterfaceController {
    private Logger logger = LoggerFactory.getLogger(BDCInterfaceController.class);
    @Autowired
    @Qualifier("secondJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BDCHttpUtils bdcHttpUtils;
    @ApiOperation(value = "不动产证明接口" ,  notes="返回参数")
    @RequestMapping(value = "/BDCZM",method = {RequestMethod.POST})
    public Object getBDCZM(@RequestParam(value = "sfzh",required = false)String sfzh,@RequestParam(value = "name",required = false)String name,
                           @RequestParam(value = "BDCQZH",required = false)String BDCQZH,@RequestParam(value = "BDCDYH",required = false)String BDCDYH){
        try{

            Map<String, Object> map = new HashMap<>();
            map.put("QLRMC",name);
            map.put("QLRZJHM",sfzh);
            map.put("QLRZJZL","1");
            map.put("BDCQZH",BDCQZH);
            map.put("BDCDYH",BDCDYH);
            map.put("YHM","00000003");
            map.put("MM","870156");
            //String ss = "{\"QLRMC\":\"\",\"QLRZJHM\":\"\",\"QLRZJZL\":\"\",\"BDCQZH\":\"\",\"BDCDYH\":\"\",\"YHM\":\"00000003\",\"MM\":\"870156\"}";
            String ss = "{\"YHM\": \"00000003\",\"MM\": \"870156\",\"BDCQZH\":\"\",\"QLRMC\": \"\",\"QLRZJZL\":\"\",\"QLRZJHM\":\"\",\"BDCDYH\":\"\"}";
            String jsonString = JSONObject.toJSONString(map);
            logger.info(jsonString);
            Object bdcPost = bdcHttpUtils.bdcPost("http://59.207.236.45:8000/Api/BDCComQuery",jsonString);
            //String tdsyq = getResult(bdcPost, "TDSYQ");
            logger.info(bdcPost+"===============");
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }






    @ApiOperation(value = "西平县不动产证明接口" ,  notes="返回参数")
    @RequestMapping(value = "/xp/BDCZM",method = {RequestMethod.POST})
    public Object getxpBDCZM(@RequestParam(value = "sfzh",required = false)String sfzh,@RequestParam(value = "name",required = false)String name,
                           @RequestParam(value = "BDCQZH",required = false)String BDCQZH,@RequestParam(value = "BDCDYH",required = false)String BDCDYH){
        try{

            Map<String, Object> map = new HashMap<>();
            map.put("QLRMC",name);
            map.put("QLRZJHM",sfzh);
            map.put("QLRZJZL","1");
            map.put("BDCQZH",BDCQZH);
            map.put("BDCDYH",BDCDYH);
            map.put("YHM","00000001");
            map.put("MM","888888");
            String jsonString = JSONObject.toJSONString(map);
            logger.info(jsonString);
            Object bdcPost = bdcHttpUtils.bdcPost("http://59.207.227.249:1010/Api/BDCComQuery",jsonString);
            logger.info(bdcPost+"===============");
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "遂平县不动产证明接口" ,  notes="返回参数")
    @RequestMapping(value = "/sp/BDCZM",method = {RequestMethod.POST})
    public Object getspBDCZM(@RequestParam(value = "sfzh",required = false)String sfzh,@RequestParam(value = "name",required = false)String name,
                             @RequestParam(value = "BDCQZH",required = false)String BDCQZH,@RequestParam(value = "BDCDYH",required = false)String BDCDYH){
        try{

            Map<String, Object> map = new HashMap<>();
            map.put("QLRMC",name);
            map.put("QLRZJHM",sfzh);
            map.put("QLRZJZL","1");
            map.put("BDCQZH",BDCQZH);
            map.put("BDCDYH",BDCDYH);
            map.put("YHM","00000001");
            map.put("MM","888888");
           String jsonString = JSONObject.toJSONString(map);
            logger.info(jsonString);
            Object bdcPost = bdcHttpUtils.bdcPost("http://59.207.225.249:1010/Api/BDCComQuery",jsonString);
            logger.info(bdcPost+"===============");
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "平舆县不动产证明接口" ,  notes="返回参数")
    @RequestMapping(value = "/py/BDCZM",method = {RequestMethod.POST})
    public Object getpyBDCZM(@RequestParam(value = "sfzh",required = false)String sfzh,@RequestParam(value = "name",required = false)String name,
                             @RequestParam(value = "BDCQZH",required = false)String BDCQZH,@RequestParam(value = "BDCDYH",required = false)String BDCDYH){
        try{

            Map<String, Object> map = new HashMap<>();
            map.put("QLRMC",name);
            map.put("QLRZJHM",sfzh);
            map.put("QLRZJZL","1");
            map.put("BDCQZH",BDCQZH);
            map.put("BDCDYH",BDCDYH);
            map.put("YHM","00000001");
            map.put("MM","888888");
            String jsonString = JSONObject.toJSONString(map);
            logger.info(jsonString);
            Object bdcPost = bdcHttpUtils.bdcPost("http://59.207.233.249:8000/Api/BDCComQuery",jsonString);
            logger.info(bdcPost+"===============");
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }




    @ApiOperation(value = "确山县不动产证明接口" ,  notes="返回参数")
    @RequestMapping(value = "/qs/BDCZM",method = {RequestMethod.POST})
    public Object getqsBDCZM(@RequestParam(value = "sfzh",required = false)String sfzh,@RequestParam(value = "name",required = false)String name,
                             @RequestParam(value = "BDCQZH",required = false)String BDCQZH,@RequestParam(value = "BDCDYH",required = false)String BDCDYH){
        try{

            Map<String, Object> map = new HashMap<>();
            map.put("QLRMC",name);
            map.put("QLRZJHM",sfzh);
            map.put("QLRZJZL","1");
            map.put("BDCQZH",BDCQZH);
            map.put("BDCDYH",BDCDYH);
            map.put("YHM","00000001");
            map.put("MM","888888");
            String jsonString = JSONObject.toJSONString(map);
            logger.info(jsonString);
            Object bdcPost = bdcHttpUtils.bdcPost("http://59.207.221.249:8000/gpsx/Api/BDCComQuery",jsonString);
            logger.info(bdcPost+"===============");
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }





    @ApiOperation(value = "汝南县不动产证明接口" ,  notes="返回参数")
    @RequestMapping(value = "/rn/BDCZM",method = {RequestMethod.POST})
    public Object getrnBDCZM(@RequestParam(value = "sfzh",required = false)String sfzh,@RequestParam(value = "name",required = false)String name,
                             @RequestParam(value = "BDCQZH",required = false)String BDCQZH,@RequestParam(value = "BDCDYH",required = false)String BDCDYH){
        try{

            Map<String, Object> map = new HashMap<>();
            map.put("QLRMC",name);
            map.put("QLRZJHM",sfzh);
            map.put("QLRZJZL","1");
            map.put("BDCQZH",BDCQZH);
            map.put("BDCDYH",BDCDYH);
            map.put("YHM","00000001");
            map.put("MM","377526");
            String jsonString = JSONObject.toJSONString(map);
            logger.info(jsonString);
            Object bdcPost = bdcHttpUtils.bdcPost("http://59.207.231.249:1010/Api/BDCComQuery",jsonString);
            logger.info(bdcPost+"===============");
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "泌阳县不动产证明接口" ,  notes="返回参数")
    @RequestMapping(value = "/by/BDCZM",method = {RequestMethod.POST})
    public Object getbyBDCZM(@RequestParam(value = "sfzh",required = false)String sfzh,@RequestParam(value = "name",required = false)String name,
                             @RequestParam(value = "BDCQZH",required = false)String BDCQZH,@RequestParam(value = "BDCDYH",required = false)String BDCDYH){
        try{

            Map<String, Object> map = new HashMap<>();
            map.put("QLRMC",name);
            map.put("QLRZJHM",sfzh);
            map.put("QLRZJZL","1");
            map.put("BDCQZH",BDCQZH);
            map.put("BDCDYH",BDCDYH);
            map.put("YHM","00000001");
            map.put("MM","958035");
            String jsonString = JSONObject.toJSONString(map);
            logger.info(jsonString);
            Object bdcPost = bdcHttpUtils.bdcPost("http://59.207.223.249:8000/Api/BDCComQuery",jsonString);
            logger.info(bdcPost+"===============");
            return bdcPost;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }




    public String getResult(Object bdcPost,String flag){
            try{
                if(!StringUtils.isEmpty(bdcPost)){
                    JSONObject res = new JSONObject();
                    JSONObject jsonObject = JSONObject.parseObject(bdcPost.toString());
                    String result = jsonObject.getString("Result");
                    if(result!=null||result!=""){
                        if(result.equals("1")){
                            String Data = jsonObject.getString("Data");
                            JSONObject jsonData = JSONObject.parseObject(Data);
                            String bdcdjxx = jsonData.getString("BDCDJXX");
                            JSONArray objects = JSONObject.parseArray(bdcdjxx);
                            JSONArray ob = new JSONArray();
                            if(objects!=null){
                                for (Object o:objects
                                     ) {
                                    JSONObject jsonObject1 = JSONObject.parseObject(o.toString());
                                    if(jsonObject1.containsKey(flag)){
                                        ob.add(jsonObject1.get(flag));
                                    }
                                }
                            }

                            String dataString = jsonData.getString(flag);
                            res.put("result","1");
                            res.put("data",ob);
                            res.put("note",jsonObject.getString("Note"));

                        }else{
                            res.put("result",result);
                            res.put("note",jsonObject.getString("Note"));
                        }
                        return res.toJSONString();
                    }
                }
                return null;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }


        }


    /**
     * 不动产证照照面信息实时推送接口
     * @param djzmData 参数为json串，是不动产照面数据信息
     * @return
     */
    @ApiOperation(value = "不动产登记证明照面数据推送接口" ,  notes="返回参数")
    @RequestMapping(value = "/djzmData",method = {RequestMethod.POST})
    public Object djzmData(@Valid @RequestBody DjzmData djzmData, BindingResult bindingResult){
        long start = System.currentTimeMillis();
        logger.info("不动产登记证明照面数据推送接口=======开始执行：{}",start);
        try{
            logger.info("不动产登记证明照面数据推送接口=======参数信息：{}",JSONObject.toJSONString(djzmData));
            if(bindingResult.hasErrors()){
                String errors = "";
                for(ObjectError error : bindingResult.getAllErrors()){
                    errors += error.getDefaultMessage()+" ";
                }
                return com.bm.https.pojo.ResponseBody.failure(errors);
            }
            logger.info("不动产登记证明照面数据推送接口=======开始插库");
            jdbcTemplate.execute(
                    "insert into djzm(bh,cqzsh,cxewm,djjg,djjgdm,djsj,djzmh,djzzbs,dyh,fj,xxewm,qlhsxdm,qlr,qlrzjhm,qlrzjhmlx,qt,ywr,ywrzjhm,ywrzjhmlx,zl,zmqlhsx) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new AbstractLobCreatingPreparedStatementCallback(new DefaultLobHandler() ){
                        protected void setValues(PreparedStatement pstmt, LobCreator lobCreator) throws SQLException {
                            pstmt.setString(1,djzmData.getBh());
                            pstmt.setString(2,djzmData.getCqzsh());
                            InputStream inputStream   =   new ByteArrayInputStream(djzmData.getCxewm().toString().getBytes());
                            lobCreator.setBlobAsBinaryStream(pstmt,3,inputStream,djzmData.getCxewm().toString().length());
                            pstmt.setString(4,djzmData.getDjjg());
                            pstmt.setString(5,djzmData.getDjjgdm());
                            pstmt.setString(6,djzmData.getDjsj());
                            pstmt.setString(7,djzmData.getDjzmh());
                            pstmt.setString(8,djzmData.getDjzzbs());
                            pstmt.setString(9,djzmData.getDyh());
                            pstmt.setString(10,djzmData.getFj());
                            InputStream inputStream2   =   new ByteArrayInputStream(djzmData.getXxewm().toString().getBytes());
                            lobCreator.setBlobAsBinaryStream(pstmt,11,inputStream2,djzmData.getXxewm().toString().length());
                            pstmt.setString(12,djzmData.getQlhsxdm());
                            pstmt.setString(13,djzmData.getQlr());
                            pstmt.setString(14,djzmData.getQlrzjhm());
                            pstmt.setString(15,djzmData.getQlrzjhmlx());
                            pstmt.setString(16,djzmData.getQt());
                            pstmt.setString(17,djzmData.getYwr());
                            pstmt.setString(18,djzmData.getYwrzjhm());
                            pstmt.setString(19,djzmData.getYwrzjhmlx());
                            pstmt.setString(20,djzmData.getZl());
                            pstmt.setString(21,djzmData.getZmqlhsx());
                        }
                    });
            logger.info("不动产登记证明照面数据推送接口=======插库结束");
            logger.info("不动产登记证明照面数据推送接口=======结束:耗时{}毫秒",System.currentTimeMillis()-start);
            return com.bm.https.pojo.ResponseBody.success();
        }catch (Exception e){
            e.printStackTrace();
            return com.bm.https.pojo.ResponseBody.failure(e.getMessage());
        }
    }


    /**
     * 不动产证照照面信息实时推送接口
     * @param qlzsData 参数为json串，是不动产照面数据信息
     * @return
     */
    @ApiOperation(value = "不动产权利证书照面数据推送接口" ,  notes="返回参数")
    @RequestMapping(value = "/qlzsData",method = {RequestMethod.POST})
    public Object qlzsData(@Valid @RequestBody  QlzsData qlzsData, BindingResult bindingResult){
        long start = System.currentTimeMillis();
        logger.info("不动产权利证书照面数据推送接口=======开始执行：{}",start);
        try{
            logger.info("不动产权利证书照面数据推送接口=======参数信息：{}",JSONObject.toJSONString(qlzsData));
            if(bindingResult.hasErrors()){
                String errors = "";
                for(ObjectError error : bindingResult.getAllErrors()){
                    errors += error.getDefaultMessage()+" ";
                }
                return com.bm.https.pojo.ResponseBody.failure(errors);
            }
            logger.info("不动产权利证书照面数据推送接口=======开始插库");
            jdbcTemplate.execute(
                    "insert into qlzs(bh,bz,cqzh,cxewm,djjg,djjgdm,djsj,djzzbs,dyh,fbczbs,fj,fjy,ft,ftjzd,ftsm,fty,gybl,gyqk,gyr,gyrzjhm,mj,mjdw,mjdwdm,qllx,qllxdm,qlqtqk,qlr,qlrlx,qlrzjhm,qlrzjhmlx,qlxz,qlxzdm,syqxq,syqxz,xxewm,yqlrgx,yt,ytdm,zl) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new AbstractLobCreatingPreparedStatementCallback(new DefaultLobHandler() ){
                        protected void setValues(PreparedStatement pstmt, LobCreator lobCreator) throws SQLException {
                            pstmt.setString(1,qlzsData.getBh());
                            pstmt.setString(2,qlzsData.getBz());
                            pstmt.setString(3,qlzsData.getCqzh());
                            InputStream inputStream   =   new ByteArrayInputStream(qlzsData.getCxewm().toString().getBytes());
                            lobCreator.setBlobAsBinaryStream(pstmt,4,inputStream,qlzsData.getCxewm().toString().length());
                            pstmt.setString(5,qlzsData.getDjjg());
                            pstmt.setString(6,qlzsData.getDjjgdm());
                            pstmt.setString(7,qlzsData.getDjsj());
                            pstmt.setString(8,qlzsData.getDjzzbs());
                            pstmt.setString(9,qlzsData.getDyh());
                            pstmt.setString(10,qlzsData.getFbczbs());
                            pstmt.setString(11,qlzsData.getFj());
                            pstmt.setString(12,qlzsData.getFjy());
                            StringBuffer ft = qlzsData.getFt();
                            if(ft==null){
                               ft = new StringBuffer("");
                            }
                            InputStream inputStream3   =   new ByteArrayInputStream(ft.toString().getBytes());
                            lobCreator.setBlobAsBinaryStream(pstmt,13,inputStream3,ft.toString().length());

                            pstmt.setString(14,qlzsData.getFtjzd());
                            pstmt.setString(15,qlzsData.getFtsm());
                            pstmt.setString(16,qlzsData.getFty());
                            pstmt.setString(17,qlzsData.getGybl());
                            pstmt.setString(18,qlzsData.getGyqk());
                            pstmt.setString(19,qlzsData.getGyr());
                            pstmt.setString(20,qlzsData.getGyrzjhm());
                            pstmt.setString(21,qlzsData.getMj());
                            pstmt.setString(22,qlzsData.getMjdw());
                            pstmt.setString(23,qlzsData.getMjdwdm());
                            pstmt.setString(24,qlzsData.getQllx());
                            pstmt.setString(25,qlzsData.getQllxdm());
                            pstmt.setString(26,qlzsData.getQlqtqk());
                            pstmt.setString(27,qlzsData.getQlr());
                            pstmt.setString(28,qlzsData.getQlrlx());
                            pstmt.setString(29,qlzsData.getQlrzjhm());
                            pstmt.setString(30,qlzsData.getQlrzjhmlx());
                            pstmt.setString(31,qlzsData.getQlxz());
                            pstmt.setString(32,qlzsData.getQlxzdm());
                            pstmt.setString(33,qlzsData.getSyqxq());
                            pstmt.setString(34,qlzsData.getSyqxz());
                            InputStream inputStream2   =   new ByteArrayInputStream(qlzsData.getXxewm().toString().getBytes());
                            lobCreator.setBlobAsBinaryStream(pstmt,35,inputStream2,qlzsData.getXxewm().toString().length());
                            pstmt.setString(36,qlzsData.getYqlrgx());
                            pstmt.setString(37,qlzsData.getYt());
                            pstmt.setString(38,qlzsData.getYtdm());
                            pstmt.setString(39,qlzsData.getZl());
                        }
                    });
            logger.info("不动产权利证书照面数据推送接口=======插库结束");
            logger.info("不动产权利证书照面数据推送接口=======结束:耗时{}毫秒",System.currentTimeMillis()-start);
            return com.bm.https.pojo.ResponseBody.success();
        }catch (Exception e){
            e.printStackTrace();
            return com.bm.https.pojo.ResponseBody.failure(e.getMessage());
        }
    }


}
