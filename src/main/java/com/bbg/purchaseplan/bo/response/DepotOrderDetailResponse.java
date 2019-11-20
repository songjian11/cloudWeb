package com.bbg.purchaseplan.bo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 仓库订单明细
 * songjian
 * 20191107
 */
@Data
@ApiModel(value = "DepotOrderDetailResponse", description = "仓库订单明细")
public class DepotOrderDetailResponse {
    @ApiModelProperty(value = "仓库编码", required = true)
    private String depotCode;
    @ApiModelProperty(value = "仓库名称", required = true)
    private String depotName;
    @ApiModelProperty(value = "要货时间(格式yyyyMMdd)", required = true)
    private String purchaseTime;
    @ApiModelProperty(value = "商品订单明细", required = true)
    private List<GoodsOrderDetailResponse> goodsList;
}
