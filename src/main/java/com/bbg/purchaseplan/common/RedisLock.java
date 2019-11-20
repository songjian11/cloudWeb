package com.bbg.purchaseplan.common;

import com.bbg.purchaseplan.exception.BussinessException;
import com.bbg.purchaseplan.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 分布式锁
 * songjian
 * 20191107
 */
@Slf4j
@Component
public class RedisLock {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 加锁
     * @param key 加锁键
     * @param timeout 锁过期时间(单位:毫秒)
     * @return
     */
    public boolean lock(String key, long timeout){
        String value = String.valueOf(timeout + System.currentTimeMillis());
        if(redisTemplate.opsForValue().setIfAbsent(key,value)){
            return true;
        }
        //判断锁超时,防止死锁
        String currentValue = (String)redisTemplate.opsForValue().get(key);
        //如果锁过期
        if(!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()){
            //获取上一个锁的时间value
            String oldValue = (String) redisTemplate.opsForValue().getAndSet(key,value);
            if(!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue) ){
                //校验是不是上个对应的商品时间戳,也是防止并发
                return true;
            }
        }
        return false;
    }

    /**
     * 解锁
     * @param key
     * @param value
     */
    public void unlock(String key,String value){
        try {
            String currentValue =  (String) redisTemplate.opsForValue().get(key);
            if(!StringUtils.isEmpty(currentValue) && currentValue.equals(value) ){
                redisTemplate.opsForValue().getOperations().delete(key);//删除key
            }
        } catch (Exception e) {
            log.error("解锁出现异常了，{}",e);
            throw new BussinessException("解锁出现异常了:" + e.getMessage());
        }
    }
}
