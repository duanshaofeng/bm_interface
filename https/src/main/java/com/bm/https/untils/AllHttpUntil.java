package com.bm.https.untils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bm.https.controller.ggzyjyzx.GgzyjyzxTestInterfaceController;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import org.dom4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.rpc.ParameterMode;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.*;

public class AllHttpUntil {


    private static Logger logger = LoggerFactory.getLogger(AllHttpUntil.class);


    public static String getLink(Map<String, Object> map, String url) {
        String link = "";
        if (map != null && map.size() > 0) {

            Set<String> keySet = map.keySet();
            for (String key :
                    keySet) {
                if (StringUtils.isEmpty(map.get(key))) {
                    continue;
                }
                link += key + "=" + map.get(key) + "&";
            }
            if (link != "") {
                link = "?" + link.substring(0, link.length() - 1);
            }

        }


        return url + link;
    }




    /**
     * 发送POST请求
     *
     * @param url
     * @param json
     * @return JSON或者字符串
     * @throws Exception
     */
    public static Object sendPostByJson(String url, String json) throws Exception {
        JSONObject jsonObject = null;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            /**
             *  创建一个httpclient对象
             */
            client = HttpClients.createDefault();
            /**
             * 创建一个post对象
             */
            HttpPost post = new HttpPost(url);
            /**
             * 包装成一个Entity对象
             */
            StringEntity entity = new StringEntity(json, "UTF-8");
            /**
             * 设置请求的内容
             */
            post.setEntity(entity);
            /**
             * 设置请求的报文头部的编码
             */
            post.setHeader(new BasicHeader("Content-Type", "application/json; charset=utf-8"));

            /**
             * 设置请求的报文头部的编码
             */
            // post.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
            /**
             * 执行post请求
             */
            response = client.execute(post);
            /**
             * 获取响应码
             */
            int statusCode = response.getStatusLine().getStatusCode();

            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            return result;


        } catch (Exception e) {
            //LOGGER.error("HttpClientService-line: {}, Exception：{}", 149, e);
            e.printStackTrace();
        } finally {
            response.close();
            client.close();
        }
        return null;
    }









    /**
     * 发送GET请求
     * @param url   请求url
     * @param
     * @return JSON或者字符串
     * @throws Exception
     */
    public static Object sendGet(String url,Map<String, Object> map) throws Exception{
        JSONObject jsonObject = null;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try{
            /**
             * 创建HttpClient对象
             */
            client = HttpClients.createDefault();
            /**
             * 创建HttpGet
             */
            url = getLink(map,url);
            System.out.println(url.toString());
            HttpGet httpGet = new HttpGet(url);

            /**
             * 请求服务
             */
            response = client.execute(httpGet);
            /**
             * 获取响应吗
             */
            int statusCode = response.getStatusLine().getStatusCode();

            HttpEntity entity = response.getEntity();
            /**
             * 通过EntityUitls获取返回内容
             */

            String result = EntityUtils.toString(entity,"UTF-8");

            return result;

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            response.close();
            client.close();
        }
        return null;
    }


    /**
     * 发送GET请求
     * @param url   请求url
     * @param
     * @return JSON或者字符串
     * @throws Exception
     */
    public static Object sendGet(String url) throws Exception{
        JSONObject jsonObject = null;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try{
            /**
             * 创建HttpClient对象
             */
            client = HttpClients.createDefault();
            /**
             * 创建HttpGet
             */

            System.out.println(url.toString());
            HttpGet httpGet = new HttpGet(url);

            /**
             * 请求服务
             */
            response = client.execute(httpGet);
            /**
             * 获取响应吗
             */
            int statusCode = response.getStatusLine().getStatusCode();

            HttpEntity entity = response.getEntity();
            /**
             * 通过EntityUitls获取返回内容
             */

            String result = EntityUtils.toString(entity,"UTF-8");

            return result;

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            response.close();
            client.close();
        }
        return null;
    }


    /**
     * 发送POST请求
     * @param url
     * @param params
     * @return JSON或者字符串
     * @throws Exception
     */
    public static Object sendPost2(String url, Map<String,String> params) throws Exception{
        JSONObject jsonObject = null;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try{
            /**
             *  创建一个httpclient对象
             */
            client = HttpClients.createDefault();
            /**
             * 创建一个post对象
             */
            HttpPost post = new HttpPost(url);
            /**
             * 包装成一个Entity对象
             */
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            StringBuffer paramsBuf = new StringBuffer() ;
            for(Map.Entry<String, String> e : params.entrySet()) {
                nvps.add(new BasicNameValuePair(e.getKey(), e.getValue()));
                paramsBuf.append("&").append(e.getKey()).append("=").append(e.getValue()) ;
            }
            post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            /**
             * 设置请求的报文头部的编码
             */
            post.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
            /**
             * 设置请求的报文头部的编码
             */
            // post.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
            /**
             * 执行post请求
             */
            response = client.execute(post);
            /**
             * 获取响应码
             */
            int statusCode = response.getStatusLine().getStatusCode();
            /**
             * 通过EntityUitls获取返回内容
             */
            String result = EntityUtils.toString(response.getEntity(),"UTF-8");
            /**
             * 转换成json,根据合法性返回json或者字符串
             */

            return result;


        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if(response!=null){
                response.close();
            }
            if(client!=null){
                client.close();
            }

        }

    }


    /**
     * 发送POST请求
     * @param url
     * @param params
     * @return JSON或者字符串
     * @throws Exception
     */
    public static Object sendPostDown(String url, Map<String,String> params) throws Exception{
        JSONObject jsonObject = null;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try{
            /**
             *  创建一个httpclient对象
             */
            client = HttpClients.createDefault();
            /**
             * 创建一个post对象
             */
            HttpPost post = new HttpPost(url);
            /**
             * 包装成一个Entity对象
             */
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            StringBuffer paramsBuf = new StringBuffer() ;
            for(Map.Entry<String, String> e : params.entrySet()) {
                nvps.add(new BasicNameValuePair(e.getKey(), e.getValue()));
                paramsBuf.append("&").append(e.getKey()).append("=").append(e.getValue()) ;
            }
            post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            /**
             * 设置请求的报文头部的编码
             */
            post.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
            /**
             * 设置请求的报文头部的编码
             */

            /**
             * 执行post请求
             */
            response = client.execute(post);
            /**
             * 获取响应码
             */
            int statusCode = response.getStatusLine().getStatusCode();
            String base64 = "";
            if(statusCode==200){
                HttpEntity entity = response.getEntity();
                byte[] bytes = EntityUtils.toByteArray(entity);
                InputStream content = entity.getContent();
                if(content!=null){
                    base64 = ioToBase64(bytes);
                    base64ToIo(base64);
                }
            }
            return base64;


        }catch (Exception e){
            return null;
        }finally {
            if(response!=null){
                response.close();
            }
            if(client!=null){
                client.close();
            }

        }

    }



    public static void base64ToIo(String strBase64) throws IOException {
        String string = strBase64;
        String fileName = "d://gril2.pdf"; //生成的新文件
        try {
            // 解码，然后将字节转换为文件
            byte[] bytes = new BASE64Decoder().decodeBuffer(string);   //将字符串转换为byte数组
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            byte[] buffer = new byte[1024];
            FileOutputStream out = new FileOutputStream(fileName);
            int bytesum = 0;
            int byteread = 0;
            while ((byteread = in.read(buffer)) != -1) {
                bytesum += byteread;
                out.write(buffer, 0, byteread); //文件写操作
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    public static void base64ToIo(String strBase64,String fileName) throws IOException {
        String string = strBase64;
        FileOutputStream out = null;
        try {
            // 解码，然后将字节转换为文件
            byte[] bytes = new BASE64Decoder().decodeBuffer(string);   //将字符串转换为byte数组
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            byte[] buffer = new byte[1024];
            out = new FileOutputStream(fileName);
            int bytesum = 0;
            int byteread = 0;
            while ((byteread = in.read(buffer)) != -1) {
                bytesum += byteread;
                out.write(buffer, 0, byteread); //文件写操作
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }finally {
            out.flush();
        }
    }


    public static String ioToBase64( byte[] bytes) throws IOException {

        String strBase64 = null;
        try {

            // in.available()返回文件的字节长度
            // 将文件中的内容读入到数组中
            strBase64 = new BASE64Encoder().encode(bytes);      //将字节流数组转换为字符串
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
        return strBase64;
    }

    /**
     * 文件转流
     * @param tradeFile
     * @return
     */
    public static byte[] File2byte(File tradeFile){
        byte[] buffer = null;
        try
        {
            FileInputStream fis = new FileInputStream(tradeFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return buffer;
    }





    /**
     * 调用上传文件接口上传文件
     * @param url
     * @param path
     * @return
     */
    public static String sendPostUplodFile(String url,File path) {
        DataOutputStream out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            //打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
            //发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);

            String BOUNDARY = "----WebKitFormBoundary07I8UIuBx6LN2KyY";
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36");
            conn.setRequestProperty("Charsert", "UTF-8");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            conn.connect();

            out = new DataOutputStream(conn.getOutputStream());
            byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();

            //添加参数file
           // File file = new File(path);
            StringBuffer sb = new StringBuffer();
            sb.append("--");
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + path.getName() + "\"");
            sb.append("\r\n");
            sb.append("Content-Type: application/octet-stream");
            sb.append("\r\n");
            sb.append("\r\n");
            out.write(sb.toString().getBytes());

            DataInputStream in1 = new DataInputStream(new FileInputStream(path));
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in1.read(bufferOut)) != -1) {
                out.write(bufferOut,0,bytes);
            }
            out.write("\r\n".getBytes());
            in1.close();
            out.write(end_data);
            //flush输出流的缓冲
            out.flush();
            //定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * webservice处理接口
     * @param url
     * @param
     * @return
     */
    public static Object webserviceClient(String url,String method,String ... key){
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(url);

        Object[] objects = new Object[0];
        try {
            objects = client.invoke(method, key);
            return objects[0];

        } catch (java.lang.Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
    public static Object webserviceClient2(String url,String method,String  key,String  content,String start,String size) {

        Service service = new Service();
        System.out.println("=============1==================service " + service);
        try {
            javax.xml.rpc.Call call = service.createCall();
            System.out.println("=============2==================call " + call);
            //设置url
            call.setTargetEndpointAddress(url);
            //注册服务端命名空间
            call.setOperationName(new QName("http://lhjc.service.credit.hnrj.com/", method));//WSDL里面描述的接口名称
            //设置返回值类型，这里返回的list
            call.setReturnType(new QName("http://lhjc.service.credit.hnrj.com/", method), String.class);
            //设置输入参数，data1、data2需要根据wsdl中的名称来填，如果输入参数为map或list 中间参数为XMLType.XSD_HEXBINARY
            call.addParameter("key", XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter("content", XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter("start", XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter("size", XMLType.XSD_STRING, ParameterMode.IN);
            String reqXml = "";
            Object[] param = new Object[]{key, content, start,size};
            String returnxml = (String) call.invoke(param);

            System.out.println(returnxml);
            return returnxml;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Object webserviceClient3(String url,String method,Map<String,Object> map) {

        Service service = new Service();
        System.out.println("=============1==================service " + service);
        try {
            javax.xml.rpc.Call call = service.createCall();
            System.out.println("=============2==================call " + call);
            //设置url
            call.setTargetEndpointAddress(url);
            //注册服务端命名空间
            call.setOperationName(new QName("http://lhjc.service.credit.hnrj.com/", method));//WSDL里面描述的接口名称
            //设置返回值类型，这里返回的list
            call.setReturnType(new QName("http://lhjc.service.credit.hnrj.com/", method), String.class);
            //设置输入参数，data1、data2需要根据wsdl中的名称来填，如果输入参数为map或list 中间参数为XMLType.XSD_HEXBINARY
            Object[] param = new Object[map.size()];
           if(map.size()>0){
               Set<String> keys = map.keySet();
               int i = 0;
               for (String keya :
                    keys) {
                   call.addParameter(keya, XMLType.XSD_STRING, ParameterMode.IN);
                   param[i] = map.get(keya);
                   i++;
               }

           }
            String returnxml = (String) call.invoke(param);
            System.out.println(returnxml);
            return returnxml;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * webservice通用版
     * @param url
     * @param method
     * @param map
     * @return
     */
    public static Object webserviceClient3(String url,String namespace,String method,Map<String,Object> map) {

        Service service = new Service();
        System.out.println("=============1==================service " + service);
        try {
            javax.xml.rpc.Call call = service.createCall();
            System.out.println("=============2==================call " + call);
            //设置url
            call.setTargetEndpointAddress(url);
            //注册服务端命名空间
            call.setOperationName(new QName(namespace, method));//WSDL里面描述的接口名称
            //设置返回值类型，这里返回的list
            call.setReturnType(new QName(namespace, method), String.class);
            //设置输入参数，data1、data2需要根据wsdl中的名称来填，如果输入参数为map或list 中间参数为XMLType.XSD_HEXBINARY
            Object[] param = new Object[map.size()];
            if(map.size()>0){
                Set<String> keys = map.keySet();
                int i = 0;
                for (String keya :
                        keys) {
                    call.addParameter(keya, XMLType.XSD_STRING, ParameterMode.IN);
                    param[i] = map.get(keya);
                    i++;
                }

            }
            String returnxml = (String) call.invoke(param);
            System.out.println(returnxml);
            return returnxml;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    /**
     * 公共资源交易中心封装xml
     * @param StartDate
     * @param EndDate
     * @param CategoryName
     * @param Token
     * @return
     */
    public static String getSoapXml(String StartDate,String EndDate,String CategoryName,String Token,String flag){
        try{
            StringBuffer xml = new StringBuffer();
            if(!isEmpty(flag)){
                if(flag.equals("qs")){
                    xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">");
                }else{
                    xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:epo=\"http://epoint.com.cn\">");
                }

            }else{
                xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">");

            }
            if(flag.equals("qs")){
                xml.append("<soapenv:Header/>");
                xml.append("<soapenv:Body>");
                xml.append("<tem:GongGaoTuisSong>");
                xml.append("<tem:StartDate>"+StartDate+"</tem:StartDate>");
                xml.append("<tem:EndDate>"+EndDate+"</tem:EndDate>");
                xml.append("<tem:CategoryName>"+CategoryName+"</tem:CategoryName>");
                xml.append("<tem:Token>"+Token+"</tem:Token>");
                xml.append("</tem:GongGaoTuisSong>");
                xml.append("</soapenv:Body>");
                xml.append("</soapenv:Envelope>");
            }else{
                xml.append("<soapenv:Header/>");
                xml.append("<soapenv:Body>");
                xml.append("<"+flag+"GetGongGaoInfo>");
                xml.append("<"+flag+"StartDate>"+StartDate+"</"+flag+"StartDate>");
                xml.append("<"+flag+"EndDate>"+EndDate+"</"+flag+"EndDate>");
                xml.append("<"+flag+"CategoryName>"+CategoryName+"</"+flag+"CategoryName>");
                xml.append("<"+flag+"Token>"+Token+"</"+flag+"Token>");
                xml.append("</"+flag+"GetGongGaoInfo>");
                xml.append("</soapenv:Body>");
                xml.append("</soapenv:Envelope>");
            }

            return xml.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 公共资源交易中心对接方法
     * @param wsdl
     * @param StartDate
     * @param EndDate
     * @param CategoryName
     * @param Token
     * @return
     */
    public static Object getWebservice(String wsdl,String StartDate,String EndDate,String CategoryName,String Token,String flag) {
        HttpClient httpclent = null;
        HttpPost httppost = null;
        String xml = "";
        try{

            httpclent = new DefaultHttpClient();
            httppost = new HttpPost(wsdl);
            String soapRequestData = getSoapXml(StartDate,EndDate,CategoryName,Token,flag);
            System.out.println(soapRequestData);
            HttpEntity re = new StringEntity(soapRequestData, "UTF-8");
            httppost.setHeader("Content-Type", "text/xml; charset=utf-8");
            if(!isEmpty(flag)){
                if(flag.equals("qs")){
                    httppost.setHeader("SOAPAction", "http://tempuri.org/GongGaoTuisSong");
                }else{
                    httppost.setHeader("SOAPAction", "http://epoint.com.cn/GetGongGaoInfo");
                }

            }else{
                httppost.setHeader("SOAPAction", "/GetGongGaoInfo");
            }

            httppost.setEntity(re);
            HttpResponse response = httpclent.execute(httppost); // 调用接口
            xml = EntityUtils.toString(response.getEntity(),"UTF-8");
            if(!isEmpty(xml)){
                xml =  StringEscapeUtils.unescapeXml(xml);
                int len =  xml.lastIndexOf("<result>");
                int len2 = xml.lastIndexOf("</result>");
                xml = xml.substring(len,len2+9 );
            }

            /*if(!isEmpty(xml)){

                String str =  StringEscapeUtils.unescapeXml(xml);
                System.out.println(str);

                JSONObject jsonObject = xml2Json(s1);
                return jsonObject;
            }*/
            return xml;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(httppost!=null){
                httppost.releaseConnection();
            }
        }
        return null;


    }

    public static Map<String, String> xmlToMap(String strXML) throws Exception {
        try {
            Map<String, String> data = new HashMap<String, String>();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            documentBuilderFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            documentBuilderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            documentBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            documentBuilderFactory.setXIncludeAware(false);
            documentBuilderFactory.setExpandEntityReferences(false);
            DocumentBuilder documentBuilder= documentBuilderFactory.newDocumentBuilder();
//DocumentBuilder documentBuilder = WXPayXmlUtil.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
            org.w3c.dom.Document doc = documentBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int idx = 0; idx < nodeList.getLength(); ++idx) {
                Node node = nodeList.item(idx);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                    data.put(element.getNodeName(), element.getTextContent());
                }
            }
            try {
                stream.close();
            } catch (Exception ex) {
// do nothing
            }
            return data;
        } catch (Exception ex) {
            throw ex;
        }
    }


    /**
     * xml转json
     * @param xmlStr
     * @return
     * @throws DocumentException
     */
    public static JSONObject xml2Json(String xmlStr) throws DocumentException {
        Document doc= DocumentHelper.parseText(xmlStr);
        JSONObject json=new JSONObject();
        dom4j2Json(doc.getRootElement(), json);
        return json;
    }

    /**
     * xml转json
     * @param element
     * @param json
     */
    public static void dom4j2Json(Element element, JSONObject json){
        //如果是属性
        for(Object o:element.attributes()){
            Attribute attr=(Attribute)o;
            if(!isEmpty(attr.getValue())){
                json.put("@"+attr.getName(), attr.getValue());
            }
        }
        List<Element> chdEl=element.elements();
        if(chdEl.isEmpty()&&!isEmpty(element.getText())){//如果没有子元素,只有一个值
            json.put(element.getName(), element.getText());
        }

        for(Element e:chdEl){//有子元素
            if(!e.elements().isEmpty()){//子元素也有子元素
                JSONObject chdjson=new JSONObject();
                dom4j2Json(e,chdjson);
                Object o=json.get(e.getName());
                if(o!=null){
                    JSONArray jsona=null;
                    if(o instanceof JSONObject){//如果此元素已存在,则转为jsonArray
                        JSONObject jsono=(JSONObject)o;
                        json.remove(e.getName());
                        jsona=new JSONArray();
                        jsona.add(jsono);
                        jsona.add(chdjson);
                    }
                    if(o instanceof JSONArray){
                        jsona=(JSONArray)o;
                        jsona.add(chdjson);
                    }
                    json.put(e.getName(), jsona);
                }else{
                    if(!chdjson.isEmpty()){
                        json.put(e.getName(), chdjson);
                    }
                }


            }else{//子元素没有子元素
                for(Object o:element.attributes()){
                    Attribute attr=(Attribute)o;
                    if(!isEmpty(attr.getValue())){
                        json.put("@"+attr.getName(), attr.getValue());
                    }
                }
                if(!e.getText().isEmpty()){
                    json.put(e.getName(), e.getText());
                }
            }
        }
    }

    public static boolean isEmpty(String str) {

        if (str == null || str.trim().isEmpty() || "null".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 准备编写统一插入数据库方法
     * 统一方法的条件就是 表结构必须与返回的字段名称相一致才可以统一插入
     * @param strs 传入返回值比如为数据根元素
     * @param tablename 传入表名 方便统一插入
     */
    public static void insertDB(String strs,String tablename) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conn = DriverManager.getConnection("jdbc:oracle:thin:root/123456@192.168.1.86:1521:orcl", "root", "123456");
                if(strs!=null && !strs.equals("")){
                    //统一转换为list
                    List<Map<String, Object>> list = JSONArray.parseObject(strs, List.class);
                    if(list.size()>0){
                        for (Map<String, Object> maps : list) {
                            String sql = "INSERT INTO  "+tablename+"(";
                            String val = " values(";
                            Set<String> set = maps.keySet();
                            int i = 0;
                            for (String str :
                                    set) {
                                i++;
                                if (i == set.size()) {
                                    sql += str + ") ";
                                    val += "'" + maps.get(str) + "')";
                                } else {
                                    sql += str + ",";
                                    val += "'" + maps.get(str) + "',";
                                }
                            }
                            preparedStatement = conn.prepareStatement((sql + val));
                            int i1 = preparedStatement.executeUpdate();
                            if(preparedStatement!=null){
                                preparedStatement.close();
                            }
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try{
                    if(conn!=null)conn.close();
                    if(preparedStatement!=null) preparedStatement.close();
                }catch (Exception e){}
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    /**
     * 准备编写统一插入数据库方法2  解决特殊字段的插入比如 blob
     * 统一方法的条件就是 表结构必须与返回的字段名称相一致才可以统一插入
     * @param strs 传入返回值比如为数据根元素
     * @param tablename 传入表名 方便统一插入
     * @param cols      传入blob字段名称  需与返回字段一致
     */
    public static void insertDB(String strs,String tablename,List<String> cols) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conn = DriverManager.getConnection("jdbc:oracle:thin:root/123456@192.168.1.86:1521:orcl", "root", "123456");
                if(strs!=null && !strs.equals("")){
                    conn.setAutoCommit(false);
                    //统一转换为list
                    List<Map<String, Object>> list = JSONArray.parseObject(strs, List.class);
                    if(list.size()>0){
                        for (Map<String, Object> maps : list) {
                            String sql = "INSERT INTO  "+tablename+"(";
                            String val = " values(";
                            Set<String> set = maps.keySet();
                            int i = 0;
                            for (String str :
                                    set) {
                                i++;
                                if (i == set.size()) {
                                    sql += str + ") ";
                                    val += "?)";
                                } else {
                                    sql += str + ",";
                                    val += "?,";
                                }
                            }
                            System.out.println(sql + val);
                            preparedStatement = conn.prepareStatement((sql + val));
                            for (Map<String, Object> maps2 : list) {
                                Set<String> set2 = maps.keySet();
                                int col = 0;
                                for (String str :set2) {
                                    col++;
                                    if(cols!=null&&cols.size()>0){
                                        if(cols.contains(str)){
                                            String ss = "";
                                            if(maps2.get(str)!=null){
                                                ss = maps2.get(str).toString();
                                            }
                                            InputStream inputStream = new ByteArrayInputStream(ss.getBytes());
                                            preparedStatement.setBlob(col,inputStream);
                                            continue;
                                        }
                                    }
                                    preparedStatement.setString(col, (String)maps2.get(str));
                                }
                                preparedStatement.addBatch();
                            }
                            preparedStatement.executeBatch();
                            conn.commit();
                            if(preparedStatement!=null){
                                preparedStatement.close();
                            }
                        }
                    }
                }
            }catch (Exception e){
                logger.info("接口调用插入数据异常：{}",e.getMessage());
            }finally {
                try{
                    if(conn!=null)conn.close();
                    if(preparedStatement!=null) preparedStatement.close();
                }catch (Exception e){
                    logger.info("接口调用插入数据异常：{}",e.getMessage());
                }
            }
        }catch(Exception e){
            logger.info("接口调用插入数据异常：{}",e.getMessage());
        }

    }

    public static void main(String[] args) throws Exception {

        StringBuffer buffer = new StringBuffer();

        //String url = "http://218.29.241.13:8830/tdjy_test/TDJYZtbMis_ZMD/Pages/Webservice/WebInfo.asmx";
         //String url = "http://218.29.241.13:8820/TPWebService/InfoToSharePlatform.asmx";
       // String url = "http://218.29.241.12:8830/tdjy/TDJYZtbMis_ZMD/Pages/Webservice/WebInfo.asmx";
         /*  String url2="http://218.29.241.12:8820/TPWebService/InfoToSharePlatform.asmx";//工程、采购、产权公告正式
        Map<String, Object> map = new HashMap<>();
        map.put("StartDate","2019-01-01");
        map.put("EndDate","2019-12-12");
        map.put("CategoryName","0101");
        map.put("Token","zmdggzy@123");
        Object webservice5 = getWebservice(url2, "2019-01-01", "2019-04-01", "0101", "zmdggzy@123","epo:");
        System.out.println(webservice5);*/
       /* JSONObject jsonObject = JSONObject.parseObject(webservice5.toString());
        String gongGaoInfo = jsonObject.getString("GongGaoInfo");
        List<String> cols = new ArrayList<>();
        cols.add("GongGaoContent");
        insertDB(gongGaoInfo,"ZMD_GGZYJYZX_GONGGAOINFO2",cols);*/






    }

    public static void insertDBforInterfaceArray(String out,String tablename){
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:interfaceDB/interfaceDB@59.207.219.23:1521:orcl", "interfaceDB", "interfaceDB");

            if(out!=null && !out.equals("")){
                List<Map<String, Object>> list = JSONArray.parseObject(out, List.class);
                if(list.size()>0){
                    for (Map<String, Object> maps : list) {
                        String sql = "INSERT INTO "+tablename+"(";
                        String val = " values(";
                        Set<String> set = maps.keySet();
                        int i = 0;
                        for (String str :
                                set) {
                            i++;
                            if (i == set.size()) {
                                sql += str + ") ";
                                val += "'" + maps.get(str) + "')";
                            } else {
                                sql += str + ",";
                                val += "'" + maps.get(str) + "',";
                            }

                        }
                        preparedStatement = conn.prepareStatement((sql + val));
                        int i1 = preparedStatement.executeUpdate();

                        if(preparedStatement!=null){
                            preparedStatement.close();
                        }
                    }

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(conn!=null){
                    conn.close();
                }

                if(preparedStatement!=null){
                    preparedStatement.close();
                }
            }catch (Exception e){}



        }
    }

    public static void insertDBforInterface(String out,String tablename){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:interfaceDB/interfaceDB@59.207.219.23:1521:orcl", "interfaceDB", "interfaceDB");
            if(out!=null && !out.equals("")){
                Map<String, Object> map = JSONObject.parseObject(out, Map.class);
                if(map!=null&&map.size()>0){
                    String sql = "INSERT INTO "+tablename+"(";
                    String val = " values(";
                    Set<String> set = map.keySet();
                    int i = 0;
                    for (String str : set) {
                        i++;
                        if (i == set.size()) {
                            sql += str + ") ";
                            val += "'" + map.get(str) + "')";
                        } else {
                            sql += str + ",";
                            val += "'" + map.get(str) + "',";
                        }
                    }
                    preparedStatement = conn.prepareStatement((sql + val));
                    int i1 = preparedStatement.executeUpdate();

                    if(preparedStatement!=null){
                        preparedStatement.close();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(conn!=null){
                    conn.close();
                }

                if(preparedStatement!=null){
                    preparedStatement.close();
                }
            }catch (Exception e){}



        }
    }
}
