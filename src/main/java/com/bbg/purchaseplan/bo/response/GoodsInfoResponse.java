package com.bbg.purchaseplan.bo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "GoodsInfoResponse", description = "商品信息")
public class GoodsInfoResponse {
    @ApiModelProperty(value = "商品编码", required = true)
    private String goodsCode;
    @ApiModelProperty(value = "商品名称",required = true)
    private String goodsName;
}
