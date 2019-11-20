package com.bbg.purchaseplan.feignService;

import com.alibaba.fastjson.JSONObject;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
@Slf4j
@Component
public class GoodsFeignServiceFallback implements FallbackFactory<GoodsFeignService> {
    @Override
    public GoodsFeignService create(Throwable throwable) {
        return new GoodsFeignService(){

            @Override
            public JSONObject showItemMaster(Map<String, String> map) {
                return null;
            }
        };
    }
}
