package com.bbg.purchaseplan.feignService.Impl;

import com.alibaba.fastjson.JSONObject;
import com.bbg.purchaseplan.dto.GoodsFeignDto;
import com.bbg.purchaseplan.feignService.GoodsFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

@Component
public class GoodsFeignServiceImpl extends AbstractFeignService{
    @Autowired
    GoodsFeignService goodsFeignService;

    /**
     * 合并根据是商品编号查询商品信息的请求
     * @param itemId
     * @return
     */
    @HystrixCollapser(batchMethod = "showItemMaster", collapserProperties = {
            @HystrixProperty(name="timerDelayInMilliseconds", value = "1000")
    })
    public Future<GoodsFeignDto> collapsingShowItemMaster(String itemId) {
        return null;
    }

    /**
     * 根据是商品编号查询商品信息
     * @param itemIds 商品编号
     * @return
     */
    @HystrixCommand
    private List<GoodsFeignDto> showItemMaster(List<String> itemIds){
        System.out.println("collapsingList当前线程" + Thread.currentThread().getName());
        System.out.println("当前请求参数个数:" + itemIds.size());
        List<GoodsFeignDto> result = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        for(String itemId : itemIds){
            map.put("itemId", itemId);
            JSONObject json = goodsFeignService.showItemMaster(map);
            result.add(json2Object(json, GoodsFeignDto.class));
        }
        System.out.println("当前请求参数:" + itemIds.toString());
        return result;
    }
}
