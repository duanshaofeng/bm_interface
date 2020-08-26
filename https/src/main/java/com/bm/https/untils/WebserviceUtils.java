package com.bm.https.untils;


import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.stereotype.Component;

@Component
public class WebserviceUtils {


    public static void main(String[] args) {


        Object syncRegister = webserviceClient("http://59.207.226.162:9111/DataSyncSkipService.asmx?WSDL", "SyncRegister", "11", "22","","");
        System.out.println(syncRegister);

    }


    public Object webserviceClient(String url,String json){
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://59.207.223.248:8080?wsdl");

        Object[] objects = new Object[0];
        try {

            objects = client.invoke("GetGovernmentData", json);
            System.out.println("返回数据:" + objects[0]);
            return objects[0];

        } catch (java.lang.Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


    public static Object webserviceClient(String url,String method, String  baseinfo,String attrinfo,String forminfo,String postinfo){
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(url);

        Object[] objects = new Object[0];
        try {

            objects = client.invoke(method, baseinfo,attrinfo,forminfo,postinfo);
            System.out.println("返回数据:" + objects[0]);
            return objects[0];

        } catch (java.lang.Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


}
