package com.bbg.purchaseplan.service;

import com.bbg.purchaseplan.bo.response.GoodsOrderInfoResponse;
import com.bbg.purchaseplan.bo.request.SupplierOrderInfoRequest;
import com.bbg.purchaseplan.bo.response.DepotOrderDetailResponse;
import com.bbg.purchaseplan.bo.response.OrderSumInfoResponse;

import java.util.List;

/**
 * 采购计划-订单信息查询和订货
 * songjian
 * 20191107
 */
public interface OrderService {
    /**
     * 根据工号查询订单汇总信息
     * @param personId 工号
     * @return
     * @throws Exception
     */
    OrderSumInfoResponse queryOrderSumInfoByPersonId(String personId)throws Exception;

    /**
     * 根据工号查询仓库商品订单明细
     * @param personId 工号
     * @return
     * @throws Exception
     */
    DepotOrderDetailResponse queryDepotOrderDetailByPersonId(String personId)throws Exception;

    /**
     * 保存供应商下单信息
     * @param param 供应商下单信息
     * @throws Exception
     */
    void saveOrder(SupplierOrderInfoRequest param)throws Exception;

    /**
     * 单价是否合法
     * @param goodsCode 商品编号
     * @param supplierCode 供应商编号
     * @param price 单价
     * @return
     * @throws Exception
     */
    String isLegalPriceBygoodsCodeAndsupplierCodeAndPrice(String depotCode, String goodsCode, String supplierCode, String price) throws Exception;

    /**
     * 根据供应商编号,仓库编号和要货时间查询商品列表
     * @param personId 工号
     * @param supplierCode 供应商编号
     * @param depotCode 仓库编号
     * @param purchaseTime 要货时间
     * @return
     * @throws Exception
     */
    List<GoodsOrderInfoResponse> queryGoodsInfo(String personId, String supplierCode, String depotCode, String purchaseTime) throws Exception;

    /**
     * 同步订单
     */
    void transferOrderData() throws Exception;
}
