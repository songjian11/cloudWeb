package com.bbg.purchaseplan.feignService;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 订单相关的远程服务调用
 * songjian
 * 20191108
 */
//@Headers({"Content-Type: application/json", "Accept: application/json" })
@FeignClient(name = "scm-supp", fallbackFactory = OrderFeignServiceFallback.class, decode404 = true)
//@FeignClient(name = "scm-supp" ,url ="http://192.168.7.162:30082/scm-supp", fallbackFactory = OrderFeignServiceFallback.class, decode404 = true)
public interface OrderFeignService {
    /**
     * 根据供应商查询商品信息
     * @param supplier
     * @return
     */
    @RequestMapping(value = "supplierItemmasterRef/selectGoodsByCondition")
    JSONObject selectGoodsByCondition(@RequestParam String supplier);//供应商查询商品

    /**
     * 根据商品编号，仓库编号查询商品信息
     * @param itemCode
     * @param loc
     * @return
     */
    @RequestMapping(value = "supplierItemmasterRef/selectGoodsByItemAndLoc")
    JSONObject selectGoodsByItemAndLoc(@RequestParam("itemCode") String itemCode, @RequestParam("loc") String loc);//商品+仓库查询商品

    /**
     * 根据商品编号，供应商编号查询供应商信息和商品信息
     * @param itemCode
     * @param supplier
     * @return
     */
    @RequestMapping(value = "supplierItemmasterRef/selectSuppliersByItemAndSupplier")
    JSONObject selectSuppliersByItemAndSupplier(@RequestParam("itemCode") String itemCode, @RequestParam("supplier") String supplier);//商品+供应商查询供应商+商品

    /**
     * 根据供应商查询供应商信息
     * @param supplier 供应商编号
     * @return
     */
    @RequestMapping(value = "supplier/getSupplierInfoo")
    JSONObject getSupplierInfoo(@RequestParam String supplier);


    /**
     * 根据供应商编号，商品编号，仓库编号查询供应商
     * @param map
     * @return
     */
    @RequestMapping(value = "supplierItemmasterLocRef/selectSuppOne")
    JSONObject selectSuppOne(@RequestParam Map<String, String> map);
}
