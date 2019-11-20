package com.bbg.purchaseplan.bo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 供应商下单信息
 * songjian
 * 20191107
 */
@Data
@ApiModel(value = "SupplierOrderInfoRequest", description = "供应商下单信息")
public class SupplierOrderInfoRequest {
    @ApiModelProperty(value = "供应商编码", required = true)
    private String suppCode;
    @ApiModelProperty(value = "供应商名称", required = true)
    private String suppName;
    @ApiModelProperty(value = "仓库编码", required = true)
    private String depotCode;
    @ApiModelProperty(value = "仓库名称", required = true)
    private String depotName;
    @ApiModelProperty(value = "要货时间(格式：yyyyMMdd)", required = true)
    private String purchaseTime;
    @ApiModelProperty(value = "订货人工号", required = true)
    private String orderPersonId;
    @ApiModelProperty(value = "订货人名称", required = true)
    private String orderPersonName;
    @ApiModelProperty(value = "商品下单信息", required = false)
    private List<GoodsOrderInfoRequest> goodsList;
}
