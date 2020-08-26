package com.bm.https.accessToken.ranqi;

import com.alibaba.fastjson.JSONObject;
import com.bm.https.untils.HttpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description  = "燃气接口类")
@RestController
@RequestMapping("/rq")
public class RanQiZsInterfaceController {
	private Logger logger = LoggerFactory.getLogger(RanQiZsInterfaceController.class);



	@ApiOperation(value = "燃气账单查询接口" ,  notes="返回参数")
	@RequestMapping(value = "/charge",method = {RequestMethod.POST,RequestMethod.GET})
	public Object charge(@RequestParam(value = "user_no",required = true)String user_no){
		try{
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

	@ApiOperation(value = "豫南燃气公司营业网点查询接口" ,  notes="返回参数")
	@RequestMapping(value = "/wdinfo",method = {RequestMethod.POST,RequestMethod.GET})
	public Object wdinfo(){
		Map<String, Object> map = new HashMap<>();
		try{

			map.put("status","0");
			map.put("mess","查询成功");
			List<Object> data = new ArrayList<>();

			data.add("解放路营业厅：解放路68号中原大厦1楼 电话：0396-2958668");
			data.add("自由街营业厅：驻马店市自由街1号  电话0396-2912106");
			data.add("泰山路营业厅：泰山路弘佳花园楼下  电话：0396-2199226");
			data.add("练江路营业厅：白桥路新天城市花园楼下  电话：0396-2376602");
			data.add("遂平营业网点：遂平县国槐路中段路北  电话0396-4900938");
			data.add("确山营业网点：确山县生产街原水务公司院内  电话:0396-7017188");
			data.add("平舆营业网点：平舆县东皇大道221号  电话:0396-5006803");
			data.add("汝南营业网点：汝南县双星大道汝南县中医院西侧20米 电话:0396-8036799");
			data.add("泌阳营业网点：泌阳县迎宾路中段老畜牧局院内 电话:0396-7905022");
			data.add("正阳营业网点：正阳县慎西路车管所北100米路东 电话:0396-8923099");
			data.add("上蔡营业网点：上蔡县秦相路南段新农合隔壁  电话:0396-6968908");
			data.add("新蔡营业网点：新蔡县人民路西段月亮湾派出所斜对面往西200米路南 电话:0396-2737079");
			data.add("豫南燃气统一对外热线服务电话：4000-678-099");
			map.put("data",data);
			logger.info("===============豫南燃气公司营业网点查询接口查询:返回  "+JSONObject.toJSONString(data));
			return data;
		}catch (Exception e){
			e.printStackTrace();
			map.put("status","1");
			map.put("mess","查询失败："+e.getMessage());
			map.put("data",null);
			return map;
		}
	}

	@ApiOperation(value = "加气站地址查询接口" ,  notes="返回参数")
	@RequestMapping(value = "/jqzAddressInfo",method = {RequestMethod.POST,RequestMethod.GET})
	public Object jqzAddressInfo(){
		Map<String, Object> map = new HashMap<>();
		try{

			map.put("status","0");
			map.put("mess","查询成功");
			List<Object> data = new ArrayList<>();
			data.add("上蔡县驻上公路S206与S219交叉口向南150米路西 负责人：郑雪龙  15290131515");
			data.add("汝南县西城大道与S333升到交叉口西南角 负责人：牛运红  13723057757");
			map.put("data",data);
			logger.info("===============加气站地址查询接口查询:返回  "+JSONObject.toJSONString(data));
			return data;
		}catch (Exception e){
			e.printStackTrace();
			map.put("status","1");
			map.put("mess","查询失败："+e.getMessage());
			map.put("data",null);
			return map;
		}
	}

	@ApiOperation(value = "燃气提供情况查询接口" ,  notes="返回参数")
	@RequestMapping(value = "/rqtgqkInfo",method = {RequestMethod.POST,RequestMethod.GET})
	public Object rqtgqkInfo(){
		Map<String, Object> map = new HashMap<>();
		try{

			map.put("status","0");
			map.put("mess","查询成功");
			List<Object> data = new ArrayList<>();
			data.add("2019年1-11月份天然气销售 12188.87万立方米.");
			data.add("2019年截止11月居民用户共39.76万户");
			map.put("data",data);
			logger.info("===============加气站地址查询接口查询:返回  "+JSONObject.toJSONString(data));
			return data;
		}catch (Exception e){
			e.printStackTrace();
			map.put("status","1");
			map.put("mess","查询失败："+e.getMessage());
			map.put("data",null);
			return map;
		}
	}


	@ApiOperation(value = "验证用户号、姓名的有效性接口" ,  notes="返回参数")
	@RequestMapping(value = "/checkUser",method = {RequestMethod.POST,RequestMethod.GET})
	public Object checkUser(@RequestParam(value = "user_no",required = true)String user_no,@RequestParam(value = "cust_name",required = true)String cust_name){
		try{
			long millis = System.currentTimeMillis();
			Map<String, Object> map = new HashMap<>();
			map.put("user_no",user_no);
			map.put("timestamp",millis);
			String jsonString = JSONObject.toJSONString(map);
			logger.info(jsonString);
			Object post = HttpUtil.sendPost("http://59.207.237.9:8083/BillQuery/CheckUser", jsonString);
			//String tdsyq = getResult(bdcPost, "TDSYQ");
			logger.info("===============燃气账单查询接口返回=="+post);
			return post;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String[] args) throws Exception {
		long millis = System.currentTimeMillis();
		Map<String, Object> map = new HashMap<>();
		map.put("user_no","1100031619");
		map.put("cust_name","郑富文");
		map.put("timestamp",millis);
		String jsonString = JSONObject.toJSONString(map);

		Object post = HttpUtil.sendPost("http://59.207.237.9:8083/BillQuery/CheckUser", jsonString);
		System.out.println(post);
	}



}
