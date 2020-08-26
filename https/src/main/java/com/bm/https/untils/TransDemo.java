package com.bm.https.untils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TransDemo {
	public static TransDemo transDemo;
	private static Logger logger = LoggerFactory.getLogger(TransDemo.class);

   
   public static void insertDB(List<Map<String, Object>> param, String sql,  String url,  String name,  String pwd,  String driver) throws Exception {
		Class.forName(driver);
		final Connection conn = DriverManager.getConnection(url, name,pwd);
		conn.setAutoCommit(false);
		PreparedStatement preparedStatement2 = conn.prepareStatement(sql);
		try {
			int num = 0;

			for (Map<String, Object> params : param) {

				num++;
				int n = 0;
				Set<Entry<String, Object>> entrySet = params.entrySet();
				for (Entry<String, Object> entry : entrySet) {
					n++;
					String key = entry.getKey();
					Object value = entry.getValue();

					if (key.contains("91")) {
						if (value == null) {
							preparedStatement2.setDate(n, null);
						} else {
							preparedStatement2.setDate(n, (java.sql.Date) value);
						}
					} else if (key.contains("93")) {
						if (value == null) {
							preparedStatement2.setTimestamp(n, null);
						} else {
							preparedStatement2.setTimestamp(n, (java.sql.Timestamp) value);
						}
					} else if (key.contains("2004")) {
						if (value == null) {
							value = "";
						}
						InputStream inputStream = new ByteArrayInputStream(value.toString().getBytes());
						preparedStatement2.setBlob(n, inputStream);

					} else if (key.contains("2005")) {
						if (value == null) {
							value = "";
						}
						StringReader resder = new StringReader((String) value);
						preparedStatement2.setCharacterStream(n, resder, value.toString().length());

					} else {
						preparedStatement2.setString(n, (String) value);
					}
				}
				preparedStatement2.addBatch();
				if (num > 20000) {
					preparedStatement2.executeBatch();
					conn.commit();
					num = 0;
					logger.info("开始==commit");
				}

			}

			preparedStatement2.executeBatch();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
			if (preparedStatement2 != null) {
				preparedStatement2.close();
			}

		}

	}

	public static void insertDB(List<Map<String, Object>> param, String sql) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		/*final Connection conn = DriverManager.getConnection(url, name,
				pwd);*/
		final Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@59.207.219.23:1521:orcl", "interfaceDB",
				"interfaceDB");
		conn.setAutoCommit(false);
		PreparedStatement preparedStatement2 = conn.prepareStatement(sql);

		try {
			int num = 0;

			for (Map<String, Object> params : param) {

				num++;
				int n = 0;
				Set<Entry<String, Object>> entrySet = params.entrySet();
				for (Entry<String, Object> entry : entrySet) {
					n++;
					String key = entry.getKey();
					Object value = entry.getValue();

					if (key.contains("91")) {
						if (value == null) {
							preparedStatement2.setDate(n, null);
						} else {
							preparedStatement2.setDate(n, (java.sql.Date) value);
						}
					} else if (key.contains("93")) {
						if (value == null) {
							preparedStatement2.setTimestamp(n, null);
						} else {
							preparedStatement2.setTimestamp(n, (java.sql.Timestamp) value);
						}
					} else if (key.contains("2004")) {
						if (value == null) {
							value = "";
						}
						InputStream inputStream = new ByteArrayInputStream(value.toString().getBytes());
						preparedStatement2.setBlob(n, inputStream);

					} else if (key.contains("2005")) {
						if (value == null) {
							value = "";
						}
						StringReader resder = new StringReader((String) value);
						preparedStatement2.setCharacterStream(n, resder, value.toString().length());

					} else {
						preparedStatement2.setString(n, (String) value);
					}
				}
				preparedStatement2.addBatch();
				if (num > 20000) {
					preparedStatement2.executeBatch();
					conn.commit();
					num = 0;
					logger.info("开始==commit");
				}

			}

			preparedStatement2.executeBatch();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
			if (preparedStatement2 != null) {
				preparedStatement2.close();
			}

		}

	}



	public static void end(final List<Map<String, Object>> getdata, final String sql, final String url, final String name, final String pwd, final String driver) {
		try {
			int size = getdata.size();
			int flag = 40000;
			if (size < 50000) {
				flag = 20000;
			}
			final int ii = size % flag == 0 ? size / flag : size / flag + 1;
			long start = System.currentTimeMillis();
			logger.info("开始==" + start);
			CustomThreadPoolExecutor exec = new CustomThreadPoolExecutor();
			// 1.初始化
			exec.init();
			ExecutorService pool = exec.getCustomThreadPoolExecutor();
			for (int i = 0; i < ii; i++) {
				logger.info("提交第" + i + "个任务!");
				final int j = i;
				final int num = flag;
				pool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							insertDB(getList(getdata, j, num), sql,url,name,pwd,driver);
							TimeUnit.SECONDS.sleep(10);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}

			// 2.销毁----此处不能销毁,因为任务没有提交执行完,如果销毁线程池,任务也就无法执行了
			// exec.destory();
			logger.info("已经开启所有的子线程");
			pool.shutdown();

			while (true) {
				if (pool.isTerminated()) {
					exec.destory();
					logger.info("所有的子线程都结束了！");
					getdata.clear();
					long end = System.currentTimeMillis();
					logger.info("结束==" + (end - start) / 1000 + " s");
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public static void end(final List<Map<String, Object>> getdata, final String sql) {
		try {
			int size = getdata.size();
			int flag = 40000;
			if (size < 50000) {
				flag = 20000;
			}
			final int ii = size % flag == 0 ? size / flag : size / flag + 1;
			long start = System.currentTimeMillis();
			logger.info("开始==" + start);
			CustomThreadPoolExecutor exec = new CustomThreadPoolExecutor();
			// 1.初始化
			exec.init();
			ExecutorService pool = exec.getCustomThreadPoolExecutor();
			for (int i = 0; i < ii; i++) {
				logger.info("提交第" + i + "个任务!");
				final int j = i;
				final int num = flag;
				pool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							insertDB(getList(getdata, j, num), sql);
							TimeUnit.SECONDS.sleep(10);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}

			// 2.销毁----此处不能销毁,因为任务没有提交执行完,如果销毁线程池,任务也就无法执行了
			// exec.destory();
			logger.info("已经开启所有的子线程");
			pool.shutdown();

			while (true) {
				if (pool.isTerminated()) {
					exec.destory();
					logger.info("所有的子线程都结束了！");
					getdata.clear();
					long end = System.currentTimeMillis();
					logger.info("结束==" + (end - start) / 1000 + " s");
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static List<Map<String, Object>> getList(List<Map<String, Object>> map, int count, int num) {
		if (map != null && map.size() > num) {
			if (map.size() % num == 0) {
				List<Map<String, Object>> subList = map.subList(count * num, (count + 1) * num);
				return subList;
			} else {
				if (map.size() / num == count) {
					List<Map<String, Object>> subList = map.subList(count * num, map.size());
					return subList;
				} else {
					List<Map<String, Object>> subList = map.subList(count * num, (count + 1) * num);
					return subList;
				}

			}

		} else {
			return map;
		}

	}
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static PreparedStatement statement = null;
	private static Statement createStatement = null;
	public static Connection getConn(String url, String user, String password,String driver) {

		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			logger.info("Connecter=>getConnection error: " + e);
		}
		return conn;
	}

	/**
	 * 将"Clob"型数据转换成"String"型数据
	 * 需要捕获"SQLException","IOException"
	 * prama:    colb1 将被转换的"Clob"型数据
	 * return:    返回转好的字符串
	 * @throws SQLException
	 * @throws IOException */
	private static String clobToString(Clob colb) throws SQLException, IOException
	{
		String outfile = "";
		if(colb != null){
			//oracle.sql.CLOB clob = (oracle.sql.CLOB)colb1;
			java.io.Reader is = colb.getCharacterStream();
			java.io.BufferedReader br = new java.io.BufferedReader(is);
			String s = br.readLine();
			while (s != null) {
				outfile += s;
				s = br.readLine();
			}
			is.close();
			br.close();
		}
		return  outfile;
	}

	/**
	 * 将"Clob"型数据转换成"String"型数据
	 * 需要捕获"SQLException","IOException"
	 * prama:    colb1 将被转换的"Clob"型数据
	 * return:    返回转好的字符串
	 * @throws SQLException
	 * @throws IOException */
	private static String blobToString(Blob blob) throws SQLException, IOException
	{
		// Blob 是二进制文件，转成文字是没有意义的
		// 所以根据传输协议，EzManager的传输协议是无法支持的
		byte[] base64;
		String newStr = ""; //返回字符串

		if(blob!=null)
		{
			try {
				base64 = org.apache.commons.io.IOUtils.toByteArray(blob.getBinaryStream());
				//newStr = new BASE64Encoder().encodeBuffer(base64);
				newStr = new jcifs.util.Base64().encode(base64);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return newStr;
	}
	private static Object getValueByType(ResultSet rs, int type, String name) throws Exception {

		switch (type) {
			case Types.DATE:
				return rs.getDate(name);
			case Types.TIMESTAMP:
				return rs.getTimestamp(name);
			case Types.CLOB:
				return clobToString(rs.getClob(name));
			case Types.BLOB:
				return blobToString(rs.getBlob(name));
			default:
				return rs.getString(name);
		}
	}

	/**
	 * 公共数据交换 支持多数据库
	 * @param datasource 数据源库
	 * @param dataneed 数据插入库
	 * @param nums  数据每次多少提交  限制在 5 10 15 20（万）
	 */
	public static String publicDataTrans(Map<String,Object> datasource,Map<String,Object> dataneed,int nums){


			final List<Map<String, Object>> map = new LinkedList<>();
			try {
				String sourceurl = (String)datasource.get("sourceurl");//源数据库信息
				String sourcename =(String) datasource.get("sourcename");
				String sourcepassword = (String)datasource.get("sourcepassword");
				String sourcedriver = (String)datasource.get("sourcedriver");
				String sourcesql = (String)datasource.get("sourcesql");//查询申sql

				String needurl = (String)dataneed.get("needurl");//目标数据库信息
				String needname =(String) dataneed.get("needname");
				String needpassword = (String)dataneed.get("needpassword");
				String needdriver = (String)dataneed.get("needdriver");
				String needsql = (String)dataneed.get("needsql");//插入sql
				//备注  插入sql的字段和查询sql的字段可以不一样  但是必须顺序一致 不能使用*
				long start = System.currentTimeMillis();
				logger.info("总时间开始开始==================" + start);

				//获取源数据库信息  执行查询
				conn = getConn(sourceurl, sourcename, sourcepassword,sourcedriver);
				createStatement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

				ResultSet rs = createStatement.executeQuery(sourcesql);
				ResultSetMetaData md = rs.getMetaData();
				int columnCount = md.getColumnCount();
				rs.last();
				int row = rs.getRow();
				int yu = row / nums;
				int i = 0;
				CustomThreadPoolExecutor exec = null;
				ExecutorService pool = null;
				if(row>=nums){
					exec = new CustomThreadPoolExecutor();
					exec.init();
					pool = exec.getCustomThreadPoolExecutor();
				}
				rs.beforeFirst();

				while(rs.next()){

					Map<String, Object> map2 =  new java.util.LinkedHashMap<>();
					for (int j = 0; j < columnCount; j++) {
						map2.put(md.getColumnType(j + 1) + "" + j, getValueByType(rs,md.getColumnType(j + 1), md.getColumnName(j + 1)));
					}
					map.add(map2);
					if(row>=nums){
						if(i<yu){
							if(map.size()>=nums){
								i++;
								final List<Map<String, Object>> map1 = new LinkedList<>();
								map1.addAll(map);
								map.clear();
								pool.execute(new Runnable() {
									@Override
									public void run() {
										try {
											end(map1,needsql,needurl,needname,needpassword,needdriver);
											TimeUnit.SECONDS.sleep(10);
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								});

								logger.info("list的size清除"+map.size());
							}

						}
					}
				}
				end(map,needsql,needurl,needname,needpassword,needdriver);
				if(row>=nums){
					logger.info("已经开启所有的子线程");
					pool.shutdown();

					while (true) {
						if (pool.isTerminated()) {
							exec.destory();
							logger.info("所有的子线程都结束了！");
							map.clear();
							long end = System.currentTimeMillis();
							logger.info("总时间结束结束=================== "+(end-start)/1000+"S");
							break;
						}
					}
				}
				long end = System.currentTimeMillis();
				logger.info("插入完毕结束结束=================== "+(end-start)/1000+"S");
				return "000";
			} catch (Exception e) {
				e.printStackTrace();
				return "111";
			}finally{
				try{
					if(conn!=null)conn.close();
					if(createStatement!=null)createStatement.close();
				}catch (Exception e){}

			}
	}


   /**
    * @param args
 * @throws SQLException 
    */
   public static void main(String[] args) throws SQLException {
	   final List<Map<String, Object>> map = new LinkedList<>();
       try {

	   conn = getConn("jdbc:oracle:thin:@59.207.219.145:1521:orcl", "gongjijin_user", "gongjijin","oracle.jdbc.driver.OracleDriver");
	   createStatement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
  		//String sql = "SELECT PROJID, PROJPWD, SERVICECODE, SERVICEVERSION,SERVICENAME , PROJECTNAME, INFOTYPE, BUS_TYPE, REL_BUS_ID, APPLYNAME, APPLY_CARDTYPE, APPLY_CARDNUMBER, CONTACTMAN, CONTACTMAN_CARDTYPE, CONTACTMAN_CARDNUMBER, TELPHONE, POSTCODE, ADDRESS, LEGALMAN, DEPTID, DEPTNAME, SS_ORGCODE, RECEIVE_USEID, RECEIVE_NAME, APPLYFROM, APPLY_TYPE, BUS_MODE, BUS_MODE_DESC, IS_MANUBRIUM, APPLY_PROPERTIY, RECEIVETIME, BELONGTO, AREACODE, DATASTATE, BELONGSYSTEM, EXTEND, CREATE_TIME, SYNC_STATUS, DATAVERSION, HANDLESTATE, QL_KIND, ACCEPT_TIME, PROMISEE_TIME, TRANSACT_TIME, FLOW_XML, CTRTIME, BATCHNO FROM PRE_APASINFO where PROJID IN (select projid from projid_temp3 )";
  		//String sql = "SELECT UNID, PROJID, ACTION, NODE_NAME, NAME, OPINION, START_TIME, END_TIME, REMARK, BELONGSYSTEM, EXTEND, CREATE_TIME,SYNC_STATUS , DATAVERSION, NEXT_NODE_NAME, HANDER_DEPTNAME, HANDER_DEPTID, AREACODE, PHASE_NAME, PHASE_CODE, CTRTIME, BATCHNO FROM PRE_NODE where PROJID IN (select projid from projid_temp3 )";
  		//String sql = "SELECT PROJID, ACCEPT_MAN, ACCEPT_TIME, PROMISEVALUE, PROMISETYPE, PROMISE_ETIME, BELONGSYSTEM, EXTEND, CREATE_TIME, SYNC_STATUS, DATAVERSION, HANDER_DEPTNAME, DOCUMENT_CODE, HANDER_DEPTID, AREACODE, CTRTIME, BATCHNO FROM PRE_ACCEPT where PROJID IN (select projid from projid_temp3 )";
  		//String sql = "SELECT  UNID, PROJID, ATTRNAME, ATTRID, SORTID, TAKETYPE, ISTAKE, AMOUNT, TAKETIME, MEMO, BELONGSYSTEM, EXTEND, CREATE_TIME, FILE_URL, FILENAME, SYNC_STATUS, DATAVERSION, AREACODE, CTRTIME, BATCHNO FROM PRE_ATTR where PROJID IN (select projid from projid_temp3 )";
  		//String sql = "SELECT  UNID, PROJID, CATALOG_CODE, LOCAL_CATALOG_CODE, TASK_CODE, TASK_HANDLE_ITEM, APPLYER_TYPE_CODE, APPLY_CARDTYPE_CODE, CONTACTMAN_CARDTYPE_CODE, IS_DELIVERY_RESULT, SATISFACTION, RESULT_CETR_NAME, RESULT_CETR_CODE, GB_PROJECT_NO, RESULT_ACCESS, REMARK, BELONGSYSTEM, EXTEND, CREATE_TIME, SYNC_STATUS, DATAVERSION, CTRTIME, BATCHNO FROM PRE_APASINFO_EXT where PROJID IN (select projid from projid_temp3 )";
  		String sql = "SELECT UNID, PROJID, TRANSACT_USER, TRANSACT_TIME, HANDLESTART, TRANSACT_DESCRIBE, TRANSACT_RESULT, RESULT_CODE, REMARK, BELONGSYSTEM, EXTEND, CREATE_TIME, SYNC_STATUS, DATAVERSION, ELECTRONIC_RECORDS, RESULTS_TIME, HANDER_DEPTNAME, HANDER_DEPTID, AREACODE, CTRTIME, BATCHNO  FROM PRE_TRANSACT where PROJID IN (select projid from projid_temp3 )";
	   long start = System.currentTimeMillis();
	   logger.info("总时间开始开始==================" + start);
	   ResultSet rs = createStatement.executeQuery(sql);
	   rs.last();
	   int row = rs.getRow();
	   logger.info("总时间开始开始==================" + row);
	   int yu = row / 150000;
	   int i = 0;
	   logger.info("开始查询"); 
	   CustomThreadPoolExecutor exec = null;
	   ExecutorService pool = null;
	   if(row>=150000){
		   	exec = new CustomThreadPoolExecutor();
			// 1.初始化
			exec.init();
			pool = exec.getCustomThreadPoolExecutor();
	   }
	   rs.beforeFirst();
	   
   	   while(rs.next()){
   		   
	   		Map<String, Object> map2 =  new java.util.LinkedHashMap<>();
	   		//apainfo
	   		/*map2.put("121", rs.getString("PROJID"));
	   		map2.put("122", rs.getString("PROJPWD"));
	   		map2.put("123", rs.getString("SERVICECODE"));
	   		map2.put("124", rs.getString("SERVICEVERSION"));
	   		map2.put("125", rs.getString("SERVICENAME"));
	   		map2.put("126", rs.getString("PROJECTNAME"));
	   		map2.put("127", rs.getString("INFOTYPE"));
	   		map2.put("128", rs.getString("BUS_TYPE"));
	   		map2.put("129", rs.getString("REL_BUS_ID"));
	   		map2.put("1210", rs.getString("APPLYNAME"));
	   		map2.put("1211", rs.getString("APPLY_CARDTYPE"));
	   		map2.put("1212", rs.getString("APPLY_CARDNUMBER"));
	   		map2.put("1213", rs.getString("CONTACTMAN"));
	   		map2.put("1214", rs.getString("CONTACTMAN_CARDTYPE"));
	   		map2.put("1215", rs.getString("CONTACTMAN_CARDNUMBER"));
	   		map2.put("1216", rs.getString("TELPHONE"));
	   		map2.put("1217", rs.getString("POSTCODE"));
	   		map2.put("1218", rs.getString("ADDRESS"));
	   		map2.put("1219", rs.getString("LEGALMAN"));
	   		map2.put("1220", rs.getString("DEPTID"));
	   		map2.put("1221", rs.getString("DEPTNAME"));
	   		map2.put("1222", rs.getString("SS_ORGCODE"));
	   		map2.put("1223", rs.getString("RECEIVE_USEID"));
	   		map2.put("1224", rs.getString("RECEIVE_NAME"));
	   		map2.put("1225", rs.getString("APPLYFROM"));
	   		map2.put("1226", rs.getString("APPLY_TYPE"));
	   		map2.put("1227", rs.getString("BUS_MODE"));
	   		map2.put("1228", rs.getString("BUS_MODE_DESC"));
	   		map2.put("1229", rs.getString("IS_MANUBRIUM"));
	   		map2.put("1230", rs.getString("APPLY_PROPERTIY"));
	   		map2.put("911", rs.getDate("RECEIVETIME"));
	   		map2.put("1231", rs.getString("BELONGTO"));
	   		map2.put("1232", rs.getString("AREACODE"));
	   		map2.put("1233", rs.getString("DATASTATE"));
	   		map2.put("1234", rs.getString("BELONGSYSTEM"));
	   		map2.put("1235", rs.getString("EXTEND"));
	   		map2.put("912", rs.getDate("CREATE_TIME"));
	   		map2.put("1236", rs.getString("SYNC_STATUS"));
	   		map2.put("1237", rs.getString("DATAVERSION"));
	   		map2.put("1238", rs.getString("HANDLESTATE"));
	   		map2.put("1239", rs.getString("QL_KIND"));
	   		map2.put("913", rs.getDate("ACCEPT_TIME"));
	   		map2.put("914", rs.getDate("PROMISEE_TIME"));
	   		map2.put("915", rs.getDate("TRANSACT_TIME"));
	   		map2.put("1240", rs.getString("FLOW_XML"));
	   		map2.put("916", rs.getDate("CTRTIME"));
	   		map2.put("1241", rs.getString("BATCHNO"));*/
	   		
	   		
	   		/*map2.put("120", rs.getString("UNID"));  //node
	   		map2.put("121", rs.getString("PROJID"));
	   		map2.put("122", rs.getString("ACTION"));
	   		map2.put("123", rs.getString("NODE_NAME"));
	   		map2.put("124", rs.getString("NAME"));
	   		map2.put("125", rs.getString("OPINION"));
	   		map2.put("911", rs.getDate("START_TIME"));
	   		map2.put("912", rs.getDate("END_TIME"));
	   		map2.put("128", rs.getString("REMARK"));
	   		map2.put("129", rs.getString("BELONGSYSTEM"));
	   		map2.put("1210", "I");
	   		map2.put("913", rs.getDate("CREATE_TIME"));
	   		map2.put("1212", rs.getString("SYNC_STATUS"));
	   		map2.put("1213", rs.getString("DATAVERSION"));
	   		map2.put("1214", rs.getString("NEXT_NODE_NAME"));
	   		map2.put("1215", rs.getString("HANDER_DEPTNAME"));
	   		map2.put("1216", rs.getString("HANDER_DEPTID"));
	   		map2.put("1217", rs.getString("AREACODE"));
	   		map2.put("1218", rs.getString("PHASE_NAME"));
	   		map2.put("1219", rs.getString("PHASE_CODE"));
	   		map2.put("914", rs.getDate("CTRTIME"));
	   		map2.put("1221", rs.getString("BATCHNO"));*/
	   		
	   		
	   		
	   	/*map2.put("120", rs.getString("UNID"));  //attr
	   		map2.put("121", rs.getString("PROJID"));
	   		map2.put("122", rs.getString("ATTRNAME"));
	   		map2.put("123", "D84518B3DC11A26827CBA1DC9FF7E1D6");
	   		map2.put("124", rs.getString("SORTID"));
	   		map2.put("125", rs.getString("TAKETYPE"));
	   		map2.put("126", rs.getString("ISTAKE"));
	   		map2.put("127", rs.getString("AMOUNT"));
	   		map2.put("911", rs.getDate("TAKETIME"));
	   		map2.put("129", rs.getString("MEMO"));
	   		map2.put("1210", rs.getString("BELONGSYSTEM"));
	   		map2.put("1211","I");
	   		map2.put("912", rs.getDate("CREATE_TIME"));
	   		map2.put("1213", rs.getString("FILE_URL"));
	   		map2.put("1214", rs.getString("FILENAME"));
	   		map2.put("1215", rs.getString("SYNC_STATUS"));
	   		map2.put("1216", rs.getString("DATAVERSION"));
	   		map2.put("1217", rs.getString("AREACODE"));
	   		map2.put("914", rs.getDate("CTRTIME"));
	   		map2.put("1221", rs.getString("BATCHNO"));
	   		*/
	   		
	   		map2.put("120", rs.getString("UNID"));  //transact
	   		map2.put("121", rs.getString("PROJID"));
	   		map2.put("122", rs.getString("TRANSACT_USER"));
	   		map2.put("911", rs.getDate("TRANSACT_TIME"));
	   		map2.put("124", rs.getString("HANDLESTART"));
	   		map2.put("125", rs.getString("TRANSACT_DESCRIBE"));
	   		map2.put("126", rs.getString("TRANSACT_RESULT"));
	   		map2.put("127", rs.getString("RESULT_CODE"));
	   		map2.put("128", rs.getString("REMARK"));
	   		map2.put("129", rs.getString("BELONGSYSTEM"));
	   		map2.put("1210", "I");
	   		map2.put("912", rs.getDate("CREATE_TIME"));
	   		map2.put("1211", rs.getString("SYNC_STATUS"));
	   		map2.put("1213", rs.getString("DATAVERSION"));
	   		map2.put("2005", rs.getClob("ELECTRONIC_RECORDS"));
	   		map2.put("913", rs.getDate("RESULTS_TIME"));
	   		map2.put("1216", rs.getString("HANDER_DEPTNAME"));
	   		map2.put("1217", rs.getString("HANDER_DEPTID"));
	   		map2.put("1218", rs.getString("AREACODE"));
	   		map2.put("914", rs.getDate("CTRTIME"));
	   		map2.put("1221", rs.getString("BATCHNO"));
	   	
	   		
	   		/*map2.put("120", rs.getString("UNID")); // apasinfo_ext
	   		map2.put("121", rs.getString("PROJID"));
	   		map2.put("122", rs.getString("CATALOG_CODE"));
	   		map2.put("123", rs.getString("LOCAL_CATALOG_CODE"));
	   		map2.put("124", rs.getString("TASK_CODE"));
	   		map2.put("125", rs.getString("TASK_HANDLE_ITEM"));
	   		map2.put("126", rs.getString("APPLYER_TYPE_CODE"));
	   		map2.put("127", rs.getString("APPLY_CARDTYPE_CODE"));
	   		map2.put("128", rs.getString("CONTACTMAN_CARDTYPE_CODE"));
	   		map2.put("129", rs.getString("IS_DELIVERY_RESULT"));
	   		map2.put("1210", rs.getString("SATISFACTION"));
	   		map2.put("1211", rs.getString("RESULT_CETR_NAME"));
	   		map2.put("1212", rs.getString("RESULT_CETR_CODE"));
	   		map2.put("1213", rs.getString("GB_PROJECT_NO"));
	   		map2.put("1214", rs.getString("RESULT_ACCESS"));
	   		map2.put("1215", rs.getString("REMARK"));
	   		map2.put("1216", rs.getString("BELONGSYSTEM"));
	   		map2.put("1217","I");
	   		map2.put("911", rs.getDate("CREATE_TIME"));
	   		map2.put("1218", rs.getString("SYNC_STATUS"));
	   		map2.put("1219", rs.getString("DATAVERSION"));
	   		map2.put("914", rs.getDate("CTRTIME"));
	   		map2.put("1221", rs.getString("BATCHNO"));*/
	   
	   		/*map2.put("121", rs.getString("PROJID"));//accept
	   		map2.put("122", rs.getString("ACCEPT_MAN"));
	   		map2.put("911", rs.getDate("ACCEPT_TIME"));
	   		map2.put("123", rs.getString("PROMISEVALUE"));
	   		map2.put("124", rs.getString("PROMISETYPE"));
	   		map2.put("917", rs.getDate("PROMISE_ETIME"));
	   		map2.put("125", rs.getString("BELONGSYSTEM"));
	   		map2.put("126", "I");
	   		map2.put("918", rs.getDate("CREATE_TIME"));
	   		map2.put("127", rs.getString("SYNC_STATUS"));
	   		map2.put("128", rs.getString("DATAVERSION"));
	   		map2.put("1211", rs.getString("HANDER_DEPTNAME"));
	   		map2.put("1212", rs.getString("DOCUMENT_CODE"));
	   		map2.put("1213", rs.getString("HANDER_DEPTID"));
	   		map2.put("129", rs.getString("AREACODE"));
	   		map2.put("916", rs.getDate("CTRTIME"));
	   		map2.put("1210", rs.getString("BATCHNO"));*/
	   		map.add(map2);
	   		
	   		if(row>=150000){
	   			if(i<yu){
	   				if(map.size()>=150000){
	   					i++;
			   			final List<Map<String, Object>> map1 = new LinkedList<>();
						logger.info("list的size超过150000"+map1.size());
						map1.addAll(map);
						map.clear();
			   			pool.execute(new Runnable() {
							@Override
							public void run() {
								try {
									end(map1,"insert into PRE_TRANSACT(UNID, PROJID, TRANSACT_USER, TRANSACT_TIME, HANDLESTART, TRANSACT_DESCRIBE, TRANSACT_RESULT, RESULT_CODE, REMARK, BELONGSYSTEM, EXTEND, CREATE_TIME, SYNC_STATUS, DATAVERSION, ELECTRONIC_RECORDS, RESULTS_TIME, HANDER_DEPTNAME, HANDER_DEPTID, AREACODE, CTRTIME, BATCHNO ) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"); 
								   	TimeUnit.SECONDS.sleep(10);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
			   			
			   			logger.info("list的size清除"+map.size());
			   		}
		   			
	   			}
	   			
	   			
	   		}
	   		
   	   }
   	   
         //end(map,"insert into PRE_APASINFO(PROJID, PROJPWD, SERVICECODE, SERVICEVERSION, SERVICENAME, PROJECTNAME, INFOTYPE, BUS_TYPE, REL_BUS_ID, APPLYNAME, APPLY_CARDTYPE, APPLY_CARDNUMBER, CONTACTMAN, CONTACTMAN_CARDTYPE, CONTACTMAN_CARDNUMBER, TELPHONE, POSTCODE, ADDRESS, LEGALMAN, DEPTID, DEPTNAME, SS_ORGCODE, RECEIVE_USEID, RECEIVE_NAME, APPLYFROM, APPLY_TYPE, BUS_MODE, BUS_MODE_DESC, IS_MANUBRIUM, APPLY_PROPERTIY, RECEIVETIME, BELONGTO, AREACODE, DATASTATE, BELONGSYSTEM, EXTEND, CREATE_TIME, SYNC_STATUS, DATAVERSION, HANDLESTATE, QL_KIND, ACCEPT_TIME, PROMISEE_TIME, TRANSACT_TIME, FLOW_XML, CTRTIME, BATCHNO) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"); 
   	   //end(map,"insert into PRE_NODE(UNID, PROJID, ACTION, NODE_NAME, NAME, OPINION, START_TIME, END_TIME, REMARK, BELONGSYSTEM, EXTEND, CREATE_TIME, SYNC_STATUS, DATAVERSION, NEXT_NODE_NAME, HANDER_DEPTNAME, HANDER_DEPTID, AREACODE, PHASE_NAME, PHASE_CODE, CTRTIME, BATCHNO) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"); 
         //end(map,"insert into PRE_ACCEPT(PROJID, ACCEPT_MAN, ACCEPT_TIME, PROMISEVALUE, PROMISETYPE, PROMISE_ETIME, BELONGSYSTEM, EXTEND, CREATE_TIME, SYNC_STATUS, DATAVERSION, HANDER_DEPTNAME, DOCUMENT_CODE, HANDER_DEPTID, AREACODE, CTRTIME, BATCHNO) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"); 
       // end(map,"insert into PRE_APASINFO_EXT(UNID, PROJID, CATALOG_CODE, LOCAL_CATALOG_CODE, TASK_CODE, TASK_HANDLE_ITEM, APPLYER_TYPE_CODE, APPLY_CARDTYPE_CODE, CONTACTMAN_CARDTYPE_CODE, IS_DELIVERY_RESULT, SATISFACTION, RESULT_CETR_NAME, RESULT_CETR_CODE, GB_PROJECT_NO, RESULT_ACCESS, REMARK, BELONGSYSTEM, EXTEND, CREATE_TIME, SYNC_STATUS, DATAVERSION, CTRTIME, BATCHNO) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"); 
       // end(map,"insert into PRE_ATTR(UNID, PROJID, ATTRNAME, ATTRID, SORTID, TAKETYPE, ISTAKE, AMOUNT, TAKETIME, MEMO, BELONGSYSTEM, EXTEND, CREATE_TIME, FILE_URL, FILENAME, SYNC_STATUS, DATAVERSION, AREACODE, CTRTIME, BATCHNO) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"); 
   	   end(map,"insert into PRE_TRANSACT(UNID, PROJID, TRANSACT_USER, TRANSACT_TIME, HANDLESTART, TRANSACT_DESCRIBE, TRANSACT_RESULT, RESULT_CODE, REMARK, BELONGSYSTEM, EXTEND, CREATE_TIME, SYNC_STATUS, DATAVERSION, ELECTRONIC_RECORDS, RESULTS_TIME, HANDER_DEPTNAME, HANDER_DEPTID, AREACODE, CTRTIME, BATCHNO ) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"); 
         if(row>=150000){
 		   	logger.info("已经开启所有的子线程");
 			pool.shutdown();
 		
 			while (true) {
 				if (pool.isTerminated()) {
 					exec.destory();
 					logger.info("所有的子线程都结束了！");
 					map.clear();
 					long end = System.currentTimeMillis();
 					logger.info("总时间结束结束=================== "+(end-start)/1000+"S");
 					break;
 				}
 			}
    	   }
     	long end = System.currentTimeMillis();
		logger.info("插入完毕结束结束=================== "+(end-start)/1000+"S");
       } catch (Exception e) {
           e.printStackTrace();
       }finally{
    	   if(conn!=null)conn.close();
    	   if(createStatement!=null)createStatement.close();
       }
   }
}
