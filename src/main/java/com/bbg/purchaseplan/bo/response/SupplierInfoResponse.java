package com.bbg.purchaseplan.bo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 供应商信息
 * songjian
 */
@Data
@ApiModel(value = "SupplierInfoResponse", description = "供应商信息")
public class SupplierInfoResponse {
    @ApiModelProperty(value = "供应商编码", required = true)
    private String suppCode;
    @ApiModelProperty(value = "供应商名称", required = true)
    private String suppName;
}