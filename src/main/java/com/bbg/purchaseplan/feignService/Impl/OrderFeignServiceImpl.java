package com.bbg.purchaseplan.feignService.Impl;

import com.alibaba.fastjson.JSONObject;
import com.bbg.purchaseplan.dto.SupplierFeignDto;
import com.bbg.purchaseplan.feignService.OrderFeignService;
import com.bbg.purchaseplan.dto.SupplierGoodsFeignDto;
import com.bbg.purchaseplan.dto.SupplierGoodsListFeignDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单相关的远程服务调用
 * songjian
 * 20191108
 */
@Slf4j
@Component
public class OrderFeignServiceImpl extends AbstractFeignService {
    @Autowired
    OrderFeignService orderFeignService;

    /**
     * 根据供应商查询商品信息
     * @param supplier 供应商查编号
     * @return
     */
    public List<SupplierGoodsFeignDto> selectGoodsByCondition(String supplier){
        JSONObject json = orderFeignService.selectGoodsByCondition(supplier);
        log.debug("======================selectGoodsByConditionParam:{}", supplier);
        List<SupplierGoodsFeignDto> goodsList = json2List(json, SupplierGoodsFeignDto.class);
        log.debug("======================selectGoodsByCondition:{}", goodsList.toString());
        return goodsList;
    }

    /**
     * 根据商品编号，仓库编号查询商品信息
     * @param itemCode 商品编号
     * @param loc 仓库编号
     * @return
     */
    public List<SupplierGoodsFeignDto> selectGoodsByItemAndLoc(String itemCode, String loc){
        JSONObject json = orderFeignService.selectGoodsByItemAndLoc(itemCode, transformDepotCode(loc));
        log.debug("======================selectGoodsByItemAndLocParam:{},{}", itemCode, transformDepotCode(loc));
        List<SupplierGoodsFeignDto> goodsList = json2List(json, SupplierGoodsFeignDto.class);
        log.debug("======================selectGoodsByItemAndLoc:{}", goodsList.toString());
        return goodsList;
    }

    /**
     * 根据商品编号，供应商编号查询供应商信息和商品信息
     * @param itemCode 商品编号
     * @param supplier 供应商查编号
     * @return
     */
    public List<SupplierGoodsListFeignDto> selectSuppliersByItemAndSupplier(String itemCode, String supplier){
        JSONObject json = orderFeignService.selectSuppliersByItemAndSupplier(itemCode, supplier);
        log.debug("======================selectSuppliersByItemAndSupplierParam:{},{}", itemCode, supplier);
        List<SupplierGoodsListFeignDto> supplierGoodsList = json2List(json, SupplierGoodsListFeignDto.class);
        log.debug("======================selectSuppliersByItemAndSupplier:{}", supplierGoodsList.toString());
        return supplierGoodsList;
    }

    /**
     * supplier
     * @param supplier 供应商查编号
     * @return
     */
    public SupplierGoodsListFeignDto getSupplierInfo(String supplier){
        JSONObject json = orderFeignService.getSupplierInfoo(supplier);
        log.debug("======================supplierGoodsListFeignModelParam:{}", supplier);
        SupplierGoodsListFeignDto supplierGoodsListFeignModel = json2Object(json, SupplierGoodsListFeignDto.class);
        log.debug("======================getSupplierInfo:{}", supplierGoodsListFeignModel.toString());
        return supplierGoodsListFeignModel;
    }

    /**
     * 查询供应商
     * @param itemCode 商品编号
     * @param supplier 供应商编号
     * @param loc 仓库编号
     * @return
     */
    public SupplierFeignDto selectSuppOne(String itemCode, String supplier, String loc){
        Map<String, String> map = new HashMap<>();
        map.put("itemCode", itemCode);
        map.put("supplier", supplier);
        map.put("loc", transformDepotCode(loc));
        map.put("storeOrderInd", "Y");
        JSONObject json = orderFeignService.selectSuppOne(map);
        log.debug("======================selectSuppOneParam:{}", map);
        List<SupplierFeignDto> supplierFeignDtos = json2List(json, SupplierFeignDto.class);
        log.debug("======================selectSuppOne:{}", supplierFeignDtos.toString());
        return (null == supplierFeignDtos || 0 == supplierFeignDtos.size()) ? null : supplierFeignDtos.get(0);
    }
}
