package com.bbg.purchaseplan.utils;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.ListOrderedMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HistoricalUtil {
	
	
	
	public static List<Map<String, Object>> parseJSON2List(String json) {

        JSONArray jsonArr = JSONArray.fromObject(json);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Iterator<JSONObject> it = jsonArr.iterator();

        

        while (it.hasNext()) {

            JSONObject json2 = it.next();

            list.add(parseJSON2Map(json2.toString()));

        }
   

        return list;

    }

 

    public static Map<String, Object> parseJSON2Map(String jsonStr) {

        ListOrderedMap map = new ListOrderedMap();

        // 最外层解析

        JSONObject json = JSONObject.fromObject(jsonStr);

 

        for (Object k : json.keySet()) {

            Object v = json.get(k);

            // 如果内层还是数组的话，继续解析

            if (v instanceof JSONArray) {

                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

 

                Iterator<JSONObject> it = ((JSONArray) v).iterator();

                while (it.hasNext()) {

                    JSONObject json2 = it.next();

                    list.add(parseJSON2Map(json2.toString()));

                }

                map.put(k.toString(), list);

            } else {

                map.put(k.toString(), v);

            }

        }

 

        Iterator iterator = map.keySet().iterator();

        List<String> lsList = new ArrayList<String>();

        int d=0;

 

        while (iterator.hasNext()) {

            

            String key = (String) iterator.next();

            Object object = map.get(key);    

            

            // 进行遍历删除，当值为空的时候删除这条数据

            if (object.equals("")) {

                iterator.remove();

                map.remove(key);

            }

            

        }

        return map;

    }
}
