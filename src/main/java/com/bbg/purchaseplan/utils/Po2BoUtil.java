package com.bbg.purchaseplan.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * po(表实体)转换为bo(视图实体)
 * 注意：po和bo属性名必须相同
 * songjian
 * 20191107
 */
public class Po2BoUtil {
    /**
     * po集合转换为bo集合
     * @return
     */
    public static <T extends Object, K extends Object> List<T> listTransform(List<K> sources, Class<T> clazz)
            throws Exception {
        List<T> result = new ArrayList<T>();
        if(null == sources && 0 == sources.size()){
            return result;
        }
        for(K obj : sources){
            T target = (T) Class.forName(clazz.getName()).newInstance();
            BeanUtils.copyProperties(obj, target);
            result.add(target);
        }
        return result;
    }
}
