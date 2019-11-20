package com.bbg.purchaseplan.feignService.Impl;

import com.alibaba.fastjson.JSONObject;
import com.bbg.purchaseplan.dto.DepotGoodsFeignDto;
import com.bbg.purchaseplan.feignService.DepotFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@Component
public class DepotFeignServiceImpl extends AbstractFeignService {
    @Autowired
    DepotFeignService depotFeignService; 

    /**
     * 根据工号和要货时间查询仓库商品信息
     * @param empNo 工号
     * @param createdate(格式:yyyyMMdd) 要货时间
     * @return
     */
    public List<DepotGoodsFeignDto> getSupplierGoods(String empNo, String createdate){
        Map<String, String> map = new HashMap<>();
        map.put("dept", "36,37");
        map.put("empNo", empNo);
//        map.put("createdate", "20180306");
        map.put("createdate", createdate);
        log.debug("======================getSupplierGoodsParam:{}", map.toString());
        JSONObject json = depotFeignService.getSupplierGoods(map);
        log.debug("======================getSupplierGoods:{}", json.toJSONString());
        return json2List(json, DepotGoodsFeignDto.class);
    }

    /**
     * 根据工号+商品编号+要货时间+仓库编号查询仓库商品信息
     * @param empNo 工号
     * @param goodscode 商品编号（非必填）
     * @param createdate(格式:yyyyMMdd) 要货时间
     * @param whld 仓库编号
     * @return
     */
    public List<DepotGoodsFeignDto> getSupplierGoodView(String empNo, String goodscode, String createdate, String whld) {
        Map<String, String> map = new HashMap<>();
        map.put("dept", "36,37");
        map.put("empNo", empNo);
        map.put("goodscode", goodscode);
//        map.put("createdate", "20180306");
        map.put("createdate", createdate);
        map.put("whld", whld);
        log.debug("======================getSupplierGoodViewParam:{}", map.toString());
        JSONObject json = depotFeignService.getSupplierGoodView(map);
        log.debug("======================getSupplierGoodView:{}", json.toJSONString());
        return json2List(json, DepotGoodsFeignDto.class);
    }
}
