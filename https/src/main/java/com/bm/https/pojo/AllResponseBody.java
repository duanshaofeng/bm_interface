package com.bm.https.pojo;

import java.util.HashMap;
import java.util.Map;

/**统一返回实体类
 * Created by 86133 on 2020/6/10.
 */
public class AllResponseBody {

    private String code;
    private String message;


    private Object data;



    public static Object failure(Object message){
        Map<Object, Object> map = new HashMap<>();
        map.put("code","111");
        map.put("message",message);
        map.put("data",null);
        return map;
    }
    public static Object success(Object data){
        Map<Object, Object> map = new HashMap<>();
        map.put("code","000");
        map.put("message","调用成功");
        map.put("data",data);
        return map;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
