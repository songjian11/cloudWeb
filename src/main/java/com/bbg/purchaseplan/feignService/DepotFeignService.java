package com.bbg.purchaseplan.feignService;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "distributionmanage", fallbackFactory = DepotFeignServiceFallback.class, decode404 = true)
//@FeignClient(value = "distributionmanage", url = "http://192.168.7.162:30082/distributionmanage", fallbackFactory = DepotFeignServiceFallback.class, decode404 = true)
public interface DepotFeignService {
    @GetMapping(value = "serviceApi/getSupplierGoods")
    JSONObject getSupplierGoods(@RequestParam Map<String, String> map);

    @GetMapping(value = "serviceApi/getSupplierGoodView")
    JSONObject getSupplierGoodView(@RequestParam Map<String, String> map);
}
