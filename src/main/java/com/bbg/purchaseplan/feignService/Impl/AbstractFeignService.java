package com.bbg.purchaseplan.feignService.Impl;

import com.alibaba.fastjson.JSONObject;
import com.bbg.purchaseplan.utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工具父类
 */
public class AbstractFeignService {
    /**
     * json转换实体类
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    protected static <T extends Object> T json2Object(JSONObject json, Class<T> clazz){
        T result = null;
        if(null == json || json.isEmpty()){
            return result;
        }
        if(StringUtils.isNotEmpty(String.valueOf(json.get("code"))) && ("0000".equals(String.valueOf(json.get("code")))
        || "0".equals(String.valueOf(json.get("code"))))){
            String str = null;
            if(json.containsKey("result")){
                str = JSONObject.toJSONString(json.get("result"));
            }else{
                str = JSONObject.toJSONString(json.get("object"));
            }
            result = JSONObject.parseObject(str, clazz);
        }
        return result;
    }

    /**
     * json转换集合List<T>
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    protected static <T extends Object> List<T> json2List(JSONObject json, Class<T> clazz){
        List<T> result = null;
        if(null == json || json.isEmpty()){
            return result;
        }
        if(StringUtils.isNotEmpty(String.valueOf(json.get("code"))) && ("0000".equals(String.valueOf(json.get("code")))
                || "0".equals(String.valueOf(json.get("code"))))){
            String str = null;
            if(json.containsKey("result")){
                str = JSONObject.toJSONString(json.get("result"));
            }else{
                str = JSONObject.toJSONString(json.get("object"));
            }
            result = JSONObject.parseArray(str, clazz);
        }
        return result;
    }

    public static String transformDepotCode(String supplier){
        Map<String, String> map = new HashMap<String, String>();
        map.put("110010", "118010");
        map.put("110012", "118012");
        map.put("110030", "118030");
        map.put("110041", "118041");
        map.put("110033", "118033");
        if(map.containsKey(supplier)){
            return map.get(supplier);
        }else{
            return supplier;
        }
    }
}
