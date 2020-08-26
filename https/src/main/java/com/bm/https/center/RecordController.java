package com.bm.https.center;


import com.bm.https.untils.RecordtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/record")
public class RecordController {

    private Logger logger = LoggerFactory.getLogger(RecordController.class);
    private static final String sql2 = "select T.DBURL as url,T.DBNAME as name,T.DBPWD as pwd ,T.DBDEPT AS dname,T.DBSQL as sql   from BANJIANDB T where t.datatype ='1'";
    private static final String sql3 = "select T.DBURL as url,T.DBNAME as name,T.DBPWD as pwd ,T.DBDEPT AS dname,T.DBSQL as sql   from BANJIANDB T where t.datatype ='2'";
    private static final String sql1 = "insert into zmd_pie (year,district,rca,toalrca) values(?,?,?,?)";
    private static final String sql4 = "insert into zmd_cer (year,district,rca,toalrca) values(?,?,?,?)";
    private static final String sql6 = "select count(1) as nums from web_info t where t.rescode = ?";
    private static final String sql5 = "select count(1) as dailynum, t.rescode as rescode, t2.restitle as resname,t2.rpdeptname as dname from web_info t left join (select a.restitle, b.rpdeptname,a.resourceid from resourceinfo@exzyml a left join department@exzyml b on a.rpdeptcode = b.rpdeptcode) t2 on t.rescode = t2.resourceid where t.crtdate between ? and ? group by t.rescode,t2.restitle,t2.rpdeptname";
    private static final String sql7 = "select t.total as nums ,t.dailytotal as dailynum ,t.interfacename as resname from pub_interfacerecord t where t.crttime between to_timestamp(?,'yyyy-MM-dd hh24:mi:ss') and to_timestamp(?,'yyyy-MM-dd hh24:mi:ss')";
    private static final String sql8 = "insert into zmd_intf (year,district,pts,toalpts,industry) values(?,?,?,?,?)";
    private static final String sql9 = "select toalrca from zmd_pie t where t.district = ? and t.`year` = ?";
    private static final String sql10 = "select toalrca from zmd_cer t where t.district = ? and t.`year` = ?";
    private static final String sql11 = "select t1.rpdeptname from resourceinfo t left join department t1 on t.rpdeptcode = t1.rpdeptcode where t.restitle = ?";
    private static final String sql12 = "select count(issue_unitname) as dailynum,t.issue_unitname as dname from gongan_hn_license_sync t where t.ctrtime between to_timestamp(?,'yyyy-MM-dd hh24:mi:ss') and to_timestamp(?,'yyyy-MM-dd hh24:mi:ss') group by t.issue_unitname";
    private static final String sql13 = "select count(issue_unitname) as nums,t.issue_unitname as dname from gongan_hn_license_sync t  group by t.issue_unitname";
    private static final String sql14 = "select sum(dailytotal) as nums,t1.interfacename as resname from pub_interfacerecord t1 group by t1.interfacename";
    private static final String sql15 = "select count(1) as nums,t.rescode as rescode,t2.restitle as resname,t2.rpdeptname as dname from web_info t left join (select a.restitle, b.rpdeptname, a.resourceid from resourceinfo@exzyml a left join department@exzyml b on a.rpdeptcode = b.rpdeptcode) t2 on t.rescode = t2.resourceid group by t.rescode, t2.restitle, t2.rpdeptname";
    private static final String sql16 = "select count(1) as nums,t.rescode as rescode,t2.restitle as resname, t2.rpdeptname as dname from web_info t left join (select a.restitle, b.rpdeptname, a.resourceid from resourceinfo@exzymllink a left join department@exzymllink b on a.rpdeptcode = b.rpdeptcode) t2 on t.rescode = t2.resourceid where t2.restitle is not null group by t.rescode, t2.restitle, t2.rpdeptname";
    private static final String sql17 = "select count(1) as dailynum from web_info t left join (select a.restitle,a.resourceid from resourceinfo@exzymllink a ) t2 on t.rescode = t2.resourceid where t2.restitle = ? and t.crtdate between ? and ?  group by t.rescode,t2.restitle";
    private static final String sql18 = "select  sum(t.dailytotal) as dailynum from pub_interfacerecord t where t.interfacename =?  and t.crttime between to_timestamp(?,'yyyy-MM-dd hh24:mi:ss') and to_timestamp(?,'yyyy-MM-dd hh24:mi:ss')";
    private static final String sql19 = "select  t.tablenames as tablenames ,t.tablenamesdesc as tablenamesdesc ,t.datatotal as datatotal ,t.resourcetype as resourcetype from pub_resource_mapping_info t ";
    private static final String sql20 = "select  t.todaydatatotal as todaydatatotal  from pub_resource_stats_info t where t.tablenames = ? and t.statstime = ?";
    private static final String sql21 = "insert into zmd_base (year,district,cont,toalcont,resname,type) values(?,?,?,?,?,?)";
    @Value("${data.mysql.url}")
    private String mysqlurl;
    @Value("${data.mysql.name}")
    private String mysqlname;
    @Value("${data.mysql.pwd}")
    private String mysqlpwd;

    @RequestMapping(value = "/BJDataRecord",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public  List<Map<String,Object>> getHttpTest(@RequestParam(value = "date",required = false)String dates){
        try{
            List<Map<String,Object>> list = new ArrayList<>();
            logger.info("BJDataRecord");
            List<Map<String,Object>> list1 = RecordtUtil.queryList("jdbc:oracle:thin:interfaceDB/interfaceDB@59.207.219.23:1521:orcl", "interfaceDB", "interfaceDB", sql2);
            if(list1!=null||list1.size()>0){
                int bdc = 0;
                int i = 0;
                for (Map<String,Object> mm:
                     list1) {
                    String dname1 = (String) mm.get("dname");

                    Map<String, Object> query = RecordtUtil.queryforbanjian((String) mm.get("url"), (String) mm.get("name"), (String) mm.get("pwd"), (String) mm.get("sql"),dates);
                    if(query!=null&&query.size()>0){

                        Map<String,Object> map1 = new HashMap<>();
                        Object num = query.get("total");
                        Object dailynum = query.get("dailynum");

                        String date = dates.replaceAll("-","");
                        if(dname1.equals("市不动产2")||dname1.equals("市不动产1")){
                            i++;
                            dname1 = "市不动产";
                            bdc = bdc+Integer.parseInt (num+"") ;

                            if(i!=2){
                                continue;
                            }
                            num = bdc;
                        }
                        //由于很多表中没有创建时间这个值  所以改成现查询上一天总数  然后现在总数减去昨天总数即可
                        String date1 = operDate(dates, -1);
                        date1 = date1.replaceAll("-","");
                        Map<String, Object> dname = RecordtUtil.queryOneForAll("com.mysql.jdbc.Driver", mysqlurl, mysqlname, mysqlpwd, sql9, new Object[]{ dname1,date1}, new int[]{12, 12});
                        Integer num2 = null;
                        if(dname!=null&&dname.size()>0){
                            num2 = (Integer)dname.get("toalrca");
                            BigDecimal nn =  BigDecimal.valueOf(num2);
                            if(dname1.equals("市不动产")){
                                Integer bdc2 = bdc - num2;
                                dailynum = bdc2;
                            }else{
                                BigDecimal num1 = (BigDecimal)num;
                                BigDecimal dailynum1 = null;
                                dailynum1= num1.subtract(nn);
                                dailynum = dailynum1;
                            }
                        }
                        map1.put("total",num);
                        map1.put("dailynum",dailynum);
                        map1.put("dname",dname1);
                        map1.put("date", query.get("date"));
                        Object[] arg = new Object[]{date, dname1,dailynum,num};
                        int[] argtype = new int[]{12,12, Types.INTEGER,Types.INTEGER};
                       int j = RecordtUtil.insertDataForBanjian(mysqlurl, mysqlname, mysqlpwd, sql1, arg, argtype);
                        logger.info("插入办件每日更新数量===+j");
                        list.add(map1);
                    }
                }
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }


    }

   public static String operDate(String str, int day) {
       Date date;
       try {
           date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
            //当前日期
           SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//格式化对象
           Calendar calendar = Calendar.getInstance();//日历对象
           calendar.setTime(date);//设置当前日期
           calendar.add(Calendar.DAY_OF_MONTH, day);//加、减
           return format.format(calendar.getTime());
       } catch (ParseException e) {
           e.printStackTrace();
       }
       return "";
   }



    @RequestMapping(value = "/exDataRecord",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public  List<Map<String,Object>> exDataRecord(@RequestParam(value = "date",required = false)String dates){
        try{
            String sql = "select t.tasknam as resname,t.rectabnam as tablenam,t.lognodcode as reslognodcode,t2.logicnodnam as resdeptname,t4.logicnodnam as rpdeptname,t4.dblinkurl as dblinkurl,t4.dbpsw as dbpsw,t4.dbusernam as dbusernam from extask t left join  exres t3 on t.rescode = t3.rescode left join  logicnodinfo t2 on t2.logicnodcode = t3.logicnod  left join  logicnodinfo t4 on t4.logicnodcode = t.lognodcode where t.status = '1'";
            List<Map<String, Object>> mapList = RecordtUtil.queryList("jdbc:oracle:thin:esplatform/esplatform@59.207.219.135:1521:orcl", "esplatform", "esplatform", sql);
            String date = operDate(dates, -1);
            String date1 = dates.replaceAll("-","");
            if(mapList!=null&&mapList.size()>0){
                for (Map<String, Object> map:
                        mapList) {

                    Object resname = map.get("resname");
                    String tablenam = (String)map.get("tablenam");
                    Object reslognodcode = map.get("reslognodcode");
                    Object resdeptname = map.get("resdeptname");
                    Object rpdeptname = map.get("rpdeptname");
                    Object dblinkurl = map.get("dblinkurl");
                    Object dbpsw = map.get("dbpsw");
                    Object dbusernam = map.get("dbusernam");

                    System.out.println(resname+"   "+tablenam+"   "+reslognodcode+"   "+resdeptname+"   "+rpdeptname+"   "+dblinkurl+"   "+dbpsw+"   "+dbusernam);
                    Boolean checkConn = RecordtUtil.checkConn((String) dblinkurl, (String) dbusernam, (String) dbpsw);
                    if(checkConn){
                        String checkTableSql = "select count(*) as nums from user_tables where table_name = ? ";
                        Map<String, Object> checkTable = RecordtUtil.queryOneForAll((String) dblinkurl, (String) dbusernam, (String) dbpsw, checkTableSql, new Object[]{tablenam.toUpperCase()}, new int[]{12});
                        BigDecimal nums = (BigDecimal)checkTable.get("nums");
                        BigDecimal one = new BigDecimal(1);
                        if(nums.compareTo(one)==0){

                            String tablesql = "select count(*) as nums from "+tablenam;
                            Map<String, Object> tablecount = RecordtUtil.queryOneForAll((String) dblinkurl, (String) dbusernam, (String) dbpsw, tablesql, new Object[]{}, new int[]{});
                            BigDecimal totalcount = (BigDecimal)tablecount.get("nums");
                            String latdaysql = "select toalcont from zmd_ex t where t.reqdistrict = ? and t.`year` = ? and t.resname = ?";

                            date = date.replaceAll("-","");
                            List<Map<String, Object>> lastdaycount = RecordtUtil.queryListForAll("com.mysql.jdbc.Driver", mysqlurl, mysqlname, mysqlpwd, latdaysql, new Object[]{ (String)rpdeptname,date,(String)resname}, new int[]{12, 12,12});
                            BigDecimal tt = BigDecimal.ZERO;
                            if(lastdaycount!=null&&lastdaycount.size()>0){
                                int toalcont = (int)lastdaycount.get(0).get("toalcont");
                                BigDecimal toalcont1 = new BigDecimal(toalcont);
                                tt = totalcount.subtract(toalcont1);
                            }

                            Object[] arg = new Object[]{date1,rpdeptname,tt,totalcount,"数据库",resname,resdeptname};
                            int[] argtype = new int[]{12,12, Types.INTEGER,Types.INTEGER,12,12,12};
                            String inertsql = "insert into zmd_ex (year,reqdistrict,cont,toalcont,typename,resname,prodistrict) values(?,?,?,?,?,?,?)";
                            int i = RecordtUtil.insertDataForBanjian(mysqlurl, mysqlname, mysqlpwd, inertsql, arg, argtype);

                        }
                    }
                }
            }

            return null;
        }catch (Exception e){
            return null;
        }
    }


    @RequestMapping(value = "/interDataRecord",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public  List<Map<String,Object>> interDataRecord(@RequestParam(value = "date",required = false)String dates){
            try{
                String sql = "select count(t.rescode) as counts,t.orgcode,t.rescode,t1.rpdeptname as rpdeptname,t2.resnam as resname,t3.logicnodnam as resdeptname from web_info t  left join department t1 on t.orgcode = t1.rpdeptcode  left join exres t2 on t.rescode = t2.rescode left join logicnodinfo t3 on t3.logicnodcode= t2.logicnod where t1.rpdeptname is not null and t2.resnam is not null group by t1.rpdeptname,t.orgcode,t.rescode,t2.resnam,t3.logicnodnam";
                List<Map<String, Object>> mapList = RecordtUtil.queryList("jdbc:oracle:thin:esplatform/esplatform@59.207.219.135:1521:orcl", "esplatform", "esplatform", sql);
                String sql23 = "select count(t.rescode) as counts,t.orgcode,t.rescode,t1.rpdeptname as rpdeptname,t2.resnam as resname,t3.logicnodnam as resdeptname from web_info t  left join department@espDBlink t1 on t.orgcode = t1.rpdeptcode  left join exres@espDBlink t2 on t.rescode = t2.rescode left join logicnodinfo@espDBlink t3 on t3.logicnodcode= t2.logicnod where t1.rpdeptname is not null and t2.resnam is not null group by t1.rpdeptname,t.orgcode,t.rescode,t2.resnam,t3.logicnodnam";
                List<Map<String, Object>> mapList23 = RecordtUtil.queryList("jdbc:oracle:thin:interfaceDB/interfaceDB@59.207.219.23:1521:orcl", "interfaceDB", "interfaceDB", sql23);
                String date = operDate(dates, -1);
                String date1 = dates.replaceAll("-","");
                for (Map<String, Object> map:
                     mapList23) {
                    String orgcode = (String)map.get("orgcode");
                    String rescode = (String)map.get("rescode");
                    String rpdeptname = (String)map.get("rpdeptname");
                    String resname = (String)map.get("resname");
                    String resdeptname = (String)map.get("resdeptname");
                    BigDecimal counts = (BigDecimal)map.get("counts");
                    BigDecimal total = BigDecimal.ZERO;
                    Boolean flag = false;
                    Iterator<Map<String, Object>> iterator = mapList.iterator();
                    while(iterator.hasNext()){
                        Map<String, Object> next = iterator.next();
                        String orgcode1 = (String)next.get("orgcode");
                        String rescode1 = (String)next.get("rescode");
                        BigDecimal counts1 = (BigDecimal)next.get("counts");
                        if(orgcode1.equals(orgcode)&&rescode1.equals(rescode)){
                            total = counts.add(counts1);
                            flag = true;
                            iterator.remove();
                            break;
                        }
                    }
                    date = date.replaceAll("-","");
                    String latdaysql = "select toalcont from zmd_ex t where t.reqdistrict = ? and t.`year` = ? and t.resname = ?";
                    List<Map<String, Object>> lastdaycount = RecordtUtil.queryListForAll("com.mysql.jdbc.Driver", mysqlurl, mysqlname, mysqlpwd, latdaysql, new Object[]{ rpdeptname,date,resname}, new int[]{12, 12,12});
                    BigDecimal tt = BigDecimal.ZERO;
                    BigDecimal totalcont = BigDecimal.ZERO;
                    if(flag){
                        totalcont = total;
                    }else{
                        totalcont =counts;
                    }
                    if(lastdaycount!=null&&lastdaycount.size()>0){
                        int toalcont = (int)lastdaycount.get(0).get("toalcont");
                        BigDecimal toalcont1 = new BigDecimal(toalcont);
                        tt = totalcont.subtract(toalcont1);

                    }

                    Object[] arg = new Object[]{date1,rpdeptname,tt,totalcont,"服务接口",resname,resdeptname};
                    int[] argtype = new int[]{12,12, Types.INTEGER,Types.INTEGER,12,12,12};
                    String inertsql = "insert into zmd_ex (year,reqdistrict,cont,toalcont,typename,resname,prodistrict) values(?,?,?,?,?,?,?)";
                    int i = RecordtUtil.insertDataForBanjian(mysqlurl, mysqlname, mysqlpwd, inertsql, arg, argtype);

                }

                if(mapList.size()>0){
                    for (Map<String, Object> map:
                            mapList) {
                        String rpdeptname = (String)map.get("rpdeptname");
                        String resname = (String)map.get("resname");
                        String resdeptname = (String)map.get("resdeptname");
                        BigDecimal counts = (BigDecimal)map.get("counts");
                        date = date.replaceAll("-","");
                        String latdaysql = "select toalcont from zmd_ex t where t.reqdistrict = ? and t.`year` = ? and t.resname = ?";
                        List<Map<String, Object>> lastdaycount = RecordtUtil.queryListForAll("com.mysql.jdbc.Driver", mysqlurl, mysqlname, mysqlpwd, latdaysql, new Object[]{ rpdeptname,date,resname}, new int[]{12, 12,12});
                        BigDecimal tt = BigDecimal.ZERO;
                        if(lastdaycount!=null&&lastdaycount.size()>0){
                            int toalcont = (int)lastdaycount.get(0).get("toalcont");
                            BigDecimal toalcont1 = new BigDecimal(toalcont);
                            tt = counts.subtract(toalcont1);

                        }

                        Object[] arg = new Object[]{date1,rpdeptname,tt,counts,"服务接口",resname,resdeptname};
                        int[] argtype = new int[]{12,12, Types.INTEGER,Types.INTEGER,12,12,12};
                        String inertsql = "insert into zmd_ex (year,reqdistrict,cont,toalcont,typename,resname,prodistrict) values(?,?,?,?,?,?,?)";
                        int i = RecordtUtil.insertDataForBanjian(mysqlurl, mysqlname, mysqlpwd, inertsql, arg, argtype);
                    }
                }

                return null;
            }catch (Exception e){
                return null;
            }
    }

    @RequestMapping(value = "/LICENSEDataRecord",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public  List<Map<String,Object>> LICENSEDataRecord(@RequestParam(value = "date",required = false)String dates){
        try{
            List<Map<String,Object>> list = new ArrayList<>();
            logger.info("LICENSEDataRecord");
            List<Map<String,Object>> list1 = RecordtUtil.queryList("jdbc:oracle:thin:interfaceDB/interfaceDB@59.207.219.23:1521:orcl", "interfaceDB", "interfaceDB", sql3);
            if(list1!=null||list1.size()>0){
                for (Map<String,Object> mm:
                        list1) {
                    Map<String, Object> query = RecordtUtil.queryforbanjian((String) mm.get("url"), (String) mm.get("name"), (String) mm.get("pwd"), (String) mm.get("sql"),dates);
                    if(query!=null||query.size()>0){
                        Map<String,Object> map1 = new HashMap<>();
                        Object num = query.get("total");
                        Object dailynum = query.get("dailynum");
                        map1.put("total",num);
                        map1.put("dailynum",dailynum);
                        map1.put("dname",(String) mm.get("dname"));
                        map1.put("date", query.get("date"));
                        String date = dates.replaceAll("-","");


                        //由于很多表中没有创建时间这个值  所以改成现查询上一天总数  然后现在总数减去昨天总数即可
                        String date1 = operDate(dates, -1);
                        date1 = date1.replaceAll("-","");
                        Map<String, Object> dname = RecordtUtil.queryOneForAll("com.mysql.jdbc.Driver", mysqlurl, mysqlname, mysqlpwd, sql10, new Object[]{(String) mm.get("dname"),date1}, new int[]{12, 12});
                        BigDecimal num1 = (BigDecimal)num;
                        BigDecimal dailynum1 = null;
                        Integer num2 = null;
                        if(dname!=null&&dname.size()>0){
                            num2 = (Integer)dname.get("toalrca");
                            BigDecimal nn =  BigDecimal.valueOf(num2);
                            dailynum1= num1.subtract(nn);
                            dailynum = dailynum1;
                        }
                        Object[] arg = new Object[]{date,(String) mm.get("dname"),dailynum,num};
                        int[] argtype = new int[]{12,12, Types.INTEGER,Types.INTEGER};
                        int i = RecordtUtil.insertDataForBanjian(mysqlurl, mysqlname, mysqlpwd, sql4, arg, argtype);
                        logger.info("插入证照每日更新数量==="+i);
                        list.add(map1);
                    }
                }
            }
            return list;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }


    }


    /**
     * 手动
     * @param dates
     * @return
     */
    @RequestMapping(value = "/LICENSEDataRecord2",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public  List<Map<String,Object>> LICENSEDataRecord2(@RequestParam(value = "date",required = false)String dates){
        try{
            List<Map<String,Object>> list = new ArrayList<>();
            logger.info("LICENSEDataRecord2");
            List<Map<String,Object>> list1 = RecordtUtil.queryListForAll("jdbc:oracle:thin:gonganju/gonganju@59.207.219.137:1521:orcl", "gonganju", "gonganju", sql12,new Object[]{dates+" 00:00:00",dates+" 23:59:59"},new int[]{12,12});
            List<Map<String,Object>> list2 = RecordtUtil.queryList("jdbc:oracle:thin:gonganju/gonganju@59.207.219.137:1521:orcl", "gonganju", "gonganju", sql13);
            if(list1!=null||list1.size()>0){
                for (Map<String,Object> mm:list1) {
                    Object dailynum = null;
                    Object num = null;
                    Object dname = null;
                    String dname1 = (String)mm.get("dname");
                    Map<String,Object> map1 = new HashMap<>();
                    for (Map<String,Object> mm2:list2 ) {
                        String dname2 = (String)mm2.get("dname");

                        if((dname1==null||dname1.equals(""))&&(dname2==null||dname2.equals(""))){
                            dailynum = mm.get("dailynum");
                            num = mm2.get("nums");
                            dname = "录入单位名称为空";
                            map1.put("total",mm2.get("nums"));
                            map1.put("dailynum",mm.get("dailynum"));
                            map1.put("dname", "录入单位名称为空");
                            map1.put("date", dates);

                        }
                        if((dname1!=null&&!dname1.equals(""))&&(dname2!=null&&!dname2.equals(""))){
                            if(dname1.equals(dname2)){
                                dailynum = mm.get("dailynum");
                                num = mm2.get("nums");
                                dname = mm2.get("dname");
                                map1.put("total",mm2.get("nums"));
                                map1.put("dailynum",mm.get("dailynum"));
                                map1.put("dname", mm.get("dname"));
                                map1.put("date", dates);
                            }
                        }
                    }


                   String date = dates.replaceAll("-","");
                    Object[] arg = new Object[]{date,dname,dailynum,num};
                    int[] argtype = new int[]{12,12, Types.INTEGER,Types.INTEGER};
                    int i = RecordtUtil.insertDataForBanjian(mysqlurl, mysqlname, mysqlpwd, sql4, arg, argtype);
                    logger.info("插入证照每日更新数量===");
                    list.add(map1);


                        //由于很多表中没有创建时间这个值  所以改成现查询上一天总数  然后现在总数减去昨天总数即可
                       /* String date1 = operDate(dates, -1);
                        date1 = date1.replaceAll("-","");
                        Map<String, Object> dname = RecordtUtil.queryOneForAll("com.mysql.jdbc.Driver", mysqlurl, mysqlname, mysqlpwd, sql10, new Object[]{(String) mm.get("dname"),date1}, new int[]{12, 12});
                        BigDecimal num1 = (BigDecimal)num;
                        BigDecimal dailynum1 = null;
                        Integer num2 = null;
                        if(dname!=null&&dname.size()>0){
                            num2 = (Integer)dname.get("toalrca");
                            BigDecimal nn =  BigDecimal.valueOf(num2);
                            dailynum1= num1.subtract(nn);
                            dailynum = dailynum1;
                        }*/

                }
            }
            return list;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }


    }


    @RequestMapping(value = "/interFaceDataRecord",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public  List<Map<String,Object>> interFaceDataRecord(@RequestParam(value = "date",required = false)String dates){
        try{
            List<Map<String,Object>> list = new ArrayList<>();
            logger.info("interFaceDataRecord");
           // List<Map<String,Object>> list1 = RecordtUtil.queryListForInterFace("jdbc:oracle:thin:interfaceDB/interfaceDB@59.207.219.23:1521:orcl", "interfaceDB", "interfaceDB", sql5,dates);
            //String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            Object[] args = new Object[]{dates+" 00:00:00",dates+" 23:59:59"};
            int[] argstype = new int[]{12,12};
            String date1 = dates.replaceAll("-","");
           // List<Map<String, Object>> maps = RecordtUtil.queryListForAll("jdbc:oracle:thin:interfaceDB/interfaceDB@59.207.219.23:1521:orcl", "interfaceDB", "interfaceDB", sql7, args, argstype);


            //查询平台中的总数  TODO
            List<Map<String, Object>> total2 = RecordtUtil.queryList("jdbc:oracle:thin:interfaceDB/interfaceDB@59.207.219.23:1521:orcl", "interfaceDB", "interfaceDB", sql15);
            List<Map<String, Object>> total3 = RecordtUtil.queryList("jdbc:oracle:thin:esplatform/esplatform@59.207.219.135:1521:orcl", "esplatform", "esplatform", sql16);
            if(total2!=null&&total2.size()>0) {
                for (Map<String, Object> tt1 : total2) {
                    String resname1 = (String) tt1.get("resname");
                    BigDecimal nums =(BigDecimal) tt1.get("nums");
                    if (total3 != null && total3.size() > 0) {
                        Iterator<Map<String, Object>> iterator = total3.iterator();
                        while (iterator.hasNext()) {
                            Map<String, Object> tt2 = iterator.next();
                            String resname2 = (String) tt2.get("resname");
                            if (resname2.equals(resname1)) {
                                BigDecimal nums2 =(BigDecimal) tt2.get("nums");
                                nums = nums.add(nums2);
                                tt1.put("nums",nums);
                                iterator.remove();
                            }
                        }
                    }
                }
            }

            if (total3 != null && total3.size() > 0) {
               total2.addAll(total3);
            }
            //23库中所有的总数
            List<Map<String, Object>> total1 = RecordtUtil.queryList("jdbc:oracle:thin:interfaceDB/interfaceDB@59.207.219.23:1521:orcl", "interfaceDB", "interfaceDB", sql14);
            //对比两个总数中相同的名称即为统一资源总数  相加
            List<Map<String,Object>> list2 = new ArrayList<>();

            if(total2!=null&&total2.size()>0){
                for (Map<String,Object> tt1:total2) {
                    Map<String,Object> re =  new HashMap<>();
                    String resname1 =(String) tt1.get("resname");
                    String dname =(String) tt1.get("dname");
                    BigDecimal nums =(BigDecimal) tt1.get("nums");
                    if(total1!=null&&total1.size()>0){
                        Iterator<Map<String, Object>> iterator = total1.iterator();
                        while(iterator.hasNext()){
                            Map<String, Object> tt2 = iterator.next();
                            String resname2 =(String) tt2.get("resname");
                            if(resname2.equals(resname1)){
                                BigDecimal nums2 =(BigDecimal) tt2.get("nums");
                                nums = nums.add(nums2);
                                iterator.remove();
                            }
                        }
                        re.put("resname",resname1);
                        re.put("nums",nums);
                        re.put("dname",dname);
                        Object[] args1 = new Object[]{resname1,dates+" 00:00:00",dates+" 23:59:59"};
                        int[] argstype1 = new int[]{12,12,12};
                        List<Map<String, Object>> map = RecordtUtil.queryListForAll("jdbc:oracle:thin:esplatform/esplatform@59.207.219.135:1521:orcl", "esplatform", "esplatform", sql17, args1, argstype1);
                        Map<String, Object> map2 = RecordtUtil.queryOneForAll("jdbc:oracle:thin:interfaceDB/interfaceDB@59.207.219.23:1521:orcl", "interfaceDB", "interfaceDB", sql18, args1, argstype1);
                        BigDecimal dailynum3 = BigDecimal.ZERO;
                        if(map!=null&&map.size()>0){
                            BigDecimal dailynum =(BigDecimal)map.get(0).get("dailynum");
                            if(dailynum!=null) {
                                dailynum3 = dailynum3.add(dailynum);
                            }
                        }
                        if(map2!=null&&map2.size()>0){
                            BigDecimal dailynum2 =(BigDecimal)map2.get("dailynum");
                            if(dailynum2!=null){
                                dailynum3 = dailynum3.add(dailynum2);
                            }
                        }


                        re.put("dailynum",dailynum3);
                        list2.add(re);
                    }
                }
            }




            if(total1!=null&&total1.size()>0){
                Iterator<Map<String, Object>> iterator = total1.iterator();
                while(iterator.hasNext()){
                    Map<String,Object> re =  new HashMap<>();
                    Map<String, Object> tt2 = iterator.next();
                    String resname2 =(String) tt2.get("resname");
                    BigDecimal nums2 =(BigDecimal) tt2.get("nums");
                    re.put("resname",resname2);
                    re.put("nums",nums2);
                    List<Map<String, Object>> dnames = RecordtUtil.queryListForAll("jdbc:oracle:thin:exzyml/exzyml@59.207.219.135:1521:orcl", "exzyml", "exzyml", sql11, new Object[]{resname2}, new int[]{12});
                    String dname = "";
                    if(dnames!=null&&dnames.size()>0){
                        dname = (String)dnames.get(0).get("rpdeptname");
                    }
                    re.put("dname",dname);
                    Object[] args1 = new Object[]{resname2,dates+" 00:00:00",dates+" 23:59:59"};
                    int[] argstype1 = new int[]{12,12,12};
                    Map<String, Object> map2 = RecordtUtil.queryOneForAll("jdbc:oracle:thin:interfaceDB/interfaceDB@59.207.219.23:1521:orcl", "interfaceDB", "interfaceDB", sql18, args1, argstype1);
                    BigDecimal dailynum3 = BigDecimal.ZERO;
                    if(map2!=null&&map2.size()>0){
                        BigDecimal dailynum =(BigDecimal)map2.get("dailynum");
                        if(dailynum!=null){
                            dailynum3 = dailynum3.add(dailynum);
                        }
                    }
                    re.put("dailynum",dailynum3);

                    list2.add(re);
                }
            }
            //TODO 封装好上面的list2之后就可以根据 resname 在两个库中分别查询当日数量  然后再插入map 即可


            if(list2!=null||list2.size()>0) {
                for (Map<String, Object> mm : list2) {
                    Object[] arg = new Object[]{date1,mm.get("dname"),mm.get("dailynum"),mm.get("nums"),mm.get("resname")};
                    int[] argtype = new int[]{12,12, Types.INTEGER,Types.INTEGER,12};
                    int i = RecordtUtil.insertDataForBanjian(mysqlurl, mysqlname, mysqlpwd, sql8, arg, argtype);
                    logger.info("插入接口每日更新数量===");
                }
            }



            return list2;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }


    }
    @RequestMapping(value = "/record",method = {RequestMethod.GET,RequestMethod.POST})
    public String record(){
        logger.info("record");
        return "record";
    }



    @RequestMapping(value = "/BASEDataRecord",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public  List<Map<String,Object>> BASEDataRecord(@RequestParam(value = "date",required = false)String dates){
        try{
            List<Map<String,Object>> list = new ArrayList<>();
            logger.info("BASEDataRecord");
            List<Map<String,Object>> list1 = RecordtUtil.queryList("jdbc:oracle:thin:exjck/exjck@59.207.219.42:1521:orcl", "exjck", "exjck", sql19);
            if(list1!=null||list1.size()>0){
                for (Map<String,Object> mm:list1) {
                    Map<String, Object> query = RecordtUtil.queryOneForAll("jdbc:oracle:thin:exjck/exjck@59.207.219.42:1521:orcl", "exjck", "exjck", sql20,new Object[]{mm.get("tablenames"),dates},new int[]{12,12});
                    Map<String,Object> map1 = new HashMap<>();
                    if(query!=null&&query.size()>0){
                        //从每日更新表中找到日更新插入量即可
                        Object todaydatatatal = query.get("todaydatatotal");
                        map1.put("dailynum",todaydatatatal);
                    }else{
                        map1.put("dailynum",0);
                    }

                    //从总数表中获取部门  名字  总数等信息
                    Object datatotal = mm.get("datatotal");//总数
                    map1.put("total",datatotal);
                    String tablenamedesc = (String)mm.get("tablenamesdesc");//这是表的中文释义  可以分隔开作为资源名称和部门名称
                    int i = tablenamedesc.indexOf("_");
                    String[] split = tablenamedesc.split("_");
                    map1.put("resname",tablenamedesc.substring(i+1));//资源名称
                    map1.put("district",tablenamedesc.substring(0,i));//部门名称
                    String resourcetype = (String)mm.get("resourcetype");//资源类型   01人口  02法人
                    if(resourcetype.equals("01")){
                        map1.put("resourcetype", "人口");
                    }else{
                        map1.put("resourcetype", "法人");
                    }
                    map1.put("date", dates);//加入日期
                    list.add(map1);
                }
            }



            if(list!=null||list.size()>0) {
                for (Map<String, Object> mm : list) {
                    Object[] arg = new Object[]{dates,mm.get("district"),mm.get("dailynum"),mm.get("total"),mm.get("resname"),mm.get("resourcetype")};
                    int[] argtype = new int[]{12,12, Types.INTEGER,Types.INTEGER,12,12};
                    int i = RecordtUtil.insertDataForBanjian(mysqlurl, mysqlname, mysqlpwd, sql21, arg, argtype);
                    logger.info("插入接口每日更新数量===");
                }
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }


    }

    /**
     * 大屏定时任务开始
     *
     */


    /**
     * 以部门为单位的提供数据记录  按照部门时间划分
     * @param dates
     * @return
     */
    @RequestMapping(value = "/providerDeptDataRecord",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public  Object providerDeptDataRecord(@RequestParam(value = "date",required = false)String dates){
        try{
            List<Map<String,Object>> list = new ArrayList<>();
            List<String> list3 = new ArrayList<>();

            String sql1 = "select t1.logicnod ,t.logicnodnam as deptname,t.dblinkurl as url,t.dbpsw as pwd,t.dbusernam as name from exres t1 left join logicnodinfo t on t.logicnodcode = t1.logicnod where t1.lastupdtime > to_date('2019-01-01','yyyy-MM-dd') and t.logicnodnam is not null group by t1.logicnod,t.logicnodnam,t.dblinkurl,t.dbpsw,t.dbusernam";
            logger.info("=========providerDeptDataRecord======start========");
            List<Map<String,Object>> list1 = RecordtUtil.queryList("jdbc:oracle:thin:esplatform/esplatform@59.207.219.135:1521:orcl", "esplatform", "esplatform", sql1);
            if(list1!=null||list1.size()>0){
                for (Map<String,Object> mm:list1) {
                    String deptname = (String)mm.get("deptname");
                    String url = (String)mm.get("url");
                    String pwd = (String)mm.get("pwd");
                    String name = (String)mm.get("name");
                    String sql2 = "select to_char(ctrtime,'yyyy-MM') as dates,sum(checkcount) as counts from providereg group by to_char(ctrtime,'yyyy-MM')";
                    Boolean aBoolean = RecordtUtil.checkConn(url, name, pwd);
                    if(!aBoolean){
                        list3.add(url);
                       continue;
                    }
                    List<Map<String,Object>> list2 = RecordtUtil.queryList(url, name, pwd, sql2);
                    if(list2!=null||list2.size()>0){

                        for (Map<String,Object> mm2:list2) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("deptname",deptname);
                            String date = (String)mm2.get("dates");
                            String[] date2 = date.split("-");
                            map.put("year",date2[0]);
                            map.put("day",date2[1]);
                            map.put("counts",mm2.get("counts"));
                            list.add(map);
                        }
                    }


                }
            }



            if(list!=null||list.size()>0) {
                String sqlinert = "insert into providerDeptDataRecord(deptname,year,month,total) values(?,?,?,?)";
                for (Map<String, Object> mm : list) {
                    Object[] arg = new Object[]{mm.get("deptname"),mm.get("year"),mm.get("day"),mm.get("counts")};
                    int[] argtype = new int[]{12,12,12, Types.INTEGER};
                    int i = RecordtUtil.insertDataForBanjian("jdbc:oracle:thin:interfaceDB/interfaceDB@59.207.219.23:1521:orcl","oracle.jdbc.OracleDriver", "interfaceDB", "interfaceDB", sqlinert, arg, argtype);
                    logger.info("providerDeptDataRecord====插入每日更新数量===");
                }
            }
            return list3;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }


    }

    /**
     * 以部门和资源为单位的提供数据记录  按照部门资源时间划分
     * @param dates
     * @return
     */
    @RequestMapping(value = "/providerDeptResDataRecord",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public  List<Map<String,Object>> providerDeptResDataRecord(@RequestParam(value = "date",required = false)String dates){
        try{
            List<Map<String,Object>> list = new ArrayList<>();

            String sql1 = "select t1.logicnod ,t.logicnodnam as deptname,t.dblinkurl as url,t.dbpsw as pwd,t.dbusernam as name from exres t1 left join logicnodinfo t on t.logicnodcode = t1.logicnod where t1.lastupdtime > to_date('2019-01-01','yyyy-MM-dd') and t.logicnodnam is not null group by t1.logicnod,t.logicnodnam,t.dblinkurl,t.dbpsw,t.dbusernam";
            logger.info("=========providerDeptResDataRecord======start========");
            List<Map<String,Object>> list1 = RecordtUtil.queryList("jdbc:oracle:thin:esplatform/esplatform@59.207.219.135:1521:orcl", "esplatform", "esplatform", sql1);
            if(list1!=null||list1.size()>0){
                for (Map<String,Object> mm:list1) {
                    String deptname = (String)mm.get("deptname");
                    String url = (String)mm.get("url");
                    String pwd = (String)mm.get("pwd");
                    String name = (String)mm.get("name");
                    String sql2 = "select to_char(ctrtime,'yyyy-MM') as dates,sum(checkcount) as counts,extabnam from providereg group by to_char(ctrtime,'yyyy-MM'),extabnam";
                    List<Map<String,Object>> list2 = RecordtUtil.queryList(url, name, pwd, sql2);
                    if(list2!=null||list2.size()>0){

                        for (Map<String,Object> mm2:list2) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("deptname",deptname);
                            String date = (String)mm2.get("dates");
                            String extabnam = (String)mm2.get("extabnam");
                            String resname = "";
                            String sql3 = "select t.resnam as resname from exres t where t.pubtabnam = '"+extabnam+"' ";
                            List<Map<String,Object>> list3 = RecordtUtil.queryList("jdbc:oracle:thin:esplatform/esplatform@59.207.219.135:1521:orcl", "esplatform", "esplatform", sql3);
                            if(list3!=null&&list3.size()>0){
                                Map<String, Object> stringObjectMap = list3.get(0);
                                resname = (String)stringObjectMap.get("resname");
                            }

                            String[] date2 = date.split("-");
                            map.put("year",date2[0]);
                            map.put("day",date2[1]);
                            map.put("counts",mm2.get("counts"));
                            map.put("resname",resname);
                            list.add(map);
                        }
                    }


                }
            }



           /* if(list!=null||list.size()>0) {
                for (Map<String, Object> mm : list) {
                    Object[] arg = new Object[]{dates,mm.get("district"),mm.get("dailynum"),mm.get("total"),mm.get("resname"),mm.get("resourcetype")};
                    int[] argtype = new int[]{12,12, Types.INTEGER,Types.INTEGER,12,12};
                    int i = RecordtUtil.insertDataForBanjian(mysqlurl, mysqlname, mysqlpwd, sql21, arg, argtype);
                    logger.info("插入接口每日更新数量===");
                }
            }*/
            return list;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }


    }


    /**
     * 以部门为单位的交换数据记录  按照部门时间划分
     * @param dates
     * @return
     */
    @RequestMapping(value = "/reqDeptDataRecord",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public  List<Map<String,Object>> reqDeptDataRecord(@RequestParam(value = "date",required = false)String dates){
        try{
            List<Map<String,Object>> list = new ArrayList<>();

            String sql1 = "select t1.logicnodnam as deptname,t1.dblinkurl as url,t1.dbpsw as pwd,t1.dbusernam as name  from extask t left join logicnodinfo t1 on t1.logicnodcode = t.lognodcode where  t1.logicnodnam is not null  group by t.lognodcode,t1.logicnodnam,t1.dblinkurl,t1.dbpsw,t1.dbusernam";
            logger.info("=========reqDeptDataRecord======start========");
            List<Map<String,Object>> list1 = RecordtUtil.queryList("jdbc:oracle:thin:esplatform/esplatform@59.207.219.135:1521:orcl", "esplatform", "esplatform", sql1);
            if(list1!=null||list1.size()>0){
                for (Map<String,Object> mm:list1) {
                    String deptname = (String)mm.get("deptname");
                    String url = (String)mm.get("url");
                    String pwd = (String)mm.get("pwd");
                    String name = (String)mm.get("name");
                    String sql2 = "select to_char(ctrtime,'yyyy-MM') as dates,orgcode as code from acqurec group by to_char(ctrtime,'yyyy-MM'),orgcode";
                    List<Map<String,Object>> list2 = RecordtUtil.queryList(url, name, pwd, sql2);

                    if(list2!=null||list2.size()>0){

                        for (Map<String,Object> mm2:list2) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("deptname",deptname);


                            String date = (String)mm2.get("dates");
                            String[] split = date.split("-");
                            map.put("year",split[0]);
                            map.put("day",split[1]);
                            String code = (String)mm2.get("code");
                            map.put("resDept",code);
                            String sql3 = "select distinct extabnam from acqurec where to_char(ctrtime,'yyyy-MM') = '"+date+"' and orgcode = '"+code+"'";
                            List<Map<String,Object>> list3 = RecordtUtil.queryList(url, name, pwd, sql3);
                            BigDecimal total = BigDecimal.ZERO;
                           //map.put("extabnam","");  //TODO
                            if(list3!=null&&list3.size()>0){
                                for (Map<String,Object> mm3:list3) {
                                    try{
                                        String extabnam = (String)mm3.get("extabnam");
                                        String sql4 = "select count(1) as nums from "+extabnam+" where batchno in (select batchno from acqurec where to_char(ctrtime,'yyyy-MM') = '"+date+"' and extabnam = '"+extabnam+"')";
                                        Map<String,Object> map2 = RecordtUtil.queryOneForAll(url, name, pwd, sql4,new Object[]{},new int[]{});
                                        BigDecimal nums = (BigDecimal)map2.get("nums");
                                        total = total.add(nums);
                                    }catch(Exception e){
                                        logger.error(e.getMessage());
                                        continue;
                                    }
                                }
                            }

                            map.put("total",total);
                            list.add(map);
                        }
                    }


                }
            }



           /* if(list!=null||list.size()>0) {
                for (Map<String, Object> mm : list) {
                    Object[] arg = new Object[]{dates,mm.get("district"),mm.get("dailynum"),mm.get("total"),mm.get("resname"),mm.get("resourcetype")};
                    int[] argtype = new int[]{12,12, Types.INTEGER,Types.INTEGER,12,12};
                    int i = RecordtUtil.insertDataForBanjian(mysqlurl, mysqlname, mysqlpwd, sql21, arg, argtype);
                    logger.info("插入接口每日更新数量===");
                }
            }*/
            return list;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }


    }


    /**
     *
     * 大屏定时任务结束
     */


    public static void main(String[] args)throws  Exception{
        String dates = "2020-03-02";
        String date1 = dates.replaceAll("-","");
        System.out.println(date1);
        System.out.println(dates);
    }



}
