package com.bm.https.accessToken.fagaiwei;

import com.alibaba.fastjson.JSONObject;
import com.bm.https.untils.AllHttpUntil;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Api(description  = "发改委资源对接接口类")
@RestController
@RequestMapping("/fgw")
public class FagaiweiResourceInfoController {
    private Logger logger = LoggerFactory.getLogger(FagaiweiResourceInfoController.class);

    @Autowired
    private ConnectUtil connectUtil;

    @Value("${shifagaiwei.url.pre}")
    private String preUrl ;
    @Value("${shifagaiwei.url.ws}")
    private String wsUrl ;

    @Value("${shifagaiwei.url.ws2}")
    private String wsUrl2 ;


    @Value("${shifagaiwei.url.ws.np2}")
    private String wsnp2 ;

    @ApiOperation(value = "网站企业信息查询列表接口" ,  notes="返回参数")
    @RequestMapping(value = "/getCompanyListnew2Num",method = {RequestMethod.POST})
    public Object getCompanyListnew2Num(@RequestParam(value = "cxnr",required = true) String cxnr,@RequestParam(value = "page",required = false) String page,@RequestParam(value = "pageSize",required = false) String pageSize){
        Map<String, String> map = new HashMap<>();
        try{
            String url = preUrl+"getCompanyListnew2Num";
            map.put("cxnr",cxnr);
            if(!StringUtils.isEmpty(page)){
                map.put("page",page);
            }else {
                map.put("page","1");
            }
            if(!StringUtils.isEmpty(pageSize)){
                map.put("pageSize",pageSize);
            }else {
                map.put("pageSize","10");
            }

            String jsonString = JSONObject.toJSONString(map);
            Object post = AllHttpUntil.sendPost2(url,map);
            logger.info("===============网站企业信息查询列表接口:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "网站查询详情基本信息接口" ,  notes="返回参数")
    @RequestMapping(value = "/getCompanyInfoByWs",method = {RequestMethod.POST})
    public Object getCompanyInfoByWs(@RequestParam("xybsm") String xybsm){
        Map<String, String> map = new HashMap<>();
        try{
            String url = preUrl+"getCompanyInfoByWs";
            map.put("xybsm",xybsm);
            String jsonString = JSONObject.toJSONString(map);
            Object post = AllHttpUntil.sendPost2(url,map);
            logger.info("===============网站查询详情基本信息接口:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @ApiOperation(value = "网站查询详情基本信息接口（其他类信息）" ,  notes="返回参数")
    @RequestMapping(value = "/getDeptInfoListByWsNew1",method = {RequestMethod.POST})
    public Object getDeptInfoListByWsNew1(@RequestParam("xybsm") String xybsm){
        Map<String, String> map = new HashMap<>();
        try{
            String url = preUrl+"getDeptInfoListByWsNew1";
            map.put("xybsm",xybsm);
            String jsonString = JSONObject.toJSONString(map);
            Object post = AllHttpUntil.sendPost2(url,map);
            logger.info("===============网站查询详情基本信息（其他类信息）接口:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "行政处罚列表" ,  notes="返回参数")
    @RequestMapping(value = "/xzcflist",method = {RequestMethod.POST})
    public Object xzcflist(@RequestParam(value = "page",required = false) String page,@RequestParam(value = "pageSize",required = false) String pageSize){
        Map<String, String> map = new HashMap<>();
        try{
            String url = preUrl+"xzcflist";
            if(!StringUtils.isEmpty(page)){
                map.put("page",page);
            }else {
                map.put("page","1");
            }
            if(!StringUtils.isEmpty(pageSize)){
                map.put("pagesize",pageSize);
            }else {
                map.put("pagesize","10");
            }
            Object post = AllHttpUntil.sendPost2(url,map);
            logger.info("===============行政处罚列表:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    @ApiOperation(value = "行政许可列表" ,  notes="返回参数")
    @RequestMapping(value = "/xzxklist",method = {RequestMethod.POST})
    public Object xzxklist(@RequestParam(value = "page",required = false) String page,@RequestParam(value = "pageSize",required = false) String pageSize){
        Map<String, String> map = new HashMap<>();
        try{
            String url = preUrl+"xzxklist";
            if(!StringUtils.isEmpty(page)){
                map.put("page",page);
            }else {
                map.put("page","1");
            }
            if(!StringUtils.isEmpty(pageSize)){
                map.put("pagesize",pageSize);
            }else {
                map.put("pagesize","10");
            }
            Object post = AllHttpUntil.sendPost2(url,map);
            logger.info("===============行政许可列表:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    @ApiOperation(value = "行政许可详情" ,  notes="返回参数")
    @RequestMapping(value = "/xzxkDetail",method = {RequestMethod.POST})
    public Object xzxkDetail(@RequestParam(value = "Id",required = true) String Id){
        Map<String, String> map = new HashMap<>();
        try{
            String url = preUrl+"xzxkDetail";
            map.put("id",Id);

            Object post = AllHttpUntil.sendPost2(url,map);
            logger.info("===============行政许可详情:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "行政处罚详情" ,  notes="返回参数")
    @RequestMapping(value = "/xzcfDetail",method = {RequestMethod.POST})
    public Object xzcfDetail(@RequestParam(value = "Id",required = true) String Id){
        Map<String, String> map = new HashMap<>();
        try{
            String url = preUrl+"xzcfDetail";
            map.put("id",Id);

            Object post = AllHttpUntil.sendPost2(url,map);
            logger.info("===============行政处罚详情:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "行政许可查询" ,  notes="返回参数")
    @RequestMapping(value = "/cmsSelectListxzxk",method = {RequestMethod.POST})
    public Object cmsSelectListxzxk(@RequestParam(value = "content",required = true) String content,@RequestParam(value = "page",required = false) String page,@RequestParam(value = "pageSize",required = false) String pageSize){
        Map<String, String> map = new HashMap<>();
        try{
            String url = preUrl+"cmsSelectListxzxk";
            map.put("content",content);
            if(!StringUtils.isEmpty(page)){
                map.put("page",page);
            }else {
                map.put("page","1");
            }
            if(!StringUtils.isEmpty(pageSize)){
                map.put("pagesize",pageSize);
            }else {
                map.put("pagesize","10");
            }
            Object post = AllHttpUntil.sendPost2(url,map);
            logger.info("===============行政许可查询:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "行政处罚查询" ,  notes="返回参数")
    @RequestMapping(value = "/cmsSelectListxzcf",method = {RequestMethod.POST})
    public Object cmsSelectListxzcf(@RequestParam("content") String content,@RequestParam(value = "page",required = false) String page,@RequestParam(value = "pageSize",required = false) String pageSize){
        Map<String, String> map = new HashMap<>();
        try{
            String url = preUrl+"cmsSelectListxzcf";
            map.put("content",content);
            if(!StringUtils.isEmpty(page)){
                map.put("page",page);
            }else {
                map.put("page","1");
            }
            if(!StringUtils.isEmpty(pageSize)){
                map.put("pagesize",pageSize);
            }else {
                map.put("pagesize","10");
            }
            Object post = AllHttpUntil.sendPost2(url,map);
            logger.info("===============行政处罚查询:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    @ApiOperation(value = "行政许可处罚统计列表" ,  notes="返回参数")
    @RequestMapping(value = "/xzxkxzcfCount",method = {RequestMethod.POST})
    public Object xzxkxzcfCount(){
        Map<String, String> map = new HashMap<>();
        try{
            String url = preUrl+"xzxkxzcfCount";
            Object post = AllHttpUntil.sendPost2(url,map);
            logger.info("===============行政许可处罚统计列表:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "黑名单省级数据列表" ,  notes="返回参数")
    @RequestMapping(value = "/getHeiMdTree",method = {RequestMethod.POST})
    public Object getHeiMdTree(){
        Map<String, String> map = new HashMap<>();
        try{
            String url = preUrl+"getHeiMdTree";
            map.put("type","2");
            Object post = AllHttpUntil.sendPost2(url,map);
            logger.info("===============黑名单省级数据列表:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "红名单省级数据列表" ,  notes="返回参数")
    @RequestMapping(value = "/getHongMdTree",method = {RequestMethod.POST})
    public Object getHongMdTree(){
        Map<String, String> map = new HashMap<>();
        try{
            String url = preUrl+"getHongMdTree";
            map.put("type","2");
            Object post = AllHttpUntil.sendPost2(url,map);
            logger.info("===============红名单省级数据列表:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "黑红名单省级每个表数据列表" ,  notes="返回参数")
    @RequestMapping(value = "/getMdList",method = {RequestMethod.POST})
    public Object getMdList(@RequestParam("id") String id,
                            @RequestParam(value = "content",required = false) String content,
                            @RequestParam(value = "start",required = false) String start,
                            @RequestParam(value = "size",required = false) String size){
        Map<String, String> map = new HashMap<>();
        try{
            String url = preUrl+"getMdList";
            map.put("id",id);
            if(!StringUtils.isEmpty(start)){
                map.put("start",start);
            }else {
                map.put("start","1");
            }
            if(!StringUtils.isEmpty(content)){
                map.put("content",content);
            }
            if(!StringUtils.isEmpty(size)){
                map.put("size",size);
            }else {
                map.put("size","10");
            }
            Object post = AllHttpUntil.sendPost2(url,map);
            logger.info("===============黑红名单省级每个表数据列表:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "红黑名单省级数据详情" ,  notes="返回参数")
    @RequestMapping(value = "/heihongDetail",method = {RequestMethod.POST})
    public Object heihongDetail(@RequestParam("id") String id,
                                @RequestParam(value = "tableid",required = true) String tableid){
        Map<String, String> map = new HashMap<>();
        try{
            String url = preUrl+"heihongDetail";
            map.put("id",id);
            map.put("tableid",tableid);
            Object post = AllHttpUntil.sendPost2(url,map);
            logger.info("===============红黑名单省级数据详情:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "市级红名单列表" ,  notes="返回参数")
    @RequestMapping(value = "/cityHongList",method = {RequestMethod.POST})
    public Object cityHongList(@RequestParam(value = "content",required = false) String content,
                               @RequestParam(value = "themeType",required = false) String themeType,
                               @RequestParam(value = "page",required = false) String page,
                               @RequestParam(value = "pagesize",required = false) String pageSize){
        Map<String, String> map = new HashMap<>();
        try{
            String url = preUrl+"cityHongList";
            if(!StringUtils.isEmpty(content)){
                map.put("content",content);
            }
            if(!StringUtils.isEmpty(themeType)){
                map.put("themeType",themeType);
            }

            if(!StringUtils.isEmpty(page)){
                map.put("page",page);
            }else {
                map.put("page","1");
            }
            if(!StringUtils.isEmpty(pageSize)){
                map.put("pagesize",pageSize);
            }else {
                map.put("pagesize","10");
            }
            Object post = AllHttpUntil.sendPost2(url,map);
            logger.info("===============市级红名单列表:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "市级黑名单列表" ,  notes="返回参数")
    @RequestMapping(value = "/cityHeiList",method = {RequestMethod.POST})
    public Object cityHeiList(@RequestParam(value = "content",required = false) String content,
                              @RequestParam(value = "themeType",required = false) String themeType,
                              @RequestParam(value = "page",required = false) String page,
                              @RequestParam(value = "pagesize",required = false) String pageSize){
        Map<String, String> map = new HashMap<>();
        try{
            String url = preUrl+"cityHeiList";
            if(!StringUtils.isEmpty(content)){
                map.put("content",content);
            }
            if(!StringUtils.isEmpty(themeType)){
                map.put("themeType",themeType);
            }
            if(!StringUtils.isEmpty(page)){
                map.put("page",page);
            }else {
                map.put("page","1");
            }
            if(!StringUtils.isEmpty(pageSize)){
                map.put("pagesize",pageSize);
            }else {
                map.put("pagesize","10");
            }
            Object post = AllHttpUntil.sendPost2(url,map);
            logger.info("===============市级黑名单列表:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "市级红名单详情" ,  notes="返回参数")
    @RequestMapping(value = "/cityHongDetail",method = {RequestMethod.POST})
    public Object cityHongDetail(@RequestParam(value = "Id",required = true) String Id){
        Map<String, String> map = new HashMap<>();
        try{
            String url = preUrl+"cityHongDetail";
            map.put("id",Id);

            Object post = AllHttpUntil.sendPost2(url,map);
            logger.info("===============市级红名单详情:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "市级黑名单详情" ,  notes="返回参数")
    @RequestMapping(value = "/cityHeiDetail",method = {RequestMethod.POST})
    public Object cityHeiDetail(@RequestParam(value = "Id",required = true) String Id){
        Map<String, String> map = new HashMap<>();
        try{
            String url = preUrl+"cityHeiDetail";
            map.put("id",Id);

            Object post = AllHttpUntil.sendPost2(url,map);
            logger.info("===============市级黑名单详情:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "验证码" ,  notes="返回参数")
    @RequestMapping(value = "/authCode2",method = {RequestMethod.POST})
    public Object authCode2(){
        Map<String, String> map = new HashMap<>();
        try{
            String url = preUrl+"authCode2";
            Object post = AllHttpUntil.sendPost2(url,map);
            logger.info("===============验证码:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "异议投诉提交地址" ,  notes="返回参数")
    @RequestMapping(value = "/submitComplain",method = {RequestMethod.POST})
    public Object submitComplain(@RequestParam(value = "complainCompanyName",required = true) String complainCompanyName,
                                 @RequestParam(value = "complainPersonName",required = true) String complainPersonName,
                                 @RequestParam(value = "complainPersonCard",required = true) String complainPersonCard,
                                 @RequestParam(value = "complainProvinceCode",required = true) String complainProvinceCode,
                                 @RequestParam(value = "complainCityCode",required = true) String complainCityCode,
                                 @RequestParam(value = "complainCountyCode",required = true) String complainCountyCode,
                                 @RequestParam(value = "complainPersonMobile",required = true) String complainPersonMobile,
                                 @RequestParam(value = "complainPersonPhone",required = true) String complainPersonPhone,
                                 @RequestParam(value = "complainPersonMail",required = true) String complainPersonMail,
                                 @RequestParam(value = "complainPersonAddress",required = true) String complainPersonAddress,
                                 @RequestParam(value = "complainTitle",required = true) String complainTitle,
                                 @RequestParam(value = "complainContent",required = true) String complainContent,
                                 @RequestParam(value = "xybsm",required = true) String xybsm,
                                 @RequestParam(value = "tableid",required = true) String tableid,
                                 @RequestParam(value = "rowInfoId",required = true) String rowInfoId,
                                 @RequestParam(value = "complainAttr",required = true) String complainAttr

    ){
        Map<String, String> map = new HashMap<>();
        try{
            String url = preUrl+"submitComplain";
            map.put("complainCompanyName",complainCompanyName);
            map.put("complainPersonName",complainPersonName);
            map.put("complainPersonCard",complainPersonCard);
            map.put("complainProvinceCode",complainProvinceCode);
            map.put("complainCityCode",complainCityCode);
            map.put("complainCountyCode",complainCountyCode);
            map.put("complainPersonMobile",complainPersonMobile);
            map.put("complainPersonPhone",complainPersonPhone);
            map.put("complainPersonMail",complainPersonMail);
            map.put("complainPersonAddress",complainPersonAddress);
            map.put("complainTitle",complainTitle);
            map.put("complainContent",complainContent);
            map.put("xybsm",xybsm);
            map.put("tableid",tableid);
            map.put("rowInfoId",rowInfoId);
            map.put("complainAttr",complainAttr);
            Object post = AllHttpUntil.sendPost2(url,map);
            logger.info("===============异议投诉提交地址:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "各单位数据量统计" ,  notes="返回参数")
    @RequestMapping(value = "/unitDataCount",method = {RequestMethod.POST})
    public Object unitDataCount(){
        Map<String, String> map = new HashMap<>();
        try{
            String url = preUrl+"unitDataCount";
            Object post = AllHttpUntil.sendPost2(url,map);
            logger.info("===============各单位数据量统计:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //TODO
    @ApiOperation(value = "网站栏目" ,  notes="返回参数")
    @RequestMapping(value = "/article",method = {RequestMethod.POST})
    public Object article(@RequestParam(value = "columnId",required = true) String columnId,
                          @RequestParam(value = "pageSize",required = false) String pageSize,
                          @RequestParam(value = "page",required = false) String page
    ){
        Map<String, String> map = new HashMap<>();
        try{
            String url = "http://59.207.219.98:8080/articleP.sp";
            map.put("columnId",columnId);
            map.put("status","1");
            map.put("act","index");
            if(!StringUtils.isEmpty(page)){
                map.put("page",page);
            }
            if(!StringUtils.isEmpty(pageSize)){
                map.put("pageSize",pageSize);
            }
            Object post = AllHttpUntil.sendPost2(url,map);
            logger.info("===============网站栏目:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }




    @ApiOperation(value = "统一社会信用代码公示" ,  notes="返回参数")
    @RequestMapping(value = "/getCreditCodeListByWs",method = {RequestMethod.POST})
    public Object getCreditCodeListByWs(@RequestParam(value = "searchContent",required = true) String searchContent,
                                        @RequestParam(value = "page",required = false) String page   ,
                                        @RequestParam(value = "pagesize",required = false) String pagesize ){
        Map<String, String> map = new HashMap<>();
        try{
            String url = preUrl+"getCreditCodeListByWs";
            if(!StringUtils.isEmpty(page)){
                map.put("page",page);
            }else {
                map.put("page","1");
            }
            if(!StringUtils.isEmpty(searchContent)){
                map.put("searchContent",searchContent);
            }
            if(!StringUtils.isEmpty(pagesize)){
                map.put("pageSize",pagesize);
            }else {
                map.put("pageSize","10");
            }
            Object post = AllHttpUntil.sendPost2(url,map);
            logger.info("===============统一社会信用代码公示:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    @ApiOperation(value = "信用报告下载" ,  notes="返回参数")
    @RequestMapping(value = "/downPdfxuchang",method = {RequestMethod.POST})
    public Object downPdfxuchang(@RequestParam(value = "xybsm",required = true) String xybsm,
                                 @RequestParam(value = "name",required = false) String name,
                                 @RequestParam(value = "tell",required = false) String tell,
                                 @RequestParam(value = "content",required = false)String content){
        Map<String, String> map = new HashMap<>();
        try{
            String url = preUrl+"downPdfxuchang";
            if(!StringUtils.isEmpty(xybsm)){
                map.put("xybsm",xybsm);
            }
            if(!StringUtils.isEmpty(name)){
                map.put("name",name);
            }
            if(!StringUtils.isEmpty(tell)){
                map.put("tell",tell);
            }
            if(!StringUtils.isEmpty(content)){
                map.put("content",content);
            }
            Object post = AllHttpUntil.sendPostDown(url,map);
            logger.info("===============信用报告下载:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "意见反馈" ,  notes="返回参数")
    @RequestMapping(value = "/insertOpinion",method = {RequestMethod.POST})
    public Object insertOpinion(@RequestParam(value = "op_name",required = true) String op_name,
                                @RequestParam(value = "op_tell",required = true) String op_tell,
                                @RequestParam(value = "op_email",required = true) String op_email,
                                @RequestParam(value = "op_content",required = true)String op_content){
        Map<String, String> map = new HashMap<>();
        try{
            String url = preUrl+"insertOpinion";
            map.put("op_name",op_name);
            map.put("op_tell",op_tell);
            map.put("op_email",op_email);
            map.put("op_content",op_content);

            Object post = AllHttpUntil.sendPost2(url,map);
            logger.info("===============意见反馈:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "获取省" ,  notes="返回参数")
    @RequestMapping(value = "/getProvince",method = {RequestMethod.POST})
    public Object getProvince(){
        Map<String, String> map = new HashMap<>();
        try{
            String url = preUrl+"getProvince";
            Object post = AllHttpUntil.sendPost2(url,map);
            logger.info("===============获取省:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "获取市" ,  notes="返回参数")
    @RequestMapping(value = "/getCityList",method = {RequestMethod.POST})
    public Object getCityList(@RequestParam(value = "provinceCode",required = true)String provinceCode){
        Map<String, String> map = new HashMap<>();
        try{
            String url = preUrl+"getCityList";
            map.put("provinceCode",provinceCode);

            Object post = AllHttpUntil.sendPost2(url,map);
            logger.info("===============获取市:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "获取县" ,  notes="返回参数")
    @RequestMapping(value = "/getCountyList",method = {RequestMethod.POST})
    public Object getCountyList(@RequestParam(value = "cityCode",required = true)String cityCode){
        Map<String, String> map = new HashMap<>();
        try{
            String url = preUrl+"getCountyList";
            map.put("cityCode",cityCode);

            Object post = AllHttpUntil.sendPost2(url,map);
            logger.info("===============获取县:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "获取主体信息列表（模糊查询）" ,  notes="返回参数")
    @RequestMapping(value = "/getSubjectPages",method = {RequestMethod.POST})
    public Object getSubjectPages (@RequestParam(value = "content",required = true)String content,
                                   @RequestParam(value = "key",required = true)String key,
                                   @RequestParam(value = "start",required = true)String start
    ){
        Map<String, Object> map = new HashMap<>();
        try{
            String method = "getSubjectPages";
            map.put("content",content);
            map.put("key",key);
            map.put("start",start);
            Object post = AllHttpUntil.webserviceClient3(wsUrl,method,map);
            logger.info("===============获取主体信息列表（模糊查询）:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "获取主体信息列表（扩展1）" ,  notes="返回参数")
    @RequestMapping(value = "/getSubjectPagesPlus",method = {RequestMethod.POST})
    public Object getSubjectPagesPlus (@RequestParam(value = "content",required = true)String content,
                                       @RequestParam(value = "key",required = true)String key,
                                       @RequestParam(value = "start",required = true)String start,
                                       @RequestParam(value = "size",required = true)String size
    ){
        Map<String, Object> map = new HashMap<>();
        try{
            String method = "getSubjectPagesPlus";
            map.put("content",content);
            map.put("key",key);
            map.put("start",start);
            map.put("size",size);
            Object post = AllHttpUntil.webserviceClient3(wsUrl,method,map);
            logger.info("===============获取主体信息列表（扩展1）:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "获取主体信息接口（扩展1 精准查询）" ,  notes="返回参数")
    @RequestMapping(value = "/getSubject",method = {RequestMethod.POST})
    public Object getSubject (@RequestParam(value = "qymc",required = false)String qymc,
                              @RequestParam(value = "key",required = true)String key,
                              @RequestParam(value = "tyshxydm",required = false)String tyshxydm,
                              @RequestParam(value = "zjgdm",required = false)String zjgdm,
                              @RequestParam(value = "gsdjm",required = false)String gsdjm,
                              @RequestParam(value = "swdjh",required = false)String swdjh
    ){
        Map<String, Object> map = new HashMap<>();
        try{
            String method = "getSubject";
            map.put("qymc",qymc);
            map.put("key",key);
            map.put("tyshxydm",tyshxydm);
            map.put("zzjgdm",zjgdm);
            map.put("gsdjm",gsdjm);
            map.put("swdjh",swdjh);
            Object post = AllHttpUntil.webserviceClient3(wsUrl,method,map);
            logger.info("===============获取主体信息接口（扩展1 精准查询）:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "获取主体信息接口（扩展2 精准查询）" ,  notes="返回参数")
    @RequestMapping(value = "/getSubjectPlus",method = {RequestMethod.POST})
    public Object getSubjectPlus (@RequestParam(value = "qymc",required = true)String qymc,
                                  @RequestParam(value = "key",required = true)String key,
                                  @RequestParam(value = "qydm",required = true)String qydm
    ){
        Map<String, Object> map = new HashMap<>();
        try{
            String method = "getSubjectPlus";
            map.put("qymc",qymc);
            map.put("key",key);
            map.put("qydm",qydm);
            Object post = AllHttpUntil.webserviceClient3(wsUrl,method,map);
            logger.info("===============获取主体信息接口（扩展2 精准查询）:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "获取主体详细信息接口" ,  notes="返回参数")
    @RequestMapping(value = "/getSubjectDetail",method = {RequestMethod.POST})
    public Object getSubjectDetail (@RequestParam(value = "id",required = true)String id,
                                    @RequestParam(value = "key",required = true)String key
    ){
        Map<String, Object> map = new HashMap<>();
        try{
            String method = "getSubjectDetail";
            map.put("id",id);
            map.put("key",key);
            Object post = AllHttpUntil.webserviceClient3(wsUrl,method,map);
            logger.info("===============获取主体详细信息接口:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "获取主体详细信息接口(扩展1 按单位查询)" ,  notes="返回参数")
    @RequestMapping(value = "/getSubjectDetailPlus4",method = {RequestMethod.POST})
    public Object getSubjectDetailPlus4 (@RequestParam(value = "id",required = true)String id,
                                         @RequestParam(value = "unitId",required = false)String unitId,
                                         @RequestParam(value = "key",required = true)String key
    ){
        Map<String, Object> map = new HashMap<>();
        try{
            String method = "getSubjectDetailPlus4";
            map.put("id",id);
            map.put("unitId",unitId);
            map.put("key",key);
            Object post = AllHttpUntil.webserviceClient3(wsUrl,method,map);
            logger.info("===============获取主体详细信息接口(扩展1 按单位查询):返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "信用共享数据反馈接口" ,  notes="返回参数")
    @RequestMapping(value = "/setFeedback",method = {RequestMethod.POST})
    public Object setFeedback (@RequestParam(value = "id",required = true)String id,
                               @RequestParam(value = "content",required = true)String content,
                               @RequestParam(value = "key",required = true)String key
    ){
        Map<String, Object> map = new HashMap<>();
        try{
            String method = "setFeedback";
            map.put("id",id);
            map.put("content",content);
            map.put("key",key);
            Object post = AllHttpUntil.webserviceClient3(wsUrl,method,map);
            logger.info("===============信用共享数据反馈接口:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "信用共享数据异议投诉接口" ,  notes="返回参数")
    @RequestMapping(value = "/setComplain",method = {RequestMethod.POST})
    public Object setComplain (@RequestParam(value = "id",required = true)String id,
                               @RequestParam(value = "content",required = true)String content,
                               @RequestParam(value = "key",required = true)String key
    ){
        Map<String, Object> map = new HashMap<>();
        try{
            String method = "setComplain";
            map.put("id",id);
            map.put("content",content);
            map.put("key",key);
            Object post = AllHttpUntil.webserviceClient3(wsUrl,method,map);
            logger.info("===============信用共享数据异议投诉接口:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "获取备忘录接口" ,  notes="返回参数")
    @RequestMapping(value = "/getBwl",method = {RequestMethod.POST})
    public Object getBwl ( @RequestParam(value = "key",required = true)String key){
        Map<String, Object> map = new HashMap<>();
        try{
            String method = "getBwl";
            map.put("key",key);
            Object post = AllHttpUntil.webserviceClient3(wsUrl,method,map);
            logger.info("===============获取备忘录接口:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "获取规范单位名称" ,  notes="返回参数")
    @RequestMapping(value = "/getUnitNames",method = {RequestMethod.POST})
    public Object getUnitNames ( @RequestParam(value = "key",required = true)String key){
        Map<String, Object> map = new HashMap<>();
        try{
            String method = "getUnitNames";
            map.put("key",key);
            Object post = AllHttpUntil.webserviceClient3(wsUrl,method,map);
            logger.info("===============获取规范单位名称:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "获取黑红名单的接口" ,  notes="返回参数")
    @RequestMapping(value = "/getMdHonghei",method = {RequestMethod.POST})
    public Object getMdHonghei ( @RequestParam(value = "key",required = true)String key){
        Map<String, Object> map = new HashMap<>();
        try{
            String method = "getMdHonghei";
            map.put("key",key);
            Object post = AllHttpUntil.webserviceClient3(wsUrl,method,map);
            logger.info("===============获取黑红名单的接口:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    @ApiOperation(value = "获取主体详细信息(扩展1,只包含基本信息)" ,  notes="返回参数")
    @RequestMapping(value = "/getSubjectDetailPlus3",method = {RequestMethod.POST})
    public Object getSubjectDetailPlus3 ( @RequestParam(value = "key",required = true)String key,
                                          @RequestParam(value = "id",required = true)String id){
        Map<String, Object> map = new HashMap<>();
        try{
            String method = "getSubjectDetailPlus3";
            map.put("key",key);
            map.put("id",id);
            Object post = AllHttpUntil.webserviceClient3(wsUrl2,wsnp2,method,map);
            logger.info("===============获取主体详细信息(扩展1,只包含基本信息):返回  "+JSONObject.toJSONString(post));
            /*if(post!=null){
                JSONObject json = JsonXmlUtils.xmlToJson(post.toString());
                return json;
            }*/

            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    @ApiOperation(value = "获取主体信息列表（模糊查询）信用一体化" ,  notes="返回参数")
    @RequestMapping(value = "/getSubjectPages2",method = {RequestMethod.POST})
    public Object getSubjectPages2 (@RequestParam(value = "content",required = true)String content,
                                   @RequestParam(value = "key",required = true)String key,
                                   @RequestParam(value = "start",required = true)String start
    ){
        Map<String, Object> map = new HashMap<>();
        try{
            String method = "getSubjectPages";
            map.put("content",content);
            map.put("key",key);
            map.put("start",start);
            Object post = AllHttpUntil.webserviceClient3(wsUrl2,wsnp2,method,map);
            logger.info("===============获取主体信息列表（模糊查询）信用一体化:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }





    @ApiOperation(value = "获取主体信息接口（扩展1 精准查询）信用一体化" ,  notes="返回参数")
    @RequestMapping(value = "/getSubject2",method = {RequestMethod.POST})
    public Object getSubject2 (@RequestParam(value = "qymc",required = false)String qymc,
                              @RequestParam(value = "key",required = true)String key,
                              @RequestParam(value = "tyshxydm",required = false)String tyshxydm,
                              @RequestParam(value = "zjgdm",required = false)String zjgdm,
                              @RequestParam(value = "gsdjm",required = false)String gsdjm,
                              @RequestParam(value = "swdjh",required = false)String swdjh
    ){
        Map<String, Object> map = new HashMap<>();
        try{
            String method = "getSubject";
            map.put("qymc",qymc);
            map.put("key",key);
            map.put("tyshxydm",tyshxydm);
            map.put("zzjgdm",zjgdm);
            map.put("gsdjm",gsdjm);
            map.put("swdjh",swdjh);
            Object post = AllHttpUntil.webserviceClient3(wsUrl2,wsnp2,method,map);
            logger.info("===============获取主体信息接口（扩展1 精准查询）信用一体化:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "获取主体信息接口（扩展2 精准查询） 信用一体化" ,  notes="返回参数")
    @RequestMapping(value = "/getSubjectPlus2",method = {RequestMethod.POST})
    public Object getSubjectPlus2 (@RequestParam(value = "qymc",required = true)String qymc,
                                  @RequestParam(value = "key",required = true)String key,
                                  @RequestParam(value = "qydm",required = true)String qydm
    ){
        Map<String, Object> map = new HashMap<>();
        try{
            String method = "getSubjectPlus";
            map.put("qymc",qymc);
            map.put("key",key);
            map.put("qydm",qydm);
            Object post = AllHttpUntil.webserviceClient3(wsUrl2,wsnp2,method,map);
            logger.info("===============获取主体信息接口（扩展2 精准查询）信用一体化:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "获取主体详细信息接口 信用一体化" ,  notes="返回参数")
    @RequestMapping(value = "/getSubjectDetail2",method = {RequestMethod.POST})
    public Object getSubjectDetail2 (@RequestParam(value = "id",required = true)String id,
                                    @RequestParam(value = "key",required = true)String key
    ){
        Map<String, Object> map = new HashMap<>();
        try{
            String method = "getSubjectDetail";
            map.put("id",id);
            map.put("key",key);
            Object post = AllHttpUntil.webserviceClient3(wsUrl2,wsnp2,method,map);
            logger.info("===============获取主体详细信息接口信用一体化:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "信用共享数据异议投诉接口 信用一体化 " ,  notes="返回参数")
    @RequestMapping(value = "/setComplain2",method = {RequestMethod.POST})
    public Object setComplain2 (@RequestParam(value = "id",required = true)String id,
                               @RequestParam(value = "content",required = true)String content,
                               @RequestParam(value = "key",required = true)String key
    ){
        Map<String, Object> map = new HashMap<>();
        try{
            String method = "setComplain";
            map.put("id",id);
            map.put("content",content);
            map.put("key",key);
            Object post = AllHttpUntil.webserviceClient3(wsUrl2,wsnp2,method,map);
            logger.info("===============信用共享数据异议投诉接口 信用一体化:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "获取黑红名单的接口 信用一体化" ,  notes="返回参数")
    @RequestMapping(value = "/getMdHonghei2",method = {RequestMethod.POST})
    public Object getMdHonghei2 ( @RequestParam(value = "key",required = true)String key){
        Map<String, Object> map = new HashMap<>();
        try{
            String method = "getMdHonghei";
            map.put("key",key);
            Object post = AllHttpUntil.webserviceClient3(wsUrl2,wsnp2,method,map);
            logger.info("===============获取黑红名单的接口 信用一体化:返回  "+JSONObject.toJSONString(post));
            return post;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "上传文件" ,  notes="返回参数")
    @RequestMapping(value = "/upFile",method = {RequestMethod.POST})
    public Object getMdHonghei2 (MultipartFile file){
        Map<String, Object> map = new HashMap<>();
        try{
            if(file!=null){
                File f=new File(file.getOriginalFilename());
                AllHttpUntil.inputStreamToFile(file.getInputStream(), f);
                String s = AllHttpUntil.sendPostUplodFile("http://59.207.252.236:8080/subjectCenter/uploadxyhn", f);
                logger.info("===============获取黑红名单的接口 信用一体化:返回  "+s);

                return s;
            }
            map.put("msg","文件是空的");
            return map;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
