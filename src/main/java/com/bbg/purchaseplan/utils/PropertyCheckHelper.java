package com.bbg.purchaseplan.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 参数校验
 * 例子:
 * PropertyCheckHelper.getInstance()
 *          .put(loginUserId, "loginUserId")
 *          .put(branId, "branId")
 *          .put(shelfNo, "shelfNo")
 *          .put(newShelfNo, "newShelfNo")
 *          .checkParameter();
 * @ClassName: CheckParametersUtil
 * @Description:TODO
 *
 *
 */
public class PropertyCheckHelper  {

    private static Logger logger = LoggerFactory.getLogger(PropertyCheckHelper.class);

    private Map<String, Object> map = new HashMap<>();

    /**
     * 添加需要校验的参数
     * @param object 实参
     * @param parameterName 参数名称
     * @return CheckParametersUtil
     * @author: XuWei
     */
    public PropertyCheckHelper put(Object object, String parameterName) {
        map.put(parameterName, object);
        return this;
    }
    /**
     * 获取CheckParametersUtil实例
     * @return CheckParametersUtil
     * @author: XuWei
     */
    public static PropertyCheckHelper getInstance(){
        return new PropertyCheckHelper();
    }

    /**
     * 校验
     * @return DataMessage
     * @author: XuWei
     * @throws Exception
     */
    public String checkParameter(){
        String msg = null;
        for(Entry<String, Object> entry : map.entrySet()) {
            if(UtilValidate.isEmpty(entry.getValue())){
                logger.info("参数【"+ entry.getKey() +"】为空");
                msg = "参数【"+ entry.getKey() + "】为空";
                return msg;
            }
        }
        return msg;
    }
}