package com.bbg.purchaseplan.bo.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商品下单信息
 * songjian
 * 20191107
 */
@Data
@ApiModel(value = "GoodsOrderInfoRequest", description = "商品下单信息")
public class GoodsOrderInfoRequest {
    @ApiModelProperty(value = "商品编码", required = true)
    private String goodsCode;
    @ApiModelProperty(value = "商品名称", required = true)
    private String goodsName;
    @ApiModelProperty(value = "单价(单位:元)", required = true)
    private String price;
    @ApiModelProperty(value = "采购数量", required = true)
    private String goodsNum;
    // 大类编号
    @JsonIgnore
    private String dept;
}
