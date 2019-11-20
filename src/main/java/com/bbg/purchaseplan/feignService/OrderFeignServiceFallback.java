package com.bbg.purchaseplan.feignService;

import com.alibaba.fastjson.JSONObject;
import com.bbg.purchaseplan.exception.BussinessException;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class OrderFeignServiceFallback implements FallbackFactory<OrderFeignService> {
    @Override
    public OrderFeignService create(Throwable throwable) {
        return new OrderFeignService() {
            @Override
            public JSONObject selectGoodsByCondition(String supplier) {
                log.error("\n访问远程服务异常:\n【服务名】:{}\n【请求地址】:{}\n【请求参数】:{}\n",
                        "scm-supp", "supplierItemmasterRef/selectGoodsByCondition", supplier);
                throw new BussinessException("访问远程服务[scm-supp]异常");
            }

            @Override
            public JSONObject selectGoodsByItemAndLoc(String itemCode, String loc) {
                log.error("\n访问远程服务异常:\n【服务名】:{}\n【请求地址】:{}\n【请求参数】:{},{}\n",
                        "scm-supp", "supplierItemmasterRef/selectGoodsByItemAndLoc", itemCode, loc);
                throw new BussinessException("访问远程服务[scm-supp]异常");
            }

            @Override
            public JSONObject selectSuppliersByItemAndSupplier(String itemCode, String supplier) {
                log.error("\n访问远程服务异常:\n【服务名】:{}\n【请求地址】:{}\n【请求参数】:{},{}\n",
                        "scm-supp", "supplierItemmasterRef/selectSuppliersByItemAndSupplier", itemCode, supplier);
                throw new BussinessException("访问远程服务[scm-supp]异常");
            }

            @Override
            public JSONObject getSupplierInfoo(String supplier) {
                log.error("\n访问远程服务异常:\n【服务名】:{}\n【请求地址】:{}\n【请求参数】:{}\n",
                        "scm-supp", "supplierItemmasterRef/getSupplierInfoo", supplier);
                throw new BussinessException("访问远程服务[scm-supp]异常");
            }

            @Override
            public JSONObject selectSuppOne(Map<String, String> map) {
                log.error("\n访问远程服务异常:\n【服务名】:{}\n【请求地址】:{}\n【请求参数】:{}\n",
                        "scm-supp", "supplierItemmasterLocRef/selectSuppOne", map.toString());
                throw new BussinessException("访问远程服务[scm-supp]异常");
            }
        };
    }
}
