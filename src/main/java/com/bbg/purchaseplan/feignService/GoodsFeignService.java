package com.bbg.purchaseplan.feignService;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "commoditymanagement", fallbackFactory = GoodsFeignServiceFallback.class, decode404 = true)
//@FeignClient(value = "commoditymanagement", url = "http://192.168.7.162:30082/commoditymanagement", fallbackFactory = GoodsFeignServiceFallback.class, decode404 = true)
public interface GoodsFeignService {
    @RequestMapping("itemMaster/showItemMaster")
    JSONObject showItemMaster(@RequestParam Map<String, String> map);
}
