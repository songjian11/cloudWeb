package com.bbg.purchaseplan.feignService;

import com.alibaba.fastjson.JSONObject;
import com.bbg.purchaseplan.exception.BussinessException;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
@Slf4j
@Component
public class DepotFeignServiceFallback implements FallbackFactory<DepotFeignService> {
    @Override
    public DepotFeignService create(Throwable throwable) {
        return new DepotFeignService() {
            @Override
            public JSONObject getSupplierGoods(Map<String, String> map) {
                log.error("\n访问远程服务异常:\n【服务名】:{}\n【请求地址】:{}\n【请求参数】:{}\n",
                        "distributionmanage", "serviceApi/getSupplierGoods", map.toString());
                throw new BussinessException("访问远程服务[distributionmanage]异常");
            }

            @Override
            public JSONObject getSupplierGoodView(Map<String, String> map) {
                log.error("\n访问远程服务异常:\n【服务名】:{}\n【请求地址】:{}\n【请求参数】:{}\n",
                        "distributionmanage", "serviceApi/getSupplierGoodView", map.toString());
                throw new BussinessException("访问远程服务[distributionmanage]异常");
            }
        };
    }
}
