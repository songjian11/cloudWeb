package com.bbg.purchaseplan.bo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单汇总信息
 * songjian
 * 20191107
 */
@ApiModel(value = "OrderSumInfoResponse", description = "订单汇总信息")
@Data
public class OrderSumInfoResponse {
    @ApiModelProperty(value = "需定商品数量(单位:个)", required = true)
    private String purchaseGoodsNum = "0";
    @ApiModelProperty(value = "已定商品数量(单位:个)", required = true)
    private String hasGoodsNum = "0";
}
