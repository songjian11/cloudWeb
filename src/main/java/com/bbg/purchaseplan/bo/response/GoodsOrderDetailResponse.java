package com.bbg.purchaseplan.bo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商品订单明细
 * songjian
 * 20191107
 */
@Data
@ApiModel(value = "GoodsOrderDetailResponse", description = "商品订单明细")
public class GoodsOrderDetailResponse {
    @ApiModelProperty(value = "供应商编号", required = true)
    private String suppCode;
    @ApiModelProperty(value = "供应商名称", required = true)
    private String suppName;
    @ApiModelProperty(value = "商品编码", required = true)
    private String goodsCode;
    @ApiModelProperty(value = "商品名称",required = true)
    private String goodsName;
    @ApiModelProperty(value = "商品库存数量(单位:个)", required = true)
    private String goodsStockNum;
    @ApiModelProperty(value = "门店订货合计数量(单位:个)", required = true)
    private String storeGoodsSumNum;
    @ApiModelProperty(value = "单价(单位:元)", required = true)
    private String price;
    @ApiModelProperty(value = "建议订货数量(单位:个)", required = true)
    private String recommendGoodsNum;
    @ApiModelProperty(value = "已订货数量(单位:个)", required = true)
    private String hasGoodsNum;
}
