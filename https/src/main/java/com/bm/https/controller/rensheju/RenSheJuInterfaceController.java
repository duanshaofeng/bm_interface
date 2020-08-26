package com.bm.https.controller.rensheju;

import com.alibaba.fastjson.JSONObject;
import com.bm.https.pojo.AllResponseBody;
import com.bm.https.untils.AllHttpUntil;
import com.bm.https.untils.HttpUtil;
import com.bm.https.untils.RecordtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description  = "市人社局接口类")
@RestController
@RequestMapping("/test/rensheju")
public class RenSheJuInterfaceController {
	private Logger logger = LoggerFactory.getLogger(RenSheJuInterfaceController.class);



	@ApiOperation(value = "个人参保信息查询接口" ,  notes="返回参数")
	@RequestMapping(value = "/rgcbxx",method = {RequestMethod.POST,RequestMethod.GET})
	public Object rgcbxx(@RequestParam(value = "user_no",required = true)String user_no){


		try{
			String url = "http://http://59.207.219.108/dataservices/services/zmdytjcx.svc/$metadata#GRCBXXCX";
			long millis = System.currentTimeMillis();
			Map<String, Object> map = new HashMap<>();
			map.put("user_no",user_no);
			map.put("timestamp",millis);
			String jsonString = JSONObject.toJSONString(map);
			logger.info(jsonString);
			Object post = HttpUtil.sendPost("http://59.207.237.9:8083/BillQuery/QueryBill", jsonString);
			//String tdsyq = getResult(bdcPost, "TDSYQ");
			logger.info("===============燃气账单查询接口返回=="+post);
			return post;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}


	@ApiOperation(value = "个人社保卡信息查询接口" ,  notes="返回参数")
	@RequestMapping(value = "/shebaoka",method = {RequestMethod.POST,RequestMethod.GET})
	public Object shebaoka(@RequestParam(value = "idCard",required = true)String idCard){

		try{
			logger.info("=========人社局个人社保卡查询=====参数idcard：{}",idCard);
			String sql = "select AAB301,AAZ500,AAC002,AAC003,FKRQ,YXQZ,AAZ502,AAE008,AAE008B,AAE010,AAE010A,AAE010B from AZ01B where AAC002 = ?";
			List<Map<String, Object>> mapList = RecordtUtil.queryListForAll("oracle.jdbc.OracleDriver", "jdbc:oracle:thin:interfaceDB/interfaceDB@59.207.219.23:1521:orcl", "interfaceDB", "interfaceDB", sql, new Object[]{idCard}, new int[]{12});
			logger.info("=========人社局个人社保卡查询=====返回：{}",JSONObject.toJSONString(mapList));
			return AllResponseBody.success(mapList);
		}catch (Exception e){
			logger.info("=========人社局个人社保卡查询=====查询异常：{}",e.getLocalizedMessage());
			return AllResponseBody.failure("查询异常 "+e.getMessage());
		}
	}

	public static Object getToken(String url) throws Exception{
		Object get = AllHttpUntil.sendGet("http://59.207.219.108/dataservices/oauth/token?client_id=96a31a620ddddad6efa3b05f6c8ae531&client_secret=df75da497b53f19b8b089e4f6089d29f");
		if(!StringUtils.isEmpty(get)){

		}
		return get;
	}

	public static void main(String[] args) throws Exception {
		//Object get = AllHttpUntil.sendGet("http://59.207.219.108/dataservices/oauth/token?client_id=96a31a620ddddad6efa3b05f6c8ae531&client_secret=df75da497b53f19b8b089e4f6089d29f");
		Object get2 = AllHttpUntil.sendGet("http://59.207.219.108/dataservices/services/ldjydpzs.svc/$metadata#V_CB21_ZPXX?access_token=c9742ecc-4f58-4196-a3ad-7b976ae1ed7e");
		System.out.println(get2);
	}


}
